/*
**
**	$Filename:	RoutingRuntimeException.java $
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

import de.morknet.mrw.base.MrwException;

/**
 * Diese RuntimeException charakterisiert alle Exceptions, die zusammen mit der Fahrstra�ensuche
 * zusammenh�ngen.
 * @author smork
 *
 */
abstract public class RoutingRuntimeException extends MrwException
{
	private static final long serialVersionUID = 1L;
	private        final Route route;

	/**
	 * Dieser Konstruktur reicht eine Fehlermeldung weiter.
	 * @param route Die zugeh�rige Fahrstra�e
	 * @param message Die Fehlermeldung.
	 */
	protected RoutingRuntimeException(final Route route, final String message)
	{
		super(message);
		this.route = route;
	}

	/**
	 * Diese Methode gibt die Fahrstra�e zur�ck, durch die diese Exception ausgel�st wurde.
	 * @return Die ausl�sende Fahrstra�e.
	 */
	public Route getRoute()
	{
		return this.route;
	}
}
