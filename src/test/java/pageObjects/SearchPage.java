package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends basePage{

	public SearchPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	
	
	@FindBy(xpath="//div[@class='product-layout product-grid col-lg-3 col-md-3 col-sm-6 col-xs-12']//img")
	List<WebElement> searchProduct;
	
	@FindBy(name="quantity")
	WebElement productQuantity;
	
	@FindBy(id="button-cart")
	WebElement addCart;
	
	@FindBy(xpath="//div[@class=\"alert alert-success alert-dismissible\"]")
	WebElement successAddedCart;
	
	public boolean isProductExist(String productName) throws InterruptedException
	{
		boolean flag=false;
		for(WebElement product : searchProduct)
		{
			System.out.println(product);
			if(product.getAttribute("title").equals(productName))
			{
				Thread.sleep(3000);
				flag= true;
				break;
			}

		}		
		return flag;
		
	}
	
	public void selectProduct(String productName) throws InterruptedException
	{
		for(WebElement product : searchProduct)
		{
			if(product.getAttribute("title").equals(productName))
			{
				Thread.sleep(1000);
				product.click();
			}
		}
	}
	
	public void enterproductQuantity(String quantity)
	{
		productQuantity.clear();
		productQuantity.sendKeys(quantity);
	}
	
	public void addCart()
	{
		addCart.click();
	}
	
	public boolean confMSG()
	{
		try
		{
			return successAddedCart.isDisplayed();
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
}
