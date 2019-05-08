package com.gurukula.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.gurukula.resources.base;

public class BranchesPage extends base {

	public WebDriver driver;

	public BranchesPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(how = How.XPATH, using = "//div[@class='col-md-4']//button[@class='btn btn-primary']")
	private WebElement create_branch_button;

	@FindBy(how = How.CSS, using = ".alert.alert-success.ng-scope")
	private WebElement success_msg;

	@FindBy(how = How.XPATH, using = "//input[@name='name']")
	private WebElement branch_nm_create_screen;

	@FindBy(how = How.XPATH, using = "//input[@name='code']")
	private WebElement branch_code_create_screen;

	@FindBy(how = How.XPATH, using = "//button[2]/span[2][text()='Save']")
	private WebElement save_button;

	@FindBy(how = How.XPATH, using = "//button[1]/span[2][text()='Cancel']")
	private WebElement cancel_button;

	@FindBy(how = How.XPATH, using = "//table/tbody/tr")
	private List<WebElement> html_table;

	@FindBy(how = How.XPATH, using = "//tr[1]//td[2]//input[1]")
	private WebElement branch_name_on_viewScreen;

	@FindBy(how = How.XPATH, using = "//tr[2]//td[2]//input[1]")
	private WebElement branch_code_on_viewScreen;

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
	 * function for clicking Create branch button
	 */

	public void clickOnCreateBranchButton(WebDriver driver) {
		try {
			if (waitforelementTobeClickable(create_branch_button, 10)) {
				System.out.println("Able to click create branch button");
				create_branch_button.click();
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				Assert.assertFalse(true, "Create button is not clickable");
			}
		} catch (NoSuchElementException e) {
			System.out.println("create_branch_button field on branches page doesn't exist : " + e.toString());

		} catch (Exception e) {

			System.out.println("Exception occured in clickOnCreateBranchButton : " + e.toString());

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
	 * function for clicking cancel button
	 */

	public void clickOnCancelButton(WebDriver driver) {
		try {
			cancel_button.click();
		} catch (NoSuchElementException e) {
			System.out.println("cancel_button field on branches page doesn't exist : " + e.toString());

		} catch (Exception e) {

			System.out.println("Exception occured in clickOnCancelButton : " + e.toString());

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
	 * function to enter branch name
	 */

	public void enterBranchNmonCreate(WebDriver driver, String branchNm) {
		try {
			branch_nm_create_screen.clear();
			branch_nm_create_screen.sendKeys(branchNm);

		} catch (NoSuchElementException e) {
			System.out
					.println("branch_nm_create_screen field on branches creation page doesn't exist : " + e.toString());

		} catch (Exception e) {

			System.out.println("Exception occured in enterBranchNmonCreate : " + e.toString());

		}
	}

	/*
	 * function to enter branch code
	 */

	public void enterBranchCodeonCreate(WebDriver driver, String branchCode) {
		try {
			branch_code_create_screen.clear();
			branch_code_create_screen.sendKeys(branchCode);

		} catch (NoSuchElementException e) {
			System.out.println(
					"branch_code_create_screen field on branches creation page doesn't exist : " + e.toString());

		} catch (Exception e) {

			System.out.println("Exception occured in branch_code_create_screen function : " + e.toString());

		}
	}

	public String getSuccessMsg(WebDriver driver) {
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

	/*
	 * function to create new branch
	 */
	public void CreateNewBranchforErrors(WebDriver driver, String branch_nm, String branch_code) {

		clickOnCreateBranchButton(driver);
		if (branch_nm.equalsIgnoreCase("")) {
			branch_nm_create_screen.clear();
			branch_nm_create_screen.sendKeys("test");
			branch_nm_create_screen.clear();
		} else {
			branch_nm_create_screen.clear();
			branch_nm_create_screen.sendKeys(branch_nm);
		}

		if (branch_code.equalsIgnoreCase("")) {
			branch_code_create_screen.clear();
			branch_code_create_screen.sendKeys("test");
			branch_code_create_screen.clear();
		} else {
			branch_code_create_screen.clear();
			branch_code_create_screen.sendKeys(branch_code);
		}

	}

	/*
	 * function to create new branch
	 */
	public Boolean CreateNewBranch(WebDriver driver, String branch_nm, String branch_code) {

		clickOnCreateBranchButton(driver);

		enterBranchNmonCreate(driver, branch_nm);
		enterBranchCodeonCreate(driver, branch_code);
		clickOnSaveButton(driver);

		if (waitforelementTobeClickable(create_branch_button, 10)) {
			driver.navigate().refresh();
			System.out.println(newBranchDetails(driver));
			String branch_details = branch_nm + "|" + branch_code;
			return branch_details.equalsIgnoreCase(newBranchDetails(driver).trim());
		} else {
			System.out.println("Create Branch button is not enabled after branch creation");
			return false;
		}

	}

	/*
	 * function to delete branch
	 */
	public Boolean DeleteBranch(WebDriver driver) {
		String oldrecord = newBranchDetails(driver);
		String delete_button_xpath = "//tr[" + html_table.size() + "]//td[4]//button[3]";
		System.out.println(delete_button_xpath);
		WebElement first_delete_button = driver.findElement(By.xpath(delete_button_xpath));
		first_delete_button.click();

		if (waitforelementTobeClickable(second_delete_btn, 10)) {
			clickOnDeleteButton(driver);
			waitforelementTobeClickable(create_branch_button, 10);
			driver.navigate().refresh();
			String newrecord = newBranchDetails(driver);
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
	 * function to edit branch
	 */
	public Boolean EditBranch(WebDriver driver, String branch_nm, String branch_code) throws InterruptedException {
		String edit_button_xpath = "//tr[" + html_table.size() + "]//td[4]//button[2]";
		System.out.println(edit_button_xpath);
		edit_button = driver.findElement(By.xpath(edit_button_xpath));
		edit_button.click();
		Thread.sleep(5000);
		enterBranchNmonCreate(driver, branch_nm);
		enterBranchCodeonCreate(driver, branch_code);
		clickOnSaveButton(driver);

		if (waitforelementTobeClickable(create_branch_button, 10)) {
			driver.navigate().refresh();
			System.out.println(newBranchDetails(driver));
			String branch_details = branch_nm + "|" + branch_code;
			return branch_details.equalsIgnoreCase(newBranchDetails(driver).trim());
		} else {
			System.out.println("Create Branch button is not enabled after branch editing");
			return false;
		}

	}

	/*
	 * function to verify view branch
	 */
	public Boolean VerifyViewFunctionality(WebDriver driver) {

		String branch_name_original = driver.findElement(By.xpath("//table/tbody/tr[1]/td[2]")).getText();
		String branch_code_original = driver.findElement(By.xpath("//table/tbody/tr[1]/td[3]")).getText();
		System.out.println("branch_name_original: " + branch_name_original);
		System.out.println("branch_code_original: " + branch_code_original);
		view_button.click();
		if (waitforVisibiltyofElement(branch_name_on_viewScreen, 10)) {

			System.out
					.println("branch_name_on_viewScreen.getText(): " + branch_name_on_viewScreen.getAttribute("value"));
			System.out
					.println("branch_code_on_viewScreen.getText(): " + branch_code_on_viewScreen.getAttribute("value"));
			if (branch_name_original.equalsIgnoreCase(branch_name_on_viewScreen.getAttribute("value"))
					&& branch_code_original.equalsIgnoreCase(branch_code_on_viewScreen.getAttribute("value"))) {
				return true;
			} else {
				System.out.println("Branch Values are not equal in view mode");
				return false;
			}
		} else {
			System.out.println("Error in displaying branches view screen");
			return false;
		}

	}

	/*
	 * Get the concatenated string of newly created branch name and branch code
	 * separated by "|"
	 */

	public String newBranchDetails(WebDriver driver) {
		try {
			System.out.println("Total no of rows: " + html_table.size());
			return driver.findElement(By.xpath("//table/tbody/tr[" + html_table.size() + "]/td[2]")).getText() + "|"
					+ driver.findElement(By.xpath("//table/tbody/tr[" + html_table.size() + "]/td[3]")).getText();
		} catch (NoSuchElementException e) {
			System.out.println("html_table field on branches page doesn't exist : " + e.toString());

		} catch (Exception e) {

			System.out.println("Exception occured in newBranchDetails : " + e.toString());

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
