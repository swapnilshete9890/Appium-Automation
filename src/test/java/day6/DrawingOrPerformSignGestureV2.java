package day6;

	import java.net.URI;
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

	public class DrawingOrPerformSignGestureV2 {

	    public static void main(String[] args) throws Exception {

	        UiAutomator2Options options = new UiAutomator2Options();
	        options.setDeviceName("ZD22284PKR");
	        options.setPlatformName("Android");
	        options.setPlatformVersion("13");
	        options.setAutomationName("UiAutomator2");
	        options.setAppPackage("com.saucelabs.mydemoapp.rn");
	        options.setAppActivity("com.saucelabs.mydemoapp.rn.MainActivity");

	        AndroidDriver driver = new AndroidDriver(
	                URI.create("http://127.0.0.1:4723/").toURL(), options);

	        // Open Menu
	        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"open menu\"]/android.widget.ImageView")).click();

	        // Click on Drawing option
	        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"menu item drawing\"]")).click();

	        Thread.sleep(3000);
	        
	        // Signature pad
	        WebElement signpad = driver.findElement(By.xpath("//android.view.View[@resource-id=\'signature-pad\']/android.view.View/android.widget.Image[2]"));
	        

	        // Get position
	        Point location = signpad.getLocation();
	        Dimension size = signpad.getSize();

	        // Starting point
	        int startX = location.x + size.width / 4;
	        int startY = location.y + size.height / 3;

	        // Curve points
	        int midX = startX + 200;
	        int midY = startY + 150;
	        int endX = startX + 300;
	        int endY = startY + 250;

	        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger1");

	        Sequence sequence = new Sequence(finger, 1)
	                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
	                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
	                .addAction(new Pause(finger, Duration.ofMillis(100)))
	                .addAction(finger.createPointerMove(Duration.ofMillis(300), PointerInput.Origin.viewport(), midX, midY))
	                .addAction(new Pause(finger, Duration.ofMillis(100)))
	                .addAction(finger.createPointerMove(Duration.ofMillis(300), PointerInput.Origin.viewport(), endX, endY))
	                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

	        driver.perform(Arrays.asList(sequence));

	        driver.quit();
	    }
	}



