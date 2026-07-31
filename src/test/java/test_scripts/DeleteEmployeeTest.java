package test_scripts;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import baseTest.BaseClass;
import objectrepository.DashboardPage;
import objectrepository.PIMPage;

public class DeleteEmployeeTest extends BaseClass
{
	@Test
	public void deleteEmployeeTest() throws EncryptedDocumentException, FileNotFoundException, IOException
	{
		DashboardPage dashboard= new DashboardPage(driver);
		dashboard.navigateToModule("PIM");
		
		String firstName = eutil.toReadDataFromExcel("Name", 1, 0);

		PIMPage pim= new PIMPage(driver);
		pim.navigateToPIMTab("Employee List");
		
		pim.searchEmployee(firstName);
		
		pim.selectEmployee(firstName);
		
		pim.deleteEmployee();
		
		pim.searchEmployee(firstName);
		
		Assert.assertEquals(pim.getSearchResultMessage(), "No Records Found", "Employee still present after deletion");
		
		Reporter.log("Employee not found", true);
		

	}
	

}
