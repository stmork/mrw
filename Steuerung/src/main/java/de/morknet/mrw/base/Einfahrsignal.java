/*
**
**	$Filename:	Einfahrsignal.java $
**	$Revision$
**	$Date$
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

/**
 * Dieser Klasse repr�sentiert ein Einfahrlichtsignal. Es kann folgende Zust�nde anzeigen:
 * <ul>
 * <li>SIGNAL_OFF
 * <li>SIGNAL_TST
 * <li>SIGNAL_HP0
 * <li>SIGNAL_HP1
 * <li>SIGNAL_HP2
 * </ul>
 * @author sm
 *
 */
public final class Einfahrsignal extends Hauptsignal
{
	private static final long serialVersionUID = 1L;

	/**
	 * Dieser Konstruktur initialisiert ein Einfahrsignal.
	 * @param abschnitt Der Gleisabschnitt zu dem das Einfahrsignal geh�rt.
	 * @param name Der Name des Signals.
	 * @param aIsHigh Die Richtung relativ zur Z�hrichtung. 
	 */
	public Einfahrsignal(Abschnitt abschnitt, String name, boolean aIsHigh)
	{
		super(abschnitt, name, aIsHigh);
	}

	@Override
	protected SignalCommand correctState(SignalCommand sb, boolean shunting)
	{
		return shunting ? SignalCommand.S0 : sb;
	}

	@Override
	protected Command getConfigCode()
	{
		return Command.CFGML3;
	}
}
