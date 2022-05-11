package com.vtiger.campaignsTest;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
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
import com.vtiger.objectRepository.HomePage;
import com.vtiger.objectRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateCampaignTest {
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
		String campaignname=ExcelUtility.getDataFromExcel("campaign", 2, 1)+randomNumber; 
		WebDriver driver = null;
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
		WebDriverUtility.browserSetting(longTimeOut,driver);
		WebDriverUtility.intiallizeActions(driver);
		LoginPage loginPage=new LoginPage(driver);
		HomePage homePage=new HomePage(driver);
		CampaignPage campaignPage=new CampaignPage(driver);
		CreateNewCampaignPage createNewCampaignPage=new CreateNewCampaignPage(driver);
		CampaignInformationPage campaignInformationPage=new CampaignInformationPage(driver);
		WebDriverUtility.navigateApp(url, driver);	
		loginPage.loginAction(username, password);
		homePage.clickCampaign(driver);
		campaignPage.clickCreateCampainLookUpImg();
		createNewCampaignPage.enterCampaignNameAndSave(campaignname);
		jutil.assertionThroughIfCondition(campaignInformationPage.getCampainName(), campaignname,"campaign");
		homePage.signout(driver);
		WebDriverUtility.quitBrowser(driver);
	}

}
