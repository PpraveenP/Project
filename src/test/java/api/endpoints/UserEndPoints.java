package api.endpoints;

import static io.restassured.RestAssured.*;
import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class UserEndPoints {
	
	public static Response createUser(User up)
	{
		Response response = given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(up)
		.when()
		.post(Routes.post_url);
		return response;
		
	}
	
	public static Response readUser(String uesrName)
	{
		Response response = given()
				.pathParam("username", uesrName)
		.when()
		.get(Routes.get_url);
		return response;
	}
	
	public static Response updateUser(String userName, User up)
	{
		Response response=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.pathParam("username", userName)
		.body(up)
		
		.when()
		.put(Routes.put_url);

		return response;
	}
	
	public static Response deleteUser(String userName)
	{
		Response response=given()
				.pathParam("username", userName)
				.when()
				.delete(Routes.delet_url);
		return response;
	}

				
	}

