package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void verifyAdminLogin() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
                "Admin",
                "admin123"
        );

        Assert.assertTrue(
                driver.getCurrentUrl().contains("/dashboard")
        );
    }
}