/*
**
**	$Filename:	SegmentWithoutRailException.java $
**	$Revision: 931 $
**	$Date: 2010-04-14 10:39:15 +0200 (Mi, 14. Apr 2010) $
**	$Author: smork $
**	$Id: SegmentWithoutRailException.java 931 2010-04-14 08:39:15Z smork $
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

import de.morknet.mrw.base.Abschnitt;
import de.morknet.mrw.util.LogUtil;

/**
 * Diese Exception wird geworfen, wenn eine Fahrstra�e �ber einen Gleisabaschnitt ohne Gleis gef�hrt werden
 * soll.
 * @author smork
 *
 */
public class SegmentWithoutRailException extends RoutingRuntimeException
{
	private static final long serialVersionUID = 1L;

	/**
	 * Dieser Konstruktur bereitet eine Fehlermeldung mit dem �bergebenen Gleisabschnitt auf.
	 * @param route Die verursachende Fahrstra�e.
	 * @param abschnitt Der verursachende Gleisabschnitt.
	 */
	public SegmentWithoutRailException(final Route route, final Abschnitt abschnitt)
	{
		super(route, LogUtil.printf("Gleisabschnitt (%s) hat kein Startgleis!", abschnitt.getNumber()));
	}
}
