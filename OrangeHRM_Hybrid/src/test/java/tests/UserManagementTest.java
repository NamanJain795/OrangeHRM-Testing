

package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.AdminPage;
import pages.LoginPage;
import pages.UserManagementPage;
import utilities.ExcelDataReader;
import utilities.ScreenshotUtil;

public class UserManagementTest extends BaseTest {

    @Test
    public void verifyAddUserPage() throws IOException,InterruptedException {
    	
    	String filePath = "src/test/resources/AdminData.xlsx";

        // Read data from Excel
        String userRole = ExcelDataReader.getCellData(
                filePath, "Sheet1", 1, 0);

        String employeeName = ExcelDataReader.getCellData(
                filePath, "Sheet1", 1, 1);

        String status = ExcelDataReader.getCellData(
                filePath, "Sheet1", 1, 2);

        String username = ExcelDataReader.getCellData(
                filePath, "Sheet1", 1, 3);

        String password = ExcelDataReader.getCellData(
                filePath, "Sheet1", 1, 4);

        String confirmPassword = ExcelDataReader.getCellData(
                filePath, "Sheet1", 1, 5);

        // Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");

        // Open Admin
        AdminPage adminPage = new AdminPage(driver);
        adminPage.openUserManagement();

        // User Management page
        UserManagementPage userManagementPage =
                new UserManagementPage(driver);

        // Click Add
        userManagementPage.clickAdd();
        
        // Verify Add User page
        Assert.assertTrue(
                userManagementPage.isAddUserPageDisplayed(),
//        		false,
                "Add User page is not displayed"
        );

        // Select User Role
        userManagementPage.selectUserRole(userRole);

        // Enter Employee Name
        userManagementPage.enterEmployeeName(employeeName);
        Thread.sleep(3000);
     
        // Select Status
        userManagementPage.selectStatus(status);

        // Enter Username
        userManagementPage.enterUsername(username);
        Thread.sleep(3000);

        // Enter Password
        userManagementPage.enterPassword(password);

        // Confirm Password
        userManagementPage.enterConfirmPassword(confirmPassword);

        // Save
        userManagementPage.clickSave();
        
    }
}