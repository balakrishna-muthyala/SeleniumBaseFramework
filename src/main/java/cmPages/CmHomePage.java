package cmPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import frameworkPackage.FrameworkBase;
import frameworkPackage.UtilityClass;

public class CmHomePage extends FrameworkBase{


	@FindBy(id="username")
	WebElement loginUsername; 

	@FindBy(id="password")
	WebElement loginPassword; 

	@FindBy(id="domainList")
	WebElement loginDomain; 

	@FindBy(id="logButton")
	WebElement loginButton;

	@FindBy(linkText="Signout")
	WebElement logout; 

	
	public CmHomePage(FrameworkBase fBase){
		this.fBase = fBase;
		PageFactory.initElements(fBase.driver, this);
	}

	
	public void openURL_CM() throws Exception
	{
		UtilityClass util = new UtilityClass(fBase);
		
		fBase.driver.get(cmURL);
		Thread.sleep(2000);
		
		boolean pageExist = util.waitForPageLoad("Login Page",5000);

		if (pageExist)
		{
			fBase.extentReportsStep("CM URL Open", "PASS", "YES");
		} 
		else 
		{
			fBase.extentReportsStep("CM URL Open", "FAIL", "YES");
		}

	}



	public void cmLogin() throws Exception 
	{
		UtilityClass util = new UtilityClass(fBase);
		
		loginUsername.sendKeys(strUsername);
		loginPassword.sendKeys(strPassword);

		Select domain = new Select(loginDomain);
		domain.selectByVisibleText("Century");

		loginButton.click();
		Thread.sleep(1000);

		boolean pageExist = util.waitForPageLoad("Customer Manager",5000);

		if (pageExist)
		{
			fBase.extentReportsStep("CM Login", "PASS", "YES");
		}
		else
		{
			fBase.extentReportsStep("CM ULogin", "FAIL", "YES");
		}


	}


	public void cmLogout() throws Exception
	{
		fBase.driver.switchTo().defaultContent();

		//driver.findElement(By.linkText("Signout")).click(); 
		//driver.findElement(By.xpath("//ul[@id='RightSubTab']/../ul/li[3]/a")).click();		
		logout.click();

		String ExpectedTitle3 = "Login Page";
		String ActualTitle3 = fBase.driver.getTitle();

		if (ActualTitle3.contentEquals(ExpectedTitle3))
		{ 
			fBase.extentReportsStep("CM Logout", "PASS", "YES");
		}
		else 
		{ 
			fBase.extentReportsStep("CM Logout", "FAIL", "YES");
		}

	}



}
