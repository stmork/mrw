/*
**
**	$Filename:	RouteIncompleteException.java $
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

package de.morknet.mrw;

import de.morknet.mrw.util.LogUtil;

/**
 * Diese RuntimeException wird geworfen, wenn die Fahrstraße nicht komplett eingerichtet werden konnte.
 * @author smork
 *
 */
public class RouteIncompleteException extends RoutingException
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Dieser Konstruktur bereitet eine Fehlermeldung mit der übergebenen Fahrstraße auf.
	 * @param route Die verursachende Fahrstraße.
	 */
	public RouteIncompleteException(final Route route)
	{
		super (route, LogUtil.printf("Fahrstraße (%s) konnte nicht komplett berechnet werden", route));
	}
}
