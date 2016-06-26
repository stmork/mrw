/*
**
**	$Filename:	Coordinates.java $
**	$Revision$
**	$Author$
**	$Id$
**
**	Copyright (C) 2010 committers of this modelrailway project. All rights reserved.
**
**	This program and the accompanying materials are made available under the
**	terms of the Eclipse Public License v1.0.
**
**	The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
**
**
*/

package de.morknet.mrw.rcc;

/**
 * Dieses Interface stellt einige Konstanten für die Darstellung des Spurplans
 * bereit. Der Spurplan ist gerastert. In ein Rasterfeld passt exakt eine
 * Weiche oder ein Signal. Dabei kann es sich auch um ein kombiniertes Signal
 * aus Vor- und Hauptsignal handeln.
 * 
 * @see GroupView
 * @author sm
 *
 */
public interface Coordinates
{
	/**
	 * Diese Konstante bestimmt die Länge eines Abstellgleises in x-Richtung.
	 */
	public final static int OVERRUN_X       =  2;

	/**
	 * Diese Konstante bestimmt die Länge eines Abstellgleises in y-Richtung.
	 */
	public final static int OVERRUN_Y       =  0;
	
	/**
	 * Breite eines Spurplanelementes.
	 */
	public final static int SCALE_X         = 32;
	
	/**
	 * Höhe eines Spurplanelementes.
	 */
	public final static int SCALE_Y         = 32;
	
	/**
	 * Abstand vom Rasterrand zur Darstellung eines Gleisbelegtfeldes. 
	 */
	public final static int BASE_OFFSET     =  5;

	/**
	 * Linienbreite eines Gleisteils.
	 */
	public final static int MRW_LINE_WIDTH  =  3;
}
