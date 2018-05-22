package centuryOrderCreation;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cmPages.*;
import frameworkPackage.FrameworkBase;

public class VerifyOrderSubmission extends FrameworkBase
{

	FrameworkBase fBase = new FrameworkBase();

	//Constructor to assign the class name to global variable
	public VerifyOrderSubmission()
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

	@Test(priority=40)
	public void accountCreation_Service() throws Exception
	{
		new AccountTab(fBase).accountCreation_Service();
	}
	
	@Test(priority=50)
	public void accountCreation_PrimaryContact() throws Exception
	{
		new ContactPage(fBase).accountCreation_PrimaryContact();
	}
	
	@Test(priority=60)
	public void accountCreation_Billing() throws Exception
	{
		new AccountTab(fBase).accountCreation_Billing();
	}
	
	@Test(priority=70)
	public void addressCreation() throws Exception
	{
		new AddressTab(fBase).addressCreation();
	}
	
	@Test(priority=80)
	public void address_SiteTechnicalContact() throws Exception
	{
		new ContactPage(fBase).address_SiteTechnicalContact();
	}
	
	@Test(priority=90)
	public void serviceConfiguration() throws Exception
	{
		new ServicePage(fBase).serviceConfiguration();
	}
	
	@Test(priority=100)
	public void orderSummary() throws Exception
	{
		new ServicePage(fBase).orderSummary();
	}
	
	@Test(priority=110)
	public void cmLogout() throws Exception
	{
		new CmHomePage(fBase).cmLogout();				
	}
	
}

