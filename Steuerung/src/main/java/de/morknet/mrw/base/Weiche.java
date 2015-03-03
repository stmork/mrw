/*
**
**	$Filename:	Weiche.java $
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

import de.morknet.mrw.UnknownDirectionCodeException;
import de.morknet.mrw.UnknownTurnStateException;
import de.morknet.mrw.batch.Batch;
import de.morknet.mrw.comm.Command;

/**
 * Diese Klasse repräsentiert eine Weiche.
 * @author smork
 *
 */
public final class Weiche extends Verzweigung
{
	private static final long serialVersionUID = 1L;

	private Gleisteil a;
	private Gleisteil b;
	private Gleisteil c;
	private final boolean bIstAbzweig;
	private final boolean cIstAbzweig;
	private final boolean bIstBevorzugt;

	/**
	 * Diese Aufzählung liefert alle Schaltzustände einer Weiche.
	 * @author sm
	 *
	 */
	public enum TurnDirection
	{
		/**
		 * Weichenlage links.
		 */
		AB(DirectionCode.LEFT,  Command.SETLFT, "links"),

		/**
		 * Weichenlage rechts.
		 */
		AC(DirectionCode.RIGHT, Command.SETRGT, "rechts");

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
			return code;
		}

		/**
		 * Diese Methode gibt das Schaltkommando zurück.
		 * @return Das Schaltkommando.
		 */
		public Command getCommand()
		{
			return cmd;
		}

		/**
		 * Weichenlage als Text.
		 */
		public String toString()
		{
			return this.text;
		}
	}
	
	private TurnDirection dir;

	/**
	 * Dieser Konstruktur initialisiert eine Weiche.
	 * @param abschnitt Der dazugehörende Gleisabschnitt.
	 * @param number Die Weichennummer.
	 * @param aIsHigh Zungenrichtung in Zählrichtung.
	 * @param bIstAbzweig Links ist Abzweig.
	 * @param cIstAbzweig Rechts ist Abzweig.
	 * @param bIstBevorzugt Links ist bevorzugte Fahrtrichtung.
	 * @param cIstBevorzugt Rechts ist bevorzugte Fahrtrichtung.
	 * @param limitStop Endabschaltung.
	 */
	public Weiche(
			Abschnitt abschnitt,
			String  number,
			boolean aIsHigh,
			boolean bIstAbzweig,
			boolean cIstAbzweig,
			boolean bIstBevorzugt,
			boolean cIstBevorzugt,
			boolean limitStop)
	{
		super(abschnitt, number, aIsHigh, limitStop);
		this.bIstAbzweig = bIstAbzweig;
		this.cIstAbzweig = cIstAbzweig;
		this.bIstBevorzugt = bIstBevorzugt;
		turnB();
	}

	/**
	 * Diese Methode definiert die benachbarten Gleisteile.
	 * @param a Gleisteil in Zungenrichtung.
	 * @param b Gleisteil am linken Abzweig.
	 * @param c Gleisteil am rechten Abzweig.
	 */
	public void link(Gleisteil a,Gleisteil b, Gleisteil c)
	{
		this.a = a;
		this.b = b;
		this.c = c;

		if (aIsHigh)
		{
			forward.add(a);
			if (bIstBevorzugt)
			{
				backward.add(b);
				backward.add(c);
			}
			else
			{
				backward.add(c);
				backward.add(b);
			}
		}
		else
		{
			backward.add(a);
			if (bIstBevorzugt)
			{
				forward.add(b);
				forward.add(c);
			}
			else
			{
				forward.add(c);
				forward.add(b);
			}
		}
	}

	@Override
	public int validate() {
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
			if (!b.isDirectionCorrect(this, !aIsHigh))
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
				log.error("Stecker B ist nicht rückverkoppelt!");
				errors++;
			}
			if (!c.isDirectionCorrect(this, !aIsHigh))
			{
				log.error("C ist falschrum gerichtet!");
				errors++;
			}
		}
		if ((!bIstAbzweig) && (!cIstAbzweig))
		{
			log.error("Eine Weiche hat mindestens einen Abzweig!");
			errors++;
		}
		return errors;
	}

	@Override
	public boolean hasTrackElement(Gleisteil g) {
		return (g == a) || (g == b) || (g == c);
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
		if ((c == g) && (aIsHigh == isGrowing))
		{
			return true;
		}
		return false;
	}

	@Override
	public void turn(Batch batch, Gleisteil prev, Gleisteil next)
	{
		if (a == prev)
		{
			if (b == next)
			{
				turnB();
			}
			else if (c == next)
			{
				turnC();
			}
			else
			{
				throw new UnknownTurnStateException(getRoute(), prev, this, next);
			}
		}
		else if (a == next)
		{
			if (b == prev)
			{
				turnB();
			}
			else if (c == prev)
			{
				turnC();
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
	 * Diese Methode schaltet diese Weiche nach links.
	 */
	private void turnB()
	{
		dir = TurnDirection.AB;
	}

	/**
	 * Diese Methode schaltet die Weiche nach rechts.
	 */
	private void turnC()
	{
		dir = TurnDirection.AC;
	}

	@Override
	public boolean isBranch()
	{
		return dir == TurnDirection.AB ? bIstAbzweig : cIstAbzweig;
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
		case LEFT:
			turnB();
			return;
		case RIGHT:
			turnC();
			return;
		}
		throw new UnknownDirectionCodeException(getRoute(), dir);
	}
	
	/**
	 * Diese Methode ermittelt, ob der Abzweig nach links führt.
	 * @return Flag, ob Abzweig nach links führt.
	 */
	public final boolean istBAbzweig()
	{
		return bIstAbzweig;
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
	 * Diese Methode gibt das {@link Gleisteil} in Zungenrichtung zurück.
	 * @return Das {@link Gleisteil} in Zungenrichtung.
	 */
	public Gleisteil getA()
	{
		return a;
	}

	/**
	 * Diese Methode gibt das nach links benachbarte {@link Gleisteil} zurück. 
	 * @return Das benachbarte linke {@link Gleisteil}.
	 */
	public Gleisteil getB()
	{
		return b;
	}

	/**
	 * Diese Methode gibt das nach rechts benachbarte {@link Gleisteil} zurück. 
	 * @return Das benachbarte rechte {@link Gleisteil}.
	 */
	public Gleisteil getC()
	{
		return c;
	}
}
