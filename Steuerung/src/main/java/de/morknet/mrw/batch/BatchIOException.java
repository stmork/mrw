/*
**
**	$Filename:	BatchIOException.java $
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

import java.io.IOException;

/**
 * Diese Exception wird geworfen, wenn ein BatchExecuter verbotenerweise wiederverwendet wird.
 * @author smork
 *
 */
public class BatchIOException extends BatchProcessingException
{
	/**
	 * Dieser Konstruktur initialisiert eine BatchProcessingException mit einer Meldung.
	 * @param e Die auslösende {@link IOException}.
	 */
	public BatchIOException(IOException e)
	{
		super(e.getLocalizedMessage());
	}

	private static final long serialVersionUID = 1L;
}
