package csoPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import frameworkPackage.FrameworkBase;

public class CsoHomePage extends FrameworkBase
{
	
	@FindBy(id="username")
	WebElement csoLoginUsername; 
	
	@FindBy(id="password")
	WebElement csoLoginPassword; 
	
	@FindBy(id="domainList")
	WebElement csoLoginDomain; 
	
	@FindBy(xpath="//input[@id='logButton']")
	WebElement csoLoginButton; 
	
	
	public CsoHomePage(FrameworkBase fBase) 
	{
		this.fBase = fBase;
		PageFactory.initElements(fBase.driver, this);
	}


	public void openURL_CSO() throws Exception
	{

		//String csoURL = "https://sit-hydra.excelacom.in/OM";
		fBase.driver.get(csoURL);

		Thread.sleep(2000);

		String ExpectedTitle = "Login Page";
		String ActualTitle = this.fBase.driver.getTitle();

		if (ActualTitle.contentEquals(ExpectedTitle))
		{
			fBase.extentReportsStep("CSO URL Open", "PASS", "YES");
		} 
		else 
		{
			fBase.extentReportsStep("CSO URL Open", "FAIL", "YES");
		}
	}

	///////////////////////////////////////////////////////////////////////////////////////////////


	public void csoLogin() throws Exception
	{

		String strUsername = "custpmauto";
		String strPassword = "welcome123";

		/*this.fBase.driver.findElement(By.id("username")).sendKeys(strUsername);
		this.fBase.driver.findElement(By.id("password")).sendKeys(strPassword);

		Select domain = new Select(this.fBase.driver.findElement(By.id("domainList")));
		domain.selectByVisibleText("Century");

		Thread.sleep(5000);

		this.fBase.driver.findElement(By.xpath("//input[@id='logButton']")).click();

		Thread.sleep(5000);*/
		
		csoLoginUsername.sendKeys(strUsername);
		csoLoginPassword.sendKeys(strPassword);
		
		Select domain = new Select(csoLoginDomain);
		domain.selectByVisibleText("Century");
		
		csoLoginButton.click();

		String ExpectedTitle = "Service Orchestrator";
		String ActualTitle = this.fBase.driver.getTitle();

		if (ActualTitle.contentEquals(ExpectedTitle))
		{
			fBase.extentReportsStep("CSO Login", "PASS", "YES");
		}
		else
		{
			fBase.extentReportsStep("CSO Login", "FAIL", "YES");
		}

		Thread.sleep(10000);
		
	}
	
	
	

}

