/*
**
**	$Filename:	NamedElement.java $
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

import java.io.Serializable;

import de.morknet.mrw.util.LogUtil;

/**
 * Diese Basisklasse wird für alle bennanten Elemente einer Modelleisenbahn verwendet.
 * @author smork
 *
 */
abstract public class NamedElement implements Serializable
{
	private static final long serialVersionUID = 1L;

	/**
	 * Der Name dieses Bauteils.
	 */
	protected final String name;
	private int logX = -1;
	private int logY = -1;
	
	/**
	 * Dieser Konstruktor benennt dieses Bauteil.
	 * @param name Der Name des Bauteils.
	 */
	protected NamedElement(String name)
	{
		this.name = name;
	}
	
	/**
	 * Diese Methode gibt den Namen dieses Bauteils wieder.
	 * @return Der Bauteilname
	 */
	public String getName()
	{
		return this.name;
	}

	/**
	 * Diese Methode gibt die Koordinaten dieses Bauteils zurück. 
	 * @return Die Koordinaten dieses Bauteils als Text.
	 */
	public String getLayoutInfo()
	{
		return LogUtil.printf("%d,%d", logX, logY);
	}

	/**
	 * Diese Methode wertet ein Koordinatenpaar als Text aus und setzt dementsprechend die Position
	 * dieses Bauteils.
	 * @param info Das Koordinatenpaar als Text.
	 */
	public void parseLayoutInfo(String info)
	{
		int i = info.indexOf(",");
		
		if (i > 0)
		{
			logX = Integer.parseInt(info.substring(0, i));
			logY = Integer.parseInt(info.substring(i+1));
		}
	}

	/**
	 * Diese Methode gibt die x-Position dieses Bauteils zurück.
	 * @return die x-Position dieses Bauteils.
	 */
	public int getLogX()
	{
		return logX;
	}

	/**
	 * Diese Methode setzt die x-Position dieses Bauteils.
	 * @param logX Die neue x-Position.
	 */
	public void setLogX(final int logX)
	{
		this.logX = logX;
	}

	/**
	 * Diese Methode gibt die y-Position dieses Bauteils zurück.
	 * @return die y-Position dieses Bauteils.
	 */
	public int getLogY()
	{
		return logY;
	}

	/**
	 * Diese Methode setzt die y-Position dieses Bauteils.
	 * @param logY Die neue y-Position.
	 */
	public void setLogY(final int logY)
	{
		this.logY = logY;
	}
}
