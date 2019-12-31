package com.SaleForce.POM;

import org.openqa.selenium.By;
import com.SaleForce.libraries.Utility_Libraries;

public class DeleteOrder {
	
	public By OrderTab() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","OrderTab","DeleteOrder");
	}
	
	public By PopUpWindow() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","PopUpWindow","DeleteOrder");
	}
	
	public By Order_List() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","Order_List","DeleteOrder");
	}
	
	public By Delete() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","Delete","DeleteOrder");
	} 

	public By Go() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","Go","DeleteOrder");
	}
}
