package tests;

import base.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import utils.Screenshot;

import java.util.concurrent.TimeUnit;

public class reportExample extends BaseTest {
    @Test
    public void test1(){
        //Test case 1
        ExtentTest extentTest = extentReports.createTest("Verify New User Test");
        extentTest.assignAuthor("Kuba");
        extentTest.assignCategory("smoke");
        extentTest.assignDevice("Windows PC");

        extentTest.pass("Test PASS marker");

        driver.get("https://google.com");
        extentTest.info("Google page",
                MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.takeScreenshot(driver)).build());

    }


}
