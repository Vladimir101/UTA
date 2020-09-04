package common;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import utils.PropertyManager;

@ExtendWith(UTATestWatcher.class)
public class BaseTestSelenium extends Reports
{
	protected static WebDriver driver;
	protected final String dataDir = "/data/";
	protected static String browser;
	protected String screenshotName;

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

// screenshot section
/*
 * protected String doScreenshot() throws IOException
 * {
 * String scName = "screenshots\\PageWithFailure"
 * + TimestampGenerator.currentTimestamp()
 * + ".png";
 * TakesScreenshot ts = (TakesScreenshot) driver;
 * FileHandler.copy(ts.getScreenshotAs(OutputType.FILE), new File(scName));
 * return scName;
 * }
 */
	
	public static WebDriver getDriver()
	{
		return driver;
	}
}
