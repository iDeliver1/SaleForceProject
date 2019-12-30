package com.SaleForce.POM;

import org.openqa.selenium.By;

import com.SaleForce.libraries.Utility_Libraries;

public class CreateOrder {
	
	public By OrderTab() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","OrderTab","Orders");
	}
	
	public By AccountName() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","AccountName","Orders");
	}
	
	public By OrderStartDate() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","OrderStartDate","Orders");
	}
	
	public By ContractNumber() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","ContractNumber","Orders");
	}
	
	public By OrderDescription() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","OrderDescription","Orders");
	}
	
	public By OrderSaveButton() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","OrderSaveButton","Orders");
	}
	
	public By OrderNumber() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","OrderNumber","Orders");
	}
	
	public By PopUpWindow() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","PopUpWindow","Orders");
	}
	
	public By NewLink() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","NewLink","Orders");
	}
		
}
