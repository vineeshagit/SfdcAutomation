package Listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import tests.BaseTest;
import utils.CommonUtils;

public class SFDCListeners extends BaseTest implements ITestListener {

	public void onTestStart(ITestResult result) {
		BaseTest.test = BaseTest.extent.createTest(result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		BaseTest.test.pass(MarkupHelper.createLabel("PASSED " + result.getName(), ExtentColor.GREEN));
		BaseTest.test.pass("Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		test.addScreenCaptureFromPath(CommonUtils.getScreenshot(driver));
		BaseTest.test.fail(MarkupHelper.createLabel("FAILED " + result.getName(), ExtentColor.RED));
		BaseTest.test.fail("Failed");

	}
}
