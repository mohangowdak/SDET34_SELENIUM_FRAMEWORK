package com.sdet34l1.genericUtility;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

/**
 * This is class is used to maintain all webdriver common actions 
 * @author MOHAN GOWDA
 *
 */
public class WebDriverUtility {
	static WebDriverWait wait;
	static Actions act;
	static Select select;
	static JavascriptExecutor js;
	static JavaUtility jutil=new JavaUtility();
	//static WebDriver driver;
	/**
	 * This method is used to initialize and launch the browser
	 * @param browser

	public static void launchBrowser(String browser) {
		switch (browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			break;
		default:
			System.out.println("please specify proper browser key");
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			break;
		}
	}
	 */
	/**
	 * This method is used to return driver instance
	 * @param driver
	 * @return

	public static WebDriver getDriver(WebDriver driver) {
		return driver;
	}
	 */



	/**
	 * This method is used to navigate to the application
	 * @param url
	 */
	public static void navigateApp(String url, WebDriver driver) {
		driver.get(url);
	}

	/**
	 * This method is used to maximize the browser and implicitly wait 
	 * @param longTimeOut
	 */
	public static void browserSetting(long longTimeOut, WebDriver driver) {
		maximizeBrowser(driver);
		waitTillPageLoad(longTimeOut, driver);
	}

	/**
	 * This method is used to maximize the browser
	 * 
	 */
	public static void maximizeBrowser(WebDriver driver) {
		driver.manage().window().maximize();
	}

	/**
	 * This method is used to implicitly wait till page Load
	 * @param longTimeOut
	 */
	public static void waitTillPageLoad(long longTimeOut, WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(longTimeOut, TimeUnit.SECONDS);
	}

	/**
	 * This method is used to intiallize the actions class variable
	 * @param driver
	 */
	public static void intiallizeActions(WebDriver driver) {
		act=new Actions(driver);
	}

	/**
	 * This method is used to mouse hover on the element
	 * @param moreLink
	 */
	public static void mouseHoverOntheElement(WebElement moreLink, WebDriver driver) {

		act.moveToElement(moreLink).perform();
	}

	/**
	 * Perform double click action on  webelement
	 * @param moreLink
	 */
	public static void doubleClick(WebElement moreLink) {

		act.doubleClick(moreLink).perform();
	}

	/**
	 * Perform double click action on  webpage
	 */
	public static void doubleClick() {

		act.doubleClick().perform();
	}


	/**
	 * This method is used to close the browser instance
	 * @param driver
	 */
	public static void quitBrowser(WebDriver driver) {
		driver.quit();
	}

	/**
	 * This method is used to wait the control till the particular element is clickable
	 * @param driver
	 * @param timeOut
	 * @param element
	 */
	public static void waitUntillElementClickable(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}


	/**
	 * This method is used to wait the control till the particular element is visible
	 * @param driver
	 * @param timeOut
	 * @param element
	 */
	public static void waitUntillElementVisible( WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * This method is used to intiallize wait instance
	 * @param driver
	 * @param timeOut
	 */
	public static void explicitlyWait(WebDriver driver, long timeOut) {
		wait=new WebDriverWait(driver, timeOut);
	}

	/**
	 * This method is used to swith the window based on title
	 * @param driver
	 * @param partialText
	 */
	public static void switchToWindowBasedOnTitle(WebDriver driver ,String partialText) {
		Set<String> sessionIDs = driver.getWindowHandles();
		for(String id:sessionIDs)
		{
			driver.switchTo().window(id);
			if(driver.getTitle().contains(partialText))
			{
				break;
			}

		}
	}

	/**
	 * This method is used to handle dropdown by value
	 * @param element
	 * @param value
	 */
	public static void selectDropDown(WebElement element, String value) {
		select=new Select(element);
		select.selectByValue(value);
	}

	/**
	 * This method is used to handle dropdown by index
	 * @param element
	 * @param index
	 */
	public static void selectDropDown(WebElement element, int index) {
		select=new Select(element);
		select.selectByIndex(index);
	}

	/**
	 * This method is used to handle dropdown by visible text
	 * @param visibleText
	 * @param element
	 */
	public static void selectDropDown(String visibleText, WebElement element) {
		select=new Select(element);
		select.selectByVisibleText(visibleText);
	}

	/**
	 * This method is used to switch the frame by index
	 * @param driver
	 * @param index
	 */
	public static void swithToFrame(WebDriver driver, int index) {
		driver.switchTo().frame(index);
	}

	/**
	 * This method is used to switch the frame by nameOrId
	 * @param driver
	 * @param nameOrId
	 */
	public static void swithToFrame(WebDriver driver, String nameOrId) {
		driver.switchTo().frame(nameOrId);
	}

	/**
	 * This method is used to switch the frame by frame Element
	 * @param driver
	 * @param element
	 */
	public static void swithToFrame(WebDriver driver, WebElement element) {
		driver.switchTo().frame(element);
	}

	/**
	 * This method is used to switch Back to main Web page
	 * @param driver
	 */
	public static void swithBackToHomeWebPage(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	/**
	 * This method is used to intiallize the javascriptexecutor interface variable
	 * @param driver
	 */
	public static void intiallizeJs(WebDriver driver) {
		
		js=(JavascriptExecutor) driver;
	}
	
	/**
	 * This method is used to enter the data inside the textfield through the javascript executor
	 * @param element
	 * @param data
	 */
	public static void enterDataThroughJs( WebElement element, String data) {

		js.executeScript("arguments[0].value=arguments[1]", element, data);
	}

	/**
	 * This method is used to click on the element through javascript excecutor
	 * @param element
	 */
	public static void clickThroughJs( WebElement element) {

		js.executeScript("arguments[0].click()", element);
	}

	/**
	 * This method is used to navigate the application through the javascript
	 * @param url
	 */
	public static void navigateApplicationThroughJs(String url) {

		js.executeScript("window.location=arguments[0]", url);
	}

	/**
	 * This method is used to scroll the webpage till specified height
	 * @param height
	 */
	public static void scroolToSpecifiedHeight(String height) {
		js.executeScript("window.scrollBy(0,"+height+")");
	}
	
	/**
	 * This method is used to scroll till the bottom of the page
	 */
	public static void scroolToBottom() {
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}
	
	/**
	 * This method is used to scroll till the element is alligned to top
	 * @param element
	 */
	public static void scrollTillElement(WebElement element) {
		js.executeScript("arguments[0].scrollIntoView()",element);
	}

	
	/**
	 * This method is used to take the screen shot
	 * @param fileName
	 * @param driver
	 * @throws IOException
	 */
	public static void takeScreenShot(String fileName, WebDriver driver) throws IOException {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dst=new File("./screenshot/"+fileName+"_"+jutil.getDateTimeInFormat()+".png");
		System.out.println(dst.getAbsolutePath());
		FileUtils.copyFile(src, dst);
		//Files.copy(src, dst);
	}
	
	public static void alertAccept(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	public static void alertDismiss(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	public static void alertsendData(WebDriver driver, String data) {
		driver.switchTo().alert().sendKeys(data);
	}
	public static String getalertText(WebDriver driver) {
		return driver.switchTo().alert().getText();
	}
	
	public static void rightClick() {
		act.contextClick().perform();
	}
	
	public static void rightClick(WebElement element) {
		act.contextClick(element).perform();
	}

}
