package testCases;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.SearchPage;
import pageObjects.ShoppingCartPage;
import pageObjects.accregPage;
import pageObjects.checkOutPage;
import pageObjects.homePage;
import pageObjects.loginPage;
import pageObjects.myAccountPage;
import testBase.BaseClass;

public class TC006_Checkoutoperation extends BaseClass {
	@Test(groups = { "master" })
	public void endToendTest() throws InterruptedException 
	{
		
		logger.info("***************Starting TC006_Checkoutoperation*******************");
		// Soft assertions
		SoftAssert myassert = new SoftAssert();

		// Account Registration
		logger.info("Account Registration................");
		System.out.println("Account Registration................");
		homePage hp = new homePage(driver);
		hp.clickMyacc();
		hp.clickReg();

		accregPage regpage = new accregPage(driver);
		regpage.enterFname(randAlpha().toUpperCase());
		regpage.enterLname(randAlpha().toUpperCase());

		String email = randAlpha() + "@gmail.com";
		regpage.enteremail(email);// randomly generated the email

		regpage.entertelephone("123567");
		String ActualPassword = randAlphanumeric();
		regpage.enterpassword("test123");
		regpage.enterconpassword("test123");
		regpage.clickAgreebtn();
		regpage.clickcontinue();
		Thread.sleep(3000);

		String Confmsg = regpage.getconfirmMsg();

		System.out.println(Confmsg);

		myassert.assertEquals(Confmsg, "Your Account Has Been Created!"); // validation

		myAccountPage mc = new myAccountPage(driver);
		mc.loggingoff();
		Thread.sleep(3000);

		// Login
		logger.info("Login to application...............");
		System.out.println("Login to application...............");
		hp.clickMyacc();
		hp.clickLogin();

		loginPage lp = new loginPage(driver);

		lp.enteremail(p.getProperty("emailid"));
		lp.enterpassword(p.getProperty("passWord"));
		lp.logginIn();

		System.out.println("Going to My Account Page? " + mc.confirmLogin());
		myassert.assertEquals(mc.confirmLogin(), true); // validation

		// search & add product to cart
		logger.info("search & add product to cart...............");
		System.out.println("search & add product to cart...............");
		hp.enterSearchProduct(p.getProperty("searchProductName"));
		hp.clickSearchicon();
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		SearchPage sp = new SearchPage(driver);

		if (sp.isProductExist(p.getProperty("searchProductName"))) {
			sp.selectProduct(p.getProperty("searchProductName"));
			sp.enterproductQuantity("1");
			sp.addCart();

		}
		Thread.sleep(3000);
		System.out.println("Added product to cart ? " + sp.confMSG());
		myassert.assertEquals(sp.confMSG(), true);

		// Shopping cart
		logger.info("Shopping cart...............");
		System.out.println("Shopping cart...............");
		ShoppingCartPage sc = new ShoppingCartPage(driver);
		sc.clickItemsToNavigateToCart();
		sc.clickViewCart();
		Thread.sleep(3000);
		sc.txtquantityboxupdate("1");
		String totprice = sc.getTotalPrice();
		Thread.sleep(3000);
		System.out.println("total price is shopping cart: " + totprice);
		myassert.assertEquals(totprice, "$100.00"); // validation
		sc.clickOnCheckout(); // navigate to checkout page
		Thread.sleep(3000);

		// Checkout Product
		logger.info("Checkout Product...............");
		System.out.println("Checkout Product...............");

		checkOutPage ch = new checkOutPage(driver);
		String firstName = randAlpha().toUpperCase();
		String lastName = randAlpha().toUpperCase();

		System.out.println(ch.handleBillingAddress(firstName, lastName, "address1", "address2", "Delhi", "500070", "India", "Delhi")? true : false);
		

		Thread.sleep(1000);
		ch.clickOnContinueAfterDeliveryAddress();
		Thread.sleep(1000);
		ch.setDeliveryMethodComment("testing...");
		Thread.sleep(1000);
		ch.clickOnContinueAfterDeliveryMethod();
		Thread.sleep(1000);
		ch.selectTermsAndConditions();
		Thread.sleep(1000);
		ch.clickOnContinueAfterPaymentMethod();
		Thread.sleep(2000);

		String total_price_inOrderPage = ch.getTotalPriceBeforeConfOrder();
		System.out.println("total price in confirmed order page:" + total_price_inOrderPage);
		myassert.assertEquals(total_price_inOrderPage, "$105.00"); // validation

		// Below code works ony if you configure SMTP for emails
		/*
		 * ch.clickOnConfirmOrder(); Thread.sleep(3000);
		 * 
		 * boolean orderconf=ch.isOrderPlaced();
		 * System.out.println("Is Order Placed? "+orderconf);
		 * myassert.assertEquals(ch.isOrderPlaced(),true);
		 */

		myassert.assertAll(); // conclusion
	}

}