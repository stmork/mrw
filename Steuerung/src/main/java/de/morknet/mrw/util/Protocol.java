/*
**
**	$Filename:	Protocol.java $
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

package de.morknet.mrw.util;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Diese Klasse pflegt Einträge für ein Protokoll-Log.
 * @author smork
 *
 */
public class Protocol
{
	private final Log log;
	private final List<String> entries = new LinkedList<String>();
	
	/**
	 * Dieser Konstruktur verteilt einen Default-Namen für dieses Protokoll.
	 */
	public Protocol()
	{
		this("Fehlerprotokoll");
	}

	/**
	 * Dieser Konstruktur verteilt einen vorgegebenen Namen für dieses Protokoll.
	 * @param name Der Protokollname.
	 */
	public Protocol(final String name)
	{
		log = LogFactory.getLog(name);
	}
	
	/**
	 * Diese Methode fügt einen Logeintrag dem Protokoll hinzu. Es wird dasa aktuelle Datum dem Eintrag vorangestellt.
	 * @param entry Der Logeintrag.
	 */
	public void add(final String entry)
	{
		synchronized(entries)
		{
			entries.add(LogUtil.now() + ": " + entry);
		}
	}

	/**
	 * Diese Methode listet das Logprotokoll auf.
	 */
	public void protocol()
	{
		synchronized(entries)
		{
			if (entries.isEmpty())
			{
				log.info("Keine Einträge.");
			}
			for (String e : entries)
			{
				log.info(e);
			}
		}
	}

	/**
	 * Diese Methode löscht das Logprotokoll.
	 */
	public void clear()
	{
		synchronized(entries)
		{
			entries.clear();
		}
	}
}
