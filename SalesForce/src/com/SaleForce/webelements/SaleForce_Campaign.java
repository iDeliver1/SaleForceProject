package com.SaleForce.webelements;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.SaleForce.libraries.Generic_Libraries;
import com.SaleForce.libraries.Utility_Libraries;

public class SaleForce_Campaign extends Generic_Libraries {

	Utility_Libraries Utility_Object = new Utility_Libraries();
	
	public void Create_Campaign(String Name, String Expected_rev, String Budget_cost, String Condition, String Status_) throws Throwable
	{
		
		String CurrentDate = Utility_Object.fGetCurrentDate();
		String AddDate = Utility_Object.fAddDate(CurrentDate, 10, Condition);
		WebDriverWait wait = new WebDriverWait(driver,10);
		Click("LeadTab","Leads");
		Click("Close","Campaign");
		Click("New","Campaign");
		IsDisplay("Name","Campaign","Create Campaign","Create Campaign page is open successfully");
		SendKeys("Name","Campaign", Name);
		Select("Status","Campaign", "Value", Status_);
		SendKeys("S_Date","Campaign", CurrentDate);
		SendKeys("E_Date","Campaign", AddDate);
		SendKeys("Expected_rev","Campaign", Expected_rev);
		SendKeys("Budget_cost","Campaign", Budget_cost);
		Click("Save","Campaign");
		wait.until(ExpectedConditions.presenceOfElementLocated(Utility_Libraries.fGetPOMvalue("xpath","HeadName","Campaign")));
		boolean Rc = GetText("HeadName","Campaign", "contains", Name);
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
