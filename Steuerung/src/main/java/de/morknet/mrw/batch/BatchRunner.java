/*
**
**	$Filename:	BatchRunner.java $
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

package de.morknet.mrw.batch;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.morknet.mrw.MrwController;
import de.morknet.mrw.Route;
import de.morknet.mrw.RoutingException;
import de.morknet.mrw.comm.MrwMessage;
import de.morknet.mrw.util.LogUtil;

/**
 * Diese Klasse f�hrt das Schalten von Kommandos aus.
 * @author smork
 *
 */
abstract public class BatchRunner extends Thread
{
	protected final static Log               log      = LogFactory.getLog(BatchRunner.class);
	protected final        BatchExecuter     executer = new BatchExecuter();
	protected final        String            title;
	protected final        ExceptionCallback feedback;
	private   final        MrwController     controller;
	private                RuntimeException  exception = null;
	protected              Route             route;
	
	/**
	 * Dieser Konstruktur initialisiert diesen BatchRunner 
	 * @param controller Der steuernde Controller
	 * @param title Ein beliebiger Threadname
	 * @param route Die zu schaltende Fahrstra�e
	 * @param feedback Evtl. Callbacks f�r R�ckmeldungen
	 */
	protected BatchRunner(
			final MrwController     controller,
			final String            title,
			final Route             route,
			final ExceptionCallback feedback)
	{
		super(title);
		this.controller = controller;
		this.feedback   = feedback;
		this.title      = title;
		this.route      = route;
	}

	/**
	 * Diese Methode f�hrt den eigentlichen Auftrag aus.
	 * @param controller Der steuernde Controller.
	 * @throws RoutingException 
	 */
	abstract protected void doJob(MrwController controller);

	/**
	 * Dieser Callback dient zum Aktualisieren der Anzeige nach Ausf�hrung des Jobs.
	 * @param controller
	 */
	abstract protected void doUpdateUI(MrwController controller);
	
	/**
	 * Diese Methode f�hrt das Schalten von Kommandos aus und regelt das Fehlerverhalten. Von hier aus werden
	 * Exceptions gefangen und vermerkt ({@link #getException()}). Es werden Fehlermeldungen an den {@link MrwController}
	 * geschickt und es wird der Fehlerzustand wieder aufgel�st. Kann der Fehlerzustand nicht aufgel�st werden, wird ein
	 * RESET an die Microcontroller gesendet (doppelter Ausf�hrungsfehler). Schl�gt auch dieser fehl, sollte das Programm
	 * verlassen werden und nach dem Fehler hardware-seitg gesucht werden (dreifacher Ausf�hrungsfehler).
	 */
	@Override
	final public void run()
	{
		final long start = System.currentTimeMillis();

		log.debug(">run()");
		try
		{
			doJob(controller);
		}
		catch(RuntimeException e)
		{
			log.error(e.getMessage(), e);
			controller.setErrorMessage(e.getMessage());

			this.exception = e;
			try
			{
				executer.clear();

				if (feedback != null)
				{
					log.debug(">errorOnSend()...");
					feedback.errorOnSend();
					log.debug("<errorOnSend()...");
				}
				
			}
			finally
			{
				clearErrorCondition();
			}
		}
		finally
		{
			long diff = System.currentTimeMillis() - start;
			log.debug(LogUtil.printf(" Stapelverarbeitung f�r %s brauchte %d ms.",
					this.title, diff));
		}

		doUpdateUI(controller);
		log.debug("<run()");
	}

	private void clearErrorCondition()
	{
		final BatchExecuter errorExecuter = new BatchExecuter();

		log.debug(">clearErrorCondition()");
		try
		{
			log.warn("Es wird versucht, den Fehlerzustand aufzul�sen...");
			route.computeForcedClearBatch(errorExecuter);
			controller.send(errorExecuter);
			log.info("Fehlerzustand aufgel�st...");
		}
		catch(Exception e)
		{
			log.error(e.getMessage(), e);
			controller.setErrorMessage("Doppelter Ausf�hrungsfehler: Fehler aufgetreten, w�hrend Fehlerzustand aufgel�st wird!");

			try
			{
				if (feedback != null)
				{
					log.debug(">errorOnClear()...");
					feedback.errorOnClear();
					log.debug("<errorOnClear()...");
				}
			}
			finally
			{
				reset();
			}
		}
		finally
		{
			errorExecuter.clear();
			route.clear();
			log.debug("<clearErrorCondition()");
		}
	}

	/**
	 * Diese Methode schickt ein RESET-Kommando an die Mikrocontroller. Das ist der letzte Versuch, einen
	 * Fehlerzustand aufzul�sen. Sollte auch dieser fehlschlagen, wird dieses Programm hart beendet.
	 */
	private void reset()
	{
		log.debug(">reset()");
		final MrwMessage msg = MrwMessage.createResetMsg();

		try
		{
			log.error("Es wird ein Reset der Mikrocontroller ausgel�st!");
			controller.send(msg, "RESET nach doppeltem Ausf�hrungsfehler!");
			controller.waitForReachability(250L);
			log.info("Der Reset der Mikrocontroller wurde durchgef�hrt!");
		}
		catch (IOException e)
		{
			final RuntimeException re = new RuntimeException("Dreifacher Ausf�hrungsfehler: Bitte Programm verlassen!", e);

			log.fatal(e.getMessage(), e);
			controller.setErrorMessage(re.getMessage());
			throw re;
		}
		finally
		{
			log.debug("<reset()");
		}
	}
	
	/**
	 * Diese Methode gibt die zum BatchRunner geh�rende Fahrstra�e zur�ck.
	 * @return Die mit diesem BatchRunner verbundene Fahrstra�e.
	 */
	final public Route getRoute()
	{
		return this.route;
	}
	
	/**
	 * Hier gibt der BatchRunner eine verursachende Exception zur�ck.
	 * @return Die verursachende Exception f�r einen Fehler.
	 */
	final public RuntimeException getException()
	{
		return exception;
	}
	
	/**
	 * Diese Methode gibt Informationen �ber diesen BatchRunner zur�ck.
	 * @return Informationen �ber diesen BatchRunner.
	 */
	public String toString()
	{
		return LogUtil.printf("BatchRunner f�r Fahrstra�e %s (%s)", route.toString(), isAlive() ? "l�uft" : "beendet");
	}

	/**
	 * Diese Methode legt eine Denkpause ein. Eine {@link InterruptedException} wird dabei abgefangen und ignoriert.
	 * @param delay Die Wartezeit in Millisekunden.
	 */
	public static void sleep(final long delay)
	{
		try
		{
			Thread.sleep(delay);
		}
		catch (InterruptedException ie)
		{
			log.warn("Probleme beim Warten von " + delay + " Sekunden.");
		}
	}
}
