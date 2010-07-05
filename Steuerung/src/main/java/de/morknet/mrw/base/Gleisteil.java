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
 * Diese abstrakte Klasse repr�sentiert ein Gleisteil. Ein Gleisteil kann sein:
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
	 * Der Gleisabschnitt zu dem dieses Gleisteil geh�rt.
	 */
	protected final Abschnitt segment;

	/**
	 * Flag, ob Gleisverbindung a in Z�hlrichtung zeigt.
	 */
	protected final boolean aIsHigh;

	/**
	 * Die Fahrstra�e zu der dieses Gleisteil geh�rt.
	 */
	volatile protected Route route = null;
	
	/**
	 * Die Liste der benachbarten Gleisteile in Z�hrichtung.
	 */
	protected final List<Gleisteil> forward  = new ArrayList<Gleisteil>(); 

	/**
	 * Die Liste der banachbarten Gleisteile entgegen Z�hlrichtung.
	 */
	protected final List<Gleisteil> backward = new ArrayList<Gleisteil>();

	/**
	 * Diese Methode �berpr�ft die benachbarten Gleisteile auf Plausibilit�t.
	 * @return Erfolg, wenn die Plausibilit�t in Ordnung ist.
	 */
	abstract public int validate();

	/**
	 * Diese Methode �berpr�ft, ob das angegebenene Gleisteil direkt mit diesem hier verbunden ist.
	 * @param gt Das auf Nachbarschaft zu �berpr�fende Gleisteil.
	 * @return True, falls das Gleisteil benachbart ist.
	 */
	abstract protected boolean hasTrackElement(Gleisteil gt);
	
	/**
	 * Diese Methode �berpr�ft, ob die Z�hlrichtung des angegebenen Gleisteils mit diesem hier �bereinstimmt.
	 * @param gt Das zu �berpr�fende Gleisteil.
	 * @param isDirection Die zu �berpr�fende Fahrtrichtung.
	 * @return Das Ergebnis.
	 */
	abstract protected boolean isDirectionCorrect(Gleisteil gt, boolean isDirection);
	
	/**
	 * Diese Methode ermittelt das Kommando, um eine Verzweigung in die richtige Lage abh�ngig von den benachbarten Gleisteilen
	 * zu bringen. Das resultierende Schaltkommando wird in den {@link Batch} eingetragen.
	 * @param batch Der {@link Batch} f�r den Schaltauftrag.
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
	 * Diese Methode gibt abh�ngig von der geforderten Gleisrichtung bzgl. der Z�hlrichtung
	 * die benachbarten Gleisteile zur�ck.
	 * @param direction Die geforderte Richtung relativ zur Z�hlrichtung.
	 * @return Die benachbarten Gleisteile relativ zur geforderten Z�hlrichtung.
	 */
	public List<Gleisteil> getRouting(final boolean direction)
	{
		return direction ? forward : backward;
	}
	
	/**
	 * Diese Methode gibt die Gleisbelegung zum dazugeh�rigen Gleisabschnitt zur�ck.
	 * @return Der Gleisbesetztzustand.
	 */
	public boolean isFree()
	{
		return !segment.isOccupied();
	}
	
	/** 
	 * Diese Methode gibt den Gleisabschnitt zu diesem Gleisabschnitt zur�ck.
	 * @return Der zu diesem Gleisteil geh�rende Gleisabschnitt.
	 */
	public Abschnitt getSegment()
	{
		return segment;
	}

	/** 
	 * Diese Methode gibt die Betriebsgruppe zu diesem Gleisabschnitt zur�ck.
	 * @return Der zu diesem Gleisteil geh�rende Betriebsgruppe.
	 */
	public Gruppe getGruppe()
	{
		return getSegment().getGroup();
	}

	/**
	 * Diese Methode gibt die zu diesem Gleisteil definierte Fahrstra�e zur�ck.
	 * @return Die Fahrstra�e, die dieses Gleisteil benutzt.
	 */
	public Route getRoute()
	{
		synchronized(this)
		{
			return this.route;
		}
	}

	/**
	 * Diese Methode setzt f�r dieses Gleisteil die dazugeh�rige Fahrstra�e. Wird
	 * die Fahrstra�e entfernt, wird automatisch dieses Gleisteil entriegelt.
	 * @param route Die zu setzende Fahrstra�e.
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
	 * Diese Methode gibt den Verriegelungszustand dieses Gleisteils zur�ck. Wenn das 
	 * Gleisteil verriegelt ist, ohne dass eine Fahrstra�e definiert wurde, wird eine 
	 * {@link LockWithoutRouteException} geworfen.
	 * @return Der Verriegelungszustand
	 * @throws LockWithoutRouteException Wenn dieses Gleisteil verriegelt ist, ohne dass 
	 * eine Fahrstra�e definiert wurde. In diesem Fall wird die Verriegelung aufgehoben.
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
	 * Diese Methode verriegelt dieses Gleisteil. Jede externe Zustands�nderung
	 * (z.B. manuelle Weichenschaltun) f�hrt zu einer Aufl�sung der Fahrstra�e.
	 * @throws LockWithoutRouteException Wenn versucht wird, dieses Gleisteil zu verriegeln
	 * ohne dass eine Fahrstra�e definiert war.
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
	 * Diese Methode gibt zur�ck, ob der Gleisverbindung "a" in Z�hlrichtung gerichtet ist.
	 * @return Die Z�hlrichtung an Gleisverbindung "a".
	 */
	public final boolean aIsHigh()
	{
		return aIsHigh;
	}
	
	/**
	 * Diese Methode gibt das Gleisteil als Klartext zur�ck.
	 * @return Der Klartext dieses Gleisteils.
	 */
	public String toString()
	{
		return "[" + getName() + "]";
	}
}
