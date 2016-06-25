/*
**
**	$Filename:	ConfigureRailway.java $
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

package de.morknet.mrw;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.morknet.mrw.EmptyController;

/**
 * Diese Klasse ist ein ausführbares Programm, das die Konfiguration der Eisenbahnanlage
 * in die Mikrocontroller programmiert.
 * @author sm
 *
 */
public class ConfigureRailway extends EmptyController
{
	private final static Log              log        = LogFactory.getLog(ConfigureRailway.class);
	private final static ConfigureRailway controller = new ConfigureRailway();

	/**
	 * Der Programmstartpunkt.
	 * @param args Die nicht verwendeten Programmargumente.
	 */
	public static void main(String[] args)
	{
		try
		{
			controller.prepareSimple();
			controller.configMicroController();
			controller.close();
		}
		catch(Exception e)
		{
			log.error(e.getLocalizedMessage(), e);
		}
	}
}
