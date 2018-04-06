package csoPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import frameworkPackage.TestBase;

public class CsoHomePage extends TestBase
{
	
	@FindBy(id="username")
	WebElement csoLoginUsername; 
	
	@FindBy(id="password")
	WebElement csoLoginPassword; 
	
	@FindBy(id="domainList")
	WebElement csoLoginDomain; 
	
	@FindBy(xpath="//input[@id='logButton']")
	WebElement csoLoginButton; 
	
	
	public CsoHomePage(TestBase testBase) 
	{
		this.testBase = testBase;
		PageFactory.initElements(testBase.driver, this);
	}


	public void openURL_CSO() throws Exception
	{

		//String csoURL = "https://sit-hydra.excelacom.in/OM";
		testBase.driver.get(csoURL);

		Thread.sleep(2000);

		String ExpectedTitle = "Login Page";
		String ActualTitle = this.testBase.driver.getTitle();

		if (ActualTitle.contentEquals(ExpectedTitle))
		{
			testBase.extentReportsStep("CSO URL Open", "PASS", "YES");
		} 
		else 
		{
			testBase.extentReportsStep("CSO URL Open", "FAIL", "YES");
		}
	}

	///////////////////////////////////////////////////////////////////////////////////////////////


	public void csoLogin() throws Exception
	{

		String strUsername = "custpmauto";
		String strPassword = "welcome123";

		/*this.testBase.driver.findElement(By.id("username")).sendKeys(strUsername);
		this.testBase.driver.findElement(By.id("password")).sendKeys(strPassword);

		Select domain = new Select(this.testBase.driver.findElement(By.id("domainList")));
		domain.selectByVisibleText("Century");

		Thread.sleep(5000);

		this.testBase.driver.findElement(By.xpath("//input[@id='logButton']")).click();

		Thread.sleep(5000);*/
		
		csoLoginUsername.sendKeys(strUsername);
		csoLoginPassword.sendKeys(strPassword);
		
		Select domain = new Select(csoLoginDomain);
		domain.selectByVisibleText("Century");
		
		csoLoginButton.click();

		String ExpectedTitle = "Service Orchestrator";
		String ActualTitle = this.testBase.driver.getTitle();

		if (ActualTitle.contentEquals(ExpectedTitle))
		{
			testBase.extentReportsStep("CSO Login", "PASS", "YES");
		}
		else
		{
			testBase.extentReportsStep("CSO Login", "FAIL", "YES");
		}

		Thread.sleep(10000);
		
	}
	
	
	

}

