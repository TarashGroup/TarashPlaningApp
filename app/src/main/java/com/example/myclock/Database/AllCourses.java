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

        int ID = GetMaxID.getCoursesMaxID();
        coursesHashMap.put(ID, l);

        return ID;
    }

    public static Course getByID (int ID) {
        return coursesHashMap.get(ID);
    }

    public static void changeByCourse (Course first, Course changed) {
        coursesHashMap.put(getIDByCourse(first), changed);
    }

    public static int removeByItem (Course l) {
        int ID = getIDByCourse(l);
        removeByID(ID);
        return ID;
    }

    public static int getIDByCourse (Course l) {
        for (Integer ID : coursesHashMap.keySet()) {
            Course found = getByID(ID);
            if (found != null && found.equals(l)) {
                return ID;
            }
        }
        return -1;
    }

    public static ArrayList<Course> getCoursesByListOfIDs (ArrayList<Integer> IDs) {
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
        coursesHashMap.remove(ID);
    }

    public static void load () {
        // HashMap.put(ID, l);-
        hasBeenLoaded = true;
    }
}
