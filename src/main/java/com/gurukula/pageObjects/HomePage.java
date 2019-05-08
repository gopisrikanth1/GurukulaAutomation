package com.gurukula.pageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


public class HomePage {

	public WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(how = How.LINK_TEXT, using = "login")
	WebElement loginLink;

	@FindBy(how = How.LINK_TEXT, using = "Register a new account")
	WebElement newAccountLink;

	@FindBy(how = How.XPATH, using = "//*[@id=\"navbar-collapse\"]/ul/li[2]/a")
	WebElement accountBtn;

	/**
	 * This method clicks on the login link
	 * 
	 */
	public void Clickonlogin(WebDriver driver) {
		try {

			loginLink.click();

		} catch (NoSuchElementException e) {
			System.out.println("login link doesn't exist :" + e.toString());

		}

		catch (Exception e) {

			System.out.println("Exception occured in Clickonlogin :" + e.toString());

		}
	}

	/**
	 * This method clicks on the Register a new account link
	 * 
	 */
	public void Clickon_newUserLink(WebDriver driver) {
		try {

			newAccountLink.click();

		} catch (NoSuchElementException e) {
			System.out.println("Register a new account link doesn't exist :" + e.toString());

		}

		catch (Exception e) {

			System.out.println("Exception occured in Clickon_newUserLink :" + e.toString());

		}

	}

}
