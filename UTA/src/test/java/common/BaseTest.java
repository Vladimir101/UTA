package common;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utils.PropertyManager;
@ExtendWith(MyTestWatcher.class)
public class BaseTest
{
	protected static WebDriver driver;
	protected final String dataDir = "/data/";
	protected static String browser;

	@BeforeAll
	static void oneTimeSetup()
	{
		browser = PropertyManager.getGlobalConfigProperty("browser");
	}

	@BeforeEach
	void setUpInBase()
	{
		switch (browser)
		{
			case "chrome" ->
			{
				System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
				driver = new ChromeDriver();
			}
			case "firefox" ->
			{
				System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
				driver = new FirefoxDriver();
			}
			case "edge" ->
			{
				System.setProperty("webdriver.edge.driver", "drivers/msedgedriver.exe");
				driver = new EdgeDriver();
			}
		}
		driver.manage().window().maximize();
	}
 

// logging section	
	private final Logger logger = LoggerFactory.getLogger("tests.selenium");

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

	public String getTestClassName()
	{
		String fqClassName = Thread.currentThread().getStackTrace()[2].getClassName();
		return fqClassName.substring(fqClassName.lastIndexOf('.') + 1);
	}

// screenshot section
	protected void doScreenshot()
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		try
		{
			FileHandler.copy(ts.getScreenshotAs(OutputType.FILE), new File("VisibleArea.png"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
