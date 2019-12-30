package com.SaleForce.libraries;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class Generic_Libraries {
	
	WebDriver driver;
	ExtentTest logger;
	ExtentReports Extndreport;
	Utility_Libraries UtilityObject = new Utility_Libraries();
	Utility_Libraries Utility_Object = new Utility_Libraries();
	
	public Generic_Libraries(ExtentTest logger,WebDriver driver,ExtentReports Extndreport) {

		this.driver = driver;
		this.logger = logger;
		this.Extndreport = Extndreport;
	}
	
	public void Click(WebElement Element) throws Throwable
	{
		
		try {
			if(Element.isEnabled())
			{
				Element.click();
			}
		} catch (Throwable e) {
			System.out.println(e);
			UtilityObject.fReportfail("Error", "Error : "+e, logger, driver, Extndreport);
		}
	}
	
	public boolean GetText(WebElement Element, String CompareType,String Val) throws Throwable
	{
		try 
		{
			Element.isDisplayed();
				switch (CompareType.toLowerCase())
				{
				  case "equalsIgnoreCase" :
					  return Element.getText().equalsIgnoreCase(Val);
					  
				  case "contains" :
					  return Element.getText().contains(Val);
				}
		} 
		catch (Throwable e) 
		{
			System.out.println(e);
			UtilityObject.fReportfail("Error", "Error : "+e, logger, driver, Extndreport);
		}
		return false;

	}
	
	public void IsDisplay(WebElement Element,String Stepname, String StepDetails) throws Throwable
	{
		try {
			if(Element.isDisplayed())
			{
				Utility_Object.fReportpass(Stepname, StepDetails, logger, driver);
			}
		} catch (Throwable e) {
			System.out.println(e);
			UtilityObject.fReportfail(Stepname, "Error : "+e, logger, driver, Extndreport);
		}
	}
	
	public void SendKeys(WebElement Element,String Val) throws Throwable
	{
		try {
			if(Element.isDisplayed())
			{
				Element.sendKeys(Val);
			}
		} catch (Throwable e) {
			System.out.println(e);
			UtilityObject.fReportfail("Error", "Error : "+e, logger, driver, Extndreport);
		}
	}
	
	public void Select(WebElement Element,String SelectType,String Val) throws Throwable
	{
		try {
			Select select_ = new Select(Element);
			switch(SelectType.toLowerCase())
			{	
			case "index":
				select_.selectByIndex(Integer.parseInt(Val));
			case "value":
				select_.selectByValue(Val);
			case "visible":
				select_.selectByVisibleText(Val);
			}
		} catch (Throwable e) {
			System.out.println(e);
			UtilityObject.fReportfail("Error", "Error : "+e, logger, driver, Extndreport);
		}
	}
	
}
