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

public class RestAssured_Assignment_006 {


    @Test
    public void RA_Assign_006() {


        // This is When Status = available ;

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("https://petstore.swagger.io/v2/user/logout")
                .then().assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("message", equalTo("ok"));
    }



}
