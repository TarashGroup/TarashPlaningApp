package com.example.myclock.Database;

import java.util.ArrayList;

public class DailyInformation {
    private ArrayList<Plan> plans = new ArrayList<>();

    public void addToDailyPlan (Plan p) {
        plans.add(p);
    }

    public void removePlan (Plan p) {
        plans.remove(p);
    }

    public void removeWithRepeating (Plan p) {
        plans.remove(p);
        PropertyHolder.removeRepeatingPlan(p);
    }

    public boolean containsPlan (Plan plan) {
        return plans.contains(plan);
    }
}
