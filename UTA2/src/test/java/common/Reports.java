package common;

import org.junit.jupiter.api.AfterAll;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ViewName;

public class Reports
{
	protected static ExtentTest test;
	protected static ExtentReports extent;
	protected static ExtentSparkReporter reportAll;
	protected static ExtentSparkReporter reportFail;
	
	public static void reportSetUp()
	{
		extent = new ExtentReports();
		String reportName = System.getProperty("user.dir")+"\\" + getTestClassName() + ".html";
		reportAll = new ExtentSparkReporter(reportName)
				.viewConfigurer()
			    .viewOrder()
			    .as(new ViewName[] { ViewName.DASHBOARD, ViewName.TEST })
			    .apply();
		reportFail = new ExtentSparkReporter(System.getProperty("user.dir")+"\\FailedResults.html")
				.filter()
				.statusFilter()
				.as(new Status[] { Status.FAIL })
				.apply(); 
		extent.attachReporter(reportAll, reportFail);
	}
	
	public static String getTestClassName()
	{
		String fqClassName = Thread.currentThread().getStackTrace()[3].getClassName();
		return fqClassName.substring(fqClassName.lastIndexOf('.') + 1);
	}
	
	@AfterAll
	static void oneTimeTearDown()
	{	
		extent.flush();
	}
}
