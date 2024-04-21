package utils;

import java.io.File;
import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class RestUtils {

	/**
	 * This method takes
	 * 
	 * @param headers
	 * @param payload
	 * @param endpoint
	 * @return token
	 */
	public Response post(HashMap<String, String> headers, Object payload, String endpoint) {
		return RestAssured.given().headers(headers).body(payload).when().post(endpoint);
	}

	/**
	 * This method takes
	 * 
	 * @param headers
	 * @param endpoint
	 * @return data
	 */
	public Response get(HashMap<String, String> headers, String endpoint) {
		return RestAssured.given().headers(headers).when().post(endpoint);
	}

	/**
	 * This method takes
	 * 
	 * @param headers
	 * @param payload
	 * @param endpoint
	 * @return response
	 */
	public Response put(HashMap<String, String> headers, HashMap<String, String> payload, String endpoint) {
		return RestAssured.given().headers(headers).body(payload).when().post(endpoint);
	}

	/**
	 * This method takes
	 * 
	 * @param res
	 * @param schema if it is matches the schema the testcase will pass otherwise
	 *               fail
	 */

	public void validateSchema(Response res, File schema) {
		res.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(schema)).extract().response();
	}

}
