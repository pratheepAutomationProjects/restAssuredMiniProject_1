package api.testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import Utils.ConstantsHelper;
import api.endpoints.UserEndpoints;
import api.payload.User;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TC_01_UserTests{
	Faker data;
	User userPayload;
	
	@BeforeSuite
	public void setupData() {
		
		data= new Faker();
		userPayload = new User();
		userPayload.setId(data.idNumber().hashCode());
		userPayload.setEmail(data.internet().safeEmailAddress());
		userPayload.setFirstName(data.name().firstName());
		userPayload.setLastName(data.name().lastName());
		userPayload.setPassword(data.internet().password(5,10));
		userPayload.setUsername(data.name().username());
		userPayload.setPhone(data.phoneNumber().cellPhone());		
	}
	
	@Test(priority =1)
	public void testPostUser() {
		RestAssured.baseURI = ConstantsHelper.url;
		Response response =UserEndpoints.CreateUser(userPayload);	
		response.then().log().all();		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=2)
	public void testGetUserDetails() {
		RestAssured.baseURI = ConstantsHelper.url;
		Response response =UserEndpoints.getUserDetails(userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
	}
	

//	public void GetUserDetails() {
//	String name=	userPayload.getUsername();
//	
//	 given().contentType(ContentType.JSON)
//	.when().get("https://petstore.swagger.io/v2/user/"+name).then().log().all();
//	}
	
	@Test(priority=3)
	public void testUpdateUser() {
		RestAssured.baseURI = ConstantsHelper.url;
		
		userPayload.setFirstName(data.name().firstName());
		userPayload.setLastName(data.name().lastName());
		userPayload.setEmail(data.internet().safeEmailAddress());
		
		Response response =UserEndpoints.UpdateUser(this.userPayload.getUsername(), userPayload);	
		response.then().log().all();		
		Assert.assertEquals(response.getStatusCode(), 200);
		//checking data after update
		
	}
	
	@Test(priority=4)
	public void testDeleteUserDetails() {
		RestAssured.baseURI = ConstantsHelper.url;
		Response response =UserEndpoints.deleteUser(this.userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);
	}
	

}
