/*
**
**	$Filename:	Route.java $
**	$Revision$
**	$Date$
**	$Author$
**	$Id$
**
**	Copyright (C) 2011 committers of this modelrailway project. All rights reserved.
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.morknet.mrw.base.Abschnitt;
import de.morknet.mrw.base.Gleis;
import de.morknet.mrw.base.Gleissperrsignal;
import de.morknet.mrw.base.Gleisteil;
import de.morknet.mrw.base.Hauptsignal;
import de.morknet.mrw.base.NamedElement;
import de.morknet.mrw.base.Signal;
import de.morknet.mrw.base.Verzweigung;
import de.morknet.mrw.base.Vorsignal;
import de.morknet.mrw.base.Signal.SignalCommand;
import de.morknet.mrw.batch.Batch;
import de.morknet.mrw.batch.BatchExecuter;
import de.morknet.mrw.util.LogUtil;
import de.morknet.mrw.util.NameUtil;
import de.morknet.mrw.util.ReferenceCounter;

/**
 * In der Route sind alle Informationen für eine Fahrstraße gespeichert. Die abgeleitete Liste
 * enthält in der Reihenfolge des Durchfahrens alle Gleisteile.
 * @author sm
 *
 */
public class Route extends LinkedList<Gleisteil>
{
	private final static Log log = LogFactory.getLog(Route.class);

	private static final long serialVersionUID = 1L;
	
	private final boolean blockDeallocation;

	/**
	 *  Flag, ob eine automatische Gleisfreigabe beim Durchfahren erfolgen soll.
	 */
	private final static boolean AUTOMATIC_SEGMENT_DEALLOCATION = true;

	/**
	 *  Flag, ob die Fahrstraße nach dem Durchfahren freigegeben werden soll.
	 */
	private boolean automaticRouteDeallocation;

	/**
	 * Diese Liste enthält die Abschnitte als Richtungsvorgabe in der Reihenfolge, über
	 * die die Fahrstraße führen soll.
	 */
	private final LinkedList<Abschnitt> routing    = new LinkedList<Abschnitt>();
	
	/**
	 *  Alle an dieser Fahrstraße beteiligten Signale.
	 */
	private final LinkedHashSet<Signal> ownedSignals = new LinkedHashSet<Signal>();

	/**
	 * Menge der abgenutzten Gleisabschnitte. Diese wurden befahren, sind aber noch Teil der Fahrstraße.
	 */
	private final HashSet<Abschnitt> usedSegments = new HashSet<Abschnitt>();

	/**
	 * Alle an dieser Fahrstraße beteiligten Abschnitte in der Reihenfolge ihres Durchfahrens.
	 */
	private final LinkedList<Abschnitt> ownedSegments = new LinkedList<Abschnitt>();

	/**
	 * In dieser Map wird pro Abschnitt die Anzahl der Abzweigungen festgehalten.
	 * Diese Zahl dient zur Ermittlung, ob Hp1 oder Hp2 auf ein Signal gegeben wird.
	 */
	private final ReferenceCounter<Abschnitt> counter = new ReferenceCounter<Abschnitt>();

	/**
	 * Menge aller durch Routen belegten Gleisteile. Wird bei einem Routing festgestellt, dass
	 * ein Gleisteil in dieser Menge enthalten ist, kann die Route über dieses Gleisteil nicht
	 * weiter verfolgt werden. Die Gleisteile sind nicht auf eine bestimmte Fahrstraße gebunden,
	 * sondern bilden die Gleisteile der Gesamtanlage, die nicht zur Fahrstraßenbildung zur
	 * Verfügung stehen.
	 */
	private final static Set<Gleisteil> allocatedTrackElements = new HashSet<Gleisteil>();

	/**
	 * Liste aller verriegelten Fahrstraßen.
	 */
	private final static List<Route> lockedRoutes = new ArrayList<Route>();
	
	/**
	 * Flag, ob es sich um eine Rangierfahrt handelt. 
	 */
	private final boolean shunting;
	
	/**
	 * In Zählrichtung oder gegen Zählrichtung.
	 */
	private final boolean direction;

	/**
	 *  Markierung, ob diese Fahrstraße verriegelt ist.
	 */
	volatile private boolean locked = false;

	/**
	 * Das Modell, das die Fahrstraße berechnet.
	 */
	private final Modell model;

	/**
	 * Dieses Flag definiert, ob diese Fahrstraße im Wartungsmodus ist.
	 */
	private final boolean maintainance;

	/**
	 * Dieser Konstruktur initialisiert den Maintainance Mode.
	 * @param model Die Modelleisenbahn.
	 */
	Route(final Modell model)
	{
		this(model, true, true, false, false, true);
	}

	/**
	 * Dieser Konstruktur initialisiert eine einfache Fahrstraße. Diese wird manuell durch Knopfdruck ausgelöst.
	 * @param model Die Modelleisenbahn.
	 * @param shunting Rangierflag.
	 * @param direction Fahrtrichtung.
	 */
	Route(final Modell model, final boolean shunting, final boolean direction)
	{
		this(model, shunting, direction, true, !shunting, false);
	}

	/**
	 * Dieser Konstruktur erzeugt eine normale Fahrstraße.
	 * @param model Die Modelleisenbahn.
	 * @param shunting Rangierflag.
	 * @param direction Fahrtrichtung.
	 * @param automaticDeallocation Automatische Gleisfreigabe.
	 * @param blockDeallocation Blockweise Freigabe.
	 */
	Route(
			final Modell  model,
			final boolean shunting,
			final boolean direction,
			final boolean automaticDeallocation,
			final boolean blockDeallocation)
	{
		this(model, shunting, direction, automaticDeallocation, blockDeallocation, false);
	}

	/**
	 * Dies hier ist der eigentliche Konstruktur. Hier werden die Werte dieser Fahrstraße initialisert.
	 * @param model Die Modelleisenbahn.
	 * @param shunting Rangierflag.
	 * @param direction Fahrtrichtung.
	 * @param automaticDeallocation Automatische Gleisfreigabe.
	 * @param blockDeallocation Blockweise Freigabe.
	 * @param maintainance Flag für Wartungsmodus.
	 */
	private Route(
			final Modell  model,
			final boolean shunting,
			final boolean direction,
			final boolean automaticDeallocation,
			final boolean blockDeallocation,
			final boolean maintainance)
	{
		this.model     = model;
		this.shunting  = shunting;
		this.direction = direction;
		this.automaticRouteDeallocation = automaticDeallocation;
		this.blockDeallocation          = shunting ? false : blockDeallocation;
		this.maintainance               = maintainance;
	}

	/**
	 * Diese Methode initialisiert die Fahrstraße im Wartungsmodus.
	 * @param segments Alle Gleisabschnitte.
	 * @param signals Alle Signale.
	 * @param selection Die ausgewählten Gleisabschnitte.
	 */
	void prepareMaintainanceMode(
			final Collection<Abschnitt> segments,
			final Collection<Signal>    signals,
			final Collection<Abschnitt> selection)
	{
		synchronized(this)
		{
			if (lockedRoutes.size() > 0)
			{
				throw new IllegalStateException(
						"Es darf zu Wartungszwecken keine andere Fahrstraße vorhanden sein!");
			}
			synchronized(ownedSegments)
			{
				ownedSegments.clear();
				for (Abschnitt a : segments)
				{
					if (!a.isOccupied())
					{
						ownedSegments.add(a);
					}
					else if (selection.contains(a))
					{
						ownedSegments.add(a);
					}
				}
		
				ownedSignals.clear();
				ownedSignals.addAll(signals);
				for (Abschnitt a : ownedSegments)
				{
					this.addAll(a.getTrackElements());
				}
			}
			
			synchronized(allocatedTrackElements)
			{
				allocatedTrackElements.addAll(this);
			}
		}
	}

	/**
	 * Diese Methode berechnet alle Kommandos, um diese Fahrstraße in den Wartungsmodus zu pberführen.
	 * @param executer Der BatchExecuter.
	 * @return Der übergebene BatchExecuter.
	 */
	private BatchExecuter turnMaintainance(final BatchExecuter executer)
	{
		Batch batch;
		
		for (Gleisteil gt : this)
		{
			gt.setRoute(this);
		}

		batch = executer.createBatch();
		synchronized(ownedSegments)
		{
			for (Signal s : ownedSignals)
			{
				s.off();
				s.addCommand(batch);
			}
	
			batch = executer.createBatch();
			for (Abschnitt a : ownedSegments)
			{
				a.on(batch);
			}
		}
		return executer;
	}

	/**
	 * Diese Methode fügt einen zur Fahrstraßenbestimmung benötigten Fixpunkt hinzu.
	 * 
	 * @param a Ein weiterer festgelegter Routenpunkt.
	 */
	public void addRoute(final Abschnitt a)
	{
		routing.add(a);
	}

	/**
	 * Hier wird eine erfolgreich geroutete Fahrstraße für die Freischaltung
	 * vorbereitet. Alle beteiligten Gleisabschnitte und Signale werden ermittelt.
	 */
	void prepare()
	{
		synchronized(this)
		{
			Abschnitt last = null;
	
			// Mehrfache Abschnitte reduzieren
			synchronized(ownedSegments)
			{
				ownedSegments.clear();
				for (Gleisteil gt : this)
				{
					Abschnitt a = gt.getSegment();
					if (a != last)
					{
						ownedSegments.add(a);
						last = a;
					}
				}
		
				// Alle Signale in Fahrtrichtung auswählen
				ownedSignals.clear();
				for (Abschnitt abschnitt : ownedSegments)
				{
					ownedSignals.addAll(abschnitt.getSignals(direction));
				}
			}
		}
	}

	/**
	 * Diese Methode schaltet alle Weichen der Fahrstraße in die richtige Lage.
	 * @param executer
	 * @return
	 * @throws RoutingRequirementsMissingException 
	 */
	private Batch turnRail(final BatchExecuter executer) throws RoutingRequirementsMissingException
	{
		if (size() < 2)
		{
			throw new RoutingRequirementsMissingException(this, "Fahrstraße hat nicht genug Gleisteile!");
		}

		/*
		 * Dieser Codeabschnitt schaltet die Fahrstraße.
		 */
		Batch batch = executer.createBatch();

		Gleisteil [] teile = new Gleisteil[size()];
		Gleisteil prev, teil, next;

		this.toArray(teile);
		prev = get(0);
		teil = get(1);
		for (int i = 1; i < (size() - 1); i++)
		{
			next = get(i+1);
			teil.turn (batch, prev, next);

			prev = teil;
			teil = next;
		}

		counter.clear();
		for (Gleisteil gt : this)
		{
			Abschnitt abschnitt = gt.getSegment();
			if (gt.isBranch())
			{
				counter.count(abschnitt);
			}

			gt.setRoute(this);
		}
		return batch;
	}

	/**
	 * Diese Methode stellt an Hand von Abzweigen fest, ob das
	 * Signal langsam überfahren werden darf. Es ist konkret die
	 * Wahl zwischen Hp1 und Hp2.
	 * @param abzweige Zahl der Abzweige
	 * @return Signalkommando für die Langsamfahrstelle.
	 */
	private SignalCommand computeSignalSymbol(Abschnitt segment, int abzweige)
	{
		return usedSegments.contains(segment) ?
				SignalCommand.S0 :
				( abzweige > 1 ? SignalCommand.S2 : SignalCommand.S1);
	}

	/**
	 * Diese Methode schaltet alle Signale der Fahrstraße.
	 * @param executer
	 * @return Der Stapel mit den Signalschaltkommandos.
	 * @throws RoutingRequirementsMissingException 
	 */
	private Batch turnSignal(BatchExecuter executer) throws RoutingRequirementsMissingException
	{
		synchronized(ownedSegments)
		{
			Vorsignal     vs  = null;
			Hauptsignal   hs  = null;
			SignalCommand sb  = SignalCommand.S0;
			int           max = ownedSegments.size() - 1;
			
			if (max < 0)
			{
				throw new RoutingRequirementsMissingException(this, "Fahrstraße enthält keine Gleisabschnitte!");
			}
			Abschnitt last = ownedSegments.get(max);
			Batch batch = executer.createBatch();
			int abzweige = counter.getValue(last);
	
			log.debug("Berechne Signalbilder...");
			if (!last.isStumpf(direction))
			{
				// Wenn kein Stumpfgleis, nochmals erniedrigen, um letztes
				// Gleis nicht in Signalberechnung mit einzubeziehen.
				max--;
			}
			else
			{
				// Da kommt kein Haupsignal mehr. Daher kann ein kombiniertes Vorsignal dunkelgetastet werden.
				sb = SignalCommand.OFF;
			}
	
			/*
			 * Die Abschnitte werden vordersten Gleisabschnitt zum hintersten durchiteriert. So können die Zustände der
			 * Hauptsignale an die davor stehenden Vorsignale weitergereicht werden. Gleissperrsignale
			 * werden zwar wie Hauptsignale signalisiert, bestimmen jedoch keine vorhergehenden Vorsignale.
			 */
			for (int i = max; i >= 0; i--)
			{
				Abschnitt segment = ownedSegments.get(i);
				
				int act = counter.getValue(segment);
				abzweige += act;
		
				for (Signal s : segment.getSignals(direction))
				{
					if (s instanceof Hauptsignal)
					{
						sb = computeSignalSymbol(segment, abzweige);
						hs = (Hauptsignal)s;
						sb = hs.setSignal(sb, shunting);
						vs = null;
						abzweige = 0;
	
						log.debug("HS " + s.getNumber() + ": " + sb + " Abzweige: " + abzweige);
					}
					else if (s instanceof Vorsignal)
					{
						if (usedSegments.contains(segment) || shunting)
						{
							sb = SignalCommand.S0;
						}
	
						vs = (Vorsignal)s;
						sb = vs.setSignal(sb, shunting);
						log.debug("VS " + s.getNumber() + ": " + sb);
					}
					else if (s instanceof Gleissperrsignal)
					{
						SignalCommand gsb = computeSignalSymbol(segment, abzweige);
						gsb = s.setSignal(gsb, shunting);
						log.debug("GS " + s.getNumber() + ": " + gsb);
					}
					else
					{
						log.info("Unbekanntes Signal " + s.getNumber());
					}
				}
			}
			
			log.debug("Schalte Signale...");
			for(Signal s : ownedSignals)
			{
				s.addCommand(batch);
			}
			return batch;
		}
	}

	/**
	 * Diese Methode schaltet die Gleisabschnitte ein. Dabei werden die Abschnitte von hinten nach
	 * vorne der Reihe nach eingeschaltet, so dass der erste Abschnitt mit dem Zug als letzter aktiviert wird.
	 * @param executer Die Stapelausführung, dass den neuen Abschnittsstapel erhält.
	 * @return Der Stapel zum Einschalten der Gleisabschnitt.
	 */
	private Batch turnOn(BatchExecuter executer)
	{
		Batch batch = executer.createBatch();
		synchronized(ownedSegments)
		{
			int max = ownedSegments.size();
	
			Abschnitt last = getLastSegment();
			if (!last.isStumpf(direction))
			{
				log.debug("Letzter Abschnitt wird abgeschaltet...");
				max--;
				last.off(batch, false);
			}
	
			log.debug("Schalte Abschnitte frei...");
			for (int i = max - 1; i >= 0;i--)
			{
				ownedSegments.get(i).on(batch);
			}
		}			
		return batch;
	}

	/**
	 * Diese Methode bereitet die Stapelausführung zum Schalten einer Fahrstraße vor.
	 * @param executer Der ausführende {@link BatchExecuter}.
	 * @return Die Stapelausführung zum Schalten einer Fahrstraße.
	 * @throws RoutingRequirementsMissingException 
	 */
	public BatchExecuter turn(final BatchExecuter executer) throws RoutingRequirementsMissingException
	{
		synchronized(this)
		{
			Batch batch;
	
			if (maintainance)
			{
				return turnMaintainance(executer);
			}
	
			try
			{
				batch = turnRail(executer);
				if (batch != null)
				{
					batch = turnSignal(executer);
					if (batch != null)
					{
//						batch.setDelay(250L);
						batch = turnOn(executer);
						if (batch != null)
						{
							return executer;
						}
					}
				}
			}
			catch(RoutingRequirementsMissingException re)
			{
				executer.clear();
				throw re;
			}
			return null;
		}
	}

	/**
	 * Listet ein Gleisteil vorläufig als belegt. So kann es nicht wiederverwendet werden.
	 * @param gt Das belegende Gleisteil.
	 */
	public void addTrackElement(Gleisteil gt)
	{
		synchronized(allocatedTrackElements)
		{
			allocatedTrackElements.add(gt);
		}
	}

	/**
	 * Entfernt das Gleisteil aus der Liste der belegten Gleisteile. So kann das Gleisteil wiederverwendet
	 * werden.
	 * @param gt das zu entfernende Gleisteil.
	 */
	public void removeTrackElement(Gleisteil gt)
	{
		synchronized(allocatedTrackElements)
		{
			allocatedTrackElements.remove(gt);
		}
	}
	
	/**
	 * Diese Methode füllt einen BatchExecuter mit Kommandos, um diese Fahrstraße zwangsabzuschalten.
	 * @param executer Der BatchExecuter der die Batches mit den Freischaltaufträgen erhält.
	 */
	public void computeForcedClearBatch(BatchExecuter executer)
	{
		Batch batch = executer.createBatch();

		synchronized(ownedSegments)
		{
			for (Abschnitt a : ownedSegments)
			{
				a.off(batch, true);
				usedSegments.add(a);
			}
		}
	}

	/**
	 * Diese Methode erzeugt Kommandos, um alle Gleisabschnitte dieser Fahrstraße
	 * wieder freizugeben. Im Wartungsmodus werden alle Gleisabschnitte abgeschaltet.
	 * @param executer Der BatchExecuter der die Batches mit den Freischaltaufträgen erhält.
	 */
	public void computeClearBatches(final BatchExecuter executer)
	{
		Batch batch = executer.createBatch();
		if (maintainance)
		{
			/*
			 * Im Wartungsmodus werden bei Freigabe alle Gleisabschnitte abgeschaltet.
			 */
			for (Abschnitt a : model.getSegments())
			{
				a.off(batch, true);
				usedSegments.add(a);
			}
		}
		else
		{
			synchronized(ownedSegments)
			{
				/*
				 * Das hier ist ein Kosmetik-Batch, der die kombinierten Lichtvorsignale
				 * zuerst auf Vr0 schaltet, bevor sie dunkelgetastet werden.
				 */
				for (Abschnitt a : ownedSegments)
				{
					a.preSignalOff(batch, direction);
				}
	
				// Hier kommt die eigentliche Fahrstraßenauflösung.
				batch = executer.createBatch();
				for (Abschnitt a : ownedSegments)
				{
					a.directedOff(batch, direction);
					usedSegments.add(a);
				}
			}
		}
	}
	
	/**
	 * Diese Methode füllt die Batches mit Kommandos, um alle Gleisabschnitte bis zum angegebenen Gleisabschnitt
	 * aufzulösen.
	 * @param executer Der BatchExecuter, der die Batches enthalten wird.
	 * @param targetSegment Der hinterste freizugebende Gleisabschnitt.
	 */
	public void computeClearUptoSegmentBatches(
			final BatchExecuter executer,
			final Abschnitt     targetSegment)
	{
		/*
		 * Das hier ist ein Kosmetik-Batch, der die kombinierten Lichtvorsignale
		 * zuerst auf Vr0 schaltet, bevor sie dunkelgetastet werden.
		 */
		final Batch batch1 = executer.createBatch();

		/*
		 * Das hier ist der eigentliche Ausschalt-Batch.
		 */
		final Batch batch2 = executer.createBatch();

		/*
		 * Es werden alle Abschnitte ausgeschaltet, bis der Zielabschnitt gefunden wurde.
		 */
		synchronized(ownedSegments)
		{
			if (ownedSegments.contains(targetSegment))
			{
				Iterator<Abschnitt> it = ownedSegments.iterator();
				Abschnitt segment;
	
				do
				{
					if (it.hasNext())
					{
						segment = it.next();
						log.debug("   " + segment);
						if (segment.isEnabled())
						{
							if (!usedSegments.contains(segment))
							{
								segment.preSignalOff(batch1, direction);
							}
							segment.directedOff(batch2, direction);
						}
						usedSegments.add(segment);
					}
					else
					{
						segment = null;
					}
				}
				while ((segment != null) && (segment != targetSegment));
			}
		}
	}

	/**
	 * Löst diese Fahrstraße komplett auf.
	 */
	public void clear()
	{
		synchronized (this)
		{
			synchronized(lockedRoutes)
			{
				/*
				 * Hier werden alle Referenzen aufgehoben.
				 */
				for (Gleisteil gt : this)
				{
					synchronized(gt)
					{
						gt.setRoute(null);
						removeTrackElement(gt);
					}
				}

				/*
				 * Hier werden alle Listen geleert.
				 */
				synchronized(ownedSegments)
				{
					ownedSegments.clear();
					ownedSignals.clear();
				}
				usedSegments.clear();
				routing.clear();
				counter.clear();
				super.clear();
				locked = false;
				lockedRoutes.remove(this);
			}
		}
	}
	
	/**
	 * Löscht alle Abschnitte bis zum angegebenen Abschnitt aus einer Fahrstraße und entfernt die entsprechenden Referenzen.
	 * @param targetSegment Der hinterste freizugebende Gleisabschnitt.
	 */
	public void clearUptoSegment(Abschnitt targetSegment)
	{
		log.debug(" >clearUptoSegment(" + targetSegment.getNumber() + ")");
		synchronized(ownedSegments)
		{
			log.debug(">>clearUptoSegment(" + targetSegment.getNumber() + ")");
			Abschnitt segment;

			if (hasBlockDeallocation() && targetSegment.hasMainSignalWithSwitch(direction))
			{
				/*
				 * Hier werden die vordersten Gleisabschnitte solange frei gegeben, bis der Zielabschnitt
				 * erreicht ist oder ein abgeschalteter Abschnitt gefunden wurde. Dieser Abschnitt bleibt dann
				 * erhalten.
				 */
				while((!ownedSegments.isEmpty()) &&
						((segment = ownedSegments.getFirst()) != targetSegment) &&
						segment.isDisabled())
				{
					clearSegment(segment);
				}
			}
			else
			{
				/*
				 * Hier werden die vordersten Gleisabschnitte solange frei gegeben, bis der Zielabschnitt
				 * inklusive gefunden wurde.
				 */
				do
				{
					if (!ownedSegments.isEmpty())
					{
						segment = ownedSegments.getFirst();
						if (segment.isDisabled())
						{
							clearSegment(segment);
						}
					}
					else
					{
						segment = null;
					}
				}
				while ((segment != null) && (segment != targetSegment) && segment.isDisabled());
			}
			log.debug("<<clearUptoSegment(" + targetSegment.getNumber() + ")");
		}
		log.debug(" <clearUptoSegment(" + targetSegment.getNumber() + ")");
	}

	/**
	 * Diese Methode gibt einen einzelnen Gleisabschnitt frei.
	 * @param segment Der Gleisabschnitt.
	 */
	private void clearSegment(Abschnitt segment)
	{
		log.debug(LogUtil.printf("Gebe Abschnitt %s frei.", segment.getNumber()));

		// Gleisteile aufräumen
		for (Gleisteil gt : segment.getTrackElements())
		{
			synchronized(gt)
			{
				// Gleisteil hat keine Fahrstraße
				gt.setRoute(null);
				
				// Entfernen aus Liste aller benutzten Gleisteile
				removeTrackElement(gt);
				
				// Entfernen aus dieser Fahrstraße
				remove(gt);
			}
		}
		
		// Signale aufräumen
		synchronized(ownedSegments)
		{
			for (Signal s : segment.getSignals(direction))
			{
				ownedSignals.remove(s);
			}
		
			// Abschnitt aufräumen
			if (routing.getFirst() == segment)
			{
				routing.remove(segment);
			}
			ownedSegments.remove(segment);
			usedSegments.remove(segment);
		}
	}

	/**
	 * Diese Methode prüft, ob ein Gleisteil für das Suchen einer Fahrstraße brauchbar ist.
	 * @param teil Das zu prüfende Gleisteil
	 * @return True, wenn Gleisteil für Fahrstraße geeignet ist.
	 */
	public boolean isFree(Gleisteil teil)
	{
		synchronized(this)
		{
			Abschnitt start = routing.getFirst();
	
			if (teil.getRoute() != null)
			{
				// Gleisteil ist schon für durch Route belegt.
				return false;
			}

			if (shunting && (teil.getGruppe() != start.getGroup()))
			{
				// Beim Rangieren darf die Fahrstraße die Gruppe nicht
				// verlassen.
				return false;
			}
			
			synchronized(allocatedTrackElements)
			{
				if (allocatedTrackElements.contains(teil))
				{
					// Gleisteil ist schon für Fahrstraße zumindest verplant.
					return false;
				}
			}
			
			// Im ersten Abschnitt darf eine Lok stehen 
			if (start == teil.getSegment())
			{
				return true;
			}
			
			// Letzter Test: Der Gleisabschnitt darf nicht durch Lok belegt sein.
			return teil.isFree();
		}
	}

	/**
	 * Listet alle Gleisteile dieser Fahrstraße auf.
	 */
	public void dump()
	{
		if (log.isDebugEnabled())
		{
			log.debug("===========================================================");
			log.debug("Liste der Gleisteile in der Reihenfolge des Durchfahrens...");
			synchronized(this)
			{
				for (NamedElement teil : this)
				{
					log.debug("  " + teil.getName());
				}
			}
			log.debug("===========================================================");
		}
	}
	
	/**
	 * Baut einen Text, der diese Fahrstraße beschreibt auf.
	 * @return Der Kurztext dieser Fahrstraße
	 */
	public String toString()
	{
		synchronized(this)
		{
			if (maintainance)
			{
				return "Wartung";
			}

			StringBuilder buffer = new StringBuilder("Route ");
			
			for (Abschnitt a : routing)
			{
				if (a != routing.getFirst())
				{
					buffer.append("/");
				}
				buffer.append(a.getNumber());
			}
			buffer.append(" ").append(NameUtil.logRouteType(this));

			return buffer.toString();
		}
	}

	/**
	 * Gibt zurück, ob diese Fahrstraße dem Rangieren dient.
	 * @return Wird auf dieser Fahrstraße rangiert.
	 */
	public boolean isShunting()
	{
		return shunting;
	}

	/**
	 * Gibt aus, ob die Fahrstraße in Zählrichtung gerichtet ist.
	 * @return Fahrtrichtung relativ zur Zählrichtung.
	 */
	public boolean getDirection()
	{
		return direction;
	}
	
	/**
	 * Gibt zurück, ob diese Fahrstraße gegen Veränderung verriegelt ist.
	 * @return Der Verriegelungszustand dieser Fahrstraße
	 */
	synchronized public boolean isLocked()
	{
		return locked;
	}

	/**
	 * Diese Methode gibt zurück, ob sich diese Fahrstraße im Wartungsmodus befindet.
	 * @return True, wenn Fahrstraße im Wartungsmodus. 
	 */
	public boolean isMaintainance()
	{
		return this.maintainance;
	}

	/**
	 * Verriegelt die Fahrstraße, um Veränderungen zu verhindern.
	 */
	public void lock()
	{
		synchronized(this)
		{
			synchronized (lockedRoutes)
			{
				/*
				 * Alle beteiligten Signale werden gegen Verstellen verriegelt. Beim
				 * Verlängern der Fahrstraße können sich Signalbilder verändern (Vorsignale!), 
				 * diese Signalbilder werden neu übernommen.
				 */
				for (Signal s : ownedSignals)
				{
					s.setState();
				}
				
				/*
				 * Alle Gleisteile werden gegen weiteren Zugriff verriegelt.
				 */
				for(Gleisteil gt : this)
				{
					gt.lock();
				}
				
				/*
				 * Die Fahrstraße selbst wird verriegelt.
				 */
				if (!locked)
				{
					lockedRoutes.add(this);
				}
				locked = true;
			}
		}
	}
	
	/**
	 * Gibt zurück, ob Fahrstraßen geschaltet sind.
	 * @return Gibt es Fahrstraßen
	 */
	public static boolean hasRoutes()
	{
		synchronized (lockedRoutes)
		{
			return !lockedRoutes.isEmpty();
		}
	}

	/**
	 * Gibt die Liste der Fahrstraßen zurück.
	 * @return Liste der Fahrstraßen
	 */
	public static List<Route> getRoutes()
	{
		synchronized(lockedRoutes)
		{
			return lockedRoutes;
		}
	}
	
	/**
	 * Gibt die Fahrstraße zurück, in der der angegebene Abschnitt ist.
	 * @param abschnitt Der Abschnitt, der seine Route sucht.
	 * @return Die Route passend zum Abschnitt.
	 */
	public static Route findRoute(Abschnitt abschnitt)
	{
		synchronized (lockedRoutes)
		{
			for (Route r : lockedRoutes)
			{
				for (Abschnitt a : r.ownedSegments)
				{
					if (a == abschnitt)
					{
						return r;
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * Gibt den vorhergehenden Abschnitt des angegebenen Abschnitts zurück. 
	 * @param abschnitt Abschnitt, von dem rückwärts gesucht werden soll.
	 * @return Der Vorgänger dieses Abschnitts.
	 */
	public Abschnitt getPrevSegment(Abschnitt abschnitt)
	{
		synchronized(ownedSegments)
		{
			int idx = ownedSegments.indexOf(abschnitt);
			if (idx > 0)
			{
				idx--;
				return ownedSegments.get(idx);
			}
		}
		return null;
	}

	/**
	 * Gibt den nächsten Abschnitt des angegebenen Abschnitts zurück.
	 * @param abschnitt Abschnitt, von dem vorwärts gesucht werden soll.
	 * @return Der Nachfolger dieses Abschnitts.
	 */
	public Abschnitt getSuccSegment(Abschnitt abschnitt)
	{
		synchronized(ownedSegments)
		{
			int idx = ownedSegments.indexOf(abschnitt);
			if (idx >= 0)
			{
				idx++;
				if (idx < ownedSegments.size())
				{
					return ownedSegments.get(idx);
				}
			}
		}
		return null;
	}

	/**
	 * Prüft, ob ein Zug in diesen Abschnitt eingefahren ist.
	 * @param abschnitt Der zu prüfende Abschnitt
	 * @return True, wenn ein Zug eingefahren ist.
	 */
	public boolean checkIfSegmentEntered(Abschnitt abschnitt)
	{
		if (!maintainance)
		{
			Abschnitt prev = getPrevSegment(abschnitt);
			if (prev != null)
			{
				return abschnitt.isOccupied() && prev.isFree();
			}
		}
		return false;
	}

	/**
	 * Testet, ob der angegebene Abschnitt verlassen wurde. Das setzt voraus, dass die nächste Abschnitt belegt ist.
	 * @param abschnitt Der zu trestende Abschnitt.
	 * @return Test, ob angegebener Abschnitt verlassen wurde.
	 */
	public boolean checkIfSegmentLeft(Abschnitt abschnitt)
	{
		if (!maintainance)
		{
			Abschnitt succ = getSuccSegment(abschnitt);
			if (succ != null)
			{
				return abschnitt.isFree() && succ.isOccupied();
			}
		}
		return false;
	}

	/**
	 * Gibt den ersten Abschnitt dieser Fahrstraße zurück.
	 * @return Der erste Abschnitt dieser Fahrstraße.
	 */
	public Abschnitt getFirstSegment()
	{
		return maintainance ? null : ownedSegments.getFirst();
	}

	/**
	 * Gibt den letzten Abschnitt dieser Fahrstraße zurück.
	 * @return Der letzte Abschnitt dieser Fahrstraße.
	 */
	public Abschnitt getLastSegment()
	{
		Abschnitt result;

		if (maintainance)
		{
			result = null;
		}
		else
		{
			synchronized(ownedSegments)
			{
				int size = ownedSegments.size();
				
				result = size > 0 ? ownedSegments.get(size - 1) : null;
			}
		}
		return result;
	}

	/**
	 * Gibt zurück, ob die automatische Gleisfreigabe aktiv ist.
	 * @return Aktivierung der automatischen Gleisfreigabe.
	 */
	public static boolean hasAutomaticSegmentDeallocation()
	{
		return AUTOMATIC_SEGMENT_DEALLOCATION;
	}

	/**
	 * Diese Methode ermittelt, ob Gleisabschnitte dieser Fahrstraße blockweise wieder freigegeben werden.
	 * @return Blockweise Freigabe von Gleisabschnitten.
	 */
	public boolean hasBlockDeallocation()
	{
		return blockDeallocation;
	}

	/**
	 * Gibt zurück, ob die Fahrstraße nach dem Durchfahren automatisch aufgelöst
	 * werden soll.
	 * @return Automatische Fahrstraßenfreigabe.
	 */
	public boolean hasAutomaticRouteDeallocation()
	{
		return automaticRouteDeallocation && (!maintainance);
	}

	/**
	 * Diese Methode aktiviert die automatische Fahrstraßenauflösung.
	 */
	public void enableAutomaticRouteDeallocation()
	{
		automaticRouteDeallocation = true;
	}

	/**
	 * Diese Methode ermittelt die Fahrstraße, zu der die übergebene Verzweigung gehört.
	 * @param branch Die Verzweigung, zu der eine Fahrstraße gesucht wird.
	 * @return Die gefundene Fahrstraße.
	 */
	public static Route findConflictingRoute(Verzweigung branch)
	{
		synchronized(branch)
		{
			if (branch.isLocked())
			{
				Route conflict = branch.getRoute();
				if (!conflict.contains(branch))
				{
					throw new IllegalStateException("Verzweigung gehört der falschen Fahrstraße an.");
				}
				if (!conflict.maintainance)
				{
					return conflict;
				}
			}
			return null;
		}
	}
	
	/**
	 * Diese Methode gibt die Zahl der Stützpunkte zurück.
	 * @return Zahl der Stützpunkte, die die Fahrstraße festlegt.
	 */
	public int getHopCount()
	{
		return routing.size();
	}
	
	/**
	 * Diese Methode gibt das letzte Gleisteil zurück.
	 * @return Das letzte Gleisteil oder null, wenn keins vorhanden ist.
	 */
	private Gleisteil peekLastElement()
	{
		int size = size();
		
		return size > 0 ? get(size - 1) : null;
	}

	/**
	 * Diese Methode prüft, ob das zu einem Gleisteil benachbarte Gleisteil ebenfalls
	 * an der Fahrstraße beteiligt ist.
	 * @param gt Das Gleisteil
	 * @param next Das benachbarte Gleisteil, das darauf geprüft wird, ob es in dieser Fahrstraße benachbart ist.
	 * @return Ob das benachbarte Gleisteil an dieser Fahrstraße beteiligt ist.
	 */
	public boolean isPart(Gleisteil gt, Gleisteil next)
	{
		boolean result;
		
		if (gt.getRoute() != this)
		{
			throw new TrackNotRoutedException(this, gt);
		}
		if (gt instanceof Gleis)
		{
			// Ein einzelnes beteiligtes Gleis ist naturgemäß immer beteiligt ;-) 
			result = true;
		}
		else if ((next == null) || (gt == peekLastElement()))
		{
			// Prüfung auf letztes Gleisteil oder Abstellgleis.
			result = true;
		}
		else
		{
			// Prüfung des Nachbargleisteils, ob es auch Nachbargleisteil
			// in der Fahrstraße ist.
			int idxA = indexOf(gt);
			int idxB = indexOf(next);
			int diff = idxA - idxB;
	
			result = (idxB >= 0) && ((diff == 1) || (diff == -1));
		}
		return result;
	}
}
