package us.jmay;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import us.jmay.utils.DataUtil;
import us.jmay.utils.JsonData;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class Main {

    private static GsonBuilder builder = new GsonBuilder().registerTypeAdapter(JsonData.class, new JsonData.JsonHandler());
    private static Gson g = builder.create();

    public static void main(String[] args) throws IOException {
        String text = Files.readString(DataUtil.getLocationFile().toPath());
        JsonData data = g.fromJson(text, JsonData.class);
        DiningHandler handler = new DiningHandler(g, data.getLocations(), data.getUrl());
        handler.setupDining();
    }
}
