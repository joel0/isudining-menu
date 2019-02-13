package us.jmay;

import com.google.gson.*;
import java.io.*;
import java.net.URL;
import java.util.*;

public class DiningHandler {

    private Map<Integer, String> locations;
    private String urlFormat;
    private Gson gson;

    public DiningHandler(Gson gson, Map<Integer, String> locations, String url) {
        this.gson = gson;
        this.locations = locations;
        this.urlFormat = url;
    }

    public void setupDining() {
        ArrayList<DiningCenter> diningCenters = new ArrayList<>();

        try {
            for (String locationName : locations.values()) {
                String url = String.format(urlFormat, locationName);
                String menuJson = fetchUrl(url);
                DiningCenter[] diningCenter = gson.fromJson(menuJson, DiningCenter[].class);
                diningCenters.add(diningCenter[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] meals = getMeals(diningCenters);
        Scanner s = new Scanner(System.in);
        int selection;
        for (int i = 0; i < meals.length; i++) {
            System.out.printf("%d. %s\n", i, meals[i]);
        }
        do {
            System.out.print("Meal: ");
            System.out.flush();
            selection = s.nextInt();
        } while (selection < 0 || selection >= meals.length);

        for (DiningCenter diningCenter : diningCenters) {
            System.out.println(diningCenter.toString(meals[selection]));
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
