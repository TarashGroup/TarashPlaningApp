package com.example.myclock.Database;
import android.util.Log;

import com.example.myclock.Views.CheckListContainerAdapter;

import java.util.ArrayList;
import java.util.HashMap;

// TODO: add period and alarm

public class Plan {
    private Integer self_ID;
    private Integer courseID;
    private int duration;
    private Double passedTime;
    private HashMap<String, Boolean> checklists = new HashMap<>();
    private boolean notification;
    private int notificationInMinutes;
    private ArrayList<String> checkList;

    public Plan(Course course, int duration, ArrayList<Long> repeatingDays,
                boolean notification ,int notificationInMinutes, ArrayList<String> checkList) {

        this.courseID = course.getSelf_ID();
        this.duration = duration;
        this.notificationInMinutes = notificationInMinutes;
        this.notification = notification;
        if (repeatingDays != null) {
            for (Long time : repeatingDays) {
                PropertyHolder.addRepeatingPlan(this, time);
            }
        }
        this.checkList = checkList;
    }

    public void setSelf_ID(Integer self_ID) {
        this.self_ID = self_ID;
    }

    public Integer getSelf_ID() {
        return self_ID;
    }

    public boolean hasNotification () {
        return notification;
    }

    public Course getCourse() {
        return AllCourses.getByID(courseID);
    }

    public void setCourse(Course course) {
        this.courseID = course.getSelf_ID();
        updateSql();
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int totalTime) {
        this.duration = totalTime;
        updateSql();
    }

    public Double getPassedTime() {
        return passedTime;
    }

    public void setPassedTime(Double passedTime) {
        this.passedTime = passedTime;
        updateSql();
    }

    public void addToCheckLists (String title) {
        checklists.put(title, false);
        updateSql();
    }

    public void doneCheckList (String title) {
        checklists.put(title, true);
        updateSql();
    }

    public void undoneCheckList (String title) {
        checklists.put(title, false);
        updateSql();
    }

    public HashMap<String, Boolean> getChecklists () {
        return checklists;
    }

    private void updateSql () {
        AllPlans.updateByID(self_ID, this);
    }
}
