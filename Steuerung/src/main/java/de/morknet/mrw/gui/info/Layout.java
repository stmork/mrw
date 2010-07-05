/*
**
**	$Filename:	Layout.java $
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

package de.morknet.mrw.gui.info;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.morknet.mrw.Modell;
import de.morknet.mrw.base.NamedElement;
import de.morknet.mrw.util.LogUtil;

/**
 * Diese Klasse liefert Layout-Informationen für den Spurplan der Eisenbahnanlage für die GUI.
 * @author sm
 *
 */
public class Layout
{
	private final static Log log  = LogFactory.getLog(Layout.class);
	private Properties gruppen    = new Properties();
	private Properties gleisteile = new Properties();
	private Properties signale    = new Properties();
	private Modell     modell;
	
	private static final String GRUPPEN    = "Gruppen";
	private static final String GLEISTEILE = "Gleisteile";
	private static final String SIGNALE    = "Signale";

	/**
	 * Dieser Konstruktur lädt die Layout-Informationen für die Modelleisenbahn. Fehlende Layout-Informationen
	 * zu Bauteilen werden automatisch hinzugefügt, so dass sie beim Aufruf von {@link #save()} mit in die Properties-Datei
	 * gespeichert werden.
	 * @param modell Die Modelleisenbahn.
	 * @throws IOException Falls beim Einlesen was schief geht.
	 */
	public Layout(final Modell modell) throws IOException
	{
		final Collection<NamedElement> c = new ArrayList<NamedElement>();
		
		File dir = new File(modell.getName());
		this.modell = modell;

		if(!dir.mkdir())
		{
			if (!dir.exists())
			{
				throw new IOException("Verzeichnis " + modell.getName() + " nicht vorhanden!");
			}
		}

		c.addAll(modell.getGroups());
		load(gruppen, getFileName(GRUPPEN),    c);
		c.clear();

		c.addAll(modell.getTrackElements());
		load(gleisteile, getFileName(GLEISTEILE), c);
		c.clear();

		c.addAll(modell.getSignals());
		load(signale, getFileName(SIGNALE), c);
		c.clear();
	}

	private void load(Properties props, String name, Collection<NamedElement> elements) throws IOException
	{
		FileInputStream fis = null;
		try
		{
			fis = new FileInputStream(name);
			props.load(fis);
			for (NamedElement element : elements)
			{
				String key  = key(element.getName());
				String info = props.getProperty(key);
				if (info != null)
				{
					element.parseLayoutInfo(info);
				}
				else
				{
					log.warn(LogUtil.printf("Element %s in %s nicht gefunden!\n", key, name));
				}
			}
		}
		catch(FileNotFoundException fnfe)
		{
			// Do nothing!
		}
		finally
		{
			if (fis != null)
			{
				fis.close();
			}
		}
	}

	/**
	 * Diese Methode speichert die Layout-Informationen zurück in eine Properties-Datei.
	 * @throws IOException Falls beim Schreiben was schief geht.
	 */
	public void save() throws IOException
	{
		Collection<NamedElement> c = new ArrayList<NamedElement>();

		c.addAll(modell.getGroups());
		save(gruppen, getFileName(GRUPPEN),    c);
		c.clear();

		c.addAll(modell.getTrackElements());
		save(gleisteile, getFileName(GLEISTEILE), c);
		c.clear();

		c.addAll(modell.getSignals());
		save(signale, getFileName(SIGNALE), c);
		c.clear();
	}
	
	private void save(Properties props, String name, Collection<NamedElement> elements) throws IOException
	{
		boolean changed = false;
		for (NamedElement element : elements)
		{
			String key = key(element.getName());
			String info = props.getProperty(key);

			if (info == null)
			{
				props.setProperty(key, element.getLayoutInfo());
				changed = true;
			}
		}
		if (changed)
		{
			FileOutputStream fos = null;
			
			try
			{
				fos = new FileOutputStream(name);
				props.store(fos, name);
			}
			finally
			{
				if (fos != null)
				{
					fos.close();
				}
			}
		}
	}
	
	private static String key(String name)
	{
		return name.replaceAll(" ", "");
	}

	private String getFileName(String name)
	{
		return modell.getName() + File.separator + name + ".properties";
	}
}
