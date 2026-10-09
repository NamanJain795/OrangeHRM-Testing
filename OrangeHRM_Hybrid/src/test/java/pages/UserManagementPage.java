package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserManagementPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Add button
    private By addButton =
            By.xpath("//button[normalize-space()='Add']");

    // Add User form
    private By userRole =
            By.xpath("(//div[contains(@class,'oxd-select-text')])[1]");

    private By employeeName =
            By.xpath("//input[@placeholder='Type for hints...']");

    private By username =
            By.xpath("//label[text()='Username']/following::input[1]");

    private By statusDropdown =
            By.xpath("//label[normalize-space()='Status']/following::div[contains(@class,'oxd-select-text')][1]");

    private By password =
            By.xpath("//label[normalize-space()='Password']/following::input[@type='password'][1]");

    private By confirmPassword =
            By.xpath("//label[normalize-space()='Confirm Password']/following::input[@type='password'][1]");

    private By saveButton =
            By.xpath("//button[normalize-space()='Save']");

    // Add User heading
    private By addUserHeading =
            By.xpath("//h6[normalize-space()='Add User']");
    
 // Success message
    private By successMessage =
            By.xpath("//div[contains(@class,'oxd-toast-content')]");

    // System Users heading
    private By systemUsersHeading =
            By.xpath("//h5[normalize-space()='System Users']");
    
 // Search User locators
    private By searchUsername =
            By.xpath("//label[normalize-space()='Username']/following::input[1]");

    private By searchButton =
            By.xpath("//button[normalize-space()='Search']");

    private By resetButton =
            By.xpath("//button[normalize-space()='Reset']");

    private By userTableRows =
            By.xpath("//div[@class='oxd-table-body']//div[@role='row']");

    private By noRecordsFound =
            By.xpath("//*[normalize-space()='No Records Found']");

    
    public UserManagementPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickAdd() {
    	wait.until(
    	        ExpectedConditions.elementToBeClickable(addButton)
    	    ).click();
    }


    public boolean isAddUserPageDisplayed() {
    	return wait.until(
    	        ExpectedConditions.visibilityOfElementLocated(addUserHeading)
    	    ).isDisplayed();
    }


    public void enterEmployeeName(String name) {

        wait.until(
            ExpectedConditions.visibilityOfElementLocated(employeeName)
        ).sendKeys(name);
    }


    public void enterUsername(String userName) {

        wait.until(
            ExpectedConditions.visibilityOfElementLocated(username)
        ).sendKeys(userName);
    }


    public void enterPassword(String pass) {

        wait.until(
            ExpectedConditions.visibilityOfElementLocated(password)
        ).sendKeys(pass);
    }


    public void enterConfirmPassword(String pass) {

        wait.until(
            ExpectedConditions.visibilityOfElementLocated(confirmPassword)
        ).sendKeys(pass);
    }


    public void clickSave() {

        wait.until(
            ExpectedConditions.elementToBeClickable(saveButton)
        ).click();
    }
    
    public void selectUserRole(String role) {

        wait.until(
            ExpectedConditions.elementToBeClickable(userRole)
        ).click();

        By roleOption =
                By.xpath("//div[@role='option']//span[text()='" + role + "']");

        wait.until(
            ExpectedConditions.elementToBeClickable(roleOption)
        ).click();
    }
    
    public void selectStatus(String status) {

        wait.until(
            ExpectedConditions.elementToBeClickable(statusDropdown)
        ).click();

        By statusOption = By.xpath(
            "//div[contains(@class,'oxd-select-option')][normalize-space()='" 
            + status + "']"
        );

        wait.until(
            ExpectedConditions.elementToBeClickable(statusOption)
        ).click();
    }
    
 // Enter username in the Search User form
    public void enterSearchUsername(String username) {
        WebElement field = wait.until(
                ExpectedConditions.visibilityOfElementLocated(searchUsername)
        );

        field.clear();
        field.sendKeys(username);
    }

    // Click the Search button
    public void clickSearch() {
        wait.until(
                ExpectedConditions.elementToBeClickable(searchButton)
        ).click();
    }

    // Check whether the expected username appears in the results
    public boolean isUsernamePresentInResults(String username) {
        wait.until(
                ExpectedConditions.or(
                        ExpectedConditions.visibilityOfElementLocated(userTableRows),
                        ExpectedConditions.visibilityOfElementLocated(noRecordsFound)
                )
        );

        for (WebElement row : driver.findElements(userTableRows)) {
            if (row.getText().contains(username)) {
                return true;
            }
        }

        return false;
    }

    // Reset the search form
    public void clickReset() {
        wait.until(
                ExpectedConditions.elementToBeClickable(resetButton)
        ).click();
    }
    
}