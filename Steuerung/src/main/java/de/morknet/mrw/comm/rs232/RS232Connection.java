/*
**
**	$Filename:	RS232Connection.java $
**	$Revision: 931 $
**	$Date: 2010-04-14 10:39:15 +0200 (Mi, 14. Apr 2010) $
**	$Author: smork $
**	$Id: RS232Connection.java 931 2010-04-14 08:39:15Z smork $
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

package de.morknet.mrw.comm.rs232;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.morknet.mrw.base.MrwException;
import de.morknet.mrw.comm.Connection;

/**
 * Diese Klasse steuert eine RS232-Verbindung zu einer realen Modelleisenbahnanlage.
 * @author sm
 *
 */
public final class RS232Connection extends Connection
{
	private final static Log log = LogFactory.getLog(RS232Connection.class);

	private final CommPortIdentifier  portId;
	private final SerialPort          port;
	private final InputStream         in;
	private final OutputStream        out;
	private final SerialReader        reader;

	/**
	 * Dieser Konstruktur baut eine Verbindung zu einer realen Modelleisenbahnanlage über eine RS232-Verbindung auf.
	 * @param port_name Der Portname der RS232-Verbindung
	 * @throws Exception Wird geworfen, wenn irgendetwas schief geht.
	 */
	public RS232Connection(String port_name) throws Exception
	{
		portId = CommPortIdentifier.getPortIdentifier(port_name);
		port = (SerialPort) portId.open("CAN", 115200);
		port.setSerialPortParams(115200,
				SerialPort.DATABITS_8,
				SerialPort.STOPBITS_1,
				SerialPort.PARITY_NONE);
		port.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
		port.enableReceiveTimeout(500);

		in  = port.getInputStream();
    	out = port.getOutputStream();
    	
    	reader = new SerialReader();
    	reader.start();
	}
	
	private class SerialReader extends Thread
	{
		private SerialReader()
		{
			super("RS232 Receiver");
			setDaemon(true);
			setPriority(MAX_PRIORITY);
		}
		
		@Override
		public void run()
		{

			try
			{
				do
				{
					final byte[] buffer = new byte[1024];
					final int    len = in.read(buffer);

					if ((processor != null) && (len > 0))
					{
						synchronized(processor)
						{
							for (int i = 0;i < len;i++)
							{
								try
								{
									processor.processByte(buffer[i] & 0xff);
								}
								catch (MrwException e)
								{
									log.error(e.getLocalizedMessage(), e);
								}
							}
						}
					}
				}
				while(!isInterrupted());
			}
			catch (final IOException ioe)
			{
				log.error(ioe.getMessage(), ioe);
			}
			finally
			{
				log.info("Beende RS232-Übertragung...");
			}
		}
	}

	@Override
	public int read() throws IOException
	{
		return in.read();
	}

	@Override
	public void write(int i) throws IOException
	{
		out.write(i);
	}

	@Override
	synchronized public void write(byte[] bytes) throws IOException
	{
		out.write(bytes);
	}

	@Override
	public final boolean isReal()
	{
		return reader.isAlive() && (!reader.isInterrupted());
	}

	@Override
	public void close()
	{
		log.info("Beenden der RS232-Übertragung einleiten...");
		try
		{
			reader.interrupt();
			reader.join();
			port.close();
		}
		catch(InterruptedException ie)
		{
			log.error(ie.getLocalizedMessage(), ie);
		}
		finally
		{
			log.info("Die RS232-Übertragung wurde beendet.");
		}
	}
}
