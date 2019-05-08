package com.gurukula.resources;

import static java.lang.Runtime.getRuntime;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class base {

	/**
	 * Created by Gopi Srikanth on 27/04/2019.
	 */

	public static WebDriver driver = null;
	public Properties prop = new Properties();

	/*
	 * This Method to initialize the webdriver and launch the application url
	 */

	public WebDriver Launchurl() throws IOException {

		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\resources\\propertyfiles\\data.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		int implicitTime = Integer.parseInt(prop.getProperty("implictWaitTime"));
		String applicationUrl = prop.getProperty("applicationUrl");
		System.out.println(browserName);

		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\src\\main\\resources\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		if (browserName.equalsIgnoreCase("firefox")) {

			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "\\src\\main\\resources\\drivers\\geckodriver.exe");

			driver = new FirefoxDriver();
		}
		if (browserName.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver",
					System.getProperty("user.dir") + "\\src\\main\\resources\\drivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		driver.manage().timeouts().implicitlyWait(implicitTime, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(applicationUrl);
		return driver;

	}

	public void launchApplication() throws IOException, InterruptedException {
		getRuntime().exec("cmd /c start java -jar gurukula-0.0.1-SNAPSHOT.war");
		Thread.sleep(100000);
	}

	/*
	 * Function to wait until the element is visible on the page
	 */

	public boolean waitforVisibiltyofElement(WebElement elementArg, long timeout) {

		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOfAllElements(elementArg));

		return elementArg.isDisplayed();
	}
	
	public boolean waitforelementTobeClickable(WebElement elementArg, long timeout) {

		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.elementToBeClickable(elementArg));

		return elementArg.isEnabled();
	}

}
