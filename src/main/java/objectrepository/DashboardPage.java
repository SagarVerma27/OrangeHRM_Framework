package objectrepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericutilities.WebDriverUtilities;

public class DashboardPage 
{
	private WebDriver driver;
	
	@FindBy(xpath="//input[@class='oxd-input oxd-input--active']")
	private WebElement search;
	
	@FindBy(xpath="//li[@class='oxd-userdropdown']")
	private WebElement userProfile;
	
	@FindBy(xpath="//i[@class='oxd-icon bi-chevron-left']")
	private WebElement hideModuleButton;
	
	@FindBy(xpath="//div[@class='oxd-brand-banner']")
	private WebElement Banner;
	
	
	public DashboardPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void navigateToModule(String moduleName)
	{
	    driver.findElement(By.xpath("//span[text()='"+moduleName+"']")).click();
	}
	
	public void launchQuickAction(String launchAction)
	{
		driver.findElement(By.xpath("//button[@title='"+launchAction+"']")).click();
	}
	
	public void searchModule(String module)
	{
	    search.sendKeys(module);
	}
	
	public void openUserMenu()
	{
		userProfile.click();
	}
	
	public void toggleSidebar()
	{
		hideModuleButton.click();
	}
	
	public boolean isOrangeHRMBannerDisplayed()
	{
		WebDriverUtilities wutil= new WebDriverUtilities();
		wutil.visiblityOfElement(driver, Banner);
		return Banner.isDisplayed();
	}
}
