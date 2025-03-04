package Utils;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

public class APIRequestSpec {

    private  static  String BASE_URL = "http://10.195.105.66:7000";

    public static RequestSpecification getRequestSpec(){
        return
                given().
                        baseUri(BASE_URL).
                        header("Content-Type", "application/json");
    }

    public static ResponseSpecification getResponseSpec(){
        return new ResponseSpecBuilder().
                expectStatusCode(200)
                .expectContentType("application/json")
                .build();
    }
}
