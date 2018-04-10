
package frameworkPackage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.TestNG;
import org.testng.annotations.Test;

public class DriverClass 
{
	@Test
	public void DriverMethod() throws IOException
	{
		//Project Location
		String projectLocationPath = System.getProperty("user.dir");
		
		//Driver Sheet Path
		String driverSheetPath = projectLocationPath+"\\src\\main\\resources\\DriverSheet.xlsx";
		
		//TestNg Xmls Location in Project
		String testNgXmlsLocation = projectLocationPath+"\\src\\test\\resources";
		
		List<String> suiteFiles=new ArrayList<String>();
		TestNG runner=new TestNG();
		
		//Read values from Excel file
		FileInputStream fis = new FileInputStream(driverSheetPath);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);

		int rowCount = sheet.getPhysicalNumberOfRows();

		for(int i=1; i<rowCount; i++)
		{
			//Execution Flag
			String executionFlag = sheet.getRow(i).getCell(2).getStringCellValue();

			if(executionFlag.equals("YES"))
			{
				//Xml names
				String testNgXmlName_Sheet = sheet.getRow(i).getCell(1).getStringCellValue();

				String testNgXmlFilePath_Project = testNgXmlsLocation+"\\"+testNgXmlName_Sheet;
				
				//TestNg files location
				suiteFiles.add(testNgXmlFilePath_Project);
			}
		}

		wb.close();

		//System.out.println(suiteFiles);

		runner.setTestSuites(suiteFiles);
		runner.run();

	}    
	
}


