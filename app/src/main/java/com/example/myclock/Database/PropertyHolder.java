package com.example.myclock.Database;

import java.util.ArrayList;
import java.util.HashMap;

public class PropertyHolder {
    HashMap<Long, DailyInformation> days = new HashMap<>();

    public DailyInformation getByDay (Long time) {
        return days.get(time);
    }

    public void addToDaily (Long time, Plan p) {
        if (days.get(time) == null) {
            days.put(time, new DailyInformation());
        }
        days.get(time).addToDailyPlan(p);
    }
}
