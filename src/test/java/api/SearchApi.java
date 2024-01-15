package api;

import entities.SearchResult;

import static io.restassured.RestAssured.given;

public class SearchApi extends ApiSpecification {
    private static final String PATH = "/mutual_integration/find_assets/search/";

    public SearchResult getSearchResult(SearchResult search, String token) {
        return given(requestSpecApiJson(PATH, token))
                .body(search)
                .log().all()
                .when()
                .post()
                .then()
                .extract()
                .jsonPath()
                .getList("results", SearchResult.class)
                .get(0);
    }
}
