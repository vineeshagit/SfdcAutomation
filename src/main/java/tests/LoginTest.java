package tests;

import java.io.IOException;

//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.LoginPage;
import utils.FileUtils;

public class LoginTest extends BaseTest {

	@Test
	public void checkClearPassword_TC01() throws IOException {
		WebDriver driver = BaseTest.getBrowserType("chrome");
		LoginPage lp = new LoginPage(driver);
		driver.get(FileUtils.readLoginTestData("prod .app.url"));
		lp.enterUsername(FileUtils.readLoginTestData("prod.username"));
		lp.clearPassword();
		lp.clickLoginButton();
	}

	@Test
	public void login_TC02() throws IOException {

		WebDriver driver = BaseTest.getBrowserType("chrome");
		LoginPage lp = new LoginPage(driver);
		driver.get(FileUtils.readLoginTestData("prod.app.url"));
		lp.username.sendKeys(FileUtils.readLoginTestData("prod.username"));
		lp.password.sendKeys(FileUtils.readLoginTestData("prod.password"));
		lp.loginButton.click();
		driver.quit();
	}

	@Test
	public void checkRememberMe_TC03() throws IOException {
		WebDriver driver = BaseTest.getBrowserType("chrome");
		LoginPage lp = new LoginPage(driver);
		driver.get(FileUtils.readLoginTestData("prod.app.url"));
		lp.enterUsername(FileUtils.readLoginTestData("prod.username"));
		lp.enterPassword(FileUtils.readLoginTestData("prod.password"));
		lp.selectRememberMeCheckbox();
		lp.clickLoginButton();
	}

	@Test
	public void validateLoginError_TC4B() throws IOException {
		WebDriver driver = BaseTest.getBrowserType("chrome");
		LoginPage lp = new LoginPage(driver);
		driver.get(FileUtils.readLoginTestData("prod.app.url"));
		// Assert.assertEquals(lp.isLoginPageOpened(driver),
		// FileUtils.readLoginTestData("prod.app.url"));
		lp.enterUsername(FileUtils.readLoginTestData("prod.invalid.username"));
		lp.enterPassword(FileUtils.readLoginTestData("prod.invalid.password"));
		lp.clickLoginButton();
		Assert.assertEquals(lp.getLoginErrorText(), FileUtils.readLoginTestData("invalid.login.errorText"));
	}
}
