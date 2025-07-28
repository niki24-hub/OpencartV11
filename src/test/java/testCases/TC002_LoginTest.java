package testCases;

import org.testng.Assert;

import org.testng.annotations.Test;

import pageObjects.homePage;
import pageObjects.loginPage;
import pageObjects.myAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass
{
	@Test(groups={"Master"})
	public void LoggingIn()
	{
		
		logger.info("***** Starting TC002_LoginTest *****");
		try
		{
		homePage hp=new homePage(driver);
		logger.info("Clicking on My Account....");
		hp.clickMyacc();
		logger.info("Clicking on Login....");
		hp.clickLogin();
		
		
	loginPage lp=new loginPage(driver);
	
	logger.info("Enter Login Details....");

	lp.enteremail(p.getProperty("emailid"));
	lp.enterpassword(p.getProperty("passWord"));
	lp.logginIn();
	myAccountPage ma=new myAccountPage(driver);
	
	logger.info("Verify expected Login successful....");

   boolean targetPage=	ma.confirmLogin();
   Assert.assertTrue(targetPage);
	
	}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("***** Finished TC002_LoginTest *****");

	
	
	}
	
}
