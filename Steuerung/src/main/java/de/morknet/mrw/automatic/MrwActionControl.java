/*
**
**	$Filename:	MrwActionControl.java $
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

package de.morknet.mrw.automatic;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.morknet.mrw.MrwController;
import de.morknet.mrw.Route;
import de.morknet.mrw.automatic.beermode.BeerMode;
import de.morknet.mrw.automatic.tourmode.TourMode;
import de.morknet.mrw.util.LogUtil;

/**
 * Diese Klasse steuert das Aktivierungsverhalten von Biermodus und Touren.
 * @author sm
 * @see BeerMode
 * @see TourMode
 *
 */
public abstract class MrwActionControl
{
	private   final static Log           log    = LogFactory.getLog(MrwActionControl.class);
	private                boolean       active = false;
	private   final        String        actionName;
	protected final        MrwController controller;
	protected              Route         route;

	/**
	 * Diesem Konstruktor wird er {@link MrwController} und der Action-Name übergeben
	 * @param controller Der {@link MrwController}.
	 * @param actionName Der Name dieser Action.
	 */
	public MrwActionControl(final MrwController controller, final String actionName)
	{
		this.controller = controller;
		this.actionName = actionName;
	}

	/**
	 * Dieser Callback wird aufgerufen, wenn diese Action aktiviert wurde.
	 * @return Erfolg
	 */
	abstract public    boolean onActivate();

	/**
	 * Dieser Callback wird aufgerufen, wenn diese Action deaktiviert wurde.
	 * @return Erfolg
	 * @see MrwActionControl
	 */
	abstract public    boolean onDeactivate();

	/**
	 * Diese Methode (de-)aktiviert diese Action je nach vorhergehendem Betriebszustand. Dementsprechend werden
	 * die Methoden {@link MrwActionControl#onActivate()} bzw. {@link #onDeactivate} aufgerufen. 
	 */
	public final void run()
	{
		log.debug(">run() active=" + active);
		
		try
		{
			if (active)
			{
				if (onDeactivate())
				{
					active = false;
				}
			}
			else
			{
				if (onActivate())
				{
					active = true;
				}
			}
		}
		finally
		{
			controller.updateActions();
		}
		
		log.debug("<run() active=" + active);
	}

	/**
	 * Diese Methode dient dazu, im Fehlerfalle diese Action zu deaktivieren. Es wird
	 * dabei die Callback-Methode onDeactivate aufgerufen. Unabhängig vom Erfolg dieses
	 * Aufrufs ist diese Action danach deaktiviert!.
	 */
	public final void deactivate()
	{
		log.debug(">deactivate() active=" + active);
		if (active)
		{
			onDeactivate();
			active = false;
		}
		else
		{
			log.warn(LogUtil.printf("Action %s ist schon deaktiviert!", actionName));
		}
		
		log.debug("<deactivate() active=" + active);
		controller.updateActions();
	}
	
	/**
	 * Diese Methode meldet zurück, ob diese Action aktiv ist.
	 * @return Der Aktivierungszustand dieser Action.
	 */
	public final boolean isActive()
	{
		return active;
	}

	/**
	 * Diese Methode gibt die aktuell geschaltete Route zurück.
	 * @return Die aktuell geschaltete Route.s
	 */
	public final Route getRoute()
	{
		return this.route;
	}
	
	public String toString()
	{
		return this.actionName;
	}
}
