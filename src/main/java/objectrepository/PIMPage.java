package objectrepository;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericutilities.WebDriverUtilities;

public class PIMPage 
{
	private WebDriver driver;
	private WebDriverUtilities wutil =  new WebDriverUtilities();
	
	@FindBy(xpath="//span[@class='oxd-topbar-body-nav-tab-item']")
	private WebElement configurationButton;
	
	@FindBy(xpath="//label[text()='Employee Id']/following::input[1]")
	private WebElement employeeId;
	
	@FindBy(xpath="//button[@class='oxd-button oxd-button--medium oxd-button--secondary']")
	private WebElement addButton;
	
	@FindBy(xpath="//button[text()=' Cancel ']")
	private WebElement cancelButton;
	                  
	@FindBy(xpath="//button[@type='submit']")
	private WebElement saveButton; // Add Employee one
	
	@FindBy(xpath="//p[text()=' * Required']/following-sibling::button[text()=' Save ']")
	private WebElement save; //Employee details one
	
	@FindBy(xpath="(//input[@placeholder='Type for hints...'])[1]")
	private WebElement SearchEmployee;
	
	@FindBy(xpath="//button[text()=' Search ']")
	  private WebElement searchButton;
	
	@FindBy(xpath="//h6[text()='Personal Details']")
	private WebElement personalDetailsHeading;
	
	@FindBy(xpath="//span[text()='No Records Found']")
	private WebElement noRecordsFound;
	
	
	public PIMPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void navigateToPIMTab(String moduleName)
	{
	    WebElement tab = driver.findElement(By.linkText(moduleName));
		wutil.visiblityOfElement(driver, tab);
		tab.click();
	}
	
	public void clickConfigurationDropdown()
	{
		configurationButton.click();
	}
	
	public void enterEmployeeName(String name, String empName)
	{
		driver.findElement(By.xpath("//input[@name='"+name+"']")).sendKeys(empName);
	}
	
	public void enterEmployeeId( String id)
	{
		employeeId.sendKeys(id);
	}
	
	public void add() 
	{
		addButton.click();
	}
	
	public void  cancel()
	{
		cancelButton.click();
	}
	
	public void save()
	{
		wutil.waitUntilLoaderDisappears(driver);
		
		saveButton.click();
	}
	
	public void searchEmployee(String empName)
	{
		wutil.waitUntilLoaderDisappears(driver);
		
		SearchEmployee.sendKeys(Keys.CONTROL+ "a");
		SearchEmployee.sendKeys(Keys.DELETE);
		SearchEmployee.sendKeys(empName);
		searchButton.click();
	    
	}
	
	public void openEmployee(String employeeName )
	{
	    wutil.waitUntilLoaderDisappears(driver);
	    WebElement addedEmpName = driver.findElement(By.xpath("//div[contains(text(), '"+employeeName+"')]"));
        addedEmpName.click();
	}
    
	public void updateEmployeeField(String fieldName, String newValue)
	{
		 wutil.waitUntilLoaderDisappears(driver);
		 WebElement name = driver.findElement(By.xpath("//input[@name='"+fieldName+"']"));
		 name.sendKeys(Keys.CONTROL+"a");
		 name.sendKeys(Keys.DELETE);
		 name.sendKeys(newValue);
		 
	}
	
	public void saveEmployee()
	{
		wutil.waitUntilLoaderDisappears(driver);
	    save.click();
	}
	
	public void updateEmployee(String firstName, String middleName, String lastName)
    {
         updateEmployeeField("firstName", firstName);

         updateEmployeeField("middleName", middleName);

         updateEmployeeField("lastName", lastName);

         saveEmployee();
     }
	
	public void navigateToEmployeeDetailsTab(String tabName)
	{
		wutil.waitUntilLoaderDisappears(driver);
		WebElement tab = driver.findElement(By.xpath("//a[text()='"+tabName+"']"));
		tab.click();
	}
	
	public String getEmployeeFullName()
	{
		 wutil.waitUntilLoaderDisappears(driver);
	     WebElement fullName = driver.findElement(By.xpath("//div[@class='orangehrm-edit-employee-name']"));
		 return fullName.getText();
	}
	
	public boolean isPersonalDetailsPageIsDisplayed()
	{
		wutil.visiblityOfElement(driver, personalDetailsHeading);
		return personalDetailsHeading.isDisplayed();
	}
	
	
	public void selectEmployee(String employeeName)
	{
        wutil.waitUntilLoaderDisappears(driver);
		
		WebElement selectEmployee = driver.findElement(By.xpath("//div[contains(text(), '"+employeeName+"')]/ancestor:: div[@class='oxd-table-card']//i[@class='oxd-icon bi-check oxd-checkbox-input-icon']"));
		selectEmployee.click();
	}
	
	public void deleteEmployee()
	
	{
		
		WebElement delete = driver.findElement(By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--label-danger orangehrm-horizontal-margin']"));
		delete.click();
		
		wutil.waitUntilLoaderDisappears(driver);
		
		WebElement confirmDelete = driver.findElement(By.xpath("//button[contains(@class,'oxd-button--label-danger') and normalize-space()='Yes, Delete']"));
		confirmDelete.click();
		
		wutil.waitUntilLoaderDisappears(driver);
	}
	
	public String getSearchResultMessage()
	{
		String status = noRecordsFound.getText();
		return status;
	}
	
}
