package us.jmay;

import java.time.LocalTime;
import java.util.Date;

public class MealHour {
    /** Meal name */
    public String name;
    public String start_time;
    public String end_time;
    private String active;

    public LocalTime getStartTime() {
        return LocalTime.parse(start_time);
    }

    public LocalTime getEndTime() {
        return LocalTime.parse(end_time);
    }

    public boolean getActive() {
        return active.equals("1");
    }
}
