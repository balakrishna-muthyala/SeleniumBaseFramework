package csoPages;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.ExtentTest;

import frameworkPackage.TestBase;
import frameworkPackage.UtilityClass;

public class CsoLoginPage extends TestBase
{
	
	@FindBy(id="username")
	WebElement csoLoginUsername; 
	
	@FindBy(id="password")
	WebElement csoLoginPassword; 
	
	@FindBy(id="domainList")
	WebElement csoLoginDomain; 
	
	@FindBy(xpath="//input[@id='logButton']")
	WebElement csoLoginButton; 
	
	
	public CsoLoginPage(WebDriver driver, HashMap<String, String> testdataHashMap, ExtentTest eTest)
	{
		this.driver = driver;
		this.testdataHashMap = testdataHashMap;
		this.eTest = eTest;
		PageFactory.initElements(driver, this);
	}
	
	
	public void openURL_CSO() throws Exception
	{

		//String csoURL = "https://sit-hydra.excelacom.in/OM";
		driver.get(csoURL);

		Thread.sleep(5000);

		String ExpectedTitle = "Login Page";
		String ActualTitle = driver.getTitle();

		if (ActualTitle.contentEquals(ExpectedTitle))
		{
			new UtilityClass(driver, testdataHashMap, eTest).extentReportsStep("CSO URL Open", "PASS", "YES");
		} 
		else 
		{
			new UtilityClass(driver, testdataHashMap, eTest).extentReportsStep("CSO URL Open", "FAIL", "YES");
		}
	}

	///////////////////////////////////////////////////////////////////////////////////////////////


	public void csoLogin() throws Exception
	{

		String strUsername = "custpmauto";
		String strPassword = "welcome123";

		/*driver.findElement(By.id("username")).sendKeys(strUsername);
		driver.findElement(By.id("password")).sendKeys(strPassword);

		Select domain = new Select(driver.findElement(By.id("domainList")));
		domain.selectByVisibleText("Century");

		Thread.sleep(5000);

		driver.findElement(By.xpath("//input[@id='logButton']")).click();

		Thread.sleep(5000);*/
		
		csoLoginUsername.sendKeys(strUsername);
		csoLoginPassword.sendKeys(strPassword);
		
		Select domain = new Select(csoLoginDomain);
		domain.selectByVisibleText("Century");
		
		csoLoginButton.click();

		String ExpectedTitle = "Service Orchestrator";
		String ActualTitle = driver.getTitle();

		if (ActualTitle.contentEquals(ExpectedTitle))
		{
			new UtilityClass(driver, testdataHashMap, eTest).extentReportsStep("CSO Login", "PASS", "YES");
		}
		else
		{
			new UtilityClass(driver, testdataHashMap, eTest).extentReportsStep("CSO Login", "FAIL", "YES");
		}

		Thread.sleep(10000);
		
	}
	
	
		

}

