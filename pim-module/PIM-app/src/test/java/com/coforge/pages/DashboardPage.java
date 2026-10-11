package com.coforge.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {
    public WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//span[text()='PIM']")
    private WebElement menuPIM;

    @FindBy(xpath = "//h6[text()='Dashboard']")
    private WebElement headerDashboard;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public boolean isDashboardDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(headerDashboard)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickPIMMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(menuPIM)).click();
    }
}