/*
**
**	$Filename:	TourPoint.java $
**	$Revision: 931 $
**	$Date: 2010-04-14 10:39:15 +0200 (Mi, 14. Apr 2010) $
**	$Author: smork $
**	$Id: TourPoint.java 931 2010-04-14 08:39:15Z smork $
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

package de.morknet.mrw.automatic;

import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.morknet.mrw.Modell;
import de.morknet.mrw.ModellFactory;
import de.morknet.mrw.automatic.tourmode.TourMode;
import de.morknet.mrw.base.Abschnitt;
import de.morknet.mrw.base.Gleis;
import de.morknet.mrw.base.Gleisteil;
import de.morknet.mrw.base.Gruppe;
import de.morknet.mrw.base.Signal;
import de.morknet.mrw.util.LogUtil;

/**
 * Diese Klasse gibt Informationen �ber einen abzufahrenden Tourpunkt einer Fahrt.
 * @see TourMode
 * @author sm
 *
 */
public class TourPoint
{
	private final static Log             log      = LogFactory.getLog(TourPoint.class);
	private final        List<Abschnitt> segments = new LinkedList<Abschnitt>();
	private final static Modell          modell   = ModellFactory.getInstance();

	/**
	 * Dieser Konstruktur wertet eine textuelle Beschreibung der erlaubten Gleisabschnitte an diesem TourPoint aus. Die Beschreibung
	 * wird als erstes als Betriebsgruppe interpretiert. In diesem Falle werden alle Hauptgleisabschnitte diesem TourPoint zugeordnet.
	 * Konnte keine Betriebsgruppe unter der textuellen Beschreibung gefunden werden, wird die textuelle Beschreibung als
	 * kommaseparierte Liste aus Signalnamen interpretiert. Wird unter diesem Namen kein Signal gefunden, wird die Beschreibung als
	 * Gleisabschnittsname interpretiert.
	 * @param input Die textuelle Beschreibung der erlaubten Gleisabschnitte.
	 * @param direction Die Fahrtrichtung.
	 */
	public TourPoint(final String input, final boolean direction)
	{
		final Gruppe group = modell.findGroup(input);

		if (group != null)
		{
			for(Abschnitt a : group.getMainSegments())
			{
				final Gleis           g          = a.getStartTrack();
				final List<Gleisteil> neighbours = g.getRouting(direction);
				final Abschnitt       n          = neighbours.get(0).getSegment();
				
				segments.add(n);
			}
		}
		else
		{
			final StringTokenizer st = new StringTokenizer(input,",");
			
			do
			{
				final String    name = st.nextToken().trim();
				final Abschnitt a;
				final Signal    s = modell.findSignal(name);

				if (s != null)
				{
					a = s.getSegment();
				}
				else
				{
					a = modell.findSegment(name);
				}
		
				if (a != null)
				{
					segments.add(a);
				}
				else
				{
					throw new IllegalArgumentException(LogUtil.printf(
							"St�tzpunkt %s l�sst sich nicht in Gleisabschnitt aufl�sen!", name));
				}
			}
			while(st.hasMoreTokens());
		}
		if (segments.isEmpty())
		{
			throw new IllegalArgumentException(LogUtil.printf(
					"Es konnten keine Gleisabschnitte f�r diesen Tourabschnitt (%s) gefunden werden", input));
		}
		
		if (log.isTraceEnabled())
		{
			for (Abschnitt a : segments)
			{
				if (a.getSignals(direction).size() == 0)
				{
					log.warn(LogUtil.printf("  St�tzpunkt %s hat kein Hauptsignal!", a.getNumber()));
				}
			}
		}
	}

	/**
	 * Diese Methode gibt die erlaubten Gleisabschnitte an diesem TourPoint zur�ck. 
	 * @return Die Liste der an diesem TourPoint erlaubten Gleisabschnitte. 
	 */
	public List<Abschnitt> getSegments()
	{
		return segments;
	}
	
	public String toString()
	{
		return segments.get(0).getNumber();
	}
}
