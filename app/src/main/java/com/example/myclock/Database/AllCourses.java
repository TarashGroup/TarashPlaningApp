package com.example.myclock.Database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class AllCourses {
    private static HashMap<Integer, Course> coursesHashMap;
    private static boolean hasBeenLoaded = false;

    public static int AddToList(Course l) {
        if (!hasBeenLoaded)
            load();

        int ID = MaxID.courseMaxID();
        l.setSelf_ID(ID);
        coursesHashMap.put(ID, l);

        return ID;
    }

    public static Course getByID (int ID) {
        if (!hasBeenLoaded)
            load();

        return coursesHashMap.get(ID);
    }

    public static void updateByID (Integer ID, Course newCourse) {
        if (!hasBeenLoaded)
            load();

        if (ID == -1)
            return;

        coursesHashMap.put(ID, newCourse);
    }


    public static int removeByCourse(Course l) {
        if (!hasBeenLoaded)
            load();

        int ID = l.getSelf_ID();
        removeByID(ID);
        return ID;
    }


    public static ArrayList<Course> getCoursesByListOfIDs (ArrayList<Integer> IDs) {
        if (!hasBeenLoaded)
            load();

        ArrayList<Course> temp = new ArrayList<>();
        for (Integer ID : IDs) {
            Course found = getByID(ID);
            if (found != null) {
                temp.add(found);
            }
        }
        return temp;
    }

    public static void removeByID (int ID) {
        if (!hasBeenLoaded)
            load();

        coursesHashMap.remove(ID);
    }

    public static void load () {
        // HashMap.put(ID, l);-
        hasBeenLoaded = true;
    }
}
