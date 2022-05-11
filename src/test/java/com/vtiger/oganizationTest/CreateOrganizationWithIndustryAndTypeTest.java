package com.vtiger.oganizationTest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sdet34l1.genericUtility.ExcelUtility;
import com.sdet34l1.genericUtility.FileUtility;
import com.sdet34l1.genericUtility.IconstantPath;
import com.sdet34l1.genericUtility.JavaUtility;
import com.sdet34l1.genericUtility.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganizationWithIndustryAndTypeTest {

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
		
		String organizationName = ExcelUtility.getDataFromExcel("organization", 4, 1)+randomNumber;
		String industryName = ExcelUtility.getDataFromExcel("organization", 4, 2);
		String typeName = ExcelUtility.getDataFromExcel("organization", 4, 3);
		
	
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
		if(driver.getTitle().contains("Home"))
		{
			ExcelUtility.setDataIntoExcel("organization", 14, 5, "HomePage is Displayed");
			ExcelUtility.setDataIntoExcel("organization", 14, 6, "pass");

		}
		driver.findElement(By.linkText("Organizations")).click();
		
		if(driver.getTitle().contains("Organizations"))
		{
			ExcelUtility.setDataIntoExcel("organization", 15, 5, "Organizations is Displayed");
			ExcelUtility.setDataIntoExcel("organization", 15, 6, "pass");
		}
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		if(driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText().equalsIgnoreCase("Creating New Organization"))
		{

			ExcelUtility.setDataIntoExcel("organization", 16, 5, "Create Organization is Displayed");
			ExcelUtility.setDataIntoExcel("organization", 16, 6, "pass");

		}
		
		driver.findElement(By.name("accountname")).sendKeys(organizationName);
		WebElement industryDropDown = driver.findElement(By.xpath("//select[@name='industry']"));
		WebElement typeDropDown = driver.findElement(By.xpath("//select[@name='accounttype']"));
		
		WebDriverUtility.selectDropDown(industryDropDown, industryName);
		WebDriverUtility.selectDropDown(typeDropDown, typeName);
		
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		WebElement headerText=driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
		WebDriverUtility.waitUntillElementVisible(headerText);
		if(headerText.getText().contains("Organization Information"))
		{

			ExcelUtility.setDataIntoExcel("organization", 17, 5, "Organization Information page is Displayed");
			ExcelUtility.setDataIntoExcel("organization", 17, 6, "pass");

		}
		
		
		WebElement ActualOrganizationName = driver.findElement(By.id("dtlview_Organization Name"));
		WebElement ActualIndustryName = driver.findElement(By.xpath("//span[@id='dtlview_Industry']/font"));
		WebElement ActualTypeName = driver.findElement(By.xpath("//span[@id='dtlview_Type']/font"));
		
		
		if(ActualOrganizationName.getText().equalsIgnoreCase(organizationName)
		&& ActualIndustryName.getText().equalsIgnoreCase(industryName)
		&&ActualTypeName.getText().equalsIgnoreCase(typeName)
		){
			System.out.println("Organization created with Industry and type Successfully");

			ExcelUtility.setDataIntoExcel("organization", 18, 5, "All data validate successfully");
			ExcelUtility.setDataIntoExcel("organization", 18, 6, "pass");

		}

			
		
		 WebElement administratorIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")); 
		 
		 WebDriverUtility.mouseHoverOntheElement(administratorIcon, driver);
			driver.findElement(By.linkText("Sign Out")).click();
		
		 if(driver.getTitle().contains("Login"))
			{
				ExcelUtility.setDataIntoExcel("organization", 19, 5, "HomePage is Displayed");
				ExcelUtility.setDataIntoExcel("organization", 19, 6, "pass");
				ExcelUtility.setDataIntoExcel("organization", 21, 2, "pass");

			}

			ExcelUtility.saveExcelData(IconstantPath.EXCELFILEPATH);
			ExcelUtility.closeExcel();
			WebDriverUtility.quitBrowser(driver);
	}
	
}
