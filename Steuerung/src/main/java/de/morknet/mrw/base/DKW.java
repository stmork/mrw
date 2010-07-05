/*
**
**	$Filename:	DKW.java $
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

package de.morknet.mrw.base;

import de.morknet.mrw.UnknownDirectionCodeException;
import de.morknet.mrw.UnknownTurnStateException;
import de.morknet.mrw.batch.Batch;
import de.morknet.mrw.comm.Command;

/**
 * Diese Klasse repräsentiert eine Doppelkreuzungsweiche (DKW).
 * @author smork
 *
 */
public final class DKW extends Verzweigung
{
	private static final long serialVersionUID = 1L;

	private Gleisteil a;
	private Gleisteil b;
	private Gleisteil c;
	private Gleisteil d;
	private final boolean bcIstBevorzugt; // Kreuz

	/**
	 * Diese Aufzählung liefert alle Schaltzustände einer DKW. Die Ausrichtung der banachbarten
	 * Gleisteile sieht so aus:
	 * <pre>
	 *     A_   _C   
	 *       \_/
	 *      _/ \_
	 *     B     D
	 * </pre>
	 * @author sm
	 *
	 */
	public enum TurnDirection
	{
		/**
		 * Weichenlage Bogenfahrt.
		 */
		AC_BD(DirectionCode.ARC,   Command.SETLFT, "Bogen"),  // Bogen

		/**
		 * Weichenlage Überkreuzfahrt.
		 */
		AD_BC(DirectionCode.CROSS, Command.SETRGT, "Kreuz");  // Kreuz
		
		private final DirectionCode code;
		private final Command       cmd;
		private final String        text;
		
		private TurnDirection(DirectionCode direction, Command cmd, String text)
		{
			this.code = direction;
			this.cmd  = cmd;
			this.text = text;
		}

		/**
		 * Diese Methode gibt die Weichenlage als {@link DirectionCode} zurück.
		 * @return Die Weichenlage als {@link DirectionCode}.
		 */
		public DirectionCode getDirectionCode()
		{
			return this.code;
		}
		
		/**
		 * Diese Methode gibt das Schaltkommando zurück.
		 * @return Das Schaltkommando.
		 */
		public Command getCommand()
		{
			return this.cmd;
		}

		/**
		 * Weichenlage als Text.
		 */
		public String toString()
		{
			return text;
		}
	}
	
	private TurnDirection dir;

	/**
	 * Dieser Konstruktor initialisiert die DKW.
	 * @param segment Der dazugehörende Gleisabschnitt.
	 * @param number Die Weichennummer.
	 * @param aIsHigh Gleisteil a zeigt in Zählrichtung.
	 * @param bcIstAbzweig
	 * @param adIstAbzweig Richtung 
	 * @param limitStop DKW hat Endabschaltung.
	 */
	public DKW(
		Abschnitt segment,
		String    number,
		boolean   aIsHigh,
		boolean   bcIstAbzweig,
		boolean   adIstAbzweig,
		boolean   limitStop)
	{
		super(segment, number, aIsHigh, limitStop);
		this.bcIstBevorzugt = true;
		turnAC_BD();
	}

	/**
	 * Diese Methode definiert die benachbarten Gleisteile.
	 * @param a Gleisteil links oben
	 * @param b Gleisteil links unten
	 * @param c Gleisteil rechts oben
	 * @param d Gleisteil rechts unten
	 */
	public void link(Gleisteil a,Gleisteil b, Gleisteil c, Gleisteil d)
	{
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		
		if (aIsHigh)
		{
			forward.add(a);
			forward.add(b);
			if (bcIstBevorzugt)
			{
				backward.add(c);
				backward.add(d);
			}
			else
			{
				backward.add(d);
				backward.add(c);
			}
		}
		else
		{
			backward.add(a);
			backward.add(b);
			if (bcIstBevorzugt)
			{
				forward.add(c);
				forward.add(d);
			}
			else
			{
				forward.add(d);
				forward.add(c);
			}
		}
	}

	@Override
	public int validate()
	{
		int errors = 0;
		
		if (a == null)
		{
			log.error("Stecker A nicht definiert!");
			errors++;
		}
		else
		{
			if (!a.hasTrackElement(this))
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
		
		if (b == null)
		{
			log.error("Stecker B nicht definiert!");
			errors++;
		}
		else
		{
			if (!b.hasTrackElement(this))
			{
				log.error("Stecker B ist nicht rückverkoppelt!");
				errors++;
			}
			if (!b.isDirectionCorrect(this, aIsHigh))
			{
				log.error("B ist falschrum gerichtet!");
				errors++;
			}
		}
		
		if (c == null)
		{
			log.error("Stecker C nicht definiert!");
			errors++;
		}
		else
		{
			if (!c.hasTrackElement(this))
			{
				log.error("Stecker C ist nicht rückverkoppelt!");
				errors++;
			}
			if (!c.isDirectionCorrect(this, !aIsHigh))
			{
				log.error("C ist falschrum gerichtet!");
				errors++;
			}
		}

		if (d == null)
		{
			log.error("Stecker D nicht definiert!");
			errors++;
		}
		else
		{
			if (!d.hasTrackElement(this))
			{
				log.error("Stecker D ist nicht rückverkoppelt!");
				errors++;
			}
			if (!d.isDirectionCorrect(this, !aIsHigh))
			{
				log.error("D ist falschrum gerichtet!");
				errors++;
			}
		}
		return errors;
	}

	@Override
	protected boolean hasTrackElement(Gleisteil g) {
		return (g == a) || (g == b) || (g == c) || (g == d);
	}

	@Override
	protected boolean isDirectionCorrect(Gleisteil g, boolean isGrowing) {
		if ((a == g) && (aIsHigh != isGrowing))
		{
			return true;
		}
		if ((b == g) && (aIsHigh != isGrowing))
		{
			return true;
		}
		if ((c == g) && (aIsHigh == isGrowing))
		{
			return true;
		}
		if ((d == g) && (aIsHigh == isGrowing))
		{
			return true;
		}
		return false;
	}

	@Override
	public void turn(Batch batch, Gleisteil prev, Gleisteil next) {
		if (a == prev)
		{
			if (c == next)
			{
				turnAC_BD();
			}
			else if (d == next)
			{
				turnAD_BC();
			}
			else
			{
				throw new UnknownTurnStateException(getRoute(), prev, this, next);
			}
		}
		else if (b == prev)
		{
			if (c == next)
			{
				turnAD_BC();
			}
			else if (d == next)
			{
				turnAC_BD();
			}
			else
			{
				throw new UnknownTurnStateException(getRoute(), prev, this, next);
			}
		}
		else if (c == prev)
		{
			if (a == next)
			{
				turnAC_BD();
			}
			else if (b == next)
			{
				turnAD_BC();
			}
			else
			{
				throw new UnknownTurnStateException(getRoute(), prev, this, next);
			}
		}
		else if (d == prev)
		{
			if (a == next)
			{
				turnAD_BC();
			}
			else if (b == next)
			{
				turnAC_BD();
			}
			else
			{
				throw new UnknownTurnStateException(getRoute(), prev, this, next);
			}
		}
		else
		{
			throw new UnknownTurnStateException(getRoute(), prev, this, next);
		}
		addCommand(batch);
	}

	@Override
	protected Command getCommand()
	{
		return dir.getCommand();
	}
	
	/**
	 * Diese Methode schaltet die DKW auf Kreuz.
	 */
	private void turnAC_BD()
	{
		dir = TurnDirection.AC_BD;
	}
	
	/**
	 * Diese Methode schaltet die DKW auf Bogen.
	 */
	private void turnAD_BC()
	{
		dir = TurnDirection.AD_BC;
	}

	@Override
	public boolean isBranch()
	{
		return dir == TurnDirection.AC_BD;
	}

	/**
	 * Diese Methode gibt die Weichenlage zurück.
	 * @return Die aktuelle Weichenlage.
	 */
	public TurnDirection getDir()
	{
		return dir;
	}

	@Override
	public DirectionCode getDirectionCode()
	{
		return dir.getDirectionCode();
	}

	@Override
	public void setDir(DirectionCode dir)
	{
		switch(dir)
		{
		case CROSS:
			turnAD_BC();
			return;
		case ARC:
			turnAC_BD();
			return;
		}
		throw new UnknownDirectionCodeException(getRoute(), dir);
	}
	
	/**
	 * Diese Methode gibt die Weichennummer samt Weichenlage als Text zurück.
	 * @return Die Weichennummer und Weichenlage als Text.
	 */
	public String toString()
	{
		return getName() + " " + dir;
	}

	/**
	 * Diese Methode gibt das benachbarte {@link Gleisteil} in Richtung links oben zurück.
	 * @return Das {@link Gleisteil} in Richtung links oben.
	 */
	public Gleisteil getA()
	{
		return a;
	}

	/**
	 * Diese Methode gibt das benachbarte {@link Gleisteil} in Richtung links unten zurück.
	 * @return Das {@link Gleisteil} in Richtung links unten.
	 */
	public Gleisteil getB()
	{
		return b;
	}

	/**
	 * Diese Methode gibt das benachbarte {@link Gleisteil} in Richtung rechts oben zurück.
	 * @return Das {@link Gleisteil} in Richtung rechts oben.
	 */
	public Gleisteil getC()
	{
		return c;
	}

	/**
	 * Diese Methode gibt das benachbarte {@link Gleisteil} in Richtung rechts unten zurück.
	 * @return Das {@link Gleisteil} in Richtung rechts unten.
	 */
	public Gleisteil getD()
	{
		return d;
	}
}
