/*
**
**	$Filename:	BeerMode.java $
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

package de.morknet.mrw.automatic.beermode;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.morknet.mrw.Modell;
import de.morknet.mrw.ModellFactory;
import de.morknet.mrw.MrwController;
import de.morknet.mrw.RoutingException;
import de.morknet.mrw.automatic.MrwActionControl;
import de.morknet.mrw.base.Abschnitt;
import de.morknet.mrw.base.Gruppe;
import de.morknet.mrw.batch.RouteEnableRunner;
import de.morknet.mrw.gui.info.BeerModeInfo;
import de.morknet.mrw.util.LogUtil;
import de.morknet.mrw.util.MrwRandom;
import de.morknet.mrw.util.ReferenceCounter;

/**
 * Diese Klasse stellt die Steuerung für den Biermodus bereit. Aus einem definiertem Bahnhof wird
 * ein Zug aus einem besetzten Gleis ausgewählt, durch ein freies Gleis eines weiteren Bahnhofs
 * geführt und wieder auf ein freies Gleis des ersten Bahnhofs geführt. Ist der Zug angekommen, wird
 * der nächste Zug ausgewählt. Die Auswahl des Zuges und des freien Gleises geschieht zufällig. Die
 * Fahrtrichtung wird dabei berücksichtigt.
 * @author smork
 *
 */
public class BeerMode extends MrwActionControl
{
	private final static int                    MIN_WAIT    = 10;
	private final static int                    MAX_WAIT    = 30;
	private final static Log                    log         = LogFactory.getLog(BeerMode.class);
	private final static Modell                 model       = ModellFactory.getInstance();
	private final static BeerModeInfo           info        = model.getBeerModeInfo();
	private final static ReferenceCounter<Abschnitt> poolCounter = new ReferenceCounter<Abschnitt>();
	private final static ReferenceCounter<Abschnitt> viaCounter  = new ReferenceCounter<Abschnitt>();
	private final static Gruppe                 poolStation = model.findGroup(info.getPoolStationName());
	private final static Gruppe                 viaStation  = model.findGroup(info.getViaStationName());
	private final static MrwRandom              aRandom     = new MrwRandom();
	private final static MrwRandom              vRandom     = new MrwRandom();
	private final static MrwRandom              eRandom     = new MrwRandom();
	private final static MrwRandom              tRandom     = new MrwRandom();
	private final        boolean                inDirection;
	private final        BeerModeTrigger        trigger;
	private       static BeerMode               beerModeLeft;
	private       static BeerMode               beerModeRight;

	private BeerMode(MrwController controller, boolean inDirection)
	{
		super(controller, "Biermodus " + (inDirection ? "rechts" : "links"));
		this.inDirection = inDirection;
		trigger          = new BeerModeTrigger(controller, this);
		initCounter();
	}

	/**
	 * Diese Methode gibt einen linksfahrenden Biermodus zurück.
	 * @param controller Der steuernde Controller
	 * @return Der Biermodus, der alles nach links fahren lässt.
	 */
	synchronized public final static BeerMode getBeerModeLeft(final MrwController controller)
	{
		if (beerModeLeft == null)
		{
			beerModeLeft = new BeerMode(controller, false);
		}
		return beerModeLeft;
	}

	/**
	 * Diese Methode gibt einen rechtsfahrenden Biermodus zurück.
	 * @param controller Der steuernde Controller
	 * @return Der Biermodus, der alles nach rechts fahren lässt.
	 */
	synchronized public final static BeerMode getBeerModeRight(final MrwController controller)
	{
		if (beerModeRight == null)
		{
			beerModeRight = new BeerMode(controller, true);
		}
		return beerModeRight;
	}

	@Override
	public boolean onActivate()
	{
		boolean success = false;

		log.debug(">onActivate()");
		try
		{
			controller.setMessage("Aktivierung Biermodus in Zählrichtung: " + inDirection);
			clearCounter();
			trigger.setDirection(inDirection);
			controller.addTrigger(trigger);
			selectNextTrack(inDirection, false);
			success = true;
		}
		catch (SelectTrackException aee)
		{
			controller.setErrorMessage(aee.getMessage());
			controller.removeTrigger(trigger);
		}
		catch(RuntimeException re)
		{
			log.error(re.getMessage(), re);
			controller.removeTrigger(trigger);
			
			throw re;
		}
		
		log.debug("<onActivate() = " + success);
		return success;
	}

	@Override
	public boolean onDeactivate()
	{
		log.debug(">onDeactivate()");
		controller.setMessage("Deaktivierung Biermodus.");
		controller.removeTrigger(trigger);
		dumpCounter();
		log.debug("<onDeactivate()");

		return true;
	}

	/**
	 * Diese Methode sucht sich abhängig von der Fahrtichtung einen Zug zur Fahrt aus.
	 * @param inDirection Die Fahrtrichtung relativ zur Zährichtung der Gleise.
	 */
	public void selectNextTrack(final boolean inDirection, final boolean doWait)
	{
		final Gruppe poolStation = getPoolStation();
		final Gruppe viaStation  = getViaStation();
		
		log.debug(">selectNextTrack(" + inDirection + ")");
		if ((poolStation != null) && (viaStation != null))
		{
			List<Abschnitt> hops     = new ArrayList<Abschnitt>();
			List<Abschnitt> occupied = poolStation.getListOfDirectedSegments(true,  inDirection);
			List<Abschnitt> via      = viaStation.getListOfDirectedSegments (false, inDirection);
			List<Abschnitt> free     = poolStation.getListOfDirectedSegments(false, inDirection);
			if (occupied.size() == 0)
			{
				throw new SelectTrackException("Es steht kein Zug zur Auswahl!");
			}
			if (via.size() == 0)
			{
				throw new SelectTrackException("Es gibt keine Durchfahrtmöglichkeit!");
			}
			if (free.size() == 0)
			{
				throw new SelectTrackException("Es gibt keinen Platz im Zielbahnhof!");
			}

			int idxA = aRandom.nextInt(occupied.size());
			int idxV = vRandom.nextInt(via.size());
			int idxE = eRandom.nextInt(free.size());

			hops.add(occupied.get(idxA));
			hops.add(via.get(idxV));
			hops.add(free.get(idxE));

			if (log.isDebugEnabled())
			{
				for(Abschnitt a : hops)
				{
					log.debug("  " + a);
				}
			}

			try
			{
				try
				{
					final long wait = doWait ? tRandom.nextInt((MAX_WAIT - MIN_WAIT) * 1000) + MIN_WAIT * 1000L : 0L;

					log.debug(LogUtil.printf(" Waiting %f seconds...", wait * 0.001));
					Thread.sleep(wait);
				}
				catch (InterruptedException e)
				{
					log.error("Probleme beim Warten auf nächste Biertour!");
					log.error(e.getLocalizedMessage(), e);
				}

				controller.setMessage("Nächste Fahrstraße wird geschaltet...");
				route = model.route(hops, false, inDirection);

				RouteEnableRunner runner = new RouteEnableRunner(controller, route);
				runner.start();
				poolCounter.count(hops.get(2));
				viaCounter.count(hops.get(1));
			}
			catch (RoutingException re)
			{
				controller.setErrorMessage(re.getLocalizedMessage());
				re.getRoute().clear();
			}
		}
		else
		{
			if (poolStation == null)
			{
				log.warn(info.getPoolStationName() + " nicht gefunden.");
			}
			if (viaStation == null)
			{
				log.warn(info.getViaStationName() + " nicht gefunden.");
			}
		}
		log.debug("<selectNextTrack(" + inDirection + ")");
	}

	/**
	 * Dieser Bahnhof dient als Schattenbahnhof. Der Schattenbahnhof wird aus dem Modell ermittelt.
	 * @return Der Schattenbahnhof.
	 */
	public static Gruppe getPoolStation()
	{
		return poolStation;
	}

	/**
	 * Dieser Bahnhof ist der Durchfahrtbahnhof. Der Durchfahrtbahnhof wird aus dem Modell ermittelt.
	 * @return Der Durchfahrtbahnhof.
	 */
	public static Gruppe getViaStation()
	{
		return viaStation;
	}
	
	/**
	 * Diese Methode initialisiert die Zähler der verwendeten Gleise/Züge zurück.
	 */
	private static void initCounter()
	{
		poolCounter.zero();
		viaCounter.zero();
	}
	
	/**
	 * Diese Methode setzt die Zähler der verwendeten Gleise/Züge zurück.
	 */
	private static void clearCounter()
	{
		poolCounter.zero();
		viaCounter.zero();
	}

	/**
	 * Diese Methode listet auf, wie häufig ein Gleis aus dem Schattenbahnhof bzw. dem
	 * Durchgangsbahnhof verwendet wurde.
	 */
	private static void dumpCounter()
	{
		log.info("=====================================================================");
		for (Abschnitt abschnitt : poolCounter.keySet())
		{
			log.info(abschnitt.getName() +  " " + poolCounter.getValue(abschnitt));
		}
		log.info("---------------------------------------------------------------------");
		for (Abschnitt abschnitt : viaCounter.keySet())
		{
			log.info(abschnitt.getName() +  " " + viaCounter.getValue(abschnitt));
		}
		log.info("=====================================================================");
	}
	
	/**
	 * Diese Methode gibt die Fahrtrichtung zurück.
	 * @return Die Fahrtrichtung dieses Biermodus.
	 */
	public final boolean getDirection()
	{
		return this.inDirection;
	}
}
