/*
**
**	$Filename:	Trigger.java $
**	$Revision$
**	$Date$
**	$Author$
**	$Id$
**
**	Copyright (C) 2011 committers of this modelrailway project. All rights reserved.
**
**	This program and the accompanying materials are made available under the
**	terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
**	which accompanies this distribution.
**
**	The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
**
**
*/

package de.morknet.mrw.automatic;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.morknet.mrw.MrwController;
import de.morknet.mrw.Route;
import de.morknet.mrw.base.Abschnitt;

/**
 * Diese Klasse dient den Action-Klassen dazu, gewisse Fahrzustände mitzuteilen. Die Trigger werden
 * in einer Liste innerhalb des {@link MrwController} verwaltet und der Reihe nach abgearbeitet.
 * @author smork
 * @see MrwController
 *
 */
abstract public class Trigger
{
	private final static Log log  = LogFactory.getLog(Trigger.class);

	/**
	 * Diese Methode wird aufgerufen, wenn der {@link MrwController} das Verlassen
	 * eines Gleisabschnitts festgestellt hat.
	 * @param route Die Fahrstraße zu der der verlassene Gleisabschnitt zählt.
	 * @param abschnitt Der verlassene Gleisabschnitt.
	 * @see MrwController
	 */
	public void railLeft(final Route route, final Abschnitt abschnitt)
	{
		log.debug("=railLeft() " + abschnitt.getName());
	}
	
	/**
	 * Diese Methode wird aufgerufen, wenn der {@link MrwController} das Erreichen
	 * eines Gleisabschnitts festgestellt hat.
	 * @param route Die Fahrstraße zu der der erreichte Gleisabschnitt zählt.
	 * @param abschnitt Der erreichte Gleisabschnitt.
	 * @see MrwController
	 */
	public void railEntered(final Route route, final Abschnitt abschnitt)
	{
		log.debug("=railEntered() " + abschnitt.getName());
	}

	/**
	 * Diese Methode wird aufgerufen, wenn der {@link MrwController} das Erreichen
	 * des letzten Gleisabschnitts festgestellt hat.
	 * @param route Die Fahrstraße zu der der letzte Gleisabschnitt zählt.
	 * @param abschnitt Der letzte erreichte Gleisabschnitt.
	 * @return Die übergebene Fahrstraße.
	 * @see MrwController
	 */
	public Route endPointReached(final Route route, final Abschnitt abschnitt)
	{
		log.debug("=endPointReached() " + abschnitt.getName());
		return route;
	}

	/**
	 * Diese Methode wird aufgerufen, wenn eine Route entfernt wurde.
	 */
	public void routeRemoved()
	{
		log.debug("=routeRemoved()");
	}
}
