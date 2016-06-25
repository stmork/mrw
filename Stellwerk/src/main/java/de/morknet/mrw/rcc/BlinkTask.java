/*
**
**	$Filename:	BlinkTask.java $
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

package de.morknet.mrw.rcc;

import java.util.TimerTask;

import de.morknet.mrw.batch.Batch;
import de.morknet.mrw.batch.BatchExecuter;

/**
 * Diese Klasse stellt einen {@link TimerTask} bereit, mit dem blinkende Anzeigeelemente im Spurplan gezeichnet
 * werden können. Zu jedem Zustandswechsel wird eine Aktualisierung der GUI veranlasst. Das passiert aber nur, wenn
 * tatsächlich {@link Batch}es zur Verarbeitung vorliegen. Blinkende Anzeigeelemente sind die Weichen- und Signalnummern
 * der Weichen und Signale, die sich im Umlauf befinden.
 * @author sm
 *
 */
public class BlinkTask extends TimerTask
{
	private final Controller controller;
	private boolean toggle = false;

	/**
	 * Dieser Konstruktur initialisiert diesen Blinktask.
	 * @param controller Der steuernde MrwController.
	 */
	public BlinkTask(Controller controller)
	{
		this.controller = controller;
	}

	@Override
	public void run()
	{
		toggle = !toggle;
		if (BatchExecuter.hasBatches())
		{
			controller.updateGroupViews();
		}
	}

	/**
	 * Diese Methode gibt den aktuellen Zustand aus, ob Anzeigeelemente ein- oder ausgeschaltet sein soll.
	 * @return Sichtbarkeit einiger Anzeigeelemente.
	 */
	public boolean getToggle()
	{
		return toggle;
	}
}
