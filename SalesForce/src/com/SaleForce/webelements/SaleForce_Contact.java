package com.SaleForce.webelements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class SaleForce_Contact {

	WebDriver driver;
	ExtentTest logger;
	ExtentReports Extndreport;
	
	public SaleForce_Contact(ExtentTest logger,WebDriver driver, ExtentReports Extndreport) {
		
		this.driver = driver;
		this.logger = logger;
		this.Extndreport = Extndreport;
	}

	public void Create_Contact()
	{
		driver.findElement(By.xpath(" //a[contains(text(),'Contacts')]")).click();
		driver.findElement(By.xpath("//input[@name='new']")).click();
		driver.findElement(By.xpath("//input[@id='name_firstcon2']")).sendKeys("RK");
		driver.findElement(By.xpath("//input[@id='name_firstcon2']")).sendKeys("Dewangan");
		driver.findElement(By.xpath("//input[@id='con4']")).sendKeys("Amaon");
		driver.findElement(By.xpath("//input[@id='con7']")).sendKeys("01/01/1990");
		driver.findElement(By.xpath("//input[@id='con10']")).sendKeys("9112233440");
		driver.findElement(By.xpath("//input[@id='con15']")).sendKeys("amazon@gamil.com");
		driver.findElement(By.xpath("//textarea[@id='con19street']")).sendKeys("Nature City, Uslapur");
		driver.findElement(By.xpath("//input[@id='con18city']")).sendKeys("Bilaspur");
		driver.findElement(By.xpath("//input[@id='con18country']")).sendKeys("India");
		driver.findElement(By.xpath("//input[@id='00N4T000005YPSt']")).sendKeys("English");
		driver.findElement(By.xpath("//td[@id='bottomButtonRow']//input[@name='save']")).click();
	}
}
