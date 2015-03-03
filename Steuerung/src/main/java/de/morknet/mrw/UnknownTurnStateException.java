/*
**
**	$Filename:	UnknownTurnStateException.java $
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

import de.morknet.mrw.base.DKW;
import de.morknet.mrw.base.Gleisteil;
import de.morknet.mrw.base.Weiche;
import de.morknet.mrw.util.LogUtil;

/**
 * Diese RuntimeException wird geworfen, wenn eine Schaltrichtung an einer Weiche nicht ermittelt werden konnte.
 * @author smork
 *
 */
public class UnknownTurnStateException extends RoutingRuntimeException
{
	private static final long serialVersionUID = 1L;

	/**
	 * Dieser Konstruktur bereitet eine Fehlermeldung mit den übergebenen Gleisteilen auf.
	 * @param route Die verursachende Fahrstraße.
	 * @param prev Das vermeintlich vorhergehende Gleisteil.
	 * @param branch Die Weiche, an der der Fehler aufgetreten ist.
	 * @param next Das vermeintlich nächste Gleisteil.
	 */
	public UnknownTurnStateException(final Route route, final Gleisteil prev, final Weiche branch, final Gleisteil next)
	{
		super(route, LogUtil.printf(
				"Verbindungen: %s -> %s -> %s a=%s b=%s c=%s",
				prev.getName(), branch.getName(), next.getName(),
				branch.getA().getName(),
				branch.getB().getName(),
				branch.getC().getName()));
	}

	/**
	 * Dieser Konstruktur bereitet eine Fehlermeldung mit den übergebenen Gleisteilen auf.
	 * @param route Die verursachende Fahrstraße.
	 * @param prev Das vermeintlich vorhergehende Gleisteil.
	 * @param dkw Die DKW, an der der Fehler aufgetreten ist.
	 * @param next Das vermeintlich nächste Gleisteil.
	 */
	public UnknownTurnStateException(final Route route, final Gleisteil prev, final DKW dkw, final Gleisteil next)
	{
		super(route, LogUtil.printf(
				"Verbindungen: %s -> %s -> %s a=%s b=%s c=%s d=%s",
				prev.getName(), dkw.getName(), next.getName(),
				dkw.getA().getName(),
				dkw.getB().getName(),
				dkw.getC().getName(),
				dkw.getD().getName()));
	}
}
