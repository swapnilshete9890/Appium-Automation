package day6;

import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class DrawingOrPerformSignatureGesture {

	public static void main(String[] args) throws Exception 
	{
		UiAutomator2Options options = new UiAutomator2Options();

		options.setDeviceName("ZD22284PKR");
		options.setPlatformName("Android");
		options.setPlatformVersion("13");
		options.setAutomationName("UiAutomator2");
		options.setAppPackage("com.saucelabs.mydemoapp.rn");
		options.setAppActivity("com.saucelabs.mydemoapp.rn.MainActivity");

		URL url = URI.create("http://127.0.0.1:4723/").toURL();

		AndroidDriver driver = new AndroidDriver(url, options);

		//click on three lines on left corner
		driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"open menu\"]/android.widget.ImageView")).click();

		//click on drawing menu option
		driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"menu item drawing\"]")).click();

		//drawing pad/signature pad
		WebElement signpad = driver.findElement(By.xpath("//android.view.View[@resource-id=\'signature-pad\']/android.view.View/android.widget.Image[2]"));

		Point location = signpad.getLocation();//getLocation() return a point, containing the location of the top left-hand corner of signpad
		System.out.println(location);
		Dimension size = signpad.getSize(); // getSize method gives heigth and width
        System.out.println(size);
        
		//find the position where you need to touch
		int startX = (location.x + (size.getWidth()/2));
		int startY = location.y + 100;

		//position till you want to move your finger to sign/draw/swipe
		int endX = startX;
		int endY = (location.y + size.getHeight());

		//PointerInput class to create a sequence of actions
		PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");

		//Sequence object, which is a list of actions that will be performed on the device

		Sequence sequence = new Sequence(finger1, 1);

		sequence.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
				.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
				.addAction(new Pause(finger1, Duration.ofMillis(200)))
				.addAction(finger1.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), endX, endY))
				.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		
		//perform sequence of actions
		driver.perform(Arrays.asList(sequence));
		
		driver.quit();




















	}

}
