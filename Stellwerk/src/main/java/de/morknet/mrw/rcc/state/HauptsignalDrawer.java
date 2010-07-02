/*
**
**	$Filename:	HauptsignalDrawer.java $
**	$Revision: 954 $
**	$Date: 2010-05-01 11:31:46 +0200 (Sa, 01. Mai 2010) $
**	$Author: smork $
**	$Id: HauptsignalDrawer.java 954 2010-05-01 09:31:46Z smork $
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

import de.morknet.mrw.base.Signal;

/**
 * Diese Klasse zeichnet ein Hauptsignal.
 * @author sm
 *
 */
abstract class HauptsignalDrawer extends SignalStateDrawer
{
	/**
	 * Dieser Konstruktur initialisiert diese Zeichenklasse mit dem
	 * zu zeichnenden Signal.
	 * @param signal Das zu zeichnende Signal
	 */
	HauptsignalDrawer(final Signal signal)
	{
		super(signal);
	}

	@Override
	int getGX()
	{
		return 0;
	}
}
