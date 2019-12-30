package com.SaleForce.e2e;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.SaleForce.libraries.Excel_Libraries;
import com.SaleForce.libraries.Utility_Libraries;
import com.SaleForce.webelements.SaleForce_Leads;
import com.SaleForce.webelements.SaleForce_Login;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class T_Leads {

	
	WebDriver driver;						
	ExtentTest logger;						
	ExtentReports Extndreport;  			
	String testName = T_Leads.class.getName();
	
	@BeforeTest
	public void Create() throws Throwable 
		{	 
			//--------------------------------------------Calling report-----------------------------------------------
			Extndreport = Utility_Libraries.fReport();
			//------------------------------------------Create a Excel file--------------------------------------------
			Excel_Libraries.fCreateExcelfile(); 
			logger 		= Extndreport.startTest(testName); 
		}
	
	@Parameters({ "Browser" , "Url" })
	@Test(priority=1,enabled=true)
	public void Launch(String Browser, String Url) throws Throwable
		{
		    //----------------------------------------------Start report test-------------------------------------------------
			testName	= new Object(){}.getClass().getEnclosingMethod().getName();
			logger 		= Extndreport.startTest(testName);
				
			//---------------------------------Variables--------------------------------------
			String[] Launch = {Browser, Url};
			Utility_Libraries.fVerifyvalue(Launch,logger);
			Browser 	= Launch[0];
			Url 	    = Launch[1];
			//--------------------------------------------------------------------------------
			
			driver = Utility_Libraries.fgetBrowser(Browser, driver);					 
			driver.navigate().to(Url);	
			driver.manage().window().maximize();
		}
	
	@Parameters({ "Username" , "Password" })
	@Test(priority=2,enabled=true)
	public void Login(String Username, String Password) throws Throwable
		{
		 	//----------------------------------------------Start report test-------------------------------------------------
			testName	= new Object(){}.getClass().getEnclosingMethod().getName();
			logger 		= Extndreport.startTest(testName);
			
			//---------------------------------Variables--------------------------------------
			String[] Login = {Username, Password};
			Utility_Libraries.fVerifyvalue(Login,logger);
			Username 	= Login[0];
			Password 	= Login[1];
			//--------------------------------------------------------------------------------
			
			SaleForce_Login objLoginClass =  new SaleForce_Login(logger, driver, Extndreport);						
			objLoginClass.Login(Username,Password);						
		}
	
	@Test(priority=3,enabled=true)
	public void Lead_Create() throws Throwable
		{	
		
			//----------------------------------------------Start report test-------------------------------------------------
			testName	= new Object(){}.getClass().getEnclosingMethod().getName();
			logger 		= Extndreport.startTest(testName);
			
			//---------------------------------Variables--------------------------------------
			String FName 	    = Excel_Libraries.fRead("Fname", System.getProperty("user.dir")+"\\src\\com\\SaleForce\\data\\Data.xlsx", "Leads");
			String LName 	    = Excel_Libraries.fRead("Lname", System.getProperty("user.dir")+"\\src\\com\\SaleForce\\data\\Data.xlsx", "Leads");
			String CompanyName  = Excel_Libraries.fRead("CompanyName", System.getProperty("user.dir")+"\\src\\com\\SaleForce\\data\\Data.xlsx", "Leads");
			String Status 	    = Excel_Libraries.fRead("Status", System.getProperty("user.dir")+"\\src\\com\\SaleForce\\data\\Data.xlsx", "Leads");
			String Campaign     = Excel_Libraries.fRead("Name", System.getProperty("user.dir")+"\\src\\com\\SaleForce\\data\\Data.xlsx", "Campaigns");
			
			String[] Leads = {FName, LName, CompanyName, Status, Campaign};
			Utility_Libraries.fVerifyvalue(Leads,logger);
			FName 			= Leads[0];
			LName 		    = Leads[1];
			CompanyName 	= Leads[2];
			Status 	        = Leads[3];
			Campaign 		= Leads[4];
			//--------------------------------------------------------------------------------
			
			SaleForce_Leads objLeadClass = new SaleForce_Leads(logger, driver, Extndreport);
			objLeadClass.Create_Leads(FName, LName, CompanyName, Status, Campaign);
		}
	
	@Test(priority=4,enabled=true)
	public void Close()
		{
			//----------------------------------------------Start report test-------------------------------------------------
			testName	= new Object(){}.getClass().getEnclosingMethod().getName();
			logger 		= Extndreport.startTest(testName);
			
			driver.close();
		}
	
	@AfterMethod	
	public void Flush(ITestResult result) throws Throwable		
		{ 
				if(result.getStatus()==ITestResult.FAILURE) 
				{ 			
					logger.log(LogStatus.FAIL,"Error :"+result.getThrowable());//+report.getClass()+" Test "+logger.getTest().statusMessage);//,ClassTest.takeScreenShot());			
				}		
				  if (result.getStatus() == ITestResult.FAILURE) {
			            logger.log(LogStatus.FAIL, "Test '"+ testName+ "' Failed : Cause>>"+result.getThrowable(),logger.addScreenCapture(Utility_Libraries.fScreenReport(driver)));
			            Excel_Libraries.fExcelReporter( ""+result.getThrowable()+"" , "Condition must be fullfill", "FAIL",""+new SimpleDateFormat("MM_dd_y_hhmmssa").format(new Date()));
			            Extndreport.endTest(logger);
			            Extndreport.flush();
			            System.exit(1);
				  	} else if (result.getStatus() == ITestResult.SKIP) {
			        	logger.log(LogStatus.SKIP, "Test '"+ testName+ "' skipped : Cause>>"+ result.getThrowable(),logger.addScreenCapture(Utility_Libraries.fScreenReport(driver)));
			        } else {
			        	logger.log(LogStatus.PASS, "Test  '"+ testName+"'  passed");
			        }	  
				  Extndreport.endTest(logger);
				  Extndreport.flush();
		}
}
