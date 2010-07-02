/*
**
**	$Filename:	ResetMicroControllerAction.java $
**	$Revision: 954 $
**	$Date: 2010-05-01 11:31:46 +0200 (Sa, 01. Mai 2010) $
**	$Author: smork $
**	$Id: ResetMicroControllerAction.java 954 2010-05-01 09:31:46Z smork $
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
 * Diese {@link Action} l�st einen Reset der Mikrocontroller aus.
 * @author sm
 *
 */
public class ResetMicroControllerAction extends Action
{
	private static final Log log = LogFactory.getLog(ResetMicroControllerAction.class);
	private static final Controller controller = Controller.getController();

	/**
	 * Der Konstruktur benennt diese {@link Action}.
	 */
	public ResetMicroControllerAction()
	{
		super("Reset der Mikrocontroller");
	}

	@Override
	public void run()
	{
		log.info("RESET");
		controller.resetMicroController();
	}
}
