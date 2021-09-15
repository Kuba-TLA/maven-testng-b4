package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import data.testData.DataProviders;
import pages.LoginPage;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyInfoTest {

    @Test(testName = "Orange HRM Info", dataProviderClass = DataProviders.class, dataProvider = "userInfo", groups = "smokeTest")
    public void orangeTest01(String uName, String pWord, List expectPanels){
        System.setProperty("webdriver.chrome.driver", "E:\\_SCHOOL\\_Selenium\\Batch-4\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/validateCredentials");

        // login to the website
        driver.findElement(By.id("txtUsername")).sendKeys(uName);
        driver.findElement(By.id("txtPassword")).sendKeys(pWord);
        driver.findElement(By.id("btnLogin")).click();

        // go to my info page
        driver.findElement(By.id("menu_pim_viewMyDetails")).click();

        // create list of actual panel details from WebElements
        List<WebElement> actPanels = driver.findElements(By.xpath("//ul[@id='sidenav']/li/a"));

        for (int i = 0; i < expectPanels.size(); i++) {
            Assert.assertEquals(actPanels.get(i).getText().toString(), expectPanels.get(i));
        }

        driver.close();
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void expectedExceptionTest(){
        System.setProperty("webdriver.chrome.driver", "E:\\_SCHOOL\\_Selenium\\Batch-4\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/validateCredentials");

        driver.findElement(By.id("txtUsernameTest")).sendKeys("Admin");
    }
}
