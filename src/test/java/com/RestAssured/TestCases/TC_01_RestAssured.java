package com.RestAssured.TestCases;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;


public class TC_01_RestAssured  {
	int id ;

	@Test(priority=1)
	public void getRequests() {
		
		given()
		
		.when().get("https://reqres.in/api/users?page=2").
		
		then().statusCode(200).body("page", equalTo(2)).log().all();
		
	}
	
	@Test(priority=2)
	public void CreateRequest() {
		HashMap<String,String> hs = new HashMap<>();
		hs.put("name", "rtr");
		hs.put("job", "leader");
		
	    id =given().body(hs).contentType("application/json")
		.when().post("https://reqres.in/api/users").jsonPath().getInt("id");
		
		//.then().statusCode(201).log().all()
	}
	@Test(priority =3,dependsOnMethods= {"CreateRequest"})
	public void UpdateRequest() {
		HashMap<String,String> hs = new HashMap<>();
		hs.put("name", "rtroo");
		hs.put("job", "leader");
		given().body(hs).contentType("application/json")
		.when().post("https://reqres.in/api/users/"+id).jsonPath().getInt("id");
		
	}
	
	@Test(priority=4)
	public void DeleteRequest() {
		
		given()
		.when().delete("https://reqres.in/api/users"+id)
		.then().statusCode(204);
	}
}
