/*
**
**	$Filename:	TourMode.java $
**	$Revision$
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

package de.morknet.mrw.automatic.tourmode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.morknet.mrw.Modell;
import de.morknet.mrw.ModellFactory;
import de.morknet.mrw.MrwController;
import de.morknet.mrw.Route;
import de.morknet.mrw.RoutingException;
import de.morknet.mrw.automatic.MrwActionControl;
import de.morknet.mrw.automatic.TourPoint;
import de.morknet.mrw.automatic.Trigger;
import de.morknet.mrw.base.Abschnitt;
import de.morknet.mrw.batch.ExceptionCallback;
import de.morknet.mrw.batch.RouteEnableRunner;
import de.morknet.mrw.gui.info.TourInfo;

/**
 * Diese Klasse fährt eine vorgegebene Fahrtstrecke ab. Diese Fahrtstrecke ist nicht mit einer Fahrstraße zu verwechseln. Die Strecke
 * wird durch eine Fahrstraße abgefahren. Die Fahrstraße wandert sozusagen durch diese Fahrtstrecke. Somit ist in der Regel die
 * Fahrstraße kürzer, als die Fahrstrecke. Solange die Fahrstrecke noch nicht vollständig abgefahren ist, wird die dazugehörige
 * Fahrstraße nicht abgeschaltet und kann somit nur aus einem endendem Gleisabschnitt bestehen, der abgeschaltet ist und in dem 
 * ein Zug steht. Sobald eine Erweiterung der Fahrstraße möglich ist, wird diese automatisch durch {@link Trigger} geschaltet.
 * Fahrtrecken können zyklisch
 * sein. In diesem Fall werden die abgefahrenen Tourpunkte hinten wieder an die Bearbeitungsliste gehängt. Eine Beendigung dieses
 * Vorgangs kann nur manuell durch Deaktivieren dieses TourModes geschehen. In diesem Fall wird die Fahrstraße nicht verlängert. Beim
 * Erreichen des letzten Gleisabschnitts wird dann die Fahrstraße aufgelöst.
 * @see Trigger
 * @see Route
 * @see TourInfo
 * @author sm
 *
 */
public class TourMode extends MrwActionControl implements ExceptionCallback
{
	private final static Modell                            modell     = ModellFactory.getInstance();
	private final static int                               MAX_HOPS   = 2;  
	private final        Log                               log;
	private final TourModeTrigger                          trigger;
	private final List<de.morknet.mrw.automatic.TourPoint> pool       = new ArrayList<TourPoint>();
	private final LinkedList<TourPoint>                    tourPoints = new LinkedList<TourPoint>();
	private final Set<Abschnitt>                           segments   = new HashSet<Abschnitt>(); 
	private final List<Abschnitt>                          dummyHops  = new LinkedList<Abschnitt>();
	private final boolean                                  shunting;
	private final boolean                                  direction;
	private final boolean                                  loop;
	private final String                                   tourName;
	
	private final static LinkedHashMap<TourInfo, TourMode> tourSet = new LinkedHashMap<TourInfo, TourMode>();

	private TourMode(
			final MrwController controller,
			final TourInfo      info)
	{
		super(controller, info.getName());
		this.shunting  = info.isShunting();
		this.direction = info.getDirection();
		this.loop      = info.isLoop();
		this.tourName  = info.getName();
		this.log       = LogFactory.getLog("TourMode " + tourName);

		log.info("Initialisiere Tour " + tourName);
		for (String name : info.getSegmentNames())
		{
			TourPoint point = new TourPoint(name, direction);
			pool.add(point);
			segments.addAll(point.getSegments());
		}
		trigger = new TourModeTrigger(this);
	}

	/**
	 * Diese Methode initialisiert eine Tour.
	 * @param controller Der steuernde Controller. 
	 * @param info Die Tourinformationen.
	 * @return Die Tour.
	 */
	synchronized public final static TourMode getTourMode(final MrwController controller, final TourInfo info)
	{
		TourMode tour = tourSet.get(info);
		if (tour == null)
		{
			tour = new TourMode(controller, info);
			tourSet.put(info, tour);
		}
		return tour;
	}

	@Override
	public boolean onActivate()
	{
		boolean success = false;

		log.debug(">onActivate()");
		if (controller.getSegmentSelection().size() == 0)
		{
			throw new IllegalStateException("Es wurde keine Startauswahl getroffen!");
		}

		List<Abschnitt> startHops = controller.getSegmentSelection();
		createRoute(startHops);
		if (route != null)
		{
			extendByCount(MAX_HOPS);
			controller.addTrigger(trigger);

			RouteEnableRunner runner = new RouteEnableRunner(controller, route, this);
			runner.enableRouteSelection();
			runner.start();
			controller.clearSegmentSelection();
			success = true;
		}
		log.debug("<onActivate() = " + success);
		return success;
	}

	@Override
	public boolean onDeactivate()
	{
		log.debug(">onDeactivate()");
		if (route != null)
		{
			route.enableAutomaticRouteDeallocation();
			this.route = null;
		}
		controller.removeTrigger(trigger);
		log.info("Tour deaktiviert");
		log.debug("<onDeactivate()");

		return true;
	}

	/**
	 * Dieser Callback deaktiviert beim Auftreten eines Fehlers beim Versenden von Kommandos diese Fahrtstrecke.
	 */
	public void errorOnSend()
	{
		log.error("Fehler beim Senden der Kommandos!");
		deactivate();
	}

	public void errorOnClear()
	{
		log.error("Fehler beim beim Bereinigen eines Fehlerzustands!");
		deactivate();
	}

	/**
	 * Diese Methode verwendet ggf. einen {@link TourPoint} wieder, wenn die Fahrt zyklisch
	 * konfiguriert ist.
	 * @param point Der evtl. wiederverwendete {@link TourPoint}.
	 * @param a Der aktuell verwendete Abschnitt.
	 */
	private void bump(final TourPoint point, final Abschnitt a)
	{
		if(loop)
		{
			if (a != null)
			{
				log.debug("Abschnitt " + a.getNumber() + " wird wiederverwendet.");
			}
			tourPoints.addLast(point);
		}
		else
		{
			if (a != null)
			{
				log.debug("Abschnitt " + a.getNumber() + " wird verworfen.");
			}
		}
	}

	/**
	 * Diese Methode versucht, einen ersten Abschnitt als Zielpunkt zu finden, der außerhalb
	 * des ersten {@link TourPoint}s liegt.
	 * @param startHops Die Liste der ersten abzuarbeitenden Abschnitte.
	 * @return Der darauffolgende erste Abschnitt eines {@link TourPoint}s.
	 */
	private Abschnitt findFirstHop(final List<Abschnitt> startHops)
	{
		for(int count = 0; count <= MAX_HOPS; count++)
		{
			log.debug("Suche ersten brauchbaren Gleisabschnitt in Hop " + count);
			TourPoint point = tourPoints.removeFirst();
			for (Abschnitt a : point.getSegments())
			{
				log.debug(" Prüfe: " + a.getNumber());
				if (!startHops.contains(a))
				{
					bump(point, null);
					log.debug("  Gefunden.");
					return a;
				}
				else
				{
					bump(point, null);
					break;
				}
			}
		}
		return null;
	}

	/**
	 * Diese Methode löst die Berechnung einer Fahrstraße aus.
	 * @param startHops Die Liste der ersten Abschnitte, die abgefahren werden soll. Diese stammt
	 * aus der Auswahl der GUI.
	 */
	private void createRoute(final List<Abschnitt> startHops)
	{
		synchronized(tourPoints)
		{
			tourPoints.clear();
			tourPoints.addAll(pool);

			final Abschnitt firstHop = findFirstHop(startHops);
			if (firstHop != null)
			{
				try
				{
					dummyHops.addAll(startHops);
					dummyHops.add(firstHop);
					route = modell.startTour(dummyHops, shunting, direction, true);
				}
				catch(RoutingException re)
				{
					log.info(re.getLocalizedMessage());
				}
				finally
				{
					dummyHops.clear();
				}
			}
		}
	}

	/**
	 * Diese Methode überprüft einen gegebenen Gleisabschnitt, ob dieser für diese Fahrtstrecke relevant ist.
	 * @param abschnitt Der zu überprüfende Gleisabschnitt.
	 * @return Die Relevanz des gegebenen Gleisabschnitts.
	 */
	public boolean isSegmentRelevant(Abschnitt abschnitt)
	{
		return segments.contains(abschnitt);
	}

	/**
	 * Diese Methode erweitert die Fahrt um einen nächsten TourPunkt.
	 */
	public void extend()
	{
		try
		{
			if (extendByCount(MAX_HOPS) > 0)
			{
				RouteEnableRunner runner = new RouteEnableRunner(controller, route, this);
				runner.start();
				controller.moveTrigger(trigger);
				controller.updateCompleteUI();
			}
		}
		catch(RuntimeException re)
		{
			log.error(re.getMessage(), re);
			deactivate();
		}
	}

	/**
	 * Diese Methode erweitert die Fahrt um die übergebenen nächsten TourPunkte. Die Zahl bestimmt die maximal
	 * zulässigen Erweiterungen. Unter Umständen kann es sein, dass die Fahrstraße überhaupt nicht erweitert werden kann.
	 * @param maxHops Die Zahl der maximalen Erweiterungen der Fahrstraße.
	 * @return Die Zahl der tatsächlich durchgeführten Erweiterungen der Fahrstraße.
	 */
	public int extendByCount(final int maxHops)
	{
		synchronized(route)
		{
			int extensions = 0;
			boolean extended;
	
			log.debug("Hops: " + route.getHopCount());
			while((route.getHopCount() <= maxHops) && (tourPoints.size() > 0))
			{
				synchronized(tourPoints)
				{
					/*
					for (TourPoint tp : tourPoints)
					{
						log.debug(tp);
					}
					*/

					TourPoint point = tourPoints.removeFirst();
					extended = false;
					for (Abschnitt a : point.getSegments())
					{
						try
						{
							log.debug("Prüfe, ob um Abschnitt " + a.getNumber() + " erweitert werden kann.");
							dummyHops.add(a);
							modell.appendRoute(route, dummyHops);

							log.debug("...ja.");
							bump(point, a);
							extensions++;
							extended = true;
							break;
						}
						catch(RoutingException re)
						{
							log.debug("...nein.");
						}
						finally
						{
							dummyHops.clear();
						}
					}
					if (!extended)
					{
						tourPoints.addFirst(point);
						break;
					}
				}
			}		
			return extensions;
		}
	}

	/**
	 * Diese Methode gibt den ersten möglich Tourpunkt an.
	 * @return Der erste Tourpunkt, der angefahren wird.
	 */
	public final TourPoint getFirstTourPoint()
	{
		return pool.get(0);
	}

	public String toString()
	{
		return tourName;
	}
}
