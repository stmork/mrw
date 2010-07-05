/*
**
**	$Filename:	VorsignalDrawer.java $
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

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;

import de.morknet.mrw.base.Signal;
import de.morknet.mrw.comm.SignalCode;

/**
 * Diese Klasse zeichnet ein Vorsignal.
 * @author sm
 *
 */
final class VorsignalDrawer extends SignalStateDrawer
{
	private static final int gx = 5 - (SCALE_X >> 1);
	private Color c1 = GREEN;
	private Color c2 = GREEN;
	private Color c3 = YELLOW;
	private Color c4 = YELLOW;

	/**
	 * Dieser Konstruktur initialisiert diese Zeichenklasse mit dem
	 * zu zeichnenden Signal.
	 * @param signal Das zu zeichnende Signal
	 */
	VorsignalDrawer(Signal signal)
	{
		super(signal);
	}

	@Override
	int getGX()
	{
		return gx;
	}

	@Override
	void prepare()
	{
		SignalCode state = signal.getSignalState();
		switch(state)
		{
		case SIGNAL_OFF:
			c1 = c2 = c3 = c4 = BLACK;
			break;

		case SIGNAL_TST:
			c1 = c2 = GREEN;
			c3 = c4 = YELLOW;
			break;

		case SIGNAL_VR0:
			c1 = c2 = BLACK;
			c3 = c4 = YELLOW;
			break;

		case SIGNAL_VR1:
			c1 = c2 = GREEN;
			c3 = c4 = BLACK;
			break;

		case SIGNAL_VR2:
			c1 = GREEN;
			c4 = YELLOW;
			c2 = c3 = BLACK;
			break;
		default:
			throw new IllegalStateException("Unusable state " + state);
		}
	}

	@Override
	void draw(GC gc)
	{
		gc.setBackground(c1);
		gc.setForeground(c1);
		gc.fillOval(gx + 1, 5, 2, 2);

		gc.setBackground(c2);
		gc.setForeground(c2);
		gc.fillOval(gx + 5, 8, 2, 2);

		gc.setBackground(c3);
		gc.setForeground(c3);
		gc.fillOval(gx + 1, 7, 2, 2);

		gc.setBackground(c4);
		gc.setForeground(c4);
		gc.fillOval(gx + 5, 10, 2, 2);
	}
}
