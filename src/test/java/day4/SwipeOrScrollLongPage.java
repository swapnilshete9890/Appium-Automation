package day4;

import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class SwipeOrScrollLongPage {

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
		
		//click on view option
		driver.findElements(By.id("android:id/text1")).get(11).click();
		
		//Get Screen Size (width x height)
		Dimension size = driver.manage().window().getSize();
		System.out.println("Screen size of device is: " + size);

		//Mid of the screen (start point of swipe)
		int startX = size.getWidth() / 2;
		int startY = size.getHeight() / 2;

		//Swipe UP → end point should be higher (smaller Y value)
		int endX = startX;
		int endY = (int) (size.getHeight() * 0.25);

		//Create virtual finger
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger1");

		//Create sequence of swipe actions
		Sequence swipe = new Sequence(finger, 1)
				.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
				.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
				.addAction(new Pause(finger, Duration.ofMillis(200)))   //pause for realistic swipe
				.addAction(finger.createPointerMove(Duration.ofMillis(300), PointerInput.Origin.viewport(), endX, endY))
				.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		//Perform the swipe
		driver.perform(Collections.singletonList(swipe));

		driver.quit();

		
	}

}
