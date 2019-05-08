package com.gurukula.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.gurukula.resources.base;

public class StaffPage extends base {

	public WebDriver driver;

	public StaffPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Create a new Staff')]")
	private WebElement create_staff_button;

	@FindBy(how = How.CSS, using = ".alert.alert-success.ng-scope")
	private WebElement success_msg;

	@FindBy(how = How.XPATH, using = "//input[@name='name']")
	private WebElement staff_nm_create_screen;

	@FindBy(how = How.XPATH, using = "//select[@name='related_branch']")
	private WebElement branch_select_element;

	@FindBy(how = How.XPATH, using = "//button[2]/span[2][text()='Save']")
	private WebElement save_button;

	@FindBy(how = How.XPATH, using = "//button[1]/span[2][text()='Cancel']")
	private WebElement cancel_button;

	@FindBy(how = How.XPATH, using = "//table/tbody/tr")
	private List<WebElement> html_table;

	@FindBy(how = How.XPATH, using = "//tr[1]//td[2]//input[1]")
	private WebElement staff_name_on_viewScreen;

	@FindBy(how = How.XPATH, using = "//tr[2]//td[2]//input[1]")
	private WebElement branch_name_on_viewScreen;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Back')]")
	private WebElement back_btn_viewScreen;

	@FindBy(how = How.XPATH, using = "//tr[1]//td[4]//button[1]//span[2]")
	private WebElement view_button;

	@FindBy(how = How.XPATH, using = "//tr[3]//td[4]//button[2]")
	private WebElement edit_button;

	@FindBy(how = How.XPATH, using = "//button[@class='btn btn-danger']//span[@class='ng-scope'][contains(text(),'Delete')]")
	private WebElement second_delete_btn;

	// get the help error messages
	@FindBy(how = How.CSS, using = (".help-block.ng-scope"))
	public List<WebElement> help_error_message;

	/*
	 * function to enter staff name
	 */

	public void enterStaff_nameonCreate(WebDriver driver, String staffNm) {
		try {
			staff_nm_create_screen.clear();
			staff_nm_create_screen.sendKeys(staffNm);

		} catch (NoSuchElementException e) {
			System.out.println("staff_nm_create_screen field on staff creation page doesn't exist : " + e.toString());

		} catch (Exception e) {

			System.out.println("Exception occured in enterStaff_nameonCreate function : " + e.toString());

		}
	}

	/*
	 * function to select branch name
	 */

	public void selectBranch_nameonCreate(WebDriver driver, String branch_name) {
		try {

			new Select(branch_select_element).selectByVisibleText(branch_name);

		} catch (NoSuchElementException e) {
			System.out.println("branch_select_element field on staff creation page doesn't exist : " + e.toString());

		} catch (Exception e) {

			System.out.println("Exception occured in selectBranch_nameonCreate function : " + e.toString());

		}
	}

	/*
	 * function for clicking Create Staff button
	 */

	public void clickOnCreateStaffButton(WebDriver driver) {
		try {
			if (waitforelementTobeClickable(create_staff_button, 10)) {
				System.out.println("Able to click create branch button");
				create_staff_button.click();
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				Assert.assertFalse(true, "Staff Create button is not clickable");
			}
		} catch (NoSuchElementException e) {
			System.out.println("create_staff_button field on branches page doesn't exist : " + e.toString());

		} catch (Exception e) {

			System.out.println("Exception occured in clickOnCreateStaffButton : " + e.toString());

		}
	}

	/*
	 * function for clicking save button
	 */

	public void clickOnSaveButton(WebDriver driver) {
		try {
			save_button.click();
		} catch (NoSuchElementException e) {
			System.out.println("save_button field on branches page doesn't exist : " + e.toString());

		} catch (Exception e) {

			System.out.println("Exception occured in clickOnSaveButton : " + e.toString());

		}
	}

	/*
	 * function to verify view staff
	 */
	public Boolean VerifyViewStaffFunctionality(WebDriver driver) {

		String staff_name_original = driver.findElement(By.xpath("//table/tbody/tr[1]/td[2]")).getText();
		String branch_name_original = driver.findElement(By.xpath("//table/tbody/tr[1]/td[3]")).getText();
		System.out.println("staff_name_original: " + staff_name_original);
		System.out.println("branch_name_original: " + branch_name_original);
		view_button.click();
		if (waitforVisibiltyofElement(branch_name_on_viewScreen, 10)) {

			System.out.println("staff_name_on_viewScreen.getText(): " + staff_name_on_viewScreen.getAttribute("value"));
			System.out
					.println("branch_name_on_viewScreen.getText(): " + branch_name_on_viewScreen.getAttribute("value"));
			if (staff_name_original.equalsIgnoreCase(staff_name_on_viewScreen.getAttribute("value"))
					&& branch_name_original.equalsIgnoreCase(branch_name_on_viewScreen.getAttribute("value"))) {
				return true;
			} else {
				System.out.println("Values of staff are not equal in view mode");
				return false;
			}
		} else {
			System.out.println("Error in displaying staff view screen");
			return false;
		}

	}

	/*
	 * function for creation of new staff invalid data
	 */

	public void CreateNewStaffforErrors(WebDriver driver, String staff_name, String branch_name) {

		clickOnCreateStaffButton(driver);

		enterStaff_nameonCreate(driver, staff_name);
		if (staff_name.equalsIgnoreCase("")) {
			staff_nm_create_screen.clear();
			staff_nm_create_screen.sendKeys("test");
			staff_nm_create_screen.clear();
		} else {
			staff_nm_create_screen.clear();
			staff_nm_create_screen.sendKeys(staff_name);
		}

		selectBranch_nameonCreate(driver, branch_name);

	}

	/*
	 * function for creation of new staff
	 */

	public boolean CreateStaff(WebDriver driver, String staff_name, String branch_name) {

		clickOnCreateStaffButton(driver);

		enterStaff_nameonCreate(driver, staff_name);
		selectBranch_nameonCreate(driver, branch_name);
		clickOnSaveButton(driver);

		if (waitforelementTobeClickable(create_staff_button, 10)) {
			driver.navigate().refresh();
			System.out.println(newStaffDetails(driver));
			String branch_details = staff_name + "|" + branch_name;
			return branch_details.equalsIgnoreCase(newStaffDetails(driver).trim());
		} else {
			System.out.println("Create Branch button is not enabled after staff creation");
			return false;
		}
	}

	/*
	 * function for clicking delete button
	 */

	public void clickOnDeleteButton(WebDriver driver) {
		try {
			second_delete_btn.click();
		} catch (NoSuchElementException e) {
			System.out.println("second_delete_btn field on branches page doesn't exist : " + e.toString());

		} catch (Exception e) {

			System.out.println("Exception occured in clickOnDeleteButton : " + e.toString());

		}
	}

	/*
	 * function to delete Staff
	 */
	public Boolean DeleteStaff(WebDriver driver) {
		String oldrecord = newStaffDetails(driver);
		String delete_button_xpath = "//tr[" + html_table.size() + "]//td[4]//button[3]";
		System.out.println(delete_button_xpath);
		WebElement first_delete_button = driver.findElement(By.xpath(delete_button_xpath));
		first_delete_button.click();

		if (waitforelementTobeClickable(second_delete_btn, 10)) {
			clickOnDeleteButton(driver);
			waitforelementTobeClickable(create_staff_button, 10);
			driver.navigate().refresh();
			String newrecord = newStaffDetails(driver);
			if (newrecord.equalsIgnoreCase(oldrecord)) {
				System.out.println("Error in deleting the record");
				return false;
			} else {
				return true;
			}

		} else {
			System.out.println("Error in displaying second delete button");
			return false;
		}
	}

	/*
	 * function to edit staff
	 */
	public Boolean editStaff(WebDriver driver, String staff_name, String branch_name) throws InterruptedException {
		String edit_button_xpath = "//tr[" + html_table.size() + "]//td[4]//button[2]";
		System.out.println(edit_button_xpath);
		edit_button = driver.findElement(By.xpath(edit_button_xpath));
		edit_button.click();
		Thread.sleep(5000);
		enterStaff_nameonCreate(driver, staff_name);
		selectBranch_nameonCreate(driver, branch_name);
		clickOnSaveButton(driver);

		if (waitforelementTobeClickable(create_staff_button, 10)) {
			driver.navigate().refresh();
			System.out.println(newStaffDetails(driver));
			String branch_details = staff_name + "|" + branch_name;
			return branch_details.equalsIgnoreCase(newStaffDetails(driver).trim());
		} else {
			System.out.println("Create Branch button is not enabled after staff creation");
			return false;
		}

	}

	/*
	 * Get the concatenated string of newly created staff name and branch name
	 * separated by "|"
	 */

	public String newStaffDetails(WebDriver driver) {
		try {
			System.out.println("Total no of rows: " + html_table.size());
			return driver.findElement(By.xpath("//table/tbody/tr[" + html_table.size() + "]/td[2]")).getText() + "|"
					+ driver.findElement(By.xpath("//table/tbody/tr[" + html_table.size() + "]/td[3]")).getText();
		} catch (NoSuchElementException e) {
			System.out.println("html_table field on staff page doesn't exist : " + e.toString());

		} catch (Exception e) {

			System.out.println("Exception occured in newStaffDetails : " + e.toString());

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

}
