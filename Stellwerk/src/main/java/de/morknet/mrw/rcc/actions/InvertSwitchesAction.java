/*
**
**	$Filename:	InvertSwitchesAction.java $
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
 * Diese {@link Action} lässt sämtliche Weichen in die entsprechend andere Lage umlaufen.
 * @author sm
 *
 */
public class InvertSwitchesAction extends Action
{
	private static final Log log = LogFactory.getLog(InvertSwitchesAction.class);
	private static final Controller controller = Controller.getController();

	/**
	 * Der Konstruktur benennt diese {@link Action}.
	 */
	public InvertSwitchesAction()
	{
		super("Alle Weichen umschalten");
	}

	@Override
	public void run()
	{
		log.info("Alle Weichen umschalten");
		controller.turnAllSwitches();
	}
}
