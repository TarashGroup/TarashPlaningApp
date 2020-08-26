package com.example.myclock.Database;
import java.util.ArrayList;
import java.util.HashMap;

// TODO: add period and alarm

public class Plan {
    private Course course;
    private Double totalTime;
    private Double passedTime;
    private Test test;
    private HashMap<String, Boolean> checklists = new HashMap<>();
    private boolean notification;

    public Plan(Course course, Double totalTime, ArrayList<Long> repeatingDays, boolean notification
                    , ArrayList<String> checkLists) {
        this.course = course;
        this.totalTime = totalTime;
        if (repeatingDays != null) {
            for (Long time : repeatingDays) {
                PropertyHolder.addRepeatingPlan(this, time);
            }
        }
    }

    public boolean hasNotification () {
        return notification;
    }

    public void addTest (Test test) {
        if (this.test == null) {
            this.test = test;
        }
        else {
            this.test.addToCurrentTest(test);
        }
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Double getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Double totalTime) {
        this.totalTime = totalTime;
    }

    public Double getPassedTime() {
        return passedTime;
    }

    public void setPassedTime(Double passedTime) {
        this.passedTime = passedTime;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public void addToCheckLists (String title) {
        checklists.put(title, false);
    }

    public void doneCheckList (String title) {
        checklists.put(title, true);
    }

    public void undoneCheckList (String title) {
        checklists.put(title, false);
    }

    public HashMap<String, Boolean> getChecklists () {
        return checklists;
    }
}
