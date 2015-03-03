/*
**
**	$Filename:	ModelInvalidException.java $
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

/**
 * Diese Exception wird geworfen, wenn die Instanz einer Modelleisenbahn ungültig ist.
 * @author smork
 *
 */
public class ModelInvalidException extends InstanceException
{
	/**
	 * Diese Konstruktur bereitet eine Fehlermeldung auf.
	 * @param errors Die Zahl der aufgetretenen Fehler.
	 */
	public ModelInvalidException(final int errors)
	{
		super("Validierungsfehler! Fehlerzahl: " + errors);
	}

	private static final long serialVersionUID = 1L;
}
