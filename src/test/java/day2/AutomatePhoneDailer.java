package day2;

import java.net.URI;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class AutomatePhoneDailer {

	public static void main(String[] args) throws Exception 
	{
		UiAutomator2Options options = new UiAutomator2Options();
		
		options.setDeviceName("ZD22284PKR");
		options.setPlatformName("Android");
		options.setPlatformVersion("13");
		options.setAutomationName("UiAutomator2");
		options.setAppPackage("com.google.android.dialer");
		options.setAppActivity("com.google.android.dialer.extensions.GoogleDialtactsActivity");
		 
		URL url = URI.create("http://127.0.0.1:4723/").toURL();
		
		AndroidDriver driver = new AndroidDriver(url, options);
		System.out.println("Application Started.......");
		
		//click on dailpad
		driver.findElement(By.id("com.google.android.dialer:id/dialpad_fab")).click();
		
		//click on number *123#
		driver.findElement(By.id("com.google.android.dialer:id/star")).click();
		driver.findElement(By.id("com.google.android.dialer:id/one")).click();
		driver.findElement(By.id("com.google.android.dialer:id/two")).click();
		driver.findElement(By.id("com.google.android.dialer:id/three")).click();
		driver.findElement(By.id("com.google.android.dialer:id/pound")).click();
		
		//click on call button
		driver.findElement(By.id("com.google.android.dialer:id/dialpad_voice_call_button")).click();
		
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		//wait for display sim list and select 1 sim from 2 sim option
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.ListView[@resource-id=\"com.google.android.dialer:id/select_dialog_listview\"]/android.widget.LinearLayout[1]"))).click();
		
		System.out.println("Test Pass...");
		driver.quit();
	      
		
	}

}
