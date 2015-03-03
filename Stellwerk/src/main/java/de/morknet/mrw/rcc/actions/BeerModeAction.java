/*
**
**	$Filename:	BeerModeAction.java $
**	$Revision$
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
 * Diese Klasse stellt die Steuerung fÃÅr den Biermodus bereit. Aus einem definiertem Bahnhof wird
 * ein Zug aus einem besetzten Gleis ausgewÃâ¬hlt, durch ein freies Gleis eines weiteren Bahnhofs
 * gefÃÅhrt und wieder auf ein freies Gleis des ersten Bahnhofs gefÃÅhrt. Ist der Zug angekommen, wird
 * der nÃâ¬chste Zug ausgewÃâ¬hlt. Die Auswahl des Zuges und des freien Gleises geschieht zufÃâ¬llig. Die
 * Fahrtrichtung wird dabei berÃÅcksichtigt.
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
	 * Diese Methode initialisiert zwei Biermodi jeweils fÃÅr die beiden Fahrtrichtungen.
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
