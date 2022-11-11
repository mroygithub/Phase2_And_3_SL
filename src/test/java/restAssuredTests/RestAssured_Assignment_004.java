package restAssuredTests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.*;

public class RestAssured_Assignment_004 {


    @Test
    public void doPOSTRESTCall(){


        HashMap<String , String> StatusData = new  HashMap<String , String>();

        StatusData.put("QA" , "available_QA");
        StatusData.put("DEV" , "available_DEV");
        StatusData.put("PROD" , "available_PROD");


        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\n" +
                        " \"id\": 9223372016900013000, \"category\": {" +
                        "\"id\": 20021," +
                        "\"name\": \"string\" }," +
                        "\"name\": \"doggie\", \"photoUrls\": [" +
                        "\"string\"" +
                        "], \"tags\": [" +
                        "{\n" +
                        " \"id\": 0," +
                        " \"name\": \"string\"" +
                        "} ]," +
                        "\"status\": \""+StatusData.get("QA")+"\"" +
                        "}")
                .when()
                .post("https://petstore.swagger.io/v2/pet")
                .then().assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("category.id",equalTo(20021))
                .body("status",equalTo(StatusData.get("QA")));
    }



}
