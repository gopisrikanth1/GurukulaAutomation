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
import com.gurukula.pageObjects.PasswordPage;
import com.gurukula.pageObjects.SessionsPage;
import com.gurukula.pageObjects.SettingsPage;
import com.gurukula.resources.base;

public class LTC_03_AcccountManagement_Test extends base {

	public base baseobj;

	public WebDriver driver;

	public HomePage homepage;

	public SettingsPage settingspage;

	public PasswordPage passwordpage;

	@Parameters({ "validUserName", "validPassword", "loginhomeTitle" })
	@BeforeMethod
	public void setupBeforeTest(String validUserName, String validPassword, String loginhomeTitle) throws IOException {

		driver = Launchurl();
		Assert.assertTrue(driver.getTitle().contains("gurukula"));
		homepage = new HomePage(driver);
		homepage.Clickonlogin(driver);
		LoginPage loginpage = new LoginPage(driver);
		loginpage.peformlogin(driver, validUserName, validPassword, "N");
		Assert.assertTrue(loginpage.VerifyLoginSuccessorNot(driver, loginhomeTitle), "Login is not succesfull");

	}

	@Test(dataProvider = "userSettingsData")
	public void PTC_AC01_to_AC07_Verify_User_settings_with_differentinputcombinations(String firstName, String lastName,
			String emailId, String userSettingMsg, String switchflag) {

		System.out.println("Executing PTC_AC01_to_AC07_Verify_User_settings_with_differentinputcombinations ");
		LoginPage loginpage = new LoginPage(driver);
		loginpage.ClickonMyaccount(driver);
		loginpage.ClickonSettings(driver);

		settingspage = new SettingsPage(driver);
		settingspage.saveUserDetails(driver, firstName, lastName, emailId);

		switch (switchflag) {
		case "flagX":
			// System.out.println("Executing screen error message validation");
			Assert.assertEquals(settingspage.GetSuccessMessagefromScreen(driver), userSettingMsg);
			break;
		case "flagY":
			System.out.println("Executing screen help error message validation");
			// System.out.println("Help Error message: " +
			// userregistrationpage.GethelpErrorfromScreen(driver));
			Assert.assertEquals(settingspage.GethelpErrorfromScreen(driver), userSettingMsg);
			break;
		}

	}
	
	

	@Test(dataProvider = "passwordsData")
	public void PTC_AC08_to_AC15_Verify_User_settings_with_differentinputcombinations(String pwd, String confpwd,
			String userSettingMsg, String switchflag) {

		System.out.println("Executing PTC_AC08_to_AC15_Verify_User_settings_with_differentinputcombinations ");
		LoginPage loginpage = new LoginPage(driver);
		loginpage.ClickonMyaccount(driver);
		loginpage.ClickonPassword(driver);

		passwordpage = new PasswordPage(driver);
		passwordpage.savePassword(driver, pwd, confpwd);

		switch (switchflag) {
		case "flagX":
			Assert.assertEquals(passwordpage.GetSuccessMessagefromScreen(driver), userSettingMsg);
			break;
		case "flagY":
			System.out.println("Executing screen help error message validation");
			Assert.assertEquals(passwordpage.GethelpErrorfromScreen(driver), userSettingMsg);
			break;
		case "flagZ":
			System.out.println("Executing screen help error message validation");
			Assert.assertEquals(passwordpage.GetErrorMessagefromScreen(driver), userSettingMsg);
			break;
		}

	}
	
	@Test
	public void PTC_AC16_InvalidateSession()
	{
		System.out.println("PTC_AC16_InvalidateSession");
		LoginPage loginpage = new LoginPage(driver);
		loginpage.ClickonMyaccount(driver);
		loginpage.ClickonSessions(driver);
		SessionsPage sessionpage = new SessionsPage(driver);
		Assert.assertTrue(sessionpage.verifyInvalidateEnabled(), "Invalidate button not enabled");
		sessionpage.clickOnInvalidate();
		Assert.assertEquals(sessionpage.getSuccessMsg(), "Session invalidated!");
	}
	

	@DataProvider(name = "passwordsData")
	public Object[][] passwordsDataFun() {

		Object[][] userSettingsdata = new Object[][] {
				// valid passwords are provided
				{ "May@2019", "May@2019", "Password changed!", "flagX" },
				// password less than 5 characters
				{ "mm", "May@2019", "Your password is required to be at least 5 characters.", "flagY" },
				// password more than 50 characters
				{ "ggggggggggggggggggggggggggggggggggggggggggggggggggg", "May@2019",
						"Your password cannot be longer than 50 characters.", "flagY" },
				// confirm password less than 5 characters
				{ "May@2019", "mm", "Your confirmation password is required to be at least 5 characters.", "flagY" },
				// confirm password more than 50 characters
				{ "May@2019", "ggggggggggggggggggggggggggggggggggggggggggggggggggg",
						"Your confirmation password cannot be longer than 50 characters.", "flagY" },
				// password is blank
				{ "", "May@2019", "Your password is required.", "flagY" },
				// confirm password is blank
				{ "May@2019", "", "Your confirmation password is required.", "flagY" },
				// password and confirm password are different
				{ "May@2019", "Apr@2019", "The password and its confirmation do not match!", "flagZ" },

		};

		return userSettingsdata;
	}

	@DataProvider(name = "userSettingsData")
	public Object[][] userSettingsDataFun() {

		Object[][] userSettingsdata = new Object[][] {
				// valid user settings details
				{ "Admin firstname", "Admin lastname", "admin1@localhost", "Settings saved!", "flagX" },
				// email id is invalid and less than 5 characters
				{ "Admin firstname", "Admin lastname", "aaaa",
						"Your e-mail is invalid.Your e-mail is required to be at least 5 characters.", "flagY" },
				// email id is invalid
				{ "Admin firstname", "Admin lastname", "aaaaaaa", "Your e-mail is invalid.", "flagY" },
				// first name is blank
				{ "", "Admin lastname", "admin1@localhost", "Your first name is required.", "flagY" },

				// last name is blank
				{ "Admin firstname", "", "admin1@localhost", "Your last name is required.", "flagY" },
				// email id is blank
				{ "Admin firstname", "Admin lastname", "", "Your e-mail is required.", "flagY" },

		};

		return userSettingsdata;
	}

	@AfterMethod
	public void closebrowser() {
		System.out.println("Closing the webdriver");
		driver.quit();

	}

}
