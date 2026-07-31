package test_scripts;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import baseTest.BaseClass;
import objectrepository.DashboardPage;
import objectrepository.PIMPage;

public class SearchEmployeeTest extends BaseClass
{

	@Test
	public void searchEmployeeTest() throws Throwable, FileNotFoundException, IOException
	{
		DashboardPage dashboard= new DashboardPage(driver);
		dashboard.navigateToModule("PIM");
		
		PIMPage pim= new PIMPage(driver);
		pim.navigateToPIMTab("Employee List");
		
		String firstName = eutil.toReadDataFromExcel("Name", 1, 0);
		String middleName = eutil.toReadDataFromExcel("Name", 1, 1);
		String lastName = eutil.toReadDataFromExcel("Name", 1, 2);
		
		pim.searchEmployee(firstName +" " +middleName);
		pim.openEmployee(firstName +" " +middleName);
		
		Assert.assertEquals(pim.getEmployeeFullName(), (firstName+" "+lastName) , "Employee not found.");
		Reporter.log("Employee found successfully", true);
		
	}
}
