/*
**
**	$Filename:	Batch.java $
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
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.morknet.mrw.base.DeviceUnit;
import de.morknet.mrw.comm.Connection;
import de.morknet.mrw.comm.MrwMessage;
import de.morknet.mrw.comm.MsgCode;
import de.morknet.mrw.util.LogUtil;
import de.morknet.mrw.util.NameUtil;

/**
 * Diese Klasse repräsentiert einen Stapel von Kommandos. Die Kommandos werden als MRW-Meldung repräsentiert.
 * Zur MRW-Meldung passt genau ein Gerät. Beides wird als BatchElement in diesem Batch gehalten. Sinn dieses
 * Batches ist, dass alle Kommandos dieses Batches erfolgreich ausgeführt sein muss, bevor der nächste Batch
 * verarbeitet werden darf.
 * @author smork
 *
 */
public class Batch
{
	private final static Log log = LogFactory.getLog(Batch.class); 

	/**
	 * Mapper von der Gerätenummer auf das passende BatchElement.
	 */
	private final        LinkedHashSet<BatchElement>  elements    = new LinkedHashSet<BatchElement>();
	
	/**
	 * Der BatchExecuter zu dem dieser Thread gehört.
	 */
	private final BatchExecuter executer;
	
	/**
	 * Der initiale Timeout in Millisekunden.
	 */
	private              long    timeout     = 5000L;
	
	/**
	 * Ein wahlweises Delay nach dem erfolgreichen Versenden von Kommandos.
	 */
	private              long    delay       =    0L;
	
	/**
	 * Die Wartezeit nach jeder CAN-Message. Diese Wartezeit schont den CAN-Bus vor Überflutung.
	 */
	private final static long    SEND_DELAY  =    1L;
	
	/**
	 * Zähler für fehlerhafte CAN-Rückmeldungen.
	 */
	private              int     errorCount  = 0;

	/**
	 * Die Zahl der eingestellten Kommandos.
	 */
	private              int     cmdCount    = 0;
	
	/**
	 * Flag, ob schon Kommandos gesendet wurden.
	 */
	private              boolean sent        = false;
	
	/**
	 * Der kontinuierlich wachsende Batch ID Zähler.
	 */
	private final static AtomicInteger idCounter   = new AtomicInteger(0);
	
	/**
	 * Die ID dieses Batches.
	 */
	private final        int     ID;
	
	/**
	 * Erzeugt einen neuen Batch. Dieser Batch kann nur vollständig OK verarbeitet werden
	 * oder er führt zu einem Abbruch der weiteren Verarbeitung.
	 * @param executer Der übergeordnete BatchExecuter.
	 */
	Batch(BatchExecuter executer)
	{
		this.executer = executer;

		ID = idCounter.getAndAdd(1);
		log.debug(LogUtil.printf("Batch mit ID %d erzeugt.", ID));
	}

	/**
	 * Fügt ein Kommando dem Batch hinzu. Dieses Kommando wird mittels eines Gerätes zu einem
	 * BatchElement verbunden.
	 * @param device Das zum Kommando passende Gerät.
	 * @param msg Das Kommando als MRW-Meldung.
	 */
	public void addBatchElement(DeviceUnit device, MrwMessage msg)
	{
		Integer id = msg.getId();
		
		if (id != 0)
		{
			synchronized(elements)
			{
				BatchElement element = new BatchElement(this, device, msg);
				elements.add(element);
				cmdCount++;
				log.debug("BatchElement " + element + " hinzugefügt.");
			}
		}
	}

	/**
	 * Entfernt ein BatchElement aus diesem Batch. Dies passiert, wenn ein Kommando erfolgreich ausgeführt
	 * wurde.
	 * @param element Das BatchElement, das entfernt werden soll.
	 */
	public void removeBatchElement(BatchElement element)
	{
		synchronized (elements)
		{
			DeviceUnit device = element.getDeviceUnit();
			
			elements.remove(element);
			device.clearBatchElement();
			log.debug("BatchElement " + element + " entfernt.");
		}
	}

	/**
	 * Setzt eine Wartezeit, die nach erfolgreichem Versenden der Kommandos gewartet wird.
	 * @param delay Das Wartequantum in Millisekunden.
	 */
	public void setDelay(long delay)
	{
		this.delay = delay;
	}

	/**
	 * Prüft, ob überhaupt Kommandos zum Versenden anstehen.
	 * @return Ob die Kommandoliste leer ist.
	 */
	public boolean isEmpty()
	{
		synchronized(elements)
		{
			return cmdCount == 0;
		}
	}

	/**
	 * Diese Methode sendet diesen Batch über die angegebene Verbindung. Die Methode wartet, bis
	 * ein Timeout erreicht wurde. Kommt als Rückmeldecode MSG_QUEUED, wird der Timeout um eine
	 * Sekunde hochgesetzt.
	 * @param connection Die Verbindung, über die die Kommandos dieses Batches gesendet werden soll.
	 * @throws IOException Wird bei Sendefehlern geworfen.
	 */
	void sendBatch(Connection connection) throws IOException
	{
		log.debug(">sendBatch() " + getIdString());
		if (sent)
		{
			throw new BatchRetransmissionException(this);
		}

		if (elements.size() > 0)
		{
			// Hier erfolgt das Aufbauen der Kommando-Liste. Das muss synchronisiert erfolgen.
			List<MrwMessage> msgList;
			synchronized (elements)
			{
				msgList = new ArrayList<MrwMessage>(elements.size());
				log.debug(LogUtil.printf("   %d Batch Kommando(s).", cmdCount));
				for (BatchElement element : elements)
				{
					MrwMessage msg = element.getMessage();
					msgList.add(msg);
					element.getDeviceUnit().setBatchElement(element);
				}
			}
			
			log.debug("Sende Kommandos...");
			synchronized (this)
			{
				// Alles auf Anfang.
				errorCount = 0;
			}
			// Hier erfolgt das Versenden der Kommandos. Das darf nicht synchronisiert erfolgen, da
			// schon während des Versendens die ersten Rückmeldungen erfolgen können.
			long sendStart = System.currentTimeMillis();
			for(MrwMessage msg : msgList)
			{
				msg.dump(">");
				connection.write(msg.getBytes());
				try
				{
					// Atempause für den CAN-Bus.
					Thread.sleep(SEND_DELAY);
				}
				catch (InterruptedException e)
				{
					log.error("Probleme beim Warten nach dem Senden: " + msg);
					log.error(e.getLocalizedMessage(), e);
				}
			}
			sent = true;
			long sendEnd = System.currentTimeMillis();
			log.debug(LogUtil.printf("Senden der %d Kommandos in %d ms erfolgt.",
					cmdCount, sendEnd - sendStart));
			log.debug("Warte auf Ausführung der Kommandos (Go, ATmega, Go!)...");
			
			try
			{
				long start = System.currentTimeMillis();
				long diff;
				
				// Solange noch
				// 1. Restzeit vorhanden ist
				// 2. Noch Rückmeldungen ausstehen 
				// 3. kein Fehler aufgetaucht ist
				// wird die Restzeit gewartet.
				synchronized (this)
				{
					while((diff = getWaitTime(start)) > 0L)
					{
						log.debug(LogUtil.printf("Warte %d ms...", diff));
						wait(diff);
						log.debug(LogUtil.printf("Es sind noch %d ms Wartezeit übrig.",
								start + timeout - System.currentTimeMillis()));
					}
				}
				synchronized(elements)
				{
					// Auflistung aller unvollständigen Kommandos.
					if (elements.size() > 0)
					{
						log.info("Liste der nicht ausgeführten Kommandos - " + getIdString() + ":");
						for(BatchElement element : elements)
						{
							log.info(element);
						}
					}
				}
				
				// Fehlerstatus loggen und Exception werfen
				if (hasError())
				{
					log.info("Batch Kommandoverarbeitung mit Fehlern.");
					throw new FailedExecutionException(this);
				}
				
				// Unvollständigkeit loggen und Exception werfen.
				if (!isComplete())
				{
					log.info("Zeit für Batch Kommandoausführung abgelaufen.");
					throw new BatchNotCompletedException(this);
				}
				log.debug("Batch Kommandoausführung durchgeführt. " + getIdString());

				if (delay > 0L)
				{
					log.debug(LogUtil.printf("Verzögere %d ms...", delay));
					Thread.sleep(delay);
					log.debug("Fertig.");
				}
			}
			catch (InterruptedException e)
			{
				log.error(e.getMessage(), e);
			}
		}
		else
		{
			log.info("Keine Kommandos für diesen Batch.");
		}
		log.debug("<sendBatch() " + getIdString());
	}

	/**
	 * Diese Methode liefert die Restlaufzeit in der auf rückemeldende Kommandos gewartet werden soll. 
	 * @param start Der Startzeitpunkt, ab dem Kommandos übertragen wurden.
	 * @return Gültige Restlaufzeit der Kommandos.
	 */
	private long getWaitTime(long start)
	{
		synchronized(this)
		{
			if (isComplete() || hasError())
			{
				return 0L;
			}
			return start + timeout - System.currentTimeMillis();
		}
	}

	/**
	 * Diese Methode verarbeitet eine MRW-Meldung gemäß ihrem Rückmelde-Code. Der Code MSG_OK markiert ein
	 * BatchElement als erfolgreich verarbeitet. Ein MSG_QUEUED setzt den Timeout um eine Sekunde hoch.
	 * Die Rückmelde-Codes
	 * <ul>
	 * <li>MSG_NO_UNITNO_DEFINED
	 * <li>MSG_UNIT_NOT_FOUND
	 * <li>MSG_UNITNO_MISSING
	 * <li>MSG_UNITTYPE_WRONG
	 * <li>MSG_NOT_CONFIGURED_YET
	 * <li>MSG_SWITCH_FAILED
	 * </ul>
	 * erhöhen den Fehlerzähler.
	 * @param msg Die verarbeitende MRW-Meldung.
	 * @return Ob die Meldung verarbeitet wurde.
	 */
	boolean processResult(MrwMessage msg, BatchElement element)
	{
		boolean processed = false;
		
		Integer id = msg.getResultId();
		synchronized(elements)
		{
			log.debug(">processResult() " + getIdString() + "  Msg-ID=" + NameUtil.getIdString(id));
			MsgCode result = msg.getResultCode();
			element.setResultCode(result);
			
			switch(result)
			{
			case MSG_OK:
				// Fertig.
				removeBatchElement(element);
				processed = true;
				break;

			case MSG_QUEUED:
				// Timeout verlängern
				synchronized(this)
				{
					timeout += 1000L;
					log.debug("Timeout: " + timeout + " ms");
				}
				processed = true;
				break;
				
			case MSG_NO_UNITNO_DEFINED:
			case MSG_UNIT_NOT_FOUND:
			case MSG_UNITNO_MISSING:
			case MSG_UNITTYPE_WRONG:
			case MSG_NOT_CONFIGURED_YET:
			case MSG_SWITCH_FAILED:
				// Fehler!
				synchronized(this)
				{
					errorCount++;
					log.error(LogUtil.printf("Fehlerzustand %s aufgetreten! Fehlerzähler: %d",
							result, errorCount));
				}
				break;

			default:
				// Alle anderen Rückemelde-Codes werden ignoriert.
				log.warn(LogUtil.printf("Antwortcode %s nicht verarbeitet.", result));
				break;
			}
			log.debug(LogUtil.printf("<processResult() %s  Msg-ID=%s", getIdString(), NameUtil.getIdString(id)));
		}
		return processed;
	}

	/**
	 * Prüft, ob Fehler aufgetreten sind.
	 * @return True, falls es zu fehlern kam
	 */
	public boolean hasError()
	{
		synchronized(this)
		{
			return this.errorCount > 0;
		}
	}

	/**
	 * Prüft, ob im Batch alle Kommandos zu einer erfolgreichen Rückmeldung geführt haben, oder 
	 * ob noch Rückmeldungen ausstehen. 
	 * @return Zustand, ob alle Rückmeldungen erfolgreich eingetroffen sind.
	 */
	public boolean isComplete()
	{
		synchronized (elements)
		{
			return elements.isEmpty();
		}
	}

	/**
	 * Löscht alle Kommandos aus diesem Batch.
	 */
	public void clear()
	{
		synchronized(elements)
		{
			for (BatchElement element : elements)
			{
				element.getDeviceUnit().clearBatchElement();
			}
			elements.clear();
		}
	}
	
	/**
	 * Diese Methode bildet einen Text, der die IDs dieses Batches und des dazugehörigen
	 * {@link BatchExecuter} enthält.
	 * @return Der Text mit den ID-Informationen
	 */
	public String getIdString()
	{
		return "BatchExecuter " + executer.getId() + ", Batch " + getId();
	}

	/**
	 * Diese Methode gibt die ID dieses Batches zurück.
	 * @return Die ID dieses Batches.
	 */
	public int getId()
	{
		return ID;
	}

	/**
	 * Diese Methode gibt die Zahl der Schaltkommandos in diesem Batch zurück. 
	 * @return Die Zahl der Schaltkommandos in diesem Batch.
	 */
	public Object getElementCount()
	{
		return elements.size();
	}
}
