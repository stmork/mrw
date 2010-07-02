/*
**
**	$Filename:	ErrorProtocolAction.java $
**	$Revision: 954 $
**	$Date: 2010-05-01 11:31:46 +0200 (Sa, 01. Mai 2010) $
**	$Author: smork $
**	$Id: ErrorProtocolAction.java 954 2010-05-01 09:31:46Z smork $
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

import org.eclipse.jface.action.Action;

import de.morknet.mrw.base.MrwException;

/**
 * Diese {@link Action} löst die Ausgabe aller Fehler aus.
 * @author sm
 *
 */
public class ErrorProtocolAction extends Action
{
	/**
	 * Der Konstruktur benennt diese {@link Action}.
	 */
	public ErrorProtocolAction()
	{
		super("Fehlerprotokoll");
	}
	
	@Override
	public void run()
	{
		MrwException.protocol();
	}
}
