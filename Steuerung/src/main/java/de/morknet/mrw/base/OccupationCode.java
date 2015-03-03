/*
**
**	$Filename:	OccupationCode.java $
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

package de.morknet.mrw.base;

import java.util.HashMap;
import java.util.Map;

import de.morknet.mrw.comm.CodeNotFoundException;

/**
 * Dieses Interface repräsentiert die Antwort-Codes der Gleisbesetztmeldung
 * auf das GETRBS-Kommando.
 * @author smork
 *
 */
public enum OccupationCode
{
	/**
	 * Abschnitt frei.
	 */
	FREE(0),
	
	/**
	 * Abschnitt belegt.
	 */
	OCCUPIED(1);
	
	private final int code;
	private final static Map<Integer, OccupationCode> map = new HashMap<Integer, OccupationCode>();

	static
	{
		for(OccupationCode oc :OccupationCode.values())
		{
			map.put(oc.getOccupationCode(), oc);
		}
	}

	private OccupationCode(int code)
	{
		this.code = code;
	}
	
	/**
	 * Diese Methode gibt den Gleisbelegungscode als Integer zurück, so wie er von den Microcontrollern gemeldet wird.
	 * @return Der Gleisbelegungscode.
	 */
	public int getOccupationCode()
	{
		return this.code;
	}

	/**
	 * Diese Methode wandelt die Integer-Variante eines Gleisbelegungscodes in eine Enum-Instanz um. 
	 * @param code Der Integer-Wert
	 * @return Der resultierende Gleisbelegungs-Code als enum.
	 * @throws CodeNotFoundException Wenn zum Gleisbelegungscode keine Enum-Instanz gefunden wurde.
	 */
	public static OccupationCode getOccupationCode(int code)
	{
		OccupationCode result = map.get(code);

		if (result == null)
		{
			throw new CodeNotFoundException(OccupationCode.class, code);
		}
		return result;
	}
	
	/**
	 * Diese Methode wandelt einen gegebenen Code in einen Boolean um und
	 * ermittelt, ob er einem <quote>Gleis belegt</quote> entspricht.
	 * @param code Der auszuwertende Code.
	 * @return Ist Code gleichbedeutend mit Gleis belegt?
	 */
	public static boolean isOccupied(int code)
	{
		return code != FREE.code;
	}
	
	/**
	 * Diese Methode wandelt einen gegebenen Code in einen Boolean um und
	 * ermittelt, ob er einem <quote>Gleis frei</quote> entspricht.
	 * @param code Der auszuwertende Code.
	 * @return Ist Code gleichbedeutend mit Gleis frei?
	 */
	public static boolean isFree(int code)
	{
		return code == FREE.code;
	}
}
