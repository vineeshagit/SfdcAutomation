package FileConstants;

import utils.CommonUtils;

public class FConstants {
	public static final String LOGIN_TESTDATA_FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/loginTestData.properties";
	public static final String USERMENU_TESTDATA_FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/userMenuTestData.properties";
	public static final String TEST_FILE_PATH = "D://hackathon//datadiffer.png";
	public static final String PHOTO_FILE_PATH = "D://hackathon//Capture.png";
	public static final String SCREENSHOTS_FOLDER_PATH = System.getProperty("user.dir") + "/src/test/resources/Screenshots/"
			+ CommonUtils.getDateAndTimeStamp() + ".png";

	public static final String SCREENSHOTS_FOLDER = System.getProperty("user.dir") + "/src/test/resources/Screenshots/"
			+ CommonUtils.getDateAndTimeStamp() + ".png";
	public static final String REPORT_FOLDER_PATH = System.getProperty("user.dir") + "/src/test/resources/Screenshots/SFDC_"
			+ CommonUtils.getDateAndTimeStamp() + ".html";
	public static final String API_TEST_DATA_FILE = System.getProperty("user.dir") + "/src/main/java/ApiTestData/testData.json";
	// SFDC_ it is a file name
	// (/ is for folder name)
}
