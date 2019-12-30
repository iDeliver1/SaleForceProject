package com.SaleForce.POM;

import org.openqa.selenium.By;

import com.SaleForce.libraries.Utility_Libraries;

public class CreateContract {

	public By ContractTab() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","ContractTab","Contracts");
	}	
	
	public By NewLink() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","New_Link","Contracts");
	}
	
	public By Account() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","Account","Contracts");
	}
	
	public By CustomerName() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","Customer_Name","Contracts");
	}
	
	public By CustomerSigned() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","Customer_Signed","Contracts");
	}
	
	public By CustomerTitle() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","Customer_Title","Contracts");
	}
	
	public By CustomerDate() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","Customer_Date","Contracts");
	}
	
	public By PriceBook() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","Price_Book","Contracts");
	}
	
	public By ContractDate() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","Contract_Date","Contracts");
	}
	
	public By ContractMonth() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","Contract_Month","Contracts");
	}
	
	public By OwnerExpirationNotice() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","Owner_Expiration_Notice","Contracts");
	}
	
	public By CompanySigned() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","Company_Signed","Contracts");
	}
	
	public By SaveButton() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","Save_Button","Contracts");
	}
	
	public By PopUpWindow() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","PopUpWindow","Contracts");
	}
	
	public By DescriptionArea() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","Description_Area","Contracts");
	}
	
	public By PageDescription() throws Throwable
	{
		return Utility_Libraries.fGetPOMvalue("xpath","PageDescription","Contracts");
	}
	
}
