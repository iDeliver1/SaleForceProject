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
import com.SaleForce.webelements.SaleForce_Campaign;
import com.SaleForce.webelements.SaleForce_Login_New;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class T_Campaign extends Driver_initializing{
	
	WebDriver driver;						
	ExtentTest logger;						
	ExtentReports Extndreport;  
	SaleForce_Login_New objLoginClass;
	SaleForce_Campaign objCampaignClass; 
	String testName = T_Leads.class.getName();
	
	@BeforeSuite
	public void Setup() throws Throwable 
		{	 
			initializationReporter(testName);
		}
	
	@BeforeTest
	public void CreateObject() throws Throwable 
		{
			 objLoginClass = new SaleForce_Login_New();	
			 objCampaignClass = new SaleForce_Campaign();
		}
	
	@Parameters({ "Browser" , "Url" , "Username" , "Password"})
	@Test()
	public void Launch(String Browser, String Url, String Username, String Password) throws Throwable
		{
			//---------------------------------Variables--------------------------------------
			String Name 	    = Excel_Libraries.fRead("Name", System.getProperty("user.dir")+"\\src\\com\\SaleForce\\data\\Data.xlsx", "Campaigns");
			String Expected_rev = Excel_Libraries.fRead("Expected_rev", System.getProperty("user.dir")+"\\src\\com\\SaleForce\\data\\Data.xlsx", "Campaigns");
			String Budget_cost  = Excel_Libraries.fRead("Budget_cost", System.getProperty("user.dir")+"\\src\\com\\SaleForce\\data\\Data.xlsx", "Campaigns");
			String Status_ 	    = Excel_Libraries.fRead("Status", System.getProperty("user.dir")+"\\src\\com\\SaleForce\\data\\Data.xlsx", "Campaigns");
			
			initializationBrowser(Browser, Url);
			
			//Login					
			objLoginClass.Login(Username,Password);	
			
			//Create Campaign
			objCampaignClass.Create_Campaign(Name, Expected_rev, Budget_cost, "day", Status_);	
			
			//Close
			driver.close();
		}
	
	@AfterMethod	
	public void Flush(ITestResult result) throws Throwable		
	    {  
			  Extndreport.endTest(logger);
			  Extndreport.flush();
	    }
}
