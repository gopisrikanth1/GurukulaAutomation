package com.gurukula.AutomationTestgurkula;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.gurukula.pageObjects.HomePage;
import com.gurukula.pageObjects.LoginPage;
import com.gurukula.resources.base;

public class LTC_01_User_Authentication_Test extends base {

	public base baseobj;

	public WebDriver driver;

	public HomePage homepage;

	// To launch the Gurukul application locally
	@BeforeSuite
	public void launchApplication() throws IOException, InterruptedException {

		// launchApplication();
	}

	@BeforeMethod
	public void setupBeforeTest() throws IOException {

		driver = Launchurl();

		// Verify Gurukul home page is launched

		Assert.assertTrue(driver.getTitle().contains("gurukula"));
		System.out.println("Gurkul home page is launched");

	}

	@Parameters({ "validUserName", "validPassword", "loginhomeTitle" })
	@Test()
	public void PTC_AM_01_Verify_User_Valid_login(String validUserName, String validPassword, String loginhomeTitle) {
		System.out.println("Executing PTC_01_Verify_User_Valid_login ");
		homepage = new HomePage(driver);
		homepage.Clickonlogin(driver);
		LoginPage loginpage = new LoginPage(driver);
		// Perform login with without "Automatic Login"
		loginpage.peformlogin(driver, validUserName, validPassword, "N");
		Assert.assertTrue(loginpage.VerifyLoginSuccessorNot(driver, loginhomeTitle), "Login is not succesfull");

	}

	@Parameters({ "invalidUserName", "invalidPassword", "invalidLoginmessage" })
	@Test()
	public void PTC_AM_02_Verify_User_InValid_login(String invalidUserName, String invalidPassword,
			String invalidLoginmessage) {
		System.out.println("Executing PTC_02_Verify_User_InValid_login ");
		homepage = new HomePage(driver);
		homepage.Clickonlogin(driver);
		LoginPage loginpage = new LoginPage(driver);
		// Perform login with without "Automatic Login"
		loginpage.peformlogin(driver, invalidUserName, invalidPassword, "N");
		Assert.assertTrue(loginpage.VerifyInvalidCreds(driver, invalidLoginmessage), "invalid credentails not found");

	}

	@Parameters({ "validUserName", "validPassword", "loginhomeTitle" })
	@Test()
	public void PTC_AM_03_Verify_User_Valid_loginwithAutomaticLoginenabled(String validUserName, String validPassword,
			String loginhomeTitle) {
		System.out.println("Executing PTC_03_Verify_User_Valid_loginwithAutomaticLoginenabled ");
		homepage = new HomePage(driver);
		homepage.Clickonlogin(driver);
		LoginPage loginpage = new LoginPage(driver);
		// Perform login with without "Automatic Login"
		loginpage.peformlogin(driver, validUserName, validPassword, "Y");
		Assert.assertTrue(loginpage.VerifyLoginSuccessorNot(driver, loginhomeTitle), "Login is not succesfull");

	}

	@Parameters({ "validUserName", "validPassword", "loginhomeTitle" })
	@Test()
	public void PTC_AM_04_Verify_User_Valid_loginandLogout(String validUserName, String validPassword,
			String loginhomeTitle) {
		System.out.println("Executing PTC_04_Verify_User_Valid_loginandLogout ");
		homepage = new HomePage(driver);
		homepage.Clickonlogin(driver);
		LoginPage loginpage = new LoginPage(driver);
		// Perform login with without "Automatic Login"
		loginpage.peformlogin(driver, validUserName, validPassword, "N");
		Assert.assertTrue(loginpage.VerifyLoginSuccessorNot(driver, loginhomeTitle), "Login is not succesfull");
		loginpage.peformlogout(driver);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Parameters({ "validUserName", "validPassword", "loginhomeTitle" })
	@Test()
	public void PTC_AM_05_Verify_User_Valid_loginandLogout(String validUserName, String validPassword,
			String loginhomeTitle) {
		System.out.println("Executing PTC_04_Verify_User_Valid_loginandLogout ");
		homepage = new HomePage(driver);
		homepage.Clickonlogin(driver);
		LoginPage loginpage = new LoginPage(driver);
		// Perform login with without "Automatic Login"
		loginpage.peformlogin(driver, validUserName, validPassword, "N");
		Assert.assertTrue(loginpage.VerifyLoginSuccessorNot(driver, loginhomeTitle), "Login is not succesfull");
		loginpage.peformlogout(driver);

	}
	
	
	@Parameters({ "validEmailid" })
	@Test()
	public void PTC_AM_06_Verify_ResetPassword_button_isenabled_with_validemailid(String validEmailid) {
		System.out.println("Executing PTC_AM_06_Verify_ResetPassword_button_isenabled_with_validemailid ");
		homepage = new HomePage(driver);
		homepage.Clickonlogin(driver);
		LoginPage loginpage = new LoginPage(driver);
		loginpage.Clickon_Did_you_forget_password(driver);
		loginpage.enterEmaildIdonForgetPasswordscreen(driver, validEmailid);
		Assert.assertFalse(loginpage.Verify_Reset_password_button_isenabled_ornot(driver),
				"Email is not enabled for valid email id");
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Parameters({ "invalidEmailid" })
	@Test()
	public void PTC_AM_07_Verify_ResetPassword_button_isenabled_with_invalidemailid(String invalidEmailid) {
		System.out.println("Executing PTC_AM_07_Verify_ResetPassword_button_isenabled_with_invalidemailid ");
		homepage = new HomePage(driver);
		homepage.Clickonlogin(driver);
		LoginPage loginpage = new LoginPage(driver);
		loginpage.Clickon_Did_you_forget_password(driver);
		loginpage.enterEmaildIdonForgetPasswordscreen(driver, invalidEmailid);
		Assert.assertTrue(loginpage.Verify_Reset_password_button_isenabled_ornot(driver),
				"Email is enabled for invalid email id");

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@AfterMethod
	public void closebrowser() {
		System.out.println("Closing the webdriver");
		driver.quit();

	}

}
