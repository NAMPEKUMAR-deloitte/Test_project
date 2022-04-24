package com.cc.qa.util;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.cc.qa.base.FunzoneAdmin;
import com.cc.qa.util.TestUtil;
import com.relevantcodes.extentreports.LogStatus;

public class Listener extends FunzoneAdmin implements ITestListener{
	
	TestUtil testutil = new TestUtil();
	
	@Override
	public void onFinish(ITestContext arg0) {
		System.out.println("onFinish");
	}

	@Override
	public void onStart(ITestContext arg0) {
		System.out.println("onStart");
		
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		 System.out.println("New Test Started " +result.getName());
		 extentTest = extentReport.startTest(result.getName(), "Class : " + result.getInstanceName());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		System.out.println("onTestFailedButWithinSuccessPercentage");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("onTestFailure");
		String errorMSg = result.getThrowable().getMessage();
		String actionInfo = result.getName();
		//Extent report
		if(!(errorMSg==null))
		extentTest.log(LogStatus.FAIL, "DEFECT FOUND :: " + errorMSg);
		extentTest.log(LogStatus.FAIL, "Test failed due to precondition failure");		
		testutil.takeScreenshot(actionInfo.replaceAll(" ", "_"));
		extentTest.log(LogStatus.FAIL, "Screenshort of DEFECT :" + imgeHtmlPath);
		extentReport.endTest(extentTest);
		extentReport.flush();
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("onTestSkipped");
		if(!(result.getThrowable()==null))
			testutil.takeScreenshot(result.getName().replaceAll(" ", "_"));
			extentTest.log(LogStatus.SKIP, "Screenshort of Error :" + imgeHtmlPath);
			extentTest.log(LogStatus.SKIP, "Test skipped " + result.getThrowable());
			extentTest.log(LogStatus.SKIP, "Test skipped due to configuration failure");
		extentReport.endTest(extentTest);
		extentReport.flush();	
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		FunzoneAdmin.extentTest.log(LogStatus.PASS, "Test passed sucessfully");
		System.out.println("onTestSuccess");
		
		extentReport.endTest(extentTest);
		extentReport.flush();
	}
}
