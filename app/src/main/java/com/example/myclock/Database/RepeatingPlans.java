package com.example.myclock.Database;

import java.util.ArrayList;
import java.util.HashMap;

public class RepeatingPlans {
    private static HashMap<Long, ArrayList<Plan>> repeatingDays = new HashMap<>();
    public static void addPlan (Plan p, Long time) {
        if (repeatingDays.get(time) == null) {
            repeatingDays.put(time, new ArrayList<Plan>());
        }
        repeatingDays.get(time).add(p);
    }

    public static ArrayList<Plan> getPlans (Long time) {
        return repeatingDays.get(time);
    }

    public static void removePlan (Plan p) {
        for (ArrayList<Plan> list : repeatingDays.values()) {
            while (list.contains(p))
                list.remove(p);
        }
    }

    public static ArrayList<Plan> getPlansWithNotification (Long time) {
        ArrayList<Plan> temp = new ArrayList<>();
        if (repeatingDays.get(time) == null)
            return null;

        for (Plan p : repeatingDays.get(time)) {
            if (p.hasNotification())
                temp.add(p);
        }
        return temp;
    }
}
