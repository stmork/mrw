/*
**
**	$Filename:	MrwProperties.java $
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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.morknet.mrw.util.LogUtil;

/**
 * Diese Klasse verwaltet die Einstellungen zur Steuerungs- bzw. Stellwerks-Software. Die Einstellungen sind in der
 * Datei $HOME/.comm.properties verzeichnet. Das ist unter UN*X das Home-Verzeichnis des Benutzers und unter Windows
 * das Profilverzeichnis typischerweise unter C:\Dokumente und Einstellungen.
 * @author sm
 *
 */
public class MrwProperties
{
	private final static Log           log             = LogFactory.getLog(MrwProperties.class);
	private final static MrwProperties m               = new MrwProperties();
	private final static String        hostname        = computeSimpleHostName();
	private final        Properties    properties      = new Properties();
	private final static String        PROPERTIES_NAME = ".comm.properties"; 

	private MrwProperties()
	{
		final String          home = System.getProperty("user.home");
		final File            name = new File(home, PROPERTIES_NAME);
		      FileInputStream fis  = null;

		try
		{
			log.debug(home);
			log.debug(name.getAbsolutePath());
			fis = new FileInputStream(name.getAbsolutePath());

			properties.load(fis);
		}
		catch (IOException e)
		{
			log.error(e.getLocalizedMessage(), e);
		}
		finally
		{
			if (fis != null)
			{
				try
				{
					fis.close();
				}
				catch (IOException e)
				{
					log.error(e.getLocalizedMessage(), e);
				}
			}
		}
	}

	/**
	 * Diese Methode gibt aus den Eisenbahneinstellungen einen Wert zurück. Wird kein Wert ermittelt, wird danach
	 * an den Schlüssel der einfache Hostname angehängt und ein weiterer Versuch zur Ermittlung des Wertes durchgeführt. 
	 * @param key Der Schlüssel des Wertes.
	 * @return Der ermittelte Wert.
	 */
	public final static String getProperty(final String key)
	{
		return getProperty(key, null);
	}

	/**
	 * Diese Methode gibt aus den Eisenbahneinstellungen einen Wert zurück. Wird kein Wert ermittelt, wird danach
	 * an den Schlüssel der einfache Hostname angehängt und ein weiterer Versuch zur Ermittlung des Wertes durchgeführt. 
	 * @param key Der Schlüssel des Wertes.
	 * @param defaultValue Der Default-Wert des Schlüssels.
	 * @return Der ermittelte Wert.
	 */
	public final static String getProperty(final String key, final String defaultValue)
	{
		String value = m.properties.getProperty(key, defaultValue);
		if (hostname != null)
		{
			value = m.properties.getProperty(key + "." + hostname, value);
		}

		log.debug(LogUtil.printf("%s=%s", key, value));
		return value;
	}

	private final static String computeSimpleHostName()
	{
		try
		{
			InetAddress addr = InetAddress.getLocalHost();
			String hostname = addr.getHostName();
	
			StringTokenizer st = new StringTokenizer(hostname, ".");
	
			return st.countTokens() > 1 ? st.nextToken() : hostname;
		}
		catch (UnknownHostException uhe)
		{
			log.error(uhe.getLocalizedMessage(), uhe);
			return null;
		}
	}
	
	/**
	 * Diese Methode gibt den einfachen Hostnamen dieses Rechners wieder. Wenn der Rückgabewert null
	 * ist, konnte der einfache Hostname nicht ermittelt werden.
	 * @return Der einfache Hostname.
	 */
	public final static String getSimpleHostName()
	{
		return hostname;
	}
}
