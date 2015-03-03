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
 * Diese RuntimeException charakterisiert alle Exceptions, die zusammen mit der Fahrstraßensuche
 * zusammenhängen.
 * @author smork
 *
 */
abstract public class RoutingRuntimeException extends MrwException
{
	private static final long serialVersionUID = 1L;
	private        final Route route;

	/**
	 * Dieser Konstruktur reicht eine Fehlermeldung weiter.
	 * @param route Die zugehörige Fahrstraße
	 * @param message Die Fehlermeldung.
	 */
	protected RoutingRuntimeException(final Route route, final String message)
	{
		super(message);
		this.route = route;
	}

	/**
	 * Diese Methode gibt die Fahrstraße zurück, durch die diese Exception ausgelöst wurde.
	 * @return Die auslösende Fahrstraße.
	 */
	public Route getRoute()
	{
		return this.route;
	}
}
