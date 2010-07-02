/*
**
**	$Filename:	Formgleissperrsignal.java $
**	$Revision: 931 $
**	$Date: 2010-04-14 10:39:15 +0200 (Mi, 14. Apr 2010) $
**	$Author: smork $
**	$Id: Formgleissperrsignal.java 931 2010-04-14 08:39:15Z smork $
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
 * Diese Klasse repräsentiert ein Formgleissperrsignal.
 * @author sm
 *
 */
public final class Formgleissperrsignal extends Gleissperrsignal implements Formsignal
{
	private static final long serialVersionUID = 1L;

	/**
	 * Dieser Konstruktur initialisiert ein Formgleissperrsignal.
	 * @param segment Der dazugehörende Gleisabschnitt.
	 * @param number Die Signalnummer.
	 * @param direction Die Zählrichtung.
	 */
	public Formgleissperrsignal(Abschnitt segment, String number, boolean direction)
	{
		super(segment, number, direction);
	}

	@Override
	protected Command getConfigCode()
	{
		return Command.CFGMF2;
	}
}
