package baseTest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import genericutilities.ExcelUtilities;
import genericutilities.JavaUtilities;
import genericutilities.PropertiesUtility;
import genericutilities.WebDriverUtilities;
import objectrepository.DashboardPage;
import objectrepository.LoginPage;
import objectrepository.UserMenuPage;

public class BaseClass 
{
	protected WebDriver driver=null;
	protected PropertiesUtility putil= new PropertiesUtility();
	protected WebDriverUtilities wutil= new WebDriverUtilities();
	protected ExcelUtilities eutil = new ExcelUtilities();
	protected JavaUtilities jutil= new JavaUtilities();
	
	@BeforeSuite
	public void beforeSuite()
	{
		Reporter.log("DB open", true);
	}
	
	@BeforeClass
	public void launchBrowser() throws IOException
	{
		String browser = putil.toReadDataFromPropFile("BROWSER");
		switch(browser.toLowerCase())
		{
		case "edge" : driver= new EdgeDriver();
//		{ EdgeOptions options = new EdgeOptions();
//                       options.addArguments("--headless=new");
//                       options.addArguments("--disable-gpu");
//                       options.addArguments("--window-size=1920,1080");
//                       options.addArguments("--no-sandbox");
//                       options.addArguments("--disable-dev-shm-usage");
//                       driver = new EdgeDriver(options);
//                      } 
			break;
  
		case "firefox" : driver= new FirefoxDriver(); break;
		
		case "chrome": driver= new ChromeDriver();
//		{ ChromeOptions options = new ChromeOptions();
//                        options.addArguments("--headless=new");
//                        options.addArguments("--disable-gpu");
//                        options.addArguments("--window-size=1920,1080");
//                        options.addArguments("--no-sandbox");
//                        options.addArguments("--disable-dev-shm-usage");
//                        driver = new ChromeDriver(options); 
//		               } 
			break;
             
		default : throw new IllegalArgumentException("Invalid Browser");
		}
		
		wutil.maximizeBrowser(driver);
		wutil.deleteCookies(driver);
		wutil.waitPageToLoad(driver);
		
		Reporter.log("Browser launched successfully", true);
	}
	
	@BeforeMethod  
	public void login() throws IOException
	{
		String url = putil.toReadDataFromPropFile("URL");
		String username = putil.toReadDataFromPropFile("USERNAME");
		String password = putil.toReadDataFromPropFile("PASSWORD");
		
		driver.get(url);
		
		wutil.waitUntilLoaderDisappears(driver);
		LoginPage login=new LoginPage(driver);
		login.login(username, password);
		
		Reporter.log("Logged into OrangeHRM", true);
		
	}
	
	@AfterMethod
	public void logout()
	{
		DashboardPage dashboard= new DashboardPage(driver);
		dashboard.openUserMenu();
		
		UserMenuPage usermenu= new UserMenuPage(driver);
		usermenu.selectUserOption("Logout");
		
		Reporter.log("Logged out successfully", true);
		
	}
	
	@AfterClass
	public void closeBrowser()
	{
		driver.quit();
		Reporter.log("close browser", true);
	}
	
	@AfterSuite
	public void afterSuite()
	{
		Reporter.log("DB close", true);
	}

}
