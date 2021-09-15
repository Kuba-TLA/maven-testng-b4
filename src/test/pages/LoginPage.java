package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    protected WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "txtUsername")
    public WebElement usernameInput;

    @FindBy(id = "txtPassword")
    public WebElement passwordInput;

    @FindBy(id = "btnLogin")
    public WebElement loginBtn;

    public void logIn(String username, String password){
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginBtn.click();
    }
}
