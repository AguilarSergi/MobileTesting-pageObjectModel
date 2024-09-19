package com.qa.test;

import org.testng.annotations.Test;
import java.lang.reflect.*;
import com.github.dockerjava.transport.DockerHttpClient.Request.Method;
import com.qa.BaseTest;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductsPage;
import io.appium.java_client.AppiumBy;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import static org.testng.Assert.assertEquals;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;

public class LoginTest extends BaseTest{
	LoginPage loginPage; //initialize loginPage
	ProductsPage productsPage; 
	
	@BeforeClass
	  public void beforeClass() {
	  }

	  @AfterClass
	  public void afterClass() {
	  }
	  
	  @BeforeMethod
	  public void beforeMethod(Method m) {
		  loginPage = new LoginPage(); //every test will start here, so we have to place it in beforeMethod
		  System.out.println("\n" + "***** starting test: " + m.name() + "*****" + "\n");
	  }

	  @AfterMethod
	  public void afterMethod() {
	  }
	
	  @Test
	  public void invalidUserName() throws InterruptedException {
		  //Thread.sleep(2000);
		  loginPage.enterUserName("invalidusername");
		  loginPage.enterPassword("secret_sauce");
		  loginPage.pressLoginButton();
		  
		  String actualErrorText = loginPage.getErrorText();
		  String expectedErrorText = "Username and password do not match any user in this service.";
		  System.out.println("actual error text: " + actualErrorText + "\n" + "expected error text: " 
		  + expectedErrorText);
		  
		  assertEquals(actualErrorText, expectedErrorText);
	  }
	  
	  @Test
	  public void validUserName() throws InterruptedException {
		  //Thread.sleep(3000);
		  loginPage.enterUserName("standard_user");
		  loginPage.enterPassword("secret_sauce");
		  productsPage = loginPage.pressLoginButton();
		  
		  Thread.sleep(2000);
		  String actualProductTitle = productsPage.getTitle();
		  String expectedProductTtitle = "PRODUCTS";
		  System.out.println("actual product text: " + actualProductTitle + "\n" + "expected product text: " 
				  + expectedProductTtitle);
		  assertEquals(actualProductTitle, expectedProductTtitle);
	  }
}
