/*
**
**	$Filename:	AussenRechts.java $
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

package de.itemis.mrw.action.tour;

import de.morknet.mrw.gui.info.TourInfo;

public class AussenRechts extends TourInfo {
	private final static String names[] =
	{
		"N3",
		"W3c",
		"O1a,O2a,O3a,O4a"
	};

	private final static TourInfo tour = new AussenRechts();
	
	private AussenRechts()
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
		return "AuÃen rechts";
	}

	@Override
	public boolean isLoop()
	{
		return true;
	}
}
