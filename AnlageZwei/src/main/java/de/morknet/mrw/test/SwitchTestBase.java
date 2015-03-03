/*
**
**	$Filename:	DkwTest.java $
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

package de.morknet.mrw.test;

import org.junit.After;
import org.junit.Before;

import de.morknet.mrw.batch.Batch;
import de.morknet.mrw.batch.BatchExecuter;

public class SwitchTestBase extends ControllerTestBase
{
	protected BatchExecuter executer;
	protected Batch         batch;

	@Before
	public void before()
	{
		executer = new BatchExecuter();
		batch = executer.createBatch();
	}
	
	@After
	public void after()
	{
		if (executer != null)
		{
			executer.clear();
		}
	}
}
