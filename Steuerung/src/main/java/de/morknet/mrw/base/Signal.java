/*
**
**	$Filename:	Signal.java $
**	$Revision$
**	$Author$
**	$Id$
**
**	Copyright (C) 2011 committers of this modelrailway project. All rights reserved.
**
**	This program and the accompanying materials are made available under the
**	terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
**	which accompanies this distribution.
**
**	The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
**
**
*/

package de.morknet.mrw.base;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.morknet.mrw.comm.Command;
import de.morknet.mrw.comm.MrwMessage;
import de.morknet.mrw.comm.SignalCode;
import de.morknet.mrw.util.LogUtil;

/**
 * Diese Klasse repräsentiert abstrakt ein Signal.
 * @author smork
 *
 */
abstract public class Signal extends DeviceUnit
{
	private static final long serialVersionUID = 1L;

	private final boolean inDirection;
	private final Abschnitt segment;
	private final String number;
	private       SignalCode currentState = SignalCode.SIGNAL_OFF;

	/**
	 * Das Log für Meldungen.
	 */
	protected final Log log;
	
	/**
	 * Das letztendlich dargestellte Signalbild.
	 */
	protected SignalCommand command      = SignalCommand.OFF;

	/**
	 * Der angeforderte Signalcode.
	 */
	protected SignalCode    nominalState = SignalCode.SIGNAL_OFF;

	/**
	 * Diese Aufzählung listet die Signalkommandos auf.
	 * <ul>
	 * <li>OFF - Das Signal wird ausgeschaltet.
	 * <li>S0 - Halt
	 * <li>S1 - Fahrt
	 * <li>S2 - Langsamfahrt
	 * <li>TEST - Testmodus, alle Lämpchen sind an.
	 * </ul>
	 * Je nach Signal und Rangiermodus kann dadurch ein anderes Signalbild entstehen. Aus Langsamfahrt muss
	 * also nicht Hp2 resultieren, sondern z.B. Hp1 bei einem Blocksignal bzw. Sh1 bei einem Ausfahrsignal
	 * im Rangiermodus.
	 * @see SignalCode
	 * @author sm
	 *
	 */
	public enum SignalCommand
	{
		/**
		 * Signal aussschalten.
		 */
		OFF,

		/**
		 * Signal Halt!
		 */
		S0,

		/**
		 * Signal Fahrt!
		 */
		S1,

		/**
		 * Signal Langsamfahrt!
		 */
		S2,
		
		/**
		 * Signal im Testmodus.
		 */
		TEST
	}

	/**
	 * Diese Methode bildet aus einer Signalnummer einen eindeutigen Namen. Dieser besteht aus
	 * dem Namen der zum Signal gehörenden Betriebsgruppe und eben der Signalnummer.
	 * @param segment Der Abschnitt, in dem das Signal steht.
	 * @param name Die Signalnummer.
	 * @return Der eindeutige Name bestehend aus Betriebsgruppennamen und Signalnummer.
	 */
	public static String buildName(Abschnitt segment, String name)
	{
		return segment.getGroup().getName() + " " + name;
	}

	/**
	 * Dieser Konstruktur initialisiert ein Signal.
	 * @param segment Der Gleisabschnitt, zu dem das Signal gehören soll
	 * @param number Die Signalnummer.
	 * @param inDirection Die Fahrtrichtung.
	 */
	public Signal(Abschnitt segment, String number, boolean inDirection)
	{
		super(segment.getModell(), buildName(segment, number));
		this.log = LogFactory.getLog(LogUtil.pad(number, 5));
		this.inDirection = inDirection;
		this.segment = segment;
		this.number = number;
	}

	/**
	 * Diese Methode gibt die Signalnummer zurück.
	 * @return Die Signalnummer.
	 */
	public String getNumber()
	{
		return this.number;
	}

	/**
	 * Diese Methode gibt relativ zur Zählrichtung den Standort wider.
	 * @param direction 
	 * @return Flag, ob die geforderte Richtung entgegen der Zählrichtung ist.
	 */
	public boolean isDirection(boolean direction)
	{
		return inDirection == direction;
	}

	/**
	 * Diese Methode korrigiert Signalkommandos in Abhängigkeit zur Darstellungsform des Signals und ob es sich
	 * beim Schaltvorgang um eine Rangierfahrt handelt.
	 * @param sb Das Signalkommando
	 * @param shunting Flag, ob es sich um Rangierfahrt handelt.
	 * @return Das korrigierte Signalkommando.
	 */
	protected SignalCommand correctState(SignalCommand sb, boolean shunting)
	{
		return sb;
	}

	/**
	 * Diese Methode gibt den Gleisabschnitt des Signals zurück.
	 * @return Der Gleisabschnitt zum Signal.
	 */
	public Abschnitt getSegment()
	{
		return this.segment;
	}

	/**
	 * Diese Methode gibt den aktuellen Schaltzustand des Signals wider.
	 * @return Der aktuelle Schaltzustand als Text.
	 */
	public String toString()
	{
		return getName() + " nominal=" + nominalState + " current=" + currentState;
	}

	/**
	 * Diese Methode bereitet eine MRW-Message so auf, dass ein Schaltkommando mit dem 
	 * richtigen Signalbild entsteht.
	 * @param msg die zu füllende MRW-Meldung.
	 * @see MrwMessage
	 * @see SignalCode
	 */
	public void addData(MrwMessage msg)
	{
		msg.addDataByte(getNominalState().getSignalCode());
	}

	/**
	 * Diese Methode gibt das Schaltkommando für Signale zurück.
	 * @return Das Signal-Kommando zum Schalten eines Signals.
	 */
	protected Command getCommand()
	{
		return Command.SETSGN;
	}

	@Override
	public MrwMessage createConfigMessage()
	{
		MrwMessage msg = MrwMessage.createCommandMsg(getConfigCode(), ctrl_id, unit_no);
		addPinConfig(msg);
		return msg;
	}

	/**
	 * Diese Methode setzt den Sollzustand eines Signals. Dabei wird das Signal entriegelt.
	 * @param sc Das Schaltkommando
	 * @param shunting  Flag, ob es sich um eine Rangierfahrt handelt.
	 * @return Die korrigierte Fassung des Schaltkommandos.
	 */
	public SignalCommand setSignal(SignalCommand sc, boolean shunting)
	{
		command = setSignalCommand(sc, shunting);
		return command;
	}
	
	/**
	 * Diese Methode schaltet ein Signal aus, so dass es kein Signalbild mehr anzeigt. Dabei wird das
	 * Signal entriegelt.
	 */
	public void off()
	{
		command      = SignalCommand.OFF;
		nominalState = SignalCode.SIGNAL_OFF;
	}
	
	/**
	 * Diese Methode schaltet ein Signal komplett ein, so dass alle Lampen eingeschaltet sind. Dabei wird das
	 * Signal entriegelt.
	 */
	public void test()
	{
		command      = SignalCommand.TEST;
		nominalState = SignalCode.SIGNAL_TST;
	}

	/**
	 * Diese Methode verriegelt ein Signal gegen Verstellen.
	 */
	public void setState()
	{
		log.debug("Zustandswechsel: " + currentState.getShortCode() + " -> " + nominalState.getShortCode());
		currentState = nominalState;
	}
	
	/**
	 * Diese Methode berechnet aus einem Signalkommando das richtige Signalbild abhängig von diesem
	 * Signal und ob rangiert oder gefahren werden soll.
	 * @param sc Das zu interpretierende Signalkommando.
	 * @param shunting Das Rangierflag.
	 * @return Der korrigierte Signalkommando.
	 */
	abstract protected SignalCommand setSignalCommand(SignalCommand sc, boolean shunting);
	
	/**
	 * Diese Methode gibt den Kommando-Code zum Konfigurieren der Microcontroller zurück.
	 * @return Das Konfigurationskommando.
	 */
	abstract protected Command getConfigCode();

	/**
	 * Diese Methode gibt den aktuellen Zustand des Signals zurück.
	 * @return Das aktuelle Signalbild dieses Signals.
	 */
	public SignalCode getSignalState()
	{
		return currentState;
	}

	/**
	 * Diese Methode gibt den Kommandocode zum Schalten eines Signals in Abhängigkeit des Sollzustandes
	 * zurück.
	 * @return Der Schaltcode für das Schaltkommando.
	 */
	protected SignalCode getNominalState()
	{
		return nominalState;
	}
}
