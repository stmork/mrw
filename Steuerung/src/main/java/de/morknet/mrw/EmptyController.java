/*
**
**	$Filename:	EmptyController.java $
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

package de.morknet.mrw;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.morknet.mrw.util.LogUtil;

/**
 * Diese Klasse stellt ein einfaches Verhalten für einen {@link MrwController} bereit.
 * @author sm
 *
 */
public abstract class EmptyController extends MrwController
{
	private final static Log            log  = LogFactory.getLog(EmptyController.class);
	private              Route          route;

	/**
	 * Diese Methode implementiert die Ausgabe einer Fehlermeldung.
	 * @param message Die auszugebende Fehlermeldung. 
	 */
	@Override
	public void setErrorMessage(String message)
	{
		log.error(message);
	}

	/**
	 * Diese Methode implementiert die Ausgabe einer Statusmeldung.
	 * @param message die auszugebende Statusmeldung.
	 */
	@Override
	public void setMessage(String message)
	{
		log.info(message);
	}

	/**
	 * Diese Methode aktualisiert alle Bestandteile in der GUI. In diesem TestController bleibt die Implementierung leer.
	 */
	@Override
	public void updateCompleteUI()
	{
		log.debug(">updateCompleteUI()");
		List<Route> routes = Route.getRoutes();

		if (!routes.contains(getSelectedRoute()))
		{
			selectRoute(routes.isEmpty() ? null : routes.get(0));
		}
		log.debug("<updateCompleteUI()");
	}

	/**
	 * Diese Methode aktualisiert den Gleisplan in der GUI. In diesem TestController bleibt die Implementierung leer.
	 */
	@Override
	public void updateTrackPlan()
	{
		log.debug(" updateTrackPlan()");
	}

	@Override
	public void selectRoute(Route route)
	{
		log.debug(" selectRoute(" + route + ")");
		this.route = route;
	}

	@Override
	public Route getSelectedRoute()
	{
		log.debug(" getSelectRoute() = " + route);
		return this.route;
	}

	@Override
	public void updateClearedSelection()
	{
		log.debug(" updateClearedSelection()");
	}

	@Override
	public void updateActions()
	{
		log.debug(" updateActions()");
	}

	@Override
	public void deactivateAction(final Route route)
	{
		log.debug(LogUtil.printf(" deactivateAction(%s)", route));
	}

	@Override
	public void deactivateActions()
	{
		log.debug(" deactivateActions()");
	}
}
