package frameworkPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
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
import com.relevantcodes.extentreports.LogStatus;

public class TestBase extends Variables{

	public TestBase testBase;

	@BeforeSuite
	public void startSuite(ITestContext testContext) 
	{
		String suiteName_xml = testContext.getCurrentXmlTest().getSuite().getName(); // Comes from TestNg xml

		if (suiteName_xml.contentEquals("Default suite")) // If execution starts from Java Classes
		{
			suiteName = testName_javaClass;
		}
		else // If execution starts from testNg xml
		{
			suiteName = suiteName_xml;
		}

		// Execution reports folder
		File executionReportsDir = new File(executionReportsFolder);
		executionReportsDir.mkdir();

		// Execution reports folder
		projectReportFolder = executionReportsFolder + "\\" + projectName;
		File projectReportDir = new File(projectReportFolder);
		projectReportDir.mkdir();

		// Suite report folder with timestamp
		suiteReportFolderWithTimestampFolder = projectReportDir + "\\" + suiteName + "-" + timeStamp();
		File suiteReportFolderWithTimestampDir = new File(suiteReportFolderWithTimestampFolder);
		suiteReportFolderWithTimestampDir.mkdir();

		// Screenshots Folder
		screenshotsReportFolder = suiteReportFolderWithTimestampFolder + "\\" + "Screenshots";
		File ssDir = new File(screenshotsReportFolder);
		ssDir.mkdir();

		// Individual Test report file
		testReportFile = suiteReportFolderWithTimestampFolder + "\\" + "TestExtentReports" + ".html";

		// Extent Report File
		eReports = new ExtentReports(testReportFile, true, DisplayOrder.NEWEST_FIRST);

		System.out.println(suiteName + " - TestSuite execution started");
		System.out.println();

	}


	@BeforeTest
	public void startTest(ITestContext testContext) throws Exception 
	{
		String testName_xml = testContext.getName();

		if (testName_xml.contentEquals("Command line test")) // If execution starts from Jenkins
		{
			testName = testName_javaClass;
		} 
		else if (testName_xml.contentEquals("Default test")) // If execution starts from Java Classes
		{
			testName = testName_javaClass;
		} 
		else // If execution starts from testNg xml
		{
			testName = testName_xml;
		}

		// get properties file for entire suite
		readPropertiesFile();

		// get test data for test case
		testdataHashMap = readTestdata(testName);

		tcExist = (testdataHashMap != null);
		if (tcExist == true) {
			System.out.println(testName + " - TestCase execution started");
			System.out.println();
			// Test case report in Extent Reports file
			eTest = eReports.startTest(testName);
			// Initiating driver
			driver = initDriver();
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
		if (testResult.getStatus() == 1) {
			methodStatus = "Passed";
		} else {
			methodStatus = "Failed";
			extentReportsStep(methodName, "FAIL", "YES");
		}
		System.out.println(methodName + " - TestMethod execution - " + methodStatus);
	}


	@AfterTest
	public void endTest() throws IOException, InterruptedException 
	{
		eReports.endTest(eTest);
		// eReports.flush();  // It will generate different Test reports for different Test cases 

		System.out.println("");
		System.out.println(testName + " - TestCase execution completed");

		//driver.close();
	}


	@AfterSuite
	public void endSuite() 
	{
		eReports.flush();  //It will generate single Test report for different Test cases under single test suite

		System.out.println("");
		System.out.println(suiteName + " - TestSuite execution completed");

		// If execution starts from Jenkins, "jenkinsBuildReportFileLocation" folder will be created automatically
		String jenkinsBuildReportFile = jenkinsWorkspacePath + "\\" + projectName + "-" + suiteName + "\\" + "\\" + "TestExtentReports.html";
		File extentReportFile = new File(testReportFile);
		File jenkinsBuildReportFileLocation = new File(jenkinsBuildReportFile);
		try {
			Files.copy(extentReportFile, jenkinsBuildReportFileLocation);
		} catch (IOException e) {
			System.err.println("Jenkins build workspace location is not identified");
		}

		/*
		 * WindowsUtils.killByName("IEDriverServer.exe");
		 * WindowsUtils.killByName("chromedriver.exe");
		 * WindowsUtils.killByName("geckodriver.exe");
		 */

	}


	/************************************************************************************************************************/


	// Driver Initialization 
	@SuppressWarnings("deprecation")
	public WebDriver initDriver() throws InterruptedException, IOException 
	{
		if (browserName.contains("Firefox")) 
		{
			System.setProperty("webdriver.gecko.driver", firefoxDriverPath);
			driver = new FirefoxDriver();
		} 
		else if (browserName.contains("Chrome")) 
		{
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
			driver = new ChromeDriver();
		} 
		else if (browserName.contains("IE")) 
		{			
			/* System.setProperty("webdriver.ie.driver",ieDriverPath); 
			 driver = new InternetExplorerDriver();*/			 

			System.setProperty("webdriver.ie.driver", ieDriverPath);
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capabilities.setCapability("requireWindowFocus", true);
			driver = new InternetExplorerDriver(capabilities);
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		// driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.SECONDS);

		return driver;

	}


	// Read the Test data from the Data sheet and store it in Hash Map 
	public HashMap<String, String> readTestdata(String testName) throws Exception 
	{
		testdataHashMap = new HashMap<String, String>();

		try 
		{
			File dataSource = new File(testDataPath);
			FileInputStream fis = new FileInputStream(dataSource);
			wb = new XSSFWorkbook(fis);
			XSSFSheet ws = wb.getSheetAt(0);

			int noOfRows = ws.getLastRowNum();
			// noOfRows = noOfRows+1;
			for (int i = 1; i <= noOfRows; i++) {
				testcaseName_Testdata = ws.getRow(i).getCell(0).getStringCellValue();
				if (testcaseName_Testdata.equalsIgnoreCase(testName)) {
					int noOfColumns = ws.getRow(i).getLastCellNum();

					for (int j = 0; j < noOfColumns; j++) {
						String columnName = ws.getRow(0).getCell(j).getStringCellValue();
						String cellValue = ws.getRow(i).getCell(j).getStringCellValue();
						testdataHashMap.put(columnName, cellValue);
					}

					break;
				}
			}

			int testdataColumns = testdataHashMap.size();
			if (testdataColumns != 0) 
			{
				System.out.println("TestCase name exist in data sheet");
			} 
			else 
			{
				System.out.println("TestCase name does not exist in data sheet");
			}

		} 

		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return testdataHashMap;

	}


	// Get the Test data based on sheet column name from the Hash Map 
	public String getTestdata(String columnName) 
	{
		String columnValue = testdataHashMap.get(columnName);
		return columnValue;
	}

	
	// Read and store the data from Properties file
	public void readPropertiesFile() 
	{
		try 
		{
			Properties prop = new Properties();
			FileInputStream input = new FileInputStream(propertiesFilePath);
			prop.load(input);

			browserName = prop.getProperty("browserName");
			cmURL = prop.getProperty("cmURL");
			csoURL = prop.getProperty("csoURL");
			strUsername = prop.getProperty("strUsername");
			strPassword = prop.getProperty("strPassword");
		} 

		catch (Exception e) 
		{
			e.printStackTrace();
		}

	}


	// Take the Screenshot and place it in the specified path
	public String captureScreen(String stepName) throws IOException, InterruptedException 
	{
		String screenshotFile = null;
		try 
		{
			TakesScreenshot screen = (TakesScreenshot) driver;
			File tempFile = screen.getScreenshotAs(OutputType.FILE);
			screenshotFile = screenshotsReportFolder + "\\" + stepName + ".png";
			File targetLocation = new File(screenshotFile);
			Files.copy(tempFile, targetLocation);
			// FileUtils.copyFile(tempLocation, targetLocation);
		} 

		catch (WebDriverException e)
		{
			e.printStackTrace();
		}

		return screenshotFile;
	}

	
	// Each and every Test step report placed in the Extent Reports 
	public void extentReportsStep(String stepName, String stepStatus, String ssRequired) throws IOException, InterruptedException 
	{
		String screenShot;

		if (ssRequired.contentEquals("YES")) 
		{
			screenShot = captureScreen(stepName + "_" + timeStamp());
		} 
		else 
		{
			screenShot = "NA";
		}

		if (stepStatus.contentEquals("PASS")) 
		{
			eTest.log(LogStatus.PASS, stepName, eTest.addScreenCapture(screenShot));
		} 
		else if (stepStatus.contentEquals("FAIL")) 
		{
			eTest.log(LogStatus.FAIL, stepName, eTest.addScreenCapture(screenShot));
		} 
		else if (stepStatus.contentEquals("INFO")) 
		{
			eTest.log(LogStatus.INFO, stepName, eTest.addScreenCapture(screenShot));
		}

	}


	// Get the System full Date & Time 
	public String timeStamp() 
	{
		String timeStamp = new SimpleDateFormat("ddMMyyyy_hhmmssSSS_a").format(new Date());
		return timeStamp;
	}


}
