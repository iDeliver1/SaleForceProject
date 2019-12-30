package com.SaleForce.webelements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.SaleForce.POM.CreateCampaign;
import com.SaleForce.libraries.Generic_Libraries;
import com.SaleForce.libraries.Utility_Libraries;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class SaleForce_Campaign {

	WebDriver driver;
	ExtentTest logger;
	ExtentReports Extndreport;
	CreateCampaign CreateCampaignPOM = new CreateCampaign();
	Utility_Libraries Utility_Object = new Utility_Libraries();
	Generic_Libraries GenericObject = new Generic_Libraries(logger, driver, Extndreport);
	
	public SaleForce_Campaign(ExtentTest logger,WebDriver driver, ExtentReports Extndreport) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		this.logger = logger;
		this.Extndreport = Extndreport;
	}
	
	public void Create_Campaign(String Name, String Expected_rev, String Budget_cost, String Condition, String Status_) throws Throwable
	{
		
		String CurrentDate = Utility_Object.fGetCurrentDate();
		String AddDate = Utility_Object.fAddDate(CurrentDate, 10, Condition);
		WebDriverWait wait = new WebDriverWait(driver,10);
		GenericObject.Click(driver.findElement(CreateCampaignPOM.Tab()));
		GenericObject.Click(driver.findElement(CreateCampaignPOM.Close()));
		GenericObject.Click(driver.findElement(CreateCampaignPOM.New()));
		GenericObject.IsDisplay(driver.findElement(CreateCampaignPOM.Name()),"Create Campaign","Create Campaign page is open successfully");
		GenericObject.SendKeys(driver.findElement(CreateCampaignPOM.Name()), Name);
		GenericObject.Select(driver.findElement(CreateCampaignPOM.Status()), "Value", Status_);
		GenericObject.SendKeys(driver.findElement(CreateCampaignPOM.S_Date()), CurrentDate);
		GenericObject.SendKeys(driver.findElement(CreateCampaignPOM.E_Date()), AddDate);
		GenericObject.SendKeys(driver.findElement(CreateCampaignPOM.Expected_rev()), Expected_rev);
		GenericObject.SendKeys(driver.findElement(CreateCampaignPOM.Budget_cost()), Budget_cost);
		GenericObject.Click(driver.findElement(CreateCampaignPOM.Save()));
		wait.until(ExpectedConditions.presenceOfElementLocated(CreateCampaignPOM.HeadName()));
		boolean Rc = GenericObject.GetText(driver.findElement(CreateCampaignPOM.HeadName()), "contains", Name);
			if(Rc == true)
			{
				//-----------------------------Reporter
				Utility_Object.fReportpass("Campaign create", "Campaign is successfully created", logger, driver);
				//------------------------------------
			}
			else
			{
				//-----------------------------Reporter
				Utility_Object.fReportfail("Campaign create", "Campaign is not successfully created", logger, driver, Extndreport);
				//------------------------------------
			}
	}
}
