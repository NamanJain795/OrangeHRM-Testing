package com.coforge.listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.coforge.base.BaseTest;
import com.coforge.utilities.ExtentManager;
import com.coforge.utilities.ScreenshotUtility;

public class ExtentTestNGListener implements ITestListener {

    private static ExtentReports extent = ExtentManager.getinstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        if (test.get() != null) {
            test.get().log(Status.PASS, "Test Passed: " + result.getMethod().getMethodName());
        }
        try {
            // Takes screenshot on failure
            String screenshotPath = ScreenshotUtility.capture(BaseTest.driver, result.getName());
            if (screenshotPath != null) {
                test.get().addScreenCaptureFromPath(screenshotPath);
            }
        } catch (Exception e) {
            test.get().log(Status.INFO, "Failed to attach screenshot: " + e.getMessage());
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        if (test.get() != null) {
            test.get().log(Status.FAIL, "Test Failed: " + result.getThrowable());
            try {
                // Takes screenshot on failure
                String screenshotPath = ScreenshotUtility.capture(BaseTest.driver, result.getName());
                if (screenshotPath != null) {
                    test.get().addScreenCaptureFromPath(screenshotPath);
                }
            } catch (Exception e) {
                test.get().log(Status.INFO, "Failed to attach screenshot: " + e.getMessage());
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        if (test.get() != null) {
            test.get().log(Status.SKIP, "Test Skipped: " + result.getMethod().getMethodName());
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        if (extent != null) {
            extent.flush();
        }
    }
}
