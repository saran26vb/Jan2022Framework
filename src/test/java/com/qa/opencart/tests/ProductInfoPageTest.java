package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.ProductInfoPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 400 - Design Product Info Page Test")
@Story("Story 101 - Product Info Test")
public class ProductInfoPageTest extends BaseTest {
	
	@BeforeClass
	@Description("Design account page set up")
	public void accPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] getProductData()
	{
		return new Object[][] {
			{"MacBook","MacBook Pro"},
			{"MacBook","MacBook Air"},
			{"Apple","Apple Cinema 30\""}
		};
	}
	
	 @Test(dataProvider = "getProductData")
	 @Description("Product Info Header Test")
	 @Severity(SeverityLevel.NORMAL)
	 public void productInfoHeaderTest(String productName, String mainProductName) {
	  searchResultsPage = accPage.doSearch(productName); 
	  productInfoPage = searchResultsPage.selectProduct(mainProductName);
	  Assert.assertEquals(productInfoPage.getProductHeaderText(), mainProductName);
	  }
	 
	
	@DataProvider
	public Object[][] getProductImgData()
	{
		return new Object[][] {
			{"MacBook","MacBook Pro",4},
			{"MacBook","MacBook Air",4},
			{"Apple","Apple Cinema 30\"", 6}
		};
	}
	/*
	 * @Test(dataProvider = "getProductImgData") public void
	 * productImagesTest(String productName, String mainProductName, int imgCount) {
	 * searchResultsPage = accPage.doSearch(productName); productInfoPage =
	 * searchResultsPage.selectProduct(mainProductName);
	 * Assert.assertEquals(productInfoPage.getProductImagesCount(), imgCount); }
	 */
	
	@DataProvider
	public Object[][] getProductInfoData()
	{
		return new Object[][] {
			{"MacBook","MacBook Pro", "Apple", "800", "$2,000.00","Product 18"},
			{"MacBook","MacBook","Apple","600", "$500.00","Product 16"}		
		};
	}
	
	@Test(dataProvider = "getProductInfoData")
	@Description("Product Info Test")
	@Severity(SeverityLevel.NORMAL)
	public void productInfoTest(String productName, String mainProduct, String brand, String rewardPoints, String price, String productCode)
	{
		searchResultsPage = accPage.doSearch(productName);
		productInfoPage= searchResultsPage.selectProduct(mainProduct);
		Map<String,String> actProductInfoMap = productInfoPage.getProductInfo();
		actProductInfoMap.forEach((k,v)->System.out.println(k + ":" + v));
		
		softAssert.assertEquals(actProductInfoMap.get("productName"),mainProduct);
		softAssert.assertEquals(actProductInfoMap.get("Brand"), brand);
		softAssert.assertEquals(actProductInfoMap.get("Reward Points"), rewardPoints);
	    softAssert.assertEquals(actProductInfoMap.get("price"), price);
	    softAssert.assertEquals(actProductInfoMap.get("Product Code"), productCode);
	    softAssert.assertAll();
	    
	}
	
	@Test
	@Description("Add to Cart Test")
	@Severity(SeverityLevel.BLOCKER)
	public void addProductToCartTest()
	{
		searchResultsPage = accPage.doSearch("MacBook"); 
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Assert.assertTrue(productInfoPage.addProductToCart("2"));
	}

}
