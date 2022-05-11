package com.vtiger.practice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HandleNotificationPopup {
public static void main(String[] args) {
	WebDriverManager.chromedriver().setup();
	ChromeOptions opt=new ChromeOptions();
	opt.addArguments("--disable-notifications");
	WebDriver driver=new ChromeDriver(opt);
	driver.get("https://www.spicejet.com/");
}
}
