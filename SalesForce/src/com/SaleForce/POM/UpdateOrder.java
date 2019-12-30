package com.SaleForce.POM;

import org.openqa.selenium.By;

import com.SaleForce.libraries.Utility_Libraries;

public class UpdateOrder {

	public By OrderTab() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","OrderTab","UpdateOrder");
	}
	
	public By PopUpWindow() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","PopUpWindow","UpdateOrder");
	}
	
	public By View() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","View","UpdateOrder");
	}
	
	public By Go() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","Go","UpdateOrder");
	}
	public By OrderList() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","OrderList","UpdateOrder");
	}
}
