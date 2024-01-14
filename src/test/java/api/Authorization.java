package api;

import entities.User;
import io.restassured.http.ContentType;
import util.ITestData;

import static io.restassured.RestAssured.given;

public class Authorization implements ITestData {

    public static String authPath = "/api-token-auth/";

    public String getAuthToken(User user) {
        return given()
                .baseUri(baseUrl + authPath)
                .contentType(ContentType.JSON)
                .body(user)
                .log().all()
                .when()
                .post()
                .then()
                .extract()
                .jsonPath()
                .get("token");
    }
}
