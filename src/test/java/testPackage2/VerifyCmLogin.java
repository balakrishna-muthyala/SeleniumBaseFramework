package testPackage2;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cmPages.*;
import frameworkPackage.FrameworkBase;

public class VerifyCmLogin extends FrameworkBase
{
	public FrameworkBase fBase = new FrameworkBase();
	
	//Constructor to assign the class name to global variable
	public VerifyCmLogin() throws Exception
	{
		testName_javaClass = this.getClass().getSimpleName();
	}

	@BeforeClass
	public void beforeTestClass() 
	{
		fBase.driver = driver;
		fBase.testdataHashMap = testdataHashMap;
		fBase.eTest = eTest;
	}
	
	
	// Test Methods

	@Test(priority=10)
	public void openURL_CM() throws Exception
	{
		new CmHomePage(fBase).openURL_CM();		
	}
	
	@Test(priority=20)
	public void cmLogin() throws Exception
	{
		new CmHomePage(fBase).cmLogin();		
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



