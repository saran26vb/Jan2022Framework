package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.Errors;

import io.qameta.allure.Step;



public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// 1. private by locators:
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwd = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");
	private By loginErrorMessg = By.cssSelector("div.alert.alert-danger.alert-dismissible");


	// 2. public page const....
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// 3. public page actions:
	public String getLoginPageTitle() {
		return eleUtil.waitForTitleIs(Constants.DEFAULT_TIME_OUT, Constants.LOGIN_PAGE_TITLE);
	}

	public String getLoginPageUrl() {
		return eleUtil.waitForUrl(Constants.DEFAULT_TIME_OUT, Constants.LOGIN_PAGE_FRACTION_URL);
	}

	public boolean isForgotPwdLinkExist() {
		return eleUtil.doIsDisplayed(forgotPwd);
	
	}

	public AccountsPage doLogin(String un, String pwd) {
		eleUtil.waitForElementToBeVisible(emailId, Constants.DEFAULT_TIME_OUT).sendKeys(un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
	}
	
	public boolean isRegisterLinkExist() {
		return eleUtil.waitForElementToBeVisible(registerLink, Constants.DEFAULT_TIME_OUT).isDisplayed();
	}
	
	public RegistrationPage navigateToRegisterPage() {
		if(isRegisterLinkExist()) {
			eleUtil.doClick(registerLink);
			return new RegistrationPage(driver);
		}
		return null;
	}


	@Step("login to application with incorrect username {0} and password {1}")
	public boolean doInvalidLogin(String un, String pwd) {
		WebElement email_ele = eleUtil.waitForElementToBeVisible(emailId, Constants.DEFAULT_TIME_OUT);
		email_ele.clear();
		email_ele.sendKeys(un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		String actualErrorMesg = eleUtil.doElementGetText(loginErrorMessg);
		System.out.println(actualErrorMesg);
			if(actualErrorMesg.contains(Errors.LOGIN_PAGE_ERROR_MESSG)) {
				return true;
			}
			return false;
	}
	

}