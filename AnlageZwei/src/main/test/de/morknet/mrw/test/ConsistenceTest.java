/*
**
**	$Filename:	ConsistenceTest.java $
**	$Revision: 931 $
**	$Date: 2010-04-14 10:39:15 +0200 (Mi, 14. Apr 2010) $
**	$Author: smork $
**	$Id: ConsistenceTest.java 931 2010-04-14 08:39:15Z smork $
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

import org.junit.Assert;
import org.junit.Test;

import de.morknet.mrw.base.Abschnitt;
import de.morknet.mrw.base.DKW;
import de.morknet.mrw.base.Gleisteil;
import de.morknet.mrw.base.Gruppe;
import de.morknet.mrw.base.MicroController;
import de.morknet.mrw.base.NoNeighbourFoundException;
import de.morknet.mrw.base.Signal;
import de.morknet.mrw.base.Weiche;

public class ConsistenceTest extends ControllerTestBase
{
	@Test
	public void actions()
	{
		Assert.assertNotNull("Biermodus nicht verfügbar!", model.getBeerModeInfo());
		Assert.assertNotNull("Tourinfos nicht verfügbar!", model.getTourInfos());
		model.areControllerReachable();
	}
	
	@Test
	public void signals()
	{
		for (Signal s : model.getSignals())
		{
			Assert.assertSame("Signal muss identisch sein!", model.findSignal(s.getName()), s);
			Assert.assertNotNull("Signal muss einen Namen haben!", s.toString());
			Assert.assertNotNull("Signal muss Zustand haben!", s.getSignalState());
		}
	}

	@Test
	public void segments()
	{
		for (Abschnitt a : model.getSegments())
		{
			Assert.assertSame("Gleisabschnitt muss identisch sein!", model.findSegment(a.getNumber()), a);
			checkSegment(a, false);
			checkSegment(a, true);
			controller.addSegmentSelection(a);
			controller.removeSegmentSelection(a);
		}
	}

	@Test
	public void tracks()
	{
		for (Gleisteil gt : model.getTrackElements())
		{
			Assert.assertSame("Gleisteil muss identisch sein!", model.find(gt.getName()), gt);
			Assert.assertNotNull(gt.toString());
			gt.aIsHigh();
			gt.isLocked();
			gt.isFree();
			gt.setLogX(gt.getLogX());
			gt.setLogY(gt.getLogY());
			gt.getLayoutInfo();
			gt.createConfigMessage();
			
			if (gt instanceof DKW)
			{
				DKW dkw = (DKW)gt;
				Assert.assertNotNull(dkw.getA());
				Assert.assertNotNull(dkw.getB());
				Assert.assertNotNull(dkw.getC());
				Assert.assertNotNull(dkw.getD());
				dkw.getDir();
			}
			else if (gt instanceof Weiche)
			{
				Weiche branch = (Weiche)gt;
				Assert.assertNotNull(branch.getA());
				Assert.assertNotNull(branch.getB());
				Assert.assertNotNull(branch.getC());
				branch.istBAbzweig();
				branch.getDir();
			}
		}
	}
	
	@Test
	public void mcu()
	{
		for (MicroController mc : model.getMicroController())
		{
			Assert.assertSame("Mikrocontroller muss identisch sein!", model.findMicroController(mc.getId()), mc);
		}
	}
	
	@Test
	public void groups()
	{
		for (Gruppe g : model.getGroups())
		{
			Assert.assertSame("Gruppe muss identisch sein!", model.findGroup(g.getName()), g);
			Assert.assertNotNull("Es muss ein Layout verfügbar sein!", g.getLayoutInfo());
			g.getListOfDirectedSegments(false, false);
			g.getListOfDirectedSegments(false, true);
			g.isInvertedDirection();
			for (Abschnitt a : g.getMainSegments())
			{
				Assert.assertSame("Gleisabschnitt muss identisch sein!", model.findSegment(a.getNumber()), a);
			}
			for(Signal s : g.getAllSignals())
			{
				Assert.assertSame("Signal muss identisch sein!", model.findSignal(s.getName()), s);
			}
			for (Abschnitt a : g.getSegments())
			{
				Assert.assertSame("Gleisabschnitt muss identisch sein!", model.findSegment(a.getNumber()), a);
				for (Gleisteil gt : a.getTrackElements())
				{
					Assert.assertSame("Gleisteil muss identisch sein!", model.find(gt.getName()), gt);
				}
			}
		}
	}

	private void checkSegment(final Abschnitt a, final boolean direction)
	{
		try
		{
			a.getMainSegment(direction);
		}
		catch(NoNeighbourFoundException nnfe)
		{
			// Do nothing.
		}
		a.hasMainSignal(direction);
		a.hasMainSignalWithSwitch(direction);
		for (Signal s : a.getSignals(direction))
		{
			Assert.assertSame("Signal muss identisch sein!", model.findSignal(s.getName()), s);
		}
	}
}
