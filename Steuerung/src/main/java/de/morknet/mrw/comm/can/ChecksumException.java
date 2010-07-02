/*
**
**	$Filename:	ChecksumException.java $
**	$Revision: 931 $
**	$Date: 2010-04-14 10:39:15 +0200 (Mi, 14. Apr 2010) $
**	$Author: smork $
**	$Id: ChecksumException.java 931 2010-04-14 08:39:15Z smork $
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
 * Diese Exception wird geworfen, wenn ein Prüfsummenfehler bei der Übertragung einer CAN-Meldung festgestellt wurde.
 * @author sm
 *
 */
public class ChecksumException extends MrwException
{
	private static final long serialVersionUID = 1L;

	/**
	 * Dieser Konstruktor bereitet eine Fehlermeldung auf.
	 * @param checksum Die empfangene fehlerhafte Prüfsumme.
	 * @param length Die Zahl der empfangenen Bytes.
	 * @param buffer Die empfangenen Bytes.
	 */
	public ChecksumException(final int checksum, final int length, final int[] buffer)
	{
		super(prepareMessage(checksum, length, buffer));
	}
	
	private final static String prepareMessage(final int checksum, final int length, final int[] buffer)
	{
		final StringWriter sw = new StringWriter();
		final PrintWriter  pw = new PrintWriter(sw);

		pw.printf("Prüfsummenfehler sum=%02x length=%d values:", checksum & 0xff, length);
		for (int i = 0;i < length;i++)
		{
			pw.printf(" %02x", buffer[i] & 0xff);
		}
		pw.close();
		return sw.toString();
	}
}
