/*
**
**	$Filename:	CANMessage.java $
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

package de.morknet.mrw.comm.can;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Diese Klasse repräsentiert eine CAN-Message.
 * @author sm
 *
 */
public class CANMessage
{
	private final static Log log = LogFactory.getLog(CANMessage.class);

	/**
	 * Flag, ob es sich hierbei um ein Remote Frame handelt.
	 */
	public final static int FRAME_REMOTE           = 0x08;

	/**
	 * Flag, ob es sich hierbei um ein Extended Frame handelt.
	 */
	public final static int FRAME_EXT              = 0x10;

	/**
	 * Die empfangene CAN-Message ist ein Remote Frame.
	 */
	public final static int RX_STAT_FRAME_REMOTE   = 0x08;

	/**
	 * Die empfangene CAN-Message ist ein Extended Frame
	 */
	public final static int RX_STAT_FRAME_EXTENDED = 0x10;

	/**
	 * Die Standard Object ID.
	 */
	protected int sid;

	/**
	 * Die Extended Object ID.
	 */
	protected int eid;
	
	/**
	 * Der Zustand der CAN-Message. Hier wird definiert, ob es sich um ein Remote oder/und Extended Frame
	 * handelt.
	 * @see #FRAME_REMOTE
	 * @see #FRAME_EXT
	 */
	protected int status;
	
	/**
	 * Die Länge der CAN-Message. Dieser Wert darf zwischen 0 und 8 inkl. variieren. 
	 */
	protected int length;
	
	/**
	 * Hier wird die Nutzlast der CAN-Message gespeichert.
	 */
	protected final int[] data = new int[8];

	/**
	 * Dieser Konstruktor initialisiert diese CAN-Message mit IDs und Status.
	 * @param sid Die 11-bittige Standard-ID.
	 * @param eid Die 18-bittige zusätzliche Extended-ID
	 * @param status Flag, ob die Extended-ID gültig ist, bzw. ob es sich um ein Remote Frame handelt.
	 * @see #FRAME_EXT
	 * @see #FRAME_REMOTE
	 */
	public CANMessage(int sid, int eid, int status)
	{
		this.sid    = sid;
		this.eid    = eid;
		this.length = 0;
		this.status = status;
	}

	/**
	 * Diese Methode gibt diese CAN-Meldung als Folge von Bytes zurück, so dass sie sofort
	 * versendet werden können.
	 * @return Die zu versendenden Bytes dieser CAN-Meldung.
	 */
	final public byte[] getBytes()
	{
		byte [] result = new byte[this.length + 7];
		int     i;
		int     sum = 0;
		
		result[0] = (byte)length;
		result[1] = (byte)status;
		result[2] = (byte)(sid & 0xff);
		result[3] = (byte)(sid >> 8);
		result[4] = (byte)(eid & 0xff);
		result[5] = (byte)(eid >> 8);

		for(i=0; i < 6; i++)
		{
			sum -= result[i];
		}

		for(i=0; i < length; i++)
		{
			result[i+6] = (byte)data[i];
			sum -= (byte)data[i];
		}
		result[length+6] = (byte)(sum & 0xff);

		return result;
	}

	/**
	 * Diese Methode fügt dieser CAN-Message ein Byte hinzu. Es muss noch Platz im Frame (max. acht Bytes)
	 * sein, damit keine {@link IllegalStateException} geworfen wird.
	 * @param i Das hinzuzufügende Byte.
	 */
	final public void addDataByte(int i)
	{
		if (length >= 8)
		{
			throw new IllegalStateException("Data bytes full!");
		}
		data[length++] = i;
	}
	
	/**
	 * Diese Methode fügt dieser CAN-Message ein Wort hinzu. Es muss noch Platz im Frame (max. acht Bytes)
	 * sein, damit keine {@link IllegalStateException} geworfen wird. Das Wort wird in Little Endian gespeichert.
	 * (Low Byte first).
	 * @param i Das hinzuzufügende Wort.
	 */
	final public void addDataWord(int i)
	{
		if (length >= 7)
		{
			throw new IllegalStateException("Data bytes full!");
		}
		data[length++] =  i       & 0xff;
		data[length++] = (i >> 8) & 0xff;
	}

	/**
	 * Diese Methode gibt ein Datenbyte aus der CAN-Message zurück. Ein Index außerhalb des gültigen Bereichs
	 * forciert eine {@link ArrayIndexOutOfBoundsException}.
	 * @param idx Der Datenindex.
	 * @return Das angeforderte Datenbyte.
	 */
	final public int getData(final int idx)
	{
		if ((idx < 0) || (idx >= length))
		{
			throw new ArrayIndexOutOfBoundsException("Index = " + idx);
		}
		return data[idx];
	}

	/**
	 * Diese Methode modifiziert ein Datenbyte in der CAN-Message. Ein Index außerhalb des gültigen Bereichs
	 * forciert eine {@link ArrayIndexOutOfBoundsException}.
	 * @param idx Der Datenindex.
	 * @param value Das zu modifizierende Byte.
	 */
	final public void modifyData(final int idx, final int value)
	{
		if ((idx < 0) || (idx >= length))
		{
			throw new ArrayIndexOutOfBoundsException("Index = " + idx);
		}
		data[idx] = value & 0xff;
	}

	/**
	 * Diese Methode gibt die Standard-ID zurück.
	 * @return Die Standard-ID.
	 */
	final public int getSid()
	{
		return sid;
	}

	/**
	 * Diese Methode setzt die Standard-ID.
	 * @param sid Die zu setzende Standard-ID.
	 */
	final public void setSid(final int sid)
	{
		this.sid = sid;
	}
	
	/**
	 * Diese Methode gibt die Extended-ID zurück. Die 18-bittige ID ist rechtsbündig!
	 * @return Die Extended-ID.
	 */
	final public int getEid()
	{
		return eid;
	}

	/**
	 * Diese Methode setzt die Standard-ID. Die 18-bittige ID ist rechtsbündig!
	 * @param eid Die zu setzende Standard-ID.
	 */
	final public void setEid(final int eid)
	{
		this.eid = eid;
		setExtended(eid != 0);
	}

	/**
	 * Diese Methode setzt den Zustand dieser CAN-Message.
	 * @param status Der neue Zustand dieser CAN-Message.
	 * @see #FRAME_EXT
	 * @see #FRAME_REMOTE 
	 */
	void setStatus(int status)
	{
		this.status = status;
	}

	/**
	 * Diese Methode definiert, ob diese CAN-Message ein Standard Frame oder ein Extended Frame sein soll.
	 * @param ext Flag, das zwischen Standard und Extended Frame unterscheidet.
	 */
	final public void setExtended(boolean ext)
	{
		if (ext)
		{
			status |= FRAME_EXT;
		}
		else
		{
			status &= (~FRAME_EXT);
		}
	}

	/**
	 * Diese Methode baut aus der Standard-ID und der Extended-ID eine 32-Bit-ID zusammen. Die oberen 2 Bits der
	 * 18-bittigen Extended-ID gehen dabei verloren. Die Standard-ID ist in den oberen 16 Bit untergebracht, die
	 * Extended-ID in den unteren.
	 * @return Die zusammengesetzte ID.
	 */
	public Integer getId()
	{
		Integer id = getSid() << 16;

		if (isExtended())
		{
			id |= getEid();
		}
		return id;
	}

	/**
	 * Diese Methode gibt zurück, ob es sich bei dieser CAN-Message um ein Extended Frame handelt.
	 * @return Flag, ob CAN-Message ein Extended Frame ist.
	 */
	public boolean isExtended()
	{
		return (this.status & FRAME_EXT) != 0;
	}

	/**
	 * Diese Methode gibt die Länge der CAN-Meldung zurück. 
	 * @return Die Länge der CAN-Meldung.
	 */
	public int length()
	{
		return this.length;
	}

	/**
	 * Diese Methode loggt diese CAN-Message im Klartext aus.
	 * @param comment Ein ergänzender Kommentar.
	 */
	synchronized public void dump(String comment)
	{
		StringWriter sw = new StringWriter();
		PrintWriter  pw = new PrintWriter(sw);

		pw.printf("ID=%04x:%04x len=%d stat=%02x", sid, eid, length, status);
		for (int i = 0;i < length; i++)
		{
		    pw.printf(" 0x%02x", data[i]);
		}
		if (comment != null)
		{
			pw.printf(" # %s", comment);
		}
		log.debug(sw.toString());
		pw.close();
	}
}
