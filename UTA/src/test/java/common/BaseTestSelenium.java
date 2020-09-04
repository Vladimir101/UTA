package common;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

import utils.PropertyManager;
@ExtendWith(SeleniumTestWatcher.class)
public class BaseTestSelenium extends BaseLogging
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
