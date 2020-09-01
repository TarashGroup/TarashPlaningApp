package com.example.myclock.Database;

import java.util.ArrayList;

public class DailyInformationHandler {
    public static DailyInformation getByDay (Long day) {
        return null;
    }

    public static void addPlan (Long day, Plan plan) {
        getByDay(day).addToDailyPlan(plan);
    }

    public static ArrayList<DailyInformation> getSlice (Long from, Long to) {
        return null;
    }
}
