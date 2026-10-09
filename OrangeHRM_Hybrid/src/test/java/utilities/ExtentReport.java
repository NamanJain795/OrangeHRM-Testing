
package utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {

    private static ExtentReports extent;
    private static String reportPath;

    public static ExtentReports getReportInstance() {

        if (extent == null) {

            File folder = new File("test-output");
            folder.mkdirs();

            String timestamp =
                    new SimpleDateFormat("yyyyMMdd_HHmmss")
                            .format(new Date());

            reportPath = new File(
                    folder,
                    "TestReport_" + timestamp + ".html"
            ).getAbsolutePath();

            ExtentSparkReporter reporter =
                    new ExtentSparkReporter(reportPath);

            reporter.config().setDocumentTitle("Test Execution Report");
            reporter.config().setReportName("Automation Test Results");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
        }

        return extent;
    }

    public static String getReportPath() {
        return reportPath;
    }

    public static ExtentTest createTest(String testName) {
        return getReportInstance().createTest(testName);
    }

    public static void flushReport() {
        if (extent != null) {
            extent.flush();
            System.out.println(
                    "HTML report generated at: " + reportPath
            );
        }
    }
}