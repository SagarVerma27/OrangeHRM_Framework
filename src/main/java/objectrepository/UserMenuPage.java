package objectrepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserMenuPage 
{
	private WebDriver driver;
	
	public UserMenuPage(WebDriver driver)
	{
		this.driver=driver;
	
	}
	
	public void selectUserOption(String option)
	{
		driver.findElement(By.xpath("//a[text()='"+option+"']")).click();
	}

}
