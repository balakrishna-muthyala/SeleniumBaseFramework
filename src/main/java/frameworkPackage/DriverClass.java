
package frameworkPackage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.TestNG;
import org.testng.annotations.Test;

@Test
public class DriverClass 
{
	
	public void DriverMethod() throws IOException
	{
		//Project Location
		String projectLocationPath = System.getProperty("user.dir");
		
		List<String> suitefiles=new ArrayList<String>();

		TestNG runner=new TestNG();
		
		//Read values from Excel file
		FileInputStream fis = new FileInputStream(projectLocationPath+"\\src\\main\\resources\\DriverSheet.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);

		int rownum = sheet.getPhysicalNumberOfRows();

		for(int i=1;i<rownum;i++)
		{
			//Execution Flag
			String xcellkey=sheet.getRow(i).getCell(2).getStringCellValue();

			if(xcellkey.equals("YES"))
			{
				//Xml names
				String xcellValue = sheet.getRow(i).getCell(1).getStringCellValue();

				//TestNg files location
				suitefiles.add(projectLocationPath+"\\src\\test\\resources\\"+xcellValue);
			}
		}

		wb.close();

		//System.out.println(suitefiles);

		runner.setTestSuites(suitefiles);
		runner.run();

	}    
	
}


