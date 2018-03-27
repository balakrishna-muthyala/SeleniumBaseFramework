package cmPages;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;

import frameworkPackage.TestBase;
import frameworkPackage.UtilityClass;

public class AccountTab extends TestBase{

	public AccountTab(WebDriver driver, HashMap<String, String> testdataHashMap, ExtentTest eTest) {
		this.driver = driver;
		this.testdataHashMap = testdataHashMap;
		this.eTest = eTest;
		PageFactory.initElements(driver, this);
	}


	// Service Account
	
	public void accountCreation_Service() throws Exception
	{

		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");

		// driver.findElement(By.xpath("//span[text()='Account']")).click();
		driver.findElement(By.linkText("Account")).click();

		driver.switchTo().frame("AccountFrame");

		driver.findElement(By.xpath("//td[@id='cmb-comboAccountType-inputCell']/following-sibling::td/div")).click();
		driver.findElement(By.xpath("//li[text()='Service']")).click();
		
		driver.findElement(By.xpath("//input[@id='accName']")).sendKeys("sAcc123");
		
		driver.findElement(By.xpath("//td[@id='cmb-comboComcastLegalEntity-inputCell']/following-sibling::td/div")).click();
		driver.findElement(By.xpath("//li[text()='Comcast Business Communications, LLC']")).click();
		
		driver.findElement(By.xpath("//td[@id='cmb-comboLineOfBusiness-inputCell']/following-sibling::td/div")).click();
		driver.findElement(By.xpath("//li[@role='option' and contains(text(),'Metro E')]")).click();
		
		driver.findElement(By.xpath("//td[@id='cmb-comboVMarket-inputCell']/following-sibling::td/div")).click();
		driver.findElement(By.xpath("//li[text()='Comcast']")).click();
		
		driver.findElement(By.id("useCustomerAddress")).click();
		Thread.sleep(1000);
		
		driver.findElement(By.id("_eventId_createAccount")).click();
		Thread.sleep(1000);

		if (driver.findElement(By.xpath("//span[@class='successMsg']")).isDisplayed()) {
			//String serviceAccountNo = driver.findElement(By.xpath("//span[@class='successMsg']")).getText();
			new UtilityClass(driver, testdataHashMap, eTest).extentReportsStep("Service Account Creation", "PASS", "YES");
		} 
		else 
		{
			new UtilityClass(driver, testdataHashMap, eTest).extentReportsStep("Service Account Creation", "FAIL", "YES");
		}

	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////


	// Billing Account creation

	public void accountCreation_Billing() throws Exception
	{

		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.switchTo().frame("AccountFrame");

		driver.findElement(By.id("_eventId_newAccount")).click();
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//td[@id='cmb-comboAccountType-inputCell']/following-sibling::td/div")).click();
		driver.findElement(By.xpath("//li[text()='Billing']")).click();
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//td[@id='assocToCombo-inputCell']/following-sibling::td/div")).click();
		driver.findElement(By.xpath("//li[text()='Service Account']")).click();
		
		driver.findElement(By.xpath("//td[@id='entityCombo-inputCell']/following-sibling::td/div")).click();
		driver.findElement(By.xpath("(//div[text()='sAcc123'])[2]")).click();
		
		driver.findElement(By.xpath("//td[@id='entityCombo-inputCell']/following-sibling::td/div")).click();
		
		driver.findElement(By.id("billingAccountName")).sendKeys("bAcc123");
		
		driver.findElement(By.xpath("//img[@src='images/plus_circle.png']")).click();
		driver.findElement(By.id("useCustomerAddress")).click();
		Thread.sleep(1000);
		
		driver.findElement(By.id("_eventId_createAccount")).click();

		if (driver.findElement(By.xpath("//span[@class='successMsg']")).isDisplayed()) 
		{
			//System.out.println(driver.findElement(By.xpath("//span[@class='successMsg']")).getText());
			new UtilityClass(driver, testdataHashMap, eTest).extentReportsStep("Billing Account Creation", "PASS", "YES");
		}
		else
		{
			new UtilityClass(driver, testdataHashMap, eTest).extentReportsStep("Billing Account Creation", "FAIL", "YES");
		}

	}

}
