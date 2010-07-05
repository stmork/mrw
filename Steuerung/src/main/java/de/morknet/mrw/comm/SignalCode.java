/*
**
**	$Filename:	SignalCode.java $
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

package de.morknet.mrw.comm;

import java.util.HashMap;
import java.util.Map;

/**
 * Diese Enum stellt Konstanten für Signalbilder zur Verfügung.
 * @author smork
 *
 */
public enum SignalCode
{
	/**
	 * Alle Lichter des Signals sind aus.
	 */
	SIGNAL_OFF(0),

	/**
	 * Das Signal zeigt Hp0 (Halt)
	 */
	SIGNAL_HP0(1),

	/**
	 * Das Signal zeigt Hp1 (Freie Fahrt)
	 */
	SIGNAL_HP1(2),

	/**
	 * Das Signal zeigt Hp2 (Langsamfahrt)
	 */
	SIGNAL_HP2(3),

	/**
	 * Das Signal zeigt Vr0 (Halt erwarten)
	 */
	SIGNAL_VR0(4),
	
	/**
	 * Das Signal zeigt Vr1 (Fahrt erwarten)
	 */
	SIGNAL_VR1(5),
	
	/**
	 * Das Signal zeigt Vr2 (Langsamfahrt erwarten)
	 */
	SIGNAL_VR2(6),
	
	/**
	 * Das Signal zeigt Sh0 (Gleissperre)
	 */
	SIGNAL_SH0(7),
	
	/**
	 * Das Signal zeigt Sh1 (Gleissperre aufgehoben)
	 */
	SIGNAL_SH1(8),
	
	/**
	 * Das Signal ist im Testmodus (Alle Lichter leuchten)
	 */
	SIGNAL_TST(9);

	private final int    code;
	private final String shortCode;
	private final static Map<Integer, SignalCode> map = new HashMap<Integer, SignalCode>();

	static
	{
		for(SignalCode sc :SignalCode.values())
		{
			map.put(sc.getSignalCode(), sc);
		}
	}

	private SignalCode(int code)
	{
		this.code = code;
		this.shortCode = toString().replaceFirst("SIGNAL_", "");
	}

	/**
	 * Diese Methode gibt zum Signal Code den Integer-Wert für die {@link MrwMessage} wieder.
	 * @return Der Integer-Wert für eine {@link MrwMessage}.
	 */
	public int getSignalCode()
	{
		return code;
	}

	/**
	 * Diese Methode wandelt die Integer-Variante dieses Codes aus einer {@link MrwMessage} in eine Enum-Instanz um. 
	 * @param code Der Integer-Wert
	 * @return Der resultierende Signal Code als enum.
	 * @throws CodeNotFoundException Wenn zum Code keine Enum-Instanz gefunden wurde.
	 */
	public static SignalCode getSignalCode(int code)
	{
		SignalCode result = map.get(code);

		if (result == null)
		{
			throw new CodeNotFoundException(SignalCode.class, code);
		}
		return result;
	}

	/**
	 * Diese Methode gibt den Signal Code als reinen Text des Signalbildes zurück.
	 * @return Das Signalbild als Klartext.
	 */
	public String getShortCode()
	{
		return shortCode;
	}
}
