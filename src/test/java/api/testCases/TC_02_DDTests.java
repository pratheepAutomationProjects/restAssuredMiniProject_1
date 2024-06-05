package api.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import Utils.ConstantsHelper;
import api.endpoints.UserEndpoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TC_02_DDTests {
	User payload;
	
	@Test(priority =1,dataProvider="data", dataProviderClass=DataProviders.class)
	public void testPostUser(String userID, String username, String frstName, String LastName, String email, String password, String phone) {
		
		payload = new User();
		payload.setId(Integer.parseInt(userID));
		payload.setEmail(email);
		payload.setFirstName(frstName);
		payload.setLastName(LastName);
		payload.setPassword(password);
		payload.setUsername(username);
		payload.setPhone(phone);
		
		RestAssured.baseURI = ConstantsHelper.url;
		Response response = UserEndpoints.CreateUser(payload);
		Assert.assertEquals(response.getStatusCode(),200);		
	}
	
	@Test(priority =2,dataProvider="usernames", dataProviderClass=DataProviders.class)
	public void testDeleteUSer(String username ) {
		RestAssured.baseURI = ConstantsHelper.url;
		Response response = UserEndpoints.deleteUser(username);
		Assert.assertEquals(response.getStatusCode(),200);
		
	}

}
