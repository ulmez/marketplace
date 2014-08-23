package se.gozacke.unit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ JUnitActorTest.class, JUnitAuthorTest.class,
		JunitCategoryTest.class, JUnitProductTest.class,
		JUnitshoppingcart.class, JUnitUserTest.class })
public class AllTests {

}