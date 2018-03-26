package testPackage2;

import org.testng.annotations.Test;

import cmPages.*;
import frameworkPackage.TestBase;

public class VerifyCmLogin extends TestBase
{
	//Constructor to assign the class name to global variable
	public VerifyCmLogin() throws Exception
	{
		testName_javaClass = this.getClass().getSimpleName();
	}

	
	// Test Methods

	@Test
	public void cmLogin() throws Exception
	{
		new CmHomePage(driver, testdataHashMap, eTest).cmLogin();		
	}
	

	
	/*public void verifyCmLogin() throws Exception
	{
		if(tcExist == true)
		{
			CmLoginPage cmLogin = new CmLoginPage(driver, testdataHashMap, eTest);
			cmLogin.openURL_CM();
			cmLogin.cmLogin();
		}
	}*/


}



