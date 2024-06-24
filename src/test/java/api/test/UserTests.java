package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {
	Faker faker;
	User userPayload;
	public Logger logger; // for logs
	@BeforeClass    //This method will execute before all test
	public void setupData() {
	faker = new Faker();
	userPayload=new User();
	
	userPayload.setId(faker.idNumber().hashCode());
	userPayload.setUsername(faker.name().username());
	userPayload.setFirstName (faker.name().firstName());
	userPayload.setLastName (faker.name().lastName());
	userPayload.setEmail(faker.internet().safeEmailAddress());
	userPayload.setPassword (faker.internet().password (5, 10));
	userPayload.setPhone (faker.phoneNumber().cellPhone());
	
	//logs
			logger= LogManager.getLogger(this.getClass());
			
			logger.debug("debugging.....");
	}

	@Test(priority=1)

	public void testPostUser()
	{
		
		logger.info("********** Creating user  ***************");
	Response response=UserEndPoints.CreateUser(userPayload);
	response.then().log().all(); 
	
	Assert.assertEquals(response.getStatusCode(), 200);
	
	logger.info("**********User is creatged  ***************");
	}
	
	@Test(priority=2)

	public void testGetUserByName()
	{
		logger.info("********** Reading User Info ***************");
	Response response=UserEndPoints.ReadUser(userPayload.getUsername());
	response.then().log().all(); 
	
	Assert.assertEquals(response.getStatusCode(), 200);
	logger.info("**********User info  is displayed ***************");
	}

	@Test (priority =3)
	public void testUpdateUserByName() {
		logger.info("********** Updating User ***************");
		//Update data using payload 
		userPayload.setFirstName (faker.name().firstName());
		userPayload.setLastName (faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response =UserEndPoints.UpdateUser(userPayload.getUsername(), userPayload);
        response.then().log().body();
        
        Assert.assertEquals(response.getStatusCode(),200);
    	logger.info("********** User updated ***************");
        //Checking data after update 
       Response responseafterupdate= UserEndPoints.ReadUser(userPayload.getUsername());
       Assert.assertEquals(responseafterupdate.getStatusCode(),200);
       responseafterupdate.then().log().all();
	}
	
	@Test (priority =4)
	
	public void testDeleteUser () {
		logger.info("**********   Deleting User  ***************");
		Response response = UserEndPoints.DeleteUser(userPayload.getUsername());
		Assert.assertEquals(response.statusCode(), 200);
		logger.info("********** User deleted ***************");
	}
	
}

