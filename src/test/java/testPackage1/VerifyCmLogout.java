package testPackage1;

import org.testng.annotations.Test;

import cmPages.*;
import frameworkPackage.TestBase;

public class VerifyCmLogout extends TestBase
{
	//Constructor to assign the class name to global variable
	public VerifyCmLogout() throws Exception
	{
		testName_javaClass = this.getClass().getSimpleName();
	}

	
	// Test Methods
	
	@Test
	public void cmLogin() throws Exception
	{
		new CmHomePage(driver, testdataHashMap, eTest).cmLogin();				
	}
	
	@Test
	public void cmLogout() throws Exception
	{
		new CmHomePage(driver, testdataHashMap, eTest).cmLogout();				
	}
	

	
}



