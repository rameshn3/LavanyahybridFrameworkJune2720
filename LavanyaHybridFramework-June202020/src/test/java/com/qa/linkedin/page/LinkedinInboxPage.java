package com.qa.linkedin.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.qa.linkedin.base.TestBase;

public class LinkedinInboxPage extends TestBase{
private Logger log = Logger.getLogger(LinkedinInboxPage.class);
	public LinkedinInboxPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="div[class*='profile-rail-card']")
	private WebElement profileRailCard;
	
	@FindBy(css="input[class*='search-global-typeahead']")
	private WebElement searchEditbox;
	
	@FindBy(css="img[class*='nav-item__profile-member-photo']")
	private WebElement profileImageIcon;
	
	@FindBy(xpath="//*[@data-control-name='nav.settings_signout']")
	private WebElement signoutLink;
	
	public void verifyProfileRaildcard() {
		log.debug("verify the profile raild card in linkedin loggedin page");
		wait.until(ExpectedConditions.visibilityOf(profileRailCard));
		Assert.assertTrue(profileRailCard.isDisplayed());
	}
	
	public SearchResultsPage doPeopleSearch(String keyword) throws InterruptedException {
		log.debug("performing the people search for :"+keyword);
		log.debug("clear the content in search editbox");
		searchEditbox.clear();
		log.debug("type the search keyword:"+keyword+" in search editbox");
		searchEditbox.sendKeys(keyword);
		log.debug("press the ENTER key from keyboard");
		searchEditbox.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		return new SearchResultsPage();
	}
	
	public void doSignout() {
		log.debug("performing the sign out from logged in page");
		log.debug("click on Profile image icon");
		wait.until(ExpectedConditions.visibilityOf(profileImageIcon));
		profileImageIcon.click();
		log.debug("wait for the sign out link");
		wait.until(ExpectedConditions.visibilityOf(signoutLink));
		log.debug("click on signout link");
		signoutLink.click();
	}
	
	
}
