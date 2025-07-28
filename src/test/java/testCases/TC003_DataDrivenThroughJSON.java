package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.homePage;
import pageObjects.loginPage;
import pageObjects.myAccountPage;
import testBase.BaseClass;
import utilities.DataProviderClass;
import utilities.ProductData;

public class TC003_DataDrivenThroughJSON extends BaseClass

{
	@Test (dataProvider="getProductTestData",dataProviderClass=DataProviderClass.class,groups={"DataDriven"})
	public void VerifyTC003LoginTest(ProductData product)
	{
		logger.info("****** Starting TC003 Data Driven login Through JSON ***********");
		try
		{
		homePage hp=new homePage(driver);
		Thread.sleep(2000);
		logger.info("Clicking on My Account....");
		hp.clickMyacc();
		logger.info("Clicking on Login....");
		hp.clickLogin();
		
		
	loginPage lp=new loginPage(driver);
	
	logger.info("Enter Login Details Via JSON file with the help of POJO ProductData class....");

	lp.enteremail(product.getUsername());
	lp.enterpassword(product.getPassword());
	
	lp.logginIn();
	
	myAccountPage ma=new myAccountPage(driver);
	boolean confirmedloggedIn=ma.confirmLogin();
	
	logger.info("Verify successful Loggin in Via JSON file with the help of POJO ProductData class....");

	
	if(product.getRes().equalsIgnoreCase("Valid"))
	{
		if(confirmedloggedIn==true)
		{
			Assert.assertTrue(true);
           ma.loggingoff();			
		}
	
	   else
	  {
			Assert.assertTrue(false);

	  }
		
    	}

		
	else if(product.getRes().equalsIgnoreCase("Invalid"))
			{
		      if(confirmedloggedIn==true)
		      {
		           ma.loggingoff();			

		      
		    	  Assert.assertTrue(false);

		      }
		      else
		      {
		    	  Assert.assertTrue(true);

		      }
		
			}

		}
		
		
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("******** Finished TC003 Data Driven login through JSON file");
		

	}
}
			
		
