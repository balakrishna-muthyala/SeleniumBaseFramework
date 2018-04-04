package cmPages;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.ExtentTest;

import frameworkPackage.TestBase;
import frameworkPackage.UtilityClass;

public class CmHomePage extends TestBase{


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


	public CmHomePage(WebDriver driver, HashMap<String, String> testdataHashMap, ExtentTest eTest){
		this.driver = driver;
		this.testdataHashMap = testdataHashMap;
		this.eTest = eTest;
		PageFactory.initElements(driver, this);
	}



	public void openURL_CM() throws Exception
	{
		driver.get(cmURL);

		boolean pageExist = new UtilityClass(driver, testdataHashMap, eTest).waitForPageLoad("Login Page",5000);

		if (pageExist)
		{
			new UtilityClass(driver, testdataHashMap, eTest).extentReportsStep("CM URL Open", "PASS", "YES");
		} 
		else 
		{
			new UtilityClass(driver, testdataHashMap, eTest).extentReportsStep("CM URL Open", "FAIL", "YES");
		}

	}



	public void cmLogin() throws Exception 
	{
		openURL_CM();

		loginUsername.sendKeys(strUsername);
		loginPassword.sendKeys(strPassword);

		Select domain = new Select(loginDomain);
		domain.selectByVisibleText("Century");

		loginButton.click();
		Thread.sleep(1000);

		boolean pageExist = new UtilityClass(driver, testdataHashMap, eTest).waitForPageLoad("Customer Manager",5000);

		if (pageExist)
		{
			new UtilityClass(driver, testdataHashMap, eTest).extentReportsStep("CM Login", "PASS", "YES");
		}
		else
		{
			new UtilityClass(driver, testdataHashMap, eTest).extentReportsStep("CM ULogin", "FAIL", "YES");
		}


	}


	public void cmLogout() throws Exception
	{
		driver.switchTo().defaultContent();

		//driver.findElement(By.linkText("Signout")).click(); 
		//driver.findElement(By.xpath("//ul[@id='RightSubTab']/../ul/li[3]/a")).click();		
		logout.click();

		String ExpectedTitle3 = "Login Page";
		String ActualTitle3 = driver.getTitle();

		if (ActualTitle3.contentEquals(ExpectedTitle3))
		{ 
			new UtilityClass(driver, testdataHashMap, eTest).extentReportsStep("CM Logout", "PASS", "YES");
		}
		else 
		{ 
			new UtilityClass(driver, testdataHashMap, eTest).extentReportsStep("CM Logout", "FAIL", "YES");
		}

	}



}
