/*
**
**	$Filename:	Formhauptsignal.java $
**	$Revision: 931 $
**	$Date: 2010-04-14 10:39:15 +0200 (Mi, 14. Apr 2010) $
**	$Author: smork $
**	$Id: Formhauptsignal.java 931 2010-04-14 08:39:15Z smork $
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
 * Diese Klasse repräsentiert ein Formhauptsignal.
 * @author sm
 *
 */
public final class Formhauptsignal extends Hauptsignal implements Formsignal
{
	private static final long serialVersionUID = 1L;

	private final int spulen;

	/**
	 * Dieser Konstruktur initialisiert ein Formhauptsignal.
	 * @param abschnitt Der dazugehörende Gleisabschnitt.
	 * @param number Die Signalnummer.
	 * @param inDirection Die Zählrichtung.
	 * @param spulen Die Zahl der Schaltspulen.
	 */
	public Formhauptsignal(Abschnitt abschnitt, String number, boolean inDirection, int spulen)
	{
		super(abschnitt, number, inDirection);
		this.spulen = spulen;
	}

	@Override
	protected SignalCommand correctState(SignalCommand sb, boolean rangieren)
	{
		if (rangieren)
		{
			sb = SignalCommand.S0;
		}
		return (sb == SignalCommand.S2) && (spulen < 3) ? SignalCommand.S1 : sb;
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
		return sb;
	}

	@Override
	protected Command getConfigCode()
	{
		return spulen == 2 ? Command.CFGMF2 : Command.CFGMF3;
	}
}
