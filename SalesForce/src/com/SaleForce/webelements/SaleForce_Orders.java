package com.SaleForce.webelements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.SaleForce.POM.CreateOrder;
import com.SaleForce.libraries.Utility_Libraries;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class SaleForce_Orders {
		
		WebDriver driver;
		ExtentTest logger;
		ExtentReports Extndreport;
		CreateOrder CreateOrderPOM = new CreateOrder();
		Utility_Libraries UtilityObject = new Utility_Libraries();
		
		public SaleForce_Orders(ExtentTest logger,WebDriver driver,ExtentReports Extndreport)
		{
			this.driver = driver;
			this.logger = logger;
			this.Extndreport = Extndreport;
		}
		
		public String Create_Orders(String AccountName,String ContractNumber,String OrderDescription) throws Throwable
		{
				try
				{
					WebDriverWait wait = new WebDriverWait(driver,30);
					String CurrentDate = UtilityObject.fGetCurrentDate();
					driver.findElement(CreateOrderPOM.OrderTab()).click();
						try
						{
							wait.until(ExpectedConditions.visibilityOfElementLocated(CreateOrderPOM.PopUpWindow()));
							//Close the popup window
							driver.findElement(CreateOrderPOM.PopUpWindow()).click();
						}
						catch(Exception f) {}
				 	driver.findElement(CreateOrderPOM.NewLink()).click();
					 	try
					 	{
					 		driver.findElement(CreateOrderPOM.AccountName()).isDisplayed();
							//-----------------------------Reporter
					 		UtilityObject.fReportpass("Create Order", "Create Order page is open successfully", logger, driver);
							//------------------------------------
					 	}
					 	catch(Exception e)
					 	{
					 		//-----------------------------Reporter
					 		UtilityObject.fReportfail("Create Order", "Error :" + e +" Create Order page is not open successfully", logger, driver, Extndreport);
							//------------------------------------
					 	}
					wait.until(ExpectedConditions.visibilityOfElementLocated(CreateOrderPOM.AccountName()));
			        driver.findElement(CreateOrderPOM.AccountName()).sendKeys(AccountName);
			        driver.findElement(CreateOrderPOM.OrderStartDate()).sendKeys(CurrentDate);
					driver.findElement(CreateOrderPOM.ContractNumber()).sendKeys(ContractNumber);
					driver.findElement(CreateOrderPOM.OrderDescription()).sendKeys(OrderDescription);
					//-------------------------Billing Address----------------------------------------/
					Thread.sleep(2000);
					driver.findElement(CreateOrderPOM.OrderSaveButton()).click();
					
					//----------------------Verification Contract is Created-------------------------/
					String OrderNumber_ =  driver.findElement(CreateOrderPOM.OrderNumber()).getText();
					OrderNumber_ = OrderNumber_.replace("Order ","");
						if(OrderNumber_.matches("[0-9]{8}"))
						{
						    //-----------------------------Reporter------------------------------------------/
							UtilityObject.fReportpass("Order create", "Order is successfully created Order Number = " + OrderNumber_, logger, driver);
							//-------------------------------------------------------------------------------
							return OrderNumber_;
						}
						else
						{
							//-----------------------------Reporter------------------------------------------/
							UtilityObject.fReportfail("Order create", "Order is not successfully created Order Number = " + OrderNumber_, logger, driver, Extndreport);
							//-------------------------------------------------------------------------------/
						}
				}
				catch(Exception e)
				{
					//-----------------------------Reporter------------------------------------------/
					UtilityObject.fReportfail("Error", "Error : " + e, logger, driver, Extndreport);
					//-------------------------------------------------------------------------------/
				}
			return null;
		}
}
