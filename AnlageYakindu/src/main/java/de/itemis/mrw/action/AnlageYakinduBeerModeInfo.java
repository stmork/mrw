/*
**
**	$Filename:	AnlageYakinduBeerModeInfo.java $
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

package de.itemis.mrw.action;

import de.morknet.mrw.gui.info.BeerModeInfo;

public class AnlageYakinduBeerModeInfo extends BeerModeInfo
{
	private final static String  poolStationName = "Bahnhof Brambusch";
	private final static String  viaStationName  = "Strecke Block";

	private final static AnlageYakinduBeerModeInfo info = new AnlageYakinduBeerModeInfo();
	
	private AnlageYakinduBeerModeInfo()
	{
	}

	@Override
	public String getPoolStationName()
	{
		return poolStationName;
	}

	@Override
	public String getViaStationName()
	{
		return viaStationName;
	}

	public static BeerModeInfo getBeerModeInfo()
	{
		return info;
	}
}
