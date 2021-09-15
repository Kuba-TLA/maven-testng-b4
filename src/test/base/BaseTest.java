package base;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pages.LoginPage;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    public WebDriver driver;
    LoginPage loginPage;

    @BeforeMethod(groups = "smokeTest")
    @Parameters({"username", "password"})
    public void SetUp(@Optional("Admin") String username, @Optional("admin123") String password){
        initializeDriver("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.get("https://opensource-demo.orangehrmlive.com/index.php/pim/viewMyDetails");
        loginPage = new LoginPage(driver);
        loginPage.logIn(username, password);
    }

    @AfterMethod(groups = "smokeTest")
    public void tearDown(){
        driver.close();
    }

    public void initializeDriver(String browser){
        switch (browser){
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
}
