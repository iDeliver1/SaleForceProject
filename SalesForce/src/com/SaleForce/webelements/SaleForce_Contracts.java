package com.SaleForce.webelements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.SaleForce.POM.CreateContract;
import com.SaleForce.libraries.Utility_Libraries;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class SaleForce_Contracts {

	WebDriver driver;
	ExtentTest logger;
	ExtentReports Extndreport;
	CreateContract CreateContractPOM = new CreateContract();
	Utility_Libraries UtilityObject = new Utility_Libraries();
	
	public SaleForce_Contracts(ExtentTest logger,WebDriver driver,ExtentReports Extndreport)
	{
		this.driver = driver;
		this.logger = logger;
		this.Extndreport = Extndreport;
	}
	
	public String Create_Contracts(String CustomerName,String CustomerTitle,String PriceBook,String ContractMonth,String OwnerExpiration,String DescriptionArea) throws Throwable
	{
		try
		{
			String CurrentDate = UtilityObject.fGetCurrentDate();
			String AccountName = driver.findElement(CreateContractPOM.Account()).getText();
			WebDriverWait wait = new WebDriverWait(driver,30);
			driver.findElement(CreateContractPOM.ContractTab()).click();
				try
				{
					//Close the popup window
					driver.findElement(CreateContractPOM.PopUpWindow()).click();
				}
				catch(Exception f) {}
			wait.until(ExpectedConditions.visibilityOfElementLocated(CreateContractPOM.NewLink()));
		 	driver.findElement(CreateContractPOM.NewLink()).click();
			 	try
			 	{
			 		driver.findElement(CreateContractPOM.CustomerName()).isDisplayed();
					//-----------------------------Reporter
			 		UtilityObject.fReportpass("Create Contract", "Create Contract page is open successfully", logger, driver);
					//------------------------------------
			 	}
			 	catch(Exception e)
			 	{
			 		//-----------------------------Reporter
			 		UtilityObject.fReportfail("Create Contract", "Error :" + e +" Create Contract page is not open successfully", logger, driver, Extndreport);
					//------------------------------------
			 	}
	        driver.findElement(CreateContractPOM.CustomerName()).sendKeys(CustomerName);
	        driver.findElement(CreateContractPOM.CustomerTitle()).sendKeys(CustomerTitle);
			driver.findElement(CreateContractPOM.CustomerDate()).sendKeys(CurrentDate);
			Select priceBook = new Select(driver.findElement(CreateContractPOM.PriceBook()));
			priceBook.selectByVisibleText(PriceBook);
			driver.findElement(CreateContractPOM.ContractDate()).sendKeys(CurrentDate);
			driver.findElement(CreateContractPOM.ContractMonth()).sendKeys(ContractMonth);
			Select OwnerExpirationNotice = new Select(driver.findElement(CreateContractPOM.OwnerExpirationNotice()));
			OwnerExpirationNotice.selectByVisibleText(OwnerExpiration);
			driver.findElement(CreateContractPOM.CompanySigned()).sendKeys(AccountName);
			driver.findElement(CreateContractPOM.DescriptionArea()).sendKeys(DescriptionArea);
			driver.findElement(CreateContractPOM.SaveButton()).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(CreateContractPOM.PageDescription()));
			String ContractNumber = driver.findElement(CreateContractPOM.PageDescription()).getText();
				//----------------------Verification Contract is Created-------------------------/
				if(ContractNumber.matches("[0-9]{8}"))
				{
					    //-----------------------------Reporter------------------------------------------/
						UtilityObject.fReportpass("Contract create", "Contract is successfully created Contract number = " + ContractNumber, logger, driver);
						//-------------------------------------------------------------------------------
						return driver.findElement(CreateContractPOM.PageDescription()).getText();
				}
				else
				{
						//-----------------------------Reporter------------------------------------------/
						UtilityObject.fReportfail("Contract create", "Contract is not successfully created Contract number = " + ContractNumber, logger, driver, Extndreport);
						//-------------------------------------------------------------------------------/
				}
		}
		catch(Exception e)
		{
			//-----------------------------Reporter
			UtilityObject.fReportfail("Error", "Error"+ e, logger, driver, Extndreport);
			//------------------------------------
		}
	    return null;
	}
}
