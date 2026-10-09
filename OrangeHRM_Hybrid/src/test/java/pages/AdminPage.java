package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminPage {

    private WebDriver driver;

    // Admin menu
    private By adminMenu = By.xpath("//span[text()='Admin']");



    public AdminPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickAdmin() {
        driver.findElement(adminMenu).click();
    }


    public void openUserManagement() {
        clickAdmin();
    }
}