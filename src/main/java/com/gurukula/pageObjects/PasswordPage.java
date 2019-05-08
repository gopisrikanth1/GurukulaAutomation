package com.gurukula.pageObjects;

import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class PasswordPage {

	public WebDriver driver;

	public PasswordPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(how = How.XPATH, using = "//input[@name='password']")
	public WebElement password;

	@FindBy(how = How.XPATH, using = "//input[@name='confirmPassword']")
	public WebElement cnfrm_password;

	@FindBy(how = How.XPATH, using = "//button[text() = 'Save']")
	private WebElement save_button;

	// get the help error messages
	@FindBy(how = How.CSS, using = (".help-block.ng-scope"))
	public List<WebElement> help_error_message;

	// get the error message after clicking register message
	@FindBy(how = How.CSS, using = ".alert.alert-danger.ng-scope")
	public List<WebElement> error_message_on_settingspage;

	// get the error message after clicking register message
	@FindBy(how = How.CSS, using = ".alert.alert-success.ng-scope")
	public List<WebElement> success_message_on_settingspage;

	/*
	 * function for entering password on Password page
	 */

	public void enterPasswordonPasswordpage(WebDriver driver, String pwd) {
		try {

			password.sendKeys(pwd);
		} catch (NoSuchElementException e) {
			System.out.println("password field on user Password page doesn't exist : " + e.toString());

		} catch (Exception e) {

			System.out.println("Exception occured in enterPasswordonPasswordpage : " + e.toString());

		}

	}

	/*
	 * function for entering confirm password on Password page
	 */

	public void enterConfirmPasswordonPasswordpage(WebDriver driver, String confpwd) {
		try {

			cnfrm_password.sendKeys(confpwd);
		} catch (NoSuchElementException e) {
			System.out.println("cnfrm_password field on user Password page doesn't exist : " + e.toString());

		} catch (Exception e) {

			System.out.println("Exception occured in enterConfirmPasswordonPasswordpage : " + e.toString());

		}

	}

	/*
	 * function for clearing password on Password page
	 */

	public void clearPasswordonPasswordPage(WebDriver driver) {
		try {
			password.clear();
		} catch (NoSuchElementException e) {
			System.out.println("cnfrm_password field on Password page doesn't exist : " + e.toString());

		} catch (Exception e) {

			System.out.println("Exception occured in clearPasswordonPasswordPage : " + e.toString());

		}

	}

	/*
	 * function for clearing password on Password page
	 */

	public void clearCnfrmPasswordonPasswordPage(WebDriver driver) {
		try {

			cnfrm_password.clear();
		} catch (NoSuchElementException e) {
			System.out.println("password field on Password page doesn't exist : " + e.toString());

		} catch (Exception e) {

			System.out.println("Exception occured in clearPasswordonPasswordPage : " + e.toString());

		}

	}

	/*
	 * function for clicking on save button on settings page
	 */

	public void ClickonSaveBtn(WebDriver driver) {
		try {
			save_button.click();
		} catch (NoSuchElementException e) {

			System.out.println("save_button field on user registration page doesn't exist : " + e.toString());

		} catch (Exception e) {

			System.out.println("Exception occured in ClickonRegisterBtn : " + e.toString());

		}

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
	 * function for getting after clicking register message
	 */

	public String GetErrorMessagefromScreen(WebDriver driver)

	{
		for (WebElement element : error_message_on_settingspage) {

			if (element.isDisplayed()) {
				return element.getText();
			}
		}
		return "";

	}

	/*
	 * function for getting success message after clicking save button
	 */

	public String GetSuccessMessagefromScreen(WebDriver driver)

	{
		for (WebElement element : success_message_on_settingspage) {

			if (element.isDisplayed()) {
				return element.getText();
			}
		}
		return "";

	}

	// function for saving user details
	public void savePassword(WebDriver driver, String pwd, String confpwd) {
		try {

			// handling password
			if (pwd.equalsIgnoreCase("")) {
				enterPasswordonPasswordpage(driver, "test");
				clearPasswordonPasswordPage(driver);
			} else {

				clearPasswordonPasswordPage(driver);
				enterPasswordonPasswordpage(driver, pwd);

			}

			// handling confirm password
			if (confpwd.equalsIgnoreCase("")) {
				enterConfirmPasswordonPasswordpage(driver, "test");
				clearCnfrmPasswordonPasswordPage(driver);
			} else {
				clearCnfrmPasswordonPasswordPage(driver);
				enterConfirmPasswordonPasswordpage(driver, confpwd);

			}

			if (save_button.isEnabled()) {
				ClickonSaveBtn(driver);
			}
		} catch (Exception e) {

			System.out.println("Exception occured in RegisterUser method : " + e.toString());

		}

	}

}
