package genericutilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtilities
{
	public void maximizeBrowser(WebDriver driver)
	{
		driver.manage().window().maximize();
	}
	
	public void deleteCookies(WebDriver driver)
	{
		driver.manage().deleteAllCookies();
	}
	
	public void waitPageToLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}
	
	public void visiblityOfElement(WebDriver driver, WebElement element)
	{
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void elementToBeClickable(WebDriver driver, WebElement element)
    {
        WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
	
	public void waitUntilLoaderDisappears(WebDriver driver)
	{
		WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("oxd-form-loader")));
	}
	
	public void switchToFrame(WebDriver driver, int index)
	{
		driver.switchTo().frame(index);
	}
	
	public void switchToFrame(WebDriver driver, String name)
	{
		driver.switchTo().frame(name);
	}
	
	public void switchToFrame(WebDriver driver, WebElement element)
	{
		driver.switchTo().frame(element);
	}
	
	public void switchToAlertAndAccept(WebDriver driver)
	{
    	driver.switchTo().alert().accept();
	}
	
	public void switchToAlertAndDismiss(WebDriver driver) 
	{
		driver.switchTo().alert().dismiss();
	}

	public String switchToAlertAndgetText(WebDriver driver)
	{
		String text = driver.switchTo().alert().getText();
		return text;
	}

	public void switchToAlertAndSendkeys(WebDriver driver, String text) 
	{
			driver.switchTo().alert().sendKeys(text);
	}
			
	public void select(WebElement element, int index)
	{
			Select sel = new Select(element);
			sel.selectByIndex(index);
	}
			
	public void select(WebElement element, String value)
	{
			Select sel = new Select(element);
			sel.selectByValue(value);
	}
			
	public void select(String text, WebElement element) 
	{
			Select sel = new Select(element);
			sel.selectByVisibleText(text);
	}
	
	public void mouseHoverOnWebElement(WebDriver driver, WebElement Element) 
	{
			Actions act = new Actions(driver);
			act.moveToElement(Element).perform();
	}
			
	public void clickOnWebElement(WebDriver driver, WebElement element) 
	{
			Actions act = new Actions(driver);
			act.moveToElement(element).click().perform();
	}
			
	public void doubleClickOnWebElement(WebDriver driver, WebElement element)
	{
			Actions act = new Actions(driver);
			act.doubleClick(element).perform();
	}
			
	public void rightClickOnWebElement(WebDriver driver, WebElement element) 
	{
			Actions act = new Actions(driver);
			act.contextClick(element).perform();
	}
			
	public void passInput(WebDriver driver, WebElement element,	String text) 
	{
			Actions act = new Actions(driver);
			act.click(element).sendKeys(text).perform();
	}
	
	public void switchToWindow(WebDriver driver) 
	{
		Set<String> allWindId = driver.getWindowHandles();
		for (String id : allWindId) 
		{
		driver.switchTo().window(id);
	    }
	}
		
	public void takeScreenshot(WebDriver driver,String fileName) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot) driver;
		File temp = ts.getScreenshotAs(OutputType.FILE);
		File perm=new File("./Screenshots/"+fileName+".png");
		 FileHandler.copy(temp, perm);
	}
		
	public void toScrollBy(WebDriver driver,int x,int y) 
	{
		JavascriptExecutor jse=(JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy("+x+","+y+")");
	}
	
	public String getTitle(WebDriver driver) 
	{
		String title = driver.getTitle();
		return title;
	}

}
