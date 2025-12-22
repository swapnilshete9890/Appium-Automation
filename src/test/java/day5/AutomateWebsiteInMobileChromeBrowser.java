package day5;

import java.net.URI;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class AutomateWebsiteInMobileChromeBrowser {

	public static void main(String[] args) throws Exception 
	{
		UiAutomator2Options options = new UiAutomator2Options();
		
		options.setDeviceName("ZD22284PKR");
		options.setPlatformName("Android");
		options.setPlatformVersion("13");
		options.setAutomationName("UiAutomator2");
		options.withBrowserName("Chrome");
		options.setChromedriverExecutable(System.getProperty("user.dir") + "/driver/chromedriver.exe");
				
		URL url =  URI.create("http://127.0.0.1:4723/").toURL();
		
		AndroidDriver driver = new AndroidDriver(url, options);
		
		driver.get("https://www.google.com/");
		
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("Taj Mahal");
		searchBox.sendKeys(Keys.ENTER);
		
		// Wait for result page to load
		Thread.sleep(3000);
        //try block use to handle "I am not a robot" checkbox if it is displayed
		try {
		    // Step 1: Switch to CAPTCHA iframe
		    WebElement captchaFrame = driver.findElement(
		        By.cssSelector("iframe[title='reCAPTCHA']")
		    );
		    driver.switchTo().frame(captchaFrame);
		    System.out.println("Switched to CAPTCHA iframe");

		    // Step 2: Click the checkbox
		    WebElement checkBox = driver.findElement(
		        By.cssSelector("div.recaptcha-checkbox-border")
		    );
		    checkBox.click();
		    System.out.println("Clicked on 'I am not a robot' checkbox");

		    Thread.sleep(4000); // Wait for verification
		    
		    // Step 3: Return to main page
		    driver.switchTo().defaultContent();
		} 
		catch (Exception e) {
		    System.out.println("No CAPTCHA found on this page");
		}

		//Verify title contains search text
		String title = driver.getTitle();

		if(title.contains("Taj Mahal")) 
		{
		    System.out.println("PASS: Search result loaded correctly.");
		}
		else 
		{
		    System.out.println("FAIL: Search result NOT loaded.");
		}

		driver.quit();
		
		
		
		

	}

}
