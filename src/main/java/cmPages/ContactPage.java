package cmPages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import frameworkPackage.FrameworkBase;

public class ContactPage extends FrameworkBase{
	
	public ContactPage(FrameworkBase fBase){
		this.fBase = fBase;
		PageFactory.initElements(fBase.driver, this);
	}

	
	// Add primary contact

	public void accountCreation_PrimaryContact() throws Exception
	{

		fBase.driver.findElement(By.xpath("//img[@id='addcontact-toolEl']")).click();
		Thread.sleep(1000);

		fBase.driver.switchTo().defaultContent();
		fBase.driver.switchTo().frame("mainFrame");

		fBase.driver.findElement(By.xpath("//td[@id='cmb-comboConTypeId-inputCell']/following-sibling::td/div")).click();
		fBase.driver.findElement(By.xpath("//li[text()='Account Primary']")).click();

		fBase.driver.findElement(By.id("firstName")).sendKeys("First name");

		fBase.driver.findElement(By.id("lastName")).sendKeys("Last name");

		fBase.driver.findElement(By.id("officePhone")).sendKeys("2345456756");

		fBase.driver.findElement(By.xpath("//input[@id='_eventId_saveContact']")).click();

		try
		{
			fBase.driver.findElement(By.xpath("//span[text()='Yes']/following-sibling::span")).click();
			Thread.sleep(1000);
		}
		catch (Exception e)
		{
			System.err.println("Exception occured in adding primary contact");
		}

		try
		{
			if (fBase.driver.findElement(By.xpath("//input[@value='success']/following-sibling::span")).isDisplayed())
			{
				fBase.extentReportsStep("Service Add Contact", "PASS", "YES");
			}
			else
			{
				fBase.extentReportsStep("Service Add Contact", "FAIL", "YES");
			}
		}
		catch (Exception e)
		{
			System.err.println("Service Account AddContact failed");
		}

		fBase.driver.findElement(By.xpath("//input[@value='Back']")).click();
		Thread.sleep(1000);

	}

	//////////////////////////////////////////////////////////////////////



	//Site technical contact creation

	public void address_SiteTechnicalContact() throws Exception
	{

		fBase.driver.switchTo().defaultContent();
		fBase.driver.switchTo().frame("mainFrame");
		fBase.driver.switchTo().frame("AddressFrame");

		fBase.driver.findElement(By.xpath("//img[@id='addcontact-toolEl']")).click();
		Thread.sleep(2000);
		
		fBase.driver.switchTo().defaultContent();
		fBase.driver.switchTo().frame("mainFrame");

		fBase.driver.findElement(By.xpath("//td[@id='cmb-comboConTypeId-inputCell']/following-sibling::td/div")).click();
		fBase.driver.findElement(By.xpath("//li[text()='Site Technical Contact']")).click();

		fBase.driver.findElement(By.id("firstName")).sendKeys("Site Technical Contact first");

		fBase.driver.findElement(By.id("lastName")).sendKeys("Site Technical Contact last");

		fBase.driver.findElement(By.id("officePhone")).sendKeys("2345456756");

		fBase.driver.findElement(By.xpath("//input[@id='_eventId_saveContact']")).click();

		try
		{
			if (fBase.driver.findElement(By.xpath("//span[text()='Yes']/following-sibling::span")).isDisplayed())
			{
				fBase.driver.findElement(By.xpath("//span[text()='Yes']/following-sibling::span")).click();			
			}
		}
		catch (Exception e)
		{
			System.err.println("Popup not present");
		}
		
		try
		{
			if (fBase.driver.findElement(By.xpath("//input[@value='success']/following-sibling::span")).isDisplayed())
			{
				//System.out.println(fBase.driver.findElement(By.xpath("//input[@value='success']/following-sibling::span")).getText());
				fBase.extentReportsStep("Adress Site Technical Contact", "PASS", "YES");
			}
			else
			{
				fBase.extentReportsStep("Adress Site Technical Contact", "FAIL", "YES");
			}
		}
		catch (Exception e)
		{
			System.err.println("Success msg not present");
		}

		fBase.driver.findElement(By.xpath("//input[@value='Back']")).click();
		Thread.sleep(1000);

	}

	
	
	
}

