package day4;                           //Application Installtion, Terminate app, check app already installed or not

import java.net.URI;
import java.net.URL;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class AppManagement {

	public static void main(String[] args) throws Exception 
	{
		 UiAutomator2Options options = new UiAutomator2Options();
		 
		 options.setDeviceName("ZD22284PKR");
		 options.setPlatformName("Android");
		 options.setPlatformVersion("13");
		 options.setAutomationName("UiAutomator2");
		 
		 URL url = URI.create("http://127.0.0.1:4723/").toURL();
		 
		 AndroidDriver driver = new AndroidDriver(url, options);
		 
		 String packageName = "com.bak.mnr.calculatrice";
		 
		 //remove application
		 driver.removeApp(packageName);
		 
		 if(!driver.isAppInstalled(packageName))
		 {
		 
		 //install application(Sample Calculator app)
		 driver.installApp("C:\\Users\\Lenovo\\OneDrive\\Desktop\\Appium\\Apk files\\sample Calculator_1.0_APKPure.apk");
		 
		 //activate app
		 driver.activateApp(packageName);
		 System.out.println("Application Installed Successfully...");
		 }
		 else
		 {
			 System.out.println("Application already installed ");
		 }
		 
		 Thread.sleep(3000);
		 //terminate application
		 driver.terminateApp(packageName);
		 System.out.println("Application Terminated");
		 
		 driver.quit();
		 
		 

	}

}
