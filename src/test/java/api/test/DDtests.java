package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDtests {

	@Test(priority=1,dataProvider = "Data", dataProviderClass = DataProviders.class)
	
	public void testPostUser(String UserId,String username,String firstName,String LastName,String Email,String Password,String Phone)
	{
		User up=new User();
		up.setId(Integer.parseInt(UserId));
		up.setUsername(username);
		up.setFirstName(firstName);
		up.setLastName(LastName);
		up.setEmail(Email);
		up.setPhone(Phone);
		up.setPassword(Password);
		
	
		Response response=UserEndPoints.createUser(up);
		
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		
		
	}
	
	@Test(priority=2,dataProvider = "UserName",dataProviderClass = DataProviders.class)
	
	public void testDeletUser(String userName)
	{
		Response response=UserEndPoints.deleteUser(userName);
		Assert.assertEquals(response.statusCode(),200);
		
	}
	
}
