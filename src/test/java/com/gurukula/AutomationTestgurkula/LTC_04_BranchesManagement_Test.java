package com.gurukula.AutomationTestgurkula;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.gurukula.pageObjects.BranchesPage;
import com.gurukula.pageObjects.HomePage;
import com.gurukula.pageObjects.LoginPage;
import com.gurukula.pageObjects.SettingsPage;
import com.gurukula.resources.base;

public class LTC_04_BranchesManagement_Test extends base {

	public base baseobj;
	public WebDriver driver;
	public HomePage homepage;
	public SettingsPage settingspage;
	public LoginPage loginpage;
	public BranchesPage branchespage;

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
		loginpage.ClickonBranches(driver);
		Thread.sleep(5000);
	}

	@Parameters({ "branch_name", "branch_code" })
	@Test(enabled = true)
	public void PTC_BM_001_Verify_BranchCreation(String branch_name, String branch_code) {
		branchespage = new BranchesPage(driver);
		// branchespage.clickOnCreateBranchButton(driver);

		Assert.assertTrue(branchespage.CreateNewBranch(driver, branch_name, branch_code),
				"Branch creation is not succesfull");
	}
	
	

	@Test(enabled = true)
	public void PTC_BM_002_Verify_Branch_view_functionality() {
		branchespage = new BranchesPage(driver);
		Assert.assertTrue(branchespage.VerifyViewFunctionality(driver), "Branch view functionality is not succesfull");
	}

	@Parameters({ "branch_name_new", "branch_code_new" })
	@Test(enabled = true)
	public void PTC_BM_003_Verify_BranchEditing(String branch_name, String branch_code) throws InterruptedException {
		branchespage = new BranchesPage(driver);
		Assert.assertTrue(branchespage.EditBranch(driver, branch_name, branch_code),
				"Branch editing is not successfull");
	}

	@Test(enabled = true)
	public void PTC_BM_004_Verify_delete_functionality() {
		branchespage = new BranchesPage(driver);
		Assert.assertTrue(branchespage.DeleteBranch(driver), "Branch delete functionality is not succesfull");
	}

	@Test(dataProvider = "createBranchData", enabled = true)
	public void PTC_BM_007_to_BM_013_Verify_Createbranch_functionality_withvariousinputs(String branch_name,
			String branch_code, String expMsg) {
		branchespage = new BranchesPage(driver);
		branchespage.CreateNewBranchforErrors(driver, branch_name, branch_code);
		Assert.assertEquals(branchespage.GethelpErrorfromScreen(driver), expMsg);
	}
	
	@Parameters({ "branch_name", "branch_code" })
	@Test(enabled = true)
	public void PTC_BM_001_Verify_BranchCreation2(String branch_name, String branch_code) {
		branchespage = new BranchesPage(driver);
		// branchespage.clickOnCreateBranchButton(driver);

		Assert.assertTrue(branchespage.CreateNewBranch(driver, branch_name, branch_code),
				"Branch creation is not succesfull");
	}
	

	@DataProvider(name = "createBranchData")
	public Object[][] CreateBranchDatasetup() {

		Object[][] createbranchdata = new Object[][] {

				// branch name is less than 5 characters
				{ "AA", "ABC", "This field is required to be at least 5 characters." },
				// branch name is more than 20 characters
				{ "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "ABC", "This field cannot be longer than 20 characters." },
				// branch name is blank
				{ "", "ABC", "This field is required." },
				// branch code is blank
				{ "ELECTRICAL", "", "This field is required." },
				// branch code is less than 2 characters
				{ "ELECTRICAL", "A", "This field is required to be at least 2 characters." },
				// branch code is not according to the pattern ^[A-Z0-9]*$.
				{ "ELECTRICAL", "aa", "This field should follow pattern ^[A-Z0-9]*$." },
				// branch name is not according to the pattern ^[a-zA-Z\\s]*$.
				{ "12", "AA", "This field should follow pattern ^[a-zA-Z\\s]*$." }

		};

		return createbranchdata;
	}

	@AfterMethod
	public void closebrowser() {
		System.out.println("Closing the webdriver");
		driver.quit();

	}

}
