/*
**
**	$Filename:	StartRailDefinedException.java $
**	$Revision: 931 $
**	$Date: 2010-04-14 10:39:15 +0200 (Mi, 14. Apr 2010) $
**	$Author: smork $
**	$Id: StartRailDefinedException.java 931 2010-04-14 08:39:15Z smork $
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

/**
 * Diese Exception wird geworfen, wenn ein Startgleis schon definiert war.
 * @author smork
 *
 */
public class StartRailDefinedException extends InstanceException
{
	private static final long serialVersionUID = 1L;

	/**
	 * Dieserr Konstruktur bereitet eine Fehlermeldung auf.
	 * @param abschnitt Der verursachende Gleisabschnitt.
	 * @param start Das Startgleis im Gleisabschnitt.
	 * @param gleis Das Nachbargleis.
	 */
	public StartRailDefinedException(
			final Abschnitt abschnitt,
			final Gleis     start,
			final Gleis     gleis)
	{
		super(LogUtil.printf("Für Abschnitt %s wurde schon Gleis %s als Startgleis definiert. Gleis %s kann nicht mehr gesetzt werden!",
				abschnitt.getNumber(),
				start.getName(), gleis.getName()));
	}
}
