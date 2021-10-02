/*
**
**	$Filename:	ControllerTestBase.java $
**	$Revision$
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

package de.morknet.mrw.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;

import de.morknet.mrw.Modell;
import de.morknet.mrw.ModellFactory;
import de.morknet.mrw.Route;
import de.morknet.mrw.base.Abschnitt;
import de.morknet.mrw.base.DKW;
import de.morknet.mrw.base.DirectionCode;
import de.morknet.mrw.base.Formgleissperrsignal;
import de.morknet.mrw.base.Gleisteil;
import de.morknet.mrw.base.OccupationCode;
import de.morknet.mrw.base.Verzweigung;
import de.morknet.mrw.base.Weiche;
import de.morknet.mrw.batch.BatchExecuter;
import de.morknet.mrw.batch.BatchRunner;
import de.morknet.mrw.comm.Command;
import de.morknet.mrw.comm.MrwMessage;
import de.morknet.mrw.comm.MsgCode;
import de.morknet.mrw.comm.dummy.DummyConnection;
import de.morknet.mrw.util.LogUtil;

abstract public class ControllerTestBase
{
	private   final static Log            log        = LogFactory.getLog(ControllerTestBase.class);
	protected final static TestController controller = TestController.getController();
	protected final static Modell         model      = ModellFactory.getInstance();

	private final static long SEGMENT_ENTER_DELAY    =  250L;
	private final static long SEGMENT_TRAVERSE_DELAY =  500L;
	private final static long ROUTE_CLEAR_DELAY      =  500L;

	@BeforeClass
	public static void setUp() throws Exception
	{
		log.info("\n\n\n\n\n");
		log.info("=============================================================");
		log.info("Starte Test mit Eisenbahnanlage '" + model.getName() + "'.");
		controller.prepareSimple();

		Formgleissperrsignal tcs = (Formgleissperrsignal) model.findSignal("Strecke Berg Gli");
		if (tcs == null)
		{
			Abschnitt a = model.findSegment("BO1");
			Assert.assertNotNull(a);

			log.info("Lege Formgleissperrsignal an...");
			tcs = new Formgleissperrsignal(a, "G1i", false);
			log.info(tcs.getName());
			Assert.assertNotNull(tcs);
			model.add(a, tcs);
		}

		Assert.assertTrue(model.validate() == 0);
		controller.selectRoute(null);
		Assert.assertNull("Es darf keine Fahrstraße ausgewählt sein!", controller.getSelectedRoute());

		Assert.assertTrue("Es darf keine Fahrstraße vorhanden sein!", Route.getRoutes().isEmpty());
		for (Abschnitt a : model.getSegments())
		{
			a.free();
			Assert.assertTrue("Abschnitt " + a.getNumber() + " muss frei sein!", a.isFree());
			Assert.assertTrue("Abschnitt " + a.getNumber() + " muss ausgeschaltet sein!", a.isDisabled());
		}
		log.info("=============================================================");
	}

	@AfterClass
	public static void tearDown() throws Exception
	{
		log.info("=============================================================");
		controller.resetMicroController();
		controller.close();
		log.info("Test der Eisenbahnanlage '" + model.getName() + "' beendet.");
		log.info("=============================================================");
		log.info("\n\n\n\n\n");
	}

	final protected void selectSegments(final String ... names)
	{
		for (String name : names)
		{
			Abschnitt segment = model.findSegment(name);
			controller.addSegmentSelection(segment);
		}
	}

	final protected void sleep(final int seconds)
	{
		try
		{
			log.info("Warte " + seconds + " Sekunden...");
			Thread.sleep(seconds * 1000L);
			log.info("Fertig.");
		}
		catch (InterruptedException e)
		{
			log.error(e.getLocalizedMessage(), e);
		}
	}

	final protected void sleep(final long millies)
	{
		try
		{
			log.debug("Warte " + millies + " Millisekunden...");
			Thread.sleep(millies);
			log.debug("Fertig.");
		}
		catch (InterruptedException e)
		{
			log.error(e.getLocalizedMessage(), e);
		}
	}

	final protected void waitForRunner(final BatchRunner runner)
	{
		waitForRunner(runner, 0);
	}

	final protected void waitForRunner(final BatchRunner runner, int seconds)
	{
		try
		{
			if (runner.getRoute().size() >= 2)
			{
				sleep(10L); // Hier wird ein Thread Switch erzwungen.
				log.debug("Gibt's Batches? " + BatchExecuter.hasBatches());
			}
			log.info(LogUtil.printf("Warte %d Sekunden auf Ausführung von [%s]...", seconds, runner));
			runner.join(1000L * seconds);
		}
		catch (InterruptedException e)
		{
			log.error(e.getLocalizedMessage(), e);
		}
	}

	final protected void dumpSwitches(final Route route)
	{
		for(Gleisteil gt : route)
		{
			if (gt instanceof Verzweigung)
			{
				final Verzweigung v = (Verzweigung)gt;

				log.info(v.getName() + ": " + v.getDirectionCode());
			}
		}
	}

	private final static Map<Character, DirectionCode> switchCodeMap = new HashMap<Character, DirectionCode>();

	static
	{
		switchCodeMap.put('L', DirectionCode.LEFT);
		switchCodeMap.put('R', DirectionCode.RIGHT);
		switchCodeMap.put('X', DirectionCode.CROSS);
		switchCodeMap.put(')', DirectionCode.ARC);
		switchCodeMap.put('(', DirectionCode.ARC);

	}

	final protected void assertSwitches(final BatchRunner runner, final String switchCodes)
	{
		assertSwitches(runner.getRoute(), switchCodes);
	}

	final private List<Verzweigung> getSwitches(List<Gleisteil> tracks)
	{
		final List<Verzweigung> list = new LinkedList<Verzweigung>();

		for(Gleisteil gt : tracks)
		{
			if (gt instanceof Verzweigung)
			{
				list.add((Verzweigung)gt);
			}
		}
		return list;
	}

	final protected void assertSwitches(final Route route, final String switchCodes)
	{
		int i = 0;
		List<Verzweigung> switches = getSwitches(route);

		Assert.assertEquals(
				"Die Zahl der Verzweigungen muss gleich der Zahl der Weichencodes sein!",
				switchCodes.length(), switches.size());
		for(Verzweigung v : switches)
		{
			Character c = switchCodes.charAt(i);
			DirectionCode dc = switchCodeMap.get(c); 

			Assert.assertNotNull(LogUtil.printf(
					"Unbekanntes Weichenkürzel '%c' an Stelle %d! Das ist Testfallfehler, kein Programmfehler!",c,i), 
					dc);
			Assert.assertEquals(LogUtil.printf(
					"Falsche Weichenstellung an %d. Weiche (%s)!", i+1, v.getName()),
					dc, v.getDirectionCode());
			Assert.assertSame(LogUtil.printf("Die Verzweigung %s konnte der Fahrstraße %s nicht zugeordnet werden!",
					v.getName(), route.toString()),
					route, Route.findConflictingRoute(v));
			
			if (v instanceof Weiche)
			{
				Weiche w = (Weiche)v;
				Assert.assertTrue(LogUtil.printf("An einer Weiche (%s) sollte es ein weiterführendes Gleisteil geben!", v.getName()),
						route.isPart(w, w.getA()) ||
						route.isPart(w, w.getB()) ||
						route.isPart(w, w.getC()));
			}
			else if (v instanceof DKW)
			{
				DKW dkw = (DKW)v;
				Assert.assertTrue(LogUtil.printf("An einer DKW (%s) sollte es ein weiterführendes Gleisteil geben!", v.getName()),
						route.isPart(dkw, dkw.getA()) ||
						route.isPart(dkw, dkw.getB()) ||
						route.isPart(dkw, dkw.getC()) ||
						route.isPart(dkw, dkw.getD()));
			}
			i++;
		}
		switches.clear();
	}

	final protected void assertSegments(final BatchRunner runner)
	{
		Route route = runner.getRoute();

		Assert.assertTrue(LogUtil.printf("Die Fahrstraße (%s) muss verriegelt sein!", route),
				route.isLocked());
		for (Abschnitt a = route.getLastSegment(); a != null; a = route.getPrevSegment(a))
		{
			for(Gleisteil gt : a.getTrackElements())
			{
				if (gt.isLocked())
				{
					Assert.assertSame(LogUtil.printf("Die Gleisteile (%s) einer Fahrstraße (%s) müssen zu dieser gehören!",
							gt.toString(), route.toString()),
							gt.getRoute(), route);
				}
			}
		}
		for (Gleisteil gt : route)
		{
			Assert.assertTrue(LogUtil.printf("Das Gleisteil (%s) muss verriegelt sein!", gt.toString()),
					gt.isLocked());
			Assert.assertSame(LogUtil.printf("Die Gleisteile (%s) einer Fahrstraße (%s) müssen zu dieser gehören!",
					gt.toString(), route.toString()),
					gt.getRoute(), route);
		}
	}

	final protected void assertSelectedRoute(final BatchRunner runner)
	{
		Assert.assertSame("Die gewählten Fahrstraßen sind nicht identisch!", 
				controller.getSelectedRoute(), runner.getRoute());
	}

	final protected Route assertRoute(
			final boolean    shunting,
			final boolean    direction,
			final String     switchCodes,
			final String ... segmentNames)
	{
		final Route route = assertStartRoute(shunting, direction, switchCodes, segmentNames);
		controller.removeAllRoutes();
		Assert.assertNull("Es darf keine Fahrstraße ausgewählt sein!", controller.getSelectedRoute());
		return route;
	}

	final protected Route assertStartRoute(
			final boolean    shunting,
			final boolean    direction,
			final String     switchCodes,
			final String ... segmentNames)
	{
		controller.clearSegmentSelection();
		Assert.assertNull("Es darf keine Fahrstraße ausgewählt sein!", controller.getSelectedRoute());
		selectSegments(segmentNames);
		for(Abschnitt a : controller.getSegmentSelection())
		{
			log.info(a);
		}

		final BatchRunner runner = controller.computeRoute(shunting, direction);
		if (runner != null)
		{
			final Route route  = runner.getRoute();

			Assert.assertFalse("Die Fahrstraße darf kein Wartungsmodus sein!", route.isMaintainance());
			waitForRunner(runner);

			if (runner.getException() != null)
			{
				controller.removeRoute(route);
				controller.clearSegmentSelection();
				throw runner.getException();
			}

			Assert.assertTrue(
					LogUtil.printf("Die Anzahl der Stützpunkte einer Fahrstraße (%s) muss mindestens 2 betragen!", route.toString()),
					route.getHopCount() >= 2);
			Assert.assertTrue(
					LogUtil.printf("Die Anzahl der Stützpunkte einer Fahrstraße (%s) kann nicht größer als die Auswahl sein!", route.toString()),
					route.getHopCount() <= segmentNames.length);
			Assert.assertTrue("Es muss eine Fahrstraße vorhanden sein!", Route.hasRoutes());

			assertSwitches(runner, switchCodes);
			assertSegments(runner);
			assertSelectedRoute(runner);
			Assert.assertEquals("Es darf kein Gleisabschnitt ausgewählt sein!", controller.getSegmentSelection().size(), 0);
			return route;
		}
		return null;
	}

	final protected void assertExtendRoute(
			final Route      route,
			final String     switchCodes,
			final String ... segmentNames)
	{
		selectSegments(segmentNames);
		for(Abschnitt a : controller.getSegmentSelection())
		{
			log.info(a);
		}

		BatchRunner runner = controller.extendRoute(route);
		waitForRunner(runner);

		assertSwitches(runner, switchCodes);
		assertSelectedRoute(runner);
		Assert.assertEquals("Es darf kein Gleisabschnitt ausgewählt sein!", controller.getSegmentSelection().size(), 0);
	}

	final protected void assertCloseRoute(final Route route)
	{
		controller.removeAllRoutes();
		Assert.assertNull("Es darf keine Fahrstraße ausgewählt sein!", controller.getSelectedRoute());
	}

	final protected void assertTour(
		final boolean    shunting,
		final boolean    direction,
		final String     switchCodes,
		final String ... segmentNames)
	{
		final Route   route    = assertStartRoute(shunting, direction, switchCodes, segmentNames);
		final boolean isStumpf = route.getLastSegment().isStumpf(direction);

		simulateTour(route);
		if (isStumpf)
		{
			controller.removeRoute(route);
		}
		Assert.assertNull("Es darf keine Fahrstraße ausgewählt sein!", controller.getSelectedRoute());
	}

	private final static class RouteSimulator extends Thread
	{
		private final DummyConnection dc = controller.getDummyConnection();
		private final Route route;
		private final int   maxSegments;

		private RouteSimulator(final Route route, final int maxSegments)
		{
			if (!controller.isDummyConnection())
			{
				throw new IllegalStateException("Simulationen können nur auf einer Dummy-Connection durchgeführt werden!");
			}
			this.route       = route;
			this.maxSegments = maxSegments;
		}

		public void run()
		{
			try
			{
				Abschnitt  act, succ;
				MrwMessage msg;
				int        count = 0;

				act = route.getFirstSegment();
				while((succ = route.getSuccSegment(act)) != null)
				{
					// Einfahren in nächsten Gleisabschnitt
					msg = MrwMessage.createResultMessage(
							Command.GETRBS, MsgCode.MSG_OK,
							succ.getMicroControllerId(), succ.getDeviceUnitNumber());
					msg.addDataByte(OccupationCode.OCCUPIED.getOccupationCode());
					dc.simulateReception(msg);
					sleep(SEGMENT_ENTER_DELAY);
					Assert.assertFalse(LogUtil.printf("%s -> %s", act, succ), route.checkIfSegmentLeft(act));
					Assert.assertFalse(LogUtil.printf("%s -> %s", act, succ), route.checkIfSegmentEntered(succ));

					// Ausfahren aus aktuellem Gleisabschnitt
					msg = MrwMessage.createResultMessage(
							Command.GETRBS, MsgCode.MSG_OK,
							act.getMicroControllerId(), act.getDeviceUnitNumber());
					msg.addDataByte(OccupationCode.FREE.getOccupationCode());
					dc.simulateReception(msg);
					sleep(SEGMENT_TRAVERSE_DELAY);
					
					count++;
					log.debug(LogUtil.printf("%d. Gleisabschnitt (%s)", count, act.getNumber()));
					if ((maxSegments > 0) && (count >= maxSegments))
					{
						log.info(LogUtil.printf("Tour wird nach %d Gleisabschnitten abgeschaltet...", count));
						succ = null;
					}
					act = succ;
				}
				sleep(ROUTE_CLEAR_DELAY);
				log.info("Tour beendet.");
			}
			catch (InterruptedException ie)
			{
				log.error(ie.getLocalizedMessage(), ie);
			}
			catch (IOException ioe)
			{
				log.error(ioe.getLocalizedMessage(), ioe);
			}
		}
	}

	final protected void simulateTour(final Route route)
	{
		simulateTour(route, 0);
	}

	final protected void simulateTour(final Route route, int maxSegments)
	{
		if (controller.isDummyConnection())
		{
			log.info("Simuliere Fahrt durch Fahrstraße " + route);
			route.getFirstSegment().occupy();
			try
			{
				final RouteSimulator simulator = new RouteSimulator(route, maxSegments);

				simulator.start();
				simulator.join();
			}
			catch (InterruptedException ie)
			{
				log.error(ie.getLocalizedMessage(), ie);
			}
			finally
			{
				log.info("Fahrsimulation beendet.");
			}
		}
		else
		{
			log.info("Im Realmodus wird keine Fahrsimulation durchgeführt!");
		}
	}
}
