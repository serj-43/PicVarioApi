package api;

import com.google.gson.reflect.TypeToken;
import entities.Asset;
import entities.User;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import util.DataProviderHelper;

import java.util.List;


public class ImportTest {

    private Asset asset;
    private User user;
    String token;
    private AssetsApi assetsApi;
    private int assetID;

    @DataProvider
    public static Object[][] getPrepare() {
        return new Object[][]{
                {
                        new DataProviderHelper<User>()
                                .getEntityListFromJson("datajson/Users.json", "testUser", new TypeToken<List<User>>()  {
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
        assetID = assetsApi.createNewAsset(asset, token);
    }

    @Test(groups = "uploadImages")
    public void uploadImageTest() {

    }
}
