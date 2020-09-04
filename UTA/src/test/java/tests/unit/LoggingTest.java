package tests.unit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import common.BaseTestAPIUnit;

public class LoggingTest extends BaseTestAPIUnit
{
	private Sum sum;
	
	@BeforeEach
	void setUp()
	{
		sum = new Sum();
	}
	
	@DisplayName("add 1 + 1")
	@Test
	void addition(TestInfo info)
	{
		printDisplayTestName(info);
		assertEquals(2, sum.sumInt(1, 1));
	}
}
