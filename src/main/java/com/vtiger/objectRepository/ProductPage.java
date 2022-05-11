package com.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {
	
	@FindBy(xpath="//img[@title='Create Product...']")
	private WebElement createProductLookUpImg;
	
	public ProductPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void clickCreateProductLookUpImg() {
		createProductLookUpImg.click();
	}
}
