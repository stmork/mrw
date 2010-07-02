/*
**
**	$Filename:	TourLinks.java $
**	$Revision: 931 $
**	$Date: 2010-04-14 10:39:15 +0200 (Mi, 14. Apr 2010) $
**	$Author: smork $
**	$Id: TourLinks.java 931 2010-04-14 08:39:15Z smork $
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

public class TourLinks extends TourInfo {
	private final static String names[] =
	{
		"N3",
		"O3c",
		"W1a,W2a",
		"N2",
		"O2c",
		"W2a,W3a,W4a,W1a",
		"N3",
		"O3c",
		"W3a,W4a,W1a,W2a",
		"N3",
		"O3c",
		"W4a,W1a,W2a,W3a"
	};

	private final static TourInfo tour = new TourLinks();
	
	private TourLinks()
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
		return false;
	}

	@Override
	public String getName()
	{
		return "Personentour links";
	}

	@Override
	public boolean isLoop()
	{
		return true;
	}
}
