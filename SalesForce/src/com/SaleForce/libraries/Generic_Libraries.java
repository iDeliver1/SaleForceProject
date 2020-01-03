package com.SaleForce.libraries;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.SaleForce.base.Driver_initializing;

public class Generic_Libraries extends Driver_initializing {

	Utility_Libraries UtilityObject = new Utility_Libraries();
	
	public void Click(String Keyname, String Filename) throws Throwable
	{
		WebElement Element = driver.findElement(Utility_Libraries.fGetPOMvalue("xpath", Keyname, Filename));
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
	
	public boolean GetText(String Keyname,String Filename, String CompareType, String Val) throws Throwable
	{
		try 
		{
			WebElement Element = driver.findElement(Utility_Libraries.fGetPOMvalue("xpath", Keyname, Filename));
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
	
	public void IsDisplay(String Keyname, String Filename, String Stepname, String StepDetails) throws Throwable
	{
		WebElement Element = driver.findElement(Utility_Libraries.fGetPOMvalue("xpath", Keyname, Filename));
		try {
			if(Element.isDisplayed())
			{
				UtilityObject.fReportpass(Stepname, StepDetails, logger, driver);
			}
		} catch (Throwable e) {
			System.out.println(e);
			UtilityObject.fReportfail(Stepname, "Error : "+e, logger, driver, Extndreport);
		}
	}
	
	public void SendKeys(String Keyname, String Filename, String Val) throws Throwable
	{
		WebElement Element = driver.findElement(Utility_Libraries.fGetPOMvalue("xpath", Keyname, Filename));
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
	
	public void Select(String Keyname ,String Filename, String SelectType,String Val) throws Throwable
	{
		WebElement Element = driver.findElement(Utility_Libraries.fGetPOMvalue("xpath", Keyname, Filename));
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
