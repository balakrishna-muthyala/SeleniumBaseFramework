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
	public String jenkinsWorkspacePath = "C:\\Users\\balakrishna.m\\.jenkins\\workspace";

	//Test inputs path
	protected static String testDataPath = projectLocation+"\\src\\main\\resources\\TestData.xlsx";
	protected static String propertiesFilePath = projectLocation+"\\src\\main\\resources\\PropertiesFile";

	public static String ieDriverPath = projectLocation+"\\src\\main\\resources\\WebDriverFiles\\IEDriverServer.exe";
	public static String chromeDriverPath = projectLocation+"\\src\\main\\resources\\WebDriverFiles\\chromedriver.exe";
	public static String firefoxDriverPath = projectLocation+"\\src\\main\\resources\\WebDriverFiles\\geckodriver.exe";

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

	
	
}
