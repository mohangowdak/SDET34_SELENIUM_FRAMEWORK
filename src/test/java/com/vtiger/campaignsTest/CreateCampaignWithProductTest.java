package com.vtiger.campaignsTest;

import org.apache.commons.math3.stat.descriptive.summary.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.sdet34l1.genericUtility.ExcelUtility;
import com.sdet34l1.genericUtility.FileUtility;
import com.sdet34l1.genericUtility.IconstantPath;
import com.sdet34l1.genericUtility.JavaUtility;
import com.sdet34l1.genericUtility.WebDriverUtility;
import com.vtiger.objectRepository.CampaignInformationPage;
import com.vtiger.objectRepository.CampaignPage;
import com.vtiger.objectRepository.CreateNewCampaignPage;
import com.vtiger.objectRepository.CreateNewProductPage;
import com.vtiger.objectRepository.HomePage;
import com.vtiger.objectRepository.LoginPage;
import com.vtiger.objectRepository.ProductPage;
import com.vtiger.objectRepository.SearchProductsPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateCampaignWithProductTest {
	public static void main(String[] args) throws Throwable {
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


		String productName=ExcelUtility.getDataFromExcel("campaign", 5, 1)+randomNumber; 
		String campaignname=ExcelUtility.getDataFromExcel("campaign", 5, 2)+randomNumber; 

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

		LoginPage loginPage=new LoginPage(driver);
		HomePage homePage=new HomePage(driver);
		ProductPage productPage=new ProductPage(driver);
		CreateNewProductPage createNewProductPage=new CreateNewProductPage(driver);
		SearchProductsPage searchProductPage=new SearchProductsPage(driver);
		
		CampaignPage campaignPage=new CampaignPage(driver);
		CreateNewCampaignPage createNewCampaignPage=new CreateNewCampaignPage(driver);
		CampaignInformationPage campaignInformationPage=new CampaignInformationPage(driver);
		loginPage.loginAction(username, password);
		
		homePage.clickProduct();
		productPage.clickCreateProductLookUpImg();
		createNewProductPage.enterProductNameAndSave(productName);
	

		WebDriverUtility.waitUntillElementClickable(homePage.getMoreDropDown());
		homePage.clickCampaign(driver);
campaignPage.clickCreateCampainLookUpImg();
		createNewCampaignPage.enterCampaignNameAndSwitchToSearchProduct(campaignname,driver);
	
		searchProductPage.selectProduct(productName, driver);
		WebDriverUtility.switchToWindowBasedOnTitle(driver,"Campaigns");
		createNewCampaignPage.saveCampaign();
		
		WebElement actualCampaignName = driver.findElement(By.id("dtlview_Campaign Name"));
		WebElement actualProductName = driver.findElement(By.xpath("//span[@id='dtlview_Product']/a"));

		jutil.assertionThroughIfCondition(actualCampaignName.getText(), campaignname, "campaign with product");
		jutil.assertionThroughIfCondition(actualProductName.getText(), productName, "");


		homePage.signout(driver);
		WebDriverUtility.quitBrowser(driver);
	}

}
