package Tests;

//import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import viewND.CONSTANTS;
import viewND.CubeND;

public class CubusNDTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testCreateCubus() {
		CubeND cubus = new CubeND("cubus" + CONSTANTS.n + "dimensional");
		//cubus.createCubus();
	}

}
