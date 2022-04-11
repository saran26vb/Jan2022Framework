package com.qa.opencart.utils;

import java.util.Arrays;
import java.util.List;

public class Constants {
	
	public static final int DEFAULT_TIME_OUT = 5;
	public static final String LOGIN_PAGE_TITLE = "Account Login";
	
	public static final String LOGIN_PAGE_FRACTION_URL = "route=account/login";
	public static final String REGISTRATION_PAGE_FRACTION_URL = "route=account/register";
	public static final String ACCOUNTS_PAGE_TITLE = "My Account";
	public static final String REGISTRATION_PAGE_TITLE = "Register Account";
	public static final List<String> ACCOUNTS_SECTIONS_LIST = Arrays.asList("My Account", 
													"My Orders", 
													"My Affiliate Account", 
													"Newsletter");
	public static final String REGISTER_SUCCESS_MESSAGE = "Your Account Has Been Created";
	public static final String SHOPPING_CART_SUCCESS_MESSAGE = "Success: You have added";
	public static final String REGISTER_SHEET_NAME = "register";
	
	public static final String PRODUCT_SHEET_NAME = "product";
	public static final String TEST_DATA_SHEET_PATH = System.getProperty("user.dir") +"/src/test/resources/testdata/DemoCartTestData.xlsx";

}