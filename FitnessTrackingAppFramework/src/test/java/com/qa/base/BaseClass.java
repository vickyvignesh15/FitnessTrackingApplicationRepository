package com.qa.base;

import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.utilities.Utils;

import io.appium.java_client.MobileDriver;
public class BaseClass extends Utils{

	
// Pre-Requisites to run the test cases
@BeforeTest
public void setup() {
	driver = initializeDriver();
}

//Post-Requisites to run the test cases
@AfterTest(alwaysRun=true)
public void tearDown(ITestResult testRestult) {
	//this.driver.quit();
}
}
