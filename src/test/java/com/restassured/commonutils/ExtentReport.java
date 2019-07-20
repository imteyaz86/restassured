package com.restassured.commonutils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


public class ExtentReport implements ITestListener, ISuiteListener, IInvokedMethodListener {
		
	
	private ExtentReports extent;
	
	@Override
	public void onStart(ISuite suite){
		try {
			String dtmCurrentTimeInfo;
			String dtmCurrentDateInfo;
			PropertyReader properties = null;
			String extentReportsLocation;
			
			//Current Date Is Generated
            DateFormat dateformat = new SimpleDateFormat("dd-MMM-yyyy");
            Date date = new Date();
            dtmCurrentDateInfo = dateformat.format(date);
            //Current Time Is Generated
            DateFormat timeformat = new SimpleDateFormat("hhmmss");
            dtmCurrentTimeInfo = timeformat.format(date);
            properties = new PropertyReader("utilities.properties");
            
            extentReportsLocation = properties.getPropertyValue("ExtentReportsLocation");
            extentReportsLocation = extentReportsLocation
                    + "_" + dtmCurrentDateInfo + "_" + dtmCurrentTimeInfo + ".html";     
            
            ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(extentReportsLocation);
            // initialize ExtentReports and attach the HtmlReporter
             extent = new ExtentReports();
            // attach only HtmlReporter
            extent.attachReporter(htmlReporter);

		}catch (Throwable t) {
			System.out.println("Error Message In Extent Report On Start  Is "+t.toString());
		}
	}

	@Override
	public void onFinish(ISuite suite) {
		extent.flush();
		
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		//System.out.println("Test Name Is"+result.getTestName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentTest logger;
		logger =extent.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
		logger.assignCategory(result.getMethod().getGroups());
		logger.log(Status.PASS, "Test Passed Successfully");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		ExtentTest logger;
		String errorMessageAtCodeLineNo= null;
		if (result.getThrowable() != null) {
			
			for (StackTraceElement ObjectName : result.getThrowable().getStackTrace())
			{  System.out.println(" message is "+ObjectName.toString());
				if(ObjectName.toString().contains("scripts") )
				{   System.out.println("Error message is "+ObjectName.toString());
					errorMessageAtCodeLineNo = "Error Occured In  : "+" "+ObjectName.toString(); 
					break;
				}
			}
		
		logger =extent.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
		logger.assignCategory(result.getMethod().getGroups());
		logger.log(Status.FAIL, errorMessageAtCodeLineNo);
		logger.log(Status.FAIL, result.getThrowable());
		}
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		ExtentTest logger;
		logger =extent.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
		logger.assignCategory(result.getMethod().getGroups());
		logger.log(Status.SKIP, "Test Is Skipped");
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		method.getTestMethod();
		
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		// TODO Auto-generated method stub
		
	}

}
