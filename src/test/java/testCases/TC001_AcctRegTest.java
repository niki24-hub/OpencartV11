package testCases;

import org.testng.AssertJUnit;

import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.accregPage;
import pageObjects.homePage;
import testBase.BaseClass;

public class TC001_AcctRegTest extends BaseClass
{
	
	
	@Test (groups={"Master","Sanity"})
	public void verifyaccRegtest()
	{
		logger.info("***** Starting TC001_AcctRegTest *****");
		try
		{
		homePage hp=new homePage(driver);
		logger.info("Clicking on My Account....");
		hp.clickMyacc();
		logger.info("Clicking on Registration....");
		hp.clickReg();
		
		accregPage accp=new accregPage(driver);
		
		
		logger.info("Entering Customer details.............");
		accp.enterFname(randAlpha().toUpperCase());
		accp.enterLname(randAlpha().toUpperCase());
		accp.enteremail(randAlpha()+ "@gmail.com");
		accp.entertelephone(randNum());
		String ActualPassword=randAlphanumeric();
		accp.enterpassword(ActualPassword);
		accp.enterconpassword(ActualPassword);
		accp.clickAgreebtn();
		accp.clickcontinue();
		logger.info("Verifying Expected result for account creation");
		String Confmsg= accp.getconfirmMsg();
		
		if(Confmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("Test Failed..........");
		    logger.debug("Debug logs............");
			Assert.assertTrue(false);

			
		}
	
		//AssertJUnit.assertEquals(Confmsg, "");
		
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("***** Finished TC001_AcctRegTest ******");
		
	}	
		
		
		
		
		
		
		
		 
		
		
		
		
		
		
		
		
		
	}

