package restAssuredTests;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

public class RestAssured_Assignment_001 {

    @Test
    public void E2E_Testing(){

        String petid = "2003";
        String petname = "petname";

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

        String Response_id_From_POST_Call = response.getBody().jsonPath().getString("id");
        Assert.assertEquals(Response_id_From_POST_Call ,petid );
        String name = response.getBody().jsonPath().getString("name");
        System.out.println(Response_id_From_POST_Call+name);

        String url = "https://petstore.swagger.io/v2/pet/"+Response_id_From_POST_Call;



        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON).when().get(url)
                .then().assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("$" , hasKey("status"))
                .body("$" , hasKey("id"))
                .body("id",equalTo(Integer.parseInt(Response_id_From_POST_Call)))
                .body("name",equalTo(name))
                .body("status",equalTo("available"));


        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON).when().delete(url)
                .then().assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("$" , hasKey("code"))
                .body("$" , hasKey("message"))
                .body("message",equalTo(String.valueOf(Response_id_From_POST_Call)))
                .body("code",equalTo(200));

    }



}
