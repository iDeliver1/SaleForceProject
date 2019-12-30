package com.SaleForce.POM;

import org.openqa.selenium.By;
import com.SaleForce.libraries.Utility_Libraries;

public class Login {
	
	public By Username() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","Username","Login");
	}
	
	public By Password() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","Password","Login");
	}

	public By Signin() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","Signin","Login");
	}
	
	public By OTPText() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","OTPText","Login");
	}
	
	public By Save() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","Save","Login");
	}

	public By Tab() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","Tab","Login");
	}
	
	public By UserError() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","UserError","Login");
	}
	
	public By YopUser() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","YopUser","Login");
	}

	public By YopSubmit() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","YopSubmit","Login");
	}
	
	public By Message() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","Message","Login");
	}
}
