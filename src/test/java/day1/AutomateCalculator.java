package day1;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class AutomateCalculator {

	public static void main(String[] args) throws InterruptedException, MalformedURLException {
		
        UiAutomator2Options options = new UiAutomator2Options();
		
		options.setDeviceName("motorola moto g72");
		options.setPlatformName("Android");
		options.setPlatformVersion("13");
		options.setAutomationName("UiAutomator2");
		options.setAppPackage("com.google.android.calculator");
		options.setAppActivity("com.android.calculator2.Calculator");
	   
		
		URL url = URI.create("http://127.0.0.1:4723/").toURL();  //http://127.0.0.1:4723/  ---> get from cmd appium server 
		
		AndroidDriver driver = new AndroidDriver(url, options);
		
		Thread.sleep(5000);
		
		System.out.println("Application Started.....");
		
		
		//click on number 8
		WebElement num8 = driver.findElement(By.id("com.google.android.calculator:id/digit_8"));
		num8.click();
		
		//click on '+'
		driver.findElement(By.id("com.google.android.calculator:id/op_add")).click();
		
		//click on number 6
		driver.findElement(By.id("com.google.android.calculator:id/digit_6")).click();
		
		//click on '='
	     driver.findElement(By.id("com.google.android.calculator:id/eq")).click();
	     
		//capture result
		String result = driver.findElement(By.id("com.google.android.calculator:id/result_final")).getText();

		if(result.equals("14"))
		{
			System.out.println("Pass...");
		}
		else
		{
			System.out.println("Fail...");
		}
		
		driver.quit();
	}

}
//Refactor Version 1:
/*
      UiAutomator2Options options = new UiAutomator2Options();

options.setUdid("ZD22284PKR");
options.setPlatformName("Android");
options.setPlatformVersion("13");
options.setAutomationName("UiAutomator2");
options.setAppPackage("com.google.android.calculator");
options.setAppActivity("com.android.calculator2.Calculator");

URL url = URI.create("http://127.0.0.1:4723/").toURL();
AndroidDriver driver = new AndroidDriver(url, options);

WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

wait.until(ExpectedConditions.elementToBeClickable(
    By.id("com.google.android.calculator:id/digit_8")
)).click();

driver.findElement(By.id("com.google.android.calculator:id/op_add")).click();
driver.findElement(By.id("com.google.android.calculator:id/digit_6")).click();
driver.findElement(By.id("com.google.android.calculator:id/eq")).click();

String result = wait.until(
    ExpectedConditions.visibilityOfElementLocated(
        By.id("com.google.android.calculator:id/result_final")
    )
).getText();

System.out.println(result.equals("14") ? "Pass..." : "Fail...");

driver.quit();

 */


//Refactor Version 2(advance):
/*
  public static void main(String[] args) throws Exception {

    // 1️ - Set Appium options
    UiAutomator2Options options = new UiAutomator2Options();
    options.setUdid("ZD22284PKR"); // use adb devices
    options.setPlatformName("Android");
    options.setPlatformVersion("13");
    options.setAutomationName("UiAutomator2");
    options.setAppPackage("com.google.android.calculator");
    options.setAppActivity("com.android.calculator2.Calculator");

    // 2️ - Start Appium session
    URL url = URI.create("http://127.0.0.1:4723").toURL();
    AndroidDriver driver = new AndroidDriver(url, options);

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    System.out.println("Application Started.....");

    // 3️ - Perform calculation: 8 + 6 =
    click(wait, By.id("com.google.android.calculator:id/digit_8"));
    click(wait, By.id("com.google.android.calculator:id/op_add"));
    click(wait, By.id("com.google.android.calculator:id/digit_6"));
    click(wait, By.id("com.google.android.calculator:id/eq"));

    // 4️- Capture and validate result
    String result = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                    By.id("com.google.android.calculator:id/result_final")
            )
    ).getText();

    if ("14".equals(result)) {
        System.out.println("Pass...");
    } else {
        System.out.println("Fail... Result was: " + result);
    }

    // 5️ - Close session
    driver.quit();
}

// 🔹 Reusable click method
private static void click(WebDriverWait wait, By locator) {
    wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
}
*/
