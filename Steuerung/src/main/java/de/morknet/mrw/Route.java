/*
**
**	$Filename:	Route.java $
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.morknet.mrw.base.Abschnitt;
import de.morknet.mrw.base.Gleis;
import de.morknet.mrw.base.Gleissperrsignal;
import de.morknet.mrw.base.Gleisteil;
import de.morknet.mrw.base.Hauptsignal;
import de.morknet.mrw.base.NamedElement;
import de.morknet.mrw.base.Signal;
import de.morknet.mrw.base.Verzweigung;
import de.morknet.mrw.base.Vorsignal;
import de.morknet.mrw.base.Signal.SignalCommand;
import de.morknet.mrw.batch.Batch;
import de.morknet.mrw.batch.BatchExecuter;
import de.morknet.mrw.util.LogUtil;
import de.morknet.mrw.util.NameUtil;
import de.morknet.mrw.util.ReferenceCounter;

/**
 * In der Route sind alle Informationen f�r eine Fahrstra�e gespeichert. Die abgeleitete Liste
 * enth�lt in der Reihenfolge des Durchfahrens alle Gleisteile.
 * @author sm
 *
 */
public class Route extends LinkedList<Gleisteil>
{
	private final static Log log = LogFactory.getLog(Route.class);

	private static final long serialVersionUID = 1L;
	
	private final boolean blockDeallocation;

	/**
	 *  Flag, ob eine automatische Gleisfreigabe beim Durchfahren erfolgen soll.
	 */
	private final static boolean AUTOMATIC_SEGMENT_DEALLOCATION = true;

	/**
	 *  Flag, ob die Fahrstra�e nach dem Durchfahren freigegeben werden soll.
	 */
	private boolean automaticRouteDeallocation;

	/**
	 * Diese Liste enth�lt die Abschnitte als Richtungsvorgabe in der Reihenfolge, �ber
	 * die die Fahrstra�e f�hren soll.
	 */
	private final LinkedList<Abschnitt> routing    = new LinkedList<Abschnitt>();
	
	/**
	 *  Alle an dieser Fahrstra�e beteiligten Signale.
	 */
	private final LinkedHashSet<Signal> ownedSignals = new LinkedHashSet<Signal>();

	/**
	 * Menge der abgenutzten Gleisabschnitte. Diese wurden befahren, sind aber noch Teil der Fahrstra�e.
	 */
	private final HashSet<Abschnitt> usedSegments = new HashSet<Abschnitt>();

	/**
	 * Alle an dieser Fahrstra�e beteiligten Abschnitte in der Reihenfolge ihres Durchfahrens.
	 */
	private final LinkedList<Abschnitt> ownedSegments = new LinkedList<Abschnitt>();

	/**
	 * In dieser Map wird pro Abschnitt die Anzahl der Abzweigungen festgehalten.
	 * Diese Zahl dient zur Ermittlung, ob Hp1 oder Hp2 auf ein Signal gegeben wird.
	 */
	private final ReferenceCounter<Abschnitt> counter = new ReferenceCounter<Abschnitt>();

	/**
	 * Menge aller durch Routen belegten Gleisteile. Wird bei einem Routing festgestellt, dass
	 * ein Gleisteil in dieser Menge enthalten ist, kann die Route �ber dieses Gleisteil nicht
	 * weiter verfolgt werden. Die Gleisteile sind nicht auf eine bestimmte Fahrstra�e gebunden,
	 * sondern bilden die Gleisteile der Gesamtanlage, die nicht zur Fahrstra�enbildung zur
	 * Verf�gung stehen.
	 */
	private final static Set<Gleisteil> allocatedTrackElements = new HashSet<Gleisteil>();

	/**
	 * Liste aller verriegelten Fahrstra�en.
	 */
	private final static List<Route> lockedRoutes = new ArrayList<Route>();
	
	/**
	 * Flag, ob es sich um eine Rangierfahrt handelt. 
	 */
	private final boolean shunting;
	
	/**
	 * In Z�hlrichtung oder gegen Z�hlrichtung.
	 */
	private final boolean direction;

	/**
	 *  Markierung, ob diese Fahrstra�e verriegelt ist.
	 */
	volatile private boolean locked = false;

	/**
	 * Das Modell, das die Fahrstra�e berechnet.
	 */
	private final Modell model;

	/**
	 * Dieses Flag definiert, ob diese Fahrstra�e im Wartungsmodus ist.
	 */
	private final boolean maintainance;

	/**
	 * Dieser Konstruktur initialisiert den Maintainance Mode.
	 * @param model Die Modelleisenbahn.
	 */
	Route(final Modell model)
	{
		this(model, true, true, false, false, true);
	}

	/**
	 * Dieser Konstruktur initialisiert eine einfache Fahrstra�e. Diese wird manuell durch Knopfdruck ausgel�st.
	 * @param model Die Modelleisenbahn.
	 * @param shunting Rangierflag.
	 * @param direction Fahrtrichtung.
	 */
	Route(final Modell model, final boolean shunting, final boolean direction)
	{
		this(model, shunting, direction, true, !shunting, false);
	}

	/**
	 * Dieser Konstruktur erzeugt eine normale Fahrstra�e.
	 * @param model Die Modelleisenbahn.
	 * @param shunting Rangierflag.
	 * @param direction Fahrtrichtung.
	 * @param automaticDeallocation Automatische Gleisfreigabe.
	 * @param blockDeallocation Blockweise Freigabe.
	 */
	Route(
			final Modell  model,
			final boolean shunting,
			final boolean direction,
			final boolean automaticDeallocation,
			final boolean blockDeallocation)
	{
		this(model, shunting, direction, automaticDeallocation, blockDeallocation, false);
	}

	/**
	 * Dies hier ist der eigentliche Konstruktur. Hier werden die Werte dieser Fahrstra�e initialisert.
	 * @param model Die Modelleisenbahn.
	 * @param shunting Rangierflag.
	 * @param direction Fahrtrichtung.
	 * @param automaticDeallocation Automatische Gleisfreigabe.
	 * @param blockDeallocation Blockweise Freigabe.
	 * @param maintainance Flag f�r Wartungsmodus.
	 */
	private Route(
			final Modell  model,
			final boolean shunting,
			final boolean direction,
			final boolean automaticDeallocation,
			final boolean blockDeallocation,
			final boolean maintainance)
	{
		this.model     = model;
		this.shunting  = shunting;
		this.direction = direction;
		this.automaticRouteDeallocation = automaticDeallocation;
		this.blockDeallocation          = shunting ? false : blockDeallocation;
		this.maintainance               = maintainance;
	}

	/**
	 * Diese Methode initialisiert die Fahrstra�e im Wartungsmodus.
	 * @param segments Alle Gleisabschnitte.
	 * @param signals Alle Signale.
	 * @param selection Die ausgew�hlten Gleisabschnitte.
	 */
	void prepareMaintainanceMode(
			final Collection<Abschnitt> segments,
			final Collection<Signal>    signals,
			final Collection<Abschnitt> selection)
	{
		synchronized(this)
		{
			if (lockedRoutes.size() > 0)
			{
				throw new IllegalStateException(
						"Es darf zu Wartungszwecken keine andere Fahrstra�e vorhanden sein!");
			}
			synchronized(ownedSegments)
			{
				ownedSegments.clear();
				for (Abschnitt a : segments)
				{
					if (!a.isOccupied())
					{
						ownedSegments.add(a);
					}
					else if (selection.contains(a))
					{
						ownedSegments.add(a);
					}
				}
		
				ownedSignals.clear();
				ownedSignals.addAll(signals);
				for (Abschnitt a : ownedSegments)
				{
					this.addAll(a.getTrackElements());
				}
			}
			
			synchronized(allocatedTrackElements)
			{
				allocatedTrackElements.addAll(this);
			}
		}
	}

	/**
	 * Diese Methode berechnet alle Kommandos, um diese Fahrstra�e in den Wartungsmodus zu pberf�hren.
	 * @param executer Der BatchExecuter.
	 * @return Der �bergebene BatchExecuter.
	 */
	private BatchExecuter turnMaintainance(final BatchExecuter executer)
	{
		Batch batch;
		
		for (Gleisteil gt : this)
		{
			gt.setRoute(this);
		}

		batch = executer.createBatch();
		synchronized(ownedSegments)
		{
			for (Signal s : ownedSignals)
			{
				s.off();
				s.addCommand(batch);
			}
	
			batch = executer.createBatch();
			for (Abschnitt a : ownedSegments)
			{
				a.on(batch);
			}
		}
		return executer;
	}

	/**
	 * Diese Methode f�gt einen zur Fahrstra�enbestimmung ben�tigten Fixpunkt hinzu.
	 * 
	 * @param a Ein weiterer festgelegter Routenpunkt.
	 */
	public void addRoute(final Abschnitt a)
	{
		routing.add(a);
	}

	/**
	 * Hier wird eine erfolgreich geroutete Fahrstra�e f�r die Freischaltung
	 * vorbereitet. Alle beteiligten Gleisabschnitte und Signale werden ermittelt.
	 */
	void prepare()
	{
		synchronized(this)
		{
			Abschnitt last = null;
	
			// Mehrfache Abschnitte reduzieren
			synchronized(ownedSegments)
			{
				ownedSegments.clear();
				for (Gleisteil gt : this)
				{
					Abschnitt a = gt.getSegment();
					if (a != last)
					{
						ownedSegments.add(a);
						last = a;
					}
				}
		
				// Alle Signale in Fahrtrichtung ausw�hlen
				ownedSignals.clear();
				for (Abschnitt abschnitt : ownedSegments)
				{
					ownedSignals.addAll(abschnitt.getSignals(direction));
				}
			}
		}
	}

	/**
	 * Diese Methode schaltet alle Weichen der Fahrstra�e in die richtige Lage.
	 * @param executer
	 * @return
	 * @throws RoutingRequirementsMissingException 
	 */
	private Batch turnRail(final BatchExecuter executer) throws RoutingRequirementsMissingException
	{
		if (size() < 2)
		{
			throw new RoutingRequirementsMissingException(this, "Fahrstra�e hat nicht genug Gleisteile!");
		}

		/*
		 * Dieser Codeabschnitt schaltet die Fahrstra�e.
		 */
		Batch batch = executer.createBatch();

		Gleisteil [] teile = new Gleisteil[size()];
		Gleisteil prev, teil, next;

		this.toArray(teile);
		prev = get(0);
		teil = get(1);
		for (int i = 1; i < (size() - 1); i++)
		{
			next = get(i+1);
			teil.turn (batch, prev, next);

			prev = teil;
			teil = next;
		}

		counter.clear();
		for (Gleisteil gt : this)
		{
			Abschnitt abschnitt = gt.getSegment();
			if (gt.isBranch())
			{
				counter.count(abschnitt);
			}

			gt.setRoute(this);
		}
		return batch;
	}

	/**
	 * Diese Methode stellt an Hand von Abzweigen fest, ob das
	 * Signal langsam �berfahren werden darf. Es ist konkret die
	 * Wahl zwischen Hp1 und Hp2.
	 * @param abzweige Zahl der Abzweige
	 * @return Signalkommando f�r die Langsamfahrstelle.
	 */
	private SignalCommand computeSignalSymbol(Abschnitt segment, int abzweige)
	{
		return usedSegments.contains(segment) ?
				SignalCommand.S0 :
				( abzweige > 1 ? SignalCommand.S2 : SignalCommand.S1);
	}

	/**
	 * Diese Methode schaltet alle Signale der Fahrstra�e.
	 * @param executer
	 * @return Der Stapel mit den Signalschaltkommandos.
	 * @throws RoutingRequirementsMissingException 
	 */
	private Batch turnSignal(BatchExecuter executer) throws RoutingRequirementsMissingException
	{
		synchronized(ownedSegments)
		{
			Vorsignal     vs  = null;
			Hauptsignal   hs  = null;
			SignalCommand sb  = SignalCommand.S0;
			int           max = ownedSegments.size() - 1;
			
			if (max < 0)
			{
				throw new RoutingRequirementsMissingException(this, "Fahrstra�e enth�lt keine Gleisabschnitte!");
			}
			Abschnitt last = ownedSegments.get(max);
			Batch batch = executer.createBatch();
			int abzweige = counter.getValue(last);
	
			log.debug("Berechne Signalbilder...");
			if (!last.isStumpf(direction))
			{
				// Wenn kein Stumpfgleis, nochmals erniedrigen, um letztes
				// Gleis nicht in Signalberechnung mit einzubeziehen.
				max--;
			}
			else
			{
				// Da kommt kein Haupsignal mehr. Daher kann ein kombiniertes Vorsignal dunkelgetastet werden.
				sb = SignalCommand.OFF;
			}
	
			/*
			 * Die Abschnitte werden vordersten Gleisabschnitt zum hintersten durchiteriert. So k�nnen die Zust�nde der
			 * Hauptsignale an die davor stehenden Vorsignale weitergereicht werden. Gleissperrsignale
			 * werden zwar wie Hauptsignale signalisiert, bestimmen jedoch keine vorhergehenden Vorsignale.
			 */
			for (int i = max; i >= 0; i--)
			{
				Abschnitt segment = ownedSegments.get(i);
				
				int act = counter.getValue(segment);
				abzweige += act;
		
				for (Signal s : segment.getSignals(direction))
				{
					if (s instanceof Hauptsignal)
					{
						sb = computeSignalSymbol(segment, abzweige);
						hs = (Hauptsignal)s;
						sb = hs.setSignal(sb, shunting);
						vs = null;
						abzweige = 0;
	
						log.debug("HS " + s.getNumber() + ": " + sb + " Abzweige: " + abzweige);
					}
					else if (s instanceof Vorsignal)
					{
						if (usedSegments.contains(segment) || shunting)
						{
							sb = SignalCommand.S0;
						}
	
						vs = (Vorsignal)s;
						sb = vs.setSignal(sb, shunting);
						log.debug("VS " + s.getNumber() + ": " + sb);
					}
					else if (s instanceof Gleissperrsignal)
					{
						SignalCommand gsb = computeSignalSymbol(segment, abzweige);
						gsb = s.setSignal(gsb, shunting);
						log.debug("GS " + s.getNumber() + ": " + gsb);
					}
					else
					{
						log.info("Unbekanntes Signal " + s.getNumber());
					}
				}
			}
			
			log.debug("Schalte Signale...");
			for(Signal s : ownedSignals)
			{
				s.addCommand(batch);
			}
			return batch;
		}
	}

	/**
	 * Diese Methode schaltet die Gleisabschnitte ein. Dabei werden die Abschnitte von hinten nach
	 * vorne der Reihe nach eingeschaltet, so dass der erste Abschnitt mit dem Zug als letzter aktiviert wird.
	 * @param executer Die Stapelausf�hrung, dass den neuen Abschnittsstapel erh�lt.
	 * @return Der Stapel zum Einschalten der Gleisabschnitt.
	 */
	private Batch turnOn(BatchExecuter executer)
	{
		Batch batch = executer.createBatch();
		synchronized(ownedSegments)
		{
			int max = ownedSegments.size();
	
			Abschnitt last = getLastSegment();
			if (!last.isStumpf(direction))
			{
				log.debug("Letzter Abschnitt wird abgeschaltet...");
				max--;
				last.off(batch, false);
			}
	
			log.debug("Schalte Abschnitte frei...");
			for (int i = max - 1; i >= 0;i--)
			{
				ownedSegments.get(i).on(batch);
			}
		}			
		return batch;
	}

	/**
	 * Diese Methode bereitet die Stapelausf�hrung zum Schalten einer Fahrstra�e vor.
	 * @param executer Der ausf�hrende {@link BatchExecuter}.
	 * @return Die Stapelausf�hrung zum Schalten einer Fahrstra�e.
	 * @throws RoutingRequirementsMissingException 
	 */
	public BatchExecuter turn(final BatchExecuter executer) throws RoutingRequirementsMissingException
	{
		synchronized(this)
		{
			Batch batch;
	
			if (maintainance)
			{
				return turnMaintainance(executer);
			}
	
			try
			{
				batch = turnRail(executer);
				if (batch != null)
				{
					batch = turnSignal(executer);
					if (batch != null)
					{
//						batch.setDelay(250L);
						batch = turnOn(executer);
						if (batch != null)
						{
							return executer;
						}
					}
				}
			}
			catch(RoutingRequirementsMissingException re)
			{
				executer.clear();
				throw re;
			}
			return null;
		}
	}

	/**
	 * Listet ein Gleisteil vorl�ufig als belegt. So kann es nicht wiederverwendet werden.
	 * @param gt Das belegende Gleisteil.
	 */
	public void addTrackElement(Gleisteil gt)
	{
		synchronized(allocatedTrackElements)
		{
			allocatedTrackElements.add(gt);
		}
	}

	/**
	 * Entfernt das Gleisteil aus der Liste der belegten Gleisteile. So kann das Gleisteil wiederverwendet
	 * werden.
	 * @param gt das zu entfernende Gleisteil.
	 */
	public void removeTrackElement(Gleisteil gt)
	{
		synchronized(allocatedTrackElements)
		{
			allocatedTrackElements.remove(gt);
		}
	}
	
	/**
	 * Diese Methode f�llt einen BatchExecuter mit Kommandos, um diese Fahrstra�e zwangsabzuschalten.
	 * @param executer Der BatchExecuter der die Batches mit den Freischaltauftr�gen erh�lt.
	 */
	public void computeForcedClearBatch(BatchExecuter executer)
	{
		Batch batch = executer.createBatch();

		synchronized(ownedSegments)
		{
			for (Abschnitt a : ownedSegments)
			{
				a.off(batch, true);
				usedSegments.add(a);
			}
		}
	}

	/**
	 * Diese Methode erzeugt Kommandos, um alle Gleisabschnitte dieser Fahrstra�e
	 * wieder freizugeben. Im Wartungsmodus werden alle Gleisabschnitte abgeschaltet.
	 * @param executer Der BatchExecuter der die Batches mit den Freischaltauftr�gen erh�lt.
	 */
	public void computeClearBatches(final BatchExecuter executer)
	{
		Batch batch = executer.createBatch();
		if (maintainance)
		{
			/*
			 * Im Wartungsmodus werden bei Freigabe alle Gleisabschnitte abgeschaltet.
			 */
			for (Abschnitt a : model.getSegments())
			{
				a.off(batch, true);
				usedSegments.add(a);
			}
		}
		else
		{
			synchronized(ownedSegments)
			{
				/*
				 * Das hier ist ein Kosmetik-Batch, der die kombinierten Lichtvorsignale
				 * zuerst auf Vr0 schaltet, bevor sie dunkelgetastet werden.
				 */
				for (Abschnitt a : ownedSegments)
				{
					a.preSignalOff(batch, direction);
				}
	
				// Hier kommt die eigentliche Fahrstra�enaufl�sung.
				batch = executer.createBatch();
				for (Abschnitt a : ownedSegments)
				{
					a.directedOff(batch, direction);
					usedSegments.add(a);
				}
			}
		}
	}
	
	/**
	 * Diese Methode f�llt die Batches mit Kommandos, um alle Gleisabschnitte bis zum angegebenen Gleisabschnitt
	 * aufzul�sen.
	 * @param executer Der BatchExecuter, der die Batches enthalten wird.
	 * @param targetSegment Der hinterste freizugebende Gleisabschnitt.
	 */
	public void computeClearUptoSegmentBatches(
			final BatchExecuter executer,
			final Abschnitt     targetSegment)
	{
		/*
		 * Das hier ist ein Kosmetik-Batch, der die kombinierten Lichtvorsignale
		 * zuerst auf Vr0 schaltet, bevor sie dunkelgetastet werden.
		 */
		final Batch batch1 = executer.createBatch();

		/*
		 * Das hier ist der eigentliche Ausschalt-Batch.
		 */
		final Batch batch2 = executer.createBatch();

		/*
		 * Es werden alle Abschnitte ausgeschaltet, bis der Zielabschnitt gefunden wurde.
		 */
		synchronized(ownedSegments)
		{
			if (ownedSegments.contains(targetSegment))
			{
				Iterator<Abschnitt> it = ownedSegments.iterator();
				Abschnitt segment;
	
				do
				{
					if (it.hasNext())
					{
						segment = it.next();
						log.debug("   " + segment);
						if (segment.isEnabled())
						{
							if (!usedSegments.contains(segment))
							{
								segment.preSignalOff(batch1, direction);
							}
							segment.directedOff(batch2, direction);
						}
						usedSegments.add(segment);
					}
					else
					{
						segment = null;
					}
				}
				while ((segment != null) && (segment != targetSegment));
			}
		}
	}

	/**
	 * L�st diese Fahrstra�e komplett auf.
	 */
	public void clear()
	{
		synchronized (this)
		{
			synchronized(lockedRoutes)
			{
				/*
				 * Hier werden alle Referenzen aufgehoben.
				 */
				for (Gleisteil gt : this)
				{
					synchronized(gt)
					{
						gt.setRoute(null);
						removeTrackElement(gt);
					}
				}

				/*
				 * Hier werden alle Listen geleert.
				 */
				synchronized(ownedSegments)
				{
					ownedSegments.clear();
					ownedSignals.clear();
				}
				usedSegments.clear();
				routing.clear();
				counter.clear();
				super.clear();
				locked = false;
				lockedRoutes.remove(this);
			}
		}
	}
	
	/**
	 * L�scht alle Abschnitte bis zum angegebenen Abschnitt aus einer Fahrstra�e und entfernt die entsprechenden Referenzen.
	 * @param targetSegment Der hinterste freizugebende Gleisabschnitt.
	 */
	public void clearUptoSegment(Abschnitt targetSegment)
	{
		log.debug(" >clearUptoSegment(" + targetSegment.getNumber() + ")");
		synchronized(ownedSegments)
		{
			log.debug(">>clearUptoSegment(" + targetSegment.getNumber() + ")");
			Abschnitt segment;

			if (hasBlockDeallocation() && targetSegment.hasMainSignalWithSwitch(direction))
			{
				/*
				 * Hier werden die vordersten Gleisabschnitte solange frei gegeben, bis der Zielabschnitt
				 * erreicht ist oder ein abgeschalteter Abschnitt gefunden wurde. Dieser Abschnitt bleibt dann
				 * erhalten.
				 */
				while((!ownedSegments.isEmpty()) &&
						((segment = ownedSegments.getFirst()) != targetSegment) &&
						segment.isDisabled())
				{
					clearSegment(segment);
				}
			}
			else
			{
				/*
				 * Hier werden die vordersten Gleisabschnitte solange frei gegeben, bis der Zielabschnitt
				 * inklusive gefunden wurde.
				 */
				do
				{
					if (!ownedSegments.isEmpty())
					{
						segment = ownedSegments.getFirst();
						if (segment.isDisabled())
						{
							clearSegment(segment);
						}
					}
					else
					{
						segment = null;
					}
				}
				while ((segment != null) && (segment != targetSegment) && segment.isDisabled());
			}
			log.debug("<<clearUptoSegment(" + targetSegment.getNumber() + ")");
		}
		log.debug(" <clearUptoSegment(" + targetSegment.getNumber() + ")");
	}

	/**
	 * Diese Methode gibt einen einzelnen Gleisabschnitt frei.
	 * @param segment Der Gleisabschnitt.
	 */
	private void clearSegment(Abschnitt segment)
	{
		log.debug(LogUtil.printf("Gebe Abschnitt %s frei.", segment.getNumber()));

		// Gleisteile aufr�umen
		for (Gleisteil gt : segment.getTrackElements())
		{
			synchronized(gt)
			{
				// Gleisteil hat keine Fahrstra�e
				gt.setRoute(null);
				
				// Entfernen aus Liste aller benutzten Gleisteile
				removeTrackElement(gt);
				
				// Entfernen aus dieser Fahrstra�e
				remove(gt);
			}
		}
		
		// Signale aufr�umen
		synchronized(ownedSegments)
		{
			for (Signal s : segment.getSignals(direction))
			{
				ownedSignals.remove(s);
			}
		
			// Abschnitt aufr�umen
			if (routing.getFirst() == segment)
			{
				routing.remove(segment);
			}
			ownedSegments.remove(segment);
			usedSegments.remove(segment);
		}
	}

	/**
	 * Diese Methode pr�ft, ob ein Gleisteil f�r das Suchen einer Fahrstra�e brauchbar ist.
	 * @param teil Das zu pr�fende Gleisteil
	 * @return True, wenn Gleisteil f�r Fahrstra�e geeignet ist.
	 */
	public boolean isFree(Gleisteil teil)
	{
		synchronized(this)
		{
			Abschnitt start = routing.getFirst();
	
			if (teil.getRoute() != null)
			{
				// Gleisteil ist schon f�r durch Route belegt.
				return false;
			}

			if (shunting && (teil.getGruppe() != start.getGroup()))
			{
				// Beim Rangieren darf die Fahrstra�e die Gruppe nicht
				// verlassen.
				return false;
			}
			
			synchronized(allocatedTrackElements)
			{
				if (allocatedTrackElements.contains(teil))
				{
					// Gleisteil ist schon f�r Fahrstra�e zumindest verplant.
					return false;
				}
			}
			
			// Im ersten Abschnitt darf eine Lok stehen 
			if (start == teil.getSegment())
			{
				return true;
			}
			
			// Letzter Test: Der Gleisabschnitt darf nicht durch Lok belegt sein.
			return teil.isFree();
		}
	}

	/**
	 * Listet alle Gleisteile dieser Fahrstra�e auf.
	 */
	public void dump()
	{
		if (log.isDebugEnabled())
		{
			log.debug("===========================================================");
			log.debug("Liste der Gleisteile in der Reihenfolge des Durchfahrens...");
			synchronized(this)
			{
				for (NamedElement teil : this)
				{
					log.debug("  " + teil.getName());
				}
			}
			log.debug("===========================================================");
		}
	}
	
	/**
	 * Baut einen Text, der diese Fahrstra�e beschreibt auf.
	 * @return Der Kurztext dieser Fahrstra�e
	 */
	public String toString()
	{
		synchronized(this)
		{
			if (maintainance)
			{
				return "Wartung";
			}

			StringBuilder buffer = new StringBuilder("Route ");
			
			for (Abschnitt a : routing)
			{
				if (a != routing.getFirst())
				{
					buffer.append("/");
				}
				buffer.append(a.getNumber());
			}
			buffer.append(" ").append(NameUtil.logRouteType(this));

			return buffer.toString();
		}
	}

	/**
	 * Gibt zur�ck, ob diese Fahrstra�e dem Rangieren dient.
	 * @return Wird auf dieser Fahrstra�e rangiert.
	 */
	public boolean isShunting()
	{
		return shunting;
	}

	/**
	 * Gibt aus, ob die Fahrstra�e in Z�hlrichtung gerichtet ist.
	 * @return Fahrtrichtung relativ zur Z�hlrichtung.
	 */
	public boolean getDirection()
	{
		return direction;
	}
	
	/**
	 * Gibt zur�ck, ob diese Fahrstra�e gegen Ver�nderung verriegelt ist.
	 * @return Der Verriegelungszustand dieser Fahrstra�e
	 */
	synchronized public boolean isLocked()
	{
		return locked;
	}

	/**
	 * Diese Methode gibt zur�ck, ob sich diese Fahrstra�e im Wartungsmodus befindet.
	 * @return True, wenn Fahrstra�e im Wartungsmodus. 
	 */
	public boolean isMaintainance()
	{
		return this.maintainance;
	}

	/**
	 * Verriegelt die Fahrstra�e, um Ver�nderungen zu verhindern.
	 */
	public void lock()
	{
		synchronized(this)
		{
			synchronized (lockedRoutes)
			{
				/*
				 * Alle beteiligten Signale werden gegen Verstellen verriegelt. Beim
				 * Verl�ngern der Fahrstra�e k�nnen sich Signalbilder ver�ndern (Vorsignale!), 
				 * diese Signalbilder werden neu �bernommen.
				 */
				for (Signal s : ownedSignals)
				{
					s.setState();
				}
				
				/*
				 * Alle Gleisteile werden gegen weiteren Zugriff verriegelt.
				 */
				for(Gleisteil gt : this)
				{
					gt.lock();
				}
				
				/*
				 * Die Fahrstra�e selbst wird verriegelt.
				 */
				if (!locked)
				{
					lockedRoutes.add(this);
				}
				locked = true;
			}
		}
	}
	
	/**
	 * Gibt zur�ck, ob Fahrstra�en geschaltet sind.
	 * @return Gibt es Fahrstra�en
	 */
	public static boolean hasRoutes()
	{
		synchronized (lockedRoutes)
		{
			return !lockedRoutes.isEmpty();
		}
	}

	/**
	 * Gibt die Liste der Fahrstra�en zur�ck.
	 * @return Liste der Fahrstra�en
	 */
	public static List<Route> getRoutes()
	{
		synchronized(lockedRoutes)
		{
			return lockedRoutes;
		}
	}
	
	/**
	 * Gibt die Fahrstra�e zur�ck, in der der angegebene Abschnitt ist.
	 * @param abschnitt Der Abschnitt, der seine Route sucht.
	 * @return Die Route passend zum Abschnitt.
	 */
	public static Route findRoute(Abschnitt abschnitt)
	{
		synchronized (lockedRoutes)
		{
			for (Route r : lockedRoutes)
			{
				for (Abschnitt a : r.ownedSegments)
				{
					if (a == abschnitt)
					{
						return r;
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * Gibt den vorhergehenden Abschnitt des angegebenen Abschnitts zur�ck. 
	 * @param abschnitt Abschnitt, von dem r�ckw�rts gesucht werden soll.
	 * @return Der Vorg�nger dieses Abschnitts.
	 */
	public Abschnitt getPrevSegment(Abschnitt abschnitt)
	{
		synchronized(ownedSegments)
		{
			int idx = ownedSegments.indexOf(abschnitt);
			if (idx > 0)
			{
				idx--;
				return ownedSegments.get(idx);
			}
		}
		return null;
	}

	/**
	 * Gibt den n�chsten Abschnitt des angegebenen Abschnitts zur�ck.
	 * @param abschnitt Abschnitt, von dem vorw�rts gesucht werden soll.
	 * @return Der Nachfolger dieses Abschnitts.
	 */
	public Abschnitt getSuccSegment(Abschnitt abschnitt)
	{
		synchronized(ownedSegments)
		{
			int idx = ownedSegments.indexOf(abschnitt);
			if (idx >= 0)
			{
				idx++;
				if (idx < ownedSegments.size())
				{
					return ownedSegments.get(idx);
				}
			}
		}
		return null;
	}

	/**
	 * Pr�ft, ob ein Zug in diesen Abschnitt eingefahren ist.
	 * @param abschnitt Der zu pr�fende Abschnitt
	 * @return True, wenn ein Zug eingefahren ist.
	 */
	public boolean checkIfSegmentEntered(Abschnitt abschnitt)
	{
		if (!maintainance)
		{
			Abschnitt prev = getPrevSegment(abschnitt);
			if (prev != null)
			{
				return abschnitt.isOccupied() && prev.isFree();
			}
		}
		return false;
	}

	/**
	 * Testet, ob der angegebene Abschnitt verlassen wurde. Das setzt voraus, dass die n�chste Abschnitt belegt ist.
	 * @param abschnitt Der zu trestende Abschnitt.
	 * @return Test, ob angegebener Abschnitt verlassen wurde.
	 */
	public boolean checkIfSegmentLeft(Abschnitt abschnitt)
	{
		if (!maintainance)
		{
			Abschnitt succ = getSuccSegment(abschnitt);
			if (succ != null)
			{
				return abschnitt.isFree() && succ.isOccupied();
			}
		}
		return false;
	}

	/**
	 * Gibt den ersten Abschnitt dieser Fahrstra�e zur�ck.
	 * @return Der erste Abschnitt dieser Fahrstra�e.
	 */
	public Abschnitt getFirstSegment()
	{
		return maintainance ? null : ownedSegments.getFirst();
	}

	/**
	 * Gibt den letzten Abschnitt dieser Fahrstra�e zur�ck.
	 * @return Der letzte Abschnitt dieser Fahrstra�e.
	 */
	public Abschnitt getLastSegment()
	{
		Abschnitt result;

		if (maintainance)
		{
			result = null;
		}
		else
		{
			synchronized(ownedSegments)
			{
				int size = ownedSegments.size();
				
				result = size > 0 ? ownedSegments.get(size - 1) : null;
			}
		}
		return result;
	}

	/**
	 * Gibt zur�ck, ob die automatische Gleisfreigabe aktiv ist.
	 * @return Aktivierung der automatischen Gleisfreigabe.
	 */
	public static boolean hasAutomaticSegmentDeallocation()
	{
		return AUTOMATIC_SEGMENT_DEALLOCATION;
	}

	/**
	 * Diese Methode ermittelt, ob Gleisabschnitte dieser Fahrstra�e blockweise wieder freigegeben werden.
	 * @return Blockweise Freigabe von Gleisabschnitten.
	 */
	public boolean hasBlockDeallocation()
	{
		return blockDeallocation;
	}

	/**
	 * Gibt zur�ck, ob die Fahrstra�e nach dem Durchfahren automatisch aufgel�st
	 * werden soll.
	 * @return Automatische Fahrstra�enfreigabe.
	 */
	public boolean hasAutomaticRouteDeallocation()
	{
		return automaticRouteDeallocation && (!maintainance);
	}

	/**
	 * Diese Methode aktiviert die automatische Fahrstra�enaufl�sung.
	 */
	public void enableAutomaticRouteDeallocation()
	{
		automaticRouteDeallocation = true;
	}

	/**
	 * Diese Methode ermittelt die Fahrstra�e, zu der die �bergebene Verzweigung geh�rt.
	 * @param branch Die Verzweigung, zu der eine Fahrstra�e gesucht wird.
	 * @return Die gefundene Fahrstra�e.
	 */
	public static Route findConflictingRoute(Verzweigung branch)
	{
		synchronized(branch)
		{
			if (branch.isLocked())
			{
				Route conflict = branch.getRoute();
				if (!conflict.contains(branch))
				{
					throw new IllegalStateException("Verzweigung geh�rt der falschen Fahrstra�e an.");
				}
				if (!conflict.maintainance)
				{
					return conflict;
				}
			}
			return null;
		}
	}
	
	/**
	 * Diese Methode gibt die Zahl der St�tzpunkte zur�ck.
	 * @return Zahl der St�tzpunkte, die die Fahrstra�e festlegt.
	 */
	public int getHopCount()
	{
		return routing.size();
	}
	
	/**
	 * Diese Methode gibt das letzte Gleisteil zur�ck.
	 * @return Das letzte Gleisteil oder null, wenn keins vorhanden ist.
	 */
	private Gleisteil peekLastElement()
	{
		int size = size();
		
		return size > 0 ? get(size - 1) : null;
	}

	/**
	 * Diese Methode pr�ft, ob das zu einem Gleisteil benachbarte Gleisteil ebenfalls
	 * an der Fahrstra�e beteiligt ist.
	 * @param gt Das Gleisteil
	 * @param next Das benachbarte Gleisteil, das darauf gepr�ft wird, ob es in dieser Fahrstra�e benachbart ist.
	 * @return Ob das benachbarte Gleisteil an dieser Fahrstra�e beteiligt ist.
	 */
	public boolean isPart(Gleisteil gt, Gleisteil next)
	{
		boolean result;
		
		if (gt.getRoute() != this)
		{
			throw new TrackNotRoutedException(this, gt);
		}
		if (gt instanceof Gleis)
		{
			// Ein einzelnes beteiligtes Gleis ist naturgem�� immer beteiligt ;-) 
			result = true;
		}
		else if ((next == null) || (gt == peekLastElement()))
		{
			// Pr�fung auf letztes Gleisteil oder Abstellgleis.
			result = true;
		}
		else
		{
			// Pr�fung des Nachbargleisteils, ob es auch Nachbargleisteil
			// in der Fahrstra�e ist.
			int idxA = indexOf(gt);
			int idxB = indexOf(next);
			int diff = idxA - idxB;
	
			result = (idxB >= 0) && ((diff == 1) || (diff == -1));
		}
		return result;
	}
}
