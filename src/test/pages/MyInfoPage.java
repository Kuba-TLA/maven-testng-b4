package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class MyInfoPage {
    private WebDriver driver;

    public MyInfoPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


}
