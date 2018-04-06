package cmPages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import frameworkPackage.TestBase;

public class ContactPage extends TestBase{
	
	public ContactPage(TestBase testBase){
		this.testBase = testBase;
		PageFactory.initElements(testBase.driver, this);
	}

	
	// Add primary contact

	public void accountCreation_PrimaryContact() throws Exception
	{

		testBase.driver.findElement(By.xpath("//img[@id='addcontact-toolEl']")).click();
		Thread.sleep(1000);

		testBase.driver.switchTo().defaultContent();
		testBase.driver.switchTo().frame("mainFrame");

		testBase.driver.findElement(By.xpath("//td[@id='cmb-comboConTypeId-inputCell']/following-sibling::td/div")).click();
		testBase.driver.findElement(By.xpath("//li[text()='Account Primary']")).click();

		testBase.driver.findElement(By.id("firstName")).sendKeys("First name");

		testBase.driver.findElement(By.id("lastName")).sendKeys("Last name");

		testBase.driver.findElement(By.id("officePhone")).sendKeys("2345456756");

		testBase.driver.findElement(By.xpath("//input[@id='_eventId_saveContact']")).click();

		try
		{
			testBase.driver.findElement(By.xpath("//span[text()='Yes']/following-sibling::span")).click();
			Thread.sleep(1000);
		}
		catch (Exception e)
		{
			System.err.println("Exception occured in adding primary contact");
		}

		try
		{
			if (testBase.driver.findElement(By.xpath("//input[@value='success']/following-sibling::span")).isDisplayed())
			{
				testBase.extentReportsStep("Service Add Contact", "PASS", "YES");
			}
			else
			{
				testBase.extentReportsStep("Service Add Contact", "FAIL", "YES");
			}
		}
		catch (Exception e)
		{
			System.err.println("Service Account AddContact failed");
		}

		testBase.driver.findElement(By.xpath("//input[@value='Back']")).click();
		Thread.sleep(1000);

	}

	//////////////////////////////////////////////////////////////////////



	//Site technical contact creation

	public void address_SiteTechnicalContact() throws Exception
	{

		testBase.driver.switchTo().defaultContent();
		testBase.driver.switchTo().frame("mainFrame");
		testBase.driver.switchTo().frame("AddressFrame");

		testBase.driver.findElement(By.xpath("//img[@id='addcontact-toolEl']")).click();
		Thread.sleep(2000);
		
		testBase.driver.switchTo().defaultContent();
		testBase.driver.switchTo().frame("mainFrame");

		testBase.driver.findElement(By.xpath("//td[@id='cmb-comboConTypeId-inputCell']/following-sibling::td/div")).click();
		testBase.driver.findElement(By.xpath("//li[text()='Site Technical Contact']")).click();

		testBase.driver.findElement(By.id("firstName")).sendKeys("Site Technical Contact first");

		testBase.driver.findElement(By.id("lastName")).sendKeys("Site Technical Contact last");

		testBase.driver.findElement(By.id("officePhone")).sendKeys("2345456756");

		testBase.driver.findElement(By.xpath("//input[@id='_eventId_saveContact']")).click();

		try
		{
			if (testBase.driver.findElement(By.xpath("//span[text()='Yes']/following-sibling::span")).isDisplayed())
			{
				testBase.driver.findElement(By.xpath("//span[text()='Yes']/following-sibling::span")).click();			
			}
		}
		catch (Exception e)
		{
			System.err.println("Popup not present");
		}
		
		try
		{
			if (testBase.driver.findElement(By.xpath("//input[@value='success']/following-sibling::span")).isDisplayed())
			{
				//System.out.println(testBase.driver.findElement(By.xpath("//input[@value='success']/following-sibling::span")).getText());
				testBase.extentReportsStep("Adress Site Technical Contact", "PASS", "YES");
			}
			else
			{
				testBase.extentReportsStep("Adress Site Technical Contact", "FAIL", "YES");
			}
		}
		catch (Exception e)
		{
			System.err.println("Success msg not present");
		}

		testBase.driver.findElement(By.xpath("//input[@value='Back']")).click();
		Thread.sleep(1000);

	}

	
	
	
}

