/*
**
**	$Filename:	AussenLinks.java $
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

public class AussenLinks extends TourInfo {
	private final static String names[] =
	{
		"N3",
		"O3c",
		"W1a,W2a,W3a,W4a"
	};

	private final static TourInfo tour = new AussenLinks();
	
	private AussenLinks()
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
		return "AuÃen links";
	}

	@Override
	public boolean isLoop()
	{
		return true;
	}
}
