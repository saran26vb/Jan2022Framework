package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegistrationPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By header = By.cssSelector("div#content h1");
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPassword = By.id("input-confirm");
	private By subscribeYes = By.xpath("//label[@class='radio-inline']//input[@type='radio' and @value='1']");
	private By subscribeNo = By.xpath("//label[@class='radio-inline']//input[@type='radio' and @value='0']");
	private By agreeChkBox = By.name("agree");
	private By continueBtn = By.xpath("//input[@type='submit']");
	private By successMsg = By.xpath("//div[@id='content']/h1");
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	
	public RegistrationPage(WebDriver driver)
	{
		this.driver= driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public String getRegistrationPageUrl()
	{
		return eleUtil.waitForUrl(Constants.DEFAULT_TIME_OUT, Constants.REGISTRATION_PAGE_FRACTION_URL);
	}
	
	public String getRegistrationPageTitle() {
		return eleUtil.waitForTitleIs(Constants.DEFAULT_TIME_OUT, Constants.REGISTRATION_PAGE_TITLE);
	}
	
	public boolean isRegistrationPageHeaderExist() {
		return eleUtil.doIsDisplayed(header);
	}
	
	public boolean accountRegistration(String firstName,String lastName, String email, String telephone, String password, String subscribe)
	{
		eleUtil.waitForElementToBeVisible(this.firstName, Constants.DEFAULT_TIME_OUT).sendKeys(firstName);
		eleUtil.doSendKeys(this.lastName, lastName);
	    eleUtil.doSendKeys(this.email, email);
	    eleUtil.doSendKeys(this.telephone, telephone);
	    eleUtil.doSendKeys(this.password, password);
	    eleUtil.doSendKeys(this.confirmPassword, password);
	    
	    if(subscribe.equalsIgnoreCase("Yes"))
	    {
	    	eleUtil.doClick(subscribeYes);
	    }
	    else
	    {
	    	eleUtil.doClick(subscribeNo);
	    }
		eleUtil.doClick(agreeChkBox);
		eleUtil.doClick(continueBtn);
		if(getAccountRegisterSuccessMessage().contains(Constants.REGISTER_SUCCESS_MESSAGE))
		{
			goToRegisterPage();
			return true;
		}
		return false;
	}
	
	public String getAccountRegisterSuccessMessage()
	{
		return eleUtil.waitForElementToBeVisible(successMsg, Constants.DEFAULT_TIME_OUT).getText();
	}
	
	private void goToRegisterPage()
	{
		eleUtil.doClick(logoutLink);
		eleUtil.doClick(registerLink);
	}

}
