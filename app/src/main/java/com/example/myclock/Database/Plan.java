package com.example.myclock.Database;
import android.util.Log;

import com.example.myclock.Views.CheckListContainerAdapter;

import java.util.ArrayList;
import java.util.HashMap;

// TODO: add period and alarm

public class Plan {
    private Course course;
    int duration;
    private Double passedTime;
    private Test test;
    private HashMap<String, Boolean> checklists = new HashMap<>();
    private boolean notification;
    ArrayList<String> checkList;

    public Plan(Course course, int duration, ArrayList<Long> repeatingDays, Long notification
                    , ArrayList<String> checkList) {
        this.course = course;
        this.duration = duration;
        if (repeatingDays != null) {
            for (Long time : repeatingDays) {
                PropertyHolder.addRepeatingPlan(this, time);
            }
        }
        this.checkList = checkList;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(Double totalTime) {
        this.duration = duration;
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
