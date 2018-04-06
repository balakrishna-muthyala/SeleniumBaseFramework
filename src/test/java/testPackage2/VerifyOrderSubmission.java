package testPackage2;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cmPages.*;
import frameworkPackage.TestBase;

public class VerifyOrderSubmission extends TestBase
{

	public TestBase testBase = new TestBase();

	//Constructor to assign the class name to global variable
	public VerifyOrderSubmission()
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
	public void openURL_CM() throws Exception
	{
		new CmHomePage(testBase).openURL_CM();		
	}
	
	@Test(priority=20)
	public void cmLogin() throws Exception
	{
		new CmHomePage(testBase).cmLogin();
	}
	
	@Test(priority=30)
	public void customerCreation() throws Exception
	{
		new CustomerTab(testBase).customerCreation();
	}

	@Test(priority=40)
	public void accountCreation_Service() throws Exception
	{
		new AccountTab(testBase).accountCreation_Service();
	}
	
	@Test(priority=50)
	public void accountCreation_PrimaryContact() throws Exception
	{
		new ContactPage(testBase).accountCreation_PrimaryContact();
	}
	
	@Test(priority=60)
	public void accountCreation_Billing() throws Exception
	{
		new AccountTab(testBase).accountCreation_Billing();
	}
	
	@Test(priority=70)
	public void addressCreation() throws Exception
	{
		new AddressTab(testBase).addressCreation();
	}
	
	@Test(priority=80)
	public void address_SiteTechnicalContact() throws Exception
	{
		new ContactPage(testBase).address_SiteTechnicalContact();
	}
	
	@Test(priority=90)
	public void serviceConfiguration() throws Exception
	{
		new ServicePage(testBase).serviceConfiguration();
	}
	
	@Test(priority=100)
	public void orderSummary() throws Exception
	{
		new ServicePage(testBase).orderSummary();
	}
	
	@Test(priority=110)
	public void cmLogout() throws Exception
	{
		new CmHomePage(testBase).cmLogout();				
	}
	
}

