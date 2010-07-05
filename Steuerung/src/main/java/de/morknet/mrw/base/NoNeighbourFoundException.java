/*
**
**	$Filename:	NoNeighbourFoundException.java $
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

/**
 * Diese Exception wird bei der Suche f�r automatische Fahrstra�en geworfen, wenn die Modellgegebenheiten
 * nicht erf�llt sind.
 * @author smork
 *
 */
public class NoNeighbourFoundException extends InstanceException
{
	private static final long serialVersionUID = 1L;

	/**
	 * Dieser Konstruktur bereitet eine Fehlermeldung auf.
	 * @param abschnitt Der ausl�sende Gleisabschnitt.
	 */
	public NoNeighbourFoundException(final Abschnitt abschnitt)
	{
		super(LogUtil.printf("Es kann kein eindeutiges Nachbargleis f�r Abschnitt %s festgestellt werden!", abschnitt.getNumber()));
	}
}
