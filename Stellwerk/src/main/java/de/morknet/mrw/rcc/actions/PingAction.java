/*
**
**	$Filename:	PingAction.java $
**	$Revision: 954 $
**	$Date: 2010-05-01 11:31:46 +0200 (Sa, 01. Mai 2010) $
**	$Author: smork $
**	$Id: PingAction.java 954 2010-05-01 09:31:46Z smork $
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
 * Diese {@link Action} l�st eine �berpr�fung der Verbindung zu den Mikrocontrollern aus.
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
