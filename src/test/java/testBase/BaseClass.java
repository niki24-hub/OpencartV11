package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseClass 
{
public static WebDriver driver;
public Logger logger;
public Properties p;
	
	@BeforeClass(groups={"Master","Sanity"})
	@Parameters({ "os" , "browser"})
   public void setUp(@Optional("os") String os,@Optional("browser")String br) throws IOException, InterruptedException
   
   {
		
		//Loading property file
	    String path= ".//src//test//resources//config.properties";
		FileReader file=new FileReader(path);
		p=new Properties();
		p.load(file); 
		
		
		logger=LogManager.getLogger(this.getClass());
		
		if(p.getProperty("Execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities cap=new DesiredCapabilities();
			
			if(os.equalsIgnoreCase("windows"))
			{
				cap.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("linux"))
			{
				cap.setPlatform(Platform.LINUX);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				cap.setPlatform(Platform.MAC);
			}
			
			else
			{
				System.out.println("No valid operating system found"); return;
			}
			
			switch(br.toLowerCase())
			{
			case "chrome" : cap.setBrowserName("chrome"); break;
			case "firefox" : cap.setBrowserName("firefox"); break;
			 default : System.out.println("No valid browser found"); return;

			}
			
			
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
			//Thread.sleep(5000);
			
		}
		
		
		
		
		
		if(p.getProperty("Execution_env").equalsIgnoreCase("local"))
		{
		
		switch(br.toLowerCase())
		{
		case "safari" : driver=new SafariDriver(); break;

		case "chrome" :  driver=new ChromeDriver(); break;
		case "firefox" :  driver=new FirefoxDriver(); break;
		
		default : System.out.println("No valid browser mentioned"); return;
		}
		}
	  // driver=new ChromeDriver();
	   driver.manage().deleteAllCookies();
	   driver.get(p.getProperty("appURL1"));
	   
	   //driver.get(p.getProperty("appURL1"));
	   driver.manage().window().maximize();
	   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	   
   }
   
	
	@AfterClass(groups={"Master","Sanity"})
   public void tearDown()
   {
	//driver.close();   
   }
	
	public String randAlpha()
	{
		String randomAlphabets=  RandomStringUtils.randomAlphabetic(5);
		return randomAlphabets;
	}
	
	public String randNum()
	{
		String randomNumber=  RandomStringUtils.randomNumeric(10);
		return randomNumber;
	}
	
	public String randAlphanumeric()
	{
		String randomAlphabets=  RandomStringUtils.randomAlphabetic(4);
		String randomNumber=  RandomStringUtils.randomNumeric(2);

		return (randomAlphabets+"@"+randomNumber);
	}
	
	public String captureScreen(String tnam) throws IOException
	
	{
		String timeStamp= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		
		
				File Sourcefile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				
				String destfilepath= ".//allscreenshots//" + tnam + "_" + timeStamp;
				File targetfile= new File(destfilepath);
				
				Sourcefile.renameTo(targetfile);
		
		
		return destfilepath;
		
	}

}
