/*
**
**	$Filename:	TestController.java $
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

package de.morknet.mrw.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.morknet.mrw.EmptyController;
import de.morknet.mrw.comm.dummy.DummyConnection;
import de.morknet.mrw.util.LogUtil;

/**
 * Dieser Test Controller dient der Grundlage zum automatische Testen mit JUnit.
 * @author sm
 *
 */
public class TestController extends EmptyController
{
	private final static Log            log        = LogFactory.getLog(TestController.class);
	private final static TestController controller = new TestController();

	/**
	 * Der Konstruktor. Der Controller ist ein Singleton. 
	 */
	private TestController()
	{
		log.info("=================================================================================");
		log.info(LogUtil.now());
		log.info("Test controller für " + model.getName());
		log.info("$Revision$");
		log.info("Copyright itemis AG 2007-2009");
		log.info("=================================================================================");
	}
	
	/**
	 * Diese Methode gibt die Controller-Instanz zurück.
	 * @return Der Testcontroller.
	 */
	public static TestController getController()
	{
		return controller;
	}

	DummyConnection getDummyConnection()
	{
		return connection instanceof DummyConnection ? (DummyConnection)connection : null;
	}

	boolean isDummyConnection()
	{
		return (connection instanceof DummyConnection) && (!connection.isReal());
	}
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
			controller.prepare();
			controller.close();
		}
		catch (Exception e)
		{
			log.error(e.getLocalizedMessage(), e);
		}
	}
}
