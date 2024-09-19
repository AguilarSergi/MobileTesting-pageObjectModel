package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.qa.BaseTest;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ProductsPage extends BaseTest{
	@AndroidFindBy (xpath = "//android.widget.TextView[@text=\\\"PRODUCTS\\\"]") 
	private WebElement productTitleText;

	
	public ProductsPage() {
		//PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}


	public String getTitle() {
		return getAttribute(productTitleText, "text");
	}
}

