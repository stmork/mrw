/*
**
**	$Filename:	CANMain.java $
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

package de.morknet.mrw.comm.test;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.morknet.mrw.comm.Connection;
import de.morknet.mrw.comm.MrwMessage;
import de.morknet.mrw.comm.can.CANMessage;
import de.morknet.mrw.comm.can.CANReceiver;
import de.morknet.mrw.comm.rs232.RS232Connection;
import de.morknet.mrw.util.LogUtil;

/**
 * Diese Klasse definiert ein einfaches Verhalten zum Versenden von MRW-Meldungen und gibt die empfangenen
 * Meldebestätigungen aus.
 * @author sm
 *
 */
abstract public class CANMain implements de.morknet.mrw.comm.can.CANMessageProcessor
{
	private final static Log        log = LogFactory.getLog(CANMain.class); 
	private              Connection connection;
	private              int        received = 0;

	/**
	 * Die zentrale Methode zum Versenden und Empfangen von MRW-Meldungen.
	 * @param args Die Argumentliste des Programmaufrufs.
	 */
	public CANMain(String [] args)
	{
		try
		{
			log.info("Start.");

			// init
			if (args.length > 0)
			{
				connection = new RS232Connection(args[0]);
			}
			else
			{
				connection = Connection.getDefaultConnection();
			}
			CANReceiver receiver = new CANReceiver();
			receiver.setProcessor(this); 
            connection.setByteProcessor(receiver);
            connection.sync();
		}
		catch(Exception e)
		{
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * Diese Methode ist ein Callback, um eine Liste mit MRW-Meldungen zu füllen.
	 * @param list Die zu füllende Liste. 
	 * @throws Exception Eine geworfene Exception, falls was schief geht.
	 */
	abstract protected void fillBatch(java.util.List<MrwMessage> list) throws Exception;

	/**
	 * Diese Methode versendet eine Liste von CAN-Messages.
	 * @see MrwMessage
	 */
	public void execute()
	{
		try
		{
			java.util.List<MrwMessage> list = new ArrayList<MrwMessage>();
			fillBatch(list);
			log.info(LogUtil.printf("Übertrage %d Meldungen...\n", list.size()));
			for (MrwMessage msg : list)
			{
				msg.dump(">");
				connection.write(msg.getBytes());
			}
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * Diese Methode wartet eine vorbestimmte Zeit und beendet danach die Verbindung.
	 * @param s Der Timeout in Sekunden.
	 */
	public void wait(int s)
	{
		try
		{
			Thread.sleep(s * 1000L);
			connection.close();
		}
		catch (InterruptedException e)
		{
			log.error(e.getMessage(), e);
		}
		synchronized(this)
		{
			log.info(LogUtil.printf("Received %d messages.\n", received));
	        log.info("Stop.");
		}
	}

	/**
	 * Diese Methode wertet eine CAN-Message aus. In diesem Falle wird diese nur ausgegeben.
	 */
	synchronized public void process(CANMessage msg)
	{
		msg.dump("< " + ++received);
	}

	/**
	 * Diese Methode erzeugt eine MRW-Meldung.
	 */
	public CANMessage createMsg()
	{
		return new MrwMessage();
	}
}
