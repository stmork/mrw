/*
**
**	$Filename:	LockWithoutRouteException.java $
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

package de.morknet.mrw;

import de.morknet.mrw.base.Gleisteil;

/**
 * Diese RuntimeException wird geworfen, wenn ein Gleisteil verriegelt werden soll, ohne dass es
 * zu einer Fahrstraße gehört.
 * @author smork
 *
 */
public class LockWithoutRouteException extends RoutingRuntimeException
{
	private static final long serialVersionUID = 1L;

	/**
	 * Dieser Konstruktur bereitet eine Fehlermeldung mit dem übergebenen Gleisteil auf.
	 * @param gleisteil Das verursachende Gleisteil.
	 */
	public LockWithoutRouteException(final Gleisteil gleisteil)
	{
		super(null, "Dieses Gleisteil (" + gleisteil.getName() + ") kann nicht ohne Fahrstraße verriegelt werden!");
	}
}
