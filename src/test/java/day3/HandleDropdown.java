package day3;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class HandleDropdown {

	public static void main(String[] args) throws Exception 
	{
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("ZD22284PKR"); //adb devices(cmd)
		options.setPlatformName("Android");
		options.setPlatformVersion("13");
		options.setAutomationName("UiAutomator2"); //appium(cmd)
		options.setAppPackage("io.appium.android.apis");
		options.setAppActivity("io.appium.android.apis.ApiDemos");
		
		URL url = URI.create("http://127.0.0.1:4723/").toURL(); // http://127.0.0.1:4723/ --> appium(cmd)
		
		AndroidDriver driver = new AndroidDriver(url, options);
		System.out.println("Application Started....");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		//click on Views option
		driver.findElements(By.id("android:id/text1")).get(11).click(); //index starts from '0'
		
		//click on Controls option
		driver.findElements(By.id("android:id/text1")).get(4).click(); //index starts from '0'
		
		//click on Light Theme option
		driver.findElements(By.id("android:id/text1")).get(0).click(); //index starts from '0'
		
		//click on dropdown
		wait.until(ExpectedConditions.elementToBeClickable(By.id("io.appium.android.apis:id/spinner1"))).click();
		
		//select Saturn option from dropdown
		WebElement saturnOption = driver.findElement(By.xpath("//android.widget.CheckedTextView[@resource-id=\"android:id/text1\" and @text=\"Saturn\"]"));
		saturnOption.click();
		
		System.out.println("Test Passed!!");
		driver.quit();
				

	}

}
