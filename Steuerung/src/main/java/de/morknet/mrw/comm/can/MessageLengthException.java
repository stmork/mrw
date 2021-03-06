/*
**
**	$Filename:	ChecksumException.java $
**	$Revision$
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

package de.morknet.mrw.comm.can;

import java.io.PrintWriter;
import java.io.StringWriter;

import de.morknet.mrw.base.MrwException;

/**
 * Diese Exception wird geworfen, wenn die Länge der Daten nicht mit dem Längenfeld der Message übereinstimmt.
 * @author sm
 *
 */
public class MessageLengthException extends MrwException
{
	private static final long serialVersionUID = 1L;

	/**
	 * Dieser Konstruktor bereitet eine Fehlermeldung auf.
	 * @param buffer Die empfangenen Bytes.
	 */
	public MessageLengthException(final int[] buffer)
	{
		super(prepareMessage(buffer));
	}
	
	private final static String prepareMessage(final int[] buffer)
	{
		final StringWriter sw = new StringWriter();
		final PrintWriter  pw = new PrintWriter(sw);

		pw.printf("Paket enthält falsche Anzahl an Bytes (%d %d):", buffer[0], buffer.length - 6);
		for (int i = 0;i < buffer.length;i++)
		{
			pw.printf(" %02x", buffer[i] & 0xff);
		}
		pw.close();
		return sw.toString();
	}
}
