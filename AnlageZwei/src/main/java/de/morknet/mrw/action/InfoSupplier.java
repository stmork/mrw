/*
**
**	$Filename:	InfoSupplier.java $
**	$Revision: 931 $
**	$Date: 2010-04-14 10:39:15 +0200 (Mi, 14. Apr 2010) $
**	$Author: smork $
**	$Id: InfoSupplier.java 931 2010-04-14 08:39:15Z smork $
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

package de.morknet.mrw.action;

import java.util.ArrayList;
import java.util.List;

import de.morknet.mrw.action.beermode.AnlageZweiBeerModeInfo;
import de.morknet.mrw.action.tour.AussenRechts;
import de.morknet.mrw.action.tour.BergtourAbwaerts;
import de.morknet.mrw.action.tour.BergtourAufwaerts;
import de.morknet.mrw.action.tour.InnenLinks;
import de.morknet.mrw.action.tour.KreuztourLinks;
import de.morknet.mrw.action.tour.KreuztourRechts;
import de.morknet.mrw.action.tour.PersonentourLinks;
import de.morknet.mrw.action.tour.PersonentourRechts;
import de.morknet.mrw.action.tour.ReinigungSchattenbahnhofLinks;
import de.morknet.mrw.action.tour.ReinigungSchattenbahnhofRechts;
import de.morknet.mrw.action.tour.ReinigungstourLinks;
import de.morknet.mrw.action.tour.ReinigungstourRechts;
import de.morknet.mrw.gui.info.BeerModeInfo;
import de.morknet.mrw.gui.info.TourInfo;

public class InfoSupplier
{
	private final static List<TourInfo> tourList = new ArrayList<TourInfo>();

	static
	{
		tourList.add(PersonentourRechts.getTour());
		tourList.add(PersonentourLinks.getTour());
		tourList.add(KreuztourRechts.getTour());
		tourList.add(KreuztourLinks.getTour());
		tourList.add(AussenRechts.getTour());
		tourList.add(InnenLinks.getTour());
		tourList.add(ReinigungstourRechts.getTour());
		tourList.add(ReinigungstourLinks.getTour());
		tourList.add(ReinigungSchattenbahnhofRechts.getTour());
		tourList.add(ReinigungSchattenbahnhofLinks.getTour());
		tourList.add(BergtourAufwaerts.getTour());
		tourList.add(BergtourAbwaerts.getTour());
	}
	
	public static List<TourInfo> getTourInfos()
	{
		return tourList;
	}

	public static BeerModeInfo getBeerModeInfo()
	{
		return AnlageZweiBeerModeInfo.getBeerModeInfo();
	}
}
