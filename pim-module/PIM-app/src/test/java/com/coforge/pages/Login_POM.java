package com.coforge.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login_POM {

    public WebDriver driver;
    private WebDriverWait wait;

    private By uname = By.name("username");
    private By pword = By.name("password");
    private By loginbutton = By.xpath("//button[@type='submit']");
    private By dashboard = By.xpath("//h6[text()='Dashboard']");

    public Login_POM(WebDriver driver2) {
        this.driver = driver2;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterusername(String username) {
        if (username != null) {
            WebElement usernameElem = wait.until(ExpectedConditions.visibilityOfElementLocated(uname));
            usernameElem.clear();
            usernameElem.sendKeys(username.trim());
        }
    }

    public void enterpassword(String password) {
        if (password != null) {
            WebElement passwordElem = wait.until(ExpectedConditions.visibilityOfElementLocated(pword));
            passwordElem.clear();
            passwordElem.sendKeys(password.trim());
        }
    }

    public void clicklogin() {
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(loginbutton));
        loginBtn.click();
    }

    public void login(String username, String password) {
        enterusername(username);
        enterpassword(password);
        clicklogin();
    }

    public boolean isdashboarddisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(dashboard)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}