/*
**
**	$Filename:	BatchNotCompletedException.java $
**	$Revision: 931 $
**	$Date: 2010-04-14 10:39:15 +0200 (Mi, 14. Apr 2010) $
**	$Author: smork $
**	$Id: BatchNotCompletedException.java 931 2010-04-14 08:39:15Z smork $
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

import de.morknet.mrw.util.LogUtil;

/**
 * Diese Exception wird geworfen, wenn ein Batch nach dem Timeout noch nicht verarbeitete
 * Kommandos hat.
 * @author smork
 *
 */
public class BatchNotCompletedException extends BatchProcessingException
{
	private static final long serialVersionUID = 1L;

	/**
	 * Dieser Konstruktur bereitet eine Fehlermeldung auf.
	 * @param batch Der fehlerhafte {@link Batch}.
	 */
	public BatchNotCompletedException(final Batch batch)
	{
		super(LogUtil.printf("Unerledigte Kommandos: %d   %s", batch.getElementCount(), batch.getIdString()));
	}
}
