/*
**
**	$Filename:	SeparatorAction.java $
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

import de.morknet.mrw.automatic.MrwActionControl;
import de.morknet.mrw.rcc.Controller;

/**
 * Diese {@link MrwAction} reprÃâ¬sentiert einen Separator, hat also keine weitere FunktionalitÃâ¬t.
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
