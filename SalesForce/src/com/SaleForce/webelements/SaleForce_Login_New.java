package com.SaleForce.webelements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.SaleForce.libraries.Generic_Libraries;
import com.SaleForce.libraries.Utility_Libraries;

 public class SaleForce_Login_New extends Generic_Libraries {

   Utility_Libraries Utility_Object = new Utility_Libraries();
		
		public void Login(String Username,String Password) throws Throwable
			{
				try {
					SendKeys("Username","Login", Username);
					SendKeys("Password","Login", Password);
						Utility_Object.fReportpass("Username and Password", "Enter Username and Password details", logger, driver);
					Click("Signin","Login");
						try {
								IsDisplay("OTPText","Login", "OTP Text", "OTP Value is showing");
								String Code = this.Email_Verification(Username);
								SendKeys("Save","Login",Code);
									Utility_Object.fReportpass("OTP Code", "5 digit code have been successfully write", logger, driver);
								Click("Save","Login");
								WebDriverWait wait = new WebDriverWait(driver,6);
								wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='tabContainer']")));
								IsDisplay("Tab","Login", "Login Pass", "User is successfully logged in SaleForce");
						} catch(Exception e) {
								try {
									IsDisplay("UserError","Login", "Error", "Error Message");
								} catch(Exception f) {							
									Utility_Object.fReportfail("Login failed", "Error : Please check your username and password. If you still can't log in, contact your Salesforce administrator.", logger, driver, Extndreport);
								}
						}	
				} catch(Exception G) {
					Utility_Object.fReportfail("Login failed", "Error : " + G, logger, driver, Extndreport);
				}
			}
		
		public String Email_Verification(String Username) throws Throwable
			{
				WebDriver objdriver1 = null;
				objdriver1 = Utility_Libraries.fgetBrowser("chrome", objdriver1);
				objdriver1.get("http://www.yopmail.com/en/");
					try {
						SendKeys("YopUser","Login",Username);
							Utility_Object.fReportpass("Username", "Enter username : " + Username, logger, objdriver1);
						Click("YopSubmit","Login");
						objdriver1.switchTo().frame(2);
						String Code = objdriver1.findElement(Utility_Libraries.fGetPOMvalue("xpath","Message","Login")).getText();
						Code = Code.substring(290, 316).replaceAll("[^0-9]", "");
							Utility_Object.fReportpass("Email_Verification", "Code has been successfully retrive " + Code, logger, objdriver1);
						objdriver1.close();
						return Code;
					} catch(Exception e) {
						Utility_Object.fReportfail("Email_Verification Failed", "Error : " + e, logger, objdriver1, Extndreport);
					}
				return null;
			}
	}
