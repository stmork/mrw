/*
**
**	$Filename:	SimpleRouteTest.java $
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

package de.morknet.mrw.test;

import org.junit.Assert;
import org.junit.Test;

import de.morknet.mrw.Route;

public class SimpleRouteTest extends ControllerTestBase
{
	@Test
	public void routes1()
	{
		assertRoute(true,  true,  "LXRRLRRR",            "S4c", "N3d");
		assertRoute(true,  true,  "RRXXXRRLLLRLL",       "S1h", "6", "N2d");
		assertRoute(false, false, "RRXRX",               "N2d", "S2d");
		assertRoute(false, true,  "RXRRLRRR",            "S3c", "N3d");
		assertRoute(true,  false, "RRRR)R(XL",           "N3d", "2", "S1f");
		assertRoute(false, true,  "RLRXRRLRRLLXRXLLRLL", "N3d", "B2");
	}
	
	@Test
	public void routes2()
	{
		assertRoute(true,  true,  "LXLLXR", "S4b", "N2c");
		assertRoute(true,  false, "R(", "N2c", "N1a");
	}
	
	@Test
	public void routes3()
	{
		assertRoute(true,  false, "R",           "S2a",  "S2b");
		assertRoute(true,  true,  "L)LLLRRR",    "S4b",  "4",  "N3c");
		assertRoute(true,  false, "RRRR(R((",    "N3c",  "S1a");
		assertRoute(true,  true,  "R)RRRR",      "S2b",  "N3d");
		assertRoute(true,  false, "RRLR",        "N3d",  "5");
		assertRoute(false, true,  "LRXR",        "SO5a", "3", "N1b");
		assertRoute(false, true,  "LRR)LLLRRRR", "SO4a", "4", "SO5a");
		assertRoute(false, true,  "LRRR)RRR",    "SO3a", "N7a");
	}
	
	@Test
	public void routeError()
	{
		final Route route = assertRoute(false, false, "", "S5a", "S2a");
		
		Assert.assertNull("Es darf keine Fahrstraße geschaltet worden sein!", route);
	}

	@Test
	public void extend()
	{
		Route route;

		route = assertStartRoute(true, true, "L)RRR",   "S4c", "N7a");
		assertExtendRoute(route,             "L)RRRLR", "N3d");
		assertCloseRoute(route);

		route = assertStartRoute(false, false, "LLRRLL",    "N2d", "S4a");
		assertExtendRoute(route,               "LLRRLLLX)", "SW1a"); 
		assertCloseRoute(route);

		route = assertStartRoute(false, false, "RRRLR",       "N3d", "S3a");
		assertExtendRoute(route,               "RRRLRR()",    "SW1a");
		assertExtendRoute(route,               "RRRLRR()RRX", "S2a");
		assertCloseRoute(route);
	}
	
	@Test
	public void free()
	{
		model.findSegment("N5a").free();
		model.findSegment("4").free();
	}
}
