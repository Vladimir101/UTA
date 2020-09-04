package tests.selenium;

import static org.junit.Assert.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import com.aventstack.extentreports.Status;

import common.BaseTestSelenium;
import common.Reports;
import pages.selenium.HomePage;
import pages.selenium.LoginPage;

public class TestPO extends BaseTestSelenium
{
	@BeforeAll
	static void report()
	{
		Reports.reportSetUp();
	}
	
	@Test
	void validLogin()
	{
		test = extent.createTest("validLogin");
		HomePage homePage = LoginPage.open(driver)
									 .validLogin("tomsmith", "SuperSecretPassword!");
		
		assertTrue(homePage.getLoginConfirmation().contains("You logged into"));
		test.log(Status.PASS, "Successfull login to the Home page");
		
		LoginPage loginPage = homePage.logout();
		
		assertTrue(loginPage.getConfirmation().contains("You logged out"));
		test.log(Status.PASS, "Successfull logout to the Home page");
	}

	@ParameterizedTest
	@CsvFileSource(resources = dataDir + "TestPO/ddt.csv", numLinesToSkip = 1)
	void invalidLogin(String username, String password, String errorMessage)
	{
		test = extent.createTest("invalidLogin");
		LoginPage loginPage = LoginPage.open(driver);
		
		loginPage.submitLogin(username, password);
		
		assertTrue(loginPage.getConfirmation().contains(errorMessage));
		test.log(Status.PASS, "Correct error message for (" + username + ", " + password + ")");
	}
}
