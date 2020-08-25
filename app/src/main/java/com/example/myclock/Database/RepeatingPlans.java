package com.example.myclock.Database;

import java.util.ArrayList;

public class RepeatingPlans {
    // Days : 0, 1, ... 6
    // First time plansOfDay is null, after that we use database to read it.
    private static ArrayList<ArrayList<Plan>> plansOfDays;

    public RepeatingPlans(ArrayList<ArrayList<Plan>> plansOfDays) {
        if (plansOfDays == null) {
            plansOfDays = new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                plansOfDays.add(new ArrayList<Plan>());
            }
        }
        RepeatingPlans.plansOfDays = plansOfDays;
    }

    public static void add_To_Day (int day, Plan plan) {
        plansOfDays.get(day).add(plan);
    }

    public void add_Repeating_Plans () {

    }

}
