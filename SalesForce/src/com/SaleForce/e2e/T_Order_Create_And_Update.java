package com.SaleForce.e2e;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
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
import com.SaleForce.webelements.SaleForce_OrderUpdate;
import com.SaleForce.webelements.SaleForce_Product;
import com.SaleForce.webelements.SaleForce_Orders;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class T_Order_Create_And_Update {

	WebDriver driver;						
	ExtentTest logger;	
	String Account_Name;
	String Order_Number;
	String Product_Name_;
	String Contract_Number;
	ExtentReports Extndreport;  			
	String testName = T_Leads.class.getName();
	Utility_Libraries Utility_Object = new Utility_Libraries();
	
	@BeforeTest
	public void Create() throws Throwable 
		{	 
			//--------------------------------------------Calling report-----------------------------------------------
			Extndreport = Utility_Libraries.fReport();
			//------------------------------------------Create a Excel file--------------------------------------------
			Excel_Libraries.fCreateExcelfile(); 
			logger 		= Extndreport.startTest(testName); 
		}
	
	@Parameters({ "Browser" , "Url" , "Username" , "Password"})
	@Test(priority=1,enabled=true)
	public void Order_Create(String Browser, String Url, String Username, String Password) throws Throwable
		{
		    //----------------------------------------------Start report test-------------------------------------------------
			testName	= new Object(){}.getClass().getEnclosingMethod().getName();
			logger 		= Extndreport.startTest(testName);
			
			//Data Load
			String AccountName 	      = Excel_Libraries.fRead("AccountName", System.getProperty("user.dir")+"\\src\\com\\SaleForce\\data\\Data.xlsx", "Account");
			String AccountNumber 	  = Excel_Libraries.fRead("AccountNumber", System.getProperty("user.dir")+"\\src\\com\\SaleForce\\data\\Data.xlsx", "Account");
			String AccountDescription = Excel_Libraries.fRead("AccountDescription", System.getProperty("user.dir")+"\\src\\com\\SaleForce\\data\\Data.xlsx", "Account");
			String CustomerTitle 	  = Excel_Libraries.fRead("CustomerTitle", System.getProperty("user.dir")+"\\src\\com\\SaleForce\\data\\Data.xlsx", "Contracts");
			String PriceBook          = Excel_Libraries.fRead("PriceBook", System.getProperty("user.dir")+"\\src\\com\\SaleForce\\data\\Data.xlsx", "Contracts");
			String ContractMonth 	  = Excel_Libraries.fRead("ContractMonth", System.getProperty("user.dir")+"\\src\\com\\SaleForce\\data\\Data.xlsx", "Contracts");
			String OwnerExpiration    = Excel_Libraries.fRead("OwnerExpiration", System.getProperty("user.dir")+"\\src\\com\\SaleForce\\data\\Data.xlsx", "Contracts");
			String DescriptionArea    = Excel_Libraries.fRead("DescriptionArea", System.getProperty("user.dir")+"\\src\\com\\SaleForce\\data\\Data.xlsx", "Contracts");
			String Product_Name  	  = Excel_Libraries.fRead("Product_Name", System.getProperty("user.dir")+"\\src\\com\\SaleForce\\data\\Data.xlsx", "Product");
			String Price 	          = Excel_Libraries.fRead("Price", System.getProperty("user.dir")+"\\src\\com\\SaleForce\\data\\Data.xlsx", "Product");
			String OrderDescription   = Excel_Libraries.fRead("OrderDescription", System.getProperty("user.dir")+"\\src\\com\\SaleForce\\data\\Data.xlsx", "Order");
			String Quantity 		  = Excel_Libraries.fRead("Quantity", System.getProperty("user.dir")+"\\src\\com\\SaleForce\\data\\Data.xlsx", "Product");
			
			//Data Load Verify
			String[] Order_Create = {Browser, Url, Username, Password, AccountName, AccountNumber, AccountDescription, CustomerTitle, PriceBook, ContractMonth, OwnerExpiration, DescriptionArea, Product_Name, Price, OrderDescription, Quantity};
			Utility_Libraries.fVerifyInputvalue(Order_Create,logger);
			Browser 			= Order_Create[0];
			Url 	    		= Order_Create[1];
			Username			= Order_Create[2];
			Password			= Order_Create[3];
			AccountName 		= Order_Create[4];
			AccountNumber 		= Order_Create[5];
			AccountDescription  = Order_Create[6];
			CustomerTitle 	 	= Order_Create[7];
			PriceBook        	= Order_Create[8];
			ContractMonth 	 	= Order_Create[9];
			OwnerExpiration  	= Order_Create[10];
			DescriptionArea  	= Order_Create[11];
			Product_Name  		= Order_Create[12];
			Price 		  		= Order_Create[13];
			OrderDescription  	= Order_Create[14];
			Quantity 	  		= Order_Create[15];
			
			//Launch
			driver = Utility_Libraries.fgetBrowser(Browser, driver);					 
			driver.navigate().to(Url);	
			driver.manage().window().maximize();
			
			//Login
			SaleForce_Login objLoginClass =  new SaleForce_Login(logger, driver, Extndreport);						
			objLoginClass.Login(Username,Password);	
			
			//Account Create
			SaleForce_Accounts objAccountClass = new SaleForce_Accounts(logger, driver, Extndreport);
			Account_Name = objAccountClass.Create_Accounts(AccountName,AccountNumber,AccountDescription);
			
			//Contract Create
			SaleForce_Contracts objContractClass = new SaleForce_Contracts(logger, driver, Extndreport);
			Contract_Number = objContractClass.Create_Contracts(Account_Name,CustomerTitle,PriceBook,ContractMonth,OwnerExpiration,DescriptionArea);
			
			//Product Create
			SaleForce_Product objProductClass = new SaleForce_Product(logger, driver, Extndreport);
			Product_Name_ = objProductClass.Create_Product(Product_Name, Price); 
			
			//Order Create
			SaleForce_Orders objOrderClass = new SaleForce_Orders(logger, driver, Extndreport);
			Order_Number = objOrderClass.Create_Orders(Account_Name, Contract_Number, OrderDescription);  
			
			//Add Product
			SaleForce_AddProduct objAddProductClass = new SaleForce_AddProduct(logger, driver, Extndreport);
			objAddProductClass.Product_Add(Product_Name_, Quantity);
			
			//Logout
			SaleForce_Logout objLogoutClass =  new SaleForce_Logout(logger, driver, Extndreport);	
			objLogoutClass.Logout();
			
			//Close
			driver.close();
			driver.quit();
				//-----------------------------Reporter
				Utility_Object.fReportpass("Browser Close", "Browser is successfully Close", logger, driver);
				//------------------------------------
		}
	
	@Parameters({ "Browser" , "Url" , "Username" , "Password"})
	@Test(priority=2,enabled=true)
	public void Order_Update(String Browser, String Url, String Username, String Password) throws Throwable
		{
			//----------------------------------------------Start report test-------------------------------------------------
			testName	= new Object(){}.getClass().getEnclosingMethod().getName();
			logger 		= Extndreport.startTest(testName);
			
			//Data Load
			String OrderNumber 	= Order_Number;
			String ProductName 	= Excel_Libraries.fRead("Product_Name_1", System.getProperty("user.dir")+"\\src\\com\\SaleForce\\data\\Data.xlsx", "Product");
			String Quantity 	= Excel_Libraries.fRead("Quantity", System.getProperty("user.dir")+"\\src\\com\\SaleForce\\data\\Data.xlsx", "Product");
			
			//Data Load Verify
			String[] OrderUpdate = {Browser, Url, Username, Password, OrderNumber, ProductName, Quantity};
			Utility_Libraries.fVerifyInputvalue(OrderUpdate,logger);
			Browser   	  = OrderUpdate[0];
			Url   		  = OrderUpdate[1];
			Username      = OrderUpdate[2];
			Password      = OrderUpdate[3];
			OrderNumber   = OrderUpdate[4];
			ProductName   = OrderUpdate[5];
			Quantity   	  = OrderUpdate[6];
			
			//Launch
			driver = Utility_Libraries.fgetBrowser(Browser, driver);					 
			driver.navigate().to(Url);	
			driver.manage().window().maximize();
			
			//Login
			SaleForce_Login objLoginClass =  new SaleForce_Login(logger, driver, Extndreport);						
			objLoginClass.Login(Username,Password);	
			
			//Order Update
			SaleForce_OrderUpdate objOrderUpdate = new SaleForce_OrderUpdate(logger, driver, Extndreport);
			objOrderUpdate.Order_Update(OrderNumber);
			
			//Add Product
			SaleForce_AddProduct objOrderClass = new SaleForce_AddProduct(logger, driver, Extndreport);
			objOrderClass.Product_Add(ProductName, Quantity);
			
			//Logout
			SaleForce_Logout objLogoutClass =  new SaleForce_Logout(logger, driver, Extndreport);	
			objLogoutClass.Logout();
			
			//Close
			driver.close();
			driver.quit();
				//-----------------------------Reporter
				Utility_Object.fReportpass("Browser Close", "Browser is successfully Close", logger, driver);
				//------------------------------------
		}
	
	@AfterMethod	
	public void Close() throws Throwable		
		{
			Extndreport.endTest(logger);
			Extndreport.flush();
		}
}
