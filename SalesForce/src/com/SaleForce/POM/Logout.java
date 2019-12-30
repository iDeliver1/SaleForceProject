package com.SaleForce.POM;

import org.openqa.selenium.By;

import com.SaleForce.libraries.Utility_Libraries;

public class Logout {

	public By Account() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","Account","Logout");
	}
	
	public By Logout_() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","Logout","Logout");
	}
	
	public By LogoutVerify() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","Logout_Verify","Logout");
	}
}
