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

public class AddEmployeeTest extends BaseClass
{

	@Test
	public void addEmployeeTest() throws EncryptedDocumentException, FileNotFoundException, IOException
	{
		
		DashboardPage dashboard= new DashboardPage(driver);
		dashboard.navigateToModule("PIM");
		
		String firstName = eutil.toReadDataFromExcel("Name", 1, 0);
		int randomNumber= jutil.randomNumber();
		
	    String uniqueFirstName = firstName + randomNumber;
		 
		String middleName = eutil.toReadDataFromExcel("Name", 1, 1);
		String lastName = eutil.toReadDataFromExcel("Name", 1, 2);
		
		//String fullName = firstName + " " + middleName + " " +lastName;
		
		PIMPage pim= new PIMPage(driver);
		pim.navigateToPIMTab("Add Employee");
		pim.enterEmployeeName("firstName", uniqueFirstName);
		pim.enterEmployeeName("middleName", middleName);
		pim.enterEmployeeName("lastName", lastName );
		pim.save();
		
		Assert.assertTrue(pim.isPersonalDetailsPageIsDisplayed(), "Employee Details page is not displayed.");
		
		pim.navigateToPIMTab("Employee List");
		String name = uniqueFirstName + " " + middleName;
		
		pim.searchEmployee(name);
		pim.openEmployee(name);
		
		Assert.assertEquals(pim.getEmployeeFullName(), (uniqueFirstName + " " + lastName));
		Reporter.log("Employee added successfully", true);
		
	}
}
