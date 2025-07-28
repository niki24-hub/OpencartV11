package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class myAccountPage extends basePage{

	public myAccountPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath="//h2[text()=\"My Account\"]")
	WebElement confirmlogin;
	
	@FindBy(xpath="//a[@class='list-group-item'][13]")
	WebElement logoutbtn;
	
	public boolean confirmLogin()
	{
		try
		{
			return (confirmlogin.isDisplayed());
		}
		catch(Exception e)
		{
			return false;
		}
}
	
	public void loggingoff()
	{
		logoutbtn.click();
	}
}