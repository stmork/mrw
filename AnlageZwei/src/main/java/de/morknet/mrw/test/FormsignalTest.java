/*
**
**	$Filename:	FormsignalTest.java $
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

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.morknet.mrw.Modell;
import de.morknet.mrw.ModellFactory;
import de.morknet.mrw.base.Signal;
import de.morknet.mrw.base.Signal.SignalCommand;
import de.morknet.mrw.batch.Batch;
import de.morknet.mrw.batch.BatchExecuter;
import de.morknet.mrw.comm.Connection;
import de.morknet.mrw.comm.MrwMessage;
import de.morknet.mrw.comm.can.CANMessage;
import de.morknet.mrw.comm.can.CANMessageProcessor;
import de.morknet.mrw.comm.can.CANReceiver;
import de.morknet.mrw.comm.can.ChecksumException;
import de.morknet.mrw.comm.dummy.DummyConnection;

public class FormsignalTest implements CANMessageProcessor
{
	private final static Log        log   = LogFactory.getLog(FormsignalTest.class);
	private final static Modell     model = ModellFactory.getInstance();
	private              Connection connection;
	private              int        received = 0;

	private FormsignalTest() throws IOException
	{
		try
		{
			connection = Connection.getDefaultConnection();
		}
		catch(Exception nspe)
		{
			connection = new DummyConnection();
		}
		CANReceiver receiver = new CANReceiver();
		receiver.setProcessor(this); 
        connection.setByteProcessor(receiver);
        connection.sync();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		log.info("Guten Tag!");
		
		int errors = model.validate();
		log.info("Validierung vollständig. Fehlerzahl: " + errors);
		
		if (errors == 0)
		{
			try
			{
				FormsignalTest eisenbahn = new FormsignalTest();
				eisenbahn.test("Bahnhof Alt Ulm G");
			}
			catch (IOException e)
			{
				log.error(e);
			}
		}
	}

	private void test(String name)
	{
		Signal signal = model.findSignal(name);
		
		if (signal == null)
		{
			throw new IllegalArgumentException("Signal " + name + " nicht gefunden.");
		}
		
		for (int i = 0;i < 20;i++)
		{
			setSignal(signal, getSignalCommand(i));
		}
		
		setSignal(signal, SignalCommand.S2);
		setSignal(signal, SignalCommand.S0);
		setSignal(signal, SignalCommand.S2);
		setSignal(signal, SignalCommand.S0);
	}

	private SignalCommand getSignalCommand(int i)
	{
		switch (i & 3)
		{
		case 0:
		case 2:
			return SignalCommand.S1;
		default:
			return SignalCommand.S0;
		}
	}

	private void setSignal(Signal signal, SignalCommand sc)
	{
		BatchExecuter executer = new BatchExecuter();
		Batch batch = executer.createBatch();
//		batch.setDelay(100L);

		signal.setSignal(sc, false);
		signal.addCommand(batch);
		executer.send(connection);
		executer.clear();
	}

	public CANMessage createMsg()
	{
		return new MrwMessage();
	}

	public void process(CANMessage msg)
	{
		++received;
		msg.dump("< " + received);

		if (msg instanceof MrwMessage)
		{
			MrwMessage mrw = (MrwMessage)msg;

			BatchExecuter.processResult(mrw);
		}
	}

	public void checksumError(ChecksumException ce)
	{
		try
		{
			log.error(ce.getLocalizedMessage(), ce);
			log.info("Prüfsummenfehler aufgetaucht.");
			log.info("  Versuche Verbindung zu resynchronisieren...");
			connection.sync();
			log.info("  Verbindung resynchronisiert.");
		}
		catch (IOException e)
		{
			log.error(e.getLocalizedMessage(), e);
		}
	}
}
