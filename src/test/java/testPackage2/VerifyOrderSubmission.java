package testPackage2;

import org.testng.annotations.Test;

import cmPages.*;
import frameworkPackage.TestBase;

public class VerifyOrderSubmission extends TestBase
{
	
	//Constructor to assign the class name to global variable
	public VerifyOrderSubmission()
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
	public void customerCreation() throws Exception
	{
		new CustomerTab(driver, testdataHashMap, eTest).customerCreation();
	}

	@Test
	public void accountCreation_Service() throws Exception
	{
		new AccountTab(driver, testdataHashMap, eTest).accountCreation_Service();
	}
	
	@Test
	public void accountCreation_PrimaryContact() throws Exception
	{
		new ContactPage(driver, testdataHashMap, eTest).accountCreation_PrimaryContact();
	}
	
	@Test
	public void accountCreation_Billing() throws Exception
	{
		new AccountTab(driver, testdataHashMap, eTest).accountCreation_Billing();
	}
	
	@Test
	public void addressCreation() throws Exception
	{
		new AddressTab(driver, testdataHashMap, eTest).addressCreation();
	}
	
	@Test
	public void address_SiteTechnicalContact() throws Exception
	{
		new ContactPage(driver, testdataHashMap, eTest).address_SiteTechnicalContact();
	}
	
	@Test
	public void serviceConfiguration() throws Exception
	{
		new ServicePage(driver, testdataHashMap, eTest).serviceConfiguration();
	}
	
	@Test
	public void orderSummary() throws Exception
	{
		new ServicePage(driver, testdataHashMap, eTest).orderSummary();
	}
	
	@Test
	public void cmLogout() throws Exception
	{
		new CmHomePage(driver, testdataHashMap, eTest).cmLogout();				
	}
	
}
