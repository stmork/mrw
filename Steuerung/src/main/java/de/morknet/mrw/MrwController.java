/*
**
**	$Filename:	MrwController.java $
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

package de.morknet.mrw;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.morknet.mrw.automatic.MrwActionControl;
import de.morknet.mrw.automatic.RouteClearTrigger;
import de.morknet.mrw.automatic.Trigger;
import de.morknet.mrw.automatic.beermode.BeerMode;
import de.morknet.mrw.automatic.tourmode.TourMode;
import de.morknet.mrw.base.Abschnitt;
import de.morknet.mrw.base.DKW;
import de.morknet.mrw.base.DeviceUnit;
import de.morknet.mrw.base.DirectionCode;
import de.morknet.mrw.base.Gleisteil;
import de.morknet.mrw.base.MicroController;
import de.morknet.mrw.base.ModelInvalidException;
import de.morknet.mrw.base.OccupationCode;
import de.morknet.mrw.base.Signal;
import de.morknet.mrw.base.Verzweigung;
import de.morknet.mrw.base.Weiche;
import de.morknet.mrw.batch.Batch;
import de.morknet.mrw.batch.BatchExecuter;
import de.morknet.mrw.batch.BatchProcessingException;
import de.morknet.mrw.batch.BatchRunner;
import de.morknet.mrw.batch.ClearRouteRunner;
import de.morknet.mrw.batch.RouteEnableRunner;
import de.morknet.mrw.comm.Command;
import de.morknet.mrw.comm.Connection;
import de.morknet.mrw.comm.MrwMessage;
import de.morknet.mrw.comm.MsgCode;
import de.morknet.mrw.comm.can.CANMessage;
import de.morknet.mrw.comm.can.CANMessageProcessor;
import de.morknet.mrw.comm.can.CANReceiver;
import de.morknet.mrw.comm.dummy.DummyConnection;
import de.morknet.mrw.comm.rs232.RS232Connection;
import de.morknet.mrw.gui.info.Layout;
import de.morknet.mrw.util.LogUtil;

/**
 * Diese Klasse stellt die zentrale Steuerung dar. Die Funktionalität wird in dieser abstrakten Klasse
 * abgebildet. Die jeweilige Darstellung in einer Konsole oder GUI muss in der entsprechend abgeleiteten Klasse erfolgen.
 * @author sm
 *
 */
abstract public class MrwController implements CANMessageProcessor
{
	private   final static Log                    log                 = LogFactory.getLog(MrwController.class);
	private   final static long                   ANSWER_DELAY        =   30L;
	private   final static long                   POLLING_DELAY_PING  =   25L;
	private   final static long                   POLLING_DELAY_QUERY =  100L;
	private   final static long                   POLLING_DELAY_RESET =  250L;
	private   final static long                   DELAY_WDT_TIMEOUT   = 1000L;
	private   final static long                   DELAY_BOOTLOADER    = 2000L;
	private   final static long                   DELAY_RESET         = DELAY_WDT_TIMEOUT + DELAY_BOOTLOADER + 1000L;
	private   final static long                   DELAY_RECONFIG      = DELAY_WDT_TIMEOUT + DELAY_BOOTLOADER + 4000L;
	private   final        List<Abschnitt>        selection           = new LinkedList<Abschnitt>();
	private   final        LinkedHashSet<Trigger> trigger             = new LinkedHashSet<Trigger>(); 
	private   final        CANReceiver            receiver            = new CANReceiver();
	
	/**
	 * Die zu steuernde Modelleisenbahnanlage.
	 */
	protected final        Modell                 model;

	/**
	 * Die Kommunikationsverbindung zur Modelleisenbahnanlage.
	 * @see RS232Connection
	 * @see DummyConnection
	 */
	protected              Connection             connection;

	/**
	 * Dieser Konstruktur instanziiert die Modelleisenbahnanlage.
	 */
	protected MrwController()
	{
		this.model = ModellFactory.getInstance();
		Trigger trigger = new RouteClearTrigger(this);
		addTrigger(trigger);
	}

	/**
	 * Diese Methode initialisiert diesen Controller. Dabei werden alle Bauelemente in einen
	 * definierten Zustand gebracht und der Zustand der Bauelemente abgefragt.
	 * @throws Exception Exceptions werden übergeordnet ausgewertet.
	 */
	public void prepare() throws Exception
	{
		prepareSimple();

		// Alle MCUs mal anpingen.
		model.ping();
		MrwMessage msg = MrwMessage.createPingMsg();
		send(msg);

		// Anlage in definierten Ausgangszustand bringen...
		BatchExecuter executer = new BatchExecuter();

		// Anlage ausschalten...
		Batch batch = executer.createBatch();
		model.off(batch);
		
		// Zustand der Anlage abfragen...
		batch = executer.createBatch();
		model.getState(batch);
		try
		{
			send(executer);
		}
		catch(BatchProcessingException bpe)
		{
			log.error(bpe.getMessage(), bpe);
		}
		finally
		{
			executer.clear();
		}	
	}

	/**
	 * Diese Methode initialisiert diesen Controller.
	 * @throws Exception
	 */
	public void prepareSimple() throws Exception
	{
		int errors = model.validate();

		if (errors > 0)
		{
			throw new ModelInvalidException(errors);
		}
		
		log.info("Validierung vollständig. Fehlerzahl: " + errors);
		Layout layout = new Layout(model);
		layout.save();

		connection = Connection.getDefaultConnection();
		connection.setByteProcessor(receiver);
		connection.sync();
		receiver.setProcessor(this);
	}

	private class SegmentHandler extends BatchRunner
	{
		private final Abschnitt segment;
		private final Abschnitt successor;
		private final boolean   direction;

		public SegmentHandler(MrwController controller, Route route, Abschnitt segment)
		{
			super(controller , "Abschnittfreigabe " + segment.getNumber(), route, null);
			this.segment   = segment;
			this.successor = route.getSuccSegment(segment);
			this.direction = route.getDirection();
		}

		@Override
		protected void doJob(MrwController controller)
		{
			log.debug("1. Check Blockfreigabe...");
			synchronized(route)
			{
				if (successor.hasMainSignal(direction) && route.hasBlockDeallocation())
				{
					/*
					 * Wenn ein Hauptsignal erreicht wurde, müssen alle Gleisabschnitte dahinter
					 * freigegeben werden, sonst funktioniert der Actiontrigger nicht. Das betrifft
					 * eigentlich nur den hintersten Gleisabschnitt.
					 */
					log.debug("Blockfreigaben für Fahrstraße " + route);
					route.clearUptoSegment(segment);
				}
			}

			log.debug("2. Check Gleisabschaltung...");
			synchronized(route)
			{
				/*
				 * Hier findet die Gleisabschaltung statt. Evtl. im Abschnitt enthaltene Signale werden
				 * Hp0 oder Vr0 geschaltet.
				 */
				route.computeClearUptoSegmentBatches(executer, segment);
				controller.updateTrackPlan();
				controller.send(executer);
				executer.clear();
				if (segment.hasMainSignal(direction) || route.isShunting() || (!route.hasBlockDeallocation()))
				{
					route.clearUptoSegment(segment);
				}
			}

			/*
			 * Dieser Aufruf dient für den Fall, dass ein Zug in einen Abschnitt
			 * verlassen hat und gleichzeitig in den nachfolgenden eingefahren ist. Diese
			 * Bedingung ist wichtig, da diese nicht eintritt, wenn z.B. durch Kontaktprobleme
			 * ein Zug aus einem Abschnitt verschwindet. 
			 */
			log.debug("3. Check: Trigger für Gleisabschnitt verlassen...");
			log.info("Abschnitt " + segment.getNumber() + " verlassen.");
			synchronized(trigger)
			{
				for (Trigger t : trigger)
				{
					t.railLeft(route, segment);
				}
			}

			/*
			 * Diese Aufrufe dienen für den Fall, dass ein Zug in einen Abschnitt
			 * eingetreten ist und gleichzeitig den vorhergehenden verlassen hat. 
			 */
			log.debug("4. Check: Trigger für letzter Gleisabschnitt erreicht...");
			if (route.getLastSegment() == successor)
			{			
				log.info("Letzter Abschnitt " + successor.getNumber());
				synchronized(trigger)
				{
					for (Trigger t : trigger)
					{
						route = t.endPointReached(route, successor);
					}
				}
			}
			
			// Trigger für "Abschnitt eingefahren" aufrufen
			log.debug("5. Check: Trigger für Gleisabschnitt erreicht...");
			log.info("Abschnitt " + successor.getNumber() + " erreicht.");
			synchronized(trigger)
			{
				for (Trigger t : trigger)
				{
					t.railEntered(route, successor);
				}
			}
		}

		@Override
		protected void doUpdateUI(MrwController controller)
		{
			controller.updateTrackPlan();
		}
	}

	/**
	 * Hier wird eine CAN-Meldung verarbeitet. Unter Umständen wird die Meldung an die entsprechenden
	 * {@link BatchExecuter} weitergereicht. Desweiteren werden die bekannten Trigger aufgerufen.
	 * @param msg Die eingetroffene CAN-Meldung
	 * @see CANMessage
	 */
	public void process(CANMessage msg)
	{
		msg.dump("<");

		if (msg instanceof MrwMessage)
		{
			MrwMessage mrw = (MrwMessage)msg;
			MicroController c;
			Command cmd = mrw.getCommand();
			if (mrw.isResult())
			{
				MsgCode result = MsgCode.getMsgCode(mrw.getData(1));
				int     cid    = mrw.getSourceControllerId();

				switch(cmd)
				{
				case GETRBS:
					// Gleisbesetztmeldung
					BatchExecuter.processResult(mrw);
					Abschnitt segment = Abschnitt.findSegment(mrw.getResultId());
					if ((result == MsgCode.MSG_OK) && (segment != null))
					{
						log.info("Gleisbelegung: " + segment.getName() + " " + mrw.getData(MrwMessage.IDX_INFO_START));
						segment.setOccupation(OccupationCode.isOccupied(mrw.getData(MrwMessage.IDX_INFO_START)));
						if (Route.hasAutomaticSegmentDeallocation())
						{
							Route route = Route.findRoute(segment);
							if (route != null)
							{
								if (route.checkIfSegmentLeft(segment))
								{
									// Abschnitte werden freigegeben.
									SegmentHandler liberator = new SegmentHandler(this, route, segment);
									liberator.start();
								}
							}
						}
						updateTrackPlan();
						return;
					}
					break;
					
				case GETDIR:
					boolean hadBatch = BatchExecuter.processResult(mrw);
					Verzweigung branch = Verzweigung.findVerzweigung(mrw.getResultId());
					if ((result == MsgCode.MSG_OK) && (branch != null))
					{
						/*
						 * Zustand merken.
						 */
						branch.setDir(DirectionCode.getDirectionCode(branch, mrw.getData(MrwMessage.IDX_INFO_START)));
						log.info(LogUtil.printf("Weichenlage: %s", branch));

						/*
						 * Wenn eine Verzweigung gesperrt ist und zu einer Fahrstraße gehört, darf keine
						 * Schaltmeldung mehr kommen. Kommt sie trotzdem, weist das auf einen manuellen
						 * Eingriff hin. Ausnahme: Wenn es vorher einen Batch gab, dann war diese
						 * Meldung programmatisch gewollt und somit nicht manuell.
						 */
						if (!hadBatch)
						{
							log.info("Manuelle Weichenschaltung erfolgt. Prüfung auf betroffene Fahrstraße...");
							Route route = Route.findConflictingRoute(branch);
							if (route != null)
							{
								setErrorMessage("Manipulation einer gültigen Fahrstraße durch manuelle Weichenschaltung!");
								log.warn("> Fahrstraße " + route + " wird aufgelöst.");
								deactivateAction(route);
								ClearRouteRunner liberator = new ClearRouteRunner(this, route);
								liberator.start();
							}
							else
							{
								log.info("Keine Fahrstraße von manueller Weichenschaltung betroffen.");
							}
						}
						updateTrackPlan();
					}
					break;

				case SETSGN:
				case SETLFT:
				case SETRGT:
					addElapsed(mrw);
					BatchExecuter.processResult(mrw);
					break;

				case SETRON:
				case SETROF:
					BatchExecuter.processResult(mrw);
					break;

				case PING:
					c = model.findMicroController(cid);
					if (c != null)
					{
						// Ping-Rückmeldung
						c.pong();
					}
					else
					{
						log.warn(LogUtil.printf("Controller mit ID %d nicht bekannt.", cid));
					}
					break;
					
				case RESET:
					switch (result)
					{
					case MSG_RESET_PENDING:
						log.warn(LogUtil.printf("Reset am Controller mit ID %d wird durchgeführt.", cid));
						break;
					case MSG_OK:
						log.warn(LogUtil.printf("Reset am Controller mit ID %d ausgelöst.", cid));
						break;
					case MSG_BOOTED:
						log.warn(LogUtil.printf("******** Controller mit ID %d ist hochgefahren und betriebsbereit", cid ));
						c = model.findMicroController(cid);
						if (c != null)
						{
							c.booted();
						}
						else
						{
							log.warn(LogUtil.printf("Controller mit ID %d nicht bekannt.", cid));
						}
						break;
					default:
						log.warn(LogUtil.printf("Reset am Controller mit ID %d (Code=%s).", cid, result.toString()));
						break;
					}
					break;

				case QRYBUF:
					c = model.findMicroController(cid);
					if (c != null)
					{
						// Zustand der Sendepuffer loggen
						c.setBufferCount(
								mrw.getData(MrwMessage.IDX_INFO_START),
								mrw.getData(MrwMessage.IDX_INFO_START+1));
						log.info(LogUtil.printf(
								"Controller mit ID: %d               RX buffer: %3d TX buffer: %3d",
								cid,
								mrw.getData(MrwMessage.IDX_INFO_START),
								mrw.getData(MrwMessage.IDX_INFO_START+1)));
					}
					else
					{
						log.warn(LogUtil.printf("Controller mit ID %d nicht bekannt.", cid));
					}
					break;
					
				case QRYERR:
					c = model.findMicroController(cid);
					if (c != null)
					{
						c.setErrorValue(mrw);
					}
					else
					{
						log.warn(LogUtil.printf("Controller mit ID %d nicht bekannt.", cid));
					}
					break;
					
				case GETVER:
					c = model.findMicroController(cid);
					if (c != null)
					{
						c.setRevisionValue(mrw);
					}
					else
					{
						log.warn(LogUtil.printf("Controller mit ID %d nicht bekannt.", cid));
					}
					break;

				case SENSOR:
					model.setSensorValue(mrw);
					break;

				default:
					// Do nothing!
					break;
				}
			}
		}
	}

	/**
	 * Diese Methode wertet die Laufzeit eines Schaltauftrages aus. Die Laufzeiten werden pro Gerät ({@link DeviceUnit})
	 * verwaltet. Die Auswertung berechnet über alle Schaltaufträge den Durchschnittswert.
	 * @param mrw Die CAN-Meldung, die die Laufzeit enthält.
	 */
	private void addElapsed(final MrwMessage mrw)
	{
		if (mrw.length() >= (MrwMessage.IDX_INFO_START + 1))
		{
			DeviceUnit dvc = DeviceUnit.findDeviceUnit(mrw.getResultId()); 
			if (dvc != null) 
			{
				dvc.addElapsed(mrw.getData(MrwMessage.IDX_INFO_START));
			}
		}
	}

	/**
	 * Diese Methode sendet den Inhalt eines {@link BatchExecuter}s.
	 * @param executer Der {@link BatchExecuter}, der die {@link Batch}es versenden soll.
	 */
	public void send(final BatchExecuter executer)
	{
		executer.send(connection);
	}

	/**
	 * Diese Methode sendet eine CAN-Meldung.
	 * @param msg Die CAN-Meldung
	 * @throws IOException Wird geworfen, falls das Versenden fehlschlug.
	 */
	public void send(final CANMessage msg) throws IOException
	{
		send(msg, null);
	}

	/**
	 * Diese Methode sendet eine CAN-Meldung und loggt diese mit einem Kommentar.
	 * @param msg Die CAN-Meldung
	 * @param comment Der Kommentar
	 * @throws IOException Wird geworfen, falls das Versenden fehlschlug.
	 */
	final public void send(final CANMessage msg, final String comment) throws IOException
	{
		msg.dump(comment == null ? ">" : "> " + comment);
		connection.write(msg.getBytes());
	}

	/**
	 * Diese Methode schließt die aktuelle Verbindung zur Eisenbahn.
	 */
	final public void close()
	{
		receiver.close();
		connection.close();
	}

	/**
	 * Diese Methode erzeugt eine CAN-Meldung auf Basis einer {@link MrwMessage}.
	 * @return Die erzeugt CAN-Meldung.
	 */
	final public CANMessage createMsg()
	{
		return new MrwMessage();
	}

	/**
	 * Diese Methode registriert einen {@link Trigger}.
	 * @param t Der zu registrierende {@link Trigger}.
	 */
	final public void addTrigger(final Trigger t)
	{
		synchronized(trigger)
		{
			trigger.add(t);
		}
	}

	/**
	 * Diese Methode meldet einen {@link Trigger} ab.
	 * @param t Der abzumeldende {@link Trigger}.
	 */
	final public void removeTrigger(final Trigger t)
	{
		synchronized(trigger)
		{
			trigger.remove(t);
		}
	}

	/**
	 * Diese Methode hängt den angegebenen {@link Trigger} an das Ende der Liste aller {@link Trigger}. Die
	 * Ausführung erfolgt asynchron, um eine {@link ConcurrentModificationException} zur vermeiden.
	 * @param t Der {@link Trigger}, der an das Ende gehängt werden soll.
	 */
	final public void moveTrigger(final Trigger t)
	{
		class TriggerMover extends Thread
		{
			private final Trigger t;

			TriggerMover(Trigger t)
			{
				super("Triggermover");
				this.t = t;
			}

			public void run()
			{
				synchronized(trigger)
				{
					trigger.remove(t);
					trigger.add(t);
				}
			}
		}
		
		TriggerMover mover = new TriggerMover(t);
		mover.start();
	}

	/**
	 * Diese Methode schaltet eine bestimmte Fahrstraße aus.
	 * @param route Die auszuschaltende Fahrstraße.
	 */
	final public void removeRoute(final Route route)
	{
		BatchExecuter executer = new BatchExecuter();

		synchronized(route)
		{
			route.computeClearBatches(executer);
			send(executer);
			executer.clear();
			route.clear();
		}
		
		updateCompleteUI();
	}
	
	/**
	 * Diese Methode schaltet alle Fahrstraßen aus.
	 */
	final public void removeAllRoutes()
	{
		deactivateActions();
		List<Route> routes = new ArrayList<Route>();
		routes.addAll(Route.getRoutes());
		BatchExecuter executer = new BatchExecuter();

		for (Route r : routes)
		{
			r.computeClearBatches(executer);
			updateTrackPlan();
		}

		send(executer);
		executer.clear();

		for (Route r : routes)
		{
			r.clear();
		}

		selectRoute(null);
		updateCompleteUI();
	}

	/**
	 * Diese Methode schaltet alle Lämpchen der Lichtsignale der Eisenbahnanlage aus.
	 */
	final public void clearSignals()
	{
		if (log.isDebugEnabled())
		{
			log.debug(">clearSignals()");
		}

		BatchExecuter executer = new BatchExecuter();
		Batch batch = executer.createBatch();
		for (Signal s : model.getSignals())
		{
			s.off();
			s.addCommand(batch);
		}

		// ...und jetzt die Kommandos senden...
		try
		{
			executer.send(connection);
		}
		catch(BatchProcessingException bpe)
		{
			log.error(bpe.getMessage(), bpe);
		}
		finally
		{
			executer.clear();

			if (log.isDebugEnabled())
			{
				log.debug("<clearSignals()");
			}
		}
	}

	/**
	 * Diese Methode schaltet alle Lämpchen der Lichtsignale der Eisenbahnanlage an.
	 */
	final public void testSignals()
	{
		if (log.isDebugEnabled())
		{
			log.debug(">testSignals()");
		}

		BatchExecuter executer = new BatchExecuter();
		Batch batch = executer.createBatch();
		for (Signal s : model.getSignals())
		{
			s.test();
			s.addCommand(batch);
		}

		// ...und jetzt die Kommandos senden...
		try
		{
			executer.send(connection);
		}
		catch(BatchProcessingException bpe)
		{
			log.error(bpe.getMessage(), bpe);
		}
		finally
		{
			executer.clear();

			if (log.isDebugEnabled())
			{
				log.debug("<testSignals()");
			}
		}
	}

	/**
	 * Diese Methode sendet eine PING-Meldung an alle Mikrocontroller.
	 */
	public void pingModelRailWay()
	{
		MrwMessage msg = MrwMessage.createPingMsg();

		try
		{
			for (MicroController ctrl : model.getMicroController())
			{
				ctrl.ping();
			}
			send(msg);

			// Atempause für den CAN-Bus.
			waitForReachability(POLLING_DELAY_PING);
		}
		catch (IOException ioe)
		{
			log.error(ioe.getMessage(), ioe);
		}
	}

	/**
	 * Diese Methode sendet einen Reset an alle Mikrocontroller.
	 */
	public void resetMicroController()
	{
		MrwMessage msg = MrwMessage.createResetMsg();
	
		try
		{
			connection.sync();
			for (MicroController mcu : model.getMicroController())
			{
				mcu.reset();
			}
			send(msg);

			// Atempause für den CAN-Bus.
			waitForReachability(POLLING_DELAY_RESET, DELAY_RESET);
		}
		catch (IOException ioe)
		{
			log.error(ioe.getMessage(), ioe);
		}
	}

	/**
	 * Diese Methode fragt den internen Zustannd aller Mikrocontroller ab. Dieser Zustand umfasst:
	 * <ul>
	 * <li>Versionsnummer</li>
	 * <li>Fehlerzustand des CAN-Controllers</li>
	 * <li>Füllzustand des Sende- und Empfangspuffers</li>
	 * <li>Erreichbarkeit per Ping</li>
	 * </ul>
	 */
	public void queryMicroControllerState()
	{
		List<MrwMessage> msgs = new ArrayList<MrwMessage>();
		msgs.add(MrwMessage.createGetVersionMsg());
		msgs.add(MrwMessage.createQueryErrorMsg());
		msgs.add(MrwMessage.createQueryBufferMsg());
		msgs.add(MrwMessage.createPingMsg());

		try
		{
			for (MicroController ctrl : model.getMicroController())
			{
				for (MrwMessage msg : msgs)
				{
					msg.setSid(ctrl.getId());
					send(msg);
				}
				ctrl.ping();
				BatchRunner.sleep(5L);
			}

			// Atempause für den CAN-Bus.
			waitForReachability(POLLING_DELAY_QUERY);

			log.info("Zustand der Mikrocontroller:");
			for(MicroController ctrl : model.getMicroController())
			{
				log.info(ctrl);
			}

			log.info("Schaltzeiten der Geräte:");
			for (Gleisteil element : model.getTrackElements())
			{
				DeviceUnit dvc = (DeviceUnit)element;
				double switchTime = dvc.getSwitchTime(MicroController.MC_TIMER_QUANTUM);
				if (switchTime > 0)
				{
					log.info(dvc.getName() + ": " + 1000.0 * switchTime + "ms");
				}
			}
		}
		catch (IOException ioe)
		{
			log.error(ioe.getMessage(), ioe);
		}
	}

	/**
	 * Diese Methode fragt den internen Zustannd aller Mikrocontroller ab. Dieser Zustand umfasst:
	 * <ul>
	 * <li>Versionsnummer</li>
	 * <li>Fehlerzustand des CAN-Controllers</li>
	 * <li>Füllzustand des Sende- und Empfangspuffers</li>
	 * <li>Erreichbarkeit per Ping</li>
	 * </ul>
	 */
	public void configMicroController()
	{
		List<MrwMessage> msgs = new ArrayList<MrwMessage>();

		try
		{
			for (MicroController ctrl : model.getMicroController())
			{
				ctrl.addConfigMessages(msgs);
				ctrl.reset();
			}
			for (MrwMessage msg : msgs)
			{
				send(msg);
				BatchRunner.sleep(1L);
			}

			// Atempause für den CAN-Bus.
			waitForReachability(POLLING_DELAY_RESET, DELAY_RECONFIG);

			log.info("Zustand der Mikrocontroller:");
			for(MicroController ctrl : model.getMicroController())
			{
				log.info(ctrl);
			}
		}
		catch (IOException ioe)
		{
			log.error(ioe.getMessage(), ioe);
		}
	}

	/**
	 * Diese Methode wartet auf Erreichbarkeit aller Mikrocontroller mit einem festgelegten Timeout.
	 * @param delay Abfrageintervalle beim Polling.
	 */
	protected void waitForReachability(long delay)
	{
		waitForReachability(delay, 0L);
	}

	/**
	 * Diese Methode wartet auf Erreichbarkeit aller Mikrocontroller mit einem festgelegten Timeout.
	 * @param interval Abfrageintervalle beim Polling.
	 * @param base Mindestwartezeit.
	 */
	public void waitForReachability(long interval, long base)
	{
		if (connection.isReal())
		{
			final Collection<MicroController> MCUs     = model.getMicroController(); 
			final int                         mcuCount = MCUs.size();
			final long                        delay    = ANSWER_DELAY * mcuCount + base;
			final long                        end      = System.currentTimeMillis() + delay;
			      long                        diff;
			      int                         count;

			log.info(LogUtil.printf("Warte %dms auf Antwort...", delay));
			do
			{
				BatchRunner.sleep(interval);

				count = 0;
				for (MicroController mcu : MCUs)
				{
					if (mcu.isReachable())
					{
						count++;
					}
				}
				diff = end - System.currentTimeMillis();
				log.debug(LogUtil.printf("Timeout: %dms, %d MCUs erreichbar.", diff, count));
			}
			while ((diff > 0L) && (count < mcuCount));

			if (model.areControllerReachable())
			{
				setMessage(LogUtil.printf("Alle Mikrocontroller sind nach %dms erreichbar!",
						delay - diff));
			}
			else
			{
				setErrorMessage(LogUtil.printf("%d Mikrocontroller sind nicht erreichbar!",
						mcuCount - count));
			}
		}
		else
		{
			BatchRunner.sleep(3000L);
			setMessage("Erreichbarkeit der Mikrocontroller simuliert.");
		}
	}

	/**
	 * Diese Methode führt die Modelleisenbahn in einen definierten Ausgangszustand. Dabei
	 * werden zuerst alle Actions deaktiviert, danach werden alle Fahrstraßen ausgeschaltet.
	 * Zuletzt werden alle Gleisabschnitte abgeschaltet und die Signale in einen definierten
	 * Zustand gebracht (Hp0, Vr0 bzw. Off bei Dunkeltastung).
	 */
	public void resetModelRailWay()
	{
		if (log.isDebugEnabled())
		{
			log.debug(">resetModelRailWay()");
		}
		
		// Erst alle Actions ausschalten.
		deactivateActions();
		
		// Modelleisenbahn komplett aussschalten.
		BatchExecuter executer = new BatchExecuter();
		Batch batch;

		// Erster Versuch: Alle Routen löschen
		for (Route r : Route.getRoutes())
		{
			r.computeClearBatches(executer);
		}

		// Zweiter Anlauf: Gesamte Anlage in definierten Auszustand bringen.
		batch = executer.createBatch();
		model.off(batch);

		// ...und jetzt die Kommandos senden...
		try
		{
			executer.send(connection);
		}
		catch(BatchProcessingException bpe)
		{
			log.error(bpe.getMessage(), bpe);
		}
		finally
		{
			executer.clear();
		}

		// Alle Fahrstraßen löschen
		List<Route> routes = new ArrayList<Route>();
		routes.addAll(Route.getRoutes());
		if (!routes.isEmpty())
		{
			for (Route r : routes)
			{
				r.clear();
			}
		}

		// UI aktualisieren
		clearSegmentSelection();
		updateCompleteUI();

		if (log.isDebugEnabled())
		{
			log.debug("<resetModelRailWay()");
		}
	}

	/**
	 * Diese Methode fragt den Betriebszustand der Eisenbahnanlage komplett ab.
	 */
	public void queryModelState()
	{
		if (log.isDebugEnabled())
		{
			log.debug(">queryModelState()");
		}

		BatchExecuter executer = new BatchExecuter();
		Batch batch = executer.createBatch();
		model.getState(batch);

		// ...und jetzt die Kommandos senden...
		try
		{
			executer.send(connection);
		}
		catch(BatchProcessingException bpe)
		{
			log.error(bpe.getMessage(), bpe);
		}
		finally
		{
			executer.clear();
		}

		// UI aktualisieren
		updateCompleteUI();

		if (log.isDebugEnabled())
		{
			log.debug("<queryModelState()");
		}
	}

	/**
	 * Diese Methode lässt alle Weichen in die jeweils andere Lage umlaufen.
	 */
	public void turnAllSwitches()
	{
		BatchExecuter executer = new BatchExecuter();
		Batch batch = executer.createBatch();
		for(Gleisteil gt :model.getTrackElements())
		{
			if (!gt.isLocked())
			{
				if (gt instanceof Weiche)
				{
					Weiche swt = (Weiche)gt;
					
					switch(swt.getDirectionCode())
					{
					case LEFT:
						swt.turn(batch, swt.getA(), swt.getC());
						break;
					case RIGHT:
						swt.turn(batch, swt.getA(), swt.getB());
						break;

					default:
						// Not available
						break;
					}
				}
				else if (gt instanceof DKW)
				{
					DKW dkw = (DKW)gt;
					
					switch(dkw.getDirectionCode())
					{
					case CROSS:
						dkw.turn(batch, dkw.getA(), dkw.getC());
						break;
					case ARC:
						dkw.turn(batch, dkw.getA(), dkw.getD());
						break;

					default:
						// Not available
						break;
					}
				}
			}
		}

		// ...und jetzt die Kommandos senden...
		try
		{
			executer.send(connection);
		}
		catch(BatchProcessingException bpe)
		{
			log.error(bpe.getMessage(), bpe);
		}
		finally
		{
			executer.clear();
		}

		// UI aktualisieren
		updateCompleteUI();
	}

	/**
	 * Diese Methode gibt alle ausgewählten Gleisabschnitte zurück. Diese können z.B. für das
	 * Schalten einer Fahrstraße benutzt werden.
	 * @return Der ausführende BatchRunner
	 */
	final public List<Abschnitt> getSegmentSelection()
	{
		return selection;
	}

	/**
	 * Diese Methode fügt einen Gleisabschnitt der Auswahlliste hinzu.
	 * @param segment Der hinzuzufügende Gleisabschnitt. 
	 */
	final public void addSegmentSelection(final Abschnitt segment)
	{
		getSegmentSelection().add(segment);
		updateCompleteUI();
	}

	/**
	 * Diese Methode entfernt einen Gleisabschnitt aus der Auswahlliste.
	 * @param segment Der zu entfernende Gleisabschnitt. 
	 */
	final public void removeSegmentSelection(final Abschnitt segment)
	{
		getSegmentSelection().remove(segment);
		updateCompleteUI();
	}

	/**
	 * Diese Methode löscht die Auswahl an Gleisabschnitten.
	 */
	final public void clearSegmentSelection()
	{
		getSegmentSelection().clear();
		updateClearedSelection();
		updateTrackPlan();
	}

	/**
	 * Diese Methode schaltet eine Fahrstraße gemäß der ausgewählten Gleisabschnitte.
	 * @param shunting  Rangierflag
	 * @param direction Fahrtrichting
	 * @return Der BatchRunner, der die Schaltung ausführt.
	 */
	final public BatchRunner computeRoute(
			final boolean shunting,
			final boolean direction)
	{
		RouteEnableRunner runner = null;

		if (log.isDebugEnabled())
		{
			log.debug(">computeRoute(" + shunting + ", " + direction + ")");
		}
		
		// Route berechnen
		try
		{
			final Route route = model.route(getSegmentSelection(), shunting, direction);

			if (route != null)
			{
				runner = new RouteEnableRunner(this, route);
				runner.enableRouteSelection();
				runner.start();
	
				// UI aktualisieren
				clearSegmentSelection();
			}
		}
		catch (RoutingException re)
		{
			log.error(re);
			setErrorMessage(re.getLocalizedMessage());
			re.getRoute().clear();
		}

		if (log.isDebugEnabled())
		{
			log.debug("<computeRoute(" + shunting + ", " + direction + ")");
		}
		
		return runner;
	}

	/**
	 * Diese Methode verlängert eine bestehende Fahrstraße entlang der ausgewählten Gleisabschnitte.
	 * @param route Die zu verlängernde Fahrstraße.
	 * @return Der ausführende BatchRunner
	 */
	final public BatchRunner extendRoute(final Route route)
	{
		RouteEnableRunner runner = null;

		if (log.isDebugEnabled())
		{
			log.debug(">extendRoute(" + route + ")");
		}

		try
		{
			if (model.appendRoute(route, getSegmentSelection()))
			{
				runner = new RouteEnableRunner(this, route);
				runner.enableRouteSelection();
				runner.start();
		
				// UI aktualisieren
				clearSegmentSelection();
			}
		}
		catch (RoutingException re)
		{
			log.error(re);
			setErrorMessage(re.getLocalizedMessage());
		}
		
		if (log.isDebugEnabled())
		{
			log.debug("<extendRoute(...)");
		}
		return runner;
	}

	/**
	 * Diese Methode aktiviert den Wartungsmodus.
	 * @return Der initialisierte {@link BatchRunner}.
	 */
	public BatchRunner maintainanceMode()
	{
		RouteEnableRunner runner = null;

		if (log.isDebugEnabled())
		{
			log.debug(">maintainanceMode()");
		}
		
		// Route berechnen
		Route route = model.maintainanceRoute(getSegmentSelection());
		if (route != null)
		{
			runner = new RouteEnableRunner(this, route);
			runner.start();
		}
		
		// UI aktualisieren
		clearSegmentSelection();
		updateCompleteUI();

		if (log.isDebugEnabled())
		{
			log.debug("<maintainanceMode()");
		}
		return runner;
	}

	/**
	 * Dieser Callback setzt eine Infomeldung ab.
	 * @param message Die Infomeldung.
	 */
	abstract public    void    setMessage(String message);

	/**
	 * Dieser Callback setzt eine Fehlermeldung ab.
	 * @param message Die Fehlermeldung.
	 */
	abstract public    void    setErrorMessage(String message);

	/**
	 * Dieser Callback aktualisiert den Status auf dem Bildschirm. Typischerweise wird hier die gsamte GUI aktualisiert.
	 */
	abstract public    void    updateCompleteUI();

	/**
	 * Dieser Callback aktualisiert den Zustand des Spurplans.
	 */
	abstract public    void    updateTrackPlan();

	/**
	 * Dieser Callback aktualisiert den Zustand der Fahrspur- und Abschnittsauswahl.
	 */
	abstract public    void    updateClearedSelection();
	
	/**
	 * Dieser Callback aktualisiert den Status der Actions auf dem Bildschirm. Actions kann ein Biermodus oder Touren sein.
	 * @see MrwActionControl
	 * @see BeerMode
	 * @see TourMode
	 */
	abstract public    void    updateActions();

	/**
	 * Dieser Callback deaktiviert alle aktiven Actions. Actions kann ein Biermodus oder Touren sein.
	 * @see MrwActionControl
	 * @see BeerMode
	 * @see TourMode
	 */
	abstract protected void    deactivateActions();

	/**
	 * Dieser Callback deaktiviert eine bestimmte Action. Actions kann ein Biermodus oder Touren sein.
	 * @param route Die zur Action gehörende Fahrstraße.
	 * @see MrwActionControl
	 * @see BeerMode
	 * @see TourMode
	 */
	abstract public void    deactivateAction(Route route);
	
	/**
	 * Diese Methode wählt eine Fahrstraße aus.
	 * @param route Die auszuwählende Fahrstraße.
	 */
	abstract public    void    selectRoute(Route route);

	/**
	 * Diese Methode gibt die aktuell ausgewählte Fahrstraße an. Typischerweise ist das eine Auswahl aus der GUI.
	 * @return Die ausgewählte Fahrstraße.
	 */
	abstract public    Route   getSelectedRoute();
}

	
