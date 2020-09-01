package suites;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

import tests.selenium.*;

@RunWith(JUnitPlatform.class)
//@SelectPackages({"tests.selenium"})
  @SelectClasses({
	  TestPO.class,
	  LoggingTest.class
  }) 
class Smoke
{}
