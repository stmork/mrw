/*
**
**	$Filename:	BeerModeAction.java $
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

package de.morknet.mrw.rcc.actions;

import de.morknet.mrw.automatic.beermode.BeerMode;
import de.morknet.mrw.rcc.Controller;

/**
 * Diese Klasse stellt die Steuerung für den Biermodus bereit. Aus einem definiertem Bahnhof wird
 * ein Zug aus einem besetzten Gleis ausgewählt, durch ein freies Gleis eines weiteren Bahnhofs
 * geführt und wieder auf ein freies Gleis des ersten Bahnhofs geführt. Ist der Zug angekommen, wird
 * der nächste Zug ausgewählt. Die Auswahl des Zuges und des freien Gleises geschieht zufällig. Die
 * Fahrtrichtung wird dabei berücksichtigt.
 * @author smork
 *
 */
public class BeerModeAction extends MrwAction
{
	private BeerModeAction otherDirectionAction = null;

	private BeerModeAction(BeerMode beermode)
	{
		super(beermode.toString(), beermode);
	}

	/**
	 * Diese Methode initialisiert zwei Biermodi jeweils für die beiden Fahrtrichtungen.
	 */
	public static void initBeerModeActions()
	{
		BeerModeAction right = new BeerModeAction(BeerMode.getBeerModeRight(Controller.getController())); 
		BeerModeAction left  = new BeerModeAction(BeerMode.getBeerModeLeft(Controller.getController()));
		right.otherDirectionAction = left;
		left.otherDirectionAction  = right;

		ActionInitializer.addAction(right);
		ActionInitializer.addAction(left);
	}

	@Override
	public void update()
	{
		setEnabled(!otherDirectionAction.isActive());
		setChecked(isActive());
	}
}
