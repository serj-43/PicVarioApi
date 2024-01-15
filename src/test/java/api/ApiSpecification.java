package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import util.ITestData;

import static org.hamcrest.Matchers.*;

public class ApiSpecification implements ITestData {

    public static String apiV1Url = "/api/v1";
    public static RequestSpecification requestSpecApiJson(String Path, String token) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUrl + apiV1Url + Path)
                .addHeader("Authorization", "Token " + token)
                .setContentType(ContentType.JSON)
                .build();
    }

    public static RequestSpecification requestSpecApiFile(String Path, String token) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUrl + apiV1Url + Path)
                .addHeader("Authorization", "Token " + token)
                .addHeader("Content-Type", "multipart/form-data")
                .build();
    }

    public static ResponseSpecification responseSpecStatusCode() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody("error", equalTo(""))
                .expectBody("errorMessage", equalTo(""))
                .build();
    }

    public static ResponseSpecification responseSpecFail() {
        return new ResponseSpecBuilder()
                .expectStatusCode(404)
                .expectBody("error", notNullValue())
                .expectBody("errorMessage", notNullValue())
                .build();
    }
}
