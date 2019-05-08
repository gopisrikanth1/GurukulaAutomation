package com.gurukula.pageObjects;

import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class UserRegistrationPage {

	public WebDriver driver;

	public UserRegistrationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(how = How.XPATH, using = "//input[@name = 'login']")
	public WebElement userreg_user_name;

	@FindBy(how = How.XPATH, using = "//input[@name = 'email']")
	public WebElement userreg_emailid;

	@FindBy(how = How.XPATH, using = "//input[@name = 'password']")
	public WebElement userreg_newpassword;

	@FindBy(how = How.XPATH, using = "//input[@name = 'confirmPassword']")
	public WebElement userreg_confirmPassword;

	// register button
	@FindBy(how = How.XPATH, using = "//*[@type='submit']")
	public WebElement userreg_register_btn;

	// get the error message after clicking register message
	@FindBy(how = How.CSS, using = ".alert.alert-danger.ng-scope")
	public List<WebElement> error_message_on_userReg;

	// get the help error messages
	@FindBy(how = How.CSS, using = (".help-block.ng-scope"))
	public List<WebElement> help_error_message;

	/*
	 * function for getting after clicking register message
	 */

	public String GetErrorMessagefromScreen(WebDriver driver)

	{
		for (WebElement element : error_message_on_userReg) {

			System.out.println(element.getAttribute("class").toString());
			System.out.println(element.isDisplayed());
			System.out.println(element.getText());

			if (element.isDisplayed()) {
				return element.getText();
			}
		}
		return "";

	}

	/*
	 * function for getting help error message
	 */

	public String GethelpErrorfromScreen(WebDriver driver)

	{
		String help_error_message_str = "";
		for (WebElement element : help_error_message) {

			System.out.println(element.getAttribute("class").toString());
			System.out.println(element.isDisplayed());
			System.out.println(element.getText());

			if (element.isDisplayed()) {
				help_error_message_str = help_error_message_str + element.getText();
			}
		}
		System.out.println("Total help error string: " + help_error_message_str);
		return help_error_message_str;

	}

	/*
	 * function to clear username
	 */

	public void clearUsernameonRegister(WebDriver driver) {
		try {
			userreg_user_name.clear();

		} catch (NoSuchElementException e) {
			System.out.println("userName_registartion field on user registration page doesn't exist : " + e.toString());

		} catch (Exception e) {

			System.out.println("Exception occured in clearUsernameonRegister : " + e.toString());

		}
	}

	/*
	 * function for entering userName username on registration form
	 */

	public void enterUsernameonRegister(WebDriver driver, String userName_registartion) {
		try {

			userreg_user_name.sendKeys(userName_registartion);
		} catch (NoSuchElementException e) {
			System.out.println("userName_registartion field on user registration page doesn't exist : " + e.toString());

		} catch (Exception e) {

			System.out.println("Exception occured in enterUsernameonRegister : " + e.toString());

		}

	}

	/*
	 * function for clearing emaildId on registration form
	 */

	public void clearEmailidonRegister(WebDriver driver) {
		try {
			userreg_emailid.clear();
		} catch (NoSuchElementException e) {
			System.out.println("userreg_emailid field on user registration page doesn't exist : " + e.toString());

		} catch (Exception e) {

			System.out.println("Exception occured in clearEmailidonRegister : " + e.toString());

		}

	}

	/*
	 * function for entering emaildId on registration form
	 */

	public void enterEmailidonRegister(WebDriver driver, String userName_emaildId) {
		try {

			userreg_emailid.sendKeys(userName_emaildId);
		} catch (NoSuchElementException e) {
			System.out.println("userreg_emailid field on user registration page doesn't exist : " + e.toString());

		} catch (Exception e) {

			System.out.println("Exception occured in enterEmailidonRegister : " + e.toString());

		}

	}

	/*
	 * function for clearing password on registration form
	 */

	public void clearnewpasswordonRegister(WebDriver driver) {
		try {
			userreg_newpassword.clear();
		} catch (NoSuchElementException e) {
			System.out.println("userreg_newpassword field on user registration page doesn't exist : " + e.toString());

		} catch (Exception e) {

			System.out.println("Exception occured in clearnewpasswordonRegister : " + e.toString());

		}

	}

	/*
	 * function for entering userName new password on registration form
	 */

	public void enternewpasswordonRegister(WebDriver driver, String newpassword) {
		try {

			userreg_newpassword.sendKeys(newpassword);

		} catch (NoSuchElementException e) {
			System.out.println("userreg_newpassword field on user registration page doesn't exist : " + e.toString());

		} catch (Exception e) {

			System.out.println("Exception occured in enternewpasswordonRegister : " + e.toString());

		}

	}

	/*
	 * function for clearing confirm password on registration form
	 */

	public void clearconfirmpasswordonRegister(WebDriver driver) {
		try {
			userreg_confirmPassword.clear();
		} catch (NoSuchElementException e) {
			System.out
					.println("userreg_confirmPassword field on user registration page doesn't exist : " + e.toString());

		} catch (Exception e) {

			System.out.println("Exception occured in clearconfirmpasswordonRegister : " + e.toString());

		}

	}

	/*
	 * function for entering confirm password on registration form
	 */

	public void enterconfirmpasswordonRegister(WebDriver driver, String confirmpassword) {
		try {

			userreg_confirmPassword.sendKeys(confirmpassword);

		} catch (NoSuchElementException e) {
			System.out
					.println("userreg_confirmPassword field on user registration page doesn't exist : " + e.toString());

		} catch (Exception e) {

			System.out.println("Exception occured in enterconfirmpasswordonRegister : " + e.toString());

		}

	}

	/*
	 * function for clicking on register button on registration form
	 */

	public void ClickonRegisterBtn(WebDriver driver) {
		try {
			userreg_register_btn.click();
		} catch (NoSuchElementException e) {

			System.out.println("userreg_register_btn field on user registration page doesn't exist : " + e.toString());

		} catch (Exception e) {

			System.out.println("Exception occured in ClickonRegisterBtn : " + e.toString());

		}

	}

	public void RegisterUser(WebDriver driver, String userreg, String emailuserreg, String newpassuserreg,
			String cnfrmpassusereg) {
		try {

			// handling username
			if (userreg.equalsIgnoreCase("")) {
				enterUsernameonRegister(driver, "test");
				clearUsernameonRegister(driver);
			} else {

				clearUsernameonRegister(driver);
				enterUsernameonRegister(driver, userreg);

			}

			// handling emailid
			if (emailuserreg.equalsIgnoreCase("")) {
				enterEmailidonRegister(driver, "test");
				clearEmailidonRegister(driver);
			} else {

				clearEmailidonRegister(driver);
				enterEmailidonRegister(driver, emailuserreg);

			}

			// handling password
			if (newpassuserreg.equalsIgnoreCase("")) {
				enternewpasswordonRegister(driver, "test");
				clearnewpasswordonRegister(driver);
			} else {

				clearnewpasswordonRegister(driver);
				enternewpasswordonRegister(driver, newpassuserreg);

			}

			// handling confirm password
			if (cnfrmpassusereg.equalsIgnoreCase("")) {
				enterconfirmpasswordonRegister(driver, "test");
				clearconfirmpasswordonRegister(driver);
			} else {

				clearconfirmpasswordonRegister(driver);
				enterconfirmpasswordonRegister(driver, cnfrmpassusereg);

			}

			if (userreg_register_btn.isEnabled()) {
				ClickonRegisterBtn(driver);
			}
		} catch (Exception e) {

			System.out.println("Exception occured in RegisterUser method : " + e.toString());

		}

	}

}
