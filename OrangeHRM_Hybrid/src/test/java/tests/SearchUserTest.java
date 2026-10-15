
package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.AdminPage;
import pages.LoginPage;
import pages.UserManagementPage;

public class SearchUserTest extends BaseTest {

	@Test
	public void verifySearchExistingUser() {
	 
	    LoginPage loginPage = new LoginPage(driver);
	    loginPage.login("Admin", "admin123");
	 
	    AdminPage adminPage = new AdminPage(driver);
	    adminPage.openUserManagement();
	 
	    UserManagementPage userManagementPage =
	            new UserManagementPage(driver);
	 
	    String username = "Admin";
	 
	    userManagementPage.enterSearchUsername(username);
	    userManagementPage.clickSearch();
	 
	    Assert.assertTrue(
	            userManagementPage.isUsernamePresentInResults(username),
	            "Existing username was not found: " + username
	    );
	}
	 
	@Test
	public void verifySearchNonExistingUser() {
	 
	    LoginPage loginPage = new LoginPage(driver);
	    loginPage.login("Admin", "admin123");
	 
	    AdminPage adminPage = new AdminPage(driver);
	    adminPage.openUserManagement();
	 
	    UserManagementPage userManagementPage =
	            new UserManagementPage(driver);
	 
	    String username = "noob";
	 
	    userManagementPage.enterSearchUsername(username);
	    userManagementPage.clickSearch();
	 
	    Assert.assertFalse(
	            userManagementPage.isUsernamePresentInResults(username),
	            "Unexpected username found: " + username
	    );
	}
}
