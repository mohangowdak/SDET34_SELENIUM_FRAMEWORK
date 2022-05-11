package com.vtiger.practice;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.common.io.Files;
import com.sdet34l1.genericUtility.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class javaScriptExecutor {
	public static void main(String[] args) throws IOException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		WebDriverUtility.intiallizeJs(driver);
		driver.manage().window().maximize();
		WebDriverUtility.navigateApplicationThroughJs("http://localhost:8888");
		WebDriverUtility.enterDataThroughJs(driver.findElement(By.name("user_name")), "admin");
		WebDriverUtility.enterDataThroughJs(driver.findElement(By.name("user_password")), "admin");
		WebDriverUtility.clickThroughJs(driver.findElement(By.id("submitButton")));
		WebDriverUtility.scrollTillElement(driver.findElement(By.xpath("//b[contains(.,'Upcoming Activities')]")));

		String fileName=new javaScriptExecutor().getClass().getName();
		WebDriverUtility.takeScreenShot(fileName, driver);

	}

}
