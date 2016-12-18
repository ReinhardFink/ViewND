package Tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)

@SuiteClasses({ 
	AffineTransformNDTest.class, 
	SystemNDTest.class 
})
public class AllTests {

}
