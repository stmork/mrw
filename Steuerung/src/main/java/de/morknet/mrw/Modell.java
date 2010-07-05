/*
**
**	$Filename:	Modell.java $
**	$Revision$
**	$Date$
**	$Author$
**	$Id$
**
**	Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
**
**	This program and the accompanying materials are made available under the
**	terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
**	which accompanies this distribution.
**
**	The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
**
**
*/

package de.morknet.mrw;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.morknet.mrw.automatic.beermode.BeerMode;
import de.morknet.mrw.automatic.tourmode.TourMode;
import de.morknet.mrw.base.Abschnitt;
import de.morknet.mrw.base.DKW;
import de.morknet.mrw.base.DeviceUnit;
import de.morknet.mrw.base.Gleis;
import de.morknet.mrw.base.Gleisteil;
import de.morknet.mrw.base.Gruppe;
import de.morknet.mrw.base.MicroController;
import de.morknet.mrw.base.NamedElement;
import de.morknet.mrw.base.Signal;
import de.morknet.mrw.base.Verzweigung;
import de.morknet.mrw.base.Weiche;
import de.morknet.mrw.batch.Batch;
import de.morknet.mrw.gui.info.BeerModeInfo;
import de.morknet.mrw.gui.info.TourInfo;
import de.morknet.mrw.util.LogUtil;
import de.morknet.mrw.util.NameUtil;

/**
 * Diese Klasse bildet den Container einer gesamten Modelleisenbahnanlage. Sie beinhalten hierarchisch
 * sortiert Betriebsgruppen, darin enthaltene Gleisabschnitte sowie deren Gleisteile und Signale.
 * @author smork
 *
 */
abstract public class Modell extends NamedElement
{
	private static final long serialVersionUID = 1L;

	private final Log log;
	private final static int               ROUTE_DEPTH     = 100;
	
	/**
	 * Zuordnung von Betriebsgruppennamen auf Betriebsgruppe.
	 */
	private final Map<String,  Gruppe>     groups          = new HashMap<String, Gruppe>();

	/**
	 * Zuordnung von Abschnittsname auf Abschnitt.
	 */
	private final Map<String,  Abschnitt>  segments        = new HashMap<String, Abschnitt>();

	/**
	 * Zuordnung von Gleisname auf Gleisteil.
	 */
	private final Map<String,  Gleisteil>  trackElements   = new HashMap<String, Gleisteil>();

	/**
	 * Zuordnung von Signalnummer auf Signal.
	 */
	private final Map<String,  Signal>     signals         = new HashMap<String, Signal>();

	/**
	 * Zuordnung von Gerätename auf Gerät.
	 */
	private final Map<String,  DeviceUnit> magneticPart    = new HashMap<String, DeviceUnit>();

	/**
	 * Zuordnung von Controller-ID auf Mikrocontroller.
	 */
	private final Map<Integer, MicroController> controller = new TreeMap<Integer, MicroController>(); 

	private final static boolean verbose = false;

	/**
	 * Dieser Konstruktur initialisiert eine Modelleisenbahnanlage.
	 * @param name Der Name der Anlage.
	 */
	protected Modell(String name)
	{
		super(name);
		log = LogFactory.getLog(name);
	}

	/**
	 * Das hier ist eine Krücke, weil Log4j nicht zwischen DEBUG und TRACE unterscheidet.
	 * @return Ob TRACE Logging durchgeführt werden soll.
	 */
	public static boolean isVerbose()
	{
		return verbose;
	}

	/**
	 * Diese Methode fügt dem Modell einen weiteren Gleisabschnitt hinzu.
	 * @param a Der weitere Gleisabschnitt.
	 */
	protected void add(Abschnitt a)
	{
		segments.put(a.getNumber(), a);
	}

	/**
	 * Diese Methode fügt einem Gleisabschnitt ein Gleisteil hinzu. Falls das Gleisteil
	 * eine Verzweigung ist, wird diese den Magnetartikeln hinzugefügt.
	 * @param a Der Gleisabschnitt, der das Gleisteil erhalten soll.
	 * @param g Das weitere Gleisteil.
	 */
	public void add(Abschnitt a, Gleisteil g)
	{
		a.add(g);
		trackElements.put(g.getName(), g);
		if (g instanceof Verzweigung)
		{
			magneticPart.put(g.getName(), g);
		}
	}

	/**
	 * Diese Methode fügt einem Gleisabschnitt ein Signal hinzu. Falls das Signal
	 * ein Formsignal ist, wird dieses den Magnetartikeln hinzugefügt.
	 * @param a Der Gleisabschnitt, der das Signal erhalten soll.
	 * @param s Das weitere Signal.
	 */
	public void add(Abschnitt a, Signal s)
	{
		a.add(s);
		signals.put(s.getName(), s);
		if (s instanceof de.morknet.mrw.base.Formsignal)
		{
			magneticPart.put(s.getNumber(), s);
		}
	}

	/**
	 * Diese Methode fügt dem Modelll einen weiteren Mikrocontroller hinzu.
	 * @param c Der weitere Mikrocontroller.
	 */
	public void add(MicroController c)
	{
		controller.put(c.getId(), c);
	}

	/**
	 * Diese Methode fügt eine Betriebsgruppe dem Modell hinzu.
	 * @param g Die weitere Betriebsgruppe.
	 */
	protected void add(Gruppe g)
	{
		groups.put(g.getName(), g);
	}
	
	/**
	 * Schaltet die ganze Anlage aus. Die Kommandos werden in den angegebenen Batch aufgelistet.
	 * @param batch Der Batch mit den Kommandos.
	 * @see Abschnitt
	 */
	public void off(Batch batch)
	{
		for (Gruppe g : getGroups())
		{
			for (Abschnitt a : g.getSegments())
			{
				a.off(batch, true);
			}
		}
	}
	
	/**
	 * Diese Methode fragt den Zustand der Anlage ab. Alle Gleisbesetztmelder und alle
	 * Weichen mit Weichenlagemeldung werden abgefragt.
	 * @param batch Der Batch, in den die Frage-Kommandos eingereiht werden.
	 */
	public void getState(Batch batch)
	{
		for (Gruppe g : getGroups())
		{
			for (Abschnitt a : g.getSegments())
			{
				batch.addBatchElement(a, a.createQuestionMsg());
			}
			for (Gleisteil gt : g.getTrackElements())
			{
				if (gt instanceof Verzweigung)
				{
					Verzweigung v = (Verzweigung)gt;
					if (v.hasLimitStop())
					{
						v.addQuestion(batch);
					}
				}
			}
		}
	}

	/**
	 * Sucht das Gleisteil passend zum Namen.
	 * @param name Der Name des zu suchenden Gleisteils.
	 * @return Das gesuchte Gleisteil.
	 */
	public Gleisteil find(String name)
	{
		Gleisteil g = null;
		
		if (name != null)
		{
			g = trackElements.get(name);
		}
		return g;
	}

	/**
	 * Sucht den Abschnitt passend zum Namen.
	 * @param name Der Name des zu suchenden Abschnitts.
	 * @return Der gesuchte Abschnitt.
	 */
	public Abschnitt findSegment(String name)
	{
		Abschnitt a = null;
		
		if (name != null)
		{
			a = segments.get(name);
		}
		return a;
	}
	
	/**
	 * Sucht die Betriebsgruppe passend zum Namen.
	 * @param name Der Name der zu suchenden Betriebsgruppe.
	 * @return Die gesuchte Betriebsgruppe.
	 */
	public Gruppe findGroup(String name)
	{
		return groups.get(name);
	}
	
	/**
	 * Sucht den Microcontroller passend zur ID.
	 * @param id Die ID des zu suchenden Microcontrollers.
	 * @return Der gesuchte Microcontroller.
	 */
	public MicroController findMicroController(int id)
	{
		return controller.get(id);
	}

	/**
	 * Sucht das Signal passend zum Namen.
	 * @param name Der Name des zu suchenden Signals.
	 * @return Das gesuchte Signal.
	 */
	public Signal findSignal(String name)
	{
		Signal s = null;
		
		if (name != null)
		{
			s = signals.get(name);
		}
		return s;
	}

	/**
	 * Sucht den Magnetartikel passend zum Namen.
	 * @param name Der Name des zu suchenden Magnetartikels.
	 * @return Der gesuchte Magnetartikel.
	 */
	public DeviceUnit findMagneticPart(String name)
	{
		return magneticPart.get(name);
	}
	
	/**
	 * Diese Methode verbindet zwei benannte Gleisteile an ein Gleis.
	 * @param name Das zu verbindende Gleis.
	 * @param a Das Gleisteil, das an Anschluss a verbunden wird.
	 * @param b Das Gleisteil, das an Anschluss b verbunden wird.
	 */
	protected void linkGleis(String name, String a, String b)
	{
		Gleis g = (Gleis)find(name);

		g.link(
			find(a),
			find(b));
	}
	
	/**
	 * Diese Methode verbindet zwei benannte Gleisteile an eine Weiche.
	 * @param name Die zu verbindende Weiche.
	 * @param a Das Gleisteil, das an den Linksabzweig verbunden wird.
	 * @param b Das Gleisteil, das an den Rechtsabzweig verbunden wird.
	 * @param c Das Gleisteil, das an das stumpfe Ende verbunden wird.
	 */
	protected void linkWeiche(String name, String a, String b, String c)
	{
		Weiche w = (Weiche)find(name);

		w.link(
			find(a),
			find(b),
			find(c));
	}
	
	/**
	 * Diese Methode verbindet zwei benannte Gleisteile an eine DKW.
	 * @param name Die zu verbindende DKW.
	 * @param a Das Gleisteil, das an den Anschluss a verbunden wird.
	 * @param b Das Gleisteil, das an den Anschluss b verbunden wird.
	 * @param c Das Gleisteil, das an das Anschluss c verbunden wird.
	 * @param d Das Gleisteil, das an das Anschluss d verbunden wird.
	 */
	protected void linkDKW(String name, String a, String b, String c, String d)
	{
		DKW dkw = (DKW)find(name);
		
		dkw.link(
			find(a),
			find(b),
			find(c),
			find(d));
	}
	
	/**
	 * Diese Methode validiert die Modelleisenbahn. Dazu wird jedes Gleisteil auf gegenseitige
	 * Verbindung überprüft.
	 * @return True, falls die Anlage OK ist.
	 */
	public int validate()
	{
		int errors = 0;
		for (Gleisteil g : trackElements.values())
		{
			errors += g.validate();
		}
		return errors;
	}

	/**
	 * Diese Methode baut rekursiv eine Fahrstraße aus Gleisteilen zusammen.
	 * @param route Die zu bildende Fahrstraße
	 * @param list Liste, in die die Gleisteile der Fahrstraße eingetragen werden.
	 * @param start Das startende Gleisteil. Dieses ist nicht das erste der gesamten Fahrstraße sondern ist das letzte schon gefundene Gleisteil, von dem aus die weitere Suche startet.
	 * @param end Das Zielgleisteil.
	 * @param depth Die aktuelle Suchtiefe.
	 * @param forceSameGroup Ein Flag, dass bestimmt, ob die Fahrstraße die Betriebsgruppe verlassen darf oder nicht.
	 * @return Erfolgsflag, ob eine Fahrstraße gefunden werden konnte.
	 */
	private boolean findRoute(
			Route                 route,
			LinkedList<Gleisteil> list,
			Gleisteil             start,
			Gleisteil             end,
			int                   depth,
			boolean               forceSameGroup)
	{
		if (log.isTraceEnabled() && isVerbose())
		{
			StringBuilder buffer = new StringBuilder("             #");
			buffer.append(LogUtil.empty(depth)).append(start.getName());
			log.trace(buffer.toString());
		}

		if (depth >= ROUTE_DEPTH)
		{
			// Max. Rekursionstiefe erreicht.
			log.warn("Maximale Suchtiefe erreicht!");
			return false;
		}
		else if (start == end)
		{
			// Ziel gefunden.
			return true;
		}
		else
		{
			// Für alle möglichen Abzweigungen der entsprechenden Richtung.
			for (Gleisteil teil  : start.getRouting(route.getDirection()))
			{
				boolean sameGroup;
				if (forceSameGroup || route.isShunting())
				{
					// Beim Rangieren muss die Fahrstraße in derselben Betriebsgruppe verbleiben.
					// Auch, wenn es durch das forceSameGroup-Flag erzwungen wird.
					sameGroup = (teil.getSegment().getGroup() == end.getSegment().getGroup());
				}
				else
				{
					sameGroup = true;
				}
				
				synchronized(teil)
				{
					// Wenn das Gleisteil unbenutzt ist...
					if (route.isFree(teil) && sameGroup)
					{
						// Gleisteil gegen weitere Belegung sichern
						route.addTrackElement(teil);
						if (findRoute(route, list, teil, end, depth + 1, forceSameGroup))
						{
							// Rücklauf aus Rekursion
							list.addFirst(teil);
							return true;
						}
						else
						{
							// Gleisteil für neue Belegungen wieder freigeben.
							route.removeTrackElement(teil);
						}
					}
				}
			}

			// Ziel nicht gefunden
			return false;
		}
	}

	/**
	 * Diese Methode überprüft die angegebene Fahrstraße auf Gültigkeit.
	 * @param route Die zu überprüfende Fahrstraße. Falls dieser Wert null ist, wird eine neue Fahrstraße angelegt.
	 * @param hops Die Liste der Gleisabschnitte, an denen entlang die Fahrstraße gebildet werden soll.
	 * @param shunting Rangierflag.
	 * @param inDirection Flag, ob die Fahrstraße in Zählrichtung oder gegen Zählrichtung geführt werden soll.
	 * @return
	 */
	private Route validateRouting(
			      Route           route,
			final List<Abschnitt> hops,
			final boolean         shunting,
			final boolean         inDirection)
		throws RoutingException
	{
		StringBuilder buffer = new StringBuilder("#### ");
		int aCount = 0;
		for (Abschnitt hop : hops)
		{
			if (aCount > 0)
			{
				buffer.append(" -> ");
			}
			buffer.append(hop.getNumber());
			aCount++;
		}
		buffer.append(" # ").append(NameUtil.logRouteType(shunting, inDirection));
		log.info(buffer.toString());

		// Validierung
		if (route == null)
		{
			route = new Route(this, shunting, inDirection);
		}
		for (Abschnitt abschnitt : hops)
		{
			if (abschnitt == null)
			{
				throw new RailSegmentNotDefinedException(route);
			}
			Gleisteil start = abschnitt.getStartTrack();
			if (start == null)
			{
				throw new SegmentWithoutRailException(route, abschnitt);
			}
		}
		return route;
	}

	/**
	 * Diese Methode bildet eine Fahrstraße entlang von vordefinierten Gleisabschnitten.
	 * @param route Die zu bildende Fahrstraße.
	 * @param start Der startende Gleisabschnitt.
	 * @param hops Die Liste weiterer Gleisabschnitte.
	 */
	synchronized private boolean buildRoute(final Route route, Abschnitt start, final List<Abschnitt> hops)
		throws RoutingException
	{
		final LinkedList<Gleisteil> list  = new LinkedList<Gleisteil>();
		      int                   count = 1;

		try
		{
			for (int i = 1;i < hops.size();i++)
			{
				list.clear();
				Abschnitt end = hops.get(i);
				log.debug(LogUtil.printf(
						"Fahrstraße >>> %s-%s Betriebsgruppe gleich? %b",
						start.getName(), end.getName(), start.getGroup() == end.getGroup()));
				synchronized(route)
				{
					if(findRoute(route, list,
							start.getStartTrack(), // Startgleis
							end.getStartTrack(),   // Zielgleis
							1,                     // Rekursionszähler
							start.getGroup() == end.getGroup()))
					{
						log.debug(LogUtil.printf("Ziel %s wurde erreicht!", end.getNumber()));
						route.addRoute(end);
						route.addAll(list);
						start = end;
						count++;
					}
					else
					{
						log.warn(LogUtil.printf("Ziel %s wurde nicht erreicht!", end.getNumber()));
						throw new RouteIncompleteException(route);
					}
				}
			}
			if (route.size() < 2)
			{
				throw new RoutingRequirementsMissingException(route, "Fahrstraße hat nicht genug Gleisteile!");
			}
		}
		finally
		{
			route.prepare();
		}
		return hops.size() == count;
	}

	/**
	 * Berechnet eine Fahrstraße über die angegebenen Markierungspunkte in Form von Abschnitten.
	 * @param hops Die Markierungspunkte
	 * @param shunting Flag für Rangierfahrt sonst normale Fahrt
	 * @param inDirection Flag für Fahrtrichtung gleich Zählrichtung
	 * @return Die berechnete Fahrstraße.
	 * @throws RoutingException 
	 */
	public Route route(
			final List<Abschnitt> hops,
			final boolean         shunting,
			final boolean         inDirection) throws RoutingException
	{
		Route route = validateRouting(null, hops, shunting, inDirection);
		Abschnitt start = hops.get(0);
		route.add(start.getStartTrack());
		route.addRoute(start);
		buildRoute(route, start, hops);
		return route;
	}

	/**
	 * Verlängert eine bestehende Fahrstraße um die angegebenen Markierungspunkte in
	 * Form von Abschnitten.
	 * @param route Die schon bestehende Fahrstraße.
	 * @param hops Die Markierungspunkte.
	 * @return Erfolgsflag.
	 * @throws RoutingException 
	 */
	public boolean appendRoute(final Route route, final List<Abschnitt> hops) throws RoutingException
	{
		List<Abschnitt> follow = new LinkedList<Abschnitt>();
		validateRouting(route, hops, route.isShunting(), route.getDirection());
		Abschnitt start = route.getLastSegment();
		
		follow.add(start);
		follow.addAll(hops);
		return buildRoute(route, start, follow);
	}

	/**
	 * Legt eine neue Fahrstraße um die angegebenen Markierungspunkte in
	 * Form von Abschnitten an.
	 * @param hops Die Markierungspunkte.
	 * @param shunting Flag für Rangierfahrt sonst normale Fahrt
	 * @param inDirection Flag für Fahrtrichtung gleich Zählrichtung
	 * @param blockDeallocation Flag für blockweise Gleisfreigabe.
	 * @return Die initialisierte Fahrstraße.
	 * @throws RoutingException 
	 */
	public Route startTour(
			final List<Abschnitt> hops,
			final boolean         shunting,
			final boolean         inDirection,
			final boolean         blockDeallocation) throws RoutingException
	{
		Route route = new Route(this, shunting, inDirection, false, blockDeallocation);
		route = validateRouting(route, hops, shunting, inDirection);
		Abschnitt start = hops.get(0);
		route.add(start.getStartTrack());
		route.addRoute(start);
		buildRoute(route, start, hops);
		return route;
	}


	/**
	 * Erzeugt eine Fahrstraße als Wartungsmodus.
	 * @param selection Die Auswahl der Abschnitte, die trotz Belegung aktiviert werden soll. 
	 * @return Die Wartungsfahrstraße.
	 */
	public Route maintainanceRoute(final List<Abschnitt> selection)
	{
		Route route = new Route(this);
		route.prepareMaintainanceMode(segments.values(), signals.values(), selection);
		return route;
	}
	
	/**
	 * Gibt alle bekannten Gleisteile zurück.
	 * @return Sammlung aller Gleisteile.
	 */
	public Collection<Gleisteil> getTrackElements()
	{
		return trackElements.values();
	}
	
	/**
	 * Gibt alle bekannten Gleisabschnitte zurück.
	 * @return Sammlung aller Gleisabschnitte.
	 */
	public Collection<Abschnitt> getSegments()
	{
		return segments.values();
	}
	
	/**
	 * Gibt alle bekannte betriebsgruppen zurück.
	 * @return Sammlung aller betriebsgruppen.
	 */
	public Collection<Gruppe> getGroups()
	{
		return groups.values();
	}

	/**
	 * Gibt alle bekannten Signale zurück.
	 * @return Sammlung aller Signale.
	 */
	public Collection<Signal> getSignals()
	{
		return signals.values();
	}

	/**
	 * Überprüft, ob alle Microcontroller erreichbar sind. Dies geschieht auf der Grundlage, dass pro
	 * Microcontroller die Zahl der Pings gleich der Zahl der Pongs sein muss.
	 * @return Erreichbartkeit aller Microcontroller.
	 */
	public boolean areControllerReachable()
	{
		for(MicroController c : controller.values())
		{
			if (!c.isReachable())
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * Führt am angegebenen Microcontroller ein Ping durch.
	 * @param id Ping an den Microcontroller mit der entsprechenden ID.
	 */
	public void ping(int id)
	{
		MicroController c = controller.get(id);
		if (c != null)
		{
			c.ping();
		}
	}

	/**
	 * Führt an allen Microcontrollern ein Ping durch.
	 */
	public void ping()
	{
		for(MicroController c : controller.values())
		{
			c.ping();
		}
	}

	/**
	 * Gibt die Liste der Microcontroller zurück.
	 * @return Liste der Microcontroller.
	 */
	public Collection<MicroController> getMicroController()
	{
		return controller.values();
	}

	/**
	 * Diese Methode gibt Informationen über den Biermodus zurück.
	 * @return Informationen über den Biermodus
	 * @see BeerModeInfo
	 * @see BeerMode
	 */
	abstract public List<TourInfo> getTourInfos();

	/**
	 * Diese Methode gibt Informationen über Fahrtstrecken zurück.
	 * @return Informationen über Fahrtstrecken.
	 * @see TourInfo
	 * @see TourMode
	 */
	abstract public BeerModeInfo getBeerModeInfo();
}
