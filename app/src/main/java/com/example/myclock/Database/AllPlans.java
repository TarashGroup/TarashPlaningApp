package com.example.myclock.Database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class AllPlans {
    private static HashMap<Integer, Plan> plansHashMap;
    private static boolean hasBeenLoaded = false;

    public static int AddToList(Plan l) {
        if (!hasBeenLoaded)
            load();

        int ID = MaxID.planMaxID();
        l.setSelf_ID(ID);
        plansHashMap.put(ID, l);

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
    }

    public static void load () {
        // HashMap.put(ID, l);-
        hasBeenLoaded = true;
    }
}
