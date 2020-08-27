package com.example.myclock;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.example.myclock.Database.GetDay;
import com.example.myclock.Database.Plan;
import com.example.myclock.Database.PropertyHolder;
import com.example.myclock.Views.CheckListContainerAdapter;
import com.example.myclock.Views.CheckListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import ir.hamsaa.persiandatepicker.Listener;
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;

public class AddPlanActivity extends AppCompatActivity {

    public ArrayList<String> courses = new ArrayList<>();
    CheckListContainerAdapter checkListContainerAdapter;
    private TimePicker tpDuration;
    private AlertDialog setDurationDialog;
    AlertDialog chooseCourseDialog;
    int durationInMinutes;
    int limit = 20;
    String course;
    Long notification;
    ArrayList<Long> repeatingDays;

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

        PersianDatePickerDialog picker = new PersianDatePickerDialog(this)
                .setPositiveButtonString("تایید")
                .setNegativeButton("بازگشت")
                .setTodayButton("امروز")
                .setTodayButtonVisible(true)
                .setMinYear(1300)
                .setMaxYear(PersianDatePickerDialog.THIS_YEAR)
                .setInitDate(new PersianCalendar())
                .setActionTextColor(Color.GRAY)
                .setTitleType(PersianDatePickerDialog.WEEKDAY_DAY_MONTH_YEAR)
                .setListener(new Listener() {
                    @Override
                    public void onDateSelected(PersianCalendar persianCalendar) {
                    }

                    @Override
                    public void onDismissed() {

                    }
                });

        picker.show();

        //*****************************************--toolbar
//        TimePicker picker=(TimePicker)findViewById(R.id.timePicker1);
       // picker.setIs24HourView(true);
        //*********************************************test
        courses.add("ریاضی");
        courses.add("شیمی");
        courses.add("عربی");
        courses.add("زبان");
        courses.add("فیزیک");
        //*********************************************/test

    }

    public void chooseCourse(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.choose_course_dialog);
        chooseCourseDialog = builder.create();
        Objects.requireNonNull(chooseCourseDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        Objects.requireNonNull(chooseCourseDialog.getWindow()).getAttributes().windowAnimations = R.style.DialogAnimation;
        LinearLayout chooseCourseLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.choose_course_dialog,null);
        LinearLayout llCourses  = chooseCourseLayout.findViewById(R.id.ll_courses);
        setCoursesButtons(llCourses);
        chooseCourseDialog.setView(chooseCourseLayout);
        chooseCourseDialog.show();
    }

    private void setCoursesButtons(LinearLayout llCourses){
        llCourses.removeAllViews();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(40, 20, 40, 20);
        params.gravity = Gravity.CENTER;
        for (int i = 0; i < courses.size(); i++) {
            Button button = new Button(this);
            button.setText(courses.get(i));
            button.setGravity(Gravity.CENTER);
            Typeface font = ResourcesCompat.getFont(this, R.font.vazir);
            button.setTypeface(font);
            button.setBackgroundResource(R.drawable.button1);
            button.setHeight((int) getResources().getDimension(R.dimen.button_height));
            button.setLayoutParams(params);
            button.setTextColor(getResources().getColor(R.color.Bar));
            llCourses.addView(button);
            button.setOnClickListener(courseButtonOnClick);
        }
    }

    View.OnClickListener courseButtonOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button btnChooseCourse = findViewById(R.id.btn_choose_course);
            Button btnSelectedCourse = (Button)view;
            btnChooseCourse.setText(btnSelectedCourse.getText());
            course = btnSelectedCourse.getText().toString();
            setButtonSelected(btnChooseCourse);
            chooseCourseDialog.dismiss();
        }
    };


//*****************************************************************************
// TODO: 8/27/2020 course / repeatingDays/ notification must be set
    private void sendPlanToDataBase(){
        PropertyHolder.addToDaily(GetDay.getDay(getStartingTime()),
                new Plan(PropertyHolder.getCourseByName(course), durationInMinutes,
                        repeatingDays, notification , checkListContainerAdapter.getCheckListsAsString() ));
    }

    public void addCheckList(View view) {
        checkListContainerAdapter.addToContainer (R.string.checkList);
    }

    public void commit(View view){
        if (!checkListContainerAdapter.check(limit))
            return;
    }

    private Long getStartingTime () {
        return 0L;
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
        if (minute > 0)
            button.setText(hour + " ساعت " + minute +" دقیقه ");
        else
            button.setText(hour + " ساعت ");
        setDurationDialog.dismiss();
        setButtonSelected(button);
    }

    private void setButtonSelected(Button button){
        button.setBackgroundResource(R.drawable.button3);
    }

    private ArrayList<Long> getAllDaysToAdd (Long firstDay, ArrayList<Boolean> daysOfTheWeek, int repeatTime) {
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
}