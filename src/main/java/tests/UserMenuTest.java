package tests;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import FileConstants.FConstants;
import FileConstants.WaitConstants;
import Listeners.SFDCListeners;
import pages.LoginPage;
import pages.UserMenuPage;
import utils.CommonUtils;
import utils.FileUtils;

@Listeners(SFDCListeners.class)
public class UserMenuTest extends BaseTest {
	// private WebDriver driver;

	private void navigateToMyProfile(UserMenuPage ump) {
		// Navigating to my profile
		Assert.assertTrue(ump.selectuserMenuOption(driver, "My Profile"), "Failed to navigate to My Profile page");
		driver.manage().timeouts().implicitlyWait(WaitConstants.IMPLICIT_WAIT_TIME);
		utils.CommonUtils.waitForElement(driver, ump.ProfilePage);
		Assert.assertTrue(ump.isMyProfilePageDisplayed(driver), "Failed to load my profile page");
	}

	private void navigateToMySettings(UserMenuPage ump) {
		// Navigating to my profile
		Assert.assertTrue(ump.selectuserMenuOption(driver, "My Settings"), "Failed to navigate to My Settings page");
		driver.manage().timeouts().implicitlyWait(WaitConstants.IMPLICIT_WAIT_TIME);
		utils.CommonUtils.waitForElementToBeDisplayed(driver, ump.quickLinksHeader);
		Assert.assertTrue(ump.isMySettingsDisplayed(driver), "Failed to load my settings page");
	}

	@BeforeSuite
	public void preCondition() throws IOException {
		driver = BaseTest.getBrowserType("chrome");
		LoginPage lp = new LoginPage(driver);
		logger.info("BaseTest: precondition: chrome is displayed");
		driver.manage().timeouts().implicitlyWait(WaitConstants.IMPLICIT_WAIT_TIME);
		driver.get(FileUtils.readLoginTestData("prod.app.url"));
		lp.username.sendKeys(FileUtils.readLoginTestData("prod.username"));
		lp.password.sendKeys(FileUtils.readLoginTestData("prod.password"));
		lp.loginButton.click();
		logger.info("BaseTest: precondition: Login Successful");
	}

	@AfterSuite
	public void tearDown() {
		UserMenuPage ump = new UserMenuPage(driver);
		ump.selectuserMenuOption(driver, "Logout");
		utils.CommonUtils.explicitSleep(Duration.ofSeconds(2));
		driver.close();
	}

	@AfterMethod
	public void waitForSometime() {
		utils.CommonUtils.explicitSleep(Duration.ofSeconds(2));
	}

	// Tests

	@Test(enabled = true)
	public void selectMyProfile_TC06_01() throws IOException {
		System.out.println("Verify user menu options");
		UserMenuPage ump = new UserMenuPage(driver);

		// User Menu options verification
		ump.selectUserMenu();
		Assert.assertTrue(ump.verifyUserMenuItems(), "Failed to verify usermenu options");
		test.info("Usermenu options shown");
		logger.info("UsermenuTest: selectmyprofile: Successful");

		// Click again to close user menu
		ump.selectUserMenu();
		test.info("usermenu close");
	}

	// @Test
	public void selectMyProfile_TC06_02() throws IOException {
		System.out.println("Navigate to My Profile");
		UserMenuPage ump = new UserMenuPage(driver);

		// Navigating to my profile
		Assert.assertTrue(ump.selectuserMenuOption(driver, "My Profile"), "");
		driver.manage().timeouts().implicitlyWait(WaitConstants.IMPLICIT_WAIT_TIME);
		utils.CommonUtils.waitForElement(driver, ump.ProfilePage);
		Assert.assertTrue(ump.isMyProfilePageDisplayed(driver), "Failed to load my profile page");
	}

	// @Test
	public void selectMyProfile_TC06_03() throws IOException {
		System.out.println("Navigate to My Profile and edit contact");
		UserMenuPage ump = new UserMenuPage(driver);

		// Navigating to my profile
		navigateToMyProfile(ump);

		// Edit contact
		ump.selectEditContact(driver);
		Assert.assertTrue(ump.verifyEditContactIframe(driver), "Failed to verify profile iframe");
		Assert.assertTrue(ump.verifyLastNameChangeInAboutTab(driver, FileUtils.readUserMenuTestData("user.newLastName")));
	}

	// @Test
	public void selectMyProfile_TC06_05() throws IOException {
		System.out.println("Navigate to My Profile and create post");
		UserMenuPage ump = new UserMenuPage(driver);

		// Navigating to my profile
		navigateToMyProfile(ump);

		// Create Post
		Assert.assertTrue(ump.verifyCreatePost(driver, FileUtils.readUserMenuTestData("post.message")));
	}

	// @Test
	public void selectMyProfile_TC06_06() throws IOException {
		System.out.println("Navigate to My Profile and upload file");
		UserMenuPage ump = new UserMenuPage(driver);

		// Navigating to my profile
		navigateToMyProfile(ump);

		// File upload
		Assert.assertTrue(ump.verifyFileUpload(driver, FConstants.TEST_FILE_PATH));
	}

	// @Test(enabled = true)
	public void selectMyProfile_TC06_07() throws IOException {
		System.out.println("Navigate to My Profile and upload photo");
		UserMenuPage ump = new UserMenuPage(driver);

		// Navigating to my profile
		navigateToMyProfile(ump);

		// Photo upload
		Assert.assertTrue(ump.verifyPhotoUpload(driver, FConstants.PHOTO_FILE_PATH));
	}

	// Select "My settings" option from user menu

	// @Test(enabled = true)
	public void selectMySettings_TC07_01(Method name) throws IOException, InterruptedException {

		System.out.println("Open My settings and take screenshot");
		UserMenuPage ump = new UserMenuPage(driver);
		// test.info("Usermenu selected");
		// Navigate to My Settings
		navigateToMySettings(ump);
		// test.info("My settings page displayed");
		// Verify all Quick Links
		Assert.assertNotNull(ump.getMySettingsQuickLink("Change My Password"), "Change My Password link not found");
		Assert.assertNotNull(ump.getMySettingsQuickLink("Email Notifications"), "Email Notifications link not found");
		Assert.assertNotNull(ump.getMySettingsQuickLink("Personal Information"), "Personal Information link not found");
		Assert.assertNotNull(ump.getMySettingsQuickLink("Customize My Tabs"), "Customize My Tabs link not found");
		Assert.assertTrue(false, "");
		test.addScreenCaptureFromPath(CommonUtils.getScreenshot(driver));
		// test.info("Screenshot is successful");
	}
}
