/*
**
**	$Filename:	UnknownDirectionCodeException.java $
**	$Revision: 931 $
**	$Date: 2010-04-14 10:39:15 +0200 (Mi, 14. Apr 2010) $
**	$Author: smork $
**	$Id: UnknownDirectionCodeException.java 931 2010-04-14 08:39:15Z smork $
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

import de.morknet.mrw.base.DirectionCode;

/**
 * Diese Exception wird geworfen, wenn eine Weichenlage nicht eindeutig zugeordnet werden kann.
 * @author smork
 *
 */
public class UnknownDirectionCodeException extends RoutingRuntimeException {
	private static final long serialVersionUID = 1L;

	/**
	 * Dieser Konstruktur bereitet eine Fehlermeldung mit dem übergebenen Richtungscode auf.
	 * @param route Die verursachende Fahrstraße.
	 * @param code Der verursachende Richtungscode.
	 * @see DirectionCode
	 */
	public UnknownDirectionCodeException(final Route route, final DirectionCode code)
	{
		super(route, "Richtungs-Code: " + code);
	}
}
