/*
**
**	$Filename:	ReinigungstourRechts.java $
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

public class ReinigungstourRechts extends TourInfo
{
	private final static String names[] =
	{
		"Strecke West 302",   // N 3 -> 3
		"Bahnhof Schattenbahnhof 304",
		"Bahnhof Schattenbahnhof N2,Bahnhof Schattenbahnhof N3,Bahnhof Schattenbahnhof N4,Bahnhof Schattenbahnhof N5",
		"Bahnhof Alt Ulm C",  // S 3 -> 2
		"Bahnhof Alt Ulm N2", // N 2 -> 2
		"Strecke West 202",
		"Bahnhof Schattenbahnhof 204",
		"Bahnhof Alt Ulm B",  // S 2 -> 2
		"Bahnhof Alt Ulm N2", // N 2 -> 3
		"Strecke West 302",
		"Bahnhof Schattenbahnhof 304",
		"Bahnhof Schattenbahnhof N5,Bahnhof Schattenbahnhof N2,Bahnhof Schattenbahnhof N3,Bahnhof Schattenbahnhof N4",
		"Bahnhof Alt Ulm C",  // S 3 -> 3
		"Bahnhof Alt Ulm N3,Bahnhof Alt Ulm N4,Bahnhof Alt Ulm N6,Bahnhof Alt Ulm N5,Bahnhof Alt Ulm N7", // N 3 -> 2
		"Strecke West 202",
		"Bahnhof Schattenbahnhof 204",
		"Bahnhof Alt Ulm B",  // S 2 -> 3
		"Bahnhof Alt Ulm N3,Bahnhof Alt Ulm N4,Bahnhof Alt Ulm N6,Bahnhof Alt Ulm N5,Bahnhof Alt Ulm N7",

		"Strecke West 302",   // N 3 -> 3
		"Bahnhof Schattenbahnhof 304",
		"Bahnhof Schattenbahnhof N4,Bahnhof Schattenbahnhof N5,Bahnhof Schattenbahnhof N2,Bahnhof Schattenbahnhof N3",
		"Bahnhof Alt Ulm C",  // S 3 -> 2
		"Bahnhof Alt Ulm N2", // N 2 -> 2
		"Strecke West 202",
		"Bahnhof Schattenbahnhof 204",
		"Bahnhof Alt Ulm B",  // S 2 -> 2
		"Bahnhof Alt Ulm N2", // N 2 -> 3
		"Strecke West 302",
		"Bahnhof Schattenbahnhof 304",
		"Bahnhof Schattenbahnhof N3,Bahnhof Schattenbahnhof N4,Bahnhof Schattenbahnhof N5,Bahnhof Schattenbahnhof N2",
		"Bahnhof Alt Ulm C",  // S 3 -> 3
		"Bahnhof Alt Ulm N3,Bahnhof Alt Ulm N4,Bahnhof Alt Ulm N6,Bahnhof Alt Ulm N5,Bahnhof Alt Ulm N7", // N 3 -> 2
		"Strecke West 202",
		"Bahnhof Schattenbahnhof 204",
		"Bahnhof Alt Ulm B",  // S 2 -> 3
		"Bahnhof Alt Ulm N3,Bahnhof Alt Ulm N4,Bahnhof Alt Ulm N6,Bahnhof Alt Ulm N5,Bahnhof Alt Ulm N7"
	};

	private final static TourInfo tour = new ReinigungstourRechts();

	public static TourInfo getTour()
	{
		return tour;
	}

	private ReinigungstourRechts()
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
		return "Reinigung rechts";
	}

	@Override
	public boolean isLoop()
	{
		return true;
	}
}
