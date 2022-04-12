package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ResultsPage {
	
	private WebDriver driver;
	
	By resultsLink =By.linkText("ResultsLink");
	
	public ResultsPage(WebDriver driver)
	{
		this.driver=driver;
	}

}
