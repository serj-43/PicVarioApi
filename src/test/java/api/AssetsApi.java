package api;

import entities.Asset;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;

public class AssetsApi extends ApiSpecification {
    private static final String PATH = "/content_import/assets/";

    public int createNewAsset(Asset asset, String token) {
        return given(requestSpecApiV1(PATH, token))
                .body(asset)
                .log().all()
                .when()
                .post("/create")
                .then()
                .extract()
                .jsonPath()
                .get("pk");
    }
}
