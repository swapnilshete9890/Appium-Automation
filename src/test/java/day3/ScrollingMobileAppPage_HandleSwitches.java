package day3;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class ScrollingMobileAppPage_HandleSwitches{

	public static void main(String[] args) throws Exception 
	{
		UiAutomator2Options options = new UiAutomator2Options();
		
		options.setDeviceName("ZD22284PKR");
		options.setPlatformName("Android");
		options.setPlatformVersion("13");
		options.setAutomationName("UiAutomator2");
		options.setAppPackage("io.appium.android.apis");
		options.setAppActivity("io.appium.android.apis.ApiDemos");
		
		URL url = URI.create("http://127.0.0.1:4723/").toURL();
		
		AndroidDriver driver = new AndroidDriver(url, options);
		
		System.out.println("Application started....");
		
		//click on views
		driver.findElements(By.id("android:id/text1")).get(11).click();
		
		//scroll to switches
		String ElementToScroll = "Switches";
		
		WebElement switchElement = driver.findElement(AppiumBy.androidUIAutomator(
				              "new UiScrollable(new UiSelector().scrollable(true))" +
		                     ".scrollIntoView(new UiSelector().text(\"" + ElementToScroll + "\"))"));
		switchElement.click();
		
		//ON Monitored switch
		WebElement monitoredSwitch = driver.findElement(By.id("io.appium.android.apis:id/monitored_switch"));
		monitoredSwitch.click();
		
		//Verification of Monitored switch
		
		String switchState = monitoredSwitch.getAttribute("checked");
		
		if(switchState.equals("true"))
		{
			System.out.println("TEST PASSED: Monitored Switch is ON");
		}
		else
		{
			System.out.println("TEST FAILED: Monitored Switch is OFF");
		}
		
		
         driver.quit();
	}

}









/*
Refactor: 
    public static void scrollToText(AndroidDriver driver, String text) {
    driver.findElement(AppiumBy.androidUIAutomator(
        "new UiScrollable(new UiSelector().scrollable(true))" +
        ".scrollIntoView(new UiSelector().text(\"" + text + "\"))"
    ));
    
    scrollToText(driver, "Switches");

}

*/