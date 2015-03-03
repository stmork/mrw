/*
**
**	$Filename:	SensorCode.java $
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

package de.morknet.mrw.comm;

import java.util.HashMap;
import java.util.Map;

/**
 * Diese enum listet Sensortypen auf.
 * @author smork
 *
 */
public enum SensorCode
{
	SENSOR_LIGHT(1),
	SENSOR_TEMP(2);
	
	private final int sensorCode;
	private final static Map<Integer, SensorCode> map = new HashMap<Integer, SensorCode>();

	static
	{
		for(SensorCode code : SensorCode.values())
		{
			map.put(code.getSensorCode(), code);
		}
	}

	private SensorCode(int code)
	{
		this.sensorCode = code;
	}

	/**
	 * Diese Methode gibt zum Sensortypes den Integer-Wert für die {@link MrwMessage} wieder.
	 * @return Der Integer-Wert für eine {@link MrwMessage}.
	 */
	public int getSensorCode()
	{
		return this.sensorCode;
	}
	
	/**
	 * Diese Methode wandelt die Integer-Variante des Sensortypes aus einer {@link MrwMessage} in eine Enum-Instanz um. 
	 * @param code Der Rückmelde-Wert als Integer
	 * @return Der resultierende Sensortypes als enum.
	 * @throws CodeNotFoundException Wenn zum Sensortypes keine Enum-Instanz gefunden wurde.
	 */
	public static SensorCode getSensorCode(int code)
	{
		SensorCode result = map.get(code);
		
		if (result == null)
		{
			throw new CodeNotFoundException(SensorCode.class, code);
		}
		return result;
	}
}
