/*
**
**	$Filename:	UnknownDeviceException.java $
**	$Revision: 931 $
**	$Date: 2010-04-14 10:39:15 +0200 (Mi, 14. Apr 2010) $
**	$Author: smork $
**	$Id: UnknownDeviceException.java 931 2010-04-14 08:39:15Z smork $
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
import de.morknet.mrw.util.NameUtil;


/**
 * Diese Exception wird geworfen, zu einer ID ein Gerät nict bekannt ist. Dies stellt ein Fehler im
 * Modell dar.
 * @author smork
 *
 */
public class UnknownDeviceException extends de.morknet.mrw.base.InstanceException
{
	/**
	 * Dieser Konstruktur bereitet eine Fehlermeldung auf.
	 * @param message Die ergänzende Fehlermeldung.
	 * @param id Die ID des Mikrocontrollers.
	 */
	public UnknownDeviceException(final String message, final int id)
	{
		super(LogUtil.printf("%s (ID=%0s)", message, NameUtil.getIdString(id)));
	}

	private static final long serialVersionUID = 1L;
}
