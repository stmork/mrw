/*
**
**	$Filename:	AusfahrsignalDrawer.java $
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

package de.morknet.mrw.rcc.state;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;

import de.morknet.mrw.base.Signal;
import de.morknet.mrw.comm.SignalCode;

/**
 * Diese Klasse zeichnet ein Ausfahrsignal.
 * @author sm
 *
 */
final class AusfahrsignalDrawer extends HauptsignalDrawer
{
	private Color c1 = RED;
	private Color c2 = RED;
	private Color c3 = WHITE;
	private Color c4 = GREEN;
	private Color c5 = YELLOW;

	/**
	 * Dieser Konstruktur initialisiert diese Zeichenklasse mit dem
	 * zu zeichnenden Signal.
	 * @param signal Das zu zeichnende Signal
	 */
	AusfahrsignalDrawer(Signal signal)
	{
		super(signal);
	}

	@Override
	void prepare()
	{
		SignalCode state = signal.getSignalState();
		switch(state)
		{
		case SIGNAL_OFF:
			c1 = c2 = c3 = c4 = c5 = BLACK;
			break;
		case SIGNAL_TST:
			c1 = RED;
			c2 = RED;
			c3 = WHITE;
			c4 = GREEN;
			c5 = YELLOW;
			break;
		case SIGNAL_HP0:
			c1 = RED;
			c2 = RED;
			c3 = BLACK;
			c4 = BLACK;
			c5 = BLACK;
			break;
		case SIGNAL_SH1:
			c1 = RED;
			c2 = BLACK;
			c3 = WHITE;
			c4 = BLACK;
			c5 = BLACK;
			break;
		case SIGNAL_HP1:
			c1 = BLACK;
			c2 = BLACK;
			c3 = BLACK;
			c4 = GREEN;
			c5 = BLACK;
			break;
		case SIGNAL_HP2:
			c1 = BLACK;
			c2 = BLACK;
			c3 = BLACK;
			c4 = GREEN;
			c5 = YELLOW;
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
		gc.fillOval(getGX() + 10, 5, 2, 2);

		gc.setBackground(c2);
		gc.setForeground(c2);
		gc.fillOval(getGX() + 10,10, 2, 2);

		gc.setBackground(c3);
		gc.setForeground(c3);
		gc.fillOval(getGX() + 10, 8, 2, 2);
		gc.fillOval(getGX() +  7, 5, 2, 2);

		gc.setBackground(c4);
		gc.setForeground(c4);
		gc.fillOval(getGX() + 13, 5, 2, 2);

		gc.setBackground(c5);
		gc.setForeground(c5);
		gc.fillOval(getGX() +  1, 5, 2, 2);
	}
}
