package genericutilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtilities 
{
	public String toReadDataFromExcel(String Sheetname, int row, int cell) throws EncryptedDocumentException, FileNotFoundException, IOException
	{
		Workbook book = WorkbookFactory.create(new FileInputStream("./src/test/resources/TestData.xlsx"));
		String data = book.getSheet(Sheetname).getRow(row).getCell(cell).getStringCellValue();
		return data;
	
	}
	

	
}
