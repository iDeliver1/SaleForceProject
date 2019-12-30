package com.SaleForce.webelements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.SaleForce.POM.Login;
import com.SaleForce.libraries.Utility_Libraries;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class SaleForce_Login {

	WebDriver driver;
	ExtentTest logger;
	ExtentReports Extndreport;
	Utility_Libraries Utility_Object = new Utility_Libraries();
	Login LoginPOM = new Login();
	
	public SaleForce_Login(ExtentTest logger,WebDriver driver, ExtentReports Extndreport) {
		
		this.driver = driver;
		this.logger = logger;
		this.Extndreport = Extndreport;
	}

	public void Login(String Username,String Password) throws Throwable
	{
		try
		{
			//Enter Username
			driver.findElement(LoginPOM.Username()).sendKeys(Username);
			//Enter Password
			driver.findElement(LoginPOM.Password()).sendKeys(Password);
				//-----------------------------Reporter
				Utility_Object.fReportpass("Username and Password", "Enter Username and Password details", logger, driver);
				//------------------------------------
			//Click Signin
			driver.findElement(LoginPOM.Signin()).click();
				try
				{
					//wait for the otp text editbox display
					driver.findElement(LoginPOM.OTPText()).isDisplayed();
					String Code = this.Email_Verification(Username);
					//Enter 5 digit number
					driver.findElement(LoginPOM.OTPText()).sendKeys(Code);
						//-----------------------------Reporter
						Utility_Object.fReportpass("OTP Code", "5 digit code have been successfully write", logger, driver);
						//------------------------------------
					//Click save
					driver.findElement(LoginPOM.Save()).click();
					WebDriverWait wait = new WebDriverWait(driver,6);
					wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='tabContainer']")));
					//wait for the main tab display
					driver.findElement(LoginPOM.Tab()).isDisplayed();
						//-----------------------------Reporter
						Utility_Object.fReportpass("Login Pass", "User is successfully logged in SaleForce", logger, driver);
						//------------------------------------
				}
				catch(Exception e)
				{
					try
					{
						//verify error message is display
						driver.findElement(LoginPOM.UserError()).isDisplayed();
					}
					catch(Exception f)
					{
						//-----------------------------Reporter
						Utility_Object.fReportfail("Login failed", "Error : Please check your username and password. If you still can't log in, contact your Salesforce administrator.", logger, driver, Extndreport);
						//------------------------------------
					}
				}	
		}
		catch(Exception G)
		{
			//-----------------------------Reporter
			Utility_Object.fReportfail("Login failed", "Error : " + G, logger, driver, Extndreport);
			//------------------------------------
		}
	}
	
	public String Email_Verification(String Username) throws Throwable
	{
		WebDriver objdriver1 = null;
		objdriver1 = Utility_Libraries.fgetBrowser("chrome", objdriver1);
		objdriver1.get("http://www.yopmail.com/en/");
			try
			{
				//Enter yop email
				objdriver1.findElement(LoginPOM.YopUser()).sendKeys(Username);
					//-----------------------------Reporter
					Utility_Object.fReportpass("Username", "Enter username : " + Username, logger, objdriver1);
					//------------------------------------
				//Click on submit button
				objdriver1.findElement(LoginPOM.YopSubmit()).click();
				objdriver1.switchTo().frame(2);
				//Get the code from the email0
				String Code = objdriver1.findElement(LoginPOM.Message()).getText();
				Code = Code.substring(290, 316).replaceAll("[^0-9]", "");
					//-----------------------------Reporter
					Utility_Object.fReportpass("Email_Verification", "Code has been successfully retrive " + Code, logger, objdriver1);
					//------------------------------------
				objdriver1.close();
				return Code;
			}
			catch(Exception e)
			{
				//-----------------------------Reporter
				Utility_Object.fReportfail("Email_Verification Failed", "Error : " + e, logger, objdriver1, Extndreport);
				//------------------------------------
			}
		return null;
	}
}
