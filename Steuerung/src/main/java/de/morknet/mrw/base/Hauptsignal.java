/*
**
**	$Filename:	Hauptsignal.java $
**	$Revision: 931 $
**	$Date: 2010-04-14 10:39:15 +0200 (Mi, 14. Apr 2010) $
**	$Author: smork $
**	$Id: Hauptsignal.java 931 2010-04-14 08:39:15Z smork $
**
**	Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
**
**	This program and the accompanying materials are made available under the
**	terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
**	which accompanies this distribution.
**
**	The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
**
**
*/

package de.morknet.mrw.base;

import de.morknet.mrw.comm.SignalCode;

/**
 * Diese Klasse repräsentiert allgemein ein Hauptsignal.
 * @author smork
 *
 */
abstract public class Hauptsignal extends Signal
{
	private static final long serialVersionUID = 1L;

	/**
	 * Dieser Konstruktur initialisiert ein Hauptsignal.
	 * @param abschnitt Der zum Hauptsignal gehörende Gleisabschnitt.
	 * @param number Die Signalnummer
	 * @param inDirection Die Sichtrichtung relativ zur Zählrichtung des Gleises.
	 */
	public Hauptsignal(Abschnitt abschnitt, String number, boolean inDirection)
	{
		super(abschnitt, number, inDirection);
	}

	@Override
	protected SignalCommand setSignalCommand(SignalCommand sb, boolean shunting)
	{
		sb = correctState(sb, shunting);
		switch(sb)
		{
		case TEST:
			nominalState = SignalCode.SIGNAL_TST;
			break;
		case S0:
			nominalState = SignalCode.SIGNAL_HP0;
			break;
		case S1:
			nominalState = shunting ? SignalCode.SIGNAL_SH1 : SignalCode.SIGNAL_HP1;
			break;
		case S2:
			nominalState = shunting ? SignalCode.SIGNAL_SH1 : SignalCode.SIGNAL_HP2;
			break;
		default:
			nominalState = SignalCode.SIGNAL_OFF;
			break;
		}
		
		// Kombiniertes Vorsignal aussschalten falls keine Fahrtfreigabe.
		if ((nominalState != SignalCode.SIGNAL_HP1) && (nominalState != SignalCode.SIGNAL_HP2))
		{
			for (Signal s : getSegment().getSignals(isDirection(true)))
			{
				if (s instanceof Vorsignal)
				{
					Vorsignal vs = (Vorsignal)s;
					
					vs.setSignal(SignalCommand.OFF, shunting);
				}
			}
		}
		return sb;
	}
}
