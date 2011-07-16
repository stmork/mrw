/*
**
**	$Filename:	DirectionCode.java $
**	$Revision$
**	$Date$
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

import java.util.HashMap;
import java.util.Map;

import de.morknet.mrw.comm.CodeNotFoundException;

/**
 * Diese Enum repräsentiert die Richtungs-Codes auf das GETDIR-Kommando.
 * @author smork
 *
 */
public enum DirectionCode
{
	/**
	 * Weiche auf links.
	 */
	LEFT(1),

	/**
	 * Weiche auf rechts.
	 */
	RIGHT(2),

	/**
	 * DKW auf Bogen.
	 */
	ARC(1),

	/**
	 * DKW auf Kreuz.
	 */
	CROSS(2);
	
	private final int code;
	private final static Map<Integer, DirectionCode> map_switch = new HashMap<Integer, DirectionCode>();
	private final static Map<Integer, DirectionCode> map_dks    = new HashMap<Integer, DirectionCode>();

	static
	{
		add(LEFT,  map_switch);
		add(RIGHT, map_switch);
		add(ARC,   map_dks);
		add(CROSS, map_dks);
	}

	private static void add(DirectionCode oc, Map<Integer, DirectionCode> map)
	{
		map.put(oc.getDirectionCode(), oc);
	}

	private DirectionCode(int code)
	{
		this.code = code;
	}
	
	/**
	 * Diese Methode gibt den Richtungs-Code als Integer zurück, so wie er von den Microcontrollern gemeldet wird.
	 * @return Der Richtungs-Code.
	 */
	public int getDirectionCode()
	{
		return this.code;
	}

	/**
	 * Diese Methode wandelt die Integer-Variante eines Richtungscodes in eine Enum-Instanz um. 
	 * @param branch Die verwendete Verzweigung.
	 * @param code Der Integer-Wert
	 * @return Der resultierende Richtungs-Code als enum.
	 * @see Weiche
	 * @see DKW
	 */
	public static DirectionCode getDirectionCode(Verzweigung branch, int code)
	{
		DirectionCode result = null;
		
		if (branch instanceof Weiche)
		{
			result = map_switch.get(code);
		}
		else if (branch instanceof DKW)
		{
			result = map_dks.get(code);
		}

		if (result == null)
		{
			throw new CodeNotFoundException(DirectionCode.class, code);
		}
		return result;
	}
}
