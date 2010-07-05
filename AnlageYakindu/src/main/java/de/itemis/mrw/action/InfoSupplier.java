/*
**
**	$Filename:	InfoSupplier.java $
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

import java.util.ArrayList;
import java.util.List;

import de.itemis.mrw.action.tour.AussenLinks;
import de.itemis.mrw.action.tour.AussenRechts;
import de.itemis.mrw.action.tour.TourLinks;
import de.itemis.mrw.action.tour.TourRechts;
import de.morknet.mrw.gui.info.BeerModeInfo;
import de.morknet.mrw.gui.info.TourInfo;

public class InfoSupplier
{
	private final static List<TourInfo> tourList = new ArrayList<TourInfo>();
	
	static
	{
		tourList.add(TourLinks.getTour());
		tourList.add(TourRechts.getTour());
		tourList.add(AussenLinks.getTour());
		tourList.add(AussenRechts.getTour());
	}
	
	public static List<TourInfo> getTourInfos()
	{
		return tourList;
	}

	public static BeerModeInfo getBeerModeInfo()
	{
		return AnlageYakinduBeerModeInfo.getBeerModeInfo();
	}
}
