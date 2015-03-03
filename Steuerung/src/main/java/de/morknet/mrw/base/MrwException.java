/*
**
**	$Filename:	MrwException.java $
**	$Revision$
**	$Date$
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

package de.morknet.mrw.base;

import de.morknet.mrw.util.LogUtil;
import de.morknet.mrw.util.Protocol;

/**
 * Diese RuntimeException bildet die Basis für alle Eisenbahn-Exceptions.
 * @author smork
 *
 */
abstract public class MrwException extends RuntimeException
{
	private static final Protocol protocol = new Protocol();
	private static final long     serialVersionUID = 1L;

	/**
	 * Dieser Konstruktor nimmt die Fehlermeldung entgegen und trägt sie in ein Fehlerprotokoll ein.
	 * @param message Die Fehlermeldung.
	 */
	protected MrwException(final String message)
	{
		super(message);
		add();
	}
	
	private void add()
	{
		protocol.add(LogUtil.printf("%s (%s)", getMessage(), getClass().getSimpleName()));
	}
	
	/**
	 * Diese Methode gibt das Fehlerprotokoll aller MrwExceptions zurück.
	 */
	public static void protocol()
	{
		protocol.protocol();
	}
}
