package com.SaleForce.e2e;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.SaleForce.libraries.Excel_Libraries;
import com.SaleForce.libraries.Utility_Libraries;
import com.SaleForce.webelements.SaleForce_Accounts;
import com.SaleForce.webelements.SaleForce_AddProduct;
import com.SaleForce.webelements.SaleForce_Contracts;
import com.SaleForce.webelements.SaleForce_Login;
import com.SaleForce.webelements.SaleForce_Logout;
import com.SaleForce.webelements.SaleForce_Orders;
import com.SaleForce.webelements.SaleForce_Product;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class T_Order_Create_1 {

	WebDriver driver;						
	ExtentTest logger;	
	String Account_Name;
	String Order_Number;
	String Product_Name_;
	String Contract_Number;
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
	public void AccountCreate() throws Throwable
		{
			//----------------------------------------------Start report test-------------------------------------------------
			testName	= new Object(){}.getClass().getEnclosingMethod().getName();
			logger 		= Extndreport.startTest(testName);
		
			//Data Load
			String AccountName 	      = Excel_Libraries.fRead("AccountName", System.getProperty("user.dir")+"\\src\\com\\SaleForce\\data\\Data.xlsx", "Account");
			String AccountNumber 	  = Excel_Libraries.fRead("AccountNumber", System.getProperty("user.dir")+"\\src\\com\\SaleForce\\data\\Data.xlsx", "Account");
			String AccountDescription = Excel_Libraries.fRead("AccountDescription", System.getProperty("user.dir")+"\\src\\com\\SaleForce\\data\\Data.xlsx", "Account");
			
			//Account Create
			SaleForce_Accounts objAccountClass = new SaleForce_Accounts(logger, driver, Extndreport);
			Account_Name = objAccountClass.Create_Accounts(AccountName,AccountNumber,AccountDescription);
		}
	
	@Test(priority=4,enabled=true)
	public void ContractCreate() throws Throwable
		{
			//----------------------------------------------Start report test-------------------------------------------------
			testName	= new Object(){}.getClass().getEnclosingMethod().getName();
			logger 		= Extndreport.startTest(testName);
		
			//Data Load
			String CustomerTitle 	  = Excel_Libraries.fRead("CustomerTitle", System.getProperty("user.dir")+"\\src\\com\\SaleForce\\data\\Data.xlsx", "Contracts");
			String PriceBook          = Excel_Libraries.fRead("PriceBook", System.getProperty("user.dir")+"\\src\\com\\SaleForce\\data\\Data.xlsx", "Contracts");
			String ContractMonth 	  = Excel_Libraries.fRead("ContractMonth", System.getProperty("user.dir")+"\\src\\com\\SaleForce\\data\\Data.xlsx", "Contracts");
			String OwnerExpiration    = Excel_Libraries.fRead("OwnerExpiration", System.getProperty("user.dir")+"\\src\\com\\SaleForce\\data\\Data.xlsx", "Contracts");
			String DescriptionArea    = Excel_Libraries.fRead("DescriptionArea", System.getProperty("user.dir")+"\\src\\com\\SaleForce\\data\\Data.xlsx", "Contracts");
			
			//Contract Create
			SaleForce_Contracts objContractClass = new SaleForce_Contracts(logger, driver, Extndreport);
			Contract_Number = objContractClass.Create_Contracts(Account_Name,CustomerTitle,PriceBook,ContractMonth,OwnerExpiration,DescriptionArea);
		}
	
	@Test(priority=5,enabled=true)
	public void ProductCreate() throws Throwable
		{
			//----------------------------------------------Start report test-------------------------------------------------
			testName	= new Object(){}.getClass().getEnclosingMethod().getName();
			logger 		= Extndreport.startTest(testName);
			
			//Data Load
			String Product_Name  	  = Excel_Libraries.fRead("Product_Name", System.getProperty("user.dir")+"\\src\\com\\SaleForce\\data\\Data.xlsx", "Product");
			String Price 	          = Excel_Libraries.fRead("Price", System.getProperty("user.dir")+"\\src\\com\\SaleForce\\data\\Data.xlsx", "Product");
			
			//Product Create
			SaleForce_Product objProductClass = new SaleForce_Product(logger, driver, Extndreport);
			Product_Name_ = objProductClass.Create_Product(Product_Name, Price); 
		}
	
	@Test(priority=6,enabled=true)
	public void OrderCreate() throws Throwable
		{
			//----------------------------------------------Start report test-------------------------------------------------
			testName	= new Object(){}.getClass().getEnclosingMethod().getName();
			logger 		= Extndreport.startTest(testName);
		
			//Data Load
			String OrderDescription   = Excel_Libraries.fRead("OrderDescription", System.getProperty("user.dir")+"\\src\\com\\SaleForce\\data\\Data.xlsx", "Order");
			
			//Order Create
			SaleForce_Orders objOrderClass = new SaleForce_Orders(logger, driver, Extndreport);
			Order_Number = objOrderClass.Create_Orders(Account_Name, Contract_Number, OrderDescription);  
			Excel_Libraries.fWrite(Order_Number, System.getProperty("user.dir")+"\\src\\com\\SaleForce\\data\\Data.xlsx", "Order");
		}

	@Test(priority=7,enabled=true)
	public void AddProduct() throws Throwable
		{
			//----------------------------------------------Start report test-------------------------------------------------
			testName	= new Object(){}.getClass().getEnclosingMethod().getName();
			logger 		= Extndreport.startTest(testName);
		
			//Data Load
			String Quantity 		  = Excel_Libraries.fRead("Quantity", System.getProperty("user.dir")+"\\src\\com\\SaleForce\\data\\Data.xlsx", "Product");
			
			//Add Product
			SaleForce_AddProduct objAddProductClass = new SaleForce_AddProduct(logger, driver, Extndreport);
			objAddProductClass.Product_Add(Product_Name_, Quantity);
		}
	
	@Test(priority=8,enabled=true)
	public void LogOut() throws Throwable
		{
			//Logout
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
