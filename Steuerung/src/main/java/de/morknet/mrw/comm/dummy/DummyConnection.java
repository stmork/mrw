/*
**
**	$Filename:	DummyConnection.java $
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

package de.morknet.mrw.comm.dummy;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.morknet.mrw.base.DirectionCode;
import de.morknet.mrw.base.MrwException;
import de.morknet.mrw.base.OccupationCode;
import de.morknet.mrw.comm.Command;
import de.morknet.mrw.comm.Connection;
import de.morknet.mrw.comm.MrwMessage;
import de.morknet.mrw.comm.MsgCode;
import de.morknet.mrw.comm.can.CANMessage;
import de.morknet.mrw.comm.can.ChecksumException;
import de.morknet.mrw.comm.can.MessageLengthException;
import de.morknet.mrw.util.LogUtil;

/**
 * Diese Klasse simuliert eine Kommunikationsverbindung zu einer Eisenbahnanlage. Sie ist in der Lage diverse Fehlerfälle zu
 * simulieren, was zum Testen brauchbar ist.
 * @author sm
 *
 */
public class DummyConnection extends Connection
{
	private final static Log    log    = LogFactory.getLog(DummyConnection.class);
	private final static Random random = new Random();
	
	// Flags für das Fehlerverhalten.
	private boolean forceSwitchFailure;
	private boolean rejectFullAnswer;
	private boolean rejectSwitchAnswer;
	private int     maxErrorOffset;
	private int     errorOffset;
	private boolean oneshot;

	static
	{
		random.setSeed(System.currentTimeMillis());
	}

	private final static int HEADER_LENGTH = 6;

	@Override
	protected void start()
	{
	}

	@Override
	protected void stop()
	{
	}

	@Override
	public void close()
	{
		log.debug(" close()");
	}

	@Override
	public int read() throws IOException
	{
		throw new IOException("read() nicht implementiert.");
	}

	@Override
	public void write(int input) throws IOException
	{
		try
		{
			processor.processByte(input & 0xff);
		}
		catch(ChecksumException ce)
		{
			handleChecksumException(ce);
		}
		catch (MrwException e)
		{
			log.error(e.getLocalizedMessage(), e);
		}
	}

	private int prepareChecksumError(final int input, final boolean doIt)
	{
		final int output;

		if ((maxErrorOffset > 0) && (doIt))
		{
			if (errorOffset <= 0)
			{
				// Zähler zufällig neu initialisieren
				errorOffset = random.nextInt(maxErrorOffset);
				
				// Prüfsumme "versauen"
				output = input ^ (random.nextInt(0xfe) + 1);
				log.warn(LogUtil.printf("Erzwinge Prüfsummenfehler (%02x -> %02x)...", input, output));
				
				if (oneshot)
				{
					maxErrorOffset = 0;
				}
			}
			else
			{
				output = input;
				errorOffset--;
			}
		}
		else
		{
			output = input;
		}
		return output;
	}

	private static long count = 0;
	
	private class AnswereThread extends Thread
	{
		private final byte[] bytes;
		
		private AnswereThread(final byte[] bytes)
		{
			super("Dummy Receiver");
			this.bytes = bytes;

			setDaemon(true);
			setPriority(MAX_PRIORITY);
		}

		@Override
		public void run() 
		{
			if (!Arrays.equals(bytes, getSyncSequence()))
			{
				try
				{
					final Command cmd = Command.getCommand(bytes[6]);

					// Delay each command
					count++;
					sleep(9L * count);
					switch(cmd)
					{
					case SETLFT:
					case SETRGT:
						sendResult(bytes, MsgCode.MSG_QUEUED);
						sleep(100L);

						if (!rejectSwitchAnswer)
						{
							sendResult(bytes, forceSwitchFailure ? MsgCode.MSG_SWITCH_FAILED : MsgCode.MSG_OK, 6);
						}
						break;
	
					case GETDIR:
						sendResult(bytes, MsgCode.MSG_OK, DirectionCode.LEFT.getDirectionCode());
						break;
	
					case GETRBS:
						sendResult(bytes, MsgCode.MSG_OK, OccupationCode.FREE.getOccupationCode());
						break;
	
					case QRYBUF:
						sendResult(bytes, MsgCode.MSG_OK, 0, 1);
						break;
	
					case QRYERR:
						sendResult(bytes, MsgCode.MSG_OK, 0, 0);
						sendResult(bytes, MsgCode.MSG_OK, 1, 0);
						sendResult(bytes, MsgCode.MSG_OK, 2, 0);
						break;
	
					case GETVER:
						sendResult(bytes, MsgCode.MSG_OK, 0, 1);
						sendResult(bytes, MsgCode.MSG_OK, 1, 2);
						sendResult(bytes, MsgCode.MSG_OK, 2, 3);
						break;
	
					case RESET:
						sendResult(bytes, MsgCode.MSG_OK);
						sendResult(bytes, MsgCode.MSG_RESET_PENDING);
						sleep(2500L);
						sendResult(bytes, MsgCode.MSG_BOOTED);
						break;
	
					default:
						sendResult(bytes, MsgCode.MSG_OK);
						break;
					}
				}
				catch(IOException ioe)
				{
					log.error(ioe.getLocalizedMessage(), ioe);
				}
				catch (InterruptedException e)
				{
					log.error(e.getLocalizedMessage(), e);
				}
				finally
				{
					count--;
				}
			}
			else
			{
				log.debug("Synchronisierung versendet.");
			}
		}
		
		private void sendResult(
				final byte[]  bytes,
				final MsgCode result,
				final int ... info) throws IOException
		{
			final int [] answer = new int[HEADER_LENGTH + MrwMessage.IDX_INFO_START + info.length];
			int idx = 0;
			
			answer[idx++] = MrwMessage.IDX_INFO_START + info.length;
			answer[idx++] = MrwMessage.RX_STAT_FRAME_EXTENDED;
			answer[idx++] = MrwMessage.GATEWAY_SID & 0xff;
			answer[idx++] = MrwMessage.GATEWAY_SID >> 8;
			answer[idx++] = bytes[2] & 0xff;
			answer[idx++] = bytes[3] & 0xff;
			answer[idx++] = Command.makeResult(bytes[HEADER_LENGTH + MrwMessage.IDX_RESULT]);
			answer[idx++] = result.getMsgCode();

			if (MrwMessage.USE_SID_IN_RESULT)
			{
				answer[idx++] = bytes[2] & 0xff;
				answer[idx++] = bytes[3] & 0xff;
			}
			answer[idx++] = bytes[4] & 0xff;
			answer[idx++] = bytes[5] & 0xff;
			for (int i = 0;i < info.length;i++)
			{
				answer[idx++] = info[i] & 0xff;
			}
			send(answer, result == MsgCode.MSG_OK);
		}

		private void send(final int [] answer, final boolean simulateChecksumError) throws IOException
		{
			if(!rejectFullAnswer)
			{
				int sum = 0;
		
				if (answer[0] < answer.length - HEADER_LENGTH)
				{
					throw new MessageLengthException(answer);
				}

				synchronized(processor)
				{
					for (int i = 0;i < (HEADER_LENGTH + answer[0]);i++)
					{
						sum -= answer[i];
						write(prepareChecksumError(answer[i], simulateChecksumError));
					}
					write(prepareChecksumError(sum, simulateChecksumError));
					write(0);
				}
			}
		}
	}

	@Override
	public void write(final byte[] bytes) throws IOException
	{
		AnswereThread answere = new AnswereThread(bytes);
		answere.start();
	}

	/**
	 * Diese Methode simuliert das Empfangen einer CAN-Message über diese Verbindung. Evtl. eingestelltes Fehlerverhalten greift hier.
	 * @param msg Die zu empfangende CAN-Message.
	 * @throws IOException Die Exception, die geworfen wird, falls beim Empfang was schief lief.
	 */
	public void simulateReception(final CANMessage msg) throws IOException
	{
		synchronized(processor)
		{
			for (byte b : msg.getBytes())
			{
				write(b);
			}
			write(0);
		}
	}

	/**
	 * Diese Methode stellt das Fehlerverhalten für Weichenschaltkommandos ein. Ist der Fehlermodus aktiviert, wird
	 * jedes Weichenschaltkommando mit einem {@link MsgCode#MSG_SWITCH_FAILED} quittiert.
	 * @param forceSwitchFailure Flag, ob Weichenschaltfehler gemeldet werden sollen.
	 */
	public void setSwitchFailureMode(final boolean forceSwitchFailure)
	{
		this.forceSwitchFailure = forceSwitchFailure; 
	}

	/**
	 * Diese Methode stellt das Fehlerverhalten für Weichenschaltkommandos ein. Ist dieser Fehlermodus aktiviert, kommen
	 * auf Weichenschaltkommandos keine Vollzugsantworten {@link MsgCode#MSG_OK} mehr. Dies kann bei einer Verklemmung einer
	 * Weiche passieren. 
	 * @param rejectSwitchAnswer Flag, ob Vollzugsmeldungen gesendet werden sollen.
	 */
	public void setRejectSwitchAnswer(final boolean rejectSwitchAnswer)
	{
		this.rejectSwitchAnswer = rejectSwitchAnswer;
	}

	/**
	 * Diese Methode stellt das Fehlerverhalten aller Schaltkommandos ein. Ist dieser Fehlermodus aktiviert, kommen
	 * keine Vollzugsantworten mehr {@link MsgCode#MSG_OK}. Das System verhält sich dann so, als ob die Anlage nicht mehr
	 * verbunden ist.
	 * @param rejectAnswer Flag, ob  überhaupt Vollzugsmeldungen gesendet werden sollen.
	 */
	public void setRejectFullAnswer(final boolean rejectAnswer)
	{
		this.rejectFullAnswer = rejectAnswer;
	}

	/**
	 * Diese Methode stellt das Fehlerverhalten bzgl. der Prüfsummenfehler ein. Dabei können sporadisch fehlerhaft empfangene
	 * Bytes simuliert werden, wodurch letztlich Prüfsummenfehler auftreten. Dabei kann gewählt werden, ob einmalig ein
	 * Fehler verursacht werden soll, oder wiederholt. Letzteres stellt eine instabile Kommunikationsverbindung dar. Das
	 * Auftreten kann zufällig nach maximal einstellbarer Bytezahl gewählt werden.
	 * @param maxErrorOffset Die Maximalzahl an Bytes, bis zu der ein Fehler wahrscheinlich aufgetreten sein muss.
	 * @param oneshot Flag, ob einmalig oder wiederholt Bytes verfälscht werden sollen.
	 */
	public void setChecksumError(final int maxErrorOffset, final boolean oneshot)
	{
		this.maxErrorOffset = maxErrorOffset;
		this.oneshot        = oneshot;
		if (maxErrorOffset > 0)
		{
			errorOffset = random.nextInt(maxErrorOffset);
		}
	}
}
