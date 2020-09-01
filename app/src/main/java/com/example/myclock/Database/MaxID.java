package com.example.myclock.Database;

import com.example.myclock.MainActivity;

public class MaxID {
    private static final int LESSON_MAX_ID = 0;
    private static final int COURSE_MAX_ID = 1;
    private static final int PLAN_MAX_ID = 2;
    private static final int DAY_MAX_ID = 3;
    private static int lessonMaxID = -1;
    private static int courseMaxID = -1;
    private static int planMaxID = -1;
    private static int dayMaxID = -1;
    static {
        load();
    }
    public static void load(){
        lessonMaxID = MainActivity.databaseAdapter.getMaxID(LESSON_MAX_ID);
        courseMaxID =  MainActivity.databaseAdapter.getMaxID(COURSE_MAX_ID);
        planMaxID = MainActivity.databaseAdapter.getMaxID(PLAN_MAX_ID);
        dayMaxID = MainActivity.databaseAdapter.getMaxID(DAY_MAX_ID);
    }
    public static int lessonMaxID () {
        lessonMaxID++;
        MainActivity.databaseAdapter.updateMaxID(LESSON_MAX_ID,lessonMaxID);
        return lessonMaxID;
    }
    public static int courseMaxID () {
        courseMaxID++;
        MainActivity.databaseAdapter.updateMaxID(COURSE_MAX_ID,courseMaxID);
        return courseMaxID;
    }
    public static int planMaxID () {
        planMaxID++;
        MainActivity.databaseAdapter.updateMaxID(PLAN_MAX_ID,planMaxID);
        return planMaxID;
    }
    public static int dayMaxID () {
        dayMaxID++;
        MainActivity.databaseAdapter.updateMaxID(DAY_MAX_ID,dayMaxID);
        return dayMaxID;
    }
}
