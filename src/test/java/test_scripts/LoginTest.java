package test_scripts;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import baseTest.BaseClass;
import objectrepository.DashboardPage;

public class LoginTest extends BaseClass
{

	@Test
	public void loginTest() throws IOException, InterruptedException 
	{
		
		DashboardPage dashboard= new DashboardPage(driver);
		
		Assert.assertTrue(dashboard.isOrangeHRMBannerDisplayed(), "Login failed: Orange HRM banner is not displayed");
		Reporter.log("Login sucessfully", true);
		
	}

}
