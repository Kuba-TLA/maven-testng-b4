package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MyInfoPage extends BasePage {
    private WebDriver driver;

    public MyInfoPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "menu_pim_viewMyDetails")
    public WebElement myInfoNavBtn;

    @FindBy(xpath = "//ul[@id='sidenav']/li/a")
    public List<WebElement> leftNavOptions;

}
