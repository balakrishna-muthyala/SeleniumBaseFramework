package cmPages;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;

import frameworkPackage.TestBase;
import frameworkPackage.UtilityClass;

public class AddressTab extends TestBase{

	public AddressTab(WebDriver driver, HashMap<String, String> testdataHashMap, ExtentTest eTest) {
		this.driver = driver;
		this.testdataHashMap = testdataHashMap;
		this.eTest = eTest;
		PageFactory.initElements(driver, this);
	}

	
	// Address Creation
	public void addressCreation() throws Exception
	{

		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		
		//driver.findElement(By.linkText("Address")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Address')]")).click();

		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.switchTo().frame("AddressFrame");

		driver.findElement(By.id("addrSiteBean.siteName")).sendKeys("site address");

		driver.findElement(By.xpath("//td[@id='cmbaddrSiteBean.siteType-inputCell']/following-sibling::td/div")).click();
		driver.findElement(By.xpath("//li[text()='Residential']")).click();

		driver.findElement(By.id("addrSiteBean.address1")).sendKeys("siteAddress 1");

		driver.findElement(By.xpath("//td[@id='CmbSitestate-inputCell']/following-sibling::td/div")).click();
		driver.findElement(By.xpath("//li[text()='Alaska']")).click();
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//td[@id='CmbSitecity-inputCell']/following-sibling::td/div")).click();
		driver.findElement(By.xpath("//li[text()='Adak']")).click();
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//td[@id='CmbSitezipcode-inputCell']/following-sibling::td/div")).click();
		driver.findElement(By.xpath("//li[text()='99546']")).click();
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//td[@id='CmbSiteheadEndName-inputCell']/following-sibling::td/div")).click();

		driver.findElement(By.xpath("//li[text()='albany.or']")).click();
		Thread.sleep(1000);
		
		driver.findElement(By.id("create")).click();

		try 
		{
			if (driver.findElement(By.name("disclaimer")).isDisplayed()) 
			{
				driver.findElement(By.name("disclaimer")).click();
				driver.switchTo().frame("codition");
				driver.findElement(By.name("diclaimercheck")).click();
				driver.findElement(By.name("createSiteaddress")).click();
			}
		} 
		catch (Exception e) 
		{
			System.err.println("more button is not displayed due to known Address ");
		}

		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.switchTo().frame("AddressFrame");

		if (driver.findElement(By.xpath("//span[@class='successMsg']")).isDisplayed())
		{
			//System.out.println(driver.findElement(By.xpath("//span[@class='successMsg']")).getText());
			new UtilityClass(driver, testdataHashMap, eTest).extentReportsStep("Adress Creation", "PASS", "YES");
		}
		else
		{
			new UtilityClass(driver, testdataHashMap, eTest).extentReportsStep("Adress Creation", "FAIL", "YES");
		}



	}


	////////////////////////////////////////////////////////////////////////

	
}


