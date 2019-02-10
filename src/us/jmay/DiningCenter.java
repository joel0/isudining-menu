package us.jmay;

import java.util.HashSet;

public class DiningCenter {
    public String title;
    public String facility;
    public Menu[] menus;
    public MealHour[] todaysHours;

    public Menu getMeal(String section) {
        for (Menu menu : menus) {
            if (menu.section.equals(section)) {
                return menu;
            }
        }
        return null;
    }

    public MealHour getOpenTime(String section) {
        for (MealHour mealHour : todaysHours) {
            if (mealHour.name.equals(section)) {
                return mealHour;
            }
        }
        return null;
    }

    public String toString(String section) {
        Menu m = getMeal(section);
        MealHour hour = getOpenTime(section);
        if (m == null || (hour != null && !hour.getActive())) {
            return title + "\n    N/A\n";
        }

        StringBuilder out = new StringBuilder();
        out.append(title);
        if (hour != null) {
            out.append(" : ").append(hour.getStartTime().toString()).append(" - ").append(hour.getEndTime().toString());
        }
        out.append('\n');
        for (MenuDisplay menuDisplay : m.menuDisplays) {
            out.append(menuDisplay.toString());
        }
        return out.toString();
    }
}
