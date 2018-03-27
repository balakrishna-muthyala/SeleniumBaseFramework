package cmPages;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;

import frameworkPackage.TestBase;
import frameworkPackage.UtilityClass;

public class CustomerTab extends TestBase{

	@FindBy(linkText="New Customer") 
	WebElement linkNewCustomer; 

	@FindBy(id="busiCustBean.businessName") 
	WebElement txtCustomerName; 

	@FindBy(name="busiCustBean.busiWorkPhone1") 
	WebElement txtWorkPhone; 

	@FindBy(xpath="//td[@id='sfAccIdLabel']/following-sibling::td/input") 
	WebElement txtSalesforceAccountID; 

	@FindBy(xpath="//td[@id='cmb-comboAddressFormatType-inputCell']/following-sibling::td/div") 
	WebElement cmbAddressFormat; 

	@FindBy(xpath="(//ul[@class='x-list-plain'])/li[text()='General Delivery']") 
	WebElement lstGeneralDelivery; 

	@FindBy(name="addrBean.addrGDBean.addrsLine1") 
	WebElement txtAddressLine1; 

	@FindBy(xpath="//td[@id='CmbGDstate-inputCell']/following-sibling::td/div") 
	WebElement cmbState; 

	@FindBy(xpath="(//ul[@class='x-list-plain'])/li[text()='Alabama']") 
	WebElement lstAlabama; 

	@FindBy(xpath="//td[@id='CmbGDcity-inputCell']/following-sibling::td/div") 
	WebElement cmbCity; 

	@FindBy(xpath="//li[text()='Abbeville']") 
	WebElement lstAbbeville; 

	@FindBy(xpath="//td[@id='CmbGDzipcode-inputCell']/following-sibling::td/div") 
	WebElement cmbZipcode; 

	@FindBy(xpath="//li[text()='36310']") 
	WebElement lst36310; 

	@FindBy(id="_eventId_createCusotmer") 
	WebElement btnCreate; 

	@FindBy(name="disclaimer") 
	WebElement btnMore; 

	@FindBy(name="diclaimercheck") 
	WebElement chkIAgree; 

	@FindBy(name="createSiteaddress") 
	WebElement btnContinue; 

	@FindBy(id="message") 
	WebElement txtSuccessMessage; 

	public CustomerTab(WebDriver driver, HashMap<String, String> testdataHashMap, ExtentTest eTest) {
		this.driver = driver;
		this.testdataHashMap = testdataHashMap;
		this.eTest = eTest;
		PageFactory.initElements(driver, this);
	}


	public  void customerCreation() throws Exception
	{
		/*		
		driver.findElement(By.linkText("New Customer")).click();
		Thread.sleep(5000);

		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.switchTo().frame("CustomerFrame");

		driver.findElement(By.id("busiCustBean.businessName")).sendKeys(customerName);
		// driver.findElement(By.xpath("//div[@id='Customer']/div")).sendKeys("Cust234");
		driver.findElement(By.name("busiCustBean.busiWorkPhone1")).sendKeys("");
		driver.findElement(By.name("busiCustBean.busiWorkPhone1")).sendKeys("7689076578");
		driver.findElement(By.xpath("//td[@id='sfAccIdLabel']/following-sibling::td/input")).sendKeys(sfAccountId);
		driver.findElement(By.xpath("//td[@id='cmb-comboAddressFormatType-inputCell']/following-sibling::td/div")).click();
		driver.findElement(By.xpath("(//ul[@class='x-list-plain'])/li[text()='General Delivery']")).click();
		driver.findElement(By.name("addrBean.addrGDBean.addrsLine1")).sendKeys("JKLD Cross street 4");
		driver.findElement(By.xpath("//td[@id='CmbGDstate-inputCell']/following-sibling::td/div")).click();
		driver.findElement(By.xpath("(//ul[@class='x-list-plain'])/li[text()='Alabama']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//td[@id='CmbGDcity-inputCell']/following-sibling::td/div")).click();
		driver.findElement(By.xpath("//li[text()='Abbeville']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//td[@id='CmbGDzipcode-inputCell']/following-sibling::td/div")).click();
		driver.findElement(By.xpath("//li[text()='36310']")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("_eventId_createCusotmer")).click();
		Thread.sleep(5000);


		try 
		{
			if (driver.findElement(By.name("disclaimer")).isDisplayed()) 
			{
				driver.findElement(By.name("disclaimer")).click();

				driver.switchTo().frame("codition");
				driver.findElement(By.name("diclaimercheck")).click();
				driver.findElement(By.name("createSiteaddress")).click();
				Thread.sleep(5000);
			}
		} 
		catch (Exception e) 
		{
			//e.printStackTrace();
		}

		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.switchTo().frame("CustomerFrame");

		String custSuccessMessage = driver.findElement(By.id("message")).getText();
		System.out.println(custSuccessMessage);

		if (custSuccessMessage.contains(customerName)) 
		{
			System.out.println("Customer created - " + customerName);
			eTest.log(LogStatus.PASS, "Customer creation");
		}
		else
		{
			eTest.log(LogStatus.FAIL, "Customer creation");
		}
		 */


		UtilityClass util = new UtilityClass(driver, testdataHashMap, eTest);

		String customerName = "Cust "+util.generateRandomNumber(7);
		String salesforceID = "SF "+util.generateRandomNumber(7);

		linkNewCustomer.click();
		Thread.sleep(2000);

		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.switchTo().frame("CustomerFrame");

		txtCustomerName.sendKeys(customerName);		
		txtWorkPhone.sendKeys("");
		Thread.sleep(1000);
		
		txtWorkPhone.sendKeys(util.getTestdata("WorkPhone"));
		
		txtSalesforceAccountID.sendKeys(salesforceID);
		
		cmbAddressFormat.click();
		
		lstGeneralDelivery.click();
		
		txtAddressLine1.sendKeys(util.getTestdata("AddressLine1"));
		
		cmbState.click();
		lstAlabama.click();
		Thread.sleep(1000);
		
		cmbCity.click();		
		lstAbbeville.click();
		Thread.sleep(1000);
		
		cmbZipcode.click();		
		lst36310.click();
		Thread.sleep(1000);
		
		btnCreate.click();

		try 
		{
			if (btnMore.isDisplayed()) 
			{
				btnMore.click();
				driver.switchTo().frame("codition");
				chkIAgree.click();
				btnContinue.click();
			}
		} 
		catch (Exception e) 
		{
			System.err.println("more button is not displayed due to known Address ");
		}

		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.switchTo().frame("CustomerFrame");

		String custSuccessMessage = txtSuccessMessage.getText();

		if (custSuccessMessage.contains(customerName)) 
		{
			new UtilityClass(driver, testdataHashMap, eTest).extentReportsStep("Customer creation", "PASS", "YES");
		}
		else
		{
			new UtilityClass(driver, testdataHashMap, eTest).extentReportsStep("Customer creation", "FAIL", "YES");
		}


	}

}
