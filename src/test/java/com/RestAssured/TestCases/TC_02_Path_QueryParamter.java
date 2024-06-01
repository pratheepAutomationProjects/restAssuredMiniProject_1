package com.RestAssured.TestCases;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class TC_02_Path_QueryParamter {
	
	@Test
	public void PathQueryParam() {
		given()
		.pathParam("myPath", "users")
		.queryParam("page", 2)
		.queryParam("id", 8)
		
		.when()
		.get("https://reqres.in/api/{myPath}")
		.then()
		.statusCode(200)
		.log().all();
	}
	@Test
	void testHeader() {
		given()
		.when()
		.post("https://www.google.com/")
		.then()
		.header("Content-Type", "text/html; charset=UTF-8");
		
	}
	
	@Test
	void headers() {
		Response resp=
		given()
		.when()
		.post("https://www.google.com/");
		
		Headers hr =resp.getHeaders();
		for(Header hrr:hr) {
			System.out.println(hrr);
		}
		
		
		
	}

}
