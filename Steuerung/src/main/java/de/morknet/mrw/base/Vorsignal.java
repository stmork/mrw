/*
**
**	$Filename:	Vorsignal.java $
**	$Revision: 931 $
**	$Date: 2010-04-14 10:39:15 +0200 (Mi, 14. Apr 2010) $
**	$Author: smork $
**	$Id: Vorsignal.java 931 2010-04-14 08:39:15Z smork $
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

import de.morknet.mrw.comm.Command;
import de.morknet.mrw.comm.SignalCode;

/**
 * Diese Klasse repräsentiert ein Vorsignal.
 * @author smork
 *
 */
public class Vorsignal extends Signal
{
	private static final long serialVersionUID = 1L;

	/**
	 * Dieser Konstruktur initialisiert ein Vorsignal.
	 * @param abschnitt Der Gleisabschnitt zu dem das Vorsignal gehört.
	 * @param name Der Name des Signals.
	 * @param inDirection Die Richtung relativ zur Zährichtung. 
	 */
	public Vorsignal(Abschnitt abschnitt, String name, boolean inDirection)
	{
		super(abschnitt, name, inDirection);
	}

	@Override
	protected SignalCommand setSignalCommand(SignalCommand sb, boolean rangieren) {
		sb = correctState(sb, rangieren);
		switch (sb)
		{
		case TEST:
			nominalState = SignalCode.SIGNAL_TST;
			break;
		case S0:
			nominalState = SignalCode.SIGNAL_VR0;
			break;
		case S1:
			nominalState = SignalCode.SIGNAL_VR1;
			break;
		case S2:
			nominalState = SignalCode.SIGNAL_VR2;
			break;
		default:
			nominalState = SignalCode.SIGNAL_OFF;
			break;
		}
		return sb;
	}

	@Override
	protected Command getConfigCode()
	{
		return Command.CFGPL3;
	}
}
