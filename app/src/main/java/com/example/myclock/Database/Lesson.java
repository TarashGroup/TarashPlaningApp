package com.example.myclock.Database;

public class Lesson {
    private boolean done = false;
    private String name;
    private Integer value;

    public Lesson(boolean isDone, String name, Integer value) {
        this.done = isDone;
        this.name = name;
        this.value = value;
        AllLessons.AddToList(this);
    }

    public void setStatus (boolean status) {
        done = status;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }

    public boolean isDone() {
        return done;
    }
}
