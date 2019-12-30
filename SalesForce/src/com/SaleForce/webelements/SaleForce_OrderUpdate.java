package com.SaleForce.webelements;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.SaleForce.POM.UpdateOrder;
import com.SaleForce.libraries.Utility_Libraries;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class SaleForce_OrderUpdate {

	WebDriver driver;
	ExtentTest logger;
	ExtentReports Extndreport;
	Utility_Libraries UtilityObject = new Utility_Libraries();
	UpdateOrder UpdateOrderPOM = new UpdateOrder();
	
	public SaleForce_OrderUpdate(ExtentTest logger,WebDriver driver,ExtentReports Extndreport)
	{
		this.driver = driver;
		this.logger = logger;
		this.Extndreport = Extndreport;
	}

	public void Order_Update(String OrderNumber) throws Throwable
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver,30);
			driver.findElement(UpdateOrderPOM.OrderTab()).click();
				try
				{
					wait.until(ExpectedConditions.visibilityOfElementLocated(UpdateOrderPOM.PopUpWindow()));
					//Close the popup window
					driver.findElement(UpdateOrderPOM.PopUpWindow()).click();
				}
				catch(Exception f) {}
		Select View = new Select(driver.findElement(UpdateOrderPOM.View()));
		View.selectByVisibleText("All Orders");
		driver.findElement(UpdateOrderPOM.Go()).click();
		List<WebElement> OrderList = driver.findElements(UpdateOrderPOM.OrderList());
			for(int iterator=0;iterator<=OrderList.size();iterator++)
			{
				if(OrderList.get(iterator).getText().equals(OrderNumber))
				{
					//-----------------------------Reporter------------------------------------------/
					UtilityObject.fReportpass("Order Select", OrderList.get(iterator).getText() + " : Order is selected to update", logger, driver);
					//-------------------------------------------------------------------------------
					OrderList.get(iterator).click();
					break;
				}
			}
		}
		catch(Exception e)
				{
					//-----------------------------Reporter------------------------------------------/
					UtilityObject.fReportfail("Error", "Error : " + e, logger, driver, Extndreport);
					//-------------------------------------------------------------------------------/
				}
	}
}
