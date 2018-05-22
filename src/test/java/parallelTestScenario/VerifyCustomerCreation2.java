package parallelTestScenario;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cmPages.*;
import frameworkPackage.FrameworkBase;

public class VerifyCustomerCreation2 extends FrameworkBase
{

	FrameworkBase fBase = new FrameworkBase();

	//Constructor to assign the class name to global variable
	public VerifyCustomerCreation2()
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
	public void openURL_CM() throws Exception
	{
		new CmHomePage(fBase).openURL_CM();		
	}
	
	@Test(priority=20)
	public void cmLogin() throws Exception
	{
		new CmHomePage(fBase).cmLogin();
	}
	
	@Test(priority=30)
	public void customerCreation() throws Exception
	{
		new CustomerTab(fBase).customerCreation();
	}

	
}
