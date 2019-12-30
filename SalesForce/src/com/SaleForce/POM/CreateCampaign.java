package com.SaleForce.POM;

import org.openqa.selenium.By;

import com.SaleForce.libraries.Utility_Libraries;

public class CreateCampaign {

	public By Tab() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","Tab","Campaign");
	}
	
	public By New() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","New","Campaign");
	}
	
	public By Name() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","Name","Campaign");
	}
	
	public By Status() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","Status","Campaign");
	}
	
	public By S_Date() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","S_Date","Campaign");
	}
	
	public By E_Date() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","E_Date","Campaign");
	}
	
	public By Expected_rev() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","Expected_rev","Campaign");
	}
	
	public By Budget_cost() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","Budget_cost","Campaign");
	}
	
	public By Save() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","Save","Campaign");
	}
	
	public By HeadName() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","HeadName","Campaign");
	}
	
	public By Close() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","Close","Campaign");
	}
}
