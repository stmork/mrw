/*
**
**	$Filename:	BeerModeTest.java $
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

package de.morknet.mrw.test;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;

import de.morknet.mrw.ModellFactory;
import de.morknet.mrw.Route;
import de.morknet.mrw.automatic.beermode.BeerMode;
import de.morknet.mrw.base.Abschnitt;
import de.morknet.mrw.util.LogUtil;

public class BeerModeTest extends ControllerTestBase
{
	private final static Log            log        = LogFactory.getLog(BeerModeTest.class);
	private final static int            TOUR_COUNT = 3;

	@Test
	public void beermode()
	{
		Assert.assertNotNull("Es muss einen Schattenbahnhof geben!",   BeerMode.getPoolStation());
		Assert.assertNotNull("Es muss einen Durchgangsbahnhof geben!", BeerMode.getViaStation());

		if (controller.isDummyConnection())
		{
			simulate(BeerMode.getBeerModeRight(controller));
			simulate(BeerMode.getBeerModeLeft(controller));
		}
		else
		{
			log.info("Im Realmodus kann der Biermodus nur bedingt getestet werden!");
		}
	}

	private final void simulate(final BeerMode beermode)
	{
		List<Abschnitt> free = BeerMode.getPoolStation().getListOfDirectedSegments(false, beermode.getDirection());
		Route           route;
		
		Assert.assertNotNull("Der Biermodus muss existieren!", beermode);
		
		// Spezialität von Mork2-Anlage
		ModellFactory.getInstance().findSegment("S1").occupy();

		// N-1 Gleise im Schattenbahnhof vorbelegen
		for (int i = 1;i < free.size();i++)
		{
			free.get(i).occupy();
		}
		
		// Biermodus starten
		Assert.assertFalse(LogUtil.printf("Der Biermodus %s muss abgeschaltet sein!", beermode), beermode.isActive());
		beermode.run();
		Assert.assertTrue(LogUtil.printf("Der Biermodus %s muss aktiv sein!", beermode), beermode.isActive());

		// Touren durchführen
		for (int count = 1; count < TOUR_COUNT; count++)
		{
			route = beermode.getRoute();
			Assert.assertNotNull("Es muss eine Fahrstraße vorhanden sein!", route);
			simulateTour(route);
		}
		
		// Deaktivieren. Nächste Fahrstraße müsste trotzdem schon aktiv
		// sein. Die ziehen wir dann aber noch durch.
		beermode.deactivate();
		route = beermode.getRoute();
		Assert.assertNotNull("Es muss eine Fahrstraße vorhanden sein!", route);
		simulateTour(route);
		
		for(Abschnitt a : free)
		{
			a.free();
		}
	}
}
