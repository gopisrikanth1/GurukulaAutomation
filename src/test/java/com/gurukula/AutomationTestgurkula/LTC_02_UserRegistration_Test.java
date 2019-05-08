package com.gurukula.AutomationTestgurkula;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.gurukula.pageObjects.HomePage;
import com.gurukula.pageObjects.UserRegistrationPage;
import com.gurukula.resources.base;

public class LTC_02_UserRegistration_Test extends base {

	public base baseobj;

	public WebDriver driver;

	public HomePage homepage;

	@BeforeMethod
	public void setupBeforeTest() throws IOException {

		driver = Launchurl();

		// Verify Gurukul home page is launched

		Assert.assertTrue(driver.getTitle().contains("gurukula"));
		System.out.println("Gurkul home page is launched");

	}

	@Test(dataProvider = "userRegistrationdetails")
	public void PTC_RM_01_to_RM16_Verify_User_registarion_with_differentinputcombinations(String UserIdforUserreg,
			String EmailidforUserreg, String passwordforUserreg, String cnfrmpasswordforUserreg, String message,
			String switchflag) {
		System.out.println("Executing PTC_RM_01_to_RM16_Verify_User_registarion_with_differentinputcombinations ");
		homepage = new HomePage(driver);
		homepage.Clickon_newUserLink(driver);
		UserRegistrationPage userregistrationpage = new UserRegistrationPage(driver);

		switch (switchflag) {
		case "flagX":
			// System.out.println("Executing screen error message validation");
			userregistrationpage.RegisterUser(driver, UserIdforUserreg, EmailidforUserreg, passwordforUserreg,
					cnfrmpasswordforUserreg);
			// System.out.println("Error message: " +
			// userregistrationpage.GetErrorMessagefromScreen(driver));
			Assert.assertEquals(userregistrationpage.GetErrorMessagefromScreen(driver), message);
			break;
		case "flagY":
			System.out.println("Executing screen help error message validation");
			userregistrationpage.RegisterUser(driver, UserIdforUserreg, EmailidforUserreg, passwordforUserreg,
					cnfrmpasswordforUserreg);
			// System.out.println("Help Error message: " +
			// userregistrationpage.GethelpErrorfromScreen(driver));
			Assert.assertEquals(userregistrationpage.GethelpErrorfromScreen(driver), message);
			break;
		}
		System.out.println(
				"Completion execution of PTC_RM_01_to_RM16_Verify_User_registarion_with_differentinputcombinations ");

	}

	@DataProvider(name = "userRegistrationdetails")
	public Object[][] userRegistrationdetailsFun() {

		Object[][] data = new Object[][] {
				// valid user registration details
				{ "gopisrikanth1", "gopisrikanthkosuru@gmail.com", "May@2019", "May@2019", "Registration Successful",
						"scenario1" },
				// Existing username details
				{ "admin", "gopisrikanth1@gmail.com", "May@2019", "May@2019",
						"Login name already registered! Please choose another one.", "flagX" },
				// Existing email details
				{ "gopisrikanth1", "admin@localhost", "May@2019", "May@2019",
						"E-mail is already in use! Please choose another one.", "flagX" },
				// password Mismatch
				{ "gopisrikanth1", "gopisrikanthkosuru@gmail.com", "May@2019", "october@2019",
						"The password and its confirmation do not match!", "flagX" },

				// Help messages starts

				// when email is invalid and less than 5 characters
				{ "gopisrikanth1", "gg", "May@2019", "october@2019",
						"Your e-mail is invalid.Your e-mail is required to be at least 5 characters.", "flagY" },
				// when email is invalid and more than than 50 characters
				{ "gopisrikanth1",
						"ggggggggggggggggggggggggggggggggggggggggggggggdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd",
						"May@2019", "october@2019",
						"Your e-mail is invalid.Your e-mail cannot be longer than 50 characters.", "flagY" },
				// when user id contains special characters
				{ "gopisrikanth#", "gopisrikanth1@gmail.com", "May@2019", "october@2019",
						"Your login can only contain lower-case letters and digits.", "flagY" },
				// when user id has more than 50 characters
				{ "ggggggggggggggggggggggggggggggggggggggggggggggggggg", "gopisrikanth1@gmail.com", "May@2019",
						"october@2019", "Your login cannot be longer than 50 characters.", "flagY" },
				// when password has less than 5 characters
				{ "gopisrikanth348", "gopisrikanthkosuru@gmail.com", "12", "october@2019",
						"Your password is required to be at least 5 characters.", "flagY" },
				// when password has has more than 50 characters
				{ "gopisrikanth348", "gopisrikanthkosuru@gmail.com",
						"ggggggggggggggggggggggggggggggggggggggggggggggggggg", "october@2019",
						"Your password cannot be longer than 50 characters.", "flagY" },

				// when confirm password has less than 5 characters
				{ "gopisrikanth348", "gopisrikanthkosuru@gmail.com", "october@2019", "12",
						"Your confirmation password is required to be at least 5 characters.", "flagY" },

				// when confirm password has has more than 50 characters
				{ "gopisrikanth348", "gopisrikanthkosuru@gmail.com", "october@2019",
						"ggggggggggggggggggggggggggggggggggggggggggggggggggg",
						"Your confirmation password cannot be longer than 50 characters.", "flagY" },
				// when confirm password is blank
				{ "gopisrikanth348", "gopisrikanthkosuru@gmail.com", "october@2019", "",
						"Your confirmation password is required.", "flagY" },
				// when new password is blank
				{ "gopisrikanth348", "gopisrikanthkosuru@gmail.com", "", "october@2019", "Your password is required.",
						"flagY" },
				// when email is blank
				{ "gopisrikanth348", "", "october@2019", "october@2019", "Your e-mail is required.", "flagY" },
				// when user name is blank
				{ "", "gopisrikanthkosuru@gmail.com", "october@2019", "october@2019", "Your login is required.",
						"flagY" }

		};
		return data;

	}

	@AfterMethod
	public void closebrowser() {
		System.out.println("Closing the webdriver");
		driver.quit();

	}

}
