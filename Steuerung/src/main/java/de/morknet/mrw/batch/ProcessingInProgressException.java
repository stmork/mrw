/*
**
**	$Filename:	ProcessingInProgressException.java $
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

package de.morknet.mrw.batch;

import de.morknet.mrw.base.Abschnitt;

/**
 * Diese Exception wird geworfen, wenn für einen Gleisabschnitt bereits ein {@link Batch} läuft.
 * @author sm
 *
 */
public class ProcessingInProgressException extends BatchProcessingException
{
	private static final long serialVersionUID = 1L;

	/**
	 * Dieser Konstruktur bereitet eine Fehlermeldung auf.
	 * @param abschnitt Der Gleisabschnitt, der schon bearbeitet wird.
	 */
	public ProcessingInProgressException(final Abschnitt abschnitt)
	{
		super("Es ist schon ein Schaltvorgang in der Verarbeitung! Abschnitt: " + abschnitt.getNumber());
	}
}
