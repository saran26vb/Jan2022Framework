package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 300 - Design registration page for open cart application")
@Story("Story 101 - Design registration page features")
public class RegistrationPageTest extends BaseTest{
	
	@BeforeClass
	@Description("Navigate to Registration Page")
	public void regPageSetUp()
	{
		registrationPage = loginPage.navigateToRegisterPage();
	}
	
	@Test
	@Description("Registration Page Test")
	@Severity(SeverityLevel.CRITICAL)
	public void registrationPageUrlTest() {
		String regUrl = registrationPage.getRegistrationPageUrl();
		Assert.assertTrue(regUrl.contains(Constants.REGISTRATION_PAGE_FRACTION_URL));
	}
	
	@Test
	@Description("Registration Page Title Test")
	@Severity(SeverityLevel.NORMAL)
	public void registrationPageTitleTest()
	{
		String pageTitle = registrationPage.getRegistrationPageTitle();
		Assert.assertEquals(pageTitle, Constants.REGISTRATION_PAGE_TITLE);
	}
	
	@Test
	@Description("Registration Page Header Test")
	@Severity(SeverityLevel.NORMAL)
	public void registrationPageHeaderTest()
	{		
		Assert.assertTrue(registrationPage.isRegistrationPageHeaderExist());
	}
	
	public String getRandomEmail()
	{
		Random random = new Random();
		String randomEmail = "JanAutomation" +random.nextInt(1000)+"@gmail.com";
		return randomEmail;		
	}
	
	/*
	 * @DataProvider public Object[][] getRegisterData() { return new Object[][] {
	 * {"Nitesh","Agarwal","142423434","nitesh@123","yes"},
	 * {"Sam","Sundar","4242424","sam@123","no"},
	 * {"Gita","Raj","434342342342","Gita@124","yes"} }; }
	 */
	
	@DataProvider
	public Object[][] getRegisterData()
	{
		Object regData[][]=ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
		return regData;
	}

	@Test(dataProvider = "getRegisterData")
	@Description("User Registration Test")
	@Severity(SeverityLevel.BLOCKER)
	public void userRegistrationTest(String firstName, String lastName, String telephone, String password, String subscribe)
	{
		Assert.assertTrue(registrationPage.accountRegistration(firstName, lastName, getRandomEmail(), telephone, password, subscribe));
	}

}
