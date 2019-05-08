package com.gurukula.pageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	public WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(how = How.ID, using = "username")
	public WebElement user_name;

	@FindBy(how = How.CSS, using = "#password")
	public WebElement user_password;

	@FindBy(how = How.XPATH, using = "//*[@id='rememberMe']")
	public WebElement automatic_Login_checkbox;

	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Authenticate')]")
	public WebElement authenticate_button;

	@FindBy(how = How.XPATH, using = "//*[@id='navbar-collapse']/ul/li[3]/a")
	public WebElement account_btn;

	@FindBy(how = How.XPATH, using = "//*[@id='navbar-collapse']/ul/li[3]/ul/li[4]/a/span[2]")
	public WebElement logout_btn;

	@FindBy(how = How.LINK_TEXT, using = "Did you forget your password?")
	public WebElement forgot_pass;

	@FindBy(how = How.LINK_TEXT, using = "Register a new account")
	public WebElement new_account;

	@FindBy(how = How.CSS, using = "strong")
	public WebElement error_text_for_login;

	@FindBy(how = How.XPATH, using = "/html/body/div[3]/div[1]/div/div/div[2]/div/div")
	public WebElement verify_login_page;

	@FindBy(how = How.XPATH, using = "//*[@name='email']")
	public WebElement email_id_on_forget_passwordscreen;

	@FindBy(how = How.XPATH, using = ".//*[@id='navbar-collapse']/ul//span[2][text()='Entities']")
	public WebElement entities_page;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='navbar-collapse']/ul//span[2][text()='Branch']")
	public WebElement branch_page;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='navbar-collapse']/ul//span[2][text()='Staff']")
	public WebElement staff_page;
	
	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Reset password')]")
	public WebElement reset_password_btn;

	@FindBy(how = How.XPATH, using = ".//*[@id='navbar-collapse']//a/span[2][text()='Settings']")
	public WebElement settingsLink;

	@FindBy(how = How.XPATH, using = ".//*[@id='navbar-collapse']//a/span[2][text()='Password']")
	public WebElement passwordLink;

	@FindBy(how = How.XPATH, using = ".//*[@id='navbar-collapse']//a/span[2][text()='Sessions']")
	public WebElement sessionsLink;

	/*
	 * function for entering user Name
	 */

	public void userNameenter(WebDriver driver, String usrName) {
		try {
			user_name.clear();
			user_name.sendKeys(usrName);
		} catch (NoSuchElementException e) {
			System.out.println("user_name link on loginpage doesn't exist : " + e.toString());

		} catch (Exception e) {

			System.out.println("Exception occured in userNameenter : " + e.toString());

		}

	}

	/*
	 * function for entering password
	 */

	public void userpasswordenter(WebDriver driver, String passwrd) {
		try {
			user_password.clear();
			user_password.sendKeys(passwrd);
		} catch (NoSuchElementException e) {
			System.out.println("user_name link on loginpage doesn't exist : " + e.toString());

		} catch (Exception e) {

			System.out.println("Exception occured in Clickonlogin : " + e.toString());

		}

	}

	/*
	 * function for clicking Authenticate button
	 */

	public void ClickOnAuthenticate(WebDriver driver) {
		try {
			authenticate_button.click();

		} catch (NoSuchElementException e) {
			System.out.println("authenticate_button  on loginpage doesn't exist : " + e.toString());

		} catch (Exception e) {

			System.out.println("Exception occured in ClickOnAuthenticate : " + e.toString());

		}

	}

	/*
	 * function for verifying login success or not
	 */

	public Boolean VerifyLoginSuccessorNot(WebDriver driver, String verifyString) {
		try {

			return verify_login_page.getText().contains(verifyString);

		} catch (NoSuchElementException e) {
			System.out.println("verify_login_page message element on loginpage doesn't exist : " + e.toString());

		} catch (Exception e) {

			System.out.println("Exception occured in verify_login_page : " + e.toString());

		}
		return false;

	}

	/*
	 * function for verifying invalid authorization message displayed or not
	 */

	public Boolean VerifyInvalidCreds(WebDriver driver, String verifyinvalidauth) {
		try {

			System.out.println("Invalid credentials text: " + error_text_for_login.getText());
			return error_text_for_login.getText().contains(verifyinvalidauth);

		} catch (NoSuchElementException e) {
			System.out.println("error_text_for_login  on loginpage doesn't exist : " + e.toString());

		} catch (Exception e) {

			System.out.println("Exception occured in VerifyInvalidCreds : " + e.toString());

		}
		return false;

	}

	/*
	 * function for checkinng or unchecking Authenticate button based on the
	 * argument CheckorUncheck flag CheckorUncheck : Y(Check) CheckorUncheck :
	 * N(UnCheck)
	 */

	public void CheckorUncheckOnAutomaticLogon(WebDriver driver, String CheckorUncheck) {
		try {

			if (CheckorUncheck == "Y") {
				if (!(automatic_Login_checkbox.isSelected())) {
					automatic_Login_checkbox.click();
				}
			} else {
				if ((automatic_Login_checkbox.isSelected())) {
					automatic_Login_checkbox.click();
				}
			}

		} catch (NoSuchElementException e) {
			System.out.println("automatic_Login_checkbox  on loginpage doesn't exist : " + e.toString());

		} catch (Exception e) {

			System.out.println("Exception occured in ClickOnAutomaticLogon : " + e.toString());

		}

	}

	/*
	 * function for login with("Y"") or without("N") Automatic Login
	 */

	public void peformlogin(WebDriver driver, String username, String password, String authenticatechckbox) {
		try {

			userNameenter(driver, username);
			userpasswordenter(driver, password);
			CheckorUncheckOnAutomaticLogon(driver, authenticatechckbox);
			ClickOnAuthenticate(driver);
		} catch (Exception e) {

			System.out.println("Exception occured in peformlogin function : " + e.toString());

		}

	}

	/*
	 * function for click on account button
	 */

	public void ClickonMyaccount(WebDriver driver) {
		try {
			account_btn.click();

		}

		catch (NoSuchElementException e) {
			System.out.println("account_btn  on user home page doesn't exist : " + e.toString());

		} catch (Exception e) {

			System.out.println("Exception occured in ClickonMyaccount function : " + e.toString());

		}

	}
	
	
	/*
	 * function for click on entities button
	 */

	public void ClickonEntities(WebDriver driver) {
		try {
			entities_page.click();

		}

		catch (NoSuchElementException e) {
			System.out.println("entities_page  on user home page doesn't exist : " + e.toString());

		} catch (Exception e) {

			System.out.println("Exception occured in ClickonEntities function : " + e.toString());

		}

	}
	
	
	/*
	 * function for click on Branches button
	 */

	public void ClickonBranches(WebDriver driver) {
		try {
			branch_page.click();

		}

		catch (NoSuchElementException e) {
			System.out.println("branch_page  on user home page doesn't exist : " + e.toString());

		} catch (Exception e) {

			System.out.println("Exception occured in ClickonBranches function : " + e.toString());

		}

	}
	
	
	/*
	 * function for click on Staff button
	 */

	public void ClickonStaff(WebDriver driver) {
		try {
			staff_page.click();

		}

		catch (NoSuchElementException e) {
			System.out.println("staff_page  on user home page doesn't exist : " + e.toString());

		} catch (Exception e) {

			System.out.println("Exception occured in ClickonStaff function : " + e.toString());

		}

	}

	/*
	 * function for click on logout button
	 */

	public void Clickonlogout(WebDriver driver) {
		try {
			logout_btn.click();

		}

		catch (NoSuchElementException e) {
			System.out.println("logout_btn  on user home page doesn't exist : " + e.toString());

		} catch (Exception e) {

			System.out.println("Exception occured in Clickonlogout function : " + e.toString());

		}

	}

	/*
	 * function for click on Settings button
	 */

	public void ClickonSettings(WebDriver driver) {
		try {
			settingsLink.click();

		}

		catch (NoSuchElementException e) {
			System.out.println("settingsLink  on user home page doesn't exist : " + e.toString());

		} catch (Exception e) {

			System.out.println("Exception occured in ClickonSettings function : " + e.toString());

		}

	}

	/*
	 * function for click on Password button
	 */

	public void ClickonPassword(WebDriver driver) {
		try {
			passwordLink.click();

		}

		catch (NoSuchElementException e) {
			System.out.println("passwordLink  on user home page doesn't exist : " + e.toString());

		} catch (Exception e) {

			System.out.println("Exception occured in ClickonPassword function : " + e.toString());

		}

	}

	/*
	 * function for click on Sessions button
	 */

	public void ClickonSessions(WebDriver driver) {
		try {
			sessionsLink.click();

		}

		catch (NoSuchElementException e) {
			System.out.println("sessionsLink  on user home page doesn't exist : " + e.toString());

		} catch (Exception e) {

			System.out.println("Exception occured in ClickonSessions function : " + e.toString());

		}

	}

	/*
	 * function for logout
	 */

	public void peformlogout(WebDriver driver) {
		try {
			ClickonMyaccount(driver);
			Clickonlogout(driver);

		}

		catch (Exception e) {

			System.out.println("Exception occured in peformlogout function : " + e.toString());

		}

	}

	/*
	 * function for click on Did you forget password link
	 */

	public void Clickon_Did_you_forget_password(WebDriver driver) {
		try {
			forgot_pass.click();

		}

		catch (NoSuchElementException e) {
			System.out.println("forgot_pass on application home page doesn't exist : " + e.toString());

		} catch (Exception e) {

			System.out.println("Exception occured in Clickon_Did_you_forget_password function : " + e.toString());

		}

	}

	/*
	 * function to perform to click on Did you forget password link
	 */

	public void enterEmaildIdonForgetPasswordscreen(WebDriver driver, String emailId) {
		try {

			email_id_on_forget_passwordscreen.sendKeys(emailId);
		}

		catch (Exception e) {

			System.out.println("Exception occured in peformlogout function : " + e.toString());

		}

	}

	/*
	 * function to perform to click on Did you forget password link
	 */

	public Boolean Verify_Reset_password_button_isenabled_ornot(WebDriver driver) {
		try {
			if (reset_password_btn.isEnabled()) {
				return false;
			} else {
				return true;
			}

		}

		catch (NoSuchElementException e) {
			System.out.println("forgot_pass on application home page doesn't exist : " + e.toString());

		} catch (Exception e) {

			System.out.println("Exception occured in Clickon_Did_you_forget_password function : " + e.toString());

		}
		return false;

	}

}
