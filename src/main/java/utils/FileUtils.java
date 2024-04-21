package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
//import java .io.File;

import FileConstants.FConstants;

public class FileUtils {

	/**
	 * To read the login test data file
	 * 
	 * @param key provide key to get the value
	 * @return value of the key passed
	 * @throws IOException
	 */
	public static String readLoginTestData(String key) throws IOException {
		FileInputStream f = new FileInputStream(FConstants.LOGIN_TESTDATA_FILE_PATH);
		Properties p = new Properties();
		p.load(f);
		return p.getProperty(key);
	}

	public static String readUserMenuTestData(String key) throws IOException {
		FileInputStream f = new FileInputStream(FConstants.USERMENU_TESTDATA_FILE_PATH);
		Properties p = new Properties();
		p.load(f);
		return p.getProperty(key);
	}
}