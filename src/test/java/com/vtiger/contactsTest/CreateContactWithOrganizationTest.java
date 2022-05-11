package com.vtiger.contactsTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.sdet34l1.genericUtility.ExcelUtility;
import com.sdet34l1.genericUtility.FileUtility;
import com.sdet34l1.genericUtility.IconstantPath;
import com.sdet34l1.genericUtility.JavaUtility;
import com.sdet34l1.genericUtility.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactWithOrganizationTest {

	public static void main(String[] args) throws IOException, InterruptedException {
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
		Workbook wb=null;
		String organizationName = ExcelUtility.getDataFromExcel("contacts",5, 1)+randomNumber;
		String contactLastName=ExcelUtility.getDataFromExcel("contacts",5, 2)+randomNumber;
	
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
		WebDriverUtility.intiallizeActions(driver);
		
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		
		driver.findElement(By.name("accountname")).sendKeys(organizationName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
        jutil.customWait(driver.findElement(By.linkText("Contacts")), 1000, 10);
        driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
	
		driver.findElement(By.name("lastname")).sendKeys(contactLastName);
		
		driver.findElement(By.xpath("//td[contains(.,'Organization Name') and @class='dvtCellLabel']/following-sibling::td[1]/img")).click();
		
		
		WebDriverUtility.switchToWindowBasedOnTitle(driver, "Accounts");
		
		
		driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys(organizationName);
		driver.findElement(By.xpath("//input[@class='crmbutton small create']")).click();
		
		driver.findElement(By.xpath("//a[.='"+organizationName+"']")).click();

		
		WebDriverUtility.switchToWindowBasedOnTitle(driver, "Contacts");
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	WebElement ActualLastName = driver.findElement(By.id("dtlview_Last Name"));
	WebElement ActualOrganizationName = driver.findElement(By.xpath("//td[@id='mouseArea_Organization Name']/a"));

		if(ActualLastName.getText().equalsIgnoreCase(contactLastName) && ActualOrganizationName.getText().equalsIgnoreCase(organizationName)){
		
			jutil.printStatement("contact created with organization Successfully");
			jutil.printStatement("TC Pass");
		}

		
		 WebElement administratorIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")); 
	

			WebDriverUtility.mouseHoverOntheElement(administratorIcon, driver);
			driver.findElement(By.linkText("Sign Out")).click();
			WebDriverUtility.quitBrowser(driver);
	}
	
}
