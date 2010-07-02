/*
**
**	$Filename:	ControllerTest.java $
**	$Revision: 931 $
**	$Date: 2010-04-14 10:39:15 +0200 (Mi, 14. Apr 2010) $
**	$Author: smork $
**	$Id: ControllerTest.java 931 2010-04-14 08:39:15Z smork $
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

package de.morknet.mrw.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import de.morknet.mrw.Route;
import de.morknet.mrw.automatic.TourPoint;
import de.morknet.mrw.automatic.Trigger;
import de.morknet.mrw.batch.BatchRunner;
import de.morknet.mrw.gui.info.BeerModeInfo;
import de.morknet.mrw.gui.info.TourInfo;
import de.morknet.mrw.util.LogUtil;

public class ControllerTest extends ControllerTestBase
{
	@Test()
	public void ping()
	{
		controller.pingModelRailWay();
	}

	@Test
	public void queryModel()
	{
		controller.queryModelState();
	}

	@Test
	public void queryMCU()
	{
		controller.queryMicroControllerState();
	}
	
	@Test
	public void clearSignals()
	{
		controller.clearSignals();
	}
	
	@Test
	public void testSignals()
	{
		controller.testSignals();
	}

	@Test
	public void invertAllSwitches()
	{
		controller.turnAllSwitches();
	}

	@Test
	public void resetModelState()
	{
		controller.resetModelRailWay();
	}

	@Test
	public void resetMicroController()
	{
		controller.resetMicroController();
	}

	@Test
	public void maintainance()
	{
		BatchRunner runner = controller.maintainanceMode();
		Assert.assertNotNull("Der Wartungsmodus ließ sich nicht aktivieren!", runner);

		Route route = runner.getRoute();
		Assert.assertNotNull("Der Wartungsmodus hat keine Pseudofahrstraße!", route);

		controller.removeRoute(route);
	}

	@Test
	public void config()
	{
		if (controller.isDummyConnection())
		{
			controller.configMicroController();
		}
	}

	@Test
	public void gui()
	{
		controller.setMessage("Start!");
		controller.updateTrackPlan();
		controller.updateCompleteUI();
		controller.setMessage("End!");
	}

	@Test
	public void beermode()
	{
		BeerModeInfo info = model.getBeerModeInfo();
		Assert.assertNotNull("Info über Biermodus nicht verfügbar!", info);
		
		String name = info.getPoolStationName();
		Assert.assertNotNull("Der Name des Schattenbahnhofs ist nicht verfügbar!", name);
		Assert.assertNotNull("Der Schattenbahnhof ist nicht auffindbar!", model.findGroup(name));

		name = info.getViaStationName();
		Assert.assertNotNull("Der Name des Durchgangsbahnhofs ist nicht verfügbar!", name);
		Assert.assertNotNull("Der Durchgangsbahnhof ist nicht auffindbar!", model.findGroup(name));
	}

	@Test
	public void tourInfos()
	{
		List<TourInfo> infos = model.getTourInfos();
		Assert.assertNotNull("Infos über Touren nicht verfügbar!", infos);
		
		for(TourInfo info : infos)
		{
			boolean direction = info.getDirection();
			info.isShunting();
			info.isLoop();
			info.getName();
			List<String> names = info.getSegmentNames();
			
			Assert.assertNotNull("Beschreibungen für Tourpunkte sind nicht verfügbar!", names);
			for(String name : names)
			{
				TourPoint point = new TourPoint(name, direction);
				Assert.assertNotNull(LogUtil.printf("Abschnittsbeschreibung %s ist nicht auffindbar!", name), point);
				Assert.assertNotNull("Der Tourpunkt hat keine Gleisabschnitte!", point.getSegments());
			}
		}
	}

	@Test
	public void deactivateActions()
	{
		controller.deactivateActions();
	}

	@Test
	public void mcuReachable()
	{
		model.areControllerReachable();
	}

	private final static class TestTrigger extends Trigger
	{
	}
	
	@Test
	public void trigger()
	{
		final Trigger trigger = new TestTrigger();

		controller.addTrigger(trigger);
		controller.moveTrigger(trigger);
		controller.removeTrigger(trigger);
	}
}
