package cmPages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import frameworkPackage.TestBase;
import frameworkPackage.UtilityClass;

public class ServicePage extends TestBase{

	public ServicePage(TestBase testBase){
		this.testBase = testBase;
		PageFactory.initElements(testBase.driver, this);
	}

	
	public void serviceConfiguration() throws Exception
	{
		UtilityClass util = new UtilityClass(testBase);
		
		testBase.driver.switchTo().defaultContent();

		testBase.driver.findElement(By.linkText("Service")).click();
		Thread.sleep(2000);

		testBase.driver.switchTo().frame("mainFrame");

		testBase.driver.findElement(By.id("loadAvailProduct")).click();

		
		//Plan search page

		testBase.driver.findElement(By.name("EDI")).click();

		//Select Price Plan
		/*testBase.driver.findElement(By.xpath("//div[text()='EDI']/../following-sibling::td/div/input")).clear();
		testBase.driver.findElement(By.xpath("//div[text()='EDI']/../following-sibling::td/div/input")).sendKeys("1");*/

		testBase.extentReportsStep("Plan Search Tab Details", "INFO", "YES");		
		testBase.driver.findElement(By.id("loadSelectedProducts")).click();
		Thread.sleep(1000);

		
		//Feature page

		testBase.extentReportsStep("Feature Tab Details", "INFO", "YES");
		testBase.driver.findElement(By.id("button_eventId_continue")).click();
		Thread.sleep(1000);
		

		//Process page

		//Service
		Select serviceTerms = new Select(testBase.driver.findElement(By.xpath("//td[text()='Terms']/following-sibling::td/select")));
		serviceTerms.selectByVisibleText("1 Month");

		Select ownIPAddress = new Select(testBase.driver.findElement(By.xpath("//td[text()='Customer Bringing Own IP Address']/following-sibling::td/select")));
		ownIPAddress.selectByVisibleText("Yes");

		testBase.extentReportsStep("Service Details", "INFO", "YES");
		
		testBase.driver.findElement(By.id("saveParam")).click();
		Thread.sleep(1000);
		
		System.out.println("Service saved in Process page");

		//UNI
		testBase.driver.findElement(By.xpath("//a[text()='UNI']")).click();
		Thread.sleep(1000);
		
		Select accessType = new Select(testBase.driver.findElement(By.xpath("//td[text()='Access Type']/following-sibling::td/select")));
		accessType.selectByVisibleText("On-net");
		
		testBase.driver.findElement(By.xpath("//img[@alt='Address Lookup']")).click();

		//For Automation Env
		//testBase.driver.switchTo().frame(testBase.driver.findElement(By.xpath("(//iframe[@id='codition'])[2]")));

		//For Hydra Env
		testBase.driver.switchTo().frame(testBase.driver.findElement(By.xpath("//iframe[@id='codition']")));

		//testBase.driver.switchTo().activeElement();

		testBase.driver.findElement(By.id("addr")).click();

		testBase.driver.findElement(By.id("backToProcess")).click();
		Thread.sleep(1000);
		
		testBase.driver.switchTo().defaultContent();
		testBase.driver.switchTo().frame("mainFrame");

		testBase.driver.findElement(By.xpath("//input[@id='surClliCom-inputEl']")).sendKeys("xxxxALxxxxx");

		testBase.driver.findElement(By.xpath("//input[@paramname='UNI Number']")).sendKeys("1123");

		Select uniPortSpeed = new Select(testBase.driver.findElement(By.xpath("//td[text()='UNI Port Speed']/following-sibling::td/select")));
		uniPortSpeed.selectByVisibleText("1GigE");

		testBase.extentReportsStep("UNI Details", "INFO", "YES");
		
		testBase.driver.findElement(By.id("saveParam")).click();
		Thread.sleep(1000);

		System.out.println("UNI saved in Process page");

		//EVC
		testBase.driver.findElement(By.xpath("//ul[@name='/EDI EVC Configuration/']/li/a")).click();
		Thread.sleep(1000);
		
		testBase.driver.findElement(By.xpath("//td[@id='locZCombo-inputCell']/following-sibling::td/div")).click();

		testBase.driver.findElement(By.xpath("//div[text()='site address']")).click();

		testBase.driver.findElement(By.xpath("//td[text()='EVC Number']/following-sibling::td/input[5]")).sendKeys("4321");

		Select basicCoSBandwidth = new Select(testBase.driver.findElement(By.xpath("//select[@paramname='Basic CoS Bandwidth']")));
		basicCoSBandwidth.selectByVisibleText("1Mbps");

		testBase.extentReportsStep("EVC Details", "INFO", "YES");
		
		testBase.driver.findElement(By.id("saveParam")).click();
		Thread.sleep(1000);

		System.out.println("EVC saved in Process page");

		
		testBase.extentReportsStep("Process Tab Details", "PASS", "YES");
		
		testBase.driver.findElement(By.id("_eventId_continue")).click();
		Thread.sleep(1000);
				
		//Equipment fee site validation
		testBase.driver.findElement(By.xpath("//span[text()='No']/following-sibling::span")).click();

		boolean elementExist = util.waitForElement("id", "submitOrder", 10000);
		
		if(!elementExist)
		{
			testBase.extentReportsStep("Service page configuration", "FAIL", "YES");
		}

		
	}



	//Order Summary Tab

	public void orderSummary() throws InterruptedException, IOException
	{
		UtilityClass util = new UtilityClass(testBase);
		
		testBase.driver.findElement(By.xpath("//td[@id='customerOrderSignatureDate-inputCell']")).click();
		testBase.driver.findElement(By.xpath("//div[@class='x-datepicker-footer']/a/span/span/span/following-sibling::span")).click();

		Select taxJurisdiction = new Select(testBase.driver.findElement(By.id("taxJurisdiction")));
		taxJurisdiction.selectByVisibleText("Interstate");

		Select saleschannel = new Select(testBase.driver.findElement(By.id("saleschannel")));
		saleschannel.selectByVisibleText("Enterprise");

		Select soldRegion = new Select(testBase.driver.findElement(By.id("soldRegion")));
		soldRegion.selectByVisibleText("All Regions");

		testBase.driver.findElement(By.id("salesforceopportunityid")).sendKeys("1231");

		testBase.driver.findElement(By.id("salesOrderNumber")).sendKeys("1232");

		testBase.driver.findElement(By.id("salesOrderId")).sendKeys("1233");

		/*testBase.driver.findElement(By.xpath("//img[@title='Search']")).click();
		Thread.sleep(5000);*/

		testBase.driver.findElement(By.xpath("//td[@id='salesOrderAcceptanceDate-inputCell']/input")).click();
		testBase.driver.findElement(By.xpath("(//div[@class='x-datepicker-footer'])[2]/a/span/span/span/following-sibling::span")).click();

		testBase.driver.findElement(By.xpath("//td[@id='salesOrderSubmitDate-inputCell']/input")).click();
		testBase.driver.findElement(By.xpath("(//div[@class='x-datepicker-footer'])[3]/a/span/span/span/following-sibling::span")).click();
		Thread.sleep(1000);
		
		testBase.driver.findElement(By.id("submitOrder")).click();
		
		//MRC NRC Pop-up
		try 
		{
			if (testBase.driver.findElement(By.xpath("//div[text()='Are you sure you want to submit the order with $0 NRC/RC?']")).isDisplayed()) 
			{
				testBase.driver.findElement(By.xpath("//span[text()='Yes']/following-sibling::span")).click();
			}
		}	
		catch (Exception e)
		{
			System.err.println("MRC, NRC values are given");
		}

		//success msg for order submission
		boolean elementExist = util.waitForElement("xpath", "//div[@id='errorResponse2']", 10000);
		
		if(elementExist)
		{
			String srid = testBase.driver.findElement(By.xpath("//input[@name='auroraEntityId']/../td[2]")).getText();
			testBase.extentReportsStep("Order submission - "+srid, "PASS", "YES");
		}
		else
		{
			System.out.println("Order not submitted");
			testBase.extentReportsStep("Order submission", "FAIL", "YES");
		}
		
		
	}




}
