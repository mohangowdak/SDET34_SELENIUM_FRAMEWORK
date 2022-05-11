package com.vtiger.contactsTest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sdet34l1.genericUtility.ExcelUtility;
import com.sdet34l1.genericUtility.FileUtility;
import com.sdet34l1.genericUtility.IconstantPath;
import com.sdet34l1.genericUtility.JavaUtility;
import com.sdet34l1.genericUtility.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactsTest {

	public static void main(String[] args) throws SQLException, IOException {
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
	
		String lastName=ExcelUtility.getDataFromExcel("contacts", 2, 1)+randomNumber; 

		

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
		
		//testCase step1==> login to the app
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		if(driver.getTitle().contains("Home"))
		{
			ExcelUtility.setDataIntoExcel("contacts", 14, 5, "HomePage is Displayed");
			ExcelUtility.setDataIntoExcel("contacts", 14, 6, "pass");

		}

		//testcase step2==> clik on contact
		driver.findElement(By.linkText("Contacts")).click();
		if(driver.getTitle().contains("Contacts"))
		{

			ExcelUtility.setDataIntoExcel("contacts", 15, 5,"Contacts page is Displayed");
			ExcelUtility.setDataIntoExcel("contacts", 15, 6, "pass");
		}

		//testcase step3==> clik on create contact
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		if(driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText().equalsIgnoreCase("Creating New Contact"))
		{

			ExcelUtility.setDataIntoExcel("contacts", 16, 5,"Create Contact page is Displayed");
			ExcelUtility.setDataIntoExcel("contacts", 16, 6, "pass");

		}

		//testcase step4==> Enter lastname and click on save
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		WebElement headerText=driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
		WebDriverUtility.waitUntillElementVisible(headerText);
		
		if(headerText.getText().contains("Contact Information"))
		{

			ExcelUtility.setDataIntoExcel("contacts", 17, 5,"Contact Information page is Displayed");
			ExcelUtility.setDataIntoExcel("contacts", 17, 6, "pass");

		}
		WebElement ActualLastName = driver.findElement(By.id("dtlview_Last Name"));
		//testcase step5==> Validate the contact
		if(ActualLastName.getText().equalsIgnoreCase(lastName))
		{
			jutil.printStatement("contact created Successfully");
			ExcelUtility.setDataIntoExcel("contacts", 18, 5,"All data validate successfully");
			ExcelUtility.setDataIntoExcel("contacts", 18, 6, "pass");
		}



		WebElement administratorIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")); 
		//testcase step6==> signout

		WebDriverUtility.mouseHoverOntheElement(administratorIcon, driver);
		driver.findElement(By.linkText("Sign Out")).click();
		
		{
			ExcelUtility.setDataIntoExcel("contacts", 19, 5,"HomePage is Displayed");
			ExcelUtility.setDataIntoExcel("contacts", 19, 6, "pass");
			ExcelUtility.setDataIntoExcel("contacts", 21, 2, "pass");

		}

		ExcelUtility.saveExcelData(IconstantPath.EXCELFILEPATH);
		ExcelUtility.closeExcel();
		WebDriverUtility.quitBrowser(driver);
	}

}
