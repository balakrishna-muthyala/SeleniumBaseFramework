package sanityTestCases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import csoPages.CsoHomePage;
import frameworkPackage.FrameworkBase;

public class VerifyCsoLogin extends FrameworkBase
{
	FrameworkBase fBase = new FrameworkBase();

	//Constructor to assign the class name to global variable
	public VerifyCsoLogin()
	{
		testName_javaClass = this.getClass().getSimpleName();
	}
	
	//Assigning Input, Output, Test data to entire test execution 
	@BeforeClass
	public void beforeTestClass() 
	{
		fBase.driver = driver;
		fBase.testdataHashMap = testdataHashMap;
		fBase.eTest = eTest;
	}
	
	// Test Methods
	
	@Test(priority=10)
	public void openURL_CSO() throws Exception
	{
		new CsoHomePage(fBase).openURL_CSO();
	}

	@Test(priority=20)
	public void csoLogin() throws Exception
	{
		new CsoHomePage(fBase).csoLogin();
	}
	
	


}
