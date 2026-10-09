
package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.AdminPage;
import pages.LoginPage;
import pages.UserManagementPage;

public class SearchUserTest extends BaseTest {

    @Test
    public void verifySearchUserByUsername() {

        // 1. Login to OrangeHRM
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");

        // 2. Open Admin / User Management
        AdminPage adminPage = new AdminPage(driver);
        adminPage.openUserManagement();

        // 3. Create the User Management page object
        UserManagementPage userManagementPage =
                new UserManagementPage(driver);

        // 4. Search for an existing username
        String expectedUsername = "noob";

        userManagementPage.enterSearchUsername(expectedUsername);
        userManagementPage.clickSearch();

        // 5. Verify the search result
        Assert.assertTrue(
                userManagementPage.isUsernamePresentInResults(
                        expectedUsername
                ),
                "Expected username was not found in search results: "
                        + expectedUsername
        );
    
    }
}