package com.qa;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.qa.test.LoginTest;
import com.qa.utils.TestUtils;
import org.testng.annotations.Parameters;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class BaseTest {
	protected static AppiumDriver driver;
	protected static Properties props;
	InputStream inputStream;
	
	public BaseTest() {
		//PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	
  @Parameters({"platformName", "platformVersion", "deviceName"})
  @BeforeTest
  public void beforeClass(String platformName, String platformVersion, String deviceName) throws Exception {
	  
	  try {
		  props = new Properties();
		  String propFileName = "config.properties";
		  
		  inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		  props.load(inputStream);
		  
			  DesiredCapabilities caps = new DesiredCapabilities();
			  caps.setCapability("platformName", platformName); 
			  caps.setCapability("appium:deviceName", deviceName); 
			  caps.setCapability("appium:platformVersion", platformVersion);
			  caps.setCapability("appium:automationName", props.getProperty("androidAutomationName")); 
			  caps.setCapability("appium:udid", props.getProperty("androidUdid")); 
			  caps.setCapability("appium:appPackage", props.getProperty("androidAppPackage"));
			  caps.setCapability("appium:appActivity", props.getProperty("androidAppActivity"));
				
			  String appURL = System.getProperty("user.dir")+File.separator+"src"+File.separator+"test"+File.separator+"resources"+File.separator+"app"+File.separator+"Android.SauceLabs.Mobile.Sample.app.2.7.1.apk";
			  caps.setCapability("appium:app", appURL); 
			  URL url = new URL(props.getProperty("appiumURL"));
			  driver = new AndroidDriver(url, caps);
			  String sessionId = driver.getSessionId().toString();
	  } catch (Exception e) {
		  e.printStackTrace();
		  throw e;
		  }
	    
  }
  
  public void waitForVisibility(WebElement e) {
	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TestUtils.WAIT)); //to not hardcode the value of wait
	  wait.until(ExpectedConditions.visibilityOf(e));
  }
  
  public void click(WebElement e) {
	  waitForVisibility(e);
	  e.click();
  }
  
  public void sendKeys(WebElement e, String text) {
	  waitForVisibility(e);
	  e.sendKeys(text);
  }
  public String getAttribute(WebElement e, String attribute) {
	  waitForVisibility(e);
	  return e.getAttribute(attribute);
  }

  @AfterTest
  public void afterClass() {
	  driver.quit();
  }

}
