package frameworkPackage;

import java.util.HashMap;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class Variables {

	//Project Name
	public static String projectName = "SeleniumBaseFramework";
	
	//Project Location
	public static String projectLocation = System.getProperty("user.dir");
	
	//Test Reports Path
	public String executionReportsFolder = "C:\\SELENIUM_ExecutionReports";
	public String jenkinsWorkspacePath = "C:\\JenkinsWorkspace";

	//Test inputs path
	protected static String testDataPath = projectLocation+"\\TestResources\\TestData.xlsx";
	protected static String propertiesFilePath = projectLocation+"\\TestResources\\PropertiesFile";

	public static String ieDriverPath = projectLocation+"\\TestResources\\WebDriverFiles\\IEDriverServer.exe";
	public static String chromeDriverPath = projectLocation+"\\TestResources\\WebDriverFiles\\chromedriver.exe";
	public static String firefoxDriverPath = projectLocation+"\\TestResources\\WebDriverFiles\\geckodriver.exe";

	//Config property file variables
	public static String browserName;
	public static String cmURL;
	public static String csoURL;
	public static String strUsername;
	public static String strPassword;

	// Test Script variables
	public WebDriver driver;
	public HashMap<String, String> testdataHashMap;
	public ExtentTest eTest;
	
	public String testcaseName_Testdata;
	public String columnName;
	public String columnValue;
	
	public static XSSFWorkbook wb;
	public static ExtentReports eReports;
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

	public String uploadFilePath = "D:\\new.txt" ;
	
}
