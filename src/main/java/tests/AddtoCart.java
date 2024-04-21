package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import utils.CommonUtils;

public class AddtoCart {

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", "/Users/chidr/Downloads/chromedriver-win64/chromedriver-win64/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement searchBar = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
		searchBar.sendKeys("kindle");
		WebElement buttonSearch = driver.findElement(By.xpath("//input[@id='nav-search-submit-button']"));
		buttonSearch.click();
		WebElement bestSellerProduct = driver.findElement(By.xpath("//span[@id='B08B495319-best-seller-label']"));
		bestSellerProduct.click();
		driver.get(
				"https://www.amazon.com/kindle-paperwhite-Signature-Edition/dp/B08B495319/ref=sr_1_6?crid=28IB0SJBBMPUU&keywords=kindle&qid=1705270148&sprefix=kindle%2Caps%2C192&sr=8-6");

		WebElement addToCart = driver.findElement(By.xpath("//input[@id='add-to-cart-button']"));
		addToCart.click();
		WebElement noThanks = driver.findElement(By.xpath("//span[@class='a-button a-button-base abb-intl-decline']//input[@type='submit']"));
		noThanks.click();
		WebElement goToCart = driver.findElement(By.xpath("//a[@href='/cart?ref_=sw_gtc']"));
		goToCart.click();
		addScreenCaptureFromPath(CommonUtils.getScreenshot1(driver));
	}

	private static void addScreenCaptureFromPath(String screenshot1) {
		return;

	}

}

// Here, testing with amazon page. After loading the amazon website it throws a captcha to enter , some website provide a testing environment where captcha is disabled.
// In automation process it doesn't allow the elements to disable as permission is denied for some websites.
//As a reason I have entered captcha manually and proceeded with the test.3333
// 
// I 
