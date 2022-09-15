package stepDef;

import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.path.xml.config.XmlPathConfig.xmlPathConfig;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.*;
import org.apache.http.HttpStatus;

import java.util.HashMap;

public class RestAssuredTask {

    private HashMap<String,String> DataForEnv;


    @Test
    public void Rest_Assured_Assignment_001(){

        String petid = "1665";
        String petname = "petname";

        // Hitting the POST Call

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\n" +
                        "  \"id\": "+petid+",\n" +
                        "  \"category\": {\n" +
                        "    \"id\": 0,\n" +
                        "    \"name\": \"string\"\n" +
                        "  },\n" +
                        "  \"name\": \""+petname+"\",\n" +
                        "  \"photoUrls\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"tags\": [\n" +
                        "    {\n" +
                        "      \"id\": 0,\n" +
                        "      \"name\": \"string\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"status\": \"available\"\n" +
                        "}")
                .when()
                .post("https://petstore.swagger.io/v2/pet");

        String id = response.getBody().jsonPath().getString("id");
        String name = response.getBody().jsonPath().getString("name");
        System.out.println(id+name);




        // Hitting the GET Call

        String url = "https://petstore.swagger.io/v2/pet/"+id;



        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON).when().get(url)
                .then().assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("$" , hasKey("status"))
                .body("$" , hasKey("id"))
                .body("id",equalTo(Integer.parseInt(id)))
                .body("name",equalTo(name))
                .body("status",equalTo("available"));


        // Hitting the DELETE Call

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON).when().delete(url)
                .then().assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("$" , hasKey("code"))
                .body("$" , hasKey("message"))
                .body("message",equalTo(String.valueOf(id)))
                .body("code",equalTo(200));

    }


    @Test
    public void Rest_Assured_Assignment_002(){

        // Validate a PUT Call ....

        DataForEnv = new HashMap<>();
        DataForEnv.put("DEV","Java Development");
        DataForEnv.put("QA","QA API Testing");
        DataForEnv.put("PROD","Production Support");

        putCallTesting("PROD");

    }

    public void putCallTesting(String env){

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\n" +
                        "   \"name\": \"morpheus\",\n" +
                        "   \"job\": \""+DataForEnv.get(env)+"\"\n" +
                        "}\n")
                .when()
                .post("https://reqres.in/api/users/2");

        String job = response.getBody().jsonPath().getString("job");
        Assert.assertEquals(job , DataForEnv.get(env));
        Assert.assertEquals(response.statusCode() , 201);

    }


    // Validate a Get Call ...

    @Test
    public void Rest_Assured_Assignment_003(){

        String url = "https://reqres.in/api/users?page=2";



        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON).when().get(url)
                .then().assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("$" , hasKey("page"))
                .body("$" , hasKey("support"))
                .body("page",equalTo(2))
                .body("support.url",equalTo("https://reqres.in/#support-heading"));

    }




}
