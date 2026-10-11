package com.coforge.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddEmployeePage {
    public WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//a[text()='Add Employee']")
    private WebElement tabAddEmployee;

    @FindBy(name = "firstName")
    private WebElement txtFirstName;

    @FindBy(name = "middleName")
    private WebElement txtMiddleName;

    @FindBy(name = "lastName")
    private WebElement txtLastName;

    @FindBy(xpath = "//label[text()='Employee Id']/parent::div/following-sibling::div/input")
    private WebElement txtEmployeeId;

    @FindBy(css = "button[type='submit']")
    private WebElement btnSave;

    @FindBy(xpath = "//span[contains(@class,'input-field-error-message')]")
    private WebElement msgFieldError;

    // Toast message for successful save confirmation
    @FindBy(xpath = "//div[contains(@class,'oxd-toast-content--success')]")
    private WebElement toastSuccess;

    public AddEmployeePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void navigateToAddEmployeeTab() {
        wait.until(ExpectedConditions.elementToBeClickable(tabAddEmployee)).click();
    }

    public void enterEmployeeDetails(String firstName, String middleName, String lastName, String empId) {
        wait.until(ExpectedConditions.visibilityOf(txtFirstName));
        
        txtFirstName.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        txtFirstName.sendKeys(firstName);

        txtMiddleName.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        txtMiddleName.sendKeys(middleName);

        txtLastName.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        txtLastName.sendKeys(lastName);
        
        if (empId != null && !empId.trim().isEmpty()) {
            txtEmployeeId.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
            txtEmployeeId.sendKeys(empId);
        }
    }

    public void clickSave() {
        btnSave.click();
    }

    public boolean isEmployeeSavedSuccessfully() {
        try {
            // Verifies success toast notification or transition to personal details header
            return wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'oxd-toast-content')]")),
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Personal Details']"))
            ));
        } catch (Exception e) {
            return false;
        }
    }

    public String getFieldErrorText() {
        return wait.until(ExpectedConditions.visibilityOf(msgFieldError)).getText();
    }
}