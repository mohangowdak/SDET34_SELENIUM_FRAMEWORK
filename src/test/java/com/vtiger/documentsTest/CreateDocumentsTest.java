package com.vtiger.documentsTest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.sdet34l1.genericUtility.ExcelUtility;
import com.sdet34l1.genericUtility.FileUtility;
import com.sdet34l1.genericUtility.IconstantPath;
import com.sdet34l1.genericUtility.JavaUtility;
import com.sdet34l1.genericUtility.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateDocumentsTest {
	public static void main(String[] args) throws Throwable, IOException {
		JavaUtility jutil=new JavaUtility();

		FileUtility.openPropertyFile(IconstantPath.PROPERTYFILEPATH);
		ExcelUtility.openExcel(IconstantPath.EXCELFILEPATH);
		
		String url = FileUtility.getDataFromPropertyFile("url");
		String timeout = FileUtility.getDataFromPropertyFile("timeout");
		String username =FileUtility.getDataFromPropertyFile("userName");
		String password = FileUtility.getDataFromPropertyFile("password");
		String browser=FileUtility.getDataFromPropertyFile("browser");

		long longTimeOut = jutil.stringToLong(timeout);

		int randomNumber = jutil.getRandomNumber(1000);
		
		String documentTitle=ExcelUtility.getDataFromExcel("document", 2, 1)+randomNumber; 
		String documentPath=ExcelUtility.getDataFromExcel("document", 2, 2); 
		String documentDescription=ExcelUtility.getDataFromExcel("document", 2, 3); 

		WebDriver driver=null;

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
			jutil.printStatement("please specify proper browser key");
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			break;
		}
		WebDriverUtility.navigateApp(url, driver);
		WebDriverUtility.browserSetting(longTimeOut,driver);
		WebDriverUtility.explicitlyWait(driver, longTimeOut);
		WebDriverUtility.intiallizeActions(driver);
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		

		
		driver.findElement(By.linkText("Documents")).click();
		driver.findElement(By.xpath("//img[@title='Create Document...']")).click();
		driver.findElement(By.xpath("//input[@name='notes_title']")).sendKeys(documentTitle);
		
		WebDriverUtility.swithToFrame(driver, 0);
		driver.findElement(By.xpath("//body[@class='cke_show_borders']")).sendKeys(documentDescription, Keys.CONTROL+"a");
		WebDriverUtility.swithBackToHomeWebPage(driver);
		driver.findElement(By.id("cke_5")).click();
		driver.findElement(By.id("cke_6")).click();
		driver.findElement(By.xpath("//input[@id='filename_I__']")).sendKeys(documentPath);
		
		driver.findElement(By.xpath("//b[.='File Information']/../../following-sibling::tr[4]//input[@title='Save [Alt+S]']")).click();
		WebDriverUtility.waitUntillElementVisible(driver.findElement(By.xpath("//span[@class='dvHeaderText']")));
		
		WebElement administratorIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")); 

		WebDriverUtility.mouseHoverOntheElement(administratorIcon, driver);
		driver.findElement(By.linkText("Sign Out")).click();
		WebDriverUtility.quitBrowser(driver);
		
	}

}
