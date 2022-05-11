package com.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sdet34l1.genericUtility.WebDriverUtility;

// create the class as webpage name and make it as public
public class HomePage {
	// declare all the elements and specify the accesss specifier as private
	@FindBy(linkText = "More")
	private WebElement moreDropDown;
	
	@FindBy(linkText = "Products")
	private WebElement productsTab;
	
	@FindBy(linkText = "Campaigns")
	private WebElement campainsTab;
	
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement admistrorIcon;
	
	@FindBy(linkText  = "Sign Out")
	private WebElement signOutLink;
	
	
	//intiallize the driver address to all the elements through constructors and make it as pubilc
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	
	//business lib
	public void clickCampaign(WebDriver driver) {
		WebDriverUtility.mouseHoverOntheElement(moreDropDown, driver);
		campainsTab.click();
	}
	
	public WebElement getMoreDropDown() {
		return moreDropDown;
	}
	
	public void clickProduct() {
		productsTab.click();
	}
	public void signout(WebDriver driver) {
		WebDriverUtility.mouseHoverOntheElement(admistrorIcon, driver);
		signOutLink.click();
	}
	
	
	
	
}
