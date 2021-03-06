/*
**
**	$Filename:	TestAction.java $
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
 * Diese {@link Action} schaltet alle Signale in einen Testmodus.
 * @author sm
 *
 */
public class TestAction extends Action {
	private static final Log        log        = LogFactory.getLog(TestAction.class);
	private static final Controller controller = Controller.getController();

	/**
	 * Der Konstruktur benennt diese {@link Action}.
	 */
	public TestAction()
	{
		super("Signale testen");
	}

	@Override
	public void run()
	{
		log.info("Signale testet");
		controller.testSignals();
	}
}
