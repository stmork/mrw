/*
**
**	$Filename:	QueryMicroControllerStateAction.java $
**	$Revision$
**	$Author$
**	$Id$
**
**	Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
**
**	This program and the accompanying materials are made available under the
**	terms of the Eclipse Public License v1.0.
**
**	The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
**
**
*/

package de.morknet.mrw.rcc.actions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.jface.action.Action;

import de.morknet.mrw.rcc.Controller;

/**
 * Diese {@link Action} löst eine Überführung der Modelleisenbahn in einen definierten
 * Ausgangszustand aus.
 * @author sm
 *
 */
public class ResetModelRailWayAction extends Action
{
	private static final Log log = LogFactory.getLog(ResetModelRailWayAction.class);
	private static final Controller controller = Controller.getController();

	/**
	 * Der Konstruktur benennt diese {@link Action}.
	 */
	public ResetModelRailWayAction()
	{
		super("Reset der Modelleisenbahn");
	}

	@Override
	public void run()
	{
		log.info("Reset der Modelleisenbahn");
		controller.resetModelRailWay();
	}
}
