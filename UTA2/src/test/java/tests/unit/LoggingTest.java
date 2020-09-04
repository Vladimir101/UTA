package tests.unit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.aventstack.extentreports.Status;

import common.UTATestWatcher;
import common.Reports;

@ExtendWith(UTATestWatcher.class)
public class LoggingTest extends Reports
{
	private Sum sum;

	@BeforeAll
	static void report()
	{
		Reports.reportSetUp();
	}

	@BeforeEach
	void setUp()
	{
		sum = new Sum();
	}

	@DisplayName("add 1 + 1")
	@Test
	void addition()
	{
		test = extent.createTest("addition");
		
		assertEquals(2, sum.sumInt(1, 1));
		test.log(Status.PASS, "1 + 1 = 2");
	}
}
