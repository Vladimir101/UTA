package pages.selenium;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.BasePage;

public class LoginPage extends BasePage
{
	private static String URL = "https://the-internet.herokuapp.com/login";
	private static String title = "The Internet";
	
// elements
	@FindBy(id = "username")
	private WebElement username;
	
	@FindBy(id = "password")
	private WebElement password;
	
	@FindBy(tagName = "button")
	private WebElement buttonLogin;
	
	@FindBy(id = "flash")
	private WebElement confirmation;
	
	@FindBy(id = "vb")
	private WebElement vb;
	
// constructor
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
// services
	public static LoginPage open(WebDriver driver)
	{
		driver.get(URL);		
		assertEquals("This is not the login page",
				title, driver.getTitle());		 
		return PageFactory.initElements(driver, LoginPage.class);
	}
	
	public void submitLogin(String user, String pass)
	{
		username.sendKeys(user);
		password.sendKeys(pass);
		clickElement(buttonLogin);	
	}
	
	public HomePage validLogin(String user, String pass)
	{
		submitLogin(user, pass);
		return PageFactory.initElements(driver, HomePage.class);
	}
	
	public LoginPage invalidLogin(String user, String pass)
	{
		submitLogin(user, pass);
		return this;
	}
	
	public String getConfirmation()
	{
		return confirmation.getText();
	}
	
	public boolean isLoaded() 
	{
        return new WebDriverWait(driver, 10)
        		.until(ExpectedConditions.presenceOfElementLocated(By.id("some element")))
        		.isDisplayed();
    }
}
