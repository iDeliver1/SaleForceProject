package com.SaleForce.webelements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.SaleForce.POM.Logout;
import com.SaleForce.libraries.Utility_Libraries;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class SaleForce_Logout {

	WebDriver driver;
	ExtentTest logger;
	ExtentReports Extndreport;
	Utility_Libraries Utility_Object = new Utility_Libraries();
	Logout LogoutPOM = new Logout();
	
	public SaleForce_Logout(ExtentTest logger,WebDriver driver, ExtentReports Extndreport) {

		this.driver = driver;
		this.logger = logger;
		this.Extndreport = Extndreport;
	}

	public void Logout() throws Throwable
	{
		try
		{
			driver.findElement(LogoutPOM.Account()).click();
			driver.findElement(LogoutPOM.Logout_()).click();
				try
				{
					WebDriverWait wait = new WebDriverWait(driver,10);
					wait.until(ExpectedConditions.presenceOfElementLocated(LogoutPOM.LogoutVerify()));
					driver.findElement(LogoutPOM.LogoutVerify()).isDisplayed();
						//-----------------------------Reporter
						Utility_Object.fReportpass("Logout", "User is successfully logout", logger, driver);
						//------------------------------------
				}
				catch(Exception e)
				{
					//-----------------------------Reporter
					Utility_Object.fReportfail("Logout", "Error :" + e +" User is not successfully logout", logger, driver, Extndreport);
					//------------------------------------
				}
		}
		catch(Exception e)
		{
			//-----------------------------Reporter
			Utility_Object.fReportfail("Logout", "Error :" + e, logger, driver, Extndreport);
			//------------------------------------
		}
	}
}
