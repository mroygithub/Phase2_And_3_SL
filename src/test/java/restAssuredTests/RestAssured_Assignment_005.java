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

public class RestAssured_Assignment_005 {


    @Test
    public void RA_Assign_005() {


        // This is When Status = available ;

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .param("status" , "available")
                .when()
                .get("https://petstore.swagger.io/v2/pet/findByStatus")
                .then().assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("status[0]",equalTo("available"));

        // This is When Status = pending ;

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .param("status" , "pending")
                .when()
                .get("https://petstore.swagger.io/v2/pet/findByStatus")
                .then().assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("status[0]",equalTo("pending"));

        // This is When Status = sold ;

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .param("status" , "sold")
                .when()
                .get("https://petstore.swagger.io/v2/pet/findByStatus")
                .then().assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("status[0]",equalTo("sold"));




    }



}
