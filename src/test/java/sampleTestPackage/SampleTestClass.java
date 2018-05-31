package sampleTestPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import frameworkPackage.FrameworkBase;

public class SampleTestClass extends FrameworkBase
{

	public static void main(String args[])
	{
		
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		String baseUrl = "http://demo.guru99.com/test/upload/";
		WebDriver driver = new ChromeDriver();
		driver.get(baseUrl);
		
        WebElement uploadElement = driver.findElement(By.id("uploadfile_0"));
        uploadElement.sendKeys("D:\\new.txt");

        driver.findElement(By.id("terms")).click();

        // click the "UploadFile" button
        driver.findElement(By.name("send")).click();
				
	}
	
}
