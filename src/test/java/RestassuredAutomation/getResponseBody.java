package RestassuredAutomation;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;



public class getResponseBody {
	@Test
	public static void basicAuth() {
		
		Response response = given().auth().basic("admin", "password").when().get("https://lms-admin-rest-service.herokuapp.com/basicauth");
		Assert.assertEquals(response.getStatusCode(), 200,"Response recieved successfully");
	}
		@Test(priority=0)
	public static void getResponse()
	{
		given().
		when().get("https://lms-admin-rest-service.herokuapp.com/programs/?programId=12615").
		then().log().all();
	}
	@Test
	public static void getResponseBody(){
		 
		   given().queryParam("programId","12615")
		           .queryParam("programName","Testing")
		           .queryParam("online","true")
		           .when().get("https://lms-admin-rest-service.herokuapp.com/programs/12615").then().log()
		           .body();
		}
	
	@Test(priority=1)
	public static void getResponseStatus() {
		
	//Response response = given().auth().basic("admin", "password").when().get("https://lms-admin-rest-service.herokuapp.com/programs");
	//Assert.assertEquals(response.getStatusCode(), 200,"Response recieved successfully");
	int statusCode = given().queryParam("12615").
			when().get("https://lms-admin-rest-service.herokuapp.com/programs/12615").getStatusCode();
System.out.println("the response status code is "+statusCode);

given().when().get("https://lms-admin-rest-service.herokuapp.com/programs/12615").then().assertThat().statusCode(200);
	}
	@Test(priority=2)
	private static void getResponseHeader() {
		System.out.println("The headers in the response "+
                get("https://lms-admin-rest-service.herokuapp.com/programs").then().extract()
        .headers());
}
@Test
public static void getResponseTime(){
	  System.out.println("The time taken to fetch the response "+get("https://lms-admin-rest-service.herokuapp.com/programs")
	         .timeIn(TimeUnit.MILLISECONDS) + " milliseconds");
	}

	@Test
	public static void getResponseContentType(){
		   System.out.println("The content type of response "+
		           get("https://lms-admin-rest-service.herokuapp.com/programs").then().extract()
		              .contentType());
		}
	}

