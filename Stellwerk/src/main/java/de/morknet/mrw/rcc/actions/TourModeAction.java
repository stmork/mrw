/*
**
**	$Filename:	TourMode.java $
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

import java.util.List;

import de.morknet.mrw.automatic.tourmode.TourMode;
import de.morknet.mrw.base.Abschnitt;
import de.morknet.mrw.gui.info.TourInfo;
import de.morknet.mrw.rcc.Controller;

/**
 * Diese {@link MrwAction} löst eine Fahrstrecke aus.
 * @author sm
 *
 */
public class TourModeAction extends MrwAction
{
	/**
	 * Dieser Konstruktur initialisiert die Fahrstrecke.
	 * @param info Die Informationen über die Fahrstrecke.
	 */
	public TourModeAction(final TourInfo info)
	{
		super(info.getName(), TourMode.getTourMode(Controller.getController(), info));
	}

	@Override
	public void update()
	{
		Controller ctrl = Controller.getController();
		List<Abschnitt> selection = ctrl.getSegmentSelection();
		
		setEnabled(((!selection.isEmpty()) && selection.get(0).isOccupied()) || isActive());
		setChecked(isActive());
	}

	/**
	 * Diese Methode gibt den Namen der Fahrstrecke zurück.
	 * @return Der Name der Fahrstrecke.
	 */
	public String toString()
	{
		return actionControl.toString();
	}
}
