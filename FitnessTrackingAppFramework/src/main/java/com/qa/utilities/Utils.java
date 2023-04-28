package com.qa.utilities;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Utils {

	
	public static AndroidDriver driver;
	public static String appiumServer = "0.0.0.0";
	public static int appiumPort = 4723;
	public static URL appiumURL = null;
	public static Properties prop;

	// Initializing Android Driver
	public AndroidDriver initializeDriver() {
		try {
			appiumURL = new URL("https://" + appiumServer + ":" + appiumPort + "/wd/hub");
			this.driver = new AndroidDriver(appiumURL, setAppCapagilitiesAndroid());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return driver;
	}

	// Setting Desired Capabilities
	public DesiredCapabilities setAppCapagilitiesAndroid() {
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-554");
		dc.setCapability(MobileCapabilityType.UDID, "emulator-5554");
		dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.0");
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "ANDROID");
		dc.setCapability("appPackage", "com.android.calculator2");
		dc.setCapability("appActivity", "com.android.calculator2.Calculator");

		return dc;
	}

	// Taking Screenshot
	public String takeScreenShotPath(String testCaseName, AndroidDriver driver) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File sourse = ts.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir") + "/reports/" + testCaseName + ".png";
		FileUtils.copyFile(sourse, new File(destinationFile));
		return destinationFile;

	}

}
