package com.qa.linkedin.testcases;

import org.testng.annotations.Test;

import com.qa.linkedin.base.TestBase;
import com.qa.linkedin.page.LinkdinHomePage;
import com.qa.linkedin.page.LinkedinInboxPage;
import com.qa.linkedin.page.SearchResultsPage;
import com.qa.linkedin.util.ExcelUtils;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.AfterClass;

public class SearchDDTest extends TestBase{
 private Logger log=Logger.getLogger(SearchDDTest.class);
 private LinkdinHomePage lhPg=null;
 private LinkedinInboxPage lboxPg=null;
 private SearchResultsPage srPg=null;
 private String path=System.getProperty("user.dir")+"\\src\\test\\java\\com\\qa\\linkedin\\data\\testdata.xlsx";
 
 
  @BeforeClass
  public void beforeClass() throws IOException {
	log.debug("Object initilization...");
	lhPg=new LinkdinHomePage();
	lboxPg=new LinkedinInboxPage();
	srPg=new SearchResultsPage();
	log.debug("Linkedin home page title verification starts");
	lhPg.verifyLinkedinHomePageTitle();
	lhPg.verifyLinkedinLogo();
	log.debug("do a click action on Sign in link");
	lhPg.clickSigninLink();
	log.debug("Linkedin login page title verification starts");
	lhPg.verifyLinkedinLoginPageTitle();
	lhPg.verifyWelcomeBackHeaderText();
	log.debug("do login process for site");
	lboxPg=lhPg.doLogin(readProperty("email"), readProperty("pwd"));
	log.debug("verify the profile rail card");
	lboxPg.verifyProfileRaildcard();
  }
  
  
  @Test(dataProvider= "searchData")
  public void searchTest(String keyword) throws InterruptedException {
	log.debug("started the searchTest for :"+keyword);
	srPg=lboxPg.doPeopleSearch(keyword);
	log.debug("navigate to search results page and verify the page title");
	srPg.verifySearchResultsPageTitle();
	int resultdata=srPg.getResultsCount();
	log.debug("search results count for:"+keyword+" is: "+resultdata);
	log.debug("navigate to linkedin loggedin page by clicking on HomeTab");
	srPg.clickHomeTab();
	  
  }
  
  @DataProvider
  public Object[][] searchData() throws InvalidFormatException, IOException{
	 Object[][] data=new ExcelUtils().getTestData(path, "Sheet1"); 
	 return data; 
  }
  
  

  @AfterClass
  public void afterClass() {
	  log.debug("perform the logout task");
	  
	  lboxPg.doSignout();
  }

}
