package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.AdminPage;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AdminTest extends BaseTest {
    AdminPage adminPage;

    @BeforeMethod
    public void setUp(){
        adminPage = new AdminPage(driver);
    }


    @Test(description = "Verify new nationality is added to the table", groups = {"smokeTest", "regression"})
    public void test01(){
        adminPage.highlightElement(adminPage.adminBtn);
        adminPage.adminBtn.click();

        adminPage.highlightElement(adminPage.nationalityBtn);
        adminPage.nationalityBtn.click();

        adminPage.highlightElement(adminPage.addBtn);
        adminPage.addBtn.click();

        adminPage.highlightElement(adminPage.addNationalityInput);
        adminPage.addNationalityInput.sendKeys("aaaaTestNationality");

        adminPage.highlightElement(adminPage.saveBtn);
        adminPage.saveBtn.click();

        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='some people']")) != null);
    }

    @Test(testName = "add new Username")
    public void test02() {
        String username = "aaaaTestUsername13";

        adminPage.adminBtn.click();
        adminPage.userMgtBtn.click();
        adminPage.addBtn.click();
        adminPage.addNewUser("Alice Duval", username, "Abc+123+321");

        adminPage.waitForElementClickability(adminPage.saveBtn);
        adminPage.saveBtn.click();

        if (adminPage.headerSystemUsers.size() < 1){
            adminPage.saveBtn.click();
        }

        adminPage.sleep(3000);
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()=" + username + "]")));
        adminPage.waitForElementClickability(driver.findElement((By.xpath("//*[text()=" + username + "]"))));
        int tableSizeBeforeDelete = adminPage.allTableRows.size();

        adminPage.userCheckbox.click();
        adminPage.deleteBtn.click();
        adminPage.confirmDeleteBtn.click();

        adminPage.sleep(2000);

        List <WebElement> users1 = driver.findElements(By.xpath("//tr/td/a"));
        int tableSizeAfterDelete = users1.size();

        Assert.assertNotEquals(tableSizeAfterDelete, tableSizeBeforeDelete);
    }

    @Test
    public void filterSearchTest(){
        adminPage.adminBtn.click();
        adminPage.selectRole("Admin");

        adminPage.sleep(5000);

        for(WebElement role: adminPage.allRoles){
            if (!role.getText().equals("Admin")){
                Assert.fail();
            }
        }

    }
}
