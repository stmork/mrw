/*
**
**	$Filename:	Formvorsignal.java $
**	$Revision: 931 $
**	$Date: 2010-04-14 10:39:15 +0200 (Mi, 14. Apr 2010) $
**	$Author: smork $
**	$Id: Formvorsignal.java 931 2010-04-14 08:39:15Z smork $
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
 * Diese Klasse repräsentiert ein Formvorsignal.
 * @author sm
 *
 */
public final class Formvorsignal extends Vorsignal implements Formsignal
{
	private static final long serialVersionUID = 1L;

	private final int spulen;

	/**
	 * Dieser Konstruktur initialisiert ein Formvorsignal.
	 * @param abschnitt Der Gleisabschnitt zu dem das Formvorsignal gehört.
	 * @param name Der Name des Signals.
	 * @param inDirection Die Richtung relativ zur Zährichtung. 
	 * @param spulen Die Zahl der Antriebsspulen.
	 */
	public Formvorsignal(Abschnitt abschnitt, String name, boolean inDirection, int spulen)
	{
		super(abschnitt, name, inDirection);
		this.spulen = spulen;
	}

	@Override
	protected SignalCommand correctState(SignalCommand sb, boolean rangieren) {
		return (sb == SignalCommand.S2) && (spulen < 3) ? SignalCommand.S1 : sb;
	}

	@Override
	protected Command getConfigCode()
	{
		return spulen == 2 ? Command.CFGPF2 : Command.CFGPF3;
	}
}
