package Tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import viewND.nd.SimpleAbstractND;
import viewND.nd.AffineTransformND;
import viewND.CONSTANTS;
import viewND.nd.SimplePointND;

public class AffineTransformNDTest {

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
	public final void test_AffineTransformND() {
		AffineTransformND id = new AffineTransformND();
		System.out.println("Print id");
		System.out.println(id);
		for (int line = 0; line < CONSTANTS.n; line++) {
			for (int row = 0; row < CONSTANTS.n; row++) {
				if (line == row)
					assertEquals("Wrong value in id", 1, id.m[line][row], 0.00001);
				else
					assertEquals("Wrong value in id", 0, id.m[line][row], 0.00001);
			}
		}
	}

	@Test
	public final void test_getRotationInstanceX_iAndX_j() {
		double theta = Math.PI;
		AffineTransformND rotation = AffineTransformND.getRotation_i_j(0, 1, theta);
		System.out.println("Print rotate PI");
		System.out.println(rotation);
		theta = Math.PI / 2;
		rotation = AffineTransformND.getRotation_i_j(0, 1, theta);
		System.out.println("Print Protate PI/2");
		System.out.println(rotation);
		theta = Math.PI / 4;
		rotation = AffineTransformND.getRotation_i_j(0, 1, theta);
		System.out.println("Print rotate PI/4");
		System.out.println(rotation);
	}

	@Test
	public final void test_concatenate() {
		// id * id = id
		AffineTransformND id = new AffineTransformND();
		AffineTransformND t1 = new AffineTransformND();
		AffineTransformND t2 = new AffineTransformND();
		t1.concatenate(t2);
		assertTrue(t1.equals(id));

		// turn PI * -PI = id
		double theta = Math.PI;
		t1 = AffineTransformND.getRotation_i_j(0, 1, theta);
		System.out.println("Print rotate PI");
		System.out.println(t1);

		t2 = AffineTransformND.getRotation_i_j(0, 1, -theta);
		System.out.println("Print rotate -PI");
		System.out.println(t2);

		t1.concatenate(t2);
		System.out.println("Print rotate PI & -PI");
		System.out.println(t1);

		assertTrue(t1.equals(id));

		// turn PI/2 * PI/2 = PI
		theta = Math.PI / 2;
		t1 = AffineTransformND.getRotation_i_j(0, 1, theta);
		System.out.println("Print rotate PI/2");
		System.out.println(t1);

		t2 = AffineTransformND.getRotation_i_j(0, 1, theta);
		System.out.println("Print rotate PI/2");
		System.out.println(t2);

		AffineTransformND t3 = AffineTransformND.getRotation_i_j(0, 1, theta * 2);
		System.out.println("Print rotate PI");
		System.out.println(t3);

		t1.concatenate(t2);
		System.out.println("Print rotate PI/2 & PI/2");
		System.out.println(t1);
		assertTrue(t1.equals(t3));

		// turn PI/4 * PI/4 * PI/4 = 3/4 * PI
		theta = Math.PI / 4;
		t1 = AffineTransformND.getRotation_i_j(0, 1, theta);
		System.out.println("Print rotate PI/4");
		System.out.println(t1);

		t2 = AffineTransformND.getRotation_i_j(0, 1, theta);
		System.out.println("Print rotate PI/4");
		System.out.println(t2);
		
		t3 = AffineTransformND.getRotation_i_j(0, 1, 3.0 * theta);
		System.out.println("Print rotate 3 * PI/4");
		System.out.println(t3);

		t1.concatenate(t2);
		t1.concatenate(t2);
		System.out.println("Print rotate PI/4 & PI/4 & PI/4");
		System.out.println(t1);
		assertTrue(t1.equals(t3));
	}
	
	@Test
	public void test_transformPointND() {
		// id * p1 = p1
		SimplePointND p1 = new SimplePointND();
		for (int i = 0; i < CONSTANTS.n; i++) {
			p1.setXi(i,i);
		}
		System.out.println("Print PointND 1/2/3.../n");
		System.out.println(p1);
		
		AffineTransformND id = new AffineTransformND();
		SimplePointND p2 = id.transform(p1);
		assertTrue(p2.equals(p1));
		
		// all_1 * p1 = all_n
		p1 = new SimplePointND();
		for (int i = 0; i < CONSTANTS.n; i++) {
			p1.setXi(i,1);
		}
		System.out.println("Print PointND 1/1/1.../1");
		System.out.println(p1);
		
		p2 = new SimplePointND();
		for (int i = 0; i < CONSTANTS.n; i++) {
			p2.setXi(i,CONSTANTS.n);
		}
		System.out.println("Print PointND n/n/n.../n");
		System.out.println(p1);
		
		AffineTransformND all_1 = new AffineTransformND();
		for (int line = 0; line < CONSTANTS.n; line++)
			for (int row = 0; row < CONSTANTS.n; row++) 
				all_1.m[line][row] = 1;
		SimpleAbstractND p3 = all_1.transform(p1);
		assertTrue(p3.equals(p2));
	}
}
