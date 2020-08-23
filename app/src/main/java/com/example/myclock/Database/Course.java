package com.example.myclock.Database;

import android.graphics.Color;
import java.util.ArrayList;

public class Course {
    private String name;
    private Double totalHours = 0.0;
    private Color color;
    private ArrayList<Lesson> parts = new ArrayList<>();

    public Course(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public void removeFromPart (Lesson lesson) {
        parts.remove(lesson);
    }

    public void addPart (Lesson lesson) {
        parts.add(lesson);
    }

    public boolean containsPart (Lesson part) {
        return parts.contains(part);
    }

    public ArrayList<Lesson> getParts() {
        return parts;
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
