/*
**
**	$Filename:	BeerModeTrigger.java $
**	$Revision: 931 $
**	$Date: 2010-04-14 10:39:15 +0200 (Mi, 14. Apr 2010) $
**	$Author: smork $
**	$Id: BeerModeTrigger.java 931 2010-04-14 08:39:15Z smork $
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

package de.morknet.mrw.automatic.beermode;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.morknet.mrw.MrwController;
import de.morknet.mrw.Route;
import de.morknet.mrw.automatic.Trigger;
import de.morknet.mrw.base.Abschnitt;

/**
 * Diese Klasse stellt Callbacks zur Verfügung, um auf den Biermodus reagieren zu können.
 * @author smork
 *
 */
public class BeerModeTrigger extends Trigger {
	private final static Log            log  = LogFactory.getLog(BeerModeTrigger.class);
	private final        MrwController  controller;
	private              boolean        inDirection;
	private final        BeerMode       beermode;
	
	/**
	 * Der Konstruktor.
	 * @param controller Der steuernde Controller
	 * @param beermode Der Biermodus.
	 */
	public BeerModeTrigger(MrwController controller, BeerMode beermode)
	{
		this.controller = controller;
		this.beermode   = beermode;
	}
	
	/**
	 * Dieser Trigger-Callback reagiert darauf, dass der Zielabschnitt erreicht wurde. In
	 * diesem Fall wird der Biermodus angewiesen, sich einen neuen Zug auszuwählen.
	 * @param route Die geschaltete Fahrstraße.
	 * @param abschnitt Der Abschnitt, in den eingetreten wurde.
	 */
	@Override
	public void railEntered(Route route, Abschnitt abschnitt)
	{
		log.info(">railEntered()");
		if ((route == null) && (abschnitt.getGroup() == BeerMode.getPoolStation()))
		{
			controller.setMessage("Zug hat Ziel erreicht.");
			beermode.selectNextTrack(inDirection);
		}
		log.info("<railEntered()");
	}

	/**
	 * Diese Methode setzt die Fahrtrichtung relativ zur Zählrichtung der Gleise.
	 * @param inDirection Die Fahrtrichtung.
	 */
	public void setDirection(boolean inDirection)
	{
		this.inDirection = inDirection;
	}
}
