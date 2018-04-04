package frameworkPackage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.TestNG;
import org.testng.annotations.Test;

public class DriverClass {

	@Test
	public void DriverMethod() throws IOException
	{
		List<String> suitefiles=new ArrayList<String>();

		TestNG runner=new TestNG();
		
		//to read values from excel
		FileInputStream fis = new FileInputStream("F:\\Driver.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);
		//XSSFRow rows = sheet.getRow(0);

		int rownum = sheet.getPhysicalNumberOfRows();

		for(int i=1;i<rownum;i++)
		{
			String xcellkey=sheet.getRow(i).getCell(1).getStringCellValue();
			System.out.println(xcellkey);

			if(xcellkey.equals("YES"))
			{
				String xcellValue = sheet.getRow(i).getCell(0).getStringCellValue();
				System.out.println(xcellValue);
				//update here according to the project folder
				suitefiles.add("F:\\SELENIUM\\selenium-projects\\SeleniumBaseFramework\\src\\test\\resources\\"+xcellValue);
			}
		}

		wb.close();

		System.out.println(suitefiles);

		runner.setTestSuites(suitefiles);
		runner.run();

	}    
	
}

