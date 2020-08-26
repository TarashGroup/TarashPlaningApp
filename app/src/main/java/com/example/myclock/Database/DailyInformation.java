package com.example.myclock.Database;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DailyInformation {
    private ArrayList<Plan> dailyPlan = new ArrayList<>();
    private Date date;

    public DailyInformation(ArrayList<Plan> dailyPlan, Date date) {
        this.dailyPlan = dailyPlan;
        this.date = date;
    }

    public ArrayList<Plan> getDailyPlan() {
        return dailyPlan;
    }

    public void setDailyPlan(ArrayList<Plan> dailyPlan) {
        this.dailyPlan = dailyPlan;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
