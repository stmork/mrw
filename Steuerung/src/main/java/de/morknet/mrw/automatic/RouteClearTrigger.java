/*
**
**	$Filename:	RouteClearTrigger.java $
**	$Revision: 931 $
**	$Date: 2010-04-14 10:39:15 +0200 (Mi, 14. Apr 2010) $
**	$Author: smork $
**	$Id: RouteClearTrigger.java 931 2010-04-14 08:39:15Z smork $
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

package de.morknet.mrw.automatic;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.morknet.mrw.MrwController;
import de.morknet.mrw.Route;
import de.morknet.mrw.base.Abschnitt;

/**
 * Dieser Trigger löst automatisch eine Fahrstraße auf, wenn der Endpunkt der Fahrstraße erreicht ist.
 * @see Route
 * @author sm
 *
 */
public class RouteClearTrigger extends Trigger
{
	private final static Log           log  = LogFactory.getLog(RouteClearTrigger.class);
	private final        MrwController ctrl;

	/**
	 * Dieser Konstruktur initialisiert das Auflösen von Fahrstraßen.
	 * @param ctrl
	 */
	public RouteClearTrigger(MrwController ctrl)
	{
		this.ctrl = ctrl;
	}

	@Override
	public Route endPointReached(Route route, Abschnitt abschnitt)
	{
		if (route.hasAutomaticRouteDeallocation())
		{
			if (!route.getLastSegment().isStumpf(route.getDirection()))
			{
				log.info("Fahrstraße auflösen: " + route);
				ctrl.removeRoute(route);
				route = null;
				log.info("Fahrstraße aufgelöst.");
			}
			else
			{
				log.info("Letzter Abschnitt ist erreicht und stumpf, daher Fahrstraße nicht aufgelöst");
			}
		}
		else
		{
			log.info("Die Fahrstraße wurde nicht aufgelöst.");
		}
		return route;
	}
}
