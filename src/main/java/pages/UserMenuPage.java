package pages;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.CommonUtils;
import utils.FileUtils;

public class UserMenuPage {

	public UserMenuPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// HomePage

	@FindBy(id = "userNavLabel")
	public WebElement userMenu;

	@FindBy(xpath = "//a[contains(@class, 'menuButtonMenuLink')]")
	public List<WebElement> userMenuOptions;

	@FindBy(xpath = "//a[@class='menuButtonMenuLink firstMenuItem']")
	public WebElement MyProfile;

//	@FindBy(id = "userNavMenu/a[2]")
//	public WebElement MySettings;
//
//	@FindBy(id = "userNavMenu/a[3]")
//	public WebElement DeveloperConsole;
//
//	@FindBy(id = "userNavMenu/a[4]")
//	public WebElement SwitchtoLightingExperience;
//
//	@FindBy(id = "userNavMenu/a[4]")
//	public WebElement Logout;

	@FindBy(xpath = "//a[@id= 'profileTab_sfdc.ProfilePlatformFeed']")
	public WebElement ProfilePage;

	// EditIcon

	@FindBy(xpath = "//a[@class='contactInfoLaunch editLink']")
	public WebElement editContactButton;

	@FindBy(xpath = "//div/h2[@id='contactInfoTitle']")
	public WebElement EditProfilePopupwindow;

	@FindBy(id = "contactInfoContentId")
	public WebElement iframeAboutTab;

	@FindBy(id = "aboutTab")
	public WebElement Abouttab;

	@FindBy(xpath = "//input[@id='lastName']")
	public WebElement AbouttablastName;

	@FindBy(xpath = "//input[@class= 'zen-btn zen-primaryBtn zen-pas']")
	public WebElement saveAllButton;

	@FindBy(id = "tailBreadcrumbNode")
	public WebElement userProfilePageNameDisplay;

	@FindBy(id = "contactTab")
	public WebElement contactTab;

	// PostLink
	@FindBy(id = "publisherAttachTextPost")
	public WebElement postLink;

	@FindBy(xpath = "/html/body")
	public WebElement postTextArea;

	@FindBy(xpath = "//input[@id='publishersharebutton']")
	public WebElement shareButton;

	// fileLink

	@FindBy(xpath = "//a[@id='publisherAttachContentPost']")
	public WebElement fileLink;

	@FindBy(xpath = "(//*[@class='contentFileTitle'])[1]")
	public WebElement verifyFilePostElement;

	@FindBy(xpath = "(//div[@class='cxfeeditem feeditem'][1]//p")
	public WebElement firstPostText;

	@FindBy(name = "j_id0:waitingForm")
	public WebElement spinnerIcon;

	@FindBy(id = "cropWaitingPage:croppingForm")
	public WebElement spinnerWhileCropping;

	@FindBy(id = "//input[@class='zen-btn']")
	public WebElement cancelUpload;

	// File Upload Section
	@FindBy(xpath = "//input[@id='chatterFile']")
	public WebElement chooseFileButton;

	@FindBy(xpath = "//a[@id='chatterUploadFileAction']")
	public WebElement uploadFileLink;

	@FindBy(css = "#displayBadge")
	public WebElement moderatorButton;

	@FindBy(id = "uploadLink")
	public WebElement updateButton;

	@FindBy(id = "progessIcon")
	public WebElement fileUploadSpinner;

	@FindBy(xpath = "//input[@id='j_id0:uploadFileForm:uploadInputFile']")
	public WebElement choosePhoto;

	@FindBy(xpath = "//iframe[@id='uploadPhotoContentId']")
	public WebElement photoUploadIframe;

	@FindBy(xpath = "//input[@id='j_id0:uploadFileForm:uploadBtn']")
	public WebElement photoSaveButton1;

	@FindBy(xpath = "//input[@class='btn saveButton']")
	public WebElement photoSaveButton2;

	// Settings page
	@FindBy(xpath = "//h2[text()='Quick Links']")
	public WebElement quickLinksHeader;

	@FindBy(xpath = "//a[contains(@class, 'zen-unit')]")
	public List<WebElement> mySettingsQuickLinks;

	/**
	 * @return true if all options are verified else false
	 * @throws IOException
	 */
	public boolean verifyUserMenuItems() throws IOException {
		boolean isEveryOptionsVerified = true;
		String[] expectedUserMenuItems = FileUtils.readUserMenuTestData("userMenu.items").split(",");
		for (int i = 0; i < expectedUserMenuItems.length; i++) {
			String actualOption = userMenuOptions.get(i).getText();
			if (actualOption.equals(expectedUserMenuItems[i])) {
				System.out.println("Option Matched : " + expectedUserMenuItems[i]);
			} else {
				System.out.println("Expected option:" + expectedUserMenuItems[i] + " Actual option: " + actualOption);
				isEveryOptionsVerified = false;
			}
		}
		System.out.println(isEveryOptionsVerified);
		return isEveryOptionsVerified;
	}

	public boolean selectuserMenuOption(WebDriver driver, String optionName) {
		if (!userMenu.isDisplayed()) {
			return false;
		}

		userMenu.click();
		WebElement userMenuOption = driver.findElement(By.xpath("//a[text()='" + optionName + "']"));

		if (!userMenuOption.isDisplayed()) {
			return false;
		}

		userMenuOption.click();
		return true;
	}

	public boolean isMyProfilePageDisplayed(WebDriver driver) {
		return utils.CommonUtils.waitForElement(driver, ProfilePage);
	}

	public void selectUserMenu() {
		if (userMenu.isDisplayed()) {
			userMenu.click();
		}
	}

	public void selectEditContact(WebDriver driver) {
		if (CommonUtils.waitForElement(driver, editContactButton)) {
			editContactButton.click();
		}

	}

	public boolean verifyEditContactIframe(WebDriver driver) {
		boolean isIframeloaded = false;
		if (CommonUtils.waitForElement(driver, iframeAboutTab)) {
			driver.switchTo().frame(iframeAboutTab);
			if (Abouttab.isDisplayed() && contactTab.isDisplayed()) {
				isIframeloaded = true;
			}
		}
		return isIframeloaded;
	}

	public boolean verifyLastNameChangeInAboutTab(WebDriver driver, String lastName) {
		boolean isLastNameChanged = false;
		if (Abouttab.isDisplayed()) {
			Abouttab.click();
			AbouttablastName.clear();
			AbouttablastName.sendKeys(lastName);
			saveAllButton.click();
			driver.switchTo().parentFrame();
			if (userProfilePageNameDisplay.isDisplayed()) {
				String actualName = userProfilePageNameDisplay.getText();
				if (actualName.contains(lastName)) {
					isLastNameChanged = true;
				}
			}
		}

		return isLastNameChanged;
	}

	public boolean verifyCreatePost(WebDriver driver, String message) {
		boolean isPostCreated = false;
		if (postLink.isDisplayed()) {
			postLink.click();
			driver.switchTo().frame(0);
			postTextArea.sendKeys(message);
			driver.switchTo().defaultContent();
			if (shareButton.isDisplayed()) {
				shareButton.click();
				isPostCreated = true;
			}
		}
		return isPostCreated;
	}

	public boolean verifyFileUpload(WebDriver driver, String filePath) {
		boolean isFileUploadSuccess = false;

		utils.CommonUtils.explicitSleep(Duration.ofSeconds(5));
		if (CommonUtils.waitForElement(driver, fileLink)) {
			fileLink.click();
			utils.CommonUtils.explicitSleep(Duration.ofSeconds(5));
			if (CommonUtils.waitForElement(driver, uploadFileLink)) {
				uploadFileLink.click();
			}

			utils.CommonUtils.explicitSleep(Duration.ofSeconds(5));
			chooseFileButton.sendKeys(filePath);
			shareButton.click();
			if (CommonUtils.waitForElement(driver, shareButton)) {
				if (verifyFilePostElement.isDisplayed()) {
					isFileUploadSuccess = true;
				}
			}
		}
		return isFileUploadSuccess;

	}

	public void clickOnUpdatePhotoButton(WebDriver driver) {
		CommonUtils.mouseHover(driver, moderatorButton);
		utils.CommonUtils.explicitSleep(Duration.ofSeconds(5));

		if (updateButton.isDisplayed()) {
			updateButton.click();
		}
	}

	public boolean verifyPhotoUpload(WebDriver driver, String photoFilePath) {
		boolean isPhotoUploaded = false;
		this.clickOnUpdatePhotoButton(driver);

		driver.switchTo().frame(photoUploadIframe);
		utils.CommonUtils.explicitSleep(Duration.ofSeconds(20));

		if (CommonUtils.waitForElement(driver, choosePhoto)) {
			choosePhoto.sendKeys(photoFilePath);
			// utils.CommonUtils.explicitSleep(Duration.ofSeconds(10));
			CommonUtils.waitForElement(driver, photoSaveButton1);
			// driver.switchTo().parentFrame();
			photoSaveButton1.click();
		}

		// utils.CommonUtils.explicitSleep(Duration.ofSeconds(5));
		if (CommonUtils.waitForElementToDisappear(driver, spinnerIcon) && CommonUtils.waitForElement(driver, photoSaveButton2)) {
			photoSaveButton2.click();
			if (CommonUtils.waitForElementToDisappear(driver, spinnerWhileCropping)) {
				isPhotoUploaded = true;
			}
		}

		driver.switchTo().parentFrame();
		return isPhotoUploaded;
	}

	public boolean isMySettingsDisplayed(WebDriver driver) {
		return utils.CommonUtils.waitForElementToBeDisplayed(driver, quickLinksHeader);
	}

	public WebElement getMySettingsQuickLink(String linkText) {
		for (WebElement element : mySettingsQuickLinks) {
			String elementText = element.getText();
			if (elementText.contains(linkText)) {
				return element;
			}
		}

		return null;
	}

}
