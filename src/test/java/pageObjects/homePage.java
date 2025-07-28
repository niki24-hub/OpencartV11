package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class homePage extends basePage {

	public homePage(WebDriver driver) 
	{
		super(driver);
	}
	
	
	@FindBy(xpath="//*[text()='My Account']")
	WebElement linkMyacc;
	
	@FindBy(xpath="//li//a[text()='Register']")
	WebElement lnkReg;
	
	@FindBy(xpath="//li//a[text()='Login']")
	WebElement lnkLogin;

	
	public void clickMyacc()
	{
		linkMyacc.click();
	}
	
	public void clickReg()
	{
		lnkReg.click();
	}
	
	public void clickLogin()
	{
		lnkLogin.click();
	}
	
}
