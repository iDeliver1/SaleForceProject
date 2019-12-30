package com.SaleForce.e2e;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.SaleForce.libraries.Excel_Libraries;
import com.SaleForce.libraries.Utility_Libraries;
import com.SaleForce.webelements.SaleForce_AddProduct;
import com.SaleForce.webelements.SaleForce_Login;
import com.SaleForce.webelements.SaleForce_Logout;
import com.SaleForce.webelements.SaleForce_OrderUpdate;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class T_Order_Update {

	WebDriver driver;						
	ExtentTest logger;						
	ExtentReports Extndreport;  			
	String testName = T_Leads.class.getName();
	
	@BeforeSuite
	public void DeleteFolder() throws IOException
	{
		FileUtils.deleteDirectory(new File("C:\\Reporting\\Report"));
	}
	
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
	public void Order_Update() throws Throwable
	{
		//----------------------------------------------Start report test-------------------------------------------------
		testName	= new Object(){}.getClass().getEnclosingMethod().getName();
		logger 		= Extndreport.startTest(testName);
		
		//---------------------------------Variables--------------------------------------
		String OrderNumber 	= Excel_Libraries.fRead("OrderNumber", System.getProperty("user.dir")+"\\src\\com\\SaleForce\\data\\Data.xlsx", "Order");
		
		String[] OrderUpdate = {OrderNumber};
		Utility_Libraries.fVerifyInputvalue(OrderUpdate,logger);
		OrderNumber   = OrderUpdate[0];
		
		SaleForce_OrderUpdate objOrderUpdate = new SaleForce_OrderUpdate(logger, driver, Extndreport);
		objOrderUpdate.Order_Update(OrderNumber);
	}
	
	@Test(priority=4,enabled=true)
	public void Product_Add() throws Throwable
		{	
			//----------------------------------------------Start report test-------------------------------------------------
			testName	= new Object(){}.getClass().getEnclosingMethod().getName();
			logger 		= Extndreport.startTest(testName);
			
			//---------------------------------Variables--------------------------------------
			String ProductName 	= Excel_Libraries.fRead("Product_Name_1", System.getProperty("user.dir")+"\\src\\com\\SaleForce\\data\\Data.xlsx", "Product");
			String Quantity 	= Excel_Libraries.fRead("Quantity", System.getProperty("user.dir")+"\\src\\com\\SaleForce\\data\\Data.xlsx", "Product");
			
			String[] ProductAdd = {ProductName, Quantity};
			Utility_Libraries.fVerifyInputvalue(ProductAdd,logger);
			ProductName   = ProductAdd[0];
			Quantity 	  = ProductAdd[1];
			
			
		}
	
	@Test(priority=5,enabled=true)
	public void Logout() throws Throwable
		{
			//----------------------------------------------Start report test-------------------------------------------------
			testName	= new Object(){}.getClass().getEnclosingMethod().getName();
			logger 		= Extndreport.startTest(testName);
		
			SaleForce_Logout objLogoutClass =  new SaleForce_Logout(logger, driver, Extndreport);	
			objLogoutClass.Logout();
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
	
	@AfterTest
	public void Close()
		{
			driver.close();
			driver.quit();
		}
	
}
