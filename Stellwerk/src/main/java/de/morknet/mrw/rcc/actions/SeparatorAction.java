/*
**
**	$Filename:	SeparatorAction.java $
**	$Revision: 954 $
**	$Date: 2010-05-01 11:31:46 +0200 (Sa, 01. Mai 2010) $
**	$Author: smork $
**	$Id: SeparatorAction.java 954 2010-05-01 09:31:46Z smork $
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

import de.morknet.mrw.automatic.MrwActionControl;
import de.morknet.mrw.rcc.Controller;

/**
 * Diese {@link MrwAction} repräsentiert einen Separator, hat also keine weitere Funktionalität.
 * @author sm
 *
 */
public class SeparatorAction extends MrwAction
{
	private static class SeparatorActionControl extends MrwActionControl
	{
		private SeparatorActionControl()
		{
			super(Controller.getController(), "Separator");
		}

		@Override
		public boolean onActivate()
		{
			return false;
		}

		@Override
		public boolean onDeactivate()
		{
			return false;
		}
		
	}

	private final static SeparatorActionControl actionControl = new SeparatorActionControl();
	/**
	 * Dieser Konstruktor initialisiert einen Separator.
	 */
	public SeparatorAction()
	{
		super("Separator", actionControl);
	}

	@Override
	public void update()
	{
		// Nichts machen, ist ja nur ein Separator!
	}

}
