package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShoppingCartPage extends basePage

{

	public ShoppingCartPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath="//div[@id='cart']")
	WebElement cartbtn;
	
	@FindBy(xpath="//strong[normalize-space()='View Cart']")
    WebElement linkviewcart;
	
	@FindBy(xpath="//table//input")
	WebElement txtquantitybox;
	
	@FindBy(xpath="//*[text()='Total:']//following::td")
	WebElement totalamt;
	
	
	@FindBy(xpath="//div[@class='pull-right']//a")
	WebElement checkoutbtn;
	
	public void clickItemsToNavigateToCart()
	{
		cartbtn.click();
	}
	
	public void clickViewCart()
	{
		linkviewcart.click();
	}
	
	public void txtquantityboxupdate(String newquantity)
	{
		txtquantitybox.clear();
		txtquantitybox.sendKeys(newquantity);
		txtquantitybox.sendKeys(Keys.ENTER); // presses Enter

	}
	
	public String getTotalPrice()
	{
		return totalamt.getText();
	}
	
	public void clickOnCheckout()
	{
		checkoutbtn.click();
	}    
}
