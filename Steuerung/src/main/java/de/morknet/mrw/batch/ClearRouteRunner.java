/*
**
**	$Filename:	ClearRouteRunner.java $
**	$Revision: 931 $
**	$Date: 2010-04-14 10:39:15 +0200 (Mi, 14. Apr 2010) $
**	$Author: smork $
**	$Id: ClearRouteRunner.java 931 2010-04-14 08:39:15Z smork $
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

/**
 * Diese Thread-Klasse wird dazu benutzt, eine Fahrstra�e asynchron freizugeben. Sie wird
 * im Falle einer manuellen Manipulation einer Weiche benutzt.
 * @author smork
 *
 */
public class ClearRouteRunner extends BatchRunner
{
	/**
	 * Dieser Konstruktur initialisiert diesen BatchRunner zum Aufl�sen einer Fahrstra�e.
	 * @param controller Der steuernde Controller.
	 * @param route Die aufzul�sende Fahrstra�e.
	 */
	public ClearRouteRunner(MrwController controller, Route route)
	{
		super(controller, "Konfliktaufl�sung f�r manuelle Weichenschaltung", route, null);
	}

	@Override
	protected void doJob(MrwController controller)
	{
		synchronized(route)
		{
			route.computeClearBatches(executer);
			controller.updateTrackPlan();
			controller.send(executer);
			executer.clear();
			route.clear();
		}
	}
	
	@Override
	protected void doUpdateUI(MrwController controller)
	{
		controller.updateCompleteUI();
	}
}
