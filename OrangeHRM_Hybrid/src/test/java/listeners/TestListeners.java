
package listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import base.BaseTest;
import utilities.ExtentReport;
import utilities.ScreenshotUtil;

public class TestListeners implements ITestListener {

    private static ExtentReports extent =
            ExtentReport.getReportInstance();

    private static ThreadLocal<ExtentTest> test =
            new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest extentTest =
                extent.createTest(
                        result.getMethod().getMethodName());

        test.set(extentTest);

        test.get().info("Test execution started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        test.get().pass("Test passed successfully");

        System.out.println(
                "TEST PASSED: "
                + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.get().fail(result.getThrowable());

        System.out.println(
                "TEST FAILED: "
                + result.getMethod().getMethodName());

        try {
            BaseTest testClass =
                    (BaseTest) result.getInstance();

            String screenshotPath =
                    ScreenshotUtil.takeScreenshot(
                            testClass.driver,
                            result.getMethod().getMethodName()
                                    + "_FAILED");

            test.get().fail(
                    "Failure screenshot",
                    MediaEntityBuilder
                            .createScreenCaptureFromPath(
                                    screenshotPath)
                            .build());

            System.out.println(
                    "Screenshot saved at: " + screenshotPath);

        } catch (IOException | RuntimeException e) {

            test.get().warning(
                    "Screenshot could not be captured: "
                            + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        test.get().skip("Test skipped");

        if (result.getThrowable() != null) {
            test.get().skip(result.getThrowable());
        }
    }

    @Override
    public void onFinish(ITestContext context) {

        extent.flush();

        System.out.println(
                "HTML report generated at: "
                + new java.io.File(
                        ExtentReport.getReportPath())
                        .getAbsolutePath());
    }
}