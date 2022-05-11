package com.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CampaignInformationPage {
	@FindBy(id="dtlview_Campaign Name")
	private WebElement campaignNameText;
	
	public CampaignInformationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public String getCampainName() {
		return campaignNameText.getText();
	}
	
	
}
