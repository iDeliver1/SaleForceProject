package com.SaleForce.POM;

import org.openqa.selenium.By;

import com.SaleForce.libraries.Utility_Libraries;


public class CreateAccounts {

	public By PopUpWindow() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","PopUpWindow","Account");
	}
	
	public By AccountTab() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","AccoutTab","Account");
	}	
	
	public By New() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","New","Account");
	}
	
	public By AccountName() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","AccountName","Account");
	}
	
	public By AccountNumber() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","AccountNumber","Account");
	}
	
	public By Account_Description() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","AccountDescription","Account");
	}
	
	public By Account_Save() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","Account_Save","Account");
	}
	
	public By AccountVerification() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","AccountVerification","Account");
	}

}
