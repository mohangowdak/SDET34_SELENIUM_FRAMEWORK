package com.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewProductPage {
	
	@FindBy(name ="productname")
	private WebElement productNameTxt;
	
	@FindBy(xpath ="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	

	public CreateNewProductPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		}
		
		public void enterProductNameAndSave(String productName) {
			productNameTxt.sendKeys(productName);
			saveBtn.click();
		}
}
