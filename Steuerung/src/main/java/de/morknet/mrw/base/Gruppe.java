/*
**
**	$Filename:	Gruppe.java $
**	$Revision: 931 $
**	$Date: 2010-04-14 10:39:15 +0200 (Mi, 14. Apr 2010) $
**	$Author: smork $
**	$Id: Gruppe.java 931 2010-04-14 08:39:15Z smork $
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
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.morknet.mrw.Modell;
import de.morknet.mrw.Route;

/**
 * Diese Klasse repräsentiert eine Betriebsgruppe einer Eisenbahn. Eine Betriebsgruppe kann eine freie Strecke
 * oder ein Bahnhof sein.
 * @author smork
 *
 */
public class Gruppe extends NamedElement
{
	private static final Log log = LogFactory.getLog(Gruppe.class);
	private static final long serialVersionUID = 1L;

	private boolean invertedDirection = false;

	@Override
	public String getLayoutInfo()
	{
		return Boolean.toString(invertedDirection);
	}

	@Override
	public void parseLayoutInfo(final String info)
	{
		invertedDirection = Boolean.parseBoolean(info);
	}

	private final HashMap<String, Abschnitt> segments      = new HashMap<String, Abschnitt>();
	private final HashMap<String, Gleisteil> trackElements = new HashMap<String, Gleisteil>();
	private final HashMap<String, Signal>    signals       = new HashMap<String, Signal>();
	private final ArrayList<Abschnitt>       mainSegments  = new ArrayList<Abschnitt>();

	/**
	 * Dieser Konstruktur initialisiert diese Betriebsgruppe.
	 * @param modell Die Modelleisenbahn.
	 * @param name Der Name der Betriebsgruppe.
	 */
	public Gruppe(final Modell modell, final String name)
	{
		super(name);
	}

	/**
	 * Diese Methode ermittelt, ob die GUI-Darstellung entgegen der Zählrichtung dargestellt werden soll.
	 * @return Die Zählrichtung
	 */
	public boolean isInvertedDirection()
	{
		return invertedDirection;
	}

	/**
	 * Diese Methode verbindet einen Gleisabschnitt mit dieser Betriebsgruppe
	 * @param segment Der zu verbindende Gleisabschnitt.
	 */
	public void add(final Abschnitt segment)
	{
		segments.put(segment.getName(), segment);
		for (Gleisteil gt : segment.getTrackElements())
		{
			trackElements.put(gt.getName(), gt);
		}
	}

	/**
	 * Diese Methode bereitet die internen Listen für die allgemeine Verwendung vor. 
	 */
	public void prepare()
	{
		for (Abschnitt a : segments.values())
		{
			for (Gleisteil gt : a.getTrackElements())
			{
				trackElements.put(gt.getName(), gt);
				if (gt instanceof Gleis)
				{
					Gleis gleis = (Gleis)gt;
					if (gleis.isMainTrack() && (!gleis.isStumpTrack()))
					{
						mainSegments.add(gleis.segment);
					}
				}
			}

			for(Signal s : a.getSignals(true))
			{
				signals.put(s.getName(), s);
			}
			for(Signal s : a.getSignals(false))
			{
				signals.put(s.getName(), s);
			}
		}
		Collections.sort(mainSegments, Abschnitt.NAME_COMPARATOR);
	}

	/**
	 * Diese Methode gibt die Sammlung aller Signale zurück.
	 * @return Die Sammlung aller Signale.
	 */
	public Collection<Signal> getAllSignals()
	{
		return signals.values();
	}

	/**
	 * Diese Methode gibt die Sammlung aller Gleisteile zurück.
	 * @return Die Sammlung aller Gleisteile.
	 */
	public Collection<Gleisteil> getTrackElements()
	{
		return trackElements.values();
	}
	
	/**
	 * Diese Methode gibt die Sammlung aller Gleisabschnitte zurück. 
	 * @return Die Sammlung aller Gleisabschnitte.
	 */
	public Collection<Abschnitt> getSegments()
	{
		return segments.values();
	}
	
	/**
	 * Gibt die Liste von Hauptgleisen einer Betriebsgruppe zurück.
	 * @return Liste von Abschnitten der Hauptgleise.
	 */
	public List<Abschnitt> getMainSegments()
	{
		return mainSegments;
	}

	/**
	 * Gibt eine Liste von Abschnitten zurück, die gewissen Kriterien gehorcht.
	 * @param occupied Abschnitt muss diesem Flag entsprechen.
	 * @param inDirection Art der Richtung relativ zur Zählrichtung.
	 * @return Liste von Abschnitten.
	 */
	public List<Abschnitt> getListOfDirectedSegments(final boolean occupied, final boolean inDirection)
	{
		log.debug(">getListOfDirectedSegments(" + occupied + ", " + inDirection + ")");
		List<Abschnitt> list = new ArrayList<Abschnitt>();
		
		for (Abschnitt abschnitt : getMainSegments())
		{
			log.debug("Hauptabschnitt: " + abschnitt.getName());
			Gleis g = abschnitt.getStartTrack();
			List<Gleisteil> vGleise = g.getRouting(inDirection);
			List<Gleisteil> rGleise = g.getRouting(!inDirection);
			
			if ((vGleise.size() > 0) && (rGleise.size() > 0))
			{
				if (occupied)
				{
					/*
					 * Wird nach besetzt gefragt, muss nur in Ausfahrtrichtung der
					 * Ausfahrabschnitt belegt sein.
					 */
					Abschnitt nachbar = vGleise.get(0).segment;
					log.debug("Nachbar: " + nachbar.getName());
					if (nachbar.isOccupied())
					{
						list.add(nachbar);
					}
				}
				else
				{
					/*
					 * Wird nach frei gefragt, muss das Hauptgleis und die beiden
					 * benachbarten Ausfahrgleise frei sein. Außerdem dürfen diese
					 * Abschnitte keiner Fahrstraße zugeordnet sein. 
					 */
					Abschnitt vNachbar = vGleise.get(0).segment;
					Abschnitt rNachbar = rGleise.get(0).segment;
					log.debug("Nachbar vorwärts: " + vNachbar.getName());
					log.debug("Nachbar rückwärts: " + rNachbar.getName());
					if ((!vNachbar.isOccupied()) && (!abschnitt.isOccupied()) && (!rNachbar.isOccupied()) &&
							(Route.findRoute(rNachbar) == null) &&
							(Route.findRoute(abschnitt) == null) &&
							(Route.findRoute(vNachbar) == null))
					{
						list.add(vNachbar);
					}
				}
			}
		}
		log.debug("<getListOfDirectedSegments(" + occupied + ", " + inDirection + ") = " + list.size());
		return list;
	}
}
