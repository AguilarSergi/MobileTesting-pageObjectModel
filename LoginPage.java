package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.qa.BaseTest;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginPage extends BaseTest{
	@AndroidFindBy (accessibility = "test-Username") private WebElement userNameTextField;
	@AndroidFindBy (accessibility = "test-Password") private WebElement passwordTextField;
	@AndroidFindBy (accessibility = "test-LOGIN") private WebElement loginButton;
	@AndroidFindBy (xpath = "//android.widget.TextView[\"\r\n"+ 
	"\"@text=\\\"Username and password do not match any user in"
			+ " this service.\\\"]") private WebElement errorText;
	
	
	
	public LoginPage() {
		//PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public LoginPage enterUserName(String userName) {
		sendKeys(userNameTextField, userName);
		return this;
	}
	
	public LoginPage enterPassword(String password) {
		sendKeys(passwordTextField, password);
		return this;
	}
	public ProductsPage pressLoginButton() {
		click(loginButton);
		return new ProductsPage();
	}
	public String getErrorText() {
		return getAttribute(errorText, "text");
	}
}

