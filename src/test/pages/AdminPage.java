package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class AdminPage extends BasePage {
    protected WebDriver driver;

    public AdminPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "Admin")
    public WebElement adminBtn;

    @FindBy(id = "menu_admin_nationality")
    public WebElement nationalityBtn;

    @FindBy(id = "btnAdd")
    public WebElement addBtn;

    @FindBy(id = "nationality_name")
    public WebElement addNationalityInput;

    @FindBy(id = "btnSave")
    public WebElement saveBtn;

    @FindBy(xpath = "//*[text()='User Management']")
    public WebElement userMgtBtn;

    @FindBy(id = "systemUser_employeeName_empName")
    public WebElement employeeNameInput;

    @FindBy(id = "systemUser_userName")
    public WebElement usernameInput;

    @FindBy(id = "systemUser_password")
    public WebElement passwordInput;

    @FindBy(id = "systemUser_confirmPassword")
    public WebElement confirmPasswordInput;

    @FindBy(xpath = "//*[text()='System Users']")
    public List<WebElement> headerSystemUsers;

    @FindBy(xpath = "//tr/td/a")
    public List<WebElement> allTableRows;

    @FindBy(xpath = "//*[text()='aaaaTestUsername13']/parent::td/preceding-sibling::td")
    public WebElement userCheckbox;

    @FindBy(id = "btnDelete")
    public WebElement deleteBtn;

    @FindBy(id = "dialogDeleteBtn")
    public WebElement confirmDeleteBtn;

    @FindBy(id = "searchSystemUser_userType")
    public WebElement selectRole;

    @FindBy(id = "searchBtn")
    public WebElement searchBtn;

    @FindBy(xpath = "//tbody/tr/td[3]")
    public List<WebElement> allRoles;

    public void addNewUser(String employeeName, String username, String password){
        employeeNameInput.sendKeys(employeeName);
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        confirmPasswordInput.sendKeys(password);
        saveBtn.click();
    }

    public void selectRole(String role){
        Select select = new Select(selectRole);
        select.selectByVisibleText(role);
        searchBtn.click();
    }


}
