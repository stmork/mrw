/*
**
**	$Filename:	BatchElementDefinedException.java $
**	$Revision: 931 $
**	$Date: 2010-04-14 10:39:15 +0200 (Mi, 14. Apr 2010) $
**	$Author: smork $
**	$Id: BatchElementDefinedException.java 931 2010-04-14 08:39:15Z smork $
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

import de.morknet.mrw.base.DeviceUnit;
import de.morknet.mrw.util.LogUtil;

/**
 * Diese Exception wird geworfen, wenn ein BatchElement einem Ger�t zugeordnet werden soll, dem schon eins
 * zugeordnet worden ist.
 * @author smork
 *
 */
public class BatchElementDefinedException extends BatchProcessingException
{
	/**
	 * Dieser Konstruktur bereitet eine Fehlermeldung auf.
	 * @param deviceUnit Das verursachende Ger�t.
	 */
	public BatchElementDefinedException(DeviceUnit deviceUnit)
	{
		super(LogUtil.printf("Es ist schon ein BatchElement [%s] definiert!", deviceUnit.getBatchElement()));
	}

	private static final long serialVersionUID = 1L;
}
