/*
**
**	$Filename:	CANReceiver.java $
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

package de.morknet.mrw.comm.can;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.morknet.mrw.base.MrwException;
import de.morknet.mrw.comm.ByteProcessor;
import de.morknet.mrw.util.LogUtil;

/**
 * Diese Klasse baut aus einzelnen empfangenen Bytes eine CAN-Message zusammen und schickt diese
 * CAN-Message zur Auswertung an den nächsten Verarbeitungsprozessor weiter.
 * @author sm
 *
 */
public class CANReceiver implements ByteProcessor
{
	private final static Log log = LogFactory.getLog(CANReceiver.class);

	private   final int                       buffer[]    = new int[15];
	private         int                       index       = 0;
	private         int                       checksum    = 0;
	protected final BlockingQueue<CANMessage> queue       = new LinkedBlockingQueue<CANMessage>();
	private         boolean                   veryVerbose = false;
	private         MessageRunner             processorThread;
	private         CANMessageProcessor       processor;

	/**
	 * Diese Klasse verarbeitet CAN-Messages aus einer Verarbeitungswarteschlange.
	 * @author sm
	 *
	 */
	protected class MessageRunner extends Thread
	{
		private boolean loop = true;

		private MessageRunner()
		{
			super("CAN-Message Verarbeitung");
		}

		/**
		 * Diese Methode führt die Verarbeitung der CAN-Messages in einer Schleife aus. Die Schleife
		 * wird durch Aufruf von {@link Thread#interrupt()} abgebrochen.
		 */
		@Override
		final public void run()
		{
			try
			{
				while(loop)
				{
					CANMessage msg = queue.take();
					if (processor != null)
					{
						try
						{
							processor.process(msg);
						}
						catch(MrwException re)
						{
							log.error(re.getLocalizedMessage(), re);
						}
					}
				}
			}
			catch (InterruptedException ie)
			{
				log.info("Veranlasse Ende der Verarbeitung von CAN-Meldungen...");
				loop = false;
			}
			finally
			{
				log.info("Verarbeitung von CAN-Meldungen sind beendet.");
			}
		}
	}

	/**
	 * Diese Methode verarbeitet ein einzelnes empfangenes Byte zu einer CAN-Meldung. Sobald
	 * die CAN-Meldung vollständig ist, wird eine CAN-Meldung instanziiert und zur Weiterverarbeitung
	 * in eine asynchrone Warteschlange gestellt, um weiterhin Bytes empfangen zu können.
	 * @param input Das empfangene Byte.
	 */
	synchronized public void processByte(final int input)
	{
		if (log.isDebugEnabled() && veryVerbose )
		{
		    log.debug(LogUtil.printf("%2d: %02x", index, input));
		}

		buffer[index++] = input;
	    checksum += input;

	    if ((buffer[0] <= 0) || (buffer[0] > 8))
	    {
            init();
	    }
	    else if ((buffer[0] + 7) == index)
	    {
	    	try
	    	{
		        if ((checksum & 0xff) == 0)
		        {
		        	CANMessage msg;
		        	if (processorThread != null)
		        	{
		        		msg = processor.createMsg();
		        		msg.setSid(buffer[2] | (buffer[3] << 8));
		        		msg.setEid(buffer[4] | (buffer[5] << 8));
		        		msg.setStatus(buffer[1]);
			        	for (int i = 0;i < buffer[0]; i++)
			        	{
			        		msg.addDataByte(buffer[i+6]);
			        	}
			        	try
			        	{
							queue.put(msg);
						}
			        	catch (InterruptedException ie)
			        	{
							log.error(ie.getMessage(), ie);
						}
		        	}
		        	else
		        	{
			        	msg = new CANMessage(
			        			buffer[2] | (buffer[3] << 8),
			        			buffer[4] | (buffer[5] << 8),
			        			buffer[1]);
			        	for (int i = 0;i < buffer[0]; i++)
			        	{
			        		msg.addDataByte(buffer[i+6]);
			        	}
		        		msg.dump("< (not processed)");
		        	}
		        }
		        else
		        {
				    throw new ChecksumException(checksum, index, buffer);
		        }
	    	}
	    	finally
	    	{
	    		init();
	    	}
	    }
	}

	/**
	 * Diese Methode definiert die Verarbeitungsinstanz für CAN-Meldungen.
	 * @param processor Die Verarbeitungsinstanz für CAN-Meldungen.
	 */
	public void setProcessor(CANMessageProcessor processor)
	{
		if ((processorThread == null) && (this.processor == null))
		{
			this.processorThread = new MessageRunner();
			this.processor = processor;
			processorThread.start();
		}
		else
		{
			throw new IllegalStateException("CAN-Message Verarbeitung schon initialisiert.");
		}
	}

	/**
	 * Diese Methode schließt die Verarbeitung von CAN-Messages ab.
	 */
	public void close()
	{
		if (processorThread != null)
		{
			log.info("Beenden der Verarbeitung von CAN-Meldungen einleiten...");
			try
			{
				this.processor = null;
				this.processorThread.interrupt();
				this.processorThread.join();
				this.processorThread = null;
			}
			catch (InterruptedException ie)
			{
				log.error(ie.getLocalizedMessage(), ie);
			}
			finally
			{
				log.info("Die Verarbeitung von CAN-Meldungen wurde beendet.");
			}
		}
	}

	/**
	 * Löscht den Empfangspuffer.
	 */
	private void init()
	{
		index = 0;
		checksum = 0;
		for (int i = 0; i < buffer.length;i++)
		{
			buffer[i] = 0;
		}
	}
}
