package frameworkPackage;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.google.common.io.Files;
import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class TestBase 
{	
public TestBase tb;
	//Project Name
	public static String projectName = "SeleniumBaseFramework";
	public static String jenkinsWorkspacePath = "C:\\Users\\balakrishna.m\\.jenkins\\workspace\\";
			
	//Project Location
	public static String projectLocation = System.getProperty("user.dir");

	// Paths
	protected static String testDataPath = projectLocation+"\\src\\main\\resources\\TestData.xlsx";
	protected static String propertiesFilePath = projectLocation+"\\src\\main\\resources\\PropertiesFile";

	public static String ieDriverPath = projectLocation+"\\src\\main\\resources\\WebDriverFiles\\IEDriverServer.exe";
	public static String chromeDriverPath = projectLocation+"\\src\\main\\resources\\WebDriverFiles\\chromedriver.exe";
	public static String firefoxDriverPath = projectLocation+"\\src\\main\\resources\\WebDriverFiles\\geckodriver.exe";

	public String executionReportsFolder = "C:\\SELENIUM_ExecutionReports";
	
	//public static String screenshotReportFolderPath ="D:\\SELENIUM\\Automation Test Reports\\Screenshots\\";
	//public static ExtentReports eReport = new ExtentReports("D:\\SELENIUM\\Automation Test Reports\\ExtentReports.html", true);

	
	//Config property file variables
	public static String browserName;
	public static String cmURL;
	public static String csoURL;
	public static String strUsername;
	public static String strPassword;


	// Test data variables
	public String testcaseName_Testdata;
	public String columnName;
	public String columnValue;


	// Test Script variables
	public HashMap<String, String> testdataHashMap;
	public static XSSFWorkbook wb;
	public static ExtentReports eReports;
	public ExtentTest eTest;
	public WebDriver driver;
	public String testName_javaClass;
	public boolean tcExist;
	public String suiteName;
	public String testName;
	public String methodName;
	public String suiteStatus;
	public String testStatus;
	public String methodStatus;

	public String projectReportFolder;
	public String suiteReportFolderWithTimestampFolder;
	public static String screenshotsReportFolder;
	public String testReportFile;
	

	@BeforeSuite
	public void startSuite(ITestContext testContext)
	{

		UtilityClass util = new UtilityClass();

		String suiteName_xml = testContext.getCurrentXmlTest().getSuite().getName();  //Comes from TestNg xml

		
		if (suiteName_xml.contentEquals("Default suite"))		// If execution starts from Java Classes
		{
			suiteName = testName_javaClass;
		}
		/*else if (suiteName_xml.contentEquals("Command line suite"))		// If execution starts from Jenkins without configuring build xml
		{
			suiteName = testName_javaClass;
		}*/
		else		// If execution starts from testNg xml
		{
			suiteName = suiteName_xml;
		}


		//Execution reports folder
		File executionReportsDir = new File(executionReportsFolder);
		executionReportsDir.mkdir();

		//Execution reports folder
		projectReportFolder = executionReportsFolder+"\\"+projectName;
		File projectReportDir = new File(projectReportFolder);
		projectReportDir.mkdir();
		
		//Suite report folder with timestamp
		suiteReportFolderWithTimestampFolder = projectReportDir+"\\"+suiteName+"-"+util.timeStamp();
		File suiteReportFolderWithTimestampDir = new File(suiteReportFolderWithTimestampFolder);
		suiteReportFolderWithTimestampDir.mkdir();
		
		//Screenshots Folder
		screenshotsReportFolder = suiteReportFolderWithTimestampFolder+"\\"+"Screenshots";
		File ssDir = new File(screenshotsReportFolder);
		ssDir.mkdir();
				
		//Individual Test report file
		testReportFile = suiteReportFolderWithTimestampFolder+"\\"+"TestExtentReports"+".html";

		
		//Extent Report File
		eReports = new ExtentReports(testReportFile, true, DisplayOrder.NEWEST_FIRST);

		System.out.println(suiteName + " - TestSuite execution started");
		System.out.println();

	}
	

	@BeforeTest
	public void startTest(ITestContext testContext) throws Exception
	{	

		UtilityClass util = new UtilityClass();

		String testName_xml = testContext.getName();

		if (testName_xml.contentEquals("Command line test"))		// If execution starts from Jenkins
		{
			testName = testName_javaClass;;	
		}
		else if (testName_xml.contentEquals("Default test"))		// If execution starts from Java Classes
		{
			testName = testName_javaClass;;	
		}
		else		// If execution starts from testNg xml
		{
			testName = testName_xml;
		}

		// get properties file for entire suite
		util.readPropertiesFile();

		// get test data for test case
		testdataHashMap = util.readTestdata(testName);

		tcExist = (testdataHashMap != null);
		if(tcExist == true)
		{
			System.out.println(testName + " - TestCase execution started");
			System.out.println();
			//Test case report in Extent Reports file
			eTest = eReports.startTest(testName);
			//Initiating driver
			driver = util.initDriver();
		}

	}



	@BeforeMethod
	public void startMethod(Method testMethod)
	{
		methodName = testMethod.getName();
	}


	@AfterMethod
	public void endMethod(ITestResult testResult) throws IOException, InterruptedException
	{		
		if(testResult.getStatus() == 1)
		{
			methodStatus = "Passed";			
		}
		else
		{
			methodStatus = "Failed";
			new UtilityClass(driver, testdataHashMap, eTest).extentReportsStep(methodName, "FAIL", "YES");
		}
		System.out.println(methodName +" - TestMethod execution - " +methodStatus);
	}



	@AfterTest
	public void endTest() throws IOException, InterruptedException
	{
		/*if(methodStatus == "Passed")			
		{
			new UtilityClass(driver, testdataHashMap, eTest).extentReportsStep("TestCase Execution - Final Screen", "PASS", "YES");
		}
		else
		{
			new UtilityClass(driver, testdataHashMap, eTest).extentReportsStep("TestCase Execution - Final Screen", "FAIL", "YES");
		}*/

		eReports.endTest(eTest);
		
		//eReports.flush();

		//driver.close();	

		System.out.println("");
		System.out.println(testName + " - TestCase execution completed");

	}

	
	@AfterSuite
	public void endSuite() 
	{
		eReports.flush();
		
		System.out.println("");
		System.out.println(suiteName + " - TestSuite execution completed");		
		
		// If execution starts from Jenkins
		String jenkinsWorkspaceReportFile = jenkinsWorkspacePath+projectName+"-"+suiteName+"\\"+"TestExtentReports.html";
		File extentReportFile = new File(testReportFile);
		File jenkinsWorkspaceLocation = new File(jenkinsWorkspaceReportFile);
		try {
			Files.copy(extentReportFile, jenkinsWorkspaceLocation);
		} catch (IOException e) {
			System.err.println("Jenkins workspace location is not identified");			
		}	
		
		/*WindowsUtils.killByName("IEDriverServer.exe");
		WindowsUtils.killByName("chromedriver.exe");
		WindowsUtils.killByName("geckodriver.exe");*/
		
	}


}
