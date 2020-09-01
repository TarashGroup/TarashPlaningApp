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

        int ID = MaxID.lessonMaxID();
        lessonHashMap.put(ID, l);

        return ID;
    }

    public static Lesson getByID (int ID) {
        return lessonHashMap.get(ID);
    }

    public static void changeByLesson (Lesson first, Lesson changed) {
        lessonHashMap.put(getIDByLesson(first), changed);
    }

    public static int removeByLesson(Lesson l) {
        int ID = getIDByLesson(l);
        removeByID(ID);
        return ID;
    }

    public static int getIDByLesson (Lesson l) {
        for (Integer ID : lessonHashMap.keySet()) {
            Lesson found = getByID(ID);
            if (found != null && found.equals(l)) {
                return ID;
            }
        }
        return -1;
    }

    public static ArrayList<Lesson> getLessonsByListOfIDs (ArrayList<Integer> IDs) {
        ArrayList<Lesson> temp = new ArrayList<>();
        for (Integer ID : IDs) {
            Lesson found = getByID(ID);
            if (found != null) {
                temp.add(found);
            }
        }
        return temp;
    }

    public static void removeByID (int ID) {
        lessonHashMap.remove(ID);
    }

    public static void load () {
        // HashMap.put(ID, l);-
        hasBeenLoaded = true;
    }
}
