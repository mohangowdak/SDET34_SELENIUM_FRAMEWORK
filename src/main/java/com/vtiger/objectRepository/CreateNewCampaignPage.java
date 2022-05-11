package com.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sdet34l1.genericUtility.WebDriverUtility;

public class CreateNewCampaignPage {

	@FindBy(name ="campaignname")
	private WebElement campaignNameTxt;

	@FindBy(xpath ="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;

	@FindBy(xpath="//td[contains(.,'Product') and @class='dvtCellLabel']/following-sibling::td/img")
	private WebElement searchProductsLookupImg;

	public CreateNewCampaignPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void enterCampaignNameAndSave(String campaignName) {
		campaignNameTxt.sendKeys(campaignName);
		saveBtn.click();
	}

	public void enterCampaignNameAndSwitchToSearchProduct(String campaignName, WebDriver driver) {
		campaignNameTxt.sendKeys(campaignName);
		searchProductsLookupImg.click();
	}
	public void saveCampaign() {
		saveBtn.click();
	}
}
