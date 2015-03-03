/*
**
**	$Filename:	RoutingRequirementsMissingException.java $
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

import de.morknet.mrw.util.LogUtil;

/**
 * Diese RuntimeException wird geworfen, wenn während des Berechnens einer Fahrstraße
 * eine Voraussetzung nicht erfüllt ist.
 * @author smork
 *
 */
public class RoutingRequirementsMissingException extends RoutingException {
	private static final long serialVersionUID = 1L;

	/**
	 * Dieser Konstruktur bereitet eine Fehlermeldung mit der übergebenen Fahrstraße auf.
	 * @param message Eine ergänzende Fehlermeldung.
	 * @param route Die verursachende Fahrstraße
	 */
	public RoutingRequirementsMissingException(final Route route, final String message)
	{
		super(route, LogUtil.printf("%s (%s)!", message, route));
	}
}
