package day5;

import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class LongPressV2 {
	
	    static AndroidDriver driver;

	    public static void main(String[] args) throws Exception 
	    {
	        // Set device capabilities
	        UiAutomator2Options options = new UiAutomator2Options();
	        options.setDeviceName("ZD22284PKR");
	        options.setPlatformName("Android");
	        options.setPlatformVersion("13");
	        options.setAutomationName("UiAutomator2");
	        options.setAppPackage("com.google.android.dialer");
	        options.setAppActivity("com.google.android.dialer.extensions.GoogleDialtactsActivity");

	        // Connect to Appium server
	        URL url = URI.create("http://127.0.0.1:4723/").toURL();
	        driver = new AndroidDriver(url, options);

	        // Open Dialpad
	        driver.findElement(By.id("com.google.android.dialer:id/dialpad_fab")).click();

	        // Type few digits
	        driver.findElement(By.id("com.google.android.dialer:id/zero")).click();
	        driver.findElement(By.id("com.google.android.dialer:id/one")).click();
	        driver.findElement(By.id("com.google.android.dialer:id/two")).click();
	        driver.findElement(By.id("com.google.android.dialer:id/five")).click();
	        driver.findElement(By.id("com.google.android.dialer:id/nine")).click();

	        // Locate the delete/backspace button
	        WebElement deleteBtn = driver.findElement(By.id("com.google.android.dialer:id/deleteButton"));

	        // Tap once (normal click)
	        deleteBtn.click();

	        // Wait for clarity
	        Thread.sleep(2000);

	        // Perform long press on delete button
	        longPress(deleteBtn);

	        driver.quit();
	    }

	    // long press method
	    static void longPress(WebElement element)
	    {
	        // Find the center of the element (long press should happen at center)
	        Point center = getCenter(element);

	        // Create a virtual finger for gesture
	        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

	        // Sequence = list of touch actions
	        Sequence longPress = new Sequence(finger, 1);

	        // 1. Move finger to element center
	        longPress.addAction(finger.createPointerMove(Duration.ZERO,
	                PointerInput.Origin.viewport(), center.x, center.y));

	        // 2. Finger touches the screen
	        longPress.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

	        // 3. Keep pressing for 1.5 seconds (long press)
	        longPress.addAction(new Pause(finger, Duration.ofMillis(1500)));

	        // 4. Finger leaves the screen
	        longPress.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

	        // Perform the gesture
	        driver.perform(Collections.singletonList(longPress));
	    }

	    // find element center
	    static Point getCenter(WebElement element)
	    {
	        int x = element.getLocation().getX() + (element.getSize().getWidth() / 2);
	        int y = element.getLocation().getY() + (element.getSize().getHeight() / 2);
	        return new Point(x, y);
	    }
	

}
