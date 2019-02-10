package us.jmay;


import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ArrayList<DiningCenter> diningCenters = new ArrayList<>();
        Gson g = new Gson();
        String urlFormat = "https://dining.iastate.edu/wp-json/dining/menu-hours/get-single-location/?slug=%s&time=";
        String[] locationNames = {
                "seasons-marketplace-2-2",
                "conversations-2",
                "union-drive-marketplace-2-2",
                "friley-windows-2-2"
        };
        try {
            for (String locationName : locationNames) {
                String url = String.format(urlFormat, locationName);
                String menuJson = fetchUrl(url);
                DiningCenter[] diningCenter = g.fromJson(menuJson, DiningCenter[].class);
                diningCenters.add(diningCenter[0]);
//                System.out.println(diningCenter[0].toString("Dinner"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//        for (String meal : getMeals(diningCenters)) {
//            System.out.println(meal);
//        }
        for (DiningCenter diningCenter : diningCenters) {
            System.out.println(diningCenter.toString("Lunch"));
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

    static String[] getMeals(List<DiningCenter> locations) {
        HashSet<String> meals = new HashSet<>();
        for (DiningCenter location : locations) {
//            for (Menu menu : location.menus) {
//                meals.add(menu.section);
//            }
            for (MealHour hour : location.todaysHours) {
                if (hour.getActive()) {
                    meals.add(hour.name);
                }
            }
        }
        return meals.toArray(new String[0]);
    }
}
