package cmPages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import frameworkPackage.FrameworkBase;

public class Attachments extends FrameworkBase {

	public Attachments(FrameworkBase fBase){
		this.fBase = fBase;
		PageFactory.initElements(fBase.driver, this);
	}

	public void centuryAttachments() throws Exception
	{
		try
		{
			clickOnAttachmentsLink();
			clickOnBrowse();
			fileUpload(uploadFilePath);
			clickOnAddAttachment();
			WebElement attachmedRows = fBase.driver.findElement(By.xpath("//table[@class='x-grid-table x-grid-table-resizer']/tbody/tr[2]"));
			if(attachmedRows.isDisplayed())
				fBase.extentReportsStep("Attachments Uploading and Adding", "PASS", "YES");							
		}		
		catch(Exception e)
		{
			System.out.println("Attachments Failed");
			fBase.extentReportsStep("Attachments Uploading", "FAIL", "YES");
		}
	}


	public void clickOnAttachmentsLink() throws InterruptedException 
	{
		fBase.driver.findElement(By.xpath("//img[@role='presentation'][@id='manageattach-toolEl']")).click();
		Thread.sleep(2000);
	}


	public void clickOnBrowse() throws InterruptedException 
	{
		fBase.driver.switchTo().frame("notesframe");

		//Click and Release Browse button
		WebElement obj = fBase.driver.findElement(By.xpath("//span[text()='Browse...']"));
		Actions action = new Actions(fBase.driver);
		action.clickAndHold(obj);
		action.release();
		action.perform();
		Thread.sleep(2000);
	}

	
	public void fileUpload(String uploadFilePath) throws AWTException, InterruptedException 
	{
		Thread.sleep(2000);
		//String uploadFilePath = "D:\\new.txt" ;
		
		//Robot for file handling on opened Windows Applications 
		
		StringSelection filePathObj = new StringSelection(uploadFilePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePathObj, null);

		Robot rb = new Robot();

		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);
		rb.keyRelease(KeyEvent.VK_V);
		rb.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(1000);

		rb.keyPress(KeyEvent.VK_TAB);
		rb.keyRelease(KeyEvent.VK_TAB);

		rb.keyPress(KeyEvent.VK_TAB);
		rb.keyRelease(KeyEvent.VK_TAB);

		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(1000);

		//Direct File Upload
		/*WebElement uploadElement = fBase.driver.findElement(By.xpath("//span[text()='Browse...']/.."));
		uploadElement.sendKeys("D:\\new.txt");*/

	}

	

	public void clickOnAddAttachment() throws InterruptedException
	{

		Select attachmentType = new Select(fBase.driver.findElement(By.id("attachmentType0")));
		attachmentType.selectByVisibleText("Access Letter");

		//Click and Release Add button
		WebElement obj = fBase.driver.findElement(By.id("btncreate"));
		Actions action = new Actions(fBase.driver);
		action.clickAndHold(obj);
		action.release();
		action.perform();
		Thread.sleep(5000);

	}







}








