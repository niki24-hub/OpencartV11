package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class loginPage extends basePage
{

	public loginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(id="input-email")
	WebElement emailtxt;
	
	@FindBy(id="input-password")
	WebElement passtxt;
	
	@FindBy(xpath="//input[@type=\"submit\"]")
	WebElement submitbtn;
	
	
	public void enteremail(String email)
	{
		emailtxt.sendKeys(email);
	}

	public void enterpassword(String password)
	{
		passtxt.sendKeys(password);
	}
	
	public void logginIn()
	{
		submitbtn.click();
	}
	
	
		

	}

