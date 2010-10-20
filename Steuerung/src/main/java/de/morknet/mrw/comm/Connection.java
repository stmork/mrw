/*
**
**	$Filename:	Connection.java $
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

package de.morknet.mrw.comm;

import java.io.IOException;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.morknet.mrw.comm.can.ChecksumException;
import de.morknet.mrw.comm.rs232.RS232Connection;
import de.morknet.mrw.util.MrwProperties;

/**
 * Diese Klasse repräsentiert eine Kommunikationsverbindung zu einer Eisenbahnanlage.
 * @author sm
 *
 */
abstract public class Connection
{
	private final static Log  log = LogFactory.getLog(Connection.class);
	private final static byte sync_sequence [] =
	{
		-1,-1,-1,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0
	};

	/**
	 * Die Verbindung zur Auswertung einzelner Bytes.
	 */
	protected ByteProcessor processor;

	/**
	 * Diese Methode öffnet die konfigurierte Default-Verbindung.
	 * @return Eine Verbindungsinstanz dieser Klasse.
	 * @throws Exception Wird geworfen, falls was fehlerhaft verlaufen ist.
	 */
	public static Connection getDefaultConnection() throws Exception
	{
		String portname = MrwProperties.getProperty("mrw.port", getDefaultPort());
		log.debug("Port name: " + portname);

		return new RS232Connection(portname);
	}

	/**
	 * Diese Methode ermittelt abhängig vom eingesetzten Betriebssystem den Dateinamen des
	 * Default-Anschlusses.
	 * @return Der Dateiname des Default-Anschlusses.
	 */
	private static String getDefaultPort()
	{
		String os = System.getProperty("os.name").toLowerCase(Locale.ROOT);
		if (os.startsWith("linux"))
		{
			return "/dev/ttyS0";
		}
		else if (os.startsWith("windows"))
		{
			return "COM1";
		}
		return null;
	}
	
	/**
	 * Diese Methode liefert zurück, ob eine reale Verbindung zu einer Modelleisenbahn besteht. Auch
	 * eine reale Verbindung kann hier false liefern, wenn die Verbindung z.B. schon abgebaut ist. Es muss
	 * also eine physikalische Verbindung bestehen und diese muss auch initialisiert und aktiv sein.
	 * @return Zustand über reale Verbindung zur Modelleisenbahn.
	 */
	public boolean isReal()
	{
		return false;
	}

	/**
	 * Diese Methode sendet eine spezielle Bytesequenz über die Verbindung, um eine
	 * synchronisierte Verbindung herzustellen.
	 * @throws IOException Wird geworfen, falls ein Verbindungsfehler aufgetreten ist.
	 */
	public void sync() throws IOException
	{
		log.info("Versende Synchronisierungssequenz über die Verbindung...");
		write(sync_sequence);
	}

	/**
	 * Diese Methode setzt die Klasse, die die über diese Connection empfagnenen Bytes auswertet.
	 * @param processor Die Auswerteklasse für empfangene Bytes.
	 */
	synchronized public void setByteProcessor(final ByteProcessor processor)
	{
		if (processor == null)
		{
			if (this.processor == null)
			{
				throw new IllegalStateException("Der ByteProcessor ist bereits gelöscht!");
			}
			stop();
			this.processor = processor;
		}
		else
		{
			if (this.processor != null)
			{
				throw new IllegalStateException("Der ByteProcessor ist bereits gesetzt!");
			}
			this.processor = processor;
			start();
		}
	}
	
	/**
	 * Diese Methode wird aufgerufen, wenn die Methode {@link #setByteProcessor(ByteProcessor)}
	 * einen {@link ByteProcessor} setzt, der ungleich null ist. Dadurch soll das Verarbeiten
	 * von Bytes seinen Betrieb aufnehmen.
	 */
	abstract protected void start();

	/**
	 * Diese Methode wird aufgerufen, wenn die Methode {@link #setByteProcessor(ByteProcessor)}
	 * einen {@link ByteProcessor} setzt, der gleich null ist. Dadurch soll das Verarbeiten
	 * von eingehenden Bytes abgeschaltet werden.
	 */
	abstract protected void stop();

	/**
	 * Diese Methode gibt die Bytesequenz zurück, mit der das CAN-Gateway synchronisiert wird.
	 * @return Die Bytesequenz zum Synchronisieren des CAN-Gateways.
	 */
	protected byte [] getSyncSequence()
	{
		return sync_sequence;
	}

	/**
	 * Diese Methode macht die Fehlerbehandlung im Falle eines Prüfsummenfehlers. Dabei wird
	 * eine Resynchronisation der Verbindung ausgeführt.
	 * @param ce Die auslösende {@link ChecksumException}.
	 * @throws IOException Wird geworfen, wenn die Resynchronisation fehlschlägt.
	 */
	protected void handleChecksumException(ChecksumException ce) throws IOException
	{
		log.error(ce.getLocalizedMessage(), ce);
		sync();
	}

	/**
	 * Diese Methode liest ein einzelnes Byte über diese Verbindung ein.
	 * @return Das gelesene Byte
	 * @throws IOException Diese Exception wird geworfen, wenn das Lesen fehlschlug.
	 */
	abstract public int read() throws IOException;
	
	/**
	 * Diese Methode schreibt ein einzelnes Byte über die Kommunikationsverbindung.
	 * @param i Das zu schreibende Byte.
	 * @throws IOException Diese Exception wird geworfen, wenn das Schreiben fehlschlug.
	 */
	abstract public void write(int i) throws IOException;

	/**
	 * Diese Methode schreibt ein Array aus Bytes über die Kommunikationsverbindung.
	 * @param bytes Die zu schreibenden Bytes.
	 * @throws IOException Diese Exception wird geworfen, wenn das Schreiben fehlschlug.
	 */
	abstract public void write(byte[] bytes) throws IOException;

	/**
	 * Diese Methode baut die Kommunikationsverbindung wieder ab.
	 */
	abstract public void close();
}
