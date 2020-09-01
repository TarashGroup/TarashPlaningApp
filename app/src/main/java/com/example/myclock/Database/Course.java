package com.example.myclock.Database;
import android.graphics.Color;

import java.util.ArrayList;

public class Course {
    private String name;
    private Double totalHours = 0.0;
    private Color color;
    private ArrayList<Integer> lessonsID = new ArrayList<>();

    public Course(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public void removeFromLessonsByID (Integer ID) {
        AllLessons.removeByID(ID);
    }

    public void changeLesson (Lesson first, Lesson changed) {
        AllLessons.changeByLesson(first, changed);
    }

    public void removeFromLessons (Lesson lesson) {
        int ID = AllLessons.removeByLesson(lesson);
        lessonsID.remove(ID);
    }

    public void addLesson (Lesson lesson) {
        int LessonID = AllLessons.AddToList(lesson);
        lessonsID.add(LessonID);
    }

    public ArrayList<Lesson> getLessons() {
        return AllLessons.getLessonsByListOfIDs(lessonsID);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Double getTotalHours() {
        return totalHours;
    }

    public void addToTotalHours (Double d) {
        totalHours += d;
    }
}
