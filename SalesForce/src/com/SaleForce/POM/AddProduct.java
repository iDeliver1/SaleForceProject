package com.SaleForce.POM;

import org.openqa.selenium.By;

import com.SaleForce.libraries.Utility_Libraries;

public class AddProduct {

	public By AddProduct_() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","AddProduct_","AddProduct");
	}
		
	public By ProductName() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","ProductName","AddProduct");
	}
	
	public By CheckBox() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","CheckBox","AddProduct");
	}	
	
	public By ProSelect() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","ProSelect","AddProduct");
	}
	
	public By Quantity() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","Quantity","AddProduct");
	}
	
	public By Save_() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","Save_","AddProduct");
	}
	
	public By EditAll() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","EditAll","AddProduct");
	}
}
