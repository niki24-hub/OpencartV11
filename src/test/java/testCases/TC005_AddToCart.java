package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.SearchPage;
import pageObjects.homePage;
import testBase.BaseClass;

public class TC005_AddToCart extends BaseClass
{
	@Test(groups= {"master"})
	public void VerifyAddToCart()
	{
		logger.info("*********** Verifying TC005_add to cart functionality *************");
		
		try
		{
			homePage hp=new homePage(driver);
			
			hp.enterSearchProduct("HP LP3065");
			hp.clickSearchicon();
			
			SearchPage sp=new SearchPage(driver);
			
			if(sp.isProductExist("HP LP3065"))
			{
				sp.selectProduct("HP LP3065");
		        sp.enterproductQuantity("2");
		        sp.addCart();
			}
			
			Assert.assertEquals(sp.confMSG(), true);
					
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("************* Finished add to cart functionality ***************** ");
	}
}
