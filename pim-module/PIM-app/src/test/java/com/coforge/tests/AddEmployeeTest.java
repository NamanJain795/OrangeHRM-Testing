package com.coforge.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.coforge.base.BaseTest;
import com.coforge.config.ConfigReader;
import com.coforge.pages.AddEmployeePage;
import com.coforge.pages.DashboardPage;
import com.coforge.pages.Login_POM;
import com.coforge.utilities.ExcelUtility;

public class AddEmployeeTest extends BaseTest {

    private DashboardPage dashboardPage;
    private AddEmployeePage addEmpPage;

    @BeforeMethod
    public void setupTest() {
        Login_POM loginPage = new Login_POM(driver);
        loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

        dashboardPage = new DashboardPage(driver);
        dashboardPage.clickPIMMenu();

        addEmpPage = new AddEmployeePage(driver);
        addEmpPage.navigateToAddEmployeeTab();
    }

    @DataProvider(name = "AddEmployeeData")
    public Object[][] getAddEmployeeData() throws IOException {
        return ExcelUtility.getexceldata("EmployeeData");
    }

    @Test(priority = 1, dataProvider = "AddEmployeeData", description = "Verify adding new employees from Excel sheet data")
    public void testAddNewEmployeeFromExcel(String firstName, String middleName, String lastName, String empId) {
        addEmpPage.enterEmployeeDetails(firstName, middleName, lastName, empId);
        addEmpPage.clickSave();

        // Strict assertion ensuring record was actually saved in DB/UI
        Assert.assertTrue(addEmpPage.isEmployeeSavedSuccessfully(), "Failed to save employee: " + firstName + " " + lastName);
    }

    @Test(priority = 2, description = "Verify employee creation is blocked when mandatory fields are left blank")
    public void testAddNewEmployeeMandatoryValidation() {
        addEmpPage.enterEmployeeDetails("", "", "", "");
        addEmpPage.clickSave();

        String errorMsg = addEmpPage.getFieldErrorText();
        Assert.assertEquals(errorMsg, "Required", "Validation error message mismatch!");
    }
}