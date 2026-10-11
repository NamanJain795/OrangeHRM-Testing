package com.coforge.pages;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmployeeListPage {
    public WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//a[text()='Employee List']")
    private WebElement tabEmployeeList;

    @FindBy(xpath = "//label[text()='Employee Name']/parent::div/following-sibling::div//input")
    private WebElement txtSearchEmployeeName;

    @FindBy(xpath = "//label[text()='Employee Id']/parent::div/following-sibling::div/input")
    private WebElement txtSearchEmployeeId;

    @FindBy(css = "button[type='submit']")
    private WebElement btnSearch;

    @FindBy(xpath = "//button[normalize-space()='Reset']")
    private WebElement btnReset;

    @FindBy(xpath = "//div[@class='oxd-table-card']")
    private List<WebElement> tableResultRows;

    @FindBy(xpath = "//span[text()='No Records Found']")
    private WebElement msgNoRecords;

    public EmployeeListPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void navigateToEmployeeListTab() {
        wait.until(ExpectedConditions.elementToBeClickable(tabEmployeeList)).click();
    }

    public void searchByName(String name) {
        wait.until(ExpectedConditions.visibilityOf(txtSearchEmployeeName));
        txtSearchEmployeeName.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        txtSearchEmployeeName.sendKeys(name);
        
        // Wait briefly for OrangeHRM auto-complete options to appear if applicable
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
        clickSearch();
    }

    public void searchById(String empId) {
        wait.until(ExpectedConditions.visibilityOf(txtSearchEmployeeId));
        txtSearchEmployeeId.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        txtSearchEmployeeId.sendKeys(empId);
        clickSearch();
    }

    public void clickSearch() {
        btnSearch.click();
        // Wait for table loading spinner to disappear
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("oxd-form-loader")));
        } catch (Exception ignored) {}
    }

    public void clickReset() {
        btnReset.click();
    }

    public int getSearchResultCount() {
        // Dynamic wait for results or no records element to populate
        wait.until(ExpectedConditions.or(
            ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='oxd-table-card']")),
            ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='No Records Found']")),
            ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(.,'Record Found')]"))
        ));
        return tableResultRows.size();
    }

    public boolean isNoRecordsFoundDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(msgNoRecords)).isDisplayed();
    }
}