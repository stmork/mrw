/*
**
**	$Filename:	ReferenceCounter.java $
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

package de.morknet.mrw.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Diese Klasse implementiert einen Referenzzähler, ohne übermäßig viele
 * Objekte zu verbrauchen, wie das bei java.lang.Integer nötig wäre, weil
 * jene Klasse imutable ist.
 * @author sm
 *
 * @param <T> Der Referenztyp
 */
public class ReferenceCounter<T> implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final HashMap<T, AtomicInteger> map = new HashMap<T, AtomicInteger>();

	/**
	 * Diese Methode zählt den Zähler für diese Referenz um eins hoch. Ist die Referenz
	 * noch nicht registriert, wird dies automatisch erledigt.
	 * 
	 * @param type Die zu zählende Referenz.
	 * @return Der gezählte Wert
	 */
	public int count(T type)
	{
		return count(type, 1);
	}

	/**
	 * Diese Methode zählt den Zähler für diese Referenz um eins hoch. Ist die Referenz
	 * noch nicht registriert, wird dies automatisch erledigt.
	 * 
	 * @param type Die zu zählende Referenz.
	 * @param value Der Wert, um den der Zähler hochgesetzt werden soll.
	 * @return Der gezählte Wert
	 */
	public int count(T type, int value)
	{
		AtomicInteger counter = map.get(type);
		if (counter == null)
		{
			counter = new AtomicInteger();
			map.put(type, counter);
		}
		return counter.addAndGet(value);
	}
	
	/**
	 * Diese Methode gibt den aktuellen Zählerstand zurück. Ist die Referenz nicht registriert,
	 * wird 0 zurückgegeben und die Referenz wird nicht registriert.
	 * 
	 * @param type Die gezählte Referenz.
	 * @return Der Zähler passend zur Referenz.
	 */
	public int getValue(T type)
	{
		AtomicInteger counter = map.get(type);
		
		return counter != null ? counter.get() : 0;
	}

	/**
	 * Diese Methode gibt einen Set aller registrierten Referenzen zurück.
	 * 
	 * @return Der Set aller registrierten Referenzen.
	 */
	public Set<T> keySet()
	{
		return map.keySet();
	}

	/**
	 * Diese Methode löscht die Zähler aller hier registrierten Referenzen. Die
	 * Referenzen bleiben erhalten.
	 */
	public void zero()
	{
		for (AtomicInteger counter : map.values())
		{
			counter.set(0);
		}
	}

	/**
	 * Diese Methode entfernt alle registrierten Referenzen.
	 */
	public void clear()
	{
		map.clear();
	}
}
