/*
**
**	$Filename:	Abschnitt.java $
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

package de.morknet.mrw.base;

import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import de.morknet.mrw.Modell;
import de.morknet.mrw.base.Signal.SignalCommand;
import de.morknet.mrw.batch.Batch;
import de.morknet.mrw.batch.ProcessingInProgressException;
import de.morknet.mrw.comm.Command;
import de.morknet.mrw.comm.MrwMessage;
import de.morknet.mrw.comm.SignalCode;
import de.morknet.mrw.util.LogUtil;

/**
 * Diese Klasse repräsentiert einen einzelnen Gleisabschnitt. Dieser Abschnitt kann über einen Gleisbesetztmelder
 * als belegt oder frei markiert werden. Der Abschnitt besteht aus Gleisteilen und Signalen und gehört direkt
 * zu einer Betriebsgruppe. Da Gleisbesetztmeldungen einem Abschnitt direkt zugeordnet werden können, ist
 * ein Abschnitt so auch eine {@link DeviceUnit}.
 * @see Gruppe
 * @see Gleisteil
 * @author smork
 *
 */
public class Abschnitt extends DeviceUnit
{
	private static final long serialVersionUID = 1L;

	/**
	 * Das Modell zu dem dieser Abschnitt gehört. 
	 */
	private final Modell model;
	
	/**
	 * Die Betriebsgruppe, zu der dieser Abschnitt zählt.
	 */
	private final Gruppe group;
	
	/**
	 * Liste aller Gleisteile in diesem Abschnitt.
	 */
	private final HashMap<String, Gleisteil> trackElements = new HashMap<String, Gleisteil>();
	
	/**
	 * Gibt an, ob dieser Gleisabschnitt belegt ist.
	 */
	volatile private boolean occupation = false;
	
	/**
	 * Das Startgleis, von dem aus eine Fahrstraße geführt werden kann. Kann daher nur ein Gleis sein,
	 * keine Verzweigung.
	 */
	private Gleis start = null;
	
	/**
	 * Dieses Flag gibt den Einschaltzustand dieses Gleisabschnitts wider.
	 */
	volatile private boolean enabled = false;

	/**
	 * Gibt an, ob ein Hauptsignal in Zählrichtung vorhanden ist.
	 */
	private Hauptsignal forwardMainSignal = null;
	
	/**
	 * Gibt an, ob ein Hauptsignal gegen die Zählrichtung vorhanden ist.
	 */
	private Hauptsignal backwardMainSignal = null;
	
	/**
	 * Flag, ob in diesem Abschnitt eine Verzweigung enthalten ist.
	 */
	private boolean hasSwitch = false;

	/**
	 * Liste der Signale die gegen die Zählrichtung zeigen.
	 */
	private final LinkedList<Signal> backwardSignals = new LinkedList<Signal>();
	
	/**
	 * Liste der Signale die in Zählrichtung zeigen.
	 */
	private final LinkedList<Signal> forwardSignals = new LinkedList<Signal>();

	/**
	 * Die Abschnittskennung.
	 */
	private final String number;
	
	/**
	 * Dieser Konstruktur initialisiert einen Gleisabschnitt.
	 * @param modell Die Modelleisenbahn.
	 * @param gruppe Die Betriebsgruppe.
	 * @param number Die Abschnittsnummer.
	 */
	public Abschnitt(Modell modell, Gruppe gruppe, String number)
	{
		super(modell, "Abschnitt " + number);
		this.group = gruppe;
		this.number = number;
		this.model = modell;
	}

	/**
	 * Diese Methode gibt den Namen ohne dem vorangestellten &quot;Abschnitt &quot; zurück
	 * @return Die reine Abschnittsnummer.
	 */
	public String getNumber()
	{
		return this.number;
	}

	/**
	 * Gibt das Modell dieses Abschnitts zurück.
	 * @return Das Modell dieser Anlage.
	 */
	public Modell getModell()
	{
		return this.model;
	}

	/**
	 * Fügt ein Gleisteil diesem Abschnitt hinzu.
	 * @param g Das hinzuzufügende Gleisteil.
	 */
	public void add(Gleisteil g)
	{
		trackElements.put(g.getName(), g);
		hasSwitch |= (g instanceof Verzweigung);
	}

	/**
	 * Fügt ein Signal diesem Abschnitt hinzu.
	 * @param s Das hinzuzufügende Signal.
	 */
	public void add(Signal s)
	{
		if (s.isDirection(true))
		{
			if (s instanceof Vorsignal)
			{
				forwardSignals.addFirst(s);
			}
			else
			{
				if (s instanceof Hauptsignal)
				{
					forwardMainSignal = (Hauptsignal)s;
				}
				forwardSignals.addLast(s);
			}
		}
		else
		{
			if (s instanceof Vorsignal)
			{
				backwardSignals.addFirst(s);
			}
			else
			{
				if (s instanceof Hauptsignal)
				{
					backwardMainSignal = (Hauptsignal)s;
				}
				backwardSignals.addLast(s);
			}
		}
	}

	/**
	 * Gibt zurück, ob der Abschnitt belegt ist.
	 * @return Gleisbelegung im Abschnitt.
	 */
	synchronized public final boolean isOccupied()
	{
		return this.occupation;
	}

	/**
	 * Gibt zurück, ob der Abschnitt frei ist.
	 * @return Gleisbelegung im Abschnitt.
	 */
	synchronized public final boolean isFree()
	{
		return !this.occupation;
	}

	/**
	 * Setzt das Belegt-Flag dieses Abschnitts.
	 * @param occupation Flag, das die Belegung dieses Abschnitts anzeigt.
	 */
	synchronized public final void setOccupation(final boolean occupation)
	{
		this.occupation = occupation;
	}
	
	/**
	 * Markiert diesen Abschnitt als besetzt.
	 */
	public void occupy()
	{
		setOccupation(true);
	}

	/**
	 * Markiert diesen Abschnitt als frei.
	 */
	public void free()
	{
		setOccupation(false);
	}

	/**
	 * Diese Methode definiert das Startgleis für diesen Gleisabschnitt. Es wird für die
	 * Fahrstraßenplanung eingesetzt.
	 * @param gleis Das Startgleis.
	 * @throws StartRailDefinedException Falls schon ein Startgleis definiert wurde.
	 */
	public void setStartTrack(Gleis gleis)
	{
		if (this.start != null)
		{
			throw new StartRailDefinedException(this, start, gleis);
		}
		this.start = gleis;
	}
	
	/**
	 * Diese Methode gibt das Startgleis für diesen Gleisabschnitt zurück. Es wird für die
	 * Fahrstraßenplanung eingesetzt.
	 * @return Das Startgleis für die Fahrstraßenplanung.
	 */
	public Gleis getStartTrack()
	{
		return start;
	}

	/**
	 * Gibt die Liste der zur Fahrtrichtung passenden Signale zurück. 
	 * @param direction Die Zählrichtungsangabe.
	 * @return Liste der Signale.
	 */
	public Collection<Signal> getSignals(boolean direction)
	{
		
		return direction ? forwardSignals : backwardSignals;
	}

	/**
	 * Diese Methode ermittelt ein in der angegebenen Fahrtrichtung stehendes Hauptsignal in
	 * diesem Abschnitt. 
	 * @param direction Die Fahrtrichtung.
	 * @return  Das Hauptsignals in Fahrtrichtung.
	 */
	public Hauptsignal getMainSignal(final boolean direction)
	{
		return direction ? forwardMainSignal : backwardMainSignal;
	}

	/**
	 * Diese Methode ermittelt, ob in der angegebenen Fahrtrichtung ein Hauptsignal in
	 * diesem Abschnitt vorhanden ist. 
	 * @param direction Die Fahrtrichtung.
	 * @return  Die Existenz eines Hauptsignals in Fahrtrichtung.
	 */
	public boolean hasMainSignal(boolean direction)
	{
		return getMainSignal(direction) != null;
	}

	/**
	 * Diese Methode ermittelt, ob in diesem Abschnitt ein Fahrtrichtung ein Hauptsignal
	 * vorhanden ist und im Gleisabschnitt eine Weiche vorhanden ist.
	 * @param direction Fahrtrichtung.
	 * @return Flag, ob Hauptsignal und Weiche in Fahrtrichtung vorhanden ist.
	 */
	public boolean hasMainSignalWithSwitch(boolean direction)
	{
		return hasMainSignal(direction) && this.hasSwitch;
	}

	/**
	 * Diese Methode schaltet diesen Gleisabschnitt ein. Es wird nur ein Einschalt-Kommando in den übergebenen
	 * Batch eingereiht, wenn der Gleisabschnitt vorher ausgeschaltet war. Für deb Fall, dass eine Antwort
	 * auf ein vorstehendes Einschaltkommando aussteht, wird eine Exception geworfen.
	 * @param batch Der Batch, in den das Einschaltkommando eingereiht werden soll.
	 * @throws ProcessingInProgressException Falls ein Schaltvorgang schon läuft.
	 */
	public void on(Batch batch)
	{
		if (isDisabled())
		{
			if (!isProcessing())
			{
				this.enabled = true;
				addCommand(batch);
			}
			else
			{
				throw new ProcessingInProgressException(this);
			}
		}
	}

	/**
	 * Diese Methode schaltet den Abschnitt aus. Ein weiteres Flag bestimmt, ob die Signale
	 * ebenfalls ausgeschaltet werden sollen. 
	 * @param batch Der Batch, der die Kommandos enthält.
	 * @param turnSignals Flag, ob Signale ebenfalls ausgeschaltet werden sollen.
	 */
	public void off(Batch batch, boolean turnSignals)
	{
		this.enabled = false;
		
		if (turnSignals)
		{
			// Schalte alle Signale auf Hp0/Sh0/Vr0
			for (Signal s : forwardSignals)
			{
				if ((s instanceof Vorsignal) && (forwardMainSignal != null))
				{
					s.setSignal(SignalCommand.OFF, true);
				}
				else
				{
					s.setSignal(SignalCommand.S0, true);
				}
				s.addCommand(batch);
				s.setState();
			}
			for (Signal s : backwardSignals)
			{
				if ((s instanceof Vorsignal) && (backwardMainSignal != null))
				{
					s.setSignal(SignalCommand.OFF, true);
				}
				else
				{
					s.setSignal(SignalCommand.S0, true);
				}
				s.addCommand(batch);
				s.setState();
			}
		}
		addCommand(batch);
	}

	/**
	 * Diese Methode ermittelt, ob ein evtl. existierendes Hauptsignal nicht auf Hp0 steht.
	 * Dies wird zur Ermittlung eines Zwischenzustandes bei kombinierten Vorsignalen
	 * gebraucht.
	 * @param direction Die Fahrtrichtung
	 * @return Ob ein in der Fahrtrichtung angegebenes Hauptsignal nicht auf Hp0 steht.
	 */
	private boolean isMainSignalH0(final boolean direction)
	{
		Hauptsignal main = getMainSignal(direction);
		boolean     result = main != null;
		
		if (main != null)
		{
			result = main.getSignalState() != SignalCode.SIGNAL_HP0;
		}
		return result;
	}
	
	/**
	 * Diese Methode schaltet im Falle eines kombinierten Licht vor- und Hauptsignals das Vorsignal auf Vr0.
	 * Der angegebene Batch ist ein Kosmetikbatch, der vor dem Dunkeltasten des Vorsignals kurz Vr0 anzeigen soll.
	 * @param batch Der Kosmetik-Batch.
	 * @param direction Die Fahrtrichtung.
	 */
	public void preSignalOff(final Batch batch, final boolean direction)
	{
		for (Signal s : (direction ? forwardSignals : backwardSignals))
		{
			if ((s instanceof Vorsignal) && isMainSignalH0(direction) && (!(s instanceof Formsignal)))
			{
				s.setSignal(SignalCommand.S0, true);
				s.addCommand(batch);
				s.setState();
			}
		}
		if (!batch.isEmpty())
		{
			batch.setDelay(250L);
		}
	}

	/**
	 * Diese Methode schaltet richtungsgebunden diesen Abschnitt aus. Es werden nur Signale der betreffenden Richtung ausgeschaltet. 
	 * @param batch Der Batch, in den die Kommandos gelistet werden.
	 * @param direction Die Fahrtrichtung.
	 */
	public void directedOff(final Batch batch, final boolean direction)
	{
		final List<Signal> signals;
		final Hauptsignal  mainSignal;

		this.enabled = false;
		if (direction)
		{
			signals    = forwardSignals;
			mainSignal = forwardMainSignal;
		}
		else
		{
			signals    = backwardSignals;
			mainSignal = backwardMainSignal;
		}

		// Schalte alle Signale auf Hp0/Sh0/Vr0
		for (Signal s : signals)
		{
			s.setSignal((s instanceof Vorsignal) && (mainSignal != null) ?
					SignalCommand.OFF :
					SignalCommand.S0, true);
			s.addCommand(batch);
			s.setState();
		}
		addCommand(batch);
	}
	
	/**
	 * Diese Methode gibt zurück, ob dieser Abschnitt aktiviert ist und somit Strom führt.
	 * @return Der Betriebszustand des Abschnitts.
	 */
	public boolean isEnabled()
	{
		return this.enabled;
	}
	
	/**
	 * Diese Methode gibt zurück, ob dieser Abschnitt deaktiviert ist und somit kein Strom führt.
	 * @return Der Betriebszustand des Abschnitts.
	 */
	public boolean isDisabled()
	{
		return !this.enabled;
	}

	/**
	 * Diese Methode gibt die Betriebsgruppe zurück zu der dieser Abschnitt gehört.
	 * @return Die Betriebsgruppe dieses Abschnitts.
	 */
	public Gruppe getGroup()
	{
		return group;
	}
	
	/**
	 * Diese Methode gibt eine Sammlung der in diesem Abschnitt enthaltenen Gleisteile zurück.
	 * @return Die Sammlung der Gleisteile dieses Abschnitts.
	 */
	public Collection<Gleisteil> getTrackElements()
	{
		return trackElements.values();
	}
	
	@Override
	protected Command getCommand()
	{
		return isEnabled() ? Command.SETRON : Command.SETROF;
	}

	/**
	 * Diese Methode löst eine Mikrocontroller-ID/Gerätenummer in einen Gleisabschnitt auf.
	 * @param id Die Mikrocontroller-ID kombiniert mit der Grätenummer.
	 * @return Der gefundene Gleisabschnitt.
	 */
	public static Abschnitt findSegment(final int id)
	{
		return (Abschnitt)findDeviceUnit(id);
	}

	/**
	 * Erzeugt eine Abfragemeldung für diesen Abschnitt zurück. 
	 * @return Die Abfrage-Meldung einer Gleisbesetztmeldung. 
	 */
	public MrwMessage createQuestionMsg()
	{
		return MrwMessage.createCommandMsg(Command.GETRBS, ctrl_id, unit_no);
	}

	/**
	 * Gibt eine Konfigurationsmeldung für diese DeviceUnit zurück.
	 * @return Die Konfigurationsmeldung samt Daten bzgl. dieses Abschnitts.
	 */
	@Override
	public MrwMessage createConfigMessage() {
		MrwMessage msg = MrwMessage.createCommandMsg(Command.CFGRAI, ctrl_id, unit_no);
		
		msg.addDataByte(pin);
		msg.addDataByte(pin ^ 7);
		return msg;
	}

	/**
	 * Testet, ob es einen Prellbock in diesem Abschnitt gibt, der in die angegebene 
	 * Richtung bzgl. der Zählrichtung zeigt.
	 * @param inDirection
	 * @return Ob in der angegebenen Fahrtrichtung ein Prellbock steht.
	 */
	public boolean isStumpf(boolean inDirection)
	{
		Gleis g = getStartTrack();
		
		if (g != null)
		{
			return g.isStump(inDirection);
		}
		return false;
	}
	
	/**
	 * Diese Methode ermittelt den Nachbarabschnitt in Fahrtrichtung.
	 * @param inDirection Die Fahrtrichtung.
	 * @return Der Nachbarabschnitt.
	 */
	public Abschnitt getMainSegment(boolean inDirection)
	{
		Abschnitt result;
		Gleis g = getStartTrack();
		if (g != null)
		{
			List<Gleisteil> rGleise = g.getRouting(!inDirection);
			if (rGleise.size() != 1)
			{
				throw new NoNeighbourFoundException(this);
			}
			return rGleise.get(0).segment;
		}
		else
		{
			result = null;
		}
		return result;
	}

	/**
	 * Diese Methode gibt die Beschreibung dieses Gleisabschnitts im Klartext aus.
	 * @return Der selbsterklärende Klartext dieses Gleisabschnitts.
	 */
	public String toString()
	{
		return LogUtil.printf("[%s] %s %s",
				getName(),isOccupied() ? "belegt" : "frei",isEnabled() ? "ein" : "aus");
	}
	
	/**
	 * Dieser Komparator vergleicht die Namen zweier Gleisabschnitte.
	 * @author sm
	 *
	 */
	public static class AbschnittComparator implements Comparator<Abschnitt>, Serializable
	{
		private static final long serialVersionUID = 1L;

		/**
		 * Diese Methode vergleicht die Namen zweier Gleisabschnitte.
		 * @param seg0 Der erste zu vergleichende Gleisabschnitt.
		 * @param seg1 Der erste zu vergleichende Gleisabschnitt.
		 * @return Ein Integerwert, der die Größenverhältnisse widergibt.
		 */
		public int compare(Abschnitt seg0, Abschnitt seg1)
		{
			return seg0.getName().compareTo(seg1.getName());
		}
	}
	
	/**
	 * Dieser Comparator vergleicht die Namen zweier Abschnitte.
	 */
	public final static AbschnittComparator NAME_COMPARATOR = new Abschnitt.AbschnittComparator();
}
