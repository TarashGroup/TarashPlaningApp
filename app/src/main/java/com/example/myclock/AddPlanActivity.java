package com.example.myclock;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myclock.Database.GetDay;
import com.example.myclock.Database.Plan;
import com.example.myclock.Database.PropertyHolder;
import com.example.myclock.Dialigs.ChooseCourseDialog;
import com.example.myclock.Dialigs.MyDialog;
import com.example.myclock.Views.CheckListContainerAdapter;

import java.util.ArrayList;
import java.util.Objects;

import ir.hamsaa.persiandatepicker.Listener;
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;

public class AddPlanActivity extends AppCompatActivity {

    public ArrayList<String> courses = new ArrayList<>();
    CheckListContainerAdapter checkListContainerAdapter;
    private TimePicker tpDuration;
    private AlertDialog setDurationDialog;
    int durationInMinutes;
    int limit = 20;
    String course;
    Long notification;
    private PersianDatePickerDialog persianDatePickerDialog;
    private PersianCalendar startingTime = new PersianCalendar();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plan);
        LinearLayout checkListContainer = findViewById(R.id.CheckListContainer);
        Button btnAddCheckList = findViewById(R.id.btn_check_list);
        checkListContainerAdapter = new CheckListContainerAdapter(this , checkListContainer, btnAddCheckList);

        //****************************************toolbar
        androidx.appcompat.widget.Toolbar tbAddPlan = findViewById(R.id.tb_addPlan_activity);
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null)
            actionbar.setDisplayHomeAsUpEnabled(true);
        tbAddPlan.setTitleTextAppearance(this, R.style.vaziriFont);
        tbAddPlan.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        //*****************************************--toolbar

        persianDatePickerDialog = new PersianDatePickerDialog(this)
                .setPositiveButtonString("تایید")
                .setNegativeButton("بازگشت")
                .setTodayButton("امروز")
                .setTodayButtonVisible(true)
                .setMinYear(1300)
                .setMaxYear(PersianDatePickerDialog.THIS_YEAR)
                .setInitDate(new PersianCalendar())
                .setActionTextColor(Color.GRAY)
                .setShowInBottomSheet(true)
                .setTitleType(PersianDatePickerDialog.WEEKDAY_DAY_MONTH_YEAR)
                .setListener(datePickerListener);


        //*********************************************test
        courses.add("ریاضی");
        courses.add("شیمی");
        courses.add("عربی");
        courses.add("زبان");
        courses.add("فیزیک");
        //*********************************************/test

    }

    Listener datePickerListener = new Listener() {
        @Override
        public void onDateSelected(PersianCalendar persianCalendar) {
            startingTime = persianCalendar;
        }

        @Override
        public void onDismissed() {

        }
    };

    //Todo: courses must be fixed.
    public void chooseCourse(View view){
        new ChooseCourseDialog(courses,(Button) findViewById(R.id.btn_choose_course)
                ,getResources().getColor(R.color.Bar),(int) getResources().getDimension(R.dimen.button_height)
                ,this,(LinearLayout) getLayoutInflater().inflate(R.layout.choose_course_dialog,null),R.layout.choose_course_dialog);
    }



//*****************************************************************************
// TODO: 8/27/2020 course / repeatingDays/ notification must be set
    private void sendPlanToDataBase(){
        PropertyHolder.addToDaily(getStartingTime(),
                new Plan(PropertyHolder.getCourseByName(course), durationInMinutes,
                        getAllDaysToAdd(null, 0), notification , checkListContainerAdapter.getCheckListsAsString() ));
    }

    public void repeat_button_listener(View view) {
        persianDatePickerDialog.show();
    }


    public void addCheckList(View view) {
        checkListContainerAdapter.addToContainer (R.string.checkList);
    }

    public void commit(View view){
        if (!checkListContainerAdapter.check(limit))
            return;
    }

    private Long getStartingTime () {
        return GetDay.getDay(startingTime.getTimeInMillis() / 1000);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AddPlanActivity.this,MainActivity.class));
        finish();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void showDurationDialog(View view){
        LinearLayout setDurationLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.duration_dialog,null);
        tpDuration = setDurationLayout.findViewById(R.id.tp_duration);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.duration_dialog);
        setDurationDialog = builder.create();
        Objects.requireNonNull(setDurationDialog.getWindow()).getAttributes().windowAnimations = R.style.DialogAnimation;
        tpDuration.setIs24HourView(true);
        setDurationDialog.setView(setDurationLayout);
        tpDuration.setHour(1);
        tpDuration.setMinute(0);
        tpDuration.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                if (i>10)
                    timePicker.setHour(10);
            }
        });
        setDurationDialog.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void setDuration(View view){
        Button button = findViewById(R.id.btn_duration);
        int minute = tpDuration.getMinute();
        int hour = tpDuration.getHour();
        durationInMinutes = hour * 60 + minute;
        if (minute == 0 && hour == 0)
            Toast.makeText(this,"تو ۰ دقیقه که نمیتونی کاری بکنی:)" , Toast.LENGTH_SHORT).show();
        else {
            if (minute > 0 && hour > 0)
                button.setText(hour + " ساعت " +  "و" +" "+ minute + " دقیقه ");
            else if (minute > 0)
                button.setText(minute + " دقیقه ");
            else
                button.setText(hour + " ساعت ");
            setDurationDialog.dismiss();
            MyDialog.setButtonSelected(button);
        }
    }



    private ArrayList<Long> getAllDaysToAdd (ArrayList<Boolean> daysOfTheWeek, int repeatTime) {
        Long firstDay = getStartingTime();
        if (daysOfTheWeek == null)
            return null;

        ArrayList<Long> temp = new ArrayList<>();
        for (int i = 0; i < repeatTime; i++) {
            for (int j = 0; j < 7; j++) {
                temp.add(firstDay + (i * 7) + j);
            }
        }
        temp.remove(firstDay);
        return temp;
    }

    public void calendar_listener (View view) {
        persianDatePickerDialog.show();
    }
}