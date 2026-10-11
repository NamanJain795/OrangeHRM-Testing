package com.coforge.tests;


import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.coforge.base.BaseTest;
import com.coforge.listener.ExtentTestNGListener;
import com.coforge.pages.Login_POM;
import com.coforge.utilities.ExcelUtility;

@Listeners(ExtentTestNGListener.class)
public class LoginTest extends BaseTest {

    @BeforeMethod
    public void beforeMethod() throws IOException {

        setup();
    }

    @AfterMethod
    public void afterMethod() {

        teardown();
    }

    @DataProvider(name = "logindata")
    public Object[][] logindata() throws IOException {
        return ExcelUtility.getexceldata("LoginData"); // 2 columns pass honge
    }
    @Test(dataProvider = "logindata")
    public void orangeHRMlogintest(
            String username,
            String password) {

        Login_POM obj =
                new Login_POM(driver);

        obj.enterusername(username);
        obj.enterpassword(password);
        obj.clicklogin();

        Assert.assertTrue(
                obj.isdashboarddisplayed());
    }
}