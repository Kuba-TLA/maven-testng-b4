package base;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.*;
import pages.LoginPage;
import utils.ConfigReader;
import utils.Screenshot;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public WebDriver driver;
    protected ExtentReports extentReports;
    protected ExtentTest extentTest;
    LoginPage loginPage;
    String configFilePath = "src/test/data/config/config.properties";

    @BeforeMethod(groups = "smokeTest")
    public void SetUp(Method method) {
        initializeDriver(ConfigReader.readProperty(configFilePath, "browser"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        extentTest = extentReports.createTest(getCustomTestName(method));
        logTestGroups(method);

        driver.get(ConfigReader.readProperty(configFilePath, "url"));
        loginPage = new LoginPage(driver);
        loginPage.logIn(ConfigReader.readProperty(configFilePath, "username"),
                ConfigReader.readProperty(configFilePath, "password"));
    }

    @AfterMethod(groups = "smokeTest")
    public void tearDown() {
        driver.close();
    }

    @BeforeSuite
    public void startReporter() {
        //initiating extent report
        extentReports = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("reports.html");

        //adding some configurations
        spark.config().setTheme(Theme.STANDARD);
        spark.config().setDocumentTitle("My Report");
        spark.config().setReportName("Orange HRM Tests");
        extentReports.attachReporter(spark);
    }

    @AfterSuite
    public void closeReporter() {
        //closing the extent reporter
        extentReports.flush();
    }

    public void initializeDriver(String browser) {
        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "ie":
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
        }
    }

    public void logTestGroups(Method method){
        Test testClass = method.getAnnotation(Test.class);
        for(String e: testClass.groups()){
            extentTest.assignCategory(e);
        }
    }

    public String getCustomTestName(Method method){
        Test testClass = method.getAnnotation(Test.class);

        if(testClass.testName().length() > 0)
            return testClass.testName();
        return method.getName();
    }

    public void logScreenshot(String title){
        extentTest.info(title,
                MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.takeScreenshot(driver)).build());
    }

    public void logScreenshot(){
        extentTest.info(MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.takeScreenshot(driver)).build());
    }

}
