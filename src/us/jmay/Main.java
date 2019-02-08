package us.jmay;


import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Main {

    public static void main(String[] args) {
        Gson g = new Gson();
        String urlFormat = "https://dining.iastate.edu/wp-json/dining/menu-hours/get-single-location/?slug=%s&time=";
        String[] locationNames = {
                "union-drive-marketplace-2-2"
        };
        try {
            for (String locationName : locationNames) {
                String url = String.format(urlFormat, locationName);
                String menuJson = fetchUrl(url);
                DiningCenter[] diningCenter = g.fromJson(menuJson, DiningCenter[].class);
                System.out.println(diningCenter[0].toString("Dinner"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String fetchUrl(String url) throws IOException {
        URL urlObj = new URL(url);
        InputStream rawStream = urlObj.openStream();
        BufferedInputStream stream = new BufferedInputStream(rawStream);
        byte[] body = stream.readAllBytes();
        stream.close();
        rawStream.close();

        return new String(body);
    }
}
