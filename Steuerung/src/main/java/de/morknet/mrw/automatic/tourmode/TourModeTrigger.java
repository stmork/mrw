/*
**
**	$Filename:	TourModeTrigger.java $
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

package de.morknet.mrw.automatic.tourmode;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.morknet.mrw.Route;
import de.morknet.mrw.automatic.Trigger;
import de.morknet.mrw.base.Abschnitt;

/**
 * Diese Klasse behandelt die Trigger für Fahrtstrecken. Er löst das Erweitern von Fahrtstraßen aus.
 * @see Route
 * @see TourMode
 * @author sm
 *
 */
public class TourModeTrigger extends Trigger
{
	private final static Log log  = LogFactory.getLog(TourModeTrigger.class);
	private final TourMode   tour;

	/**
	 * Der Konstruktur
	 * @param tour Die Fahrstrecke, die mit diesem {@link Trigger} verwaltet wird.
	 */
	public TourModeTrigger(TourMode tour)
	{
		this.tour = tour;
	}

	@Override
	public void railLeft(Route route, Abschnitt abschnitt)
	{
		log.info(">railLeft() " + tour);
		log.info(" " + abschnitt.getNumber());
		if (tour.isSegmentRelevant(abschnitt))
		{
			log.debug("Abschnitt " + abschnitt.getNumber() + " für [" + tour + "] relevant.");
			tour.extend();
		}
		log.info("<railLeft() " + tour);
	}

	@Override
	public Route endPointReached(Route route, Abschnitt abschnitt)
	{
		log.info(">endPointReached() " + tour);
		tour.extend();
		log.info("<endPointReached() " + tour);
		return route;
	}
	
	@Override
	public void routeRemoved()
	{
		log.info(">routeRemoved() " + tour);
		tour.extend();
		log.info("<routeRemoved() " + tour);
	}
}
