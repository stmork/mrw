/*
**
**	$Filename:	CodeNotFoundException.java $
**	$Revision$
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

package de.morknet.mrw.comm;

import de.morknet.mrw.base.MrwException;

/**
 * Diese RuntimeException wird geworfen, wenn eine {@link MrwMessage} in einem illegalen
 * Zustand ist.
 * @author smork
 *
 */
public class IllegalMrwMessageException extends MrwException
{
	private static final long serialVersionUID = 1L;

	/**
	 * Dieser Konstruktor bereitet eine Fehlermeldung auf.
	 */
	public IllegalMrwMessageException(final String message)
	{
		super(message);
	}
}
