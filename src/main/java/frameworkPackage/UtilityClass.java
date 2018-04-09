package frameworkPackage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;

public class UtilityClass extends FrameworkBase {

	public UtilityClass(FrameworkBase fBase) 
	{
		this.fBase = fBase;
	}
	

	// Generate Random Number based on Time stamp for the specified String length (max length is 17)
	public String generateRandomNumber(int numberLength) 
	{
		int beginIndex = 0;
		int endIndex = numberLength;
		String strTimeStamp = new SimpleDateFormat("SSSssmmhhddMMyyyy").format(new Date());
		String randomNumber = strTimeStamp.substring(beginIndex, endIndex);
		return randomNumber;
	}


	// Test wait for the expected web element on the page
	public boolean waitForElement(String locatorType, String locatorValue, int waitTimeInMillisec) throws InterruptedException, IOException 
	{
		boolean elementExist = false;
		int waitIncrement = 0;

		try 
		{
			if (locatorType.contains("id")) 
			{
				elementExist = fBase.driver.findElement(By.id(locatorValue)).isDisplayed();
			} 
			else if (locatorType.contains("name")) 
			{
				elementExist = fBase.driver.findElement(By.name(locatorValue)).isDisplayed();
			}
			else if (locatorType.contains("xpath")) 
			{
				elementExist = fBase.driver.findElement(By.xpath(locatorValue)).isDisplayed();
			}
		} 
		catch (Exception e) 
		{
			Thread.sleep(1000);
			waitIncrement = 1000;
		}

		while (elementExist==false && waitIncrement <= waitTimeInMillisec) 
		{
			try 
			{
				if (locatorType.contains("id")) 
				{
					elementExist = fBase.driver.findElement(By.id(locatorValue)).isDisplayed();
				} 
				else if (locatorType.contains("name")) 
				{
					elementExist = fBase.driver.findElement(By.name(locatorValue)).isDisplayed();
				}
				else if (locatorType.contains("xpath")) 
				{
					elementExist = fBase.driver.findElement(By.xpath(locatorValue)).isDisplayed();
				}
				else
				{
					waitIncrement = waitTimeInMillisec+1;
					System.out.println("Locator Type is not handled");
				}
			} 
			catch (Exception e) 
			{
				Thread.sleep(1000);
				waitIncrement = waitIncrement + 1000;
			}
		}

		if (!elementExist) {
			System.out.println("Element not exist - " + locatorValue.toString());
			fBase.extentReportsStep("Element not exist - " + locatorValue.toString(), "INFO", "YES");
		}

		return elementExist;

	}

	
	// Test wait for the expected page up to specified time
	public boolean waitForPageLoad(String ExpectedPageTitle, int waitTimeInMillisec) throws InterruptedException, IOException 
	{
		boolean pageExist = false;
		int waitIncrement = 0;
		String ActualPageTitle = "";

		try
		{
			ActualPageTitle = fBase.driver.getTitle();
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
		catch (Exception e) 
		{
		}

		while (!pageExist && waitIncrement <= waitTimeInMillisec) 
		{
			ActualPageTitle = fBase.driver.getTitle();
			if (ActualPageTitle.contentEquals(ExpectedPageTitle)) 
			{
				pageExist = true;
			} 
			else 
			{
				Thread.sleep(1000);
				waitIncrement = waitIncrement + 1000;
			}
		}

		if (!pageExist) 
		{
			System.out.println("Page not loaded - " + ExpectedPageTitle);
			fBase.extentReportsStep("Page not loaded - " + ExpectedPageTitle, "INFO", "YES");
		}

		return pageExist;

	}


}



