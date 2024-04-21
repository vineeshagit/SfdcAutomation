package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "username")
	public WebElement username;

	@FindBy(id = "password")
	public WebElement password;

	@FindBy(name = "Login")
	public WebElement loginButton;

	@FindBy(xpath = "//*[@id='rememberUn']")
	public WebElement rememberMe;

	@FindBy(id = "error")
	public WebElement errorMessage;

	@FindBy(partialLinkText = "Forgot")
	public WebElement fogotPassword;

	@FindBy(id = "idcard-identity")
	public WebElement savedUsername;

	@FindBy(id = "un")
	public WebElement forgotUsername;

	public void enterUsername(String emailID) {
		logger.info("LoginPage: enterUsername: Entering the username ");
		if (username.isDisplayed()) {
			username.sendKeys(emailID);
		} else {
			// System.out.println("Username element not found");
			logger.error("LoginPage: enterUsername: username not displayed");
		}
	}

	public void enterPassword(String pass) {
		if (password.isDisplayed()) {
			password.sendKeys(pass);
		} else {
			// System.out.println("pass element not found");
			logger.error("LoginPage: enterPassword: Password not displayed");
		}
	}

	public void clickLoginButton() {
		if (loginButton.isDisplayed()) {
			loginButton.click();
		} else {
			System.out.println("login  element not found");
		}
	}

	public void selectRememberMeCheckbox() {
		if (!rememberMe.isSelected()) {
			rememberMe.click();
		} else {
			System.out.println("pass element not found");
		}
	}

	public void clearPassword() {
		if (password.isDisplayed()) {
			password.clear();
		} else {
			System.out.println("password entered already");
		}
	}

	public String isLoginPageOpened(WebDriver driver) {
		return driver.getTitle();
	}

	public String getLoginErrorText() {
		if (errorMessage.isDisplayed()) {
			return errorMessage.getText();
		} else {
			return null;
		}

	}
}
