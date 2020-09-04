package tests.unit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.aventstack.extentreports.Status;

import common.UTATestWatcher;
import common.Reports;

@ExtendWith(UTATestWatcher.class)
public class HelloTest extends Reports
{
	@BeforeAll
	static void report()
	{
		Reports.reportSetUp();
	}
	
	@Test
	void test()
	{
		test = extent.createTest("hello");
		
		assertTrue(true);
		test.log(Status.PASS, "hello");

	}
}
