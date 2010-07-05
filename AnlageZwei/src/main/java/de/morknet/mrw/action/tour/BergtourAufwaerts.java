/*
**
**	$Filename:	BergtourAufwaerts.java $
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

package de.morknet.mrw.action.tour;

import de.morknet.mrw.gui.info.TourInfo;

public class BergtourAufwaerts extends TourInfo
{
	private final static String names[] =
	{
		"N3d",
		"Strecke West 302",
		"Bahnhof Schattenbahnhof 304",
		"Bahnhof Schattenbahnhof",
		"Bahnhof Alt Ulm C",
		"Bahnhof Alt Ulm N3",
		"Strecke West 302",
		"Bahnhof Schattenbahnhof 304",
		"Bahnhof Schattenbahnhof",
		"Bahnhof Alt Ulm C",
		"Bahnhof Alt Ulm N2",
		"Strecke Berg B",
		"Strecke Berg A",
		"Strecke Berg 1I"
	};

	private final static TourInfo tour = new BergtourAufwaerts();

	private BergtourAufwaerts()
	{
		super(names);
	}

	public static TourInfo getTour()
	{
		return tour;
	}

	@Override
	public boolean getDirection()
	{
		return true;
	}

	@Override
	public String getName()
	{
		return "Berg aufwärts";
	}

	@Override
	public boolean isLoop()
	{
		return false;
	}
}
