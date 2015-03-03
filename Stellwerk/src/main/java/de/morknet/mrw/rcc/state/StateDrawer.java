/*
**
**	$Filename:	StateDrawer.java $
**	$Revision$
**	$Date$
**	$Author$
**	$Id$
**
**	Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
**
**	This program and the accompanying materials are made available under the
**	terms of the Eclipse Public License v1.0.
**
**	The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
**
**
*/

package de.morknet.mrw.rcc.state;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.graphics.GC;

import de.morknet.mrw.base.DeviceUnit;

/**
 * Diese abstrakte Klasse definiert das Verhalten zum Zeichnen von GerÃâ¬ten (Signale, Weichen)
 * in einer GUI.
 * @see DeviceUnit
 * @author sm
 *
 */
abstract class StateDrawer
{
	/**
	 * Diese Methode bereitet ein GerÃâ¬t zum Zeichnen vor.
	 */
	abstract void prepare();
	
	/**
	 * Diese Methode veranlasst das Zeichnen in den angegebenen Grafikkontext.
	 * @param gc Der Grafikkontext.
	 */
	abstract void draw(GC gc);
	
	/**
	 * Diese Methode berechnet einen horizontalen Versatz abhÃâ¬ngig vom zu zeichnenden GerÃâ¬t.
	 * @return Der horizontale Versatz.
	 */
	abstract int  getGX();

	/**
	 * In dieser {@link Map} werden alle zu zeichnenden GerÃâ¬te verzeichnet.
	 */
	final static Map<DeviceUnit, StateDrawer> drawers = new HashMap<DeviceUnit, StateDrawer>();
}
