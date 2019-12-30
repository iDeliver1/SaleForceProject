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
import com.SaleForce.webelements.SaleForce_Contracts;
import com.SaleForce.webelements.SaleForce_Login;
import com.SaleForce.webelements.SaleForce_Logout;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class T_Contracts {
	
	WebDriver driver;						
	ExtentTest logger;						
	ExtentReports Extndreport;  			
	String testName = T_Contracts.class.getName();

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
		@Test(priority=1)
		public void Launch(String Browser, String Url) throws Throwable
			{
			    //----------------------------------------------Start report test-------------------------------------------------
				testName	= new Object(){}.getClass().getEnclosingMethod().getName();
				logger 		= Extndreport.startTest(testName);
				String [] Launch = {Browser,Url};
				Utility_Libraries.fVerifyvalue(Launch, logger);
				Browser = Launch[0];
				Url = Launch[1];
				//---------------------------------------------------------------------------------------------------------------
				driver = Utility_Libraries.fgetBrowser(Browser, driver);					 
				driver.navigate().to(Url);	
				driver.manage().window().maximize();
			}
		
		@Parameters({ "Username" , "Password" })
		@Test(priority=2)
		public void Login(String Username,String Password) throws Throwable
			{
			 	//----------------------------------------------Start report test-------------------------------------------------
				testName	= new Object(){}.getClass().getEnclosingMethod().getName();
				logger 		= Extndreport.startTest(testName);
				String [] Login = {Username,Password};
				Utility_Libraries.fVerifyvalue(Login, logger);
				Username 	= Login[0];
				Password 	= Login[1];
				
				//--------------------------------------------------------------------------------			
				SaleForce_Login objLoginClass =  new SaleForce_Login(logger, driver, Extndreport);						
				objLoginClass.Login(Username,Password);							
			}
		
		@Test(priority=3)
		public void Contract_Create() throws Throwable
			{	
				//----------------------------------------------Start report test-------------------------------------------------
				testName	= new Object(){}.getClass().getEnclosingMethod().getName();
				logger 		= Extndreport.startTest(testName);
				
				//---------------------------------Variables--------------------------------------
				String CustomerName 	    = Excel_Libraries.fRead("CustomerName", System.getProperty("user.dir")+"\\src\\com\\SaleForce\\data\\Data.xlsx", "Contracts");
				String CustomerTitle 	    = Excel_Libraries.fRead("CustomerTitle", System.getProperty("user.dir")+"\\src\\com\\SaleForce\\data\\Data.xlsx", "Contracts");
				String PriceBook            = Excel_Libraries.fRead("PriceBook", System.getProperty("user.dir")+"\\src\\com\\SaleForce\\data\\Data.xlsx", "Contracts");
				String ContractMonth 	    = Excel_Libraries.fRead("ContractMonth", System.getProperty("user.dir")+"\\src\\com\\SaleForce\\data\\Data.xlsx", "Contracts");
				String OwnerExpiration     	= Excel_Libraries.fRead("OwnerExpiration", System.getProperty("user.dir")+"\\src\\com\\SaleForce\\data\\Data.xlsx", "Contracts");
				String DescriptionArea      = Excel_Libraries.fRead("DescriptionArea", System.getProperty("user.dir")+"\\src\\com\\SaleForce\\data\\Data.xlsx", "Contracts");
				
				String[] Contracts = {CustomerName, CustomerTitle, PriceBook, ContractMonth, OwnerExpiration,DescriptionArea};
				Utility_Libraries.fVerifyvalue(Contracts,logger);
				CustomerName 			= Contracts[0];
				CustomerTitle 		    = Contracts[1];
				PriceBook            	= Contracts[2];
				ContractMonth 	        = Contracts[3];
				OwnerExpiration 		= Contracts[4];
				DescriptionArea 		= Contracts[5];
				
				//-------------------------------------------------------------------------------------------
				SaleForce_Contracts objContractClass = new SaleForce_Contracts(logger, driver, Extndreport);
				objContractClass.Create_Contracts(CustomerName,CustomerTitle,PriceBook,ContractMonth,OwnerExpiration,DescriptionArea);
			}
		
		@Test(priority=4,enabled=true)
		public void Logout() throws Throwable
			{
			
				//----------------------------------------------Start report test-------------------------------------------------
				testName	= new Object(){}.getClass().getEnclosingMethod().getName();
				logger 		= Extndreport.startTest(testName);
			
				SaleForce_Logout objLogoutClass =  new SaleForce_Logout(logger, driver, Extndreport);	
				objLogoutClass.Logout();
			}
		
		@Test(priority=5,enabled=true)
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
