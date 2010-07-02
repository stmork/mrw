/*
**
**	$Filename:	Blocksignal.java $
**	$Revision: 931 $
**	$Date: 2010-04-14 10:39:15 +0200 (Mi, 14. Apr 2010) $
**	$Author: smork $
**	$Id: Blocksignal.java 931 2010-04-14 08:39:15Z smork $
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

/**
 * Diese Klasse repräsentiert ein Blocksignal. Es kann folgende Zustände anzeigen:
 * <ul>
 * <li>SIGNAL_OFF
 * <li>SIGNAL_TST
 * <li>SIGNAL_HP0
 * <li>SIGNAL_HP1
 * </ul>
 * @author smork
 *
 */
public final class Blocksignal extends Hauptsignal
{
	private static final long serialVersionUID = 1L;

	/**
	 * Dieser Konstruktur initialisiert ein Blocksignal.
	 * @param abschnitt Der Gleisabschnitt zu dem das Blocksignal gehört.
	 * @param name Der Name des Signals.
	 * @param aIsHigh Die Richtung relativ zur Zährichtung. 
	 */
	public Blocksignal(Abschnitt abschnitt, String name, boolean aIsHigh)
	{
		super(abschnitt, name, aIsHigh);
	}

	@Override
	protected SignalCommand correctState(SignalCommand sb, boolean shunting)
	{
		return shunting ?
				SignalCommand.S0 :
				(sb == SignalCommand.S2 ? SignalCommand.S1 : sb);
	}

	@Override
	protected Command getConfigCode()
	{
		return Command.CFGML2;
	}
}
