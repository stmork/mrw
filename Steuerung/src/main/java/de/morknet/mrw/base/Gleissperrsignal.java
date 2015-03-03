/*
**
**	$Filename:	Gleissperrsignal.java $
**	$Revision$
**	$Author$
**	$Id$
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
 * Diese Klasse repräsentiert ein Gleissperrlichtsignal. Es kann folgende Zustände anzeigen:
 * <ul>
 * <li>SIGNAL_OFF
 * <li>SIGNAL_TST
 * <li>SIGNAL_SH0
 * </ul>
 * @author smork
 *
 */
public class Gleissperrsignal extends Signal
{
	private static final long serialVersionUID = 1L;

	/**
	 * Dieser Konstruktor initialisiert ein Gleissperrsignal. 
	 * @param segment Der dazugehörende Gleisabschnitt.
	 * @param number Die Signalnummer.
	 * @param aIsHigh Die Zählrichtung.
	 */
	public Gleissperrsignal(final Abschnitt segment, final String number, final boolean aIsHigh)
	{
		super(segment, number, aIsHigh);
	}

	@Override
	protected SignalCommand setSignalCommand(final SignalCommand sc, final boolean shunting)
	{
		if (sc == SignalCommand.S0)
		{
			nominalState = SignalCode.SIGNAL_SH0;
		}
		else
		{
			nominalState = SignalCode.SIGNAL_SH1;
		}
		return sc;
	}

	@Override
	protected Command getConfigCode()
	{
		return Command.CFGSL2;
	}
}
