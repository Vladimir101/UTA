package tests.unit;

import com.aventstack.extentreports.Status;

import common.Reports;

public class Sum extends Reports
{
	public int sumInt(int a, int b)
	{
		test.log(Status.PASS, "In sumInt: a = " + a + "; b = " + b);
		return a + b;
	}
}
