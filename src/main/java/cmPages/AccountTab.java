package cmPages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import frameworkPackage.FrameworkBase;

public class AccountTab extends FrameworkBase{

	public AccountTab(FrameworkBase fBase) 
	{
		this.fBase = fBase;
		PageFactory.initElements(fBase.driver, this);
	}
	
	
	// Service Account
	
	public void accountCreation_Service() throws Exception
	{

		fBase.driver.switchTo().defaultContent();
		fBase.driver.switchTo().frame("mainFrame");

		// fBase.driver.findElement(By.xpath("//span[text()='Account']")).click();
		fBase.driver.findElement(By.linkText("Account")).click();

		fBase.driver.switchTo().frame("AccountFrame");

		fBase.driver.findElement(By.xpath("//td[@id='cmb-comboAccountType-inputCell']/following-sibling::td/div")).click();
		fBase.driver.findElement(By.xpath("//li[text()='Service']")).click();
		
		fBase.driver.findElement(By.xpath("//input[@id='accName']")).sendKeys("sAcc123");
		
		fBase.driver.findElement(By.xpath("//td[@id='cmb-comboComcastLegalEntity-inputCell']/following-sibling::td/div")).click();
		fBase.driver.findElement(By.xpath("//li[text()='Comcast Business Communications, LLC']")).click();
		
		fBase.driver.findElement(By.xpath("//td[@id='cmb-comboLineOfBusiness-inputCell']/following-sibling::td/div")).click();
		fBase.driver.findElement(By.xpath("//li[@role='option' and contains(text(),'Metro E')]")).click();
		
		fBase.driver.findElement(By.xpath("//td[@id='cmb-comboVMarket-inputCell']/following-sibling::td/div")).click();
		fBase.driver.findElement(By.xpath("//li[text()='Comcast']")).click();
		
		fBase.driver.findElement(By.id("useCustomerAddress")).click();
		Thread.sleep(1000);
		
		fBase.driver.findElement(By.id("_eventId_createAccount")).click();
		Thread.sleep(1000);

		if (fBase.driver.findElement(By.xpath("//span[@class='successMsg']")).isDisplayed()) {
			//String serviceAccountNo = fBase.driver.findElement(By.xpath("//span[@class='successMsg']")).getText();
			fBase.extentReportsStep("Service Account Creation", "PASS", "YES");
		} 
		else 
		{
			fBase.extentReportsStep("Service Account Creation", "FAIL", "YES");
		}

	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////


	// Billing Account creation

	public void accountCreation_Billing() throws Exception
	{

		fBase.driver.switchTo().defaultContent();
		fBase.driver.switchTo().frame("mainFrame");
		fBase.driver.switchTo().frame("AccountFrame");

		fBase.driver.findElement(By.id("_eventId_newAccount")).click();
		Thread.sleep(1000);
		
		fBase.driver.findElement(By.xpath("//td[@id='cmb-comboAccountType-inputCell']/following-sibling::td/div")).click();
		fBase.driver.findElement(By.xpath("//li[text()='Billing']")).click();
		Thread.sleep(1000);
		
		fBase.driver.findElement(By.xpath("//td[@id='assocToCombo-inputCell']/following-sibling::td/div")).click();
		fBase.driver.findElement(By.xpath("//li[text()='Service Account']")).click();
		
		fBase.driver.findElement(By.xpath("//td[@id='entityCombo-inputCell']/following-sibling::td/div")).click();
		fBase.driver.findElement(By.xpath("(//div[text()='sAcc123'])[2]")).click();
		
		fBase.driver.findElement(By.xpath("//td[@id='entityCombo-inputCell']/following-sibling::td/div")).click();
		
		fBase.driver.findElement(By.id("billingAccountName")).sendKeys("bAcc123");
		
		fBase.driver.findElement(By.xpath("//img[@src='images/plus_circle.png']")).click();
		fBase.driver.findElement(By.id("useCustomerAddress")).click();
		Thread.sleep(1000);
		
		fBase.driver.findElement(By.id("_eventId_createAccount")).click();

		if (fBase.driver.findElement(By.xpath("//span[@class='successMsg']")).isDisplayed()) 
		{
			//System.out.println(fBase.driver.findElement(By.xpath("//span[@class='successMsg']")).getText());
			fBase.extentReportsStep("Billing Account Creation", "PASS", "YES");
		}
		else
		{
			fBase.extentReportsStep("Billing Account Creation", "FAIL", "YES");
		}

	}

}
