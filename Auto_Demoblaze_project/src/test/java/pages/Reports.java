package pages;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class Reports {

    private static ExtentReports extent;
    private static ExtentTest test;
    private static ExtentSparkReporter spark;

    public static ExtentReports getInstance() {
        if (extent == null) {
            extent = new ExtentReports();
            spark = new ExtentSparkReporter("report.html");
            extent.attachReporter(spark);
        }
        return extent;
    }

    public static void startTest(String testName) {
        test = extent.createTest(testName);
    }

    public static void logInfo(String message) {
        test.info(message);
    }

    public static void logPass(String message) {
        test.pass(message);
    }

    public static void logFail(String message) {
        test.fail(message);
    }

    public static void endReport() {
        extent.flush();
    }
}
