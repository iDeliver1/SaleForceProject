package com.SaleForce.webelements;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.SaleForce.POM.AddProduct;
import com.SaleForce.libraries.Utility_Libraries;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class SaleForce_AddProduct {

	WebDriver driver;
	ExtentTest logger;
	ExtentReports Extndreport;
	Utility_Libraries UtilityObject = new Utility_Libraries();
	AddProduct AddProductPOM = new AddProduct();
	
	public SaleForce_AddProduct(ExtentTest logger,WebDriver driver,ExtentReports Extndreport) {

		this.driver = driver;
		this.logger = logger;
		this.Extndreport = Extndreport;
	}

	public void Product_Add(String ProductName, String Quantity) throws Throwable
	{
		try
		{
			String Product = null;
			driver.findElement(AddProductPOM.AddProduct_()).click();
			List<WebElement> Productlist_  = driver.findElements(AddProductPOM.ProductName());
				for(int i=0;i<=Productlist_.size();i++)
				{
					if(Productlist_.get(i).getText().contains(ProductName))
					{
						Product = Productlist_.get(i).getText();
						List<WebElement> ProductChecklist_  = driver.findElements(AddProductPOM.CheckBox());
						ProductChecklist_.get(i).click();
							//-----------------------------Reporter
							UtilityObject.fReportpass("Product Select", "Product is selected by checkbox " + Product, logger, driver);
							//------------------------------------
						break;
					}
				}
				driver.findElement(AddProductPOM.ProSelect()).click();
				driver.findElement(AddProductPOM.Quantity()).sendKeys(Quantity);
					//-----------------------------Reporter
					UtilityObject.fReportpass("Product Quantity", "Product " + Product +" Product Quantity is added", logger, driver);
					//------------------------------------
				driver.findElement(AddProductPOM.Save_()).click();
				driver.findElement(AddProductPOM.EditAll()).isDisplayed();
					//-----------------------------Reporter
					UtilityObject.fReportpass("Product add", "Product " + Product +"is successfully added in the current order ", logger, driver);
					//------------------------------------
		}
		catch(Exception e)
		{
			//-----------------------------Reporter
			UtilityObject.fReportfail("Error message", "Error : "+ e, logger, driver, Extndreport);
			//------------------------------------
		}	
	}
}
