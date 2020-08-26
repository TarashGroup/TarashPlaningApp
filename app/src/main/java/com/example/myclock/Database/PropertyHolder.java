package com.example.myclock.Database;

import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class PropertyHolder {
    private static HashMap<Long, DailyInformation> days = new HashMap<>();
    private static HashMap<Long, ArrayList<Plan>> repeatingDays = new HashMap<>();
    private static ArrayList<Course> allCourse = new ArrayList<>();

    public static DailyInformation getPlansByDay (Long time) {
        return days.get(time);
    }

    public static void addToDaily (Long time, Plan p) {
        Log.d("************", time + " " + p);
        if (days.get(time) == null) {
            days.put(time, new DailyInformation());
        }
        days.get(time).addToDailyPlan(p);
    }

    public static void set_Repeating_Plans () {
        Long time = GetDay.getDay();
        ArrayList<Plan> plansForToday = getRepeatingPlans(time);
        if (plansForToday != null) {
            for (Plan p : plansForToday) {
                addToDaily(time, p);
            }
        }
    } // TODO remove

    public static void addRepeatingPlan (Plan p, Long time) {
        Log.d("**********Repeating", time + " " + p);
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
        ArrayList<Plan> temp = new ArrayList<>();
        if (repeatingDays.get(time) == null)
            return null;

        for (Plan p : repeatingDays.get(time)) {
            if (p.hasNotification())
                temp.add(p);
        }
        return temp;
    }

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
}
