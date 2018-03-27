package frameworkPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;

import com.google.common.io.Files;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class UtilityClass extends TestBase{

	public UtilityClass() {
	}
	
	public UtilityClass(WebDriver driver, HashMap<String, String> testdataHashMap, ExtentTest eTest) {
		this.driver = driver;
		this.testdataHashMap = testdataHashMap;
		this.eTest = eTest;
		PageFactory.initElements(driver, this);
	}
	
	
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
			/*System.setProperty("webdriver.ie.driver",ieDriverPath); 
			driver = new InternetExplorerDriver();*/
			
			System.setProperty("webdriver.ie.driver",ieDriverPath); 
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capabilities.setCapability("requireWindowFocus", true);
			driver = new InternetExplorerDriver(capabilities);
		}

		driver.manage().window().maximize();		
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		//driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.SECONDS);

		
		return driver;

	}


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


	public HashMap<String,String> readTestdata(String testName) throws Exception 
	{
		testdataHashMap = new HashMap<String, String>();
		
		try {

			File dataSource = new File(testDataPath);
			FileInputStream fis = new FileInputStream(dataSource);
			wb = new XSSFWorkbook(fis);
			XSSFSheet ws = wb.getSheetAt(0);
			
			int noOfRows = ws.getLastRowNum();
			//noOfRows = noOfRows+1;
			for(int i=1; i<=noOfRows; i++)
			{	
				testcaseName_Testdata = ws.getRow(i).getCell(0).getStringCellValue();
				if(testcaseName_Testdata.equalsIgnoreCase(testName))
				{
					int noOfColumns = ws.getRow(i).getLastCellNum();
					
					for (int j=0; j<noOfColumns; j++)
					{
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
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return testdataHashMap;

	}


	public String getTestdata(String columnName)
	{
		String columnValue= testdataHashMap.get(columnName);
		return columnValue;
	}
	

	
	public String captureScreen(String stepName) throws IOException, InterruptedException
	{
		String screenshotFile = null;
		try 
		{			
			TakesScreenshot screen = (TakesScreenshot) driver;
			File tempFile = screen.getScreenshotAs(OutputType.FILE);
			screenshotFile = screenshotsReportFolder+"\\"+stepName+".png";
			File targetLocation = new File(screenshotFile);
			Files.copy(tempFile, targetLocation);	
			//FileUtils.copyFile(tempLocation, targetLocation);	
		} 
		catch (WebDriverException e) 
		{
			e.printStackTrace();
		}
		return screenshotFile;
	}



	public void extentReportsStep(String stepName, String stepStatus, String ssRequired) throws IOException, InterruptedException
	{
		String screenShot;

		if(ssRequired.contentEquals("YES"))
		{
			screenShot = captureScreen(stepName+"_"+timeStamp());
		}
		else
		{
			screenShot ="NA";
		}

		if(stepStatus.contentEquals("PASS"))
		{				
			eTest.log(LogStatus.PASS, stepName, eTest.addScreenCapture(screenShot));
		}
		else if(stepStatus.contentEquals("FAIL"))
		{
			eTest.log(LogStatus.FAIL, stepName, eTest.addScreenCapture(screenShot));
		}
		else if(stepStatus.contentEquals("INFO"))
		{
			eTest.log(LogStatus.INFO, stepName, eTest.addScreenCapture(screenShot));
		}

	}


	
	public String timeStamp()
	{
		String timeStamp = new SimpleDateFormat("ddMMyyyy_hhmmssSSS_a").format(new Date());
		return timeStamp;
	}



	public String generateRandomNumber(int numberLength)
	{
		int beginIndex = 0;
		int endIndex = numberLength;
		String strTimeStamp = new SimpleDateFormat("SSSssmmhhddMMyyyy").format(new Date());
		String randomNumber = strTimeStamp.substring(beginIndex, endIndex);
		return randomNumber;
		
		/*String strTimeStamp = new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date());
		int beginIndex = strTimeStamp.length() - numberLength;
		int endIndex = strTimeStamp.length();
		String randomNumber = strTimeStamp.substring(beginIndex, endIndex);
		return randomNumber;*/
		
	}

	

	public boolean waitForElement(String locatorName, String locatorValue, int waitTimeInMillisec) throws InterruptedException, IOException
	{
		
		boolean elementExist = false;
		int waitIncrement = 0;
		
		try 
		{
			if(locatorName.contains("id"))
			{
				elementExist = driver.findElement(By.id(locatorValue)).isDisplayed();
			}
			else if(locatorName.contains("xpath"))
			{
				elementExist = driver.findElement(By.xpath(locatorValue)).isDisplayed();
			}
		} 
		catch (Exception e) 
		{
			Thread.sleep(1000);
			waitIncrement = 1000;
		}
		
		while( ! elementExist && waitIncrement <= waitTimeInMillisec)
		{
			try 
			{
				if(locatorName.contains("id"))
				{
					elementExist = driver.findElement(By.id(locatorValue)).isDisplayed();
				}
				else if(locatorName.contains("xpath"))
				{
					elementExist = driver.findElement(By.xpath(locatorValue)).isDisplayed();
				}								
			} 
			catch (Exception e) 
			{
				Thread.sleep(1000);
				waitIncrement = waitIncrement+1000;
			}
		}
		
		if(!elementExist)
		{
			System.out.println("Element not exist - "+ locatorValue.toString());
			new UtilityClass(driver, testdataHashMap, eTest).extentReportsStep("Element not exist - "+ locatorValue.toString(), "FAIL", "YES");
		}
		
		return elementExist;
				
	}


	
	public boolean waitForPageLoad(String ExpectedPageTitle, int waitTimeInMillisec) throws InterruptedException, IOException
	{
		
		boolean pageExist = false;
		int waitIncrement = 0;		
		String ActualPageTitle = "";
		
		try {
			ActualPageTitle = driver.getTitle();
			if (ActualPageTitle.contentEquals(ExpectedPageTitle))
			{
				pageExist = true;				
			}
			else
			{
				Thread.sleep(1000);
				waitIncrement = 1000;
			}
		}
		catch(Exception e)
		{			
		}
		
		while( ! pageExist && waitIncrement <= waitTimeInMillisec)
		{
			ActualPageTitle = driver.getTitle();
			if (ActualPageTitle.contentEquals(ExpectedPageTitle))
			{
				pageExist = true;				
			}
			else
			{
				Thread.sleep(1000);
				waitIncrement = waitIncrement+1000;
			}
		}
		
		if(!pageExist)
		{
			System.out.println("Page not loaded - "+ ExpectedPageTitle);
			new UtilityClass(driver, testdataHashMap, eTest).extentReportsStep("Page not loaded - "+ ExpectedPageTitle, "FAIL", "YES");
		}
		
		return pageExist;
					
	}

	
	
	

}
