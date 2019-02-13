package us.jmay;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import us.jmay.utils.DataUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    private static GsonBuilder builder = new GsonBuilder().registerTypeAdapter(JsonData.class, new JsonData.JsonHandler());
    private static Gson g = builder.create();

    // TODO: Doesn't work but we're trying chief,
    public static void main(String[] args) throws IOException, URISyntaxException {
        DataUtil util = new DataUtil();
        String text = new String(Files.readAllBytes(Paths.get(util.getLocationFile().toURI())), StandardCharsets.UTF_8);
        JsonData data = g.fromJson(text, JsonData.class);
        DiningHandler handler = new DiningHandler(g, data.getLocations(), data.getUrl());
        handler.setupDining();
    }
}
