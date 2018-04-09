package cmPages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import frameworkPackage.FrameworkBase;
import frameworkPackage.UtilityClass;

public class AddressTab extends FrameworkBase{

	public AddressTab(FrameworkBase fBase) 
	{
		this.fBase = fBase;
		PageFactory.initElements(fBase.driver, this);
	}
	
	
	// Address Creation
	public void addressCreation() throws Exception
	{

		UtilityClass util = new UtilityClass(fBase);
		
		fBase.driver.switchTo().defaultContent();
		fBase.driver.switchTo().frame("mainFrame");
		
		//fBase.driver.findElement(By.linkText("Address")).click();
		fBase.driver.findElement(By.xpath("//span[contains(text(),'Address')]")).click();

		fBase.driver.switchTo().defaultContent();
		fBase.driver.switchTo().frame("mainFrame");
		fBase.driver.switchTo().frame("AddressFrame");

		fBase.driver.findElement(By.id("addrSiteBean.siteName")).sendKeys("Custom site name");

		fBase.driver.findElement(By.xpath("//td[@id='cmbaddrSiteBean.siteType-inputCell']/following-sibling::td/div")).click();
		fBase.driver.findElement(By.xpath("//li[text()='Residential']")).click();

		fBase.driver.findElement(By.id("addrSiteBean.address1")).sendKeys("CustomSiteAddress 1");

		fBase.driver.findElement(By.xpath("//td[@id='CmbSitestate-inputCell']/following-sibling::td/div")).click();
		fBase.driver.findElement(By.xpath("//li[text()='Alaska']")).click();
		Thread.sleep(1000);
		
		fBase.driver.findElement(By.xpath("//td[@id='CmbSitecity-inputCell']/following-sibling::td/div")).click();
		fBase.driver.findElement(By.xpath("//li[text()='Adak']")).click();
		Thread.sleep(1000);
		
		fBase.driver.findElement(By.xpath("//td[@id='CmbSitezipcode-inputCell']/following-sibling::td/div")).click();
		fBase.driver.findElement(By.xpath("//li[text()='99546']")).click();
		Thread.sleep(1000);
		
		fBase.driver.findElement(By.xpath("//td[@id='CmbSiteheadEndName-inputCell']/following-sibling::td/div")).click();
		fBase.driver.findElement(By.xpath("//li[text()='albany.or']")).click();
		Thread.sleep(1000);
		
		fBase.driver.findElement(By.id("create")).click();
		
		util.waitForElement("name", "disclaimer", 5);

		try 
		{
			if (fBase.driver.findElement(By.name("disclaimer")).isDisplayed()) 
			{
				fBase.driver.findElement(By.name("disclaimer")).click();
				fBase.driver.switchTo().frame("codition");
				fBase.driver.findElement(By.name("diclaimercheck")).click();
				fBase.driver.findElement(By.name("createSiteaddress")).click();
			}
		} 
		catch (Exception e) 
		{
			System.err.println("more button is not displayed due to known Address ");
		}

		fBase.driver.switchTo().defaultContent();
		fBase.driver.switchTo().frame("mainFrame");
		fBase.driver.switchTo().frame("AddressFrame");

		if (fBase.driver.findElement(By.xpath("//span[@class='successMsg']")).isDisplayed())
		{
			//System.out.println(fBase.driver.findElement(By.xpath("//span[@class='successMsg']")).getText());
			fBase.extentReportsStep("Adress Creation", "PASS", "YES");
		}
		else
		{
			fBase.extentReportsStep("Adress Creation", "FAIL", "YES");
		}



	}


	////////////////////////////////////////////////////////////////////////

	
}


