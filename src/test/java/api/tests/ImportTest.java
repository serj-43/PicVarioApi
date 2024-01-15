package api.tests;

import api.AssetsApi;
import api.Authorization;
import api.ImportApi;
import api.SearchApi;
import com.google.gson.reflect.TypeToken;
import entities.Asset;
import entities.SearchResult;
import entities.User;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import util.DataProviderHelper;

import java.util.List;

import static org.testng.Assert.assertEquals;


public class ImportTest {

    private Asset asset;
    private User user;
    private SearchResult find, searchResult;
    String token;
    private AssetsApi assetsApi;
    private ImportApi importApi;
    private SearchApi searchApi;

    @DataProvider
    public static Object[][] getPrepare() {
        return new Object[][]{
                {
                        new DataProviderHelper<User>()
                                .getEntityListFromJson("datajson/Users.json", "testUser", new TypeToken<List<User>>() {
                                }.getType()).next()[0],
                        new DataProviderHelper<Asset>()
                                .getEntityListFromJson("datajson/Assets.json", "createAsset", new TypeToken<List<Asset>>() {
                                }.getType()).next()[0],
                }
        };
    }

    @Factory(dataProvider = "getPrepare")
    public ImportTest(User user, Asset asset) {
        this.user = user;
        this.asset = asset;
    }

    @BeforeGroups("uploadImages")
    public void beforeAll() {
        token = new Authorization().getAuthToken(user);
        assetsApi = new AssetsApi();
        importApi = new ImportApi();
        searchApi = new SearchApi();
        asset.setExternalID("test" + (int)(Math.random() * 10000));
        asset.setPk(assetsApi.createNewAsset(asset, token));

    }

    @Test(groups = "uploadImages")
    public void uploadImageTest() throws InterruptedException {
        int assetID = importApi.uploadFile(asset, token);
        find = SearchResult.builder()
                .search("pcvr-" + assetID)
                .build();
        Thread.sleep(10000);
        searchResult = searchApi.getSearchResult(find, token);
        assertEquals(searchResult.getThumbnail().getName(), "testPicture.jpeg");
    }
}
