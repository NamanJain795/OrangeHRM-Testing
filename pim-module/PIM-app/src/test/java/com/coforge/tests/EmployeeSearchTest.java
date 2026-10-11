package com.coforge.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.coforge.base.BaseTest;
import com.coforge.config.ConfigReader;
import com.coforge.pages.DashboardPage;
import com.coforge.pages.EmployeeListPage;
import com.coforge.pages.Login_POM;
import com.coforge.utilities.ExcelUtility;

public class EmployeeSearchTest extends BaseTest {

    private DashboardPage dashboardPage;
    private EmployeeListPage employeeListPage;

    @BeforeMethod
    public void setupTest() {
        Login_POM loginPage = new Login_POM(driver);
        loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

        dashboardPage = new DashboardPage(driver);
        dashboardPage.clickPIMMenu();

        employeeListPage = new EmployeeListPage(driver);
        employeeListPage.navigateToEmployeeListTab();
    }

    @DataProvider(name = "SearchData")
    public Object[][] getSearchData() throws IOException {
        return ExcelUtility.getexceldata("EmployeeData");
    }

    @Test(priority = 1, dataProvider = "SearchData", description = "Verify directory search by employee name using Excel data")
    public void testSearchByEmployeeNameFromExcel(String firstName, String middleName, String lastName, String empId) {
        employeeListPage.searchByName(firstName);
        
        // Ensures at least 1 record is found for the created user
        Assert.assertTrue(employeeListPage.getSearchResultCount() > 0, "No search results returned for Name: " + firstName);
    }

    @Test(priority = 2, dataProvider = "SearchData", description = "Verify directory search by employee ID")
    public void testSearchByEmployeeId(String firstName, String middleName, String lastName, String empId) {
        employeeListPage.searchById(empId);
        
        // Ensures at least 1 record is found for the specific Employee ID
        Assert.assertTrue(employeeListPage.getSearchResultCount() > 0, "No search results returned for ID: " + empId);
    }

    @Test(priority = 3, description = "Verify directory search with no matching record")
    public void testSearchNoMatchingRecord() {
        employeeListPage.searchByName("NonExistentUserXYZ99");
        Assert.assertTrue(employeeListPage.isNoRecordsFoundDisplayed(), "'No Records Found' message was not displayed!");
    }
}