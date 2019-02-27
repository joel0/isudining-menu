package us.jmay.utils;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Class used to represent the JSON data in locations.json
 */
public class JsonData {

    private Map<Integer, String> locations;
    private String urlFormat;

    public JsonData(Map<Integer, String> locations, String urlFormat) {
        this.locations = locations;
        this.urlFormat = urlFormat;
    }

    public Map<Integer, String> getLocations() {
        return locations;
    }

    public String getUrl() {
        return urlFormat;
    }

    public static class JsonHandler implements JsonDeserializer<JsonData> {

        @Override
        public JsonData deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            Map<Integer, String> locations = new HashMap<>();
            JsonObject object = jsonElement.getAsJsonObject();
            JsonArray loc = object.get("locations").getAsJsonArray();
            for (int i = 0; i < loc.size(); i++) {
                locations.put(i, loc.get(i).getAsString());
            }
            return new JsonData(locations, object.get("url").getAsString());
        }
    }
}
