package day1;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class ApplicationAPKInstallation {

	public static void main(String[] args) throws MalformedURLException, InterruptedException
	{
		UiAutomator2Options options = new UiAutomator2Options();
		
		options.setDeviceName("AndroidEmulator");
		options.setPlatformName("Android");
		options.setPlatformVersion("13");
		options.setAutomationName("UiAutomator2");
		options.setApp("C:\\Users\\Lenovo\\OneDrive\\Desktop\\Appium\\Apk files\\sample Calculator_1.0_APKPure.apk");
	   
		
		URL url = URI.create("http://127.0.0.1:4723/").toURL();  //http://127.0.0.1:4723/  ---> get from cmd appium server 
		
		AndroidDriver driver = new AndroidDriver(url, options);
		
		Thread.sleep(5000);
		
		System.out.println("Application Started.....");
		
		driver.quit();
		

	}

}
