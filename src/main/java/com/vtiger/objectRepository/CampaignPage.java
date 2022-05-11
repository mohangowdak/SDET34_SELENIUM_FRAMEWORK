package com.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CampaignPage {
	
	@FindBy(xpath="//img[@title='Create Campaign...']")
	private WebElement createCampainLookUpImg;
	
	public CampaignPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void clickCreateCampainLookUpImg() {
		createCampainLookUpImg.click();
	}
}
