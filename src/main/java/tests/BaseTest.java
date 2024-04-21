package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import FileConstants.FConstants;

/**
 * The purpose of this class is to have a common methods across the different
 * test classes
 */
public class BaseTest {
	public static ExtentReports extent;
	public static ExtentTest test;
	public static WebDriver driver;

	static Logger logger = LogManager.getLogger();

	@BeforeSuite
	public void configuration() {
		extent = new ExtentReports();
		logger.info("BaseTest: Configuration:  Report configuration started");
		ExtentSparkReporter report = new ExtentSparkReporter(FConstants.REPORT_FOLDER_PATH);
		extent.attachReporter(report);
		logger.info("BaseTest: Configuration:Report configuration Finished");
	}

	@AfterSuite
	public void teardown() {
		logger.info("BaseTest: teardown :After Suite Finished");
		extent.flush();
	}

	/**
	 * This method configures and return browser object
	 * 
	 * @param BrowserName eg: chrome, firefox
	 * @return Webdriver object
	 */
	public static WebDriver getBrowserType(String BrowserName) {
		WebDriver driver;
		String browser = BrowserName;
		switch (browser) {
		case "chrome":
			driver = new ChromeDriver();
			// System.out.println("Chrome is configured");
			logger.info("BaseTest: getBrowserType : Chrome Browser Configured");
			break;

		case "safari":
			driver = new SafariDriver();
			// System.out.println("Safari is configured");
			logger.info("BaseTest: getBrowserType : Safari Browser Configured");
			break;

		case "firefox":
			driver = new FirefoxDriver();
			System.out.println("firefox is configured");
			break;
		default:
			driver = null;
			System.out.println("No browser is Configured");
			break;

		}
		return driver;

	}

}
