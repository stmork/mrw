/*
**
**	$Filename:	TourRechts.java $
**	$Revision: 931 $
**	$Date: 2010-04-14 10:39:15 +0200 (Mi, 14. Apr 2010) $
**	$Author: smork $
**	$Id: TourRechts.java 931 2010-04-14 08:39:15Z smork $
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

package de.itemis.mrw.action.tour;

import de.morknet.mrw.gui.info.TourInfo;

public class TourRechts extends TourInfo {
	private final static String names[] =
	{
		"N3",
		"W3c",
		"O1a,O2a",
		"N2",
		"W2c",
		"O2a,O3a,O4a,O1a",
		"N3",
		"W3c",
		"O3a,O4a,O1a,O2a",
		"N3",
		"O3c",
		"O4a,O1a,O2a,O3a"
	};

	private final static TourInfo tour = new TourRechts();
	
	private TourRechts()
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
		return "Personentour rechts";
	}

	@Override
	public boolean isLoop()
	{
		return true;
	}
}
