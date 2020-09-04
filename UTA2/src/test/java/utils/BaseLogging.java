package utils;

import org.junit.jupiter.api.TestInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseLogging
{
	private final Logger logger = LoggerFactory.getLogger("UTA");

	public void printTestName(TestInfo info)
	{
		String testName = info.getTestMethod().get().getName();
		logger.info(testName);
	}

	public void printDisplayTestName(TestInfo info)
	{
		String testName1 = info.getTestMethod().get().getName();
		String testName2 = info.getDisplayName();
		logger.info(testName1 + " " + testName2);
	}

	public static String getTestClassName()
	{
		String fqClassName = Thread.currentThread().getStackTrace()[2].getClassName();
		return fqClassName.substring(fqClassName.lastIndexOf('.') + 1);
	}
}
