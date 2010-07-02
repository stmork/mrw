/*
**
**	$Filename:	RoutingRequirementsMissingException.java $
**	$Revision: 931 $
**	$Date: 2010-04-14 10:39:15 +0200 (Mi, 14. Apr 2010) $
**	$Author: smork $
**	$Id: RoutingRequirementsMissingException.java 931 2010-04-14 08:39:15Z smork $
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
 * Diese RuntimeException wird geworfen, wenn w�hrend des Berechnens einer Fahrstra�e
 * eine Voraussetzung nicht erf�llt ist.
 * @author smork
 *
 */
public class RoutingRequirementsMissingException extends RoutingException {
	private static final long serialVersionUID = 1L;

	/**
	 * Dieser Konstruktur bereitet eine Fehlermeldung mit der �bergebenen Fahrstra�e auf.
	 * @param message Eine erg�nzende Fehlermeldung.
	 * @param route Die verursachende Fahrstra�e
	 */
	public RoutingRequirementsMissingException(final Route route, final String message)
	{
		super(route, LogUtil.printf("%s (%s)!", message, route));
	}
}
