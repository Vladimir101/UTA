package common;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import utils.TimestampGenerator;

public class UTATestWatcher extends Reports implements TestWatcher
{
	private String packageName;

	public void testAborted(ExtensionContext extensionContext, Throwable throwable)
	{
		// do something
	}

	public void testDisabled(ExtensionContext extensionContext, Optional<String> optional)
	{
		// do something
	}

	public void testFailed(ExtensionContext extensionContext, Throwable throwable)
	{	
		test.log(Status.FAIL, extensionContext.getDisplayName());
		System.out.println("'" + extensionContext.getDisplayName() + "' failed");
		String scName;
		
		packageName = getPackageName(extensionContext);
		if (packageName.equals("tests.selenium"))
		{
			try
			{
				scName = "\\" + doScreenshot();
				String scPath = System.getProperty("user.dir") + scName;
				test.fail(MediaEntityBuilder.createScreenCaptureFromPath(scPath).build());
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			BaseTestSelenium.getDriver().quit();
		}
	}

	public void testSuccessful(ExtensionContext extensionContext)
	{
		test.log(Status.PASS, extensionContext.getDisplayName());
		
		packageName = getPackageName(extensionContext);
		System.out.println("'" + extensionContext.getDisplayName() + "' passed");
		if (packageName.equals("tests.selenium"))
			BaseTestSelenium.getDriver().quit();
	}

	private String getPackageName(ExtensionContext extensionContext)
	{
		String testClassString = extensionContext.getRequiredTestClass().toString();
		String testClassName = testClassString.substring(testClassString.indexOf(' ') + 1);
		return testClassName.substring(0, testClassName.lastIndexOf('.'));
	}
	
	private String doScreenshot() throws IOException 
	{
		String scName = "screenshots\\PageWithFailure" 
						+ TimestampGenerator.currentTimestamp() 
						+ ".png";
		TakesScreenshot ts = (TakesScreenshot) BaseTestSelenium.getDriver();
		FileHandler.copy(ts.getScreenshotAs(OutputType.FILE), new File(scName));
		return scName;
	}
}