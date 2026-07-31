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

public class EditEmployeeTest extends BaseClass
{
	@Test
	public void editEmployeeTest() throws EncryptedDocumentException, FileNotFoundException, IOException
	{
		DashboardPage dashboard= new DashboardPage(driver);
		dashboard.navigateToModule("PIM");
		
		PIMPage pim= new PIMPage(driver);
		pim.navigateToPIMTab("Employee List");
		
		String firstName = eutil.toReadDataFromExcel("Name", 1, 0);
		String lastName = eutil.toReadDataFromExcel("Name", 1, 2);
		String tabName = eutil.toReadDataFromExcel("Tab", 1, 0);
	
		pim.searchEmployee(firstName);
		pim.openEmployee(firstName);
		pim.updateEmployeeField("lastName", lastName);
		pim.navigateToEmployeeDetailsTab(tabName);
		
		Assert.assertEquals(pim.getEmployeeFullName(), (firstName+" "+lastName));
		Reporter.log("Name changes successfully", true);
		
	}

}
