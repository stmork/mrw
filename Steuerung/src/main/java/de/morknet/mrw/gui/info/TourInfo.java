/*
**
**	$Filename:	TourInfo.java $
**	$Revision: 931 $
**	$Date: 2010-04-14 10:39:15 +0200 (Mi, 14. Apr 2010) $
**	$Author: smork $
**	$Id: TourInfo.java 931 2010-04-14 08:39:15Z smork $
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
 * Diese Klasse beinhaltet Informationen f�r eine Fahrstrecke.
 * @see TourMode
 * @see TourPoint
 * @author sm
 *
 */
public abstract class TourInfo
{
	private final List<String> nameList;

	/**
	 * Diese Methode giobt Auskunft dar�ber, ob die Fahrstrecke zyklisch verl�uft (Kreisfahrt).
	 * @return Flag, ob Fahrtstrekce zyklisch ist.
	 */
	abstract public boolean isLoop();

	/**
	 * Diese Methode gibt Auskunft �ber die Fahrtrichtung relativ zur Z�hlrichtung der Gleise.
	 * @return Flag zur Fahrtrichtung.
	 */
	abstract public boolean getDirection();

	/**
	 * Diese Methode benennt diese Fahrtstrecke mit einem Titel.
	 * @return Der Name dieser Fahrtstrecke.
	 */
	abstract public String  getName();

	/**
	 * Dieser Konstruktor initialisiert die Informationen f�r die Tourpunkte.
	 * @param names Die Tourpunkte dieser Fahrtstrecke.
	 */
	protected TourInfo(final String [] names)
	{
		nameList = Arrays.asList(names);
	}

	/**
	 * Diese Methode gibt die textuelle Beschreibung der Tourpunkte wider. Die Tourpunkte werden in den {@link TourPoint}-Klassen
	 * in Gleisabschnitte aufgel�st. Diesen folgt dann die Fahrtstrecke.
	 * @return Die Liste der textuellen Beschreibungen der Tourpunkte.
	 */
	public final List<String> getSegmentNames()
	{
		return nameList;
	}

	/**
	 * Fahrstrecken sind routem��ig Fahrten, weil Rangierfahrten nicht �ber Betriebsgruppen hinaus gelegt werden k�nnen.
	 * @return Flag, ob es sich um eine Rangierfahrt handeln soll.
	 */
	public boolean isShunting()
	{
		return false;
	}
}
