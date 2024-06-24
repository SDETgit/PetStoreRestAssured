package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.User;
import io.restassured.response.Response;

//Here we did CRUD method implementation Create Read Update Delete 
public class UserEndPoints {
	
	public static Response CreateUser (User payload){ 
		Response response = 
		given()
			.contentType("application/json")
			.accept("application/json")
			.body(payload)
		.when() 
			.post(Routes.post_url);
		return response;
	}
	public static Response	ReadUser (String username){
		Response response =
			given()
				.pathParam("username", username)
			.when() 
				.get(Routes.get_url);
			return response;
		
	}
	public static Response	UpdateUser (String username , User payload){
		Response response =
			given()
				.contentType("application/json")
				.accept("application/json")
				.pathParams("username", username)
				.body(payload)
			.when() 
				.put(Routes.update_url);
			return response;
	}
	public static Response	DeleteUser (String username){
		Response response =
			given()
				.pathParams("username", username)
			.when() 
				.delete(Routes.delete_url);
			return response;
	}

}
