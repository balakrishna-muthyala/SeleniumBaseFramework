package cmPages;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;

import frameworkPackage.TestBase;
import frameworkPackage.UtilityClass;

public class ContactPage extends TestBase{

	public ContactPage(WebDriver driver, HashMap<String, String> testdataHashMap, ExtentTest eTest) {
		this.driver = driver;
		this.testdataHashMap = testdataHashMap;
		this.eTest = eTest;
		PageFactory.initElements(driver, this);
	}
	

	// Add primary contact

	public void accountCreation_PrimaryContact() throws Exception
	{

		driver.findElement(By.xpath("//img[@id='addcontact-toolEl']")).click();
		Thread.sleep(1000);

		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");

		driver.findElement(By.xpath("//td[@id='cmb-comboConTypeId-inputCell']/following-sibling::td/div")).click();
		driver.findElement(By.xpath("//li[text()='Account Primary']")).click();

		driver.findElement(By.id("firstName")).sendKeys("First name");

		driver.findElement(By.id("lastName")).sendKeys("Last name");

		driver.findElement(By.id("officePhone")).sendKeys("2345456756");

		driver.findElement(By.xpath("//input[@id='_eventId_saveContact']")).click();

		try
		{
			driver.findElement(By.xpath("//span[text()='Yes']/following-sibling::span")).click();
			Thread.sleep(1000);
		}
		catch (Exception e)
		{
			System.err.println("Exception occured in adding primary contact");
		}

		try
		{
			if (driver.findElement(By.xpath("//input[@value='success']/following-sibling::span")).isDisplayed())
			{
				new UtilityClass(driver, testdataHashMap, eTest).extentReportsStep("Service Add Contact", "PASS", "YES");
			}
			else
			{
				new UtilityClass(driver, testdataHashMap, eTest).extentReportsStep("Service Add Contact", "FAIL", "YES");
			}
		}
		catch (Exception e)
		{
			System.err.println("Service Account AddContact failed");
		}

		driver.findElement(By.xpath("//input[@value='Back']")).click();
		Thread.sleep(1000);

	}

	//////////////////////////////////////////////////////////////////////



	//Site technical contact creation

	public void address_SiteTechnicalContact() throws Exception
	{

		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.switchTo().frame("AddressFrame");

		driver.findElement(By.xpath("//img[@id='addcontact-toolEl']")).click();
		Thread.sleep(2000);
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");

		driver.findElement(By.xpath("//td[@id='cmb-comboConTypeId-inputCell']/following-sibling::td/div")).click();
		driver.findElement(By.xpath("//li[text()='Site Technical Contact']")).click();

		driver.findElement(By.id("firstName")).sendKeys("Site Technical Contact first");

		driver.findElement(By.id("lastName")).sendKeys("Site Technical Contact last");

		driver.findElement(By.id("officePhone")).sendKeys("2345456756");

		driver.findElement(By.xpath("//input[@id='_eventId_saveContact']")).click();

		try
		{
			if (driver.findElement(By.xpath("//span[text()='Yes']/following-sibling::span")).isDisplayed())
			{
				driver.findElement(By.xpath("//span[text()='Yes']/following-sibling::span")).click();			
			}
		}
		catch (Exception e)
		{
			System.err.println("Popup not present");
		}
		
		try
		{
			if (driver.findElement(By.xpath("//input[@value='success']/following-sibling::span")).isDisplayed())
			{
				//System.out.println(driver.findElement(By.xpath("//input[@value='success']/following-sibling::span")).getText());
				new UtilityClass(driver, testdataHashMap, eTest).extentReportsStep("Adress Site Technical Contact", "PASS", "YES");
			}
			else
			{
				new UtilityClass(driver, testdataHashMap, eTest).extentReportsStep("Adress Site Technical Contact", "FAIL", "YES");
			}
		}
		catch (Exception e)
		{
			System.err.println("Success msg not present");
		}

		driver.findElement(By.xpath("//input[@value='Back']")).click();
		Thread.sleep(1000);

	}

	
	
	
}

