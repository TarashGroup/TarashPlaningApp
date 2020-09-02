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
        l.setSelf_ID(ID);
        lessonHashMap.put(ID, l);

        return ID;
    }

    public static Lesson getByID (int ID) {
        if (!hasBeenLoaded)
            load();

        return lessonHashMap.get(ID);
    }

    public static void updateByID (Integer ID, Lesson newLesson) {
        if (!hasBeenLoaded)
            load();

        if (ID == -1)
            return;

        lessonHashMap.put(ID, newLesson);
    }


    public static int removeByLesson(Lesson l) {
        if (!hasBeenLoaded)
            load();

        int ID = l.getSelf_ID();
        removeByID(ID);
        return ID;
    }


    public static ArrayList<Lesson> getLessonsByListOfIDs (ArrayList<Integer> IDs) {
        if (!hasBeenLoaded)
            load();

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
        if (!hasBeenLoaded)
            load();

        lessonHashMap.remove(ID);
    }

    public static void load () {
        // HashMap.put(ID, l);-
        hasBeenLoaded = true;
    }

    public boolean isDuplicate (ArrayList<Integer> IDs, String name) {
        for (Integer ID : IDs) {
            if (getByID(ID).getName().equals(name))
                return true;
        }

        return false;
    }
}
