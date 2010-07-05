/*
**
**	$Filename:	Gleisteil.java $
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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.morknet.mrw.LockWithoutRouteException;
import de.morknet.mrw.Route;
import de.morknet.mrw.batch.Batch;

/**
 * Diese abstrakte Klasse repräsentiert ein Gleisteil. Ein Gleisteil kann sein:
 * <ul>
 * <li>Ein Gleis
 * <li>Eine Verzweigung (also Weiche oder DKW)
 * <ul>
 * @author sm
 *
 */
abstract public class Gleisteil extends DeviceUnit
{
	private static final long serialVersionUID = 1L;
	private boolean locked = false;

	/**
	 * Das Log, in das Meldungen geschrieben werden.
	 */
	protected final Log log;
	
	/**
	 * Der Gleisabschnitt zu dem dieses Gleisteil gehört.
	 */
	protected final Abschnitt segment;

	/**
	 * Flag, ob Gleisverbindung a in Zählrichtung zeigt.
	 */
	protected final boolean aIsHigh;

	/**
	 * Die Fahrstraße zu der dieses Gleisteil gehört.
	 */
	volatile protected Route route = null;
	
	/**
	 * Die Liste der benachbarten Gleisteile in Zährichtung.
	 */
	protected final List<Gleisteil> forward  = new ArrayList<Gleisteil>(); 

	/**
	 * Die Liste der banachbarten Gleisteile entgegen Zählrichtung.
	 */
	protected final List<Gleisteil> backward = new ArrayList<Gleisteil>();

	/**
	 * Diese Methode überprüft die benachbarten Gleisteile auf Plausibilität.
	 * @return Erfolg, wenn die Plausibilität in Ordnung ist.
	 */
	abstract public int validate();

	/**
	 * Diese Methode überprüft, ob das angegebenene Gleisteil direkt mit diesem hier verbunden ist.
	 * @param gt Das auf Nachbarschaft zu überprüfende Gleisteil.
	 * @return True, falls das Gleisteil benachbart ist.
	 */
	abstract protected boolean hasTrackElement(Gleisteil gt);
	
	/**
	 * Diese Methode überprüft, ob die Zählrichtung des angegebenen Gleisteils mit diesem hier übereinstimmt.
	 * @param gt Das zu überprüfende Gleisteil.
	 * @param isDirection Die zu überprüfende Fahrtrichtung.
	 * @return Das Ergebnis.
	 */
	abstract protected boolean isDirectionCorrect(Gleisteil gt, boolean isDirection);
	
	/**
	 * Diese Methode ermittelt das Kommando, um eine Verzweigung in die richtige Lage abhängig von den benachbarten Gleisteilen
	 * zu bringen. Das resultierende Schaltkommando wird in den {@link Batch} eingetragen.
	 * @param batch Der {@link Batch} für den Schaltauftrag.
	 * @param prev Das vorhergehende Gleisteil.
	 * @param next Das nachfolgende Gleisteil.
	 */
	abstract public void turn(Batch batch, Gleisteil prev, Gleisteil next);
	
	/**
	 * Diese Methode ermittelt, ob es sich bei diesem Gleisteil um eine Verzweigung handelt.
	 * @return Flag, ob es eine Verzweigung ist.
	 */
	abstract public boolean isBranch();

	protected Gleisteil(final Abschnitt abschnitt, final String name, final boolean aIsHigh)
	{
		super(abschnitt.getModell(), name);
		this.segment = abschnitt;
		this.aIsHigh = aIsHigh;

		log = LogFactory.getLog(name);
	}

	/**
	 * Diese Methode gibt abhängig von der geforderten Gleisrichtung bzgl. der Zählrichtung
	 * die benachbarten Gleisteile zurück.
	 * @param direction Die geforderte Richtung relativ zur Zählrichtung.
	 * @return Die benachbarten Gleisteile relativ zur geforderten Zählrichtung.
	 */
	public List<Gleisteil> getRouting(final boolean direction)
	{
		return direction ? forward : backward;
	}
	
	/**
	 * Diese Methode gibt die Gleisbelegung zum dazugehörigen Gleisabschnitt zurück.
	 * @return Der Gleisbesetztzustand.
	 */
	public boolean isFree()
	{
		return !segment.isOccupied();
	}
	
	/** 
	 * Diese Methode gibt den Gleisabschnitt zu diesem Gleisabschnitt zurück.
	 * @return Der zu diesem Gleisteil gehörende Gleisabschnitt.
	 */
	public Abschnitt getSegment()
	{
		return segment;
	}

	/** 
	 * Diese Methode gibt die Betriebsgruppe zu diesem Gleisabschnitt zurück.
	 * @return Der zu diesem Gleisteil gehörende Betriebsgruppe.
	 */
	public Gruppe getGruppe()
	{
		return getSegment().getGroup();
	}

	/**
	 * Diese Methode gibt die zu diesem Gleisteil definierte Fahrstraße zurück.
	 * @return Die Fahrstraße, die dieses Gleisteil benutzt.
	 */
	public Route getRoute()
	{
		synchronized(this)
		{
			return this.route;
		}
	}

	/**
	 * Diese Methode setzt für dieses Gleisteil die dazugehörige Fahrstraße. Wird
	 * die Fahrstraße entfernt, wird automatisch dieses Gleisteil entriegelt.
	 * @param route Die zu setzende Fahrstraße.
	 */
	public void setRoute(Route route)
	{
		synchronized(this)
		{
			this.route = route;
			if (route == null)
			{
				locked = false;
			}
		}
	}
	
	/**
	 * Diese Methode gibt den Verriegelungszustand dieses Gleisteils zurück. Wenn das 
	 * Gleisteil verriegelt ist, ohne dass eine Fahrstraße definiert wurde, wird eine 
	 * {@link LockWithoutRouteException} geworfen.
	 * @return Der Verriegelungszustand
	 * @throws LockWithoutRouteException Wenn dieses Gleisteil verriegelt ist, ohne dass 
	 * eine Fahrstraße definiert wurde. In diesem Fall wird die Verriegelung aufgehoben.
	 */ 
	public boolean isLocked()
	{
		synchronized(this)
		{
			if ((route == null) && (locked))
			{
				locked = false;
				throw new LockWithoutRouteException(this);
			}
			return locked;
		}
	}

	/**
	 * Diese Methode verriegelt dieses Gleisteil. Jede externe Zustandsänderung
	 * (z.B. manuelle Weichenschaltun) führt zu einer Auflösung der Fahrstraße.
	 * @throws LockWithoutRouteException Wenn versucht wird, dieses Gleisteil zu verriegeln
	 * ohne dass eine Fahrstraße definiert war.
	 */
	public void lock()
	{
		synchronized(this)
		{
			if (route == null)
			{
				throw new LockWithoutRouteException(this);
			}
			locked = true;
		}
	}
	
	/**
	 * Diese Methode gibt zurück, ob der Gleisverbindung "a" in Zählrichtung gerichtet ist.
	 * @return Die Zählrichtung an Gleisverbindung "a".
	 */
	public final boolean aIsHigh()
	{
		return aIsHigh;
	}
	
	/**
	 * Diese Methode gibt das Gleisteil als Klartext zurück.
	 * @return Der Klartext dieses Gleisteils.
	 */
	public String toString()
	{
		return "[" + getName() + "]";
	}
}
