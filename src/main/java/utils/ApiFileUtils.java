package utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.jayway.jsonpath.JsonPath;

import FileConstants.FConstants;

/**
 * Thsi class is for read json files and read the data from a file and return
 * the values
 */
public class ApiFileUtils {

	/**
	 * This method reads a json file
	 * 
	 * @return string values
	 * @throws IOException
	 */
	public static String readJsonFileToString() throws IOException {
		byte[] data = Files.readAllBytes(Paths.get(FConstants.API_TEST_DATA_FILE));
		return new String(data, StandardCharsets.UTF_8);
	}

	/**
	 * This method takes an argument as
	 * 
	 * @param jsonPath
	 * @return object
	 * @throws IOException
	 */
	public static Object getTestData(String jsonPath) throws IOException {
		String testData = "";
		testData = ApiFileUtils.readJsonFileToString();
		Object value = JsonPath.read(testData, jsonPath);
		return value;
	}
}
