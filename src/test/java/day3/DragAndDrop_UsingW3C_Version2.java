package day3;

import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class DragAndDrop_UsingW3C_Version2 {

    public static void main(String[] args) throws Exception 
    {
        //Set device and app capabilities
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("ZD22284PKR");
        options.setPlatformName("Android");
        options.setPlatformVersion("13");
        options.setAutomationName("UiAutomator2");
        options.setAppPackage("io.appium.android.apis");
        options.setAppActivity("io.appium.android.apis.ApiDemos");

        //Connect to Appium server
        URL url = URI.create("http://127.0.0.1:4723/").toURL();
        AndroidDriver driver = new AndroidDriver(url, options);

        System.out.println("Application Started...");

        //Click on Views option
        driver.findElements(By.id("android:id/text1")).get(11).click();

        //Click on Drag and Drop option
        driver.findElements(By.id("android:id/text1")).get(7).click();

        //Find source and destination circles
        WebElement source = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_1"));
        WebElement destination = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_2"));

        //Get the center points of both elements
        Point start = getCenter(source);
        Point end = getCenter(destination);

        //Create finger (touch) input
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        //Create a sequence of actions (drag gesture)
        Sequence drag = new Sequence(finger, 1);

        //1. Move finger to source element
        drag.addAction(finger.createPointerMove(
                Duration.ZERO,                          //no delay
                PointerInput.Origin.viewport(),         //screen reference
                start.getX(), start.getY()));           //center of source

        //2. Finger touches down (press)
        drag.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

        //3. Move finger to destination element (drag)
        drag.addAction(finger.createPointerMove(
                Duration.ofMillis(600),                 //drag speed
                PointerInput.Origin.viewport(),
                end.getX(), end.getY()));               //center of target

        //4. Finger releases (drop)
        drag.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        //Perform the complete action
        driver.perform(Collections.singletonList(drag));

        System.out.println("Drag and drop completed!");

        driver.quit();
    }


    //Method to calculate center of any mobile element
    private static Point getCenter(WebElement element) {

        Point location = element.getLocation();   //top-left corner (x,y)
        Dimension size = element.getSize();       //width & height

        int centerX = location.x + size.width / 2;
        int centerY = location.y + size.height / 2;

        return new Point(centerX, centerY);       //return center point
    }
}
