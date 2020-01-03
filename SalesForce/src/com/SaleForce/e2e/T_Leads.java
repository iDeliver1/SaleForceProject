package com.SaleForce.e2e;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.SaleForce.base.Driver_initializing;
import com.SaleForce.libraries.Excel_Libraries;
import com.SaleForce.webelements.SaleForce_Leads;
import com.SaleForce.webelements.SaleForce_Login;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class T_Leads extends Driver_initializing {

	WebDriver driver;						
	ExtentTest logger;						
	ExtentReports Extndreport;  
	SaleForce_Login objLoginClass;
	SaleForce_Leads objLeadClass; 
	String testName = T_Leads.class.getName();
	
	@BeforeSuite
	public void CreateFile() throws Throwable 
		{	 
			initializationReporter(testName);
		}
	
	@BeforeTest
	public void Setup() throws Throwable 
		{
			 objLoginClass = new SaleForce_Login(logger, driver, Extndreport);	
			 objLeadClass  = new SaleForce_Leads(logger, driver, Extndreport);
		}
	
	@Parameters({ "Browser" , "Url" })
	@Test(priority=1,enabled=true)
	public void Launch(String Browser, String Url) throws Throwable
		{
		    //----------------------------------------------Start report test-------------------------------------------------
			testName	= new Object(){}.getClass().getEnclosingMethod().getName();
			logger 		= Extndreport.startTest(testName);
			
			initializationBrowser(Browser, Url);
		}
	
	@Parameters({ "Username" , "Password" })
	@Test(priority=2,enabled=true)
	public void Login(String Username, String Password) throws Throwable
		{
		 	//----------------------------------------------Start report test-------------------------------------------------
			testName	= new Object(){}.getClass().getEnclosingMethod().getName();
			logger 		= Extndreport.startTest(testName);
			
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
			Extndreport.endTest(logger);
			Extndreport.flush();
		}
}
