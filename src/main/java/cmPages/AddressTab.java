package cmPages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import frameworkPackage.TestBase;

public class AddressTab extends TestBase{

	public AddressTab(TestBase testBase) 
	{
		this.testBase = testBase;
		PageFactory.initElements(testBase.driver, this);
	}
	
	
	// Address Creation
	public void addressCreation() throws Exception
	{

		testBase.driver.switchTo().defaultContent();
		testBase.driver.switchTo().frame("mainFrame");
		
		//testBase.driver.findElement(By.linkText("Address")).click();
		testBase.driver.findElement(By.xpath("//span[contains(text(),'Address')]")).click();

		testBase.driver.switchTo().defaultContent();
		testBase.driver.switchTo().frame("mainFrame");
		testBase.driver.switchTo().frame("AddressFrame");

		testBase.driver.findElement(By.id("addrSiteBean.siteName")).sendKeys("site address");

		testBase.driver.findElement(By.xpath("//td[@id='cmbaddrSiteBean.siteType-inputCell']/following-sibling::td/div")).click();
		testBase.driver.findElement(By.xpath("//li[text()='Residential']")).click();

		testBase.driver.findElement(By.id("addrSiteBean.address1")).sendKeys("siteAddress 1");

		testBase.driver.findElement(By.xpath("//td[@id='CmbSitestate-inputCell']/following-sibling::td/div")).click();
		testBase.driver.findElement(By.xpath("//li[text()='Alaska']")).click();
		Thread.sleep(1000);
		
		testBase.driver.findElement(By.xpath("//td[@id='CmbSitecity-inputCell']/following-sibling::td/div")).click();
		testBase.driver.findElement(By.xpath("//li[text()='Adak']")).click();
		Thread.sleep(1000);
		
		testBase.driver.findElement(By.xpath("//td[@id='CmbSitezipcode-inputCell']/following-sibling::td/div")).click();
		testBase.driver.findElement(By.xpath("//li[text()='99546']")).click();
		Thread.sleep(1000);
		
		testBase.driver.findElement(By.xpath("//td[@id='CmbSiteheadEndName-inputCell']/following-sibling::td/div")).click();

		testBase.driver.findElement(By.xpath("//li[text()='albany.or']")).click();
		Thread.sleep(1000);
		
		testBase.driver.findElement(By.id("create")).click();

		try 
		{
			if (testBase.driver.findElement(By.name("disclaimer")).isDisplayed()) 
			{
				testBase.driver.findElement(By.name("disclaimer")).click();
				testBase.driver.switchTo().frame("codition");
				testBase.driver.findElement(By.name("diclaimercheck")).click();
				testBase.driver.findElement(By.name("createSiteaddress")).click();
			}
		} 
		catch (Exception e) 
		{
			System.err.println("more button is not displayed due to known Address ");
		}

		testBase.driver.switchTo().defaultContent();
		testBase.driver.switchTo().frame("mainFrame");
		testBase.driver.switchTo().frame("AddressFrame");

		if (testBase.driver.findElement(By.xpath("//span[@class='successMsg']")).isDisplayed())
		{
			//System.out.println(testBase.driver.findElement(By.xpath("//span[@class='successMsg']")).getText());
			testBase.extentReportsStep("Adress Creation", "PASS", "YES");
		}
		else
		{
			testBase.extentReportsStep("Adress Creation", "FAIL", "YES");
		}



	}


	////////////////////////////////////////////////////////////////////////

	
}


