package cmPages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import frameworkPackage.TestBase;

public class AccountTab extends TestBase{

	public AccountTab(TestBase testBase) 
	{
		this.testBase = testBase;
		PageFactory.initElements(testBase.driver, this);
	}
	
	
	// Service Account
	
	public void accountCreation_Service() throws Exception
	{

		testBase.driver.switchTo().defaultContent();
		testBase.driver.switchTo().frame("mainFrame");

		// testBase.driver.findElement(By.xpath("//span[text()='Account']")).click();
		testBase.driver.findElement(By.linkText("Account")).click();

		testBase.driver.switchTo().frame("AccountFrame");

		testBase.driver.findElement(By.xpath("//td[@id='cmb-comboAccountType-inputCell']/following-sibling::td/div")).click();
		testBase.driver.findElement(By.xpath("//li[text()='Service']")).click();
		
		testBase.driver.findElement(By.xpath("//input[@id='accName']")).sendKeys("sAcc123");
		
		testBase.driver.findElement(By.xpath("//td[@id='cmb-comboComcastLegalEntity-inputCell']/following-sibling::td/div")).click();
		testBase.driver.findElement(By.xpath("//li[text()='Comcast Business Communications, LLC']")).click();
		
		testBase.driver.findElement(By.xpath("//td[@id='cmb-comboLineOfBusiness-inputCell']/following-sibling::td/div")).click();
		testBase.driver.findElement(By.xpath("//li[@role='option' and contains(text(),'Metro E')]")).click();
		
		testBase.driver.findElement(By.xpath("//td[@id='cmb-comboVMarket-inputCell']/following-sibling::td/div")).click();
		testBase.driver.findElement(By.xpath("//li[text()='Comcast']")).click();
		
		testBase.driver.findElement(By.id("useCustomerAddress")).click();
		Thread.sleep(1000);
		
		testBase.driver.findElement(By.id("_eventId_createAccount")).click();
		Thread.sleep(1000);

		if (testBase.driver.findElement(By.xpath("//span[@class='successMsg']")).isDisplayed()) {
			//String serviceAccountNo = testBase.driver.findElement(By.xpath("//span[@class='successMsg']")).getText();
			testBase.extentReportsStep("Service Account Creation", "PASS", "YES");
		} 
		else 
		{
			testBase.extentReportsStep("Service Account Creation", "FAIL", "YES");
		}

	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////


	// Billing Account creation

	public void accountCreation_Billing() throws Exception
	{

		testBase.driver.switchTo().defaultContent();
		testBase.driver.switchTo().frame("mainFrame");
		testBase.driver.switchTo().frame("AccountFrame");

		testBase.driver.findElement(By.id("_eventId_newAccount")).click();
		Thread.sleep(1000);
		
		testBase.driver.findElement(By.xpath("//td[@id='cmb-comboAccountType-inputCell']/following-sibling::td/div")).click();
		testBase.driver.findElement(By.xpath("//li[text()='Billing']")).click();
		Thread.sleep(1000);
		
		testBase.driver.findElement(By.xpath("//td[@id='assocToCombo-inputCell']/following-sibling::td/div")).click();
		testBase.driver.findElement(By.xpath("//li[text()='Service Account']")).click();
		
		testBase.driver.findElement(By.xpath("//td[@id='entityCombo-inputCell']/following-sibling::td/div")).click();
		testBase.driver.findElement(By.xpath("(//div[text()='sAcc123'])[2]")).click();
		
		testBase.driver.findElement(By.xpath("//td[@id='entityCombo-inputCell']/following-sibling::td/div")).click();
		
		testBase.driver.findElement(By.id("billingAccountName")).sendKeys("bAcc123");
		
		testBase.driver.findElement(By.xpath("//img[@src='images/plus_circle.png']")).click();
		testBase.driver.findElement(By.id("useCustomerAddress")).click();
		Thread.sleep(1000);
		
		testBase.driver.findElement(By.id("_eventId_createAccount")).click();

		if (testBase.driver.findElement(By.xpath("//span[@class='successMsg']")).isDisplayed()) 
		{
			//System.out.println(testBase.driver.findElement(By.xpath("//span[@class='successMsg']")).getText());
			testBase.extentReportsStep("Billing Account Creation", "PASS", "YES");
		}
		else
		{
			testBase.extentReportsStep("Billing Account Creation", "FAIL", "YES");
		}

	}

}
