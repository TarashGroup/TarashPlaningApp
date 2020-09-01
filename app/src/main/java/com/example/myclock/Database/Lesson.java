package com.example.myclock.Database;

import com.example.myclock.litner.All;

public class Lesson {
    private Integer self_ID;
    private boolean done;
    private String name;
    private Integer value;

    public Lesson(boolean isDone, String name, Integer value) {
        this.done = isDone;
        this.name = name;
        this.value = value;
    }

    public void setSelf_ID (Integer ID) {
        this.self_ID = ID;
    }

    public Integer getSelf_ID() {
        return self_ID;
    }

    public void setStatus (boolean status) {
        this.done = status;
        updateSql();
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }

    public boolean getStatus() {
        return done;
    }

    private void updateSql () {
        AllLessons.updateByID(self_ID, this);
    }
}
