/*
**
**	$Filename:	BeerModeInfo.java $
**	$Revision$
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

package de.morknet.mrw.gui.info;

import de.morknet.mrw.automatic.beermode.BeerMode;

/**
 * Diese Klasse beinhaltet Informationen für den Biermodus.
 * @see BeerMode
 * @author sm
 *
 */
abstract public class BeerModeInfo
{
	/**
	 * Diese Methode gibt den Namen des Schattenbahnhofs zurück.
	 * @return Der Name des Schattenbahnhofs.
	 */
	abstract public String getPoolStationName();

	/**
	 * Diese Methode gibt den Namen des Durchgangsbahnhofs zurück.
	 * @return Der Name des Durchgangsbahnhofs.
	 */
	abstract public String getViaStationName();
}
