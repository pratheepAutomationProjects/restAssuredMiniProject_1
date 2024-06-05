package api.endpoints;

import static io.restassured.RestAssured.*;
import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpoints {
	
	public static Response CreateUser(User payload){	
		Response response  =given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload)
		.when().post("/v2/user");
		return response;
	}
	
	public static Response getUserDetails(String username) {
		Response response  =given().contentType(ContentType.JSON).accept(ContentType.JSON).pathParam("Username", username)
		.when().get("/v2/user/{Username}");
		return response;
	}
	

	public static Response UpdateUser(String username, User payload){	
		Response response =given().contentType(ContentType.JSON).accept(ContentType.JSON).pathParam("Username", username).body(payload)
		.when().put("/v2/user/{Username}");
		return response;
	}
	
	public static Response deleteUser(String username) {
		Response response  =given().contentType(ContentType.JSON).accept(ContentType.JSON).pathParam("Username", username)
		.when().delete("/v2/user/{Username}");
		return response;
	}

	
}
