package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.response.Response;

//Here we did CRUD method implementation Create Read Update Delete 
public class UserEndPoints2 {
	
	//Method created to get URL's from properties file
	static ResourceBundle getURL() {  //route is name of property file 
	 ResourceBundle routes = ResourceBundle.getBundle("routes"); //Here we do not need to specify path default it takes routes file path under resources 
	 return routes;
	}
	
	public static Response CreateUser (User payload){ 
		
		String post_url = getURL().getString("post_url");
		Response response = 
		given()
			.contentType("application/json")
			.accept("application/json")
			.body(payload)
		.when() 
			.post(post_url);
		return response;
	}
	public static Response	ReadUser (String username){
	   String get_url =getURL().getString("get_url");
		
		Response response =
			given()
				.pathParam("username", username)
			.when() 
				.get(get_url);
			return response;
		
	}
	public static Response	UpdateUser (String username , User payload){
		
	String update_url =	getURL().getString("update_url");
		Response response =
			given()
				.contentType("application/json")
				.accept("application/json")
				.pathParams("username", username)
				.body(payload)
			.when() 
				.put(update_url);
			return response;
	}
	public static Response	DeleteUser (String username){
		String delete_url = getURL().getString("delete_url");
		Response response =
			given()
				.pathParams("username", username)
			.when() 
				.delete(delete_url);
			return response;
	}

}
