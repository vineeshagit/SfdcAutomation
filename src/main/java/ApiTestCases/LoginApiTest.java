package ApiTestCases;

import static org.hamcrest.MatcherAssert.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ApiTestData.AddUser;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.ApiFileUtils;

public class LoginApiTest extends BaseApiTest {
	String token = null;

	@Test(priority = -1)
	public void loginTest01() throws IOException {
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");

		Object payload = ApiFileUtils.getTestData("$.payload.login");

		Response loginRes = request.post(headers, payload, "login");
		request.validateSchema(loginRes, new File(System.getProperty("user.dir") + "\\src\\main\\java\\ResponseSchema\\LoginSchema.json"));

		// Assert.assertEquals(loginRes.getStatusCode(), 201);
		// loginRes.then().assertThat().statusCode(201);
		// System.out.println(loginRes.asPrettyString());
		assertThat("status code Should Mtch", loginRes.getStatusCode() == 201);

	}

	@Test(enabled = false)
	public void getDataTest02() {
		Response getReq = RestAssured.given().header("Content-type", "application/json").header("token", token).when().get("getdata").then()
				.statusCode(200).extract().response();

		// System.out.println(getReq.asPrettyString());

	}

	@Test()
	public void addUserTest03() throws JsonProcessingException {
		/*
		 * HashMap<String,String> user1 = new HashMap<String,String>();
		 * user1.put("accountno","TA-1000444"); user1.put("departmentno","4");
		 * user1.put("salary","75000"); user1.put("pincode","505007");
		 */

		/*
		 * JsonObject user= new JsonObject();
		 * user.addProperty("accountno","TA-1000444");
		 * user.addProperty("departmentno","4"); user.addProperty("salary","75000");
		 * user.addProperty("pincode","505007");
		 */

		AddUser user = new AddUser("TA-1000", "3", "85000", "505007");

		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		String jString = om.writeValueAsString(user);

		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-type", "application/json");
		headers.put("token", token);

		Response getReq = RestAssured.given().headers(headers).body(jString).when().post("addData").then().statusCode(201).extract().response();

		System.out.println(getReq.asPrettyString());

	}
}
