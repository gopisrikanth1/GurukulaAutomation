package com.gurukula.AutomationTestgurkula;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.gurukula.pageObjects.HomePage;
import com.gurukula.pageObjects.LoginPage;
import com.gurukula.pageObjects.SettingsPage;
import com.gurukula.pageObjects.StaffPage;
import com.gurukula.resources.base;

public class LTC_05_StaffManagement_Test extends base {

	public base baseobj;
	public WebDriver driver;
	public HomePage homepage;
	public SettingsPage settingspage;
	public LoginPage loginpage;
	public StaffPage staffpage;

	@Parameters({ "validUserName", "validPassword", "loginhomeTitle" })
	@BeforeMethod
	public void setupBeforeTest(String validUserName, String validPassword, String loginhomeTitle)
			throws IOException, InterruptedException {
		driver = Launchurl();
		Assert.assertTrue(driver.getTitle().contains("gurukula"));
		homepage = new HomePage(driver);
		homepage.Clickonlogin(driver);
		loginpage = new LoginPage(driver);
		loginpage.peformlogin(driver, validUserName, validPassword, "N");
		Assert.assertTrue(loginpage.VerifyLoginSuccessorNot(driver, loginhomeTitle), "Login is not succesfull");
		loginpage.ClickonEntities(driver);
		loginpage.ClickonStaff(driver);
		Thread.sleep(5000);
	}

	@Test(dataProvider = "createStaffvalidData", enabled = true)
	public void PTC_SM_001_Verify_StaffCreation(String staff_name, String branch_name) {
		staffpage = new StaffPage(driver);

		Assert.assertTrue(staffpage.CreateStaff(driver, staff_name, branch_name), "Staff creation is not succesfull");
	}

	@Test(enabled = true)
	public void PTC_SM_002_Verify_Branch_view_functionality() {
		staffpage = new StaffPage(driver);
		Assert.assertTrue(staffpage.VerifyViewStaffFunctionality(driver), "Staff view functionality is not succesfull");
	}

	@Parameters({ "staff_name_new", "branch_name_staff_new" })
	@Test(enabled = true)
	public void PTC_SM_003_Verify_BranchEditing(String staff_name, String branch_name) throws InterruptedException {
		staffpage = new StaffPage(driver);
		Assert.assertTrue(staffpage.editStaff(driver, staff_name, branch_name), "Staff editing is not successfull");
	}

	@Test(enabled = true)
	public void PTC_SM_004_Verify_delete_functionality() {
		staffpage = new StaffPage(driver);
		Assert.assertTrue(staffpage.DeleteStaff(driver), "Staff delete functionality is not succesfull");
	}

	@Test(dataProvider = "createStaffinvalidData", enabled = true)
	public void PTC_SM_007_to_SM_009_Verify_Createbranch_functionality_withvariousinputs(String staff_name,
			String branch_nm, String expMsg) {
		staffpage = new StaffPage(driver);
		staffpage.CreateNewStaffforErrors(driver, staff_name, branch_nm);
		Assert.assertEquals(staffpage.GethelpErrorfromScreen(driver), expMsg);
	}

	@DataProvider(name = "createStaffvalidData")
	public Object[][] getStaffData() {
		Object[][] createStaffdata = new Object[][] { { "srikanth", "CIVILENG" }, { "gopi", "CIVILENG" },
				{ "kosuru", "CIVILENG" } };
		return createStaffdata;

	}

	@DataProvider(name = "createStaffinvalidData")
	public Object[][] CreatestaffInvalidDatasetup() {

		Object[][] createstaffinvalid = new Object[][] {
				// staff name is more than 50 characters
				{ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "CIVILENG",
						"This field cannot be longer than 50 characters." },
				// staff name is blanks
				{ "", "CIVILENG", "This field is required." },
				// branch name is not according to the pattern ^[a-zA-Z\\s]*$.
				{ "1111", "CIVILENG", "This field should follow pattern ^[a-zA-Z\\s]*$." }

		};

		return createstaffinvalid;
	}

	@AfterMethod
	public void closebrowser() {
		System.out.println("Closing the webdriver");
		driver.quit();

	}

}
