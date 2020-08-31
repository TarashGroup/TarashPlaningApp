package com.example.myclock.Database;
import android.graphics.Color;

import com.example.myclock.litner.All;

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

    public void removeFromPart (Lesson lesson) {
        int ID = AllLessons.removeByItem(lesson);
        lessonsID.remove(ID);
    }

    public void addPart (Lesson lesson) {
        int LessonID = AllLessons.AddToList(lesson);
        lessonsID.add(LessonID);
    }

    public ArrayList<Lesson> getLessons() {
        ArrayList<Lesson> temp = new ArrayList<>();
        for (Integer ID : lessonsID) {
            temp.add(AllLessons.getByID(ID));
        }
        return temp;
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
