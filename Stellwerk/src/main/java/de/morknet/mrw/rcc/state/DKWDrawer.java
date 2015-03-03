/*
**
**	$Filename:	DKWDrawer.java $
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

import de.morknet.mrw.base.DKW;
import de.morknet.mrw.base.Gleisteil;
import de.morknet.mrw.base.Gruppe;
import de.morknet.mrw.base.Verzweigung;

/**
 * Diese Klasse zeichnet den Lagezustand einer DKW. Da eine DKW potenziel immer zwei Fahrwege
 * hat, wird nach MÃÂ¶glichkeit nur die Variante gezeichnet, die zum Fahrweg einer FahrstraÃÂe
 * gehÃÂ¶rt.
 * @author sm
 *
 */
final class DKWDrawer extends SwitchStateDrawer
{
	private final DKW dkw;
	private final Gruppe gruppe;

	/**
	 * Dieser Konstruktur initialisiert diese Zeichenklasse mit der
	 * zu zeichnenden DKW.
	 * @param v Die zu zeichnende DKW.
	 */
	DKWDrawer(Verzweigung v)
	{
		dkw    = (DKW)v;
		gruppe = v.getGruppe();
	}

	@Override
	void draw(GC gc)
	{
		Gleisteil backward;
		Gleisteil forward;

		if (dkw.getRoute() != null)
		{
			if (dkw.getB().getRoute() == null)
			{
				backward = dkw.getB();
				switch(dkw.getDir())
				{
				case AC_BD:
					forward  = dkw.getD();
					break;
				case AD_BC:
					forward  = dkw.getC();
					break;
				default:
					forward  = null;
					break;
				}
			}
			else
			{
				backward = dkw.getA();
				switch(dkw.getDir())
				{
				case AC_BD:
					forward  = dkw.getC();
					break;
				case AD_BC:
					forward  = dkw.getD();
					break;
				default:
					forward  = null;
					break;
				}
			}
		}
		else
		{
			backward = dkw.aIsHigh() ^ gruppe.isInvertedDirection() ? dkw.getC() : dkw.getB();

			if (dkw.aIsHigh() ^ gruppe.isInvertedDirection())
			{
				switch(dkw.getDir())
				{
				case AC_BD:
					forward  = dkw.getA();
					break;
				case AD_BC:
					forward  = dkw.getB();
					break;
				default:
					forward  = null;
					break;
				}
			}
			else
			{
				switch(dkw.getDir())
				{
				case AC_BD:
					forward  = dkw.getD();
					break;
				case AD_BC:
					forward  = dkw.getC();
					break;
				default:
					forward  = null;
					break;
				}
			}
		}
		SwitchStateDrawer.drawSwitchDir(gc, dkw, backward, -1);
		SwitchStateDrawer.drawSwitchDir(gc, dkw, forward,   1);
	}
}
