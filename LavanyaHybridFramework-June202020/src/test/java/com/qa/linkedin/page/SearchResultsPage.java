package com.qa.linkedin.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.qa.linkedin.base.TestBase;

public class SearchResultsPage extends TestBase{
private Logger log = Logger.getLogger(SearchResultsPage.class);
	
public SearchResultsPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="h3[class*='search-results__total']")
	private WebElement searchResultsText;
	
	@FindBy(xpath = "//span[contains(@id,'feed-tab-icon')]")
	private WebElement homeTab;
	
	public void verifySearchResultsPageTitle() {
		log.debug("verifying the verifySearchResultsPageTitle");
		wait.until(ExpectedConditions.titleContains("Search | LinkedIn"));
		Assert.assertTrue(driver.getPageSource().contains("Search | LinkedIn"));
	}
	
	public int getResultsCount() {
		
		log.debug("fetch the results count text");
		String txt=searchResultsText.getText();
		log.debug("search results text is-->"+txt);
		//txt="Showing 33,667 results";
		String[] str=txt.split(" ");
		log.debug("search results count :"+str[1]);
		
		return Integer.parseInt(str[1].replace(",", ""));
	}

	public void clickHomeTab() {
		log.debug("performing the click action on HomeTab...");
		wait.until(ExpectedConditions.visibilityOf(homeTab));
		wait.until(ExpectedConditions.elementToBeClickable(homeTab));
		homeTab.click();
	}
	
}
