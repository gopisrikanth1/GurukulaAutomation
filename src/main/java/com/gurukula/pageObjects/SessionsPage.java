package com.gurukula.pageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SessionsPage {

	public WebDriver driver;

	public SessionsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(how = How.XPATH, using = "//button[text()='Invalidate']")
	private WebElement invalidate_btn;

	@FindBy(how = How.CSS, using = ".alert.alert-success.ng-scope")
	private WebElement success_msg;

	public void clickOnInvalidate() {
		try {
		invalidate_btn.click();
		} catch (NoSuchElementException e) {
			System.out.println("invalidate_btn field on sessions page doesn't exist : " + e.toString());

		} catch (Exception e) {

			System.out.println("Exception occured in clickOnInvalidate : " + e.toString());

		}
	}

	public Boolean verifyInvalidateEnabled() {
		try {
			return invalidate_btn.isEnabled();
		} catch (NoSuchElementException e) {
			System.out.println("invalidate_btn field on sessions page doesn't exist : " + e.toString());

		} catch (Exception e) {

			System.out.println("Exception occured in verifyInvalidateEnabled : " + e.toString());

		}
		return false;
	}

	public String getSuccessMsg() {
		try {
			if (success_msg.isDisplayed()) {
				return success_msg.getText();
			}
		} catch (NoSuchElementException e) {
			System.out.println("success_msg field on sessions page doesn't exist : " + e.toString());

		} catch (Exception e) {

			System.out.println("Exception occured in getSuccessMsg : " + e.toString());

		}

		return "";
	}

}
