package cmPages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import frameworkPackage.FrameworkBase;
import frameworkPackage.UtilityClass;

public class ServicePage extends FrameworkBase{

	public ServicePage(FrameworkBase fBase){
		this.fBase = fBase;
		PageFactory.initElements(fBase.driver, this);
	}

	
	public void serviceConfiguration() throws Exception
	{
		UtilityClass util = new UtilityClass(fBase);
		
		fBase.driver.switchTo().defaultContent();

		fBase.driver.findElement(By.linkText("Service")).click();
		Thread.sleep(2000);

		fBase.driver.switchTo().frame("mainFrame");

		fBase.driver.findElement(By.id("loadAvailProduct")).click();

		
		//Plan search page

		fBase.driver.findElement(By.name("EDI")).click();

		//Select Price Plan
		/*fBase.driver.findElement(By.xpath("//div[text()='EDI']/../following-sibling::td/div/input")).clear();
		fBase.driver.findElement(By.xpath("//div[text()='EDI']/../following-sibling::td/div/input")).sendKeys("1");*/

		fBase.extentReportsStep("Plan Search Tab Details", "INFO", "YES");		
		fBase.driver.findElement(By.id("loadSelectedProducts")).click();
		Thread.sleep(1000);

		
		//Feature page

		fBase.extentReportsStep("Feature Tab Details", "INFO", "YES");
		fBase.driver.findElement(By.id("button_eventId_continue")).click();
		Thread.sleep(1000);
		

		//Process page

		//Service
		Select serviceTerms = new Select(fBase.driver.findElement(By.xpath("//td[text()='Terms']/following-sibling::td/select")));
		serviceTerms.selectByVisibleText("1 Month");

		Select ownIPAddress = new Select(fBase.driver.findElement(By.xpath("//td[text()='Customer Bringing Own IP Address']/following-sibling::td/select")));
		ownIPAddress.selectByVisibleText("Yes");

		fBase.extentReportsStep("EDI Details", "INFO", "YES");
		
		fBase.driver.findElement(By.id("saveParam")).click();
		Thread.sleep(1000);
		
		System.out.println("EDI service details saved in Process page");

		//UNI
		fBase.driver.findElement(By.xpath("//a[text()='UNI']")).click();
		Thread.sleep(1000);
		
		Select accessType = new Select(fBase.driver.findElement(By.xpath("//td[text()='Access Type']/following-sibling::td/select")));
		accessType.selectByVisibleText("On-net");
		
		fBase.driver.findElement(By.xpath("//img[@alt='Address Lookup']")).click();

		//For Automation Env
		//fBase.driver.switchTo().frame(fBase.driver.findElement(By.xpath("(//iframe[@id='codition'])[2]")));

		//For Hydra Env
		fBase.driver.switchTo().frame(fBase.driver.findElement(By.xpath("//iframe[@id='codition']")));

		//fBase.driver.switchTo().activeElement();

		fBase.driver.findElement(By.id("addr")).click();

		fBase.driver.findElement(By.id("backToProcess")).click();
		Thread.sleep(1000);
		
		fBase.driver.switchTo().defaultContent();
		fBase.driver.switchTo().frame("mainFrame");

		fBase.driver.findElement(By.xpath("//input[@id='surClliCom-inputEl']")).sendKeys("xxxxALxxxxx");

		fBase.driver.findElement(By.xpath("//input[@paramname='UNI Number']")).sendKeys("1123");

		Select uniPortSpeed = new Select(fBase.driver.findElement(By.xpath("//td[text()='UNI Port Speed']/following-sibling::td/select")));
		uniPortSpeed.selectByVisibleText("1GigE");

		fBase.extentReportsStep("UNI Details", "INFO", "YES");
		
		fBase.driver.findElement(By.id("saveParam")).click();
		Thread.sleep(1000);

		System.out.println("UNI saved in Process page");

		//EVC
		fBase.driver.findElement(By.xpath("//ul[@name='/EDI EVC Configuration/']/li/a")).click();
		Thread.sleep(1000);
		
		fBase.driver.findElement(By.xpath("//td[@id='locZCombo-inputCell']/following-sibling::td/div")).click();

		fBase.driver.findElement(By.xpath("//div[text()='Custom site name']")).click();

		fBase.driver.findElement(By.xpath("//td[text()='EVC Number']/following-sibling::td/input[5]")).sendKeys("4321");

		Select basicCoSBandwidth = new Select(fBase.driver.findElement(By.xpath("//select[@paramname='Basic CoS Bandwidth']")));
		basicCoSBandwidth.selectByVisibleText("1Mbps");

		fBase.extentReportsStep("EVC Details", "INFO", "YES");
		
		fBase.driver.findElement(By.id("saveParam")).click();
		Thread.sleep(1000);

		System.out.println("EVC saved in Process page");

		
		fBase.extentReportsStep("Process Tab Details", "PASS", "YES");
		
		fBase.driver.findElement(By.id("_eventId_continue")).click();
		Thread.sleep(1000);
				
		//Equipment fee site validation
		fBase.driver.findElement(By.xpath("//span[text()='No']/following-sibling::span")).click();

		boolean elementExist = util.waitForElement("id", "submitOrder", 10000);
		
		if(!elementExist)
		{
			fBase.extentReportsStep("Service page configuration", "FAIL", "YES");
		}

		
	}



	//Order Summary Tab

	public void orderSummary() throws InterruptedException, IOException
	{
		UtilityClass util = new UtilityClass(fBase);
		
		fBase.driver.findElement(By.xpath("//td[@id='customerOrderSignatureDate-inputCell']")).click();
		fBase.driver.findElement(By.xpath("//div[@class='x-datepicker-footer']/a/span/span/span/following-sibling::span")).click();

		Select taxJurisdiction = new Select(fBase.driver.findElement(By.id("taxJurisdiction")));
		taxJurisdiction.selectByVisibleText("Interstate");

		Select saleschannel = new Select(fBase.driver.findElement(By.id("saleschannel")));
		saleschannel.selectByVisibleText("Enterprise");

		Select soldRegion = new Select(fBase.driver.findElement(By.id("soldRegion")));
		soldRegion.selectByVisibleText("All Regions");

		fBase.driver.findElement(By.id("salesforceopportunityid")).sendKeys("1231");

		fBase.driver.findElement(By.id("salesOrderNumber")).sendKeys("1232");

		fBase.driver.findElement(By.id("salesOrderId")).sendKeys("1233");

		/*fBase.driver.findElement(By.xpath("//img[@title='Search']")).click();
		Thread.sleep(5000);*/

		fBase.driver.findElement(By.xpath("//td[@id='salesOrderAcceptanceDate-inputCell']/input")).click();
		fBase.driver.findElement(By.xpath("(//div[@class='x-datepicker-footer'])[2]/a/span/span/span/following-sibling::span")).click();

		fBase.driver.findElement(By.xpath("//td[@id='salesOrderSubmitDate-inputCell']/input")).click();
		fBase.driver.findElement(By.xpath("(//div[@class='x-datepicker-footer'])[3]/a/span/span/span/following-sibling::span")).click();
		Thread.sleep(1000);
		
		fBase.driver.findElement(By.id("submitOrder")).click();
		
		//MRC NRC Pop-up
		try 
		{
			if (fBase.driver.findElement(By.xpath("//div[text()='Are you sure you want to submit the order with $0 NRC/RC?']")).isDisplayed()) 
			{
				fBase.driver.findElement(By.xpath("//span[text()='Yes']/following-sibling::span")).click();
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
			String srid = fBase.driver.findElement(By.xpath("//input[@name='auroraEntityId']/../td[2]")).getText();
			fBase.extentReportsStep("Order submission - "+srid, "PASS", "YES");
		}
		else
		{
			System.out.println("Order not submitted");
			fBase.extentReportsStep("Order submission", "FAIL", "YES");
		}
		
		
	}




}
