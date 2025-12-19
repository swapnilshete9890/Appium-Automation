package day3;     

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

public class DragAndDrop_UsingW3C {

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
        
        System.out.println("Application Started..");
        
        //Click on Views
        driver.findElements(By.id("android:id/text1")).get(11).click();
        
        //click on Drag and Drop option
        driver.findElements(By.id("android:id/text1")).get(7).click();
        
        //By using W3C class
        
        WebElement source = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_1"));
        
        WebElement destination = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_2"));
        
        // find center of source and target web element
        Point sourceElementCenter = getCenter(source);
        Point destinationElementCenter = getCenter(destination);
        
      //PointerInput class to create a sequence of actions 
		//that move the pointer to the center of the element, 
		//press down on the element, and then release the element.
        
       PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH,"finger1");
       
       //Sequence object, which is a list of actions that will be performed on the device
       Sequence sequence = new Sequence (finger1, 1)
               
               //move finger to the starting position
               .addAction(finger1.createPointerMove(
                       Duration.ZERO, 
                       PointerInput.Origin.viewport(), 
                       sourceElementCenter.getX(), 
                       sourceElementCenter.getY()
               ))
               
               //finger coming down to contact with screen
               .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
               
               .addAction(new Pause(finger1, Duration.ofMillis(588)))
       
               //move finger to the end position
               .addAction(finger1.createPointerMove(
                       Duration.ofMillis(588), 
                       PointerInput.Origin.viewport(), 
                       destinationElementCenter.getX(), 
                       destinationElementCenter.getY()
               ))
               
               //move the finger up
               .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
       
       //perform sequence of actions
       driver.perform(Arrays.asList(sequence));
       
       driver.quit();
	}
	private static Point getCenter(WebElement element) {

	    // get location of the element
	    Point location = element.getLocation();

	    // get dimension (width & height)
	    Dimension size = element.getSize();

	    // calculate the center point
	    Point center = new Point(location.x + size.width / 2, location.y + size.height / 2 );

	    return center;
	}


}
