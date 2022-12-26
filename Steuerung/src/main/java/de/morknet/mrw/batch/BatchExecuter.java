/*
**
**	$Filename:	BatchExecuter.java $
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

package de.morknet.mrw.batch;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.morknet.mrw.base.DeviceUnit;
import de.morknet.mrw.comm.Connection;
import de.morknet.mrw.comm.MrwMessage;
import de.morknet.mrw.util.LogUtil;
import de.morknet.mrw.util.NameUtil;

/**
 * Der BatchExecuter ist eine Verwaltungsklasse für Batches. 
 * @author smork
 *
 */
public class BatchExecuter
{
	private final static Log                log         = LogFactory.getLog(BatchExecuter.class);
	
	/**
	 * Menge aller aktiven BatchExecuter.
	 */
	private final static Set<BatchExecuter> executerSet = new HashSet<BatchExecuter>();
	
	/**
	 * Liste der zu verarbeitenden Batches.
	 */
	private final        LinkedList<Batch>  workQueue = new LinkedList<Batch>();
	private final static AtomicInteger      idCounter = new AtomicInteger(0);
	private final        int                ID;

	/**
	 * Dieser Konstruktur initialisiert einen neuen BatchExecuter und registriert ihn in der
	 * Abarbeitungsliste.
	 */
	public BatchExecuter()
	{
		synchronized (executerSet)
		{
			executerSet.add(this);
		}

		ID = idCounter.getAndAdd(1);
		log.debug(LogUtil.printf("BatchExecuter mit ID %d erzeugt.", ID));
	}

	/**
	 * Diese Methode erzeugt einen neuen Batch und trägt ihn in die Liste der zu verarbeitenden
	 * Batches ein.
	 * @return der neue Batch.
	 */
	public Batch createBatch()
	{
		checkValidity();

		Batch batch = new Batch(this);
		synchronized (workQueue)
		{
			workQueue.add(batch);
		}
		return batch;
	}
	
	/**
	 * Diese Methode sendet alle in diesem BatchExecuter gesammelten Batches.
	 * @param connection Die Verbindun, über die die Batches ihre Kommandos senden.
	 */
	public void send(Connection connection)
	{
		boolean success = false;

		if (log.isDebugEnabled())
		{
			log.debug(">send() ID=" + ID);
		}

		checkValidity();
		try
		{
			while (!workQueue.isEmpty())
			{
				Batch first = workQueue.getFirst();
				first.sendBatch(connection);

				log.debug("Entferne Batch...");
				synchronized(workQueue)
				{
					workQueue.remove(first);
				}
			}
			success = true;
		}
		catch(BatchProcessingException bpe)
		{
			clear();
			throw bpe;
		}
		catch(IOException e)
		{
			clear();
			throw new BatchIOException(e);
		}
		finally
		{
			if (log.isDebugEnabled())
			{
				log.debug("<send() = " + success + "  ID=" + ID );
			}
		}
	}

	/**
	 * Diese Methode verarbeitet die Antwort eines Kommandos.
	 * @param msg Die Rückantwort eines Kommandos
	 * @return True, wenn die Rückantwort einem Batch zugeordnet werden konnte.
	 */
	public static boolean processResult(MrwMessage msg)
	{
		boolean hadBatch = false;

		if (msg.isResult())
		{
			int id = msg.getResultId();
			
			if (id != 0)
			{
				DeviceUnit device = DeviceUnit.findDeviceUnit(id);
				if (device.isProcessing())
				{
					BatchElement element = device.getBatchElement();
					Batch batch = element.getBatch();

					synchronized(batch)
					{
						batch.processResult(msg, element);
						
						if (batch.isComplete() || batch.hasError())
						{
							log.debug("Notifying batch...");
							batch.notifyAll();
							log.debug("Done.");
						}
						hadBatch = true;
					}
				}
				else
				{
					log.debug("Batch für Meldung mit ID " + NameUtil.getIdString(id) + " nicht gefunden.");
				}
			}
		}
		return hadBatch;
	}

	/**
	 * Prüft, ob dieser BatchExecuter Batches zur Verarbeitung anstehend hat.
	 * @return True, falls Batches zur Verarbeitung anstehen.
	 */
	public static boolean hasBatches()
	{
		synchronized(executerSet)
		{
			for (BatchExecuter executer : executerSet)
			{
				synchronized (executer.workQueue)
				{
					if (!executer.workQueue.isEmpty())
					{
						if (!executer.workQueue.getFirst().isComplete())
						{
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	/**
	 * Diese Methode löscht alle Batches aus den entsprechenden Warteschlangen und
	 * deinitialisiert diese.
	 */
	public void clear()
	{
		synchronized (executerSet)
		{
			synchronized(workQueue)
			{
				for (Batch batch : workQueue)
				{
					batch.clear();
				}
				workQueue.clear();
			}
			executerSet.remove(this);
			log.debug("BatchExecuter mit ID " + ID + " entfernt.");
		}
	}

	/**
	 * Diese Methode gibt die ID dieses BatchExecuter zurück.
	 * @return Die ID dieses BatchExecuters.
	 */
	public int getId()
	{
		return ID;
	}

	private void checkValidity()
	{
		synchronized(executerSet)
		{
			if (!executerSet.contains(this))
			{
				throw new InvalidBatchExecuterException(this);
			}
		}
	}
}
