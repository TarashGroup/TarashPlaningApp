package com.example.myclock.Database;

import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import LitnerBox.LitnerBox;
import TestSheet.TestSheet;

public class PropertyHolder {


    // LitnerBox Fields
    private static ArrayList<ArrayList<Note>> litnerBox;
    private static ArrayList<Note> doneBoxes;
    private static ArrayList<Note> failedBoxes;
    private static Boolean litnerShouldBeUpdated = Boolean.TRUE; // pashmam :/
    private final static List<Integer> readingDays = Arrays.asList(1,3,7,15,31);

    // Plans Fields
    private static HashMap<Long, DailyInformation> days = new HashMap<>();

    // Repeating Plans Fields
    private static HashMap<Long, ArrayList<Plan>> repeatingDays = new HashMap<>();

    // All values Fields (Not letting user make 2 same courses) TODO: Should be completed and used
    private static ArrayList<Course> allCourse = new ArrayList<>();

    // TestSheet Fields
    private static ArrayList<TestSheet> testSheets = new ArrayList<>();

    // Time Fields
    private static Long lastVisitedDay = 0L;
    private static Long pastDays = 0L;

    // Plans methods
    public static DailyInformation getPlansByDay (Long time) {
        return days.get(time);
    }

    public static void addToDaily (Long time, Plan p) {
        if (days.get(time) == null) {
            days.put(time, new DailyInformation());
        }
        days.get(time).addToDailyPlan(p);
    }
    ///////////////////////////////////////////////////////////////////////////////////
    // Repeating Plans methods
    public static void set_Repeating_Plans () {
        Long time = GetDay.getDay();
        ArrayList<Plan> plansForToday = getRepeatingPlans(time);
        if (plansForToday != null) {
            for (Plan p : plansForToday) {
                addToDaily(time, p);
            }
        }
        repeatingDays.remove(time);
    }

    public static void addRepeatingPlan (Plan p, Long time) {
        if (repeatingDays.get(time) == null) {
            repeatingDays.put(time, new ArrayList<Plan>());
        }
        repeatingDays.get(time).add(p);
    }

    public static ArrayList<Plan> getRepeatingPlans (Long time) {
        return repeatingDays.get(time);
    }

    public static void removeRepeatingPlan (Plan p) {
        for (ArrayList<Plan> list : repeatingDays.values()) {
            while (list.contains(p))
                list.remove(p);
        }
    }

    public static ArrayList<Plan> getPlansWithNotification (Long time) {
        // TODO: Should be changed and be written for all plans as well
        return null;
    }
    ////////////////////////////////////////////////////////////////////////////////////



    // All values methods
    public static void addToCourses (Course course) {
        allCourse.add(course);
    }

    public static void removeFromAllCourses (Course course) {
        allCourse.remove(course);
    }

    public static ArrayList<Course> getAllCourse() {
        return allCourse;
    }

    public static Course getCourseByName (String name) {
        for (Course course : allCourse)
            if (course.getName().equals(name))
                return course;

        return null;
    }

    /////////////////////////////////////////////////////////////////////////////////


    // Load From SqLite
    public static void loadDataBase () {
        lastVisitedDay = 0L; // Load
        pastDays = GetDay.getDay() - lastVisitedDay;

        if (pastDays != 0L) {
            litnerShouldBeUpdated = Boolean.TRUE;
        }

        lastVisitedDay += pastDays;

        // TODO : Load local variables
    }
    /////////////////////////////////////////////////////////////////////////////////


    // LitnerBoxMethods
    public static Long getLastVisitedDay() {
        return lastVisitedDay;
    }

    public static ArrayList<ArrayList<Note>> getLitnerBoxValues() {
        return litnerBox;
    }

    public static ArrayList<Note> getDoneBoxes() {
        return doneBoxes;
    }

    public static ArrayList<Note> getFailedBoxes() {
        return failedBoxes;
    }

    public static Long getPastDays() {
        return pastDays;
    }

    public static void setLitnerShouldBeUpdated(Boolean litnerShouldBeUpdated) {
        PropertyHolder.litnerShouldBeUpdated = litnerShouldBeUpdated;
    }

    public static Boolean getLitnerShouldBeUpdated() {
        return litnerShouldBeUpdated;
    }

    public static void removeFromBox (int index, Note note) {
        litnerBox.get(index).remove(note);
    }

    public static void addToBox (int index, Note note) {
        litnerBox.get(index).add(note);
    }

    public static void removeFromDoneBox (Note note) {
        doneBoxes.remove(note);
    }

    public static void addToDoneBox (Note note) {
        doneBoxes.add(note);
    }

    public static void removeFromFailedBox (Note note) {
        failedBoxes.remove(note);
    }

    public static void addToFailedBox (Note note) {
        failedBoxes.add(note);
    }

    public static void shiftBoxes () {
        for (int i = 31; i >= 0; i--) {
            if (readingDays.contains(i)) {
                failedBoxes.addAll(litnerBox.get(i));
                litnerBox.get(i).clear();
            } else {
                litnerBox.set(i + 1, litnerBox.get(i));
            }
        }
    }
    ////////////////////////////////////////////////////////////////////////////

    // TestSheet methods
    public static ArrayList<TestSheet> getTestSheets() {
        return testSheets;
    }
}
