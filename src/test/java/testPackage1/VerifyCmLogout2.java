package testPackage1;

import org.testng.annotations.Test;

import cmPages.*;
import frameworkPackage.TestBase;

public class VerifyCmLogout2 extends TestBase
{
	//Constructor to assign the class name to global variable
	public VerifyCmLogout2() throws Exception
	{
		testName_javaClass = this.getClass().getSimpleName();
	}

	
	// Test Methods
	
	@Test(priority=1)
	public void cmLogin() throws Exception
	{
		new CmHomePage(driver, testdataHashMap, eTest).cmLogin();				
	}
	
	@Test(priority=2)
	public void cmLogout() throws Exception
	{
		new CmHomePage(driver, testdataHashMap, eTest).cmLogout();				
	}
	

	
}



