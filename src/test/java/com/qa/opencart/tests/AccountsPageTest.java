package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 200 - Design Accounts page for open cart application")
@Story("US 101 - design login page features")
@Story("US 102 - design accounts page features")
public class AccountsPageTest extends BaseTest {

	@Description("pre login for accounts page tests")
	@BeforeClass
	public void accPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	@Description("accounts Page Title Test")
	@Severity(SeverityLevel.NORMAL)
	public void accountsPageTitleTest() {
		String actAccountPageTitle = accPage.getAccountsPageTitle();
		System.out.println("Acc page title : " + actAccountPageTitle);
		Assert.assertEquals(actAccountPageTitle, Constants.ACCOUNTS_PAGE_TITLE);
	}

	@Test
	@Description("accounts Page header Test")
	@Severity(SeverityLevel.NORMAL)
	public void accPageHeaderTest() {
		Assert.assertTrue(accPage.isAccountsPageHeaderExist());
	}

	@Test
	@Description("search Exist Test")
	@Severity(SeverityLevel.CRITICAL)
	public void searchExistTest() {
		Assert.assertTrue(accPage.isSearchExist());
	}

	@Test
	@Description("acc Sections Test")
	@Severity(SeverityLevel.NORMAL)
	public void accSectionsTest() {
		List<String> actSecList = accPage.getAccountsPageSectionsList();
		System.out.println("Accounts Sections list = " + actSecList);
		Assert.assertEquals(actSecList, Constants.ACCOUNTS_SECTIONS_LIST);
	}
	
	@DataProvider
	public Object[][] getProductCountData()
	{
		return new Object[][] {
			{"MacBook",3},
			{"iMAC",1},
			{"Apple",1}
		};
	}
	
	@DataProvider
	public Object[][] getProductSearchData()
	{
		return new Object[][] {
			{"MacBook"},
			{"iMAC"},
			{"Apple"}
		};
	}
	
	@Test(dataProvider = "getProductSearchData")
	@Description("Search Header Test")
	@Severity(SeverityLevel.NORMAL)
	public void searchHeaderTest(String productName)
	{
		searchResultsPage = accPage.doSearch(productName);
		String searchHeader = searchResultsPage.getResultsPageHeaderValue();
		Assert.assertTrue(searchHeader.contains(productName));
	}
	
	@Test(dataProvider = "getProductCountData")
	@Description("check product count test after search")
	@Severity(SeverityLevel.CRITICAL)
	public void searchProductCountTest(String productName, int productCount)
	{
		searchResultsPage = accPage.doSearch(productName);
		int actProductCount = searchResultsPage.getProductSearchCount();
		Assert.assertEquals(actProductCount, productCount);
		
	}
	
	@Test(dataProvider = "getProductSearchData")
	@Description("check product list test after search")
	@Severity(SeverityLevel.CRITICAL)
	public void getSearchProductListTest(String productName)
	{
		searchResultsPage = accPage.doSearch(productName);
		List<String> actProductsList = searchResultsPage.getProductsResultsList();
		System.out.println(actProductsList);
	}

}
