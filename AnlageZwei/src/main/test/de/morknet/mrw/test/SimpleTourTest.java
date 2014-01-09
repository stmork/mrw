package de.morknet.mrw.test;

import org.junit.Test;

public class SimpleTourTest extends ControllerTestBase
{
	@Test
	public void tour()
	{
		if (controller.isDummyConnection())
		{
			controller.removeAllRoutes();
			assertTour(true,  true,  "LXRRLRRR",            "S4c", "N3d");
			assertTour(false, true,  "RLRXRRLRRLLXRXLLRLL", "N3d", "B2");
			assertTour(false, false, "LLRLLXRXLLRRLRRXRLR", "B2", "B1", "N3d");
		}
	}
}
