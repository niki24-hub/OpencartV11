package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class accregPage extends basePage

{

	public accregPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(name="firstname")
	WebElement Fname;
	
	@FindBy(name="lastname")
	WebElement Lname;
	
	@FindBy(name="email")
	WebElement Email;
	
	@FindBy(name="telephone")
	WebElement Tel;		
	
	@FindBy (name="password")
	WebElement Password;
	
	@FindBy (name="confirm")
	WebElement Confassword;
	
	@FindBy (name="agree")
	WebElement Agreechkbox;
	
	@FindBy(xpath="//*[@value='Continue']")
	WebElement Continuebtn;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement Confirmmsg;
	
	public void enterFname(String fname)
	{
		Fname.sendKeys(fname);
	}
	
	public void enterLname(String lname)
	{
		Lname.sendKeys(lname);
	}

	public void enteremail(String email)
	{
		Email.sendKeys(email);
	}
	
	public void entertelephone(String telephone)
	{
		Tel.sendKeys(telephone);
	}

	public void enterpassword(String pwd)
	{
		Password.sendKeys(pwd);
	}
	
	public void enterconpassword(String conpwd)
	{
		Confassword.sendKeys(conpwd);
	}

	public void clickAgreebtn()
	{
		Agreechkbox.click();
	}

	public void clickcontinue()
	{
		Continuebtn.click();
	}

	public String getconfirmMsg()
	{
		try
		{
			return (Confirmmsg.getText());
		}
		catch(Exception e)
		{
			return (e.getMessage()); 
		}
	}
	

}
