/*
**
**	$Filename:	Ausfahrsignal.java $
**	$Revision: 931 $
**	$Date: 2010-04-14 10:39:15 +0200 (Mi, 14. Apr 2010) $
**	$Author: smork $
**	$Id: Ausfahrsignal.java 931 2010-04-14 08:39:15Z smork $
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
 * Diese Klasse repräsentiert ein Ausfahrlichtsignal. Es kann folgende Zustände anzeigen:
 * <ul>
 * <li>SIGNAL_OFF
 * <li>SIGNAL_TST
 * <li>SIGNAL_HP0
 * <li>SIGNAL_SH1
 * <li>SIGNAL_HP1
 * <li>SIGNAL_HP2
 * </ul>
 * @author smork
 *
 */
public final class Ausfahrsignal extends Hauptsignal
{
	private static final long serialVersionUID = 1L;

	/**
	 * Dieser Konstruktur initialisiert ein Ausfahrsignal.
	 * @param abschnitt Der Gleisabschnitt zu dem das Ausfahrsignal gehört.
	 * @param name Der Name des Signals.
	 * @param aIsHigh Die Richtung relativ zur Zährichtung. 
	 */
	public Ausfahrsignal(Abschnitt abschnitt, String name, boolean aIsHigh) 
	{
		super(abschnitt, name, aIsHigh);
	}

	@Override
	protected Command getConfigCode()
	{
		return Command.CFGML4;
	}
}
