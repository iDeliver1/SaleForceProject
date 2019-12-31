package com.SaleForce.e2e;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.SaleForce.libraries.Excel_Libraries;
import com.SaleForce.libraries.Utility_Libraries;
import com.SaleForce.webelements.SaleForce_DeleteOrder;
import com.SaleForce.webelements.SaleForce_Login;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class T_Order_Delete {

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
			Utility_Libraries.fVerifyInputvalue(Launch,logger);
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
			Utility_Libraries.fVerifyInputvalue(Login,logger);
			Username 	= Login[0];
			Password 	= Login[1];
			//--------------------------------------------------------------------------------
			
			SaleForce_Login objLoginClass =  new SaleForce_Login(logger, driver, Extndreport);						
			objLoginClass.Login(Username,Password);						
		}
	
	@Test(priority=3,enabled=true)
	public void Delete() throws Throwable
	{	
		SaleForce_DeleteOrder objDeleteOrder = new SaleForce_DeleteOrder(logger, driver, Extndreport);
		objDeleteOrder.Delete();	
	}
	
}
