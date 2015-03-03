/*
**
**	$Filename:	ErrorConditionTest.java $
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

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.morknet.mrw.Route;
import de.morknet.mrw.base.DeviceUnit;
import de.morknet.mrw.base.DirectionCode;
import de.morknet.mrw.batch.BatchNotCompletedException;
import de.morknet.mrw.batch.FailedExecutionException;
import de.morknet.mrw.comm.Command;
import de.morknet.mrw.comm.MrwMessage;
import de.morknet.mrw.comm.MsgCode;
import de.morknet.mrw.comm.dummy.DummyConnection;
import de.morknet.mrw.util.LogUtil;

public class ErrorConditionTest extends ControllerTestBase
{
	private final static Log             log = LogFactory.getLog(ErrorConditionTest.class);
	private final        DummyConnection dc  = controller.getDummyConnection();

	@Before
	public void before()
	{
		log.info("----------------------------------------------------------------------------");
	}

	@After
	public void after()
	{
		if (controller.isDummyConnection())
		{
			dc.setSwitchFailureMode(false);
			dc.setRejectSwitchAnswer(false);
			dc.setRejectFullAnswer(false);
			dc.setChecksumError(0, true);
		}
	}

	@Test
	public void manipulation() throws IOException
	{
		manual("DKW 8",     DirectionCode.CROSS, true);
		manual("Weiche 17", DirectionCode.RIGHT, false);
		manual("Weiche 7",  DirectionCode.RIGHT, true);
		manual("DKW 4",     DirectionCode.CROSS, false);
		manual("DKW 4",     DirectionCode.ARC,   false);
	}

	@Test
	public void switchFailure()
	{
		if (dc != null)
		{
			dc.setSwitchFailureMode(true);
			assertRouteException(FailedExecutionException.class);
		}
		else
		{
			log.info("Im Realmodus können keine Weichenfehler produziert werden!");
		}
	}

	@Test
	public void timeoutSwitch()
	{
		if (dc != null)
		{
			dc.setRejectSwitchAnswer(true);
			assertRouteException(BatchNotCompletedException.class);
		}
		else
		{
			log.info("Im Realmodus können keine Weichenfehler produziert werden!");
		}
	}

	@Test
	public void timeoutReset()
	{
		if (dc != null)
		{
			dc.setRejectFullAnswer(true);
			assertRouteException(BatchNotCompletedException.class);
		}
		else
		{
			log.info("Im Realmodus können keine Fehler produziert werden!");
		}
	}

	@Test
	public void singleChecksumError()
	{
		if (dc != null)
		{
			dc.setChecksumError(128, true);
			assertRouteException(BatchNotCompletedException.class);
		}
		else
		{
			log.info("Im Realmodus können keine Prüfsummenfehler produziert werden!");
		}
	}

	@Test
	public void multipleChecksumError()
	{
		if (dc != null)
		{
			dc.setChecksumError(128, false);
			assertRouteException(BatchNotCompletedException.class);
		}
		else
		{
			log.info("Im Realmodus können keine Prüfsummenfehler produziert werden!");
		}
	}
	
	private final void assertRouteException(Class<? extends RuntimeException> expected)
	{
		try
		{
			assertRoute(true,  true,  "LXRRLRRR", "S4c", "N3d");
			throw new AssertionError("Erwartete " + expected.getSimpleName());
		}
		catch(RuntimeException re)
		{
			Assert.assertSame(expected, re.getClass());
		}
	}

	private void manual(final String name, final DirectionCode code, final boolean mustFind) throws IOException
	{
		if (controller.isDummyConnection())
		{
			final Route route = assertStartRoute(true, true, "L)RRR",   "S4c", "N7a");

			Assert.assertNotNull("Es muss eine Fahrstraße geschaltet sein!", route);
			DeviceUnit unit = model.findMagneticPart(name);
			Assert.assertNotNull(LogUtil.printf("Es muss Gerät %s gefunden werden!", unit.getName()), unit);
	
			MrwMessage msg = MrwMessage.createResultMessage(
					Command.GETDIR, MsgCode.MSG_OK,
					unit.getMicroControllerId(), unit.getDeviceUnitNumber());
			msg.addDataByte(code.getDirectionCode());
			dc.simulateReception(msg);
			sleep(500L);

			if (mustFind)
			{
				Assert.assertNull("Es darf keine Fahrstraße mehr aktiv sein!", controller.getSelectedRoute());
			}
			else
			{
				Assert.assertNotNull("Es muss noch eine Fahrstraße aktiv sein!", controller.getSelectedRoute());
				controller.removeRoute(route);
			}
		}
		else
		{
			log.info("Im Realmodus können Manipulationen von Fahrstraßen nicht getestet werden!");
		}
	}
}
