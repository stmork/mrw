/*
**
**	$Filename:	ProcessingInProgressException.java $
**	$Revision: 931 $
**	$Date: 2010-04-14 10:39:15 +0200 (Mi, 14. Apr 2010) $
**	$Author: smork $
**	$Id: ProcessingInProgressException.java 931 2010-04-14 08:39:15Z smork $
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
 * Diese Exception wird geworfen, wenn f�r einen Gleisabschnitt bereits ein {@link Batch} l�uft.
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
