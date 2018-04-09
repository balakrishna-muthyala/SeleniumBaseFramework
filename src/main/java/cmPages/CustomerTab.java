package cmPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import frameworkPackage.FrameworkBase;
import frameworkPackage.UtilityClass;

public class CustomerTab extends FrameworkBase{

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
	

	public CustomerTab(FrameworkBase fBase){
		this.fBase = fBase;
		PageFactory.initElements(fBase.driver, this);
	}

	
	public  void customerCreation() throws Exception
	{
		UtilityClass util = new UtilityClass(fBase);

		String customerName = "Cust "+util.generateRandomNumber(7);
		String salesforceID = "SF "+util.generateRandomNumber(7);

		linkNewCustomer.click();
		Thread.sleep(2000);

		fBase.driver.switchTo().defaultContent();
		fBase.driver.switchTo().frame("mainFrame");
		fBase.driver.switchTo().frame("CustomerFrame");

		txtCustomerName.sendKeys(customerName);		
		txtWorkPhone.sendKeys("");
		Thread.sleep(1000);
		
		txtWorkPhone.sendKeys(fBase.getTestdata("WorkPhone"));
		
		txtSalesforceAccountID.sendKeys(salesforceID);
		
		cmbAddressFormat.click();
		
		lstGeneralDelivery.click();
		
		txtAddressLine1.sendKeys(fBase.getTestdata("CustomerAddressLine1"));
		
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
		
		util.waitForElement("name", "disclaimer", 5);
		
		try 
		{
			if (btnMore.isDisplayed()) 
			{
				btnMore.click();
				fBase.driver.switchTo().frame("codition");
				chkIAgree.click();
				btnContinue.click();
			}
		} 
		catch (Exception e) 
		{
			System.err.println("more button is not displayed due to known Address ");
		}

		fBase.driver.switchTo().defaultContent();
		fBase.driver.switchTo().frame("mainFrame");
		fBase.driver.switchTo().frame("CustomerFrame");

		String custSuccessMessage = txtSuccessMessage.getText();

		if (custSuccessMessage.contains(customerName)) 
		{
			fBase.extentReportsStep("Customer creation", "PASS", "YES");
		}
		else
		{
			fBase.extentReportsStep("Customer creation", "FAIL", "YES");
		}

		/*		
		fBase.driver.findElement(By.linkText("New Customer")).click();
		Thread.sleep(5000);

		fBase.driver.switchTo().defaultContent();
		fBase.driver.switchTo().frame("mainFrame");
		fBase.driver.switchTo().frame("CustomerFrame");

		fBase.driver.findElement(By.id("busiCustBean.businessName")).sendKeys(customerName);
		// fBase.driver.findElement(By.xpath("//div[@id='Customer']/div")).sendKeys("Cust234");
		fBase.driver.findElement(By.name("busiCustBean.busiWorkPhone1")).sendKeys("");
		fBase.driver.findElement(By.name("busiCustBean.busiWorkPhone1")).sendKeys("7689076578");
		fBase.driver.findElement(By.xpath("//td[@id='sfAccIdLabel']/following-sibling::td/input")).sendKeys(sfAccountId);
		fBase.driver.findElement(By.xpath("//td[@id='cmb-comboAddressFormatType-inputCell']/following-sibling::td/div")).click();
		fBase.driver.findElement(By.xpath("(//ul[@class='x-list-plain'])/li[text()='General Delivery']")).click();
		fBase.driver.findElement(By.name("addrBean.addrGDBean.addrsLine1")).sendKeys("JKLD Cross street 4");
		fBase.driver.findElement(By.xpath("//td[@id='CmbGDstate-inputCell']/following-sibling::td/div")).click();
		fBase.driver.findElement(By.xpath("(//ul[@class='x-list-plain'])/li[text()='Alabama']")).click();
		Thread.sleep(1000);
		fBase.driver.findElement(By.xpath("//td[@id='CmbGDcity-inputCell']/following-sibling::td/div")).click();
		fBase.driver.findElement(By.xpath("//li[text()='Abbeville']")).click();
		Thread.sleep(1000);
		fBase.driver.findElement(By.xpath("//td[@id='CmbGDzipcode-inputCell']/following-sibling::td/div")).click();
		fBase.driver.findElement(By.xpath("//li[text()='36310']")).click();
		Thread.sleep(1000);
		fBase.driver.findElement(By.id("_eventId_createCusotmer")).click();
		Thread.sleep(5000);


		try 
		{
			if (fBase.driver.findElement(By.name("disclaimer")).isDisplayed()) 
			{
				fBase.driver.findElement(By.name("disclaimer")).click();

				fBase.driver.switchTo().frame("codition");
				fBase.driver.findElement(By.name("diclaimercheck")).click();
				fBase.driver.findElement(By.name("createSiteaddress")).click();
				Thread.sleep(5000);
			}
		} 
		catch (Exception e) 
		{
			//e.printStackTrace();
		}

		fBase.driver.switchTo().defaultContent();
		fBase.driver.switchTo().frame("mainFrame");
		fBase.driver.switchTo().frame("CustomerFrame");

		String custSuccessMessage = fBase.driver.findElement(By.id("message")).getText();
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

	}

}
