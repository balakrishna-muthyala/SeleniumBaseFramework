package testPackage2;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import csoPages.CsoHomePage;
import frameworkPackage.TestBase;

public class VerifyCsoLogin extends TestBase
{
	public TestBase testBase = new TestBase();

	//Constructor to assign the class name to global variable
	public VerifyCsoLogin()
	{
		testName_javaClass = this.getClass().getSimpleName();
	}
	
	@BeforeClass
	public void beforeTestClass() 
	{
		testBase.driver = driver;
		testBase.testdataHashMap = testdataHashMap;
		testBase.eTest = eTest;
	}
	
	// Test Methods
	
	@Test(priority=10)
	public void openURL_CSO() throws Exception
	{
		new CsoHomePage(testBase).openURL_CSO();
	}

	@Test(priority=20)
	public void csoLogin() throws Exception
	{
		new CsoHomePage(testBase).csoLogin();
	}
	
	


}
