/*
**
**	$Filename:	ReinigungSchattenbahnhofRechts.java $
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

public class ReinigungSchattenbahnhofRechts extends TourInfo
{
	private final static String names[] =
	{
		"Strecke West 302",
		"Bahnhof Schattenbahnhof 304",
		"Bahnhof Schattenbahnhof N2,Bahnhof Schattenbahnhof N3,Bahnhof Schattenbahnhof N4,Bahnhof Schattenbahnhof N5",
		"Bahnhof Alt Ulm C",
		"Bahnhof Alt Ulm N6,Bahnhof Alt Ulm N2,Bahnhof Alt Ulm N3,Bahnhof Alt Ulm N4",

		"Strecke West 302",
		"Bahnhof Schattenbahnhof 304",
		"Bahnhof Schattenbahnhof N3,Bahnhof Schattenbahnhof N4,Bahnhof Schattenbahnhof N5,Bahnhof Schattenbahnhof N2",
		"Bahnhof Alt Ulm C",
		"Bahnhof Alt Ulm N2,Bahnhof Alt Ulm N3,Bahnhof Alt Ulm N4,Bahnhof Alt Ulm N6",

		"Strecke West 302",
		"Bahnhof Schattenbahnhof 304",
		"Bahnhof Schattenbahnhof N4,Bahnhof Schattenbahnhof N5,Bahnhof Schattenbahnhof N2,Bahnhof Schattenbahnhof N3",
		"Bahnhof Alt Ulm C",
		"Bahnhof Alt Ulm N3,Bahnhof Alt Ulm N4,Bahnhof Alt Ulm N6,Bahnhof Alt Ulm N2",

		"Strecke West 302",
		"Bahnhof Schattenbahnhof 304",
		"Bahnhof Schattenbahnhof N5,Bahnhof Schattenbahnhof N2,Bahnhof Schattenbahnhof N3,Bahnhof Schattenbahnhof N4",
		"Bahnhof Alt Ulm C",
		"Bahnhof Alt Ulm N4,Bahnhof Alt Ulm N6,Bahnhof Alt Ulm N2,Bahnhof Alt Ulm N3"
	};

	private final static TourInfo tour = new ReinigungSchattenbahnhofRechts();

	public static TourInfo getTour()
	{
		return tour;
	}

	private ReinigungSchattenbahnhofRechts()
	{
		super(names);
	}

	@Override
	public boolean getDirection()
	{
		return true;
	}

	@Override
	public String getName()
	{
		return "Reinigung Schattenbahnhof rechts";
	}

	@Override
	public boolean isLoop()
	{
		return true;
	}
}
