/*
**
**	$Filename:	TrackNotRoutedException.java $
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
 * Diese RuntimeException wird geworfen, wenn ein betroffenes Gleisteil nicht zur richtigen Fahrstra�e passt.
 * @author smork
 *
 */
public class TrackNotRoutedException extends RoutingRuntimeException {
	private static final long serialVersionUID = 1L;

	/**
	 * Dieser Konstruktur bereitet eine Fehlermeldung mit der �bergebenen Fahrstra�e und dem Gleisteil auf.
	 * @param route Die verursachende Fahrstra�e.
	 * @param gt Das verursachende Gleisteil.
	 */
	public TrackNotRoutedException(final Route route, final Gleisteil gt)
	{
		super(route, "Gleisteil nicht Bestandteil dieser Fahrstra�e! (" + gt.getRoute() + "/" + route + ")");
	}
}
