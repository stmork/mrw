/*
**
**	$Filename:	TcpConnection.java $
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

package de.morknet.mrw.comm.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.morknet.mrw.base.MrwException;
import de.morknet.mrw.comm.Connection;
import de.morknet.mrw.comm.can.ChecksumException;
import de.morknet.mrw.util.LogUtil;

public class TcpConnection extends Connection
{
	private final static Log log = LogFactory.getLog(TcpConnection.class);
	private final static int BUFFER_SIZE = 256;
	private final Socket socket;
	private final InputStream         in;
	private final OutputStream        out;
	private final TcpReader reader;

	/**
	 * Dieser Konstruktur baut eine Verbindung zu einer realen Modelleisenbahnanlage
	 * über eine TCP-Verbindung auf. Remote muss daher der mrw_daemon laufen.
	 * @param hostname Der Hostname der TCP-Verbindung
	 * @param port Der Socket-Port der TCP-Verbindung
	 * @throws IOException Wird geworfen, wenn irgendetwas schief geht.
	 */
	public TcpConnection(final String hostname, final int port) throws IOException
	{
		log.debug(LogUtil.printf("Versuche TCP-Verbindung über %s:%d", hostname, port));
		socket = new Socket(hostname, port);
		socket.setSoTimeout(1000);
		in  = socket.getInputStream();
		out = socket.getOutputStream();
		reader = new TcpReader();
	}

	private class TcpReader extends Thread
	{
		private TcpReader()
		{
			super("TCP Receiver");
			setDaemon(true);
			setPriority(MAX_PRIORITY);
		}
		
		@Override
		public void run()
		{
			try
			{
				final byte[] buffer = new byte[BUFFER_SIZE];

				do
				{
					final int len;

					try
					{
						len = in.read(buffer);
						if (len > 0)
						{
							for (int i = 0;i < len;i++)
							{
								try
								{
									processor.processByte(buffer[i] & 0xff);
								}
								catch(ChecksumException ce)
								{
									handleChecksumException(ce);
								}
								catch (MrwException e)
								{
									log.error(e.getLocalizedMessage(), e);
								}
							}
						}
					}
					catch (SocketTimeoutException ste)
					{
						// This block intentionally left blank
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
				log.info("Beende TCP-Übertragung...");
			}
		}
	}

	@Override
	public void close()
	{
		log.info("Beenden der TCP-Übertragung einleiten...");
		try
		{
			reader.interrupt();
			reader.join();
			socket.close();
		}
		catch(InterruptedException ie)
		{
			log.error(ie.getLocalizedMessage(), ie);
		}
		catch (IOException ioe)
		{
			log.error(ioe.getLocalizedMessage(), ioe);
		}
		finally
		{
			log.info("Die TCP-Übertragung wurde beendet.");
		}
	}

	@Override
	public final boolean isReal()
	{
		return reader.isAlive() && (!reader.isInterrupted());
	}

	@Override
	protected void start()
	{
		reader.start();
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
	public void write(byte[] bytes) throws IOException
	{
		out.write(bytes);
	}

	@Override
	protected void stop()
	{
	}
}
