package cmPages;

import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.ExtentTest;

import frameworkPackage.TestBase;
import frameworkPackage.UtilityClass;

public class ServicePage extends TestBase{

	public ServicePage(WebDriver driver, HashMap<String, String> testdataHashMap, ExtentTest eTest) {
		this.driver = driver;
		this.testdataHashMap = testdataHashMap;
		this.eTest = eTest;
		PageFactory.initElements(driver, this);
	}


	public void serviceConfiguration() throws Exception
	{

		driver.switchTo().defaultContent();

		driver.findElement(By.linkText("Service")).click();
		Thread.sleep(2000);

		driver.switchTo().frame("mainFrame");

		driver.findElement(By.id("loadAvailProduct")).click();

		
		//Plan search page

		driver.findElement(By.name("EDI")).click();

		//Select Price Plan
		/*driver.findElement(By.xpath("//div[text()='EDI']/../following-sibling::td/div/input")).clear();
		driver.findElement(By.xpath("//div[text()='EDI']/../following-sibling::td/div/input")).sendKeys("1");*/

		new UtilityClass(driver, testdataHashMap, eTest).extentReportsStep("Plan Search Tab Details", "INFO", "YES");		
		driver.findElement(By.id("loadSelectedProducts")).click();
		Thread.sleep(1000);

		
		//Feature page

		new UtilityClass(driver, testdataHashMap, eTest).extentReportsStep("Feature Tab Details", "INFO", "YES");
		driver.findElement(By.id("button_eventId_continue")).click();
		Thread.sleep(1000);
		

		//Process page

		//Service
		Select serviceTerms = new Select(driver.findElement(By.xpath("//td[text()='Terms']/following-sibling::td/select")));
		serviceTerms.selectByVisibleText("1 Month");

		Select ownIPAddress = new Select(driver.findElement(By.xpath("//td[text()='Customer Bringing Own IP Address']/following-sibling::td/select")));
		ownIPAddress.selectByVisibleText("Yes");

		driver.findElement(By.id("saveParam")).click();
		Thread.sleep(1000);
		
		System.out.println("Service saved in Process page");

		//UNI
		driver.findElement(By.xpath("//a[text()='UNI']")).click();
		Thread.sleep(1000);
		
		Select accessType = new Select(driver.findElement(By.xpath("//td[text()='Access Type']/following-sibling::td/select")));
		accessType.selectByVisibleText("On-net");
		
		driver.findElement(By.xpath("//img[@alt='Address Lookup']")).click();

		//For Automation Env
		//driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@id='codition'])[2]")));

		//For Hydra Env
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='codition']")));

		//driver.switchTo().activeElement();

		driver.findElement(By.id("addr")).click();

		driver.findElement(By.id("backToProcess")).click();

		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");

		driver.findElement(By.xpath("//input[@id='surClliCom-inputEl']")).sendKeys("ALALALALALA");

		driver.findElement(By.xpath("//input[@paramname='UNI Number']")).sendKeys("1123");

		Select uniPortSpeed = new Select(driver.findElement(By.xpath("//td[text()='UNI Port Speed']/following-sibling::td/select")));
		uniPortSpeed.selectByVisibleText("1GigE");

		driver.findElement(By.id("saveParam")).click();
		Thread.sleep(1000);

		System.out.println("UNI saved in Process page");

		//EVC
		driver.findElement(By.xpath("//ul[@name='/EDI EVC Configuration/']/li/a")).click();
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//td[@id='locZCombo-inputCell']/following-sibling::td/div")).click();

		driver.findElement(By.xpath("//div[text()='site address']")).click();

		driver.findElement(By.xpath("//td[text()='EVC Number']/following-sibling::td/input[5]")).sendKeys("4321");

		Select basicCoSBandwidth = new Select(driver.findElement(By.xpath("//select[@paramname='Basic CoS Bandwidth']")));
		basicCoSBandwidth.selectByVisibleText("1Mbps");

		driver.findElement(By.id("saveParam")).click();
		Thread.sleep(1000);

		System.out.println("EVC saved in Process page");

		new UtilityClass(driver, testdataHashMap, eTest).extentReportsStep("Process Tab Details", "INFO", "YES");
		
		driver.findElement(By.id("_eventId_continue")).click();
		Thread.sleep(1000);
				
		//Equipment fee site validation
		driver.findElement(By.xpath("//span[text()='No']/following-sibling::span")).click();

		boolean elementExist = new UtilityClass(driver, testdataHashMap, eTest).waitForElement("id", "submitOrder", 10000);
		
		if(elementExist)
		{
			//new UtilityClass(driver, testdataHashMap, eTest).extentReportsStep("Service page configuration", "PASS", "YES");
		}
		else
		{
			new UtilityClass(driver, testdataHashMap, eTest).extentReportsStep("Service page configuration", "FAIL", "YES");
		}

		
	}



	//Order Summary Tab

	public void orderSummary() throws InterruptedException, IOException
	{

		driver.findElement(By.xpath("//td[@id='customerOrderSignatureDate-inputCell']")).click();
		driver.findElement(By.xpath("//div[@class='x-datepicker-footer']/a/span/span/span/following-sibling::span")).click();

		Select taxJurisdiction = new Select(driver.findElement(By.id("taxJurisdiction")));
		taxJurisdiction.selectByVisibleText("Interstate");

		Select saleschannel = new Select(driver.findElement(By.id("saleschannel")));
		saleschannel.selectByVisibleText("Enterprise");

		Select soldRegion = new Select(driver.findElement(By.id("soldRegion")));
		soldRegion.selectByVisibleText("All Regions");

		driver.findElement(By.id("salesforceopportunityid")).sendKeys("1231");

		driver.findElement(By.id("salesOrderNumber")).sendKeys("1232");

		driver.findElement(By.id("salesOrderId")).sendKeys("1233");

		/*driver.findElement(By.xpath("//img[@title='Search']")).click();
		Thread.sleep(5000);*/

		driver.findElement(By.xpath("//td[@id='salesOrderAcceptanceDate-inputCell']/input")).click();
		driver.findElement(By.xpath("(//div[@class='x-datepicker-footer'])[2]/a/span/span/span/following-sibling::span")).click();

		driver.findElement(By.xpath("//td[@id='salesOrderSubmitDate-inputCell']/input")).click();
		driver.findElement(By.xpath("(//div[@class='x-datepicker-footer'])[3]/a/span/span/span/following-sibling::span")).click();
		Thread.sleep(1000);
		
		driver.findElement(By.id("submitOrder")).click();
		
		//MRC NRC Pop-up
		try 
		{
			if (driver.findElement(By.xpath("//div[text()='Are you sure you want to submit the order with $0 NRC/RC?']")).isDisplayed()) 
			{
				driver.findElement(By.xpath("//span[text()='Yes']/following-sibling::span")).click();
			}
		}	
		catch (Exception e)
		{
			System.err.println("MRC, NRC values are given");
		}

		//success msg for order submission
		boolean elementExist = new UtilityClass(driver, testdataHashMap, eTest).waitForElement("xpath", "//div[@id='errorResponse2']", 10000);
		
		if(elementExist)
		{
			String srid = driver.findElement(By.xpath("//input[@name='auroraEntityId']/../td[2]")).getText();
			new UtilityClass(driver, testdataHashMap, eTest).extentReportsStep("Order submission - "+srid, "PASS", "YES");
		}
		else
		{
			System.out.println("Order not submitted");
			new UtilityClass(driver, testdataHashMap, eTest).extentReportsStep("Order submission", "FAIL", "YES");
		}
		
		
	}




}
