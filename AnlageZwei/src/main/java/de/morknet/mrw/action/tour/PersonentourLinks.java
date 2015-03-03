/*
**
**	$Filename:	PersonentourLinks.java $
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

public class PersonentourLinks extends TourInfo {
	private final static String names[] =
	{
		"Bahnhof Schattenbahnhof 201",
		"Bahnhof Schattenbahnhof 203",
		"Bahnhof Alt Ulm G",
		"Bahnhof Alt Ulm P3,Bahnhof Alt Ulm P2",
		"Bahnhof Schattenbahnhof G",
		"Bahnhof Schattenbahnhof",
		"Strecke West H",
		"Bahnhof Alt Ulm P2,Bahnhof Alt Ulm P3"
	};

	private final static TourInfo tour = new PersonentourLinks();
	
	private PersonentourLinks()
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
