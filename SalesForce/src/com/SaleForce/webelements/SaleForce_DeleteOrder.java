package com.SaleForce.webelements;

import java.util.List;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.SaleForce.POM.DeleteOrder;
import com.SaleForce.libraries.Utility_Libraries;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class SaleForce_DeleteOrder {

	WebDriver driver;
	ExtentTest logger;
	ExtentReports Extndreport;
	DeleteOrder DeleteOrderPOM = new DeleteOrder();
	Utility_Libraries UtilityObject = new Utility_Libraries();
	
	public SaleForce_DeleteOrder(ExtentTest logger,WebDriver driver,ExtentReports Extndreport) {

		this.driver = driver;
		this.logger = logger;
		this.Extndreport = Extndreport;
	}

	public void Delete() throws Throwable
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver,30);
			driver.findElement(DeleteOrderPOM.OrderTab()).click();
				try
				{
					wait.until(ExpectedConditions.visibilityOfElementLocated(DeleteOrderPOM.PopUpWindow()));
					
					//Close the popup window
					driver.findElement(DeleteOrderPOM.PopUpWindow()).click();
				}
				catch(Exception f) {}
			List<WebElement> listDelete = driver.findElements(DeleteOrderPOM.Order_List());
				for(int i=0;i<=listDelete.size();i++)
				{
					String Order = listDelete.get(i).getText();
					listDelete.get(i).click();
					driver.findElement(DeleteOrderPOM.Delete()).click();
					Thread.sleep(1000);
					driver.switchTo().alert().accept();
					//-----------------------------Reporter
			 		UtilityObject.fReportpass("Delete Order", Order + " :Order is deleted successfully", logger, driver);
					//------------------------------------
					Thread.sleep(2000);
					wait.until(ExpectedConditions.stalenessOf(driver.findElement(DeleteOrderPOM.Go())));
				}			
		}
		catch(Exception e)
		{
			//-----------------------------Reporter
	 		UtilityObject.fReportfail("Delete Order", "Error :" + e +" Order is not deleted", logger, driver, Extndreport);
			//------------------------------------
		}	
	}
}
