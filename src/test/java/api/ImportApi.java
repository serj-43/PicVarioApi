package api;

import entities.Asset;
import entities.SearchResult;

import java.io.File;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ImportApi extends ApiSpecification {
    private static final String PATH = "/content_import/assets/upload/";

    public int uploadFile(Asset asset, String token) {
        return given(requestSpecApiFile(PATH, token))
                .multiPart(new File(asset.getFilepath()))
                .log().all()
                .when()
                .post("/" + asset.getPk())
                .then()
                .extract()
                .jsonPath()
                .get("pk");
    }
}
