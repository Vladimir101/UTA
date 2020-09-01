package tests.selenium;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import common.BaseTest;
import pages.selenium.HomePage;
import pages.selenium.LoginPage;

public class TestPO extends BaseTest
{
	@Test
	public void validLogin(TestInfo info)
	{
		printTestName(info);		
		HomePage homePage = LoginPage.open(driver)
									 .validLogin("tomsmith", "SuperSecretPassword!");
		assertTrue(homePage.getLoginConfirmation().contains("You logged into"));
		
		LoginPage loginPage = homePage.logout();
		assertTrue(loginPage.getConfirmation().contains("You logged iut"));
	}

	@ParameterizedTest
	@CsvFileSource(resources = dataDir + "TestPO/ddt.csv", numLinesToSkip = 1)
	void invalidLogin(String username, String password, String errorMessage, TestInfo info)
	{
		printDisplayTestName(info);
		
		LoginPage loginPage = LoginPage.open(driver);
		loginPage.submitLogin(username, password);
		assertTrue(loginPage.getConfirmation().contains(errorMessage));
	}
}
