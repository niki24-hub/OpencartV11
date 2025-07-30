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

	@FindBy(name="search")
	WebElement txtSearchbox;
	
	@FindBy(xpath="//div[@id=\"search\"]//button[@type=\"button\"]")
	WebElement clkSearch;

	
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
	
	public void enterSearchProduct(String prodName)
	{
		txtSearchbox.sendKeys(prodName);
	}
	
	public void clickSearchicon()
	{
		clkSearch.click();
	}
	
}
