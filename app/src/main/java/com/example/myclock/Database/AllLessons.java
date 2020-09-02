package com.example.myclock.Database;

import com.example.myclock.MainActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class AllLessons {
    private static HashMap<Integer, Lesson> lessonHashMap = new HashMap<>();
    private static boolean hasBeenLoaded = false;
    static {
        load();
    }

    public static int AddToList(Lesson l) {
        if (!hasBeenLoaded)
            load();
        int ID = MaxID.lessonMaxID();
        l.setSelf_ID(ID);
        lessonHashMap.put(ID, l);
        MainActivity.databaseAdapter.addLesson(ID,l);
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
        MainActivity.databaseAdapter.updateLesson(ID, newLesson);
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
        MainActivity.databaseAdapter.removeLesson(ID);
    }

    public static void load () {
        lessonHashMap = MainActivity.databaseAdapter.getLessons();
        hasBeenLoaded = true;
    }
}
