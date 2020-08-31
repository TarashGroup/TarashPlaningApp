package com.example.myclock.Database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class AllLessons {
    private static HashMap<Integer, Lesson> lessonHashMap;
    private static boolean hasBeenLoaded = false;

    public static int AddToList(Lesson l) {
        if (!hasBeenLoaded)
            load();

        int ID = GetMaxID.getLessonsMaxID();
        lessonHashMap.put(ID, l);

        return ID;
    }

    public static Lesson getByID (int ID) {
        return lessonHashMap.get(ID);
    }

    public static void changeByID (Lesson l, int ID) {
        lessonHashMap.put(ID, l);
    }

    public static int removeByItem (Lesson l) {
        for (Integer ID : lessonHashMap.keySet()) {
            if (Objects.equals(lessonHashMap.get(ID), l)) {
                removeByID(ID);
                return ID;
            }
        }

        return -1;
    }

    public static void removeByID (int ID) {
        lessonHashMap.remove(ID);
    }

    public static void load () {
        // HashMap.put(ID, l);-
        hasBeenLoaded = true;
    }
}
