package day5;

import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class LongPressOnButton {

	static AndroidDriver driver;

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

		driver = new AndroidDriver(url, options);

		//click on dailpad
		driver.findElement(By.id("com.google.android.dialer:id/dialpad_fab")).click();

		//click on number 012348765
		driver.findElement(By.id("com.google.android.dialer:id/zero")).click();
		driver.findElement(By.id("com.google.android.dialer:id/one")).click();
		driver.findElement(By.id("com.google.android.dialer:id/two")).click();
		driver.findElement(By.id("com.google.android.dialer:id/three")).click();
		driver.findElement(By.id("com.google.android.dialer:id/four")).click();
		driver.findElement(By.id("com.google.android.dialer:id/eight")).click();
		driver.findElement(By.id("com.google.android.dialer:id/seven")).click();
		driver.findElement(By.id("com.google.android.dialer:id/six")).click();
		driver.findElement(By.id("com.google.android.dialer:id/five")).click();
		
		//click on backspace button/delete button
		WebElement backspace_Btn = driver.findElement(By.id("com.google.android.dialer:id/deleteButton"));
		backspace_Btn.click();
		
		Thread.sleep(3000);
		//long press on backspace button/delete button
		longPress(backspace_Btn);
		
		driver.quit();
   }
	
	static void longPress(WebElement element)
	{
		//find location of webelement
		Point location = element.getLocation();
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		
		Sequence longpress = new Sequence(finger, 1);
		longpress.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), location.x, location.y));
		longpress.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		longpress.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), location.x, location.y));
		longpress.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		
		//perform the sequence of action
		driver.perform(Collections.singletonList(longpress));		
	}

}
