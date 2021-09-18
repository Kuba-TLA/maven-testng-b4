package tests;

import base.BaseTest;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import data.testData.DataProviders;
import org.yaml.snakeyaml.error.Mark;
import pages.MyInfoPage;
import utils.Screenshot;

import java.util.ArrayList;
import java.util.List;

public class MyInfoTest extends BaseTest {
    MyInfoPage page;

    @BeforeMethod
    public void setUp(){
        page = new MyInfoPage(driver);
    }

    @Test(testName = "Orange HRM Info", dataProviderClass = DataProviders.class, dataProvider = "userInfo", groups = "smokeTest")
    public void orangeTest01(ArrayList<String> expectPanels){
        extentTest.assignAuthor("Kuba");
        extentTest.assignCategory("smoke");
        extentTest.assignDevice("Windows PC");

        // go to my info page
        page.click(page.myInfoNavBtn);
        extentTest.info("Navigated to My Info Page");
        extentTest.info("My Info Page",
                MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.takeScreenshot(driver)).build());

        // create list of actual panel details from WebElements
        List<WebElement> actPanels = page.leftNavOptions;
        List<String> actualPanelsForLog = new ArrayList<>();
        for(WebElement each: actPanels){
            actualPanelsForLog.add(each.getText());
        }

        extentTest.info("captured actual list of nave buttons on the left panel");
        extentTest.info(MarkupHelper.createUnorderedList(actualPanelsForLog));

        //comparing
        for (int i = 0; i < expectPanels.size(); i++) {
            Assert.assertEquals(actPanels.get(i).getText(), expectPanels.get(i));
        }

        String[][] data = new String[2][1];
        data[0][0] = actualPanelsForLog.toString();
        data[1][0] = expectPanels.toString();

        extentTest.pass(MarkupHelper.createTable(data));


    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void expectedExceptionTest(){
        System.setProperty("webdriver.chrome.driver", "E:\\_SCHOOL\\_Selenium\\Batch-4\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/validateCredentials");

        driver.findElement(By.id("txtUsernameTest")).sendKeys("Admin");

    }
}
