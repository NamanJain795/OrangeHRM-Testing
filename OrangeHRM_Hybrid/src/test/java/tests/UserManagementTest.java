

package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.AdminPage;
import pages.LoginPage;
import pages.UserManagementPage;
import utilities.ExcelDataReader;
import utilities.ScreenshotUtil;

public class UserManagementTest extends BaseTest {

	@DataProvider(name = "addUserData")
    public Object[][] addUserData() throws IOException {
        return ExcelDataReader.getCellData(
                "src/test/resources/AdminData.xlsx",
                "Sheet1"
        );
    }
 
    @Test(dataProvider = "addUserData")
    public void verifyAddUserPage(
            String userRole,
            String employeeName,
            String status,
            String username,
            String password,
            String confirmPassword)
            throws InterruptedException {
 
        // Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");
 
        // Open Admin / User Management
        AdminPage adminPage = new AdminPage(driver);
        adminPage.openUserManagement();
 
        // Open Add User form
        UserManagementPage userManagementPage =
                new UserManagementPage(driver);
 
        userManagementPage.clickAdd();
 
        Assert.assertTrue(
                userManagementPage.isAddUserPageDisplayed(),
//        		false,
                "Add User page is not displayed"
        );
 
        // Fill the form using the current Excel row
        userManagementPage.selectUserRole(userRole);
        userManagementPage.enterEmployeeName(employeeName);
 
        Thread.sleep(3000);
 
        userManagementPage.selectStatus(status);
        userManagementPage.enterUsername(username);
        userManagementPage.enterPassword(password);
        userManagementPage.enterConfirmPassword(confirmPassword);
 
        userManagementPage.clickSave();
 
        // TODO: Verify the save result using your existing
        // success-message or user-search verification methods.
    }
}
