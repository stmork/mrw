/*
**
**	$Filename:	StateDrawer.java $
**	$Revision: 954 $
**	$Date: 2010-05-01 11:31:46 +0200 (Sa, 01. Mai 2010) $
**	$Author: smork $
**	$Id: StateDrawer.java 954 2010-05-01 09:31:46Z smork $
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
 * Diese abstrakte Klasse definiert das Verhalten zum Zeichnen von Geräten (Signale, Weichen)
 * in einer GUI.
 * @see DeviceUnit
 * @author sm
 *
 */
abstract class StateDrawer
{
	/**
	 * Diese Methode bereitet ein Gerät zum Zeichnen vor.
	 */
	abstract void prepare();
	
	/**
	 * Diese Methode veranlasst das Zeichnen in den angegebenen Grafikkontext.
	 * @param gc Der Grafikkontext.
	 */
	abstract void draw(GC gc);
	
	/**
	 * Diese Methode berechnet einen horizontalen Versatz abhängig vom zu zeichnenden Gerät.
	 * @return Der horizontale Versatz.
	 */
	abstract int  getGX();

	/**
	 * In dieser {@link Map} werden alle zu zeichnenden Geräte verzeichnet.
	 */
	final static Map<DeviceUnit, StateDrawer> drawers = new HashMap<DeviceUnit, StateDrawer>();
}
