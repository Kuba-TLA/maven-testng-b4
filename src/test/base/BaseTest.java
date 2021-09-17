package base;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.LoginPage;
import utils.ConfigReader;

import java.util.concurrent.TimeUnit;

public class BaseTest{
    public WebDriver driver;
    LoginPage loginPage;
    String configFilePath = "src/test/data/config/config.properties";

    @BeforeMethod(groups = "smokeTest")
    public void SetUp(){
        initializeDriver(ConfigReader.readProperty(configFilePath, "browser"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.get(ConfigReader.readProperty(configFilePath, "url"));
        loginPage = new LoginPage(driver);
        loginPage.logIn(ConfigReader.readProperty(configFilePath,"username"),
                ConfigReader.readProperty(configFilePath, "password"));
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
