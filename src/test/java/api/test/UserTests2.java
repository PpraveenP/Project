package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests2 {

	Faker fk;
	User up;
	public Logger logger;
	 
	@BeforeClass()
	public void setupData()
	{
		fk=new Faker();
		up=new User();
		
		up.setId(fk.idNumber().hashCode());
		up.setUsername(fk.name().username());
		up.setFirstName(fk.name().firstName());
		up.setLastName(fk.name().lastName());
		up.setEmail(fk.internet().safeEmailAddress());
		up.setPhone(fk.phoneNumber().cellPhone());
		up.setPassword(fk.internet().password(5,10));
		
		logger=LogManager.getLogger(this.getClass());
	}
	
	@Test(priority=1)
	public void testPostUser()
	{
		logger.info("*******  creating user *******");
		
		Response response=UserEndPoints2.createUser(up);
	
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		
		logger.info("*******  user is created *******");
	}
	@Test(priority=2)
	public void testGetUser()
	{
		logger.info("*******  reading  user information *******");
		
	      Response response = UserEndPoints2.readUser(this.up.getUsername());
		
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		
		logger.info("******* user information is displayed *******");
	}
	@Test(priority=3)
	public void testUpdateUser()
	{
		logger.info("******* updating user information  *******");
		
		up.setFirstName(fk.name().firstName());
		up.setLastName(fk.name().lastName());
		up.setEmail(fk.internet().safeEmailAddress());
		
		Response response=UserEndPoints2.updateUser(this.up.getUsername(), up);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		
		//checking the data after updating 
		Response resAfterUpdate=UserEndPoints2.readUser(this.up.getUsername());
		Assert.assertEquals(response.getStatusCode(),200);
		
		logger.info("******* user information is updated *******");
	}
	@Test(priority=4)
	public void testDeletUser()
	{
		logger.info("******* Deleting user information  *******");
		
		Response response=UserEndPoints2.deleteUser(this.up.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		
		logger.info("******* user information is deleted *******");
		
	}
	
	
}
