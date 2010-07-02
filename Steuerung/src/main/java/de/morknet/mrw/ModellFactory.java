/*
**
**	$Filename:	ModellFactory.java $
**	$Revision: 931 $
**	$Date: 2010-04-14 10:39:15 +0200 (Mi, 14. Apr 2010) $
**	$Author: smork $
**	$Id: ModellFactory.java 931 2010-04-14 08:39:15Z smork $
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

package de.morknet.mrw;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.morknet.mrw.util.MrwProperties;

/**
 * Diese Klasse instanziiert die Beschreibung für eine Modelleisenbahn. Der Klassenname der Modelleisenbahn muss
 * in der Datei <it>$HOME/.comm.properties</it> als Schlüssel <em>mrw.railway</em> hinterlegt sein. Die Modelleisenbahn
 * wird als Singleton instanziiert.
 * @author sm
 *
 */
public class ModellFactory
{
	private final static Log           log         = LogFactory.getLog(ModellFactory.class);
	private final static ModellFactory factory     = new ModellFactory();
	private              Modell        modell      = null;

	/**
	 * Der Schlüssel in der Konfigurationsdatei für den verwendeten Anlagennamen. 
	 */
	public  final static String        RAILWAY_KEY = "mrw.railway";

	private ModellFactory()
	{
		String railway = null;

		try
		{
			railway = MrwProperties.getProperty(RAILWAY_KEY);

			if (railway != null)
			{
				Class<?> cls = getClass().getClassLoader().loadClass(railway);
				modell = (Modell) cls.newInstance();
			}
			else
			{
				log.error("Eisenbahnanlage kann nicht geladen werden. Sind Sie sicher,");
				log.error("dass Sie das Property '" + RAILWAY_KEY + "' konfiguriert haben?");
			}
		}
		catch (ClassNotFoundException e)
		{
			log.error(e.getLocalizedMessage(), e);
			throw new RuntimeException(
					"Modelleisenbahn kann nicht geladen werden! Klasse der Anlage: " + railway);
		}
		catch (InstantiationException e)
		{
			log.error(e.getLocalizedMessage(), e);
			throw new RuntimeException(
					"Modelleisenbahn kann nicht geladen werden! Klasse der Anlage: " + railway); 
		}
		catch (IllegalAccessException e)
		{
			log.error(e.getLocalizedMessage(), e);
			throw new RuntimeException(
					"Modelleisenbahn kann nicht geladen werden! Klasse der Anlage: " + railway); 
		}
	}

	/**
	 * Diese Methode gibt eine Instanz einer Modelleisenbahnanlage zurück.
	 * @return Die Modelleisenbahnanlage.
	 */
	public static Modell getInstance()
	{
		return ModellFactory.factory.modell;
	}
}
