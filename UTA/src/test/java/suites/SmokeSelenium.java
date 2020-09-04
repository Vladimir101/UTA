package suites;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

import tests.selenium.*;

@RunWith(JUnitPlatform.class)
@SelectClasses({
  TestPO.class
  })
public class SmokeSelenium
{}
