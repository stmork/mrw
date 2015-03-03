/*
**
**	$Filename:	ReinigungstourLinks.java $
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

package de.morknet.mrw.action.tour;

import de.morknet.mrw.gui.info.TourInfo;

public class ReinigungstourLinks extends TourInfo
{
	private final static String names[] =
	{
		"Bahnhof Schattenbahnhof 201",
		"Bahnhof Schattenbahnhof 203",
		"Bahnhof Alt Ulm G",  // N 2 ->   3
		"Bahnhof Alt Ulm P3,Bahnhof Alt Ulm P4,Bahnhof Alt Ulm P6,Bahnhof Alt Ulm P5,Bahnhof Alt Ulm P7", // 3 -> S 3
		"Bahnhof Schattenbahnhof G",
		"Bahnhof Schattenbahnhof P2,Bahnhof Schattenbahnhof P3,Bahnhof Schattenbahnhof P4,Bahnhof Schattenbahnhof P5",
		"Strecke West H",     // N 3 ->   3
		"Bahnhof Alt Ulm P3,Bahnhof Alt Ulm P4,Bahnhof Alt Ulm P6,Bahnhof Alt Ulm P5,Bahnhof Alt Ulm P7", // 3 -> S 2
		"Bahnhof Schattenbahnhof 201",
		"Bahnhof Schattenbahnhof 203",
		"Bahnhof Alt Ulm G",  // N 2 ->   2 
		"Bahnhof Alt Ulm P2", //   2 -> S 3
		"Bahnhof Schattenbahnhof G",
		"Bahnhof Schattenbahnhof P3,Bahnhof Schattenbahnhof P4,Bahnhof Schattenbahnhof P5,Bahnhof Schattenbahnhof P2",
		"Strecke West H",     // N 3 ->   2
		"Bahnhof Alt Ulm P2", //   2 -> S 2

		"Bahnhof Schattenbahnhof 201",
		"Bahnhof Schattenbahnhof 203",
		"Bahnhof Alt Ulm G",  // N 2 ->   3
		"Bahnhof Alt Ulm P3,Bahnhof Alt Ulm P4,Bahnhof Alt Ulm P6,Bahnhof Alt Ulm P5,Bahnhof Alt Ulm P7", // 3 -> S 3
		"Bahnhof Schattenbahnhof G",
		"Bahnhof Schattenbahnhof P4,Bahnhof Schattenbahnhof P5,Bahnhof Schattenbahnhof P2,Bahnhof Schattenbahnhof P3",
		"Strecke West H",     // N 3 ->   3
		"Bahnhof Alt Ulm P3,Bahnhof Alt Ulm P4,Bahnhof Alt Ulm P6,Bahnhof Alt Ulm P5,Bahnhof Alt Ulm P7", // 3 -> S 2
		"Bahnhof Schattenbahnhof 201",
		"Bahnhof Schattenbahnhof 203",
		"Bahnhof Alt Ulm G",  // N 2 ->   2 
		"Bahnhof Alt Ulm P2", //   2 -> S 3
		"Bahnhof Schattenbahnhof G",
		"Bahnhof Schattenbahnhof P5,Bahnhof Schattenbahnhof P2,Bahnhof Schattenbahnhof P3,Bahnhof Schattenbahnhof P4",
		"Strecke West H",     // N 3 ->   2
		"Bahnhof Alt Ulm P2"  //   2 -> S 2
	};

	private final static TourInfo tour = new ReinigungstourLinks();

	public static TourInfo getTour()
	{
		return tour;
	}

	private ReinigungstourLinks()
	{
		super(names);
	}

	@Override
	public boolean getDirection()
	{
		return false;
	}

	@Override
	public String getName()
	{
		return "Reinigung links";
	}

	@Override
	public boolean isLoop()
	{
		return true;
	}
}
