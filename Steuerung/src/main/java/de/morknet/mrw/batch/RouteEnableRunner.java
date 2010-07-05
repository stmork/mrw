/*
**
**	$Filename:	RouteEnableRunner.java $
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

package de.morknet.mrw.batch;

import de.morknet.mrw.MrwController;
import de.morknet.mrw.Route;
import de.morknet.mrw.RoutingException;

/**
 * Diese Klasse schaltet eine Fahrstraße asynchron in einem eigenständigen Thread. Der Thread wird durch
 * einen {@link BatchRunner} definiert, der ein definiertes Fehlerauflösungsverhalten hat.
 * @author sm
 *
 */
public class RouteEnableRunner extends BatchRunner
{
	private boolean selectRoute = false;

	/**
	 * Dieser Konstruktur initialisiert das Schalten einer Fahrstraße 
	 * @param controller Der steuernde Controller.
	 * @param route Die zu schaltende Fahrstraße.
	 */
	public RouteEnableRunner(final MrwController controller, final Route route)
	{
		this(controller, route, null);
	}

	/**
	 * Dieser Konstruktur initialisiert das Schalten einer Fahrstraße 
	 * @param controller Der steuernde Controller.
	 * @param route Die zu schaltende Fahrstraße.
	 * @param feedback Die Feedback-Callbacks für Fehlerfälle
	 */
	public RouteEnableRunner(final MrwController controller, final Route route, final ExceptionCallback feedback)
	{
		super(controller, route.toString(), route, feedback);
	}

	@Override
	protected void doJob(MrwController controller)
	{
		log.debug(" >doJob()");
		route.dump();
		synchronized(route)
		{
			try
			{
				log.debug(">>doJob()");
				route.turn(executer);
				controller.updateTrackPlan();
				controller.send(executer);
				route.lock();
			}
			catch (RoutingException re)
			{
				log.error(re.getLocalizedMessage(), re);
				controller.setErrorMessage(re.getLocalizedMessage());
			}
			finally
			{
				executer.clear();
				log.debug("<<doJob()");
			}
		}
		controller.setMessage(" Fahrstraße vollständig geschaltet.");
		log.debug(" <doJob()");
	}

	@Override
	protected void doUpdateUI(MrwController controller)
	{
		controller.updateCompleteUI();
		if (selectRoute)
		{
			controller.selectRoute(route);
		}
	}

	/**
	 * Diese Methode aktiviert das automatische Selektieren einer Fahrstraße im {@link MrwController}, falls
	 * diese erfolgreich geschaltet werden konnte.
	 */
	public void enableRouteSelection()
	{
		selectRoute = true;
	}
}
