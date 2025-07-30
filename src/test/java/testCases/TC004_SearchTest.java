package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.SearchPage;
import pageObjects.homePage;
import testBase.BaseClass;

public class TC004_SearchTest extends BaseClass  
{
	@Test(groups= {"master"})
	public void VerifySearchTest()
	{
		logger.info("************* Starting TC004_SearchTest *****************");
		
		try
		{
			homePage hp=new homePage(driver);
			
			logger.info("Enter product to search and enter");
			hp.enterSearchProduct("mac");
			hp.clickSearchicon();
			
			SearchPage sp=new SearchPage(driver);
			
			logger.info("********** Verify searched product exists ***************");
			boolean expectedresultofexistingproduct=sp.isProductExist("MacBook");
			
			Assert.assertEquals(expectedresultofexistingproduct, true);
					
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("************ Finished TC004_SearchTest ******************8");
	}
}
