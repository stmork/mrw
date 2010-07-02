/*
**
**	$Filename:	LogUtil.java $
**	$Revision: 931 $
**	$Date: 2010-04-14 10:39:15 +0200 (Mi, 14. Apr 2010) $
**	$Author: smork $
**	$Id: LogUtil.java 931 2010-04-14 08:39:15Z smork $
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

package de.morknet.mrw.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * In dieser Klasse sind einige Hilfsmethoden f�r das Logging untergebracht. 
 * @author smork
 *
 */
public class LogUtil
{
	private final static SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy  HH:mm:ss");
	private final static String emptyTxt = "                                ";
	private final static int    emptyLen = emptyTxt.length();
	
	/**
	 * Diese Methode f�hrt ein printf in einen String durch. 
	 * @param fmt Der Format-String.
	 * @param args Die Argumente, die im Format-String verarbeitet werden.
	 * @return Der formatierte Text.
	 */
	public final static String printf(final String fmt, final Object ... args)
	{
		final StringWriter sw = new StringWriter();
		final PrintWriter  pw = new PrintWriter(sw);

		pw.printf(fmt, args);
		final String result = sw.toString();
	    
		pw.close();
	    return result;
	}
	
	/**
	 * Diese Methode gibt den aktuellen Zeitpunkt als formatierten Text zur�ck.
	 * @return Der aktuelle Zeitpunkt als Klartext.
	 */
	public final static String now()
	{
		return format.format(new Date());
	}

	/**
	 * Diese Methode gibt den angegebenen Zeitpunkt als formatierten Text zur�ck.
	 * @param date Der angegebene Zeitpunkt.
	 * @return Der angegebene Zeitpunkt als Klartext.
	 */
	public final static String format(Date date)
	{
		return format.format(date);
	}

	/**
	 * Diese Methode f�llt einen String mit einer Leertaste auf.
	 * @param name Der aufzuf�llende String.
	 * @param min Die Mindestl�nge des Strings.
	 * @return Der aufgef�llte String.
	 */
	public final static String pad(final String name, final int min)
	{
		return pad(name, min, ' ');
	}

	/**
	 * Diese Methode f�llt einen String mit einem beliebigen Zeichen auf.
	 * @param name Der aufzuf�llende String.
	 * @param min Die Mindestl�nge des Strings.
	 * @param padding Das aufzuf�llende Zeichen.
	 * @return Der aufgef�llte String.
	 */
	public final static String pad(final String name, final int min, final char padding)
	{
		StringBuilder buffer = new StringBuilder(name);
		
		while (buffer.length() < min)
		{
			buffer.append(padding);
		}
		return buffer.toString();
	}

	/**
	 * Diese Methode f�llt einen Text linksseitig mit Leerzeichen auf.
	 * @param text Der Text, der rechts steht.
	 * @param min Die Minimall�nge des aufgef�llten Textes.
	 * @return Der links aufgef�llte Text.
	 */
	public final static String padLeft(final String text, final int min)
	{
		return padLeft(text, min, ' ');
	}

	/**
	 * Diese Methode f�llt linksseitig mit einem Zeichen Zeichen auf.
	 * @param text Der Text, der rechts steht.
	 * @param padding Das Zeichen, das linksseitig aufgef�llt wird.
	 * @param min Die Minimall�nge des aufgef�llten Textes.
	 * @return Der links aufgef�llte Text.
	 */
	public final static String padLeft(final String text, final int min, final char padding)
	{
		if (text.length() < min)
		{
			StringBuilder buffer = new StringBuilder();
			
			for (int i = text.length();i < min; i++)
			{
				buffer.append(padding);
			}
			buffer.append(text);
			return buffer.toString();
		}
		return text;
	}

	/**
	 * Diese Methode gibt einen Text mit einer definierten Anzahl an Leerzeichen zur�ck.
	 * @param len Die Zahl der Leerzeichen.
	 * @return Der Leerzeichentext.
	 */
	public final static String empty(final int len)
	{
		if (len <= emptyLen)
		{
			return emptyTxt.substring(0, len);
		}
		else
		{
			StringBuilder buffer = new StringBuilder(emptyTxt);
			
			for (int i = emptyLen;i < len;i++)
			{
				buffer.append(' ');
			}
			return buffer.toString();
		}
	}
}
