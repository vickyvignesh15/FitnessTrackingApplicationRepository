package com.qa.utilities;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.xml.XmlSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentTestNGListener implements IReporter {

	private static final String OUTPUT_FOLDER = "TestOutputFolder";
	private static final String FILE_NAME = "ExtentReport.html";

	private ExtentReports extent;

	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		// TODO Auto-generated method stub
		init();

		for (ISuite suite : suites) {
			Map<String, ISuiteResult> result = suite.getResults();

			for (ISuiteResult r : result.values()) {
				ITestContext context = r.getTestContext();

				buildTestNodes(context.getFailedTests(), Status.FAIL);
				buildTestNodes(context.getSkippedTests(), Status.SKIP);
				buildTestNodes(context.getPassedTests(), Status.PASS);
			}
		}

		for (String s : Reporter.getOutput()) {
			extent.addTestRunnerOutput(s);
		}

		extent.flush();

	}

	// Initializing extend report
	private void init() {
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"\\Reports\\" + FILE_NAME);
		htmlReporter.config().setDocumentTitle("Fitness Tracking App Report");
		htmlReporter.config().setReportName("Fitness Tracking App Report - Created by Vigneshwer Gandhi");
		htmlReporter.config().setTheme(Theme.STANDARD);
		

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setReportUsesManualConfiguration(true);
		extent.setSystemInfo("Company", "OMNIFY");
		extent.setSystemInfo("Environment", "Framework Assignment");
	}

	private void buildTestNodes(IResultMap tests, Status status) {
		ExtentTest test;

		if (tests.size() > 0) {
			for (ITestResult result : tests.getAllResults()) {
				test = extent.createTest(result.getMethod().getMethodName());

				for (String group : result.getMethod().getGroups())
					test.assignCategory(group);

				if (result.getThrowable() != null) {
					test.log(status, result.getThrowable());
				} else {
					test.log(status, "Test " + status.toString().toLowerCase() + "ed");
				}

				test.getModel().setStartTime(getTime(result.getStartMillis()));
				test.getModel().setEndTime(getTime(result.getEndMillis()));
			}
		}
	}

	// Getting Time
	private java.util.Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}

}
