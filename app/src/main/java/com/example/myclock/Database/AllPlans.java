package com.example.myclock.Database;

import com.example.myclock.MainActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class AllPlans {
    private static HashMap<Integer, Plan> plansHashMap = new HashMap<>();
    private static boolean hasBeenLoaded = false;
    static {
        load();
    }

    public static int AddToList(Plan p) {
        if (!hasBeenLoaded)
            load();

        int ID = MaxID.planMaxID();
        p.setSelf_ID(ID);
        plansHashMap.put(ID, p);
        MainActivity.databaseAdapter.addPlan(ID,p);

        return ID;
    }

    public static Plan getByID (int ID) {
        if (!hasBeenLoaded)
            load();

        return plansHashMap.get(ID);
    }

    public static void updateByID (Integer ID, Plan newPlan) {
        if (!hasBeenLoaded)
            load();

        if (ID == -1)
            return;

        plansHashMap.put(ID, newPlan);
        MainActivity.databaseAdapter.updatePlan(ID,newPlan);
    }


    public static int removeByPlan(Plan l) {
        if (!hasBeenLoaded)
            load();

        int ID = l.getSelf_ID();
        removeByID(ID);

        return ID;
    }


    public static ArrayList<Plan> getPlansByListOfIDs (ArrayList<Integer> IDs) {
        if (!hasBeenLoaded)
            load();

        ArrayList<Plan> temp = new ArrayList<>();
        for (Integer ID : IDs) {
            Plan found = getByID(ID);
            if (found != null) {
                temp.add(found);
            }
        }
        return temp;
    }

    public static void removeByID (int ID) {
        if (!hasBeenLoaded)
            load();

        plansHashMap.remove(ID);
        MainActivity.databaseAdapter.removePlan(ID);
    }

    public static void load () {
        plansHashMap = MainActivity.databaseAdapter.getPlans();
        hasBeenLoaded = true;
    }
}
