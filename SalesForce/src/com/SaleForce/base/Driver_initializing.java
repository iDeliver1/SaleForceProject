package com.SaleForce.base;

import org.openqa.selenium.WebDriver;

import com.SaleForce.libraries.Excel_Libraries;
import com.SaleForce.libraries.Utility_Libraries;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class Driver_initializing {

	public static WebDriver driver;
	public static ExtentTest logger ;
	public static ExtentReports Extndreport;
	
	public void initializationBrowser(String Browser, String URL)
	{
		driver = Utility_Libraries.fgetBrowser(Browser, driver);					 
		driver.navigate().to(URL);	
		driver.manage().window().maximize();
	}
	
	public void initializationReporter(String testName) throws Throwable
	{
		//--------------------------------------------Calling report-----------------------------------------------
		Extndreport = Utility_Libraries.fReport();
		//------------------------------------------Create a Excel file--------------------------------------------
		Excel_Libraries.fCreateExcelfile(); 
		logger = Extndreport.startTest(testName);
	}

}
