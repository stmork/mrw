/*
**
**	$Filename:	SwitchDrawer.java $
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

package de.morknet.mrw.rcc.state;

import org.eclipse.swt.graphics.GC;

import de.morknet.mrw.base.Gleisteil;
import de.morknet.mrw.base.Verzweigung;
import de.morknet.mrw.base.Weiche;

/**
 * Diese Klasse zeichnet den Lagezustand einer Weiche.
 * @author sm
 *
 */
final class SwitchDrawer extends SwitchStateDrawer
{
	private final Weiche branch;

	/**
	 * Dieser Konstruktur initialisiert diese Zeichenklasse mit der
	 * zu zeichnenden Weiche.
	 * @param v Die zu zeichnende Weiche.
	 */
	SwitchDrawer(Verzweigung v)
	{
		branch = (Weiche)v;
	}

	@Override
	void draw(GC gc)
	{
		Gleisteil dir;

		switch (branch.getDir())
		{
		case AB:
			dir = branch.getC();
			break;

		case AC:
			dir = branch.getB();
			break;
			
		default:
			dir = null;
		}
		
		SwitchStateDrawer.drawSwitchDir(gc, branch, dir, 1);
	}
}
