/*
**
**	$Filename:	Gleis.java $
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

package de.morknet.mrw.base;

import de.morknet.mrw.batch.Batch;
import de.morknet.mrw.comm.Command;
import de.morknet.mrw.comm.MrwMessage;

/**
 * Diese Klasse repräsentiert ein einfaches Gleis
 * @author sm
 *
 */
public final class Gleis extends Gleisteil
{
	private static final long serialVersionUID = 1L;

	private Gleisteil a;
	private Gleisteil b;
	private final boolean branch;
	private final boolean mainTrack;

	/**
	 * Dieser Konstruktur initialisiert ein einfaches Gleis.
	 * @param abschnitt Der zugehörige Gleisabschnitt.
	 * @param name Der Gleisname.
	 * @param direction Zählrichtung
	 * @param branch Flag, ob es eine Verzweigung ist.
	 * @param mainTrack Flag, ob es ein Hauptgleis ist.
	 */
	public Gleis(
			final Abschnitt abschnitt,
			final String    name,
			final boolean   direction,
			final boolean   branch,
			final boolean   mainTrack)
	{
		super(abschnitt, name, direction);
		this.branch    = branch;
		this.mainTrack = mainTrack;
	}

	/**
	 * Diese Methode verbindet dieses Gleis mit zwei benachbarten {@link Gleisteil}en. Ist eins der beiden Gleisteile null, dann
	 * handelt es sich bei diesem Gleis um ein Stumpfgleis.
	 * @param a Das eine Gleisteil.
	 * @param b Das andere Gleisteil.
	 */
	public void link(Gleisteil a, Gleisteil b)
	{
		this.a = a;
		this.b = b;

		if (aIsHigh)
		{
			if (a != null)
			{
				forward.add(a);
			}
			if (b != null)
			{
				backward.add(b);
			}
		}
		else
		{
			if (a != null)
			{
				backward.add(a);
			}
			if (b != null)
			{
				forward.add(b);
			}
		}
	}

	@Override
	public int validate()
	{
		int errors = 0;

		if ((a == null) && (b == null))
		{
			log.error("Stecker A und B nicht definiert!");
			errors++;
		}
		if (a != null)
		{
			if(!a.hasTrackElement(this))
			{
				log.error("Stecker A ist nicht rückverkoppelt!");
				errors++;
			}
			if (!a.isDirectionCorrect(this, aIsHigh))
			{
				log.error("A ist falschrum gerichtet!");
				errors++;
			}
		}
		if (b != null)
		{
			if(!b.hasTrackElement(this))
			{
				log.error("Stecker B ist nicht rückverkoppelt!");
				errors++;
			}
			if (!b.isDirectionCorrect(this, !aIsHigh))
			{
				log.error("B ist falschrum gerichtet!");
				errors++;
			}
		}
		return errors;
	}

	@Override
	public boolean hasTrackElement(Gleisteil g)
	{
		return (g == a) || (g == b);
	}

	@Override
	protected boolean isDirectionCorrect(Gleisteil g, boolean isGrowing) {
		if ((a == g) && (aIsHigh != isGrowing))
		{
			return true;
		}
		if ((b == g) && (aIsHigh == isGrowing))
		{
			return true;
		}
		return false;
	}

	/**
	 * Diese Methode ermittelt, ob dieses Gleis in der angegebenen Fahrtrichtung stumpf ist.
	 * @param inDirection Die Abfragerichtung. 
	 * @return Flag, ob Gleis in gefragter Richtung stumpf ist.
	 */
	boolean isStump(boolean inDirection)
	{
		return this.aIsHigh == inDirection ? a == null : b == null;
	}

	/**
	 * Diese Methode ermittelt, ob dieses Gleis ein Stumpfgleis ist.
	 * @return Flag, ob dieses Gleis stumpf ist.
	 */
	boolean isStumpTrack()
	{
		return (a == null) || (b == null);
	}

	@Override
	public void turn(Batch batch, Gleisteil prev, Gleisteil next) {
	}

	@Override
	public boolean isBranch()
	{
		return branch;
	}

	@Override
	protected Command getCommand()
	{
		return null;
	}

	@Override
	public MrwMessage createConfigMessage()
	{
		return null;
	}

	/**
	 * Diese Methode ermittelt, ob dieses Gleis ein Hauptgleis ist.
	 * @return Flag, ob dieses Gleis ein Hauptgleis ist.
	 */
	public boolean isMainTrack()
	{
		return mainTrack;
	}
}
