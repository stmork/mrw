/*
**
**	$Filename:	TourInfo.java $
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

package de.morknet.mrw.gui.info;

import java.util.Arrays;
import java.util.List;

import de.morknet.mrw.automatic.TourPoint;
import de.morknet.mrw.automatic.tourmode.TourMode;

/**
 * Diese Klasse beinhaltet Informationen für eine Fahrstrecke.
 * @see TourMode
 * @see TourPoint
 * @author sm
 *
 */
public abstract class TourInfo
{
	private final List<String> nameList;

	/**
	 * Diese Methode giobt Auskunft darüber, ob die Fahrstrecke zyklisch verläuft (Kreisfahrt).
	 * @return Flag, ob Fahrtstrekce zyklisch ist.
	 */
	abstract public boolean isLoop();

	/**
	 * Diese Methode gibt Auskunft über die Fahrtrichtung relativ zur Zählrichtung der Gleise.
	 * @return Flag zur Fahrtrichtung.
	 */
	abstract public boolean getDirection();

	/**
	 * Diese Methode benennt diese Fahrtstrecke mit einem Titel.
	 * @return Der Name dieser Fahrtstrecke.
	 */
	abstract public String  getName();

	/**
	 * Dieser Konstruktor initialisiert die Informationen für die Tourpunkte.
	 * @param names Die Tourpunkte dieser Fahrtstrecke.
	 */
	protected TourInfo(final String [] names)
	{
		nameList = Arrays.asList(names);
	}

	/**
	 * Diese Methode gibt die textuelle Beschreibung der Tourpunkte wider. Die Tourpunkte werden in den {@link TourPoint}-Klassen
	 * in Gleisabschnitte aufgelöst. Diesen folgt dann die Fahrtstrecke.
	 * @return Die Liste der textuellen Beschreibungen der Tourpunkte.
	 */
	public final List<String> getSegmentNames()
	{
		return nameList;
	}

	/**
	 * Fahrstrecken sind routemäßig Fahrten, weil Rangierfahrten nicht über Betriebsgruppen hinaus gelegt werden können.
	 * @return Flag, ob es sich um eine Rangierfahrt handeln soll.
	 */
	public boolean isShunting()
	{
		return false;
	}
}
