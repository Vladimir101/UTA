package tests.unit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sum
{
	private final Logger logger = LoggerFactory.getLogger(Sum.class);

	public int sumInt(int a, int b)
	{
		logger.info("sumInt: {}, {}", a, b);
		return a + b;
	}
}
