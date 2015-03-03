/*
**
**	$Filename:	NameUtil.java $
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

package de.morknet.mrw.util;

import de.morknet.mrw.Route;
import de.morknet.mrw.base.Signal;

/**
 * Diese Hilfsklasse bereitet Texte auf.
 * @author smork
 *
 */
public class NameUtil
{
	/**
	 * Gibt die Fahrtart einer Fahrstraße im Klartext aus. 
	 * @param shunting Ob rangiert wird.
	 * @param direction In welche Richtung relativ zur Zählrichtung gefahren wird.
	 * @return Die Fahrtart im Klartext.
	 */
	public final static String logRouteType(final boolean shunting, final boolean direction)
	{
		return (shunting ? "Rangieren " : "Fahrt ") + (direction ? "re." : "li.");
	}

	/**
	 * Gibt die Fahrtart einer Fahrstraße im Klartext aus. 
	 * @param route Die Fahrstraße selbst.
	 * @return Die Fahrtart im Klartext.
	 */
	public final static String logRouteType(final Route route)
	{
		return logRouteType(route.isShunting(), route.getDirection());
	}

	/**
	 * Gibt den reinen Namen eines Signals zurück.
	 * @param s Das Signal mit dem entsprechenden Signalname.
	 * @return Der Signalname ohne seine Betriebsgruppe.
	 */
	public final static String getNumber(final Signal s)
	{
		String grpName = s.getSegment().getGroup().getName();
		return s.getName().substring(grpName.length() + 1);
	}

	/**
	 * Diese Methode gibt einen formatierten String einer CAN-Message ID zurück.
	 * @param id Die zu formatierende CAN-Message ID.
	 * @return Die formatierte CAN-Message ID.
	 */
	public final static String getIdString(final int id)
	{
		final StringBuilder buffer = new StringBuilder();
		
		buffer.
			append(LogUtil.padLeft(Integer.toString((id >> 16) & 0xffff, 16), 4, '0')).append(':').
			append(LogUtil.padLeft(Integer.toString( id        & 0xffff, 16), 4, '0'));
		
		return buffer.toString();
	}
	
	/**
	 * Diese Methode wandelt einen Text in einen ID-String um. Whitespaces werden in einen Punkt "." umgewandelt.
	 * Großbuchstaben werden in einen Kleinbuchstaben gewandelt und es wird ein Punkt vorangestellt. Ansonsten
	 * werden nur Buchstaben und Ziffern übernommen.
	 * @param name Der umzuwandelnde Text.
	 * @return Der umgewandelte ID-String.
	 */
	public final static String getRegistryIdString(final String name)
	{
		StringBuilder buffer = new StringBuilder();
		
		for (char c : name.toCharArray())
		{
			if (Character.isUpperCase(c) || Character.isWhitespace(c))
			{
				if (buffer.length() > 0)
				{
					if (buffer.charAt(buffer.length() - 1) != '.')
					{
						buffer.append(".");
					}
				}
			}
			if (Character.isLetterOrDigit(c))
			{
				switch(c)
				{
				case 'ä':
				case 'Ä':
					buffer.append("ae");
					break;
				case 'ö':
				case 'Ö':
					buffer.append("oe");
					break;
				case 'ü':
				case 'Ü':
					buffer.append("ue");
					break;
				case 'ß':
					buffer.append("ss");
					break;
				default:
					buffer.append(Character.toLowerCase(c));
					break;
				}
			}
		}
		return buffer.toString();
	}
}
