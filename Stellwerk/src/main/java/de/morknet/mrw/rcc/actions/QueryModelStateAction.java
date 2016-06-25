/*
**
**	$Filename:	QueryModelStateAction.java $
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
 * Diese {@link Action} löst eine Abfrage nach dem Zustand der Modelleisenbahn aus.
 * @author sm
 *
 */
public class QueryModelStateAction extends Action
{
	private static final Log log = LogFactory.getLog(QueryModelStateAction.class);
	private static final Controller controller = Controller.getController();

	/**
	 * Der Konstruktur benennt diese {@link Action}.
	 */
	public QueryModelStateAction()
	{
		super("Zustand der Modelleisenbahn");
	}

	@Override
	public void run()
	{
		log.info("Zustand der Modelleisenbahn abfragen");
		controller.queryModelState();
	}
}
