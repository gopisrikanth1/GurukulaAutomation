package com.gurukula.pageObjects;

import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SettingsPage {

	public WebDriver driver;

	public SettingsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(how = How.XPATH, using = "//input[@name='firstName']")
	public WebElement first_name;

	@FindBy(how = How.XPATH, using = "//input[@name='lastName']")
	public WebElement last_name;

	@FindBy(how = How.XPATH, using = "//input[@name='email']")
	public WebElement email_locator;

	@FindBy(how = How.NAME, using = "langKey")
	private WebElement language_dropdown;

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
	 * function for entering first name on settings page
	 */

	public void enterFirstNameonSettings(WebDriver driver, String frst_nm) {
		try {

			first_name.sendKeys(frst_nm);
		} catch (NoSuchElementException e) {
			System.out.println("first_name field on user Settings page doesn't exist : " + e.toString());

		} catch (Exception e) {

			System.out.println("Exception occured in enterFirstNameonSettings : " + e.toString());

		}

	}

	/*
	 * function for entering last name on settings page
	 */

	public void enterLastNameonSettings(WebDriver driver, String lst_nm) {
		try {

			first_name.sendKeys(lst_nm);
		} catch (NoSuchElementException e) {
			System.out.println("first_name field on user Settings page doesn't exist : " + e.toString());

		} catch (Exception e) {

			System.out.println("Exception occured in enterFirstNameonSettings : " + e.toString());

		}

	}

	/*
	 * function for entering email id on settings page
	 */

	public void enterEmailIdonSettings(WebDriver driver, String email) {
		try {

			email_locator.sendKeys(email);
		} catch (NoSuchElementException e) {
			System.out.println("email_locator field on user Settings page doesn't exist : " + e.toString());

		} catch (Exception e) {

			System.out.println("Exception occured in enterEmailIdonSettings : " + e.toString());

		}

	}

	/*
	 * function for clearing first name on settings page
	 */

	public void clearFirstNameonSettings(WebDriver driver) {
		try {

			first_name.clear();
		} catch (NoSuchElementException e) {
			System.out.println("first_name field on user Settings page doesn't exist : " + e.toString());

		} catch (Exception e) {

			System.out.println("Exception occured in clearFirstNameonSettings : " + e.toString());

		}

	}

	/*
	 * function for clearing last name on settings page
	 */

	public void clearLastNameonSettings(WebDriver driver) {
		try {

			first_name.clear();
		} catch (NoSuchElementException e) {
			System.out.println("first_name field on user Settings page doesn't exist : " + e.toString());

		} catch (Exception e) {

			System.out.println("Exception occured in clearLastNameonSettings : " + e.toString());

		}

	}

	/*
	 * function for clearing email id on settings page
	 */

	public void clearEmailIdonSettings(WebDriver driver) {
		try {

			email_locator.clear();
		} catch (NoSuchElementException e) {
			System.out.println("email_locator field on user Settings page doesn't exist : " + e.toString());

		} catch (Exception e) {

			System.out.println("Exception occured in clearEmailIdonSettings : " + e.toString());

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
	public void saveUserDetails(WebDriver driver, String firstNm, String last_nm, String emailId) {
		try {

			// handling firstname
			if (firstNm.equalsIgnoreCase("")) {
				clearFirstNameonSettings(driver);
				enterFirstNameonSettings(driver, "test");
				clearFirstNameonSettings(driver);
			} else {

				clearFirstNameonSettings(driver);
				enterFirstNameonSettings(driver, firstNm);

			}

			// handling last name
			if (last_nm.equalsIgnoreCase("")) {
				clearLastNameonSettings(driver);
				enterLastNameonSettings(driver, "test");
				clearLastNameonSettings(driver);
			} else {
				clearLastNameonSettings(driver);
				enterLastNameonSettings(driver, last_nm);

			}

			// handling emailid
			if (emailId.equalsIgnoreCase("")) {
				clearEmailIdonSettings(driver);
				enterEmailIdonSettings(driver, "test");
				clearEmailIdonSettings(driver);
			} else {

				clearEmailIdonSettings(driver);
				enterEmailIdonSettings(driver, emailId);

			}

			if (save_button.isEnabled()) {
				ClickonSaveBtn(driver);
			}
		} catch (Exception e) {

			System.out.println("Exception occured in RegisterUser method : " + e.toString());

		}

	}

}
