package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener

{
	public ExtentReports extent;
	public ExtentSparkReporter spark;
	public ExtentTest test;
	
  String reportname;
	
	
	public void onStart(ITestContext testContext)
	{
		String timeStamp= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	 reportname="Extent-Report_" + timeStamp + ".html";
     String repofilepath = ".//reports//" + reportname;
        spark=new ExtentSparkReporter(repofilepath);
        
        spark.config().setDocumentTitle("Open Cart Application");
        spark.config().setReportName("OpenCart Extent Reports");
        spark.config().setTheme(Theme.DARK);
        
       extent=new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Application", "OpenCart");
        extent.setSystemInfo("Module", "Admin");
        extent.setSystemInfo("SubModule", "Login");
        extent.setSystemInfo("Username", System.getProperty("user.name"));
        extent.setSystemInfo("Environement", "QA");
        
        
       String osname= testContext.getCurrentXmlTest().getParameter("os");
       extent.setSystemInfo("Operating system", osname);
       
     String browsername=  testContext.getCurrentXmlTest().getParameter("browser");
     extent.setSystemInfo("Browser Name", browsername);
     
     List<String> allgroup=testContext.getCurrentXmlTest().getIncludedGroups();
     if(!allgroup.isEmpty())
     {
    	 extent.setSystemInfo("Groups", allgroup.toString());
     }
		
	}
	
	public void onTestSuccess(ITestResult result)
	{
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS,result.getName()+ " : got passed");
	}
	
	public void onTestFailure(ITestResult result)
	{
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		 test.log(Status.FAIL, result.getName()+ " : got failed");
		 test.log(Status.INFO,result.getThrowable().getMessage());
		 
		 try
		 {
		   String cappath=new BaseClass().captureScreen(result.getName());
		   test.addScreenCaptureFromPath(cappath);
		 }
		 catch(IOException e1)
		 {
			 e1.printStackTrace();
		 }
		
		
	}
	
	public void onTestSkipped(ITestResult result)
	{
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName() + " : got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
		
	 String extentreportpath=".//reports//"  + reportname;
		File extentrep = new File(extentreportpath);
		try
		{
			Desktop.getDesktop().browse(extentrep.toURI());

		}
		catch(IOException e)
		{
			e.printStackTrace();
		} 
	}
}



