package testPackage1;

import org.testng.annotations.Test;

import csoPages.CsoLoginPage;
import frameworkPackage.TestBase;

public class VerifyCsoLogin extends TestBase
{

	//Constructor to assign the class name to global variable
	public VerifyCsoLogin()
	{
		testName_javaClass = this.getClass().getSimpleName();
	}
	
	
	// Test Methods
	
	@Test(priority=1)
	public void openURL_CSO() throws Exception
	{
		new CsoLoginPage(driver, testdataHashMap, eTest).openURL_CSO();
	}

	@Test(priority=2)
	public void csoLogin() throws Exception
	{
		new CsoLoginPage(driver, testdataHashMap, eTest).csoLogin();
	}
	
	


}
