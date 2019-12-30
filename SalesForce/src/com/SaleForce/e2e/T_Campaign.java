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
import com.SaleForce.webelements.SaleForce_Campaign;
import com.SaleForce.webelements.SaleForce_Login;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class T_Campaign {

	
	WebDriver driver;						
	ExtentTest logger;						
	ExtentReports Extndreport;  			
	String testName = T_Campaign.class.getName();
	
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
	public void Campaign_Create() throws Throwable
		{
			//----------------------------------------------Start report test-------------------------------------------------
			testName	= new Object(){}.getClass().getEnclosingMethod().getName();
			logger 		= Extndreport.startTest(testName);
		
			//---------------------------------Variables--------------------------------------
			String Name 	    = Excel_Libraries.fRead("Name", System.getProperty("user.dir")+"\\src\\com\\SaleForce\\data\\Data.xlsx", "Campaigns");
			String Expected_rev = Excel_Libraries.fRead("Expected_rev", System.getProperty("user.dir")+"\\src\\com\\SaleForce\\data\\Data.xlsx", "Campaigns");
			String Budget_cost  = Excel_Libraries.fRead("Budget_cost", System.getProperty("user.dir")+"\\src\\com\\SaleForce\\data\\Data.xlsx", "Campaigns");
			String Status_ 	    = Excel_Libraries.fRead("Status", System.getProperty("user.dir")+"\\src\\com\\SaleForce\\data\\Data.xlsx", "Campaigns");
			
			String[] Campaign = {Name, Expected_rev, Budget_cost};
			Utility_Libraries.fVerifyvalue(Campaign,logger);
			Name 			= Campaign[0];
			Expected_rev    = Campaign[1];
			Budget_cost 	= Campaign[2];
			//--------------------------------------------------------------------------------
			
			SaleForce_Campaign objCampaignClass = new SaleForce_Campaign(logger, driver, Extndreport);
			objCampaignClass.Create_Campaign(Name, Expected_rev, Budget_cost, "day", Status_);	
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
