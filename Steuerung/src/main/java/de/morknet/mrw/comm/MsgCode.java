/*
**
**	$Filename:	MsgCode.java $
**	$Revision: 931 $
**	$Date: 2010-04-14 10:39:15 +0200 (Mi, 14. Apr 2010) $
**	$Author: smork $
**	$Id: MsgCode.java 931 2010-04-14 08:39:15Z smork $
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
 * Diese Enum listet Rückmelde-Codes auf.
 * @author smork
 *
 */
public enum MsgCode {
	/**
	 * Dieser Wert ist nur ein Flag, dass eine Meldung nicht gesendet werden soll.
	 */
	NO_RESULT(-1),

	/**
	 * Meldung OK.
	 */
	MSG_OK(0),

	/**
	 * Auftragsstapel ist voll.
	 */
	MSG_QUEUE_FULL(1),
	
	/**
	 * Kommando unbekannt.
	 */
	MSG_UNKNOWN_CMD(2),
	
	/**
	 * Kommando wartet schon auf Abarbeitung.
	 */
	MSG_PENDING(3),
	
	/**
	 * Kommando ignoriert.
	 */
	MSG_IGNORED(4),
	
	/**
	 * Kommando in Auftragsliste eingereiht.
	 */
	MSG_QUEUED(5),
	
	/**
	 * Controller ist noch nicht konfiguriert.
	 */
	MSG_NOT_CONFIGURED_YET(6),
	
	/**
	 * Kommando gehörte zu unbekanntem Gerät.
	 */
	MSG_NO_UNITNO_DEFINED(7),
	
	/**
	 * Das Kommando passt nicht zu einem Gerät.
	 */
	MSG_UNITTYPE_WRONG(8),
	
	/**
	 * Der Controller wartet auf ausgelösten Reset.
	 */
	MSG_RESET_PENDING(9),
	
	/**
	 * Kommando hat keine Gerätenummer.
	 */
	MSG_UNITNO_MISSING(10),
	
	/**
	 * Kommando kann Gerätenummer nicht zuordnen.
	 */
	MSG_UNIT_NOT_FOUND(11),
	
	/**
	 * Konfigurationskommando außerhalb vom Konfigmodus aufgerufen.
	 */
	MSG_NOT_IN_CONFIG_MODE(12),
	
	/**
	 * Controller ist frisch gebootet.
	 */
	MSG_BOOTED(13),
	
	/**
	 * Die Controller-ID wurde nicht geändert.
	 */
	MSG_ID_NOT_CHANGED(14),
	
	/**
	 * Prüfsummenfehler beim Flashen neuer Firmware.
	 */
	MSG_CHECKSUM_ERROR(15),
	
	/**
	 * Informationsantwort.
	 */
	MSG_INFO(16),
	
	/**
	 * Die Controller-ID wurde nicht geändert, weil der ID-Taster nicht gedrückt wurde.
	 */
	MSG_ID_CHANGE_DISABLED(17),
	
	/**
	 * Die Firmware passt nicht zur Hardware.
	 */
	MSG_HARDWARE_MISMATCH(18),
	
	/**
	 * Schaltfehler bei Weiche mit Endabschaltung.
	 */
	MSG_SWITCH_FAILED(19);

	private final int msgCode;
	private final static Map<Integer, MsgCode> map = new HashMap<Integer, MsgCode>();

	static
	{
		for(MsgCode code :MsgCode.values())
		{
			map.put(code.getMsgCode(), code);
		}
	}

	private MsgCode(int code)
	{
		this.msgCode = code;
	}

	/**
	 * Diese Methode gibt zum Rückmelde-Wert den Integer-Wert für die {@link MrwMessage} wieder.
	 * @return Der Integer-Wert für eine {@link MrwMessage}.
	 */
	public int getMsgCode()
	{
		return this.msgCode;
	}
	
	/**
	 * Diese Methode wandelt die Integer-Variante des Rückmelde-Codes aus einer {@link MrwMessage} in eine Enum-Instanz um. 
	 * @param code Der Rückmelde-Wert als Integer
	 * @return Der resultierende Rückmelde-Wert als enum.
	 * @throws CodeNotFoundException Wenn zum Rückmelde-Code keine Enum-Instanz gefunden wurde.
	 */
	public static MsgCode getMsgCode(int code)
	{
		MsgCode result = map.get(code);
		
		if (result == null)
		{
			throw new CodeNotFoundException(MsgCode.class, code);
		}
		return result;
	}
}
