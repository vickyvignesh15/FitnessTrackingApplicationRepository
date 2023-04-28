package com.qa.utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import io.appium.java_client.android.AndroidDriver;

public class Listeners extends Utils implements ITestListener {

	
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("Tescase Execution Started: "+result.getName());
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("Testcase Passed: "+result.getName());
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("Testcase Failed: "+result.getName());
		String testMethodName = result.getMethod().getMethodName();
		AndroidDriver driver = null;
		
		try {
			driver = (AndroidDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(driver);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			takeScreenShotPath(testMethodName, driver);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("Testcase Execution Skipped: "+result.getName());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
