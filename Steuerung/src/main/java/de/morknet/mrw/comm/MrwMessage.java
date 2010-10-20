/*
**
**	$Filename:	MrwMessage.java $
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

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.morknet.mrw.comm.Command;
import de.morknet.mrw.comm.MsgCode;
import de.morknet.mrw.comm.SignalCode;
import de.morknet.mrw.comm.can.CANMessage;

/**
 * Diese Klasse repräsentiert eine MRW-Message. Diese kann entweder ein Kommando, oder eine Kommando-Rückmeldung
 * beinhalten. Die Klasse ist von CANMessage abgeleitet, welche nur eine allgemeine Form einer CAN-Meldung
 * darstellt. Diese Klasse ist speziell für MRW zugeschnitten und beinhaltet so auch einige nützliche
 * Konstanten.
 * @author smork
 *
 */
public class MrwMessage extends CANMessage
{
	private final static Log log = LogFactory.getLog(MrwMessage.class);

	/**
	 * Die Boradcast ID.
	 */
	public final static int BROADCAST_SID = 0x7ff; 

	/**
	 * Die Standard ID des RS232-CAN-Gateway.
	 */
	public final static int GATEWAY_SID = 0; 

	/**
	 * Der Data-Index für den {@link Command}.
	 */
	public final static int IDX_COMMAND = 0;
	
	/**
	 * Der Data-Index für den Rückmelde-Code des Kommandos.
	 */
	public final static int IDX_RESULT = IDX_COMMAND;
	
	/**
	 * Der Data-Index für den {@link MsgCode}.
	 */
	public final static int IDX_MSG_CODE = 1;
	
	/**
	 * Der Data-Index für den {@link SignalCode} beim SETSGN-Kommando. 
	 */
	public final static int IDX_SIGNAL_CODE = 1;

	/**
	 * Diese Flag gibt an, ob die sendende Controller-Adresse auch in der Result-Meldung
	 * enthalten sein soll. Diese Adresse ist schon in der OID eingebaut, so dass sie 
	 * hier doppelt übertragen wird.
	 */
	public final static boolean USE_SID_IN_RESULT = false;

	/**
	 * Der Data-Index für den Start der Infobytes.
	 */
	public final static int IDX_INFO_START = USE_SID_IN_RESULT ? 6 : 4;

	/**
	 * Der Data-Index für das Low Byte der Gerätenummer des sendenden Controller. 
	 */
	public final static int IDX_LEID = IDX_INFO_START - 2;

	/**
	 * Der Data-Index für das High Byte der Gerätenummer des sendenden Controller. 
	 */
	public final static int IDX_HEID = IDX_INFO_START - 1;

	/**
	 * Der Konstruktur für eine leere MRW-Message.
	 */
	public MrwMessage()
	{
		super(BROADCAST_SID, 0, 0);
	}

	/**
	 * Prüft, ob es sich um eine Ergebnis-Meldung handelt.
	 * @return True, falls diese Meldung eine Ergebnis-Meldung ist.
	 */
	public boolean isResult()
	{
		return Command.isResult(data[IDX_RESULT]);
	}

	/**
	 * Gibt die gesamte ID des Absenders an. Das erzwingt eine Überprüfung auf eine
	 * Ergebnis-Meldung. 
	 * @return Die gesamte ID des Absenders.
	 */
	public int getResultId()
	{
		checkResult();
		return (getSourceControllerId() << 16) | getSourceUnitNo();
	}

	/**
	 * Gibt den Ergebnis-Code zurück. Das erzwingt eine Überprüfung auf eine
	 * Ergebnis-Meldung. 
	 * @return Der Ergebnis-Code der Meldung.
	 */
	public MsgCode getResultCode()
	{
		checkResult();
		return MsgCode.getMsgCode(data[IDX_MSG_CODE]);
	}

	/**
	 * Gibt das Kommando zurück, das dieser Ergebnis-Meldung zu Grunde liegt. Das erzwingt eine Überprüfung auf eine
	 * Ergebnis-Meldung.  
	 * @return Das auslösende Kommando.
	 */
	public Command getCommand()
	{
		checkResult();
		return Command.getCommand(data[IDX_COMMAND]);
	}

	/**
	 * Prüft, ob es sich bei dieser Message um eine Ergebnis-Meldung handelt. Wenn nicht, wird eine
	 * IllegalStateException geworfen.
	 */
	private void checkResult()
	{
		if (!isResult())
		{
			throw new IllegalMrwMessageException("Diese MRW-Meldung ist keine Antwort auf ein Kommando!");
		}
	}

	/**
	 * Diese statische Methode erzeugt eine Meldung und initialisiert diese.
	 * @param cmd Das Kommando
	 * @param id Der Ziel-Controller
	 * @param no Das Ziel Gerät
	 * @return Die erzeugt Meldung.
	 */
	public static MrwMessage createCommandMsg(Command cmd, int id, int no)
	{
		MrwMessage msg = new MrwMessage();
		msg.setSid(id);
		msg.setEid(no);
		msg.setExtended(true);
		msg.addDataByte(cmd);
		return msg;
	}

	/**
	 * Erzeugt eine Ping-Meldung.
	 * @return Die erzeugte Ping-Meldung.
	 */
	public static MrwMessage createPingMsg()
	{
		MrwMessage msg = new MrwMessage();
		msg.addDataByte(Command.PING);
		return msg;
	}

	/**
	 * Erzeugt eine Query Buffer State Meldung.
	 * @return Die erzeugte Query Buffer State Meldung
	 */
	public static MrwMessage createQueryBufferMsg()
	{
		MrwMessage msg = new MrwMessage();
		msg.addDataByte(Command.QRYBUF);
		return msg;
	}

	/**
	 * Erzeugt eine Query Error State Meldung.
	 * @return Die erzeugte Query Error State Meldung
	 */
	public static MrwMessage createQueryErrorMsg()
	{
		MrwMessage msg = new MrwMessage();
		msg.addDataByte(Command.QRYERR);
		return msg;
	}

	/**
	 * Erzeugt eine Reset-Meldung.
	 * @return Die erzeugte Reset-Meldung.
	 */
	public static MrwMessage createResetMsg()
	{
		MrwMessage msg = new MrwMessage();
		msg.addDataByte(Command.RESET);
		return msg;
	}

	/**
	 * Erzeugt eine Versionsabfrage-Meldung.
	 * @return Die erzeugte Versionsabfrage-Meldung.
	 */
	public static MrwMessage createGetVersionMsg()
	{
		MrwMessage msg = new MrwMessage();
		msg.addDataByte(Command.GETVER);
		return msg;
	}

	/**
	 * Diese Methode erzeugt eine Antwort-Meldung. Eine so erzeugte Meldung macht nur zu Simulationszwecken Sinn.
	 * @param cmd Der Kommando-Code
	 * @param code Der Antwort-Code
	 * @param id Die Controller-ID von der die Antwort stammen soll.
	 * @param no Die Gerätenummer, die die Antwort erzeugt.
	 * @return Die Antwort-Meldung.
	 */
	public static MrwMessage createResultMessage(Command cmd, MsgCode code, int id, int no)
	{
		MrwMessage msg = new MrwMessage();
		msg.setSid(GATEWAY_SID);
		msg.setEid(id);
		msg.addDataByte(Command.makeResult(cmd));
		msg.addDataByte(code.getMsgCode());
		if (USE_SID_IN_RESULT)
		{
			msg.addDataWord(id);
		}
		msg.addDataWord(no);
		return msg;
	}

	/**
	 * Diese Methode fügt einer Meldung ein Kommando-Code hinzu.
	 * @param cmd Der hinzuzufügende Kommando-Code.
	 */
	public void addDataByte(Command cmd)
	{
		if (length != 0)
		{
			throw new IllegalMrwMessageException(
					"Um ein Kommando einer Meldung hinzuzufügen, muss eine Meldung vorher leer sein!");
		}
		addDataByte(cmd.getCommand());
	}

	/**
	 * Wandelt den Inhalt dieser Meldung in Klartext um.
	 * @return Klartext Der Klartext dieser meldung.
	 */
	public String toString()
	{
		String cmd_text = Command.getCommand(data[0]).toString();

		StringWriter sw = new StringWriter();
		PrintWriter  pw = new PrintWriter(sw);
		int i;

		if (isResult())
		{
			
			String res_text = MsgCode.getMsgCode(data[1]).toString();
            pw.printf("ID=%04x:%04x len=%d stat=%02x # %-12.12s %-22.22s %04x:%04x",
                    sid, eid, length, status,
                    cmd_text,
                    res_text,
                    getSourceControllerId(),
                    getSourceUnitNo());
            for (i = IDX_INFO_START;i < length; i++)
            {
                pw.printf(" 0x%02x", data[i]);
            }
		}
		else
		{
            pw.printf("ID=%04x:%04x len=%d stat=%02x # %s",
                    sid, eid, length, status, cmd_text);
            if (data[IDX_COMMAND] == Command.SETSGN.getCommand())
            {
                String sig_text = SignalCode.getSignalCode(data[IDX_SIGNAL_CODE]).toString();

                pw.printf(" %s", sig_text);
            }
            else
            {
                for (i = 1;i < length; i++)
                {
                    pw.printf(" 0x%02x", data[i]);
                }
            }
		}
		String result = sw.toString();
	    pw.close();
	    return result;
	}

	/**
	 * Diese Methode gibt den Inhalt dieser Meldung im Klartext aus.
	 * @param comment Ein optionaler zusätzlicher Kommentar. 
	 */
	synchronized public void dump(String comment)
	{
		if (comment != null)
		{
			log.debug(toString() + " # " + comment);
		}
		else
		{
			log.debug(toString());
		}
	}

	/**
	 * Gibt die ID des Quellcontrollers zurück.
	 * @return Die ID des Absenders.
	 */
	public int getSourceControllerId()
	{
		checkResult();
		return getEid();
	}

	/**
	 * Gibt die ID des Quellgerätes zurück.
	 * @return Die ID des Gerätes.
	 */
	public int getSourceUnitNo()
	{
		checkResult();
		return computeWord(data[IDX_LEID], data[IDX_HEID]);
	}

	/**
	 * Diese Methode baut aus zwei Int-Werten einen 16-bit-Wert zusammen.
	 * @param l Das Lowbyte.
	 * @param h Date Highbyte.
	 * @return Das zusammengesetzte 16-Bit-Wort.
	 */
	public static int computeWord(int l, int h)
	{
		return l | (h << 8);
	}
}
