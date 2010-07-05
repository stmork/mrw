/*
**
**	$Filename:	PingAction.java $
**	$Revision$
**	$Date$
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
 * Diese {@link Action} löst eine Überprüfung der Verbindung zu den Mikrocontrollern aus.
 * @author sm
 *
 */
public class PingAction extends Action {
	private static final Log        log        = LogFactory.getLog(PingAction.class);
	private static final Controller controller = Controller.getController();

	/**
	 * Der Konstruktur benennt diese {@link Action}.
	 */
	public PingAction()
	{
		super("Ping an Mikrocontroller");
	}

	@Override
	public void run()
	{
		log.info("Ping");
		controller.pingModelRailWay();
	}
}
