package api.endpoints;

import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class UserEndPoints2 {
	
	//method created for getting url from properties file
	
	public static ResourceBundle getUrl()
	{
		ResourceBundle routes = ResourceBundle.getBundle("routes");//load the properties file
		return routes;
	}
	
	public static Response createUser(User up)
	{
		String post_url = getUrl().getString("post_url");
		Response response = given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(up)
		.when()
		.post(post_url);
		return response;
		
	}
	
	public static Response readUser(String uesrName)
	{
		String get_url = getUrl().getString("get_url");
		Response response = given()
				.pathParam("username", uesrName)
		.when()
		.get(get_url);
		return response;
	}
	
	public static Response updateUser(String userName, User up)
	{
		String put_url = getUrl().getString("put_url");
		Response response=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.pathParam("username", userName)
		.body(up)
		
		.when()
		.put(put_url);

		return response;
	}
	
	public static Response deleteUser(String userName)
	{
		String delet_url = getUrl().getString("delet_url");

		Response response=given()
				.pathParam("username", userName)
				.when()
				.delete(delet_url);
		return response;
	}

				
	}

