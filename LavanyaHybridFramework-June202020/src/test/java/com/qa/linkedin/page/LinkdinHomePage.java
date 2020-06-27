package com.qa.linkedin.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.qa.linkedin.base.TestBase;

public class LinkdinHomePage extends TestBase{
private Logger log = Logger.getLogger(LinkdinHomePage.class);
	public LinkdinHomePage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="a.nav__logo-link")
	private WebElement linkedinLogo;
	
	@FindBy(linkText="Sign in")
	private WebElement signinLink;
	
	@FindBy(css="h1.header__content__heading")
	private WebElement welcomeBackHeaderText;
	
	@FindBy(id="username")
	private WebElement emailEditbox;
	
	@FindBy(name="session_password")
	private WebElement passwordEditbox;
	
	@FindBy(xpath="//button[@type='submit']")
	private WebElement signinBtn;
	
	
	public void verifyLinkedinHomePageTitle() {
		log.debug("verifying the Linkedin Home page title");
		wait.until(ExpectedConditions.titleContains("LinkedIn: Log In or Sign Up"));
		Assert.assertEquals(driver.getTitle(), "LinkedIn: Log In or Sign Up");
	}
	
	public void verifyLinkedinLogo() {
		log.debug("verify the linkedin logo in home page");
		wait.until(ExpectedConditions.visibilityOf(linkedinLogo));
		Assert.assertTrue(linkedinLogo.isDisplayed());
	}
	
	
	public void clickSigninLink() {
		log.debug("performing the click action on signin Link");
		wait.until(ExpectedConditions.visibilityOf(signinLink));
		wait.until(ExpectedConditions.elementToBeClickable(signinLink));
		signinLink.click();
	}
	
	public void verifyLinkedinLoginPageTitle() {
		log.debug("verifying the Linkedin Login page title");
		wait.until(ExpectedConditions.titleContains("LinkedIn Login, Sign in | LinkedIn"));
		Assert.assertEquals(driver.getTitle(), "LinkedIn Login, Sign in | LinkedIn");
	}
	
	public void verifyWelcomeBackHeaderText() {
		log.debug("verify the linkedin login page - welcomeBack header text");
		wait.until(ExpectedConditions.visibilityOf(welcomeBackHeaderText));
		Assert.assertTrue(welcomeBackHeaderText.isDisplayed());
	}
	
	public void clickSigninBtn() {
		log.debug("performing the click action on signin Button");
		wait.until(ExpectedConditions.visibilityOf(signinBtn));
		wait.until(ExpectedConditions.elementToBeClickable(signinBtn));
		signinBtn.click();
	}
	
	
	public LinkedinInboxPage doLogin(String email,String pwd) {
		log.debug("started logging to the Linkedin website");
		log.debug("clear the content in email editbox");
		emailEditbox.clear();
		log.debug("type the value: "+email+" in email editbox");
		emailEditbox.sendKeys(email);
		log.debug("clear the content in password editbox");
		passwordEditbox.clear();
		log.debug("type the value: "+pwd+" in password editbox");
		passwordEditbox.sendKeys(pwd);
		clickSigninBtn();
		return new LinkedinInboxPage();
	}
}
