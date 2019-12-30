package com.SaleForce.webelements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.SaleForce.POM.CreateLead;
import com.SaleForce.libraries.Utility_Libraries;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class SaleForce_Leads {

	
	WebDriver driver;
	ExtentTest logger;
	ExtentReports Extndreport;
	CreateLead CreateLeadPOM = new CreateLead();
	Utility_Libraries Utility_Object = new Utility_Libraries();
	
	
	public SaleForce_Leads(ExtentTest logger,WebDriver driver, ExtentReports Extndreport) {
		
		this.driver = driver;
		this.logger = logger;
		this.Extndreport = Extndreport;
	}

	public void Create_Leads(String FName, String LName, String CompanyName, String Status, String Campaign) throws Throwable
	{
		try
		{
			driver.findElement(CreateLeadPOM.LeadTab()).click();
				try
				{
					WebDriverWait wait = new WebDriverWait(driver,6);
					wait.until(ExpectedConditions.presenceOfElementLocated(CreateLeadPOM.Close()));
					//Close the popup window
					driver.findElement(CreateLeadPOM.Close()).click();
				}
				catch(Exception f) {}
			driver.findElement(CreateLeadPOM.New()).click();
				try
				{
					driver.findElement(CreateLeadPOM.FName()).isDisplayed();
					//-----------------------------Reporter
					Utility_Object.fReportpass("Create Lead", "Create Lead page is open successfully", logger, driver);
					//------------------------------------
				}
				catch(Exception e)
				{
					//-----------------------------Reporter
					Utility_Object.fReportfail("Create Lead", "Error :" + e +" Create Lead page is not open successfully", logger, driver, Extndreport);
					//------------------------------------
				}
			driver.findElement(CreateLeadPOM.FName()).sendKeys(FName);
			driver.findElement(CreateLeadPOM.LName()).sendKeys(LName);
			driver.findElement(CreateLeadPOM.LName()).sendKeys(Campaign);
			driver.findElement(CreateLeadPOM.CompanyName()).sendKeys(CompanyName);
			Select Status_ = new Select( driver.findElement(CreateLeadPOM.Status()));
			Status_.selectByValue(Status);
			driver.findElement(CreateLeadPOM.Save()).click();
			WebDriverWait wait = new WebDriverWait(driver,6);
			wait.until(ExpectedConditions.presenceOfElementLocated(CreateLeadPOM.HeadName()));
			String LeadName = driver.findElement(CreateLeadPOM.HeadName()).getText();
				if(LeadName.contains(FName.concat(" " + LName)))
				{
					//-----------------------------Reporter
					Utility_Object.fReportpass("Lead create", "Lead is successfully created", logger, driver);
					//------------------------------------
				}
				else
				{
					//-----------------------------Reporter
					Utility_Object.fReportfail("Lead create", "Lead is not successfully created", logger, driver, Extndreport);
					//------------------------------------
				}
		}
		catch(Exception E)
		{
			//-----------------------------Reporter
			Utility_Object.fReportfail("Error message", "Error : "+ E, logger, driver, Extndreport);
			//------------------------------------
		}
	}
}
