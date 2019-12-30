package com.SaleForce.webelements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.SaleForce.POM.CreateProduct;
import com.SaleForce.libraries.Utility_Libraries;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class SaleForce_Product {

	WebDriver driver;
	ExtentTest logger;
	ExtentReports Extndreport;
	Utility_Libraries Utility_Object = new Utility_Libraries();
	CreateProduct CreateProductPOM = new CreateProduct();
	
	public SaleForce_Product(ExtentTest logger,WebDriver driver, ExtentReports Extndreport) {

		this.driver = driver;
		this.logger = logger;
		this.Extndreport = Extndreport;
	}

	public String Create_Product(String Product_Name,String Price) throws Throwable
	{
		try
		{
			driver.findElement(CreateProductPOM.Tab()).click();
			WebDriverWait wait = new WebDriverWait(driver,10);
				try
				{
					wait.until(ExpectedConditions.visibilityOfElementLocated(CreateProductPOM.PopUpWindow()));
					//Close the popup window
					driver.findElement(CreateProductPOM.PopUpWindow()).click();
				}
				catch(Exception f) {}
			driver.findElement(CreateProductPOM.New()).click();
			driver.findElement(CreateProductPOM.Product_Name()).sendKeys(Product_Name);
			driver.findElement(CreateProductPOM.Product_Code()).sendKeys(Utility_Libraries.fTimestamp());
			driver.findElement(CreateProductPOM.ActiveCheck()).click();
			driver.findElement(CreateProductPOM.Save()).click();
			String Product = driver.findElement(CreateProductPOM.Verify()).getText();
				if(Product.contains(Product_Name))
				{
					//-----------------------------Reporter
					Utility_Object.fReportpass("Create Product", "Product has been successfully created " + Product + " name.", logger, driver);
					//------------------------------------
				}
				else
				{
					//-----------------------------Reporter
					Utility_Object.fReportfail("Create Product", "Product has been not successfully created " + Product + " name.", logger, driver, Extndreport);
					//------------------------------------
				}
			driver.findElement(CreateProductPOM.Add()).click();
			driver.findElement(CreateProductPOM.StandedPrice()).sendKeys(Price);
			driver.findElement(CreateProductPOM.PriceSave()).click();
				try
				{
					driver.findElement(CreateProductPOM.VerifyPrice()).isDisplayed();
						//-----------------------------Reporter
						Utility_Object.fReportpass("Price verify", "Price"+ Price +" is added in the product successfully", logger, driver);
						//------------------------------------
				}
				catch(Exception e)
				{
						//-----------------------------Reporter
						Utility_Object.fReportfail("Price verify", "Price"+ Price +" is not added in the product", logger, driver, Extndreport);
						//------------------------------------
				}
			driver.findElement(CreateProductPOM.PriceBookAdd()).click();
			driver.findElement(CreateProductPOM.CheckBox()).click();
			driver.findElement(CreateProductPOM.Select_()).click();
			driver.findElement(CreateProductPOM.PriceCheckBox()).click();
			driver.findElement(CreateProductPOM.ListPriceSave()).click();
			String Product_ = driver.findElement(CreateProductPOM.Verify()).getText();
				if(Product_.contains(Product_Name))
				{
					//-----------------------------Reporter
					Utility_Object.fReportpass("Create price", "Price Books and Standard Price is successfully created for the " + Product + " name.", logger, driver);
					//------------------------------------
					return Product_;
				}
				else
				{
					//-----------------------------Reporter
					Utility_Object.fReportfail("Create price", "Price Books and Standard Price is not created for the " + Product + " name.", logger, driver, Extndreport);
					//------------------------------------
				}		
		}
		catch(Exception e)
		{
			//-----------------------------Reporter
			Utility_Object.fReportfail("Error", "Error :" + e , logger, driver, Extndreport);
			//------------------------------------
		}
	return null;
	}
}
