package utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import FileConstants.FConstants;
import FileConstants.WaitConstants;

public class CommonUtils {

	public static boolean waitForElement(WebDriver driver, WebElement element) {
		boolean isELementClickable = false;
		WebDriverWait wait = new WebDriverWait(driver, WaitConstants.WAIT_FOR_ElEMENT);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			isELementClickable = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isELementClickable;
	}

	public static boolean waitForElementToBeDisplayed(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, WaitConstants.WAIT_FOR_ElEMENT);
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean waitForElementToDisappear(WebDriver driver, WebElement element) {
		boolean isELementInvisible = false;
		WebDriverWait wait = new WebDriverWait(driver, WaitConstants.WAIT_FOR_ElEMENT_TO_DISAPPEAR);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			isELementInvisible = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isELementInvisible;
	}

	public static void mouseHover(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();

	}

	public static void explicitSleep(Duration timeout) {
		try {
			Thread.sleep(timeout);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getScreenshot(WebDriver driver) {
		String filePath = FConstants.SCREENSHOTS_FOLDER_PATH;
		TakesScreenshot screen = (TakesScreenshot) driver;
		File source = screen.getScreenshotAs(OutputType.FILE);
		File destination = new File(filePath);
		source.renameTo(destination);
		return filePath;
	}

	public static String getScreenshot1(WebDriver driver) {
		String filePath = FConstants.SCREENSHOTS_FOLDER;
		TakesScreenshot screen = (TakesScreenshot) driver;
		File source = screen.getScreenshotAs(OutputType.FILE);
		File destination = new File(filePath);
		source.renameTo(destination);
		return filePath;
	}

	public static String getDateAndTimeStamp() {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}
}
