package com.SaleForce.POM;

import org.openqa.selenium.By;

import com.SaleForce.libraries.Utility_Libraries;

public class CreateLead {

	public By LeadTab() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","LeadTab","Leads");
	}
	
	public By New() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","New","Leads");
	}
	
	public By FName() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","FName","Leads");
	}
	
	public By Campaign() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","Campaign","Leads");
	}
	
	public By LName() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","LName","Leads");
	}
	
	public By CompanyName() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","CompanyName","Leads");
	}
	
	public By Close() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","Close","Leads");
	}
	
	public By Status() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","Status","Leads");
	}
	
	public By Save() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","Save","Leads");
	}
	
	public By HeadName() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","HeadName","Leads");
	}
}
