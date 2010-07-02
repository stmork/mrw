/*
**
**	$Filename:	InvalidBatchExecuterException.java $
**	$Revision: 931 $
**	$Date: 2010-04-14 10:39:15 +0200 (Mi, 14. Apr 2010) $
**	$Author: smork $
**	$Id: InvalidBatchExecuterException.java 931 2010-04-14 08:39:15Z smork $
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
 * Diese Exception wird geworfen, wenn ein BatchExecuter verbotenerweise wiederverwendet wird.
 * @author smork
 *
 */
public class InvalidBatchExecuterException extends BatchProcessingException
{
	/**
	 * Dieser Konstruktur bereitet eine Fehlermeldung auf.
	 * @param batchExecuter Der fehlerhafter {@link BatchExecuter}.
	 */
	public InvalidBatchExecuterException(final BatchExecuter batchExecuter)
	{
		super(LogUtil.printf("BatchExecuter mit ID %d ist nicht g�ltig!", batchExecuter.getId()));
	}

	private static final long serialVersionUID = 1L;
}
