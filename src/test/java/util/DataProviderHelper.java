package util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class DataProviderHelper<E> {

    protected String iterationBlockName = null;

    public Iterator<Object[]> getEntityListFromJson(String filePath, String blockName, Type typeOfE) {
        Gson gson = new Gson();
        List<E> objectsList;
        try (InputStream inputStream = DataProviderHelper.class
                .getClassLoader()
                .getResourceAsStream(filePath)) {
            assert inputStream != null;
                JsonElement jsonData = new JsonParser().parse(new InputStreamReader(inputStream));
                JsonElement dataSet = jsonData.getAsJsonObject().get(blockName);
                objectsList = gson.fromJson(dataSet, typeOfE);
            assert objectsList != null;
            return objectsList
                    .stream()
                    .map((g) -> new Object[]{g})
                    .collect(Collectors.toList())
                    .iterator();
        } catch (IOException e) {
            System.out.println( "Не получилось прочитать данные из json" + filePath);
            return null;
        }
    }
}
