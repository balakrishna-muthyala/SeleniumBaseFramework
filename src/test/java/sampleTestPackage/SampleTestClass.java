package sampleTestPackage;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import frameworkPackage.FrameworkBase;

public class SampleTestClass extends FrameworkBase
{

	public static void main(String args[]) throws InterruptedException
	{
		/*
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);		
		WebDriver driver = new ChromeDriver();
		
		String baseUrl = "http://demo.guru99.com/test/upload/";
		driver.get(baseUrl);
		
		//Files direct uploading without using AutoIt or Robot classes
        WebElement uploadElement = driver.findElement(By.id("uploadfile_0"));
        uploadElement.sendKeys("D:\\new.txt");
		Thread.sleep(2000);
		
        driver.findElement(By.id("terms")).click();
		Thread.sleep(2000);
		
        // click the "UploadFile" button
        driver.findElement(By.name("send")).click();
		*/		
		
		
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);		
		WebDriver driver = new ChromeDriver();
		
		String baseUrl = "https://files.fm/";
		driver.get(baseUrl);
		Thread.sleep(2000);
		
		//Files uploading without using AutoIt or Robot classes by using the direct upload link in the application
        WebElement uploadElement = driver.findElement(By.xpath("//input[@id='file_upload']/following-sibling::input"));
        uploadElement.sendKeys("D:\\new.txt");
        Thread.sleep(2000);
                       
        driver.findElement(By.xpath("//div[contains(text(),'Start file upload')]")).click();
        Thread.sleep(2000);

	}
	
}


