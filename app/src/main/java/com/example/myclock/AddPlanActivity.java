package com.example.myclock;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.example.myclock.Database.GetDay;
import com.example.myclock.Database.Plan;
import com.example.myclock.Database.PropertyHolder;
import com.example.myclock.Dialigs.ChooseCourseDialog;
import com.example.myclock.Dialigs.MyDialog;
import com.example.myclock.Dialigs.RepeatDialog;
import com.example.myclock.Dialigs.TimeDurationDialog;
import com.example.myclock.Views.CheckListContainerAdapter;

import java.util.ArrayList;
import java.util.Objects;

import ir.hamsaa.persiandatepicker.Listener;
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;

public class AddPlanActivity extends AppCompatActivity {
    ChooseCourseDialog chooseCourseDialog;
    TimeDurationDialog timeDurationDialog;
    RepeatDialog repeatDialog;
    public ArrayList<String> courses = new ArrayList<>(); // test
    CheckListContainerAdapter checkListContainerAdapter;
    int limit = 20;
    String course;
    Long notification;
    private PersianDatePickerDialog persianDatePickerDialog;
    private PersianCalendar startingTime = new PersianCalendar();
    private TextView tvDate;

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
                .setMinYear(PersianDatePickerDialog.THIS_YEAR)
                .setInitDate(new PersianCalendar())
                .setActionTextColor(Color.GRAY)
                .setShowInBottomSheet(true)
                .setTitleType(PersianDatePickerDialog.WEEKDAY_DAY_MONTH_YEAR)
                .setTitleColor(getResources().getColor(R.color.Bar))
                .setTypeFace(ResourcesCompat.getFont(AddPlanActivity.this,R.font.vazir))
                .setPickerBackgroundColor(getResources().getColor(R.color.peach))
                .setActionTextColor(getResources().getColor(R.color.Bar))
                .setListener(datePickerListener);
        tvDate = findViewById(R.id.tv_date);
        setTvDateText();

        //*********************************************test
        courses.add("ریاضی");
        courses.add("شیمی");
        courses.add("عربی");
        courses.add("زبان");
        courses.add("فیزیک");
        //*********************************************/test

        chooseCourseDialog = new ChooseCourseDialog(courses,(Button) findViewById(R.id.btn_choose_course)
                ,getResources().getColor(R.color.Bar),(int) getResources().getDimension(R.dimen.button_height)
                ,this,(LinearLayout) getLayoutInflater().inflate(R.layout.choose_course_dialog,null),R.layout.choose_course_dialog);

        timeDurationDialog = new TimeDurationDialog((LinearLayout) getLayoutInflater().inflate(R.layout.duration_dialog,null),
                this,(Button) findViewById(R.id.btn_duration),R.layout.duration_dialog);

        repeatDialog = new RepeatDialog(this,(LinearLayout)getLayoutInflater().inflate(R.layout.repeat_layout,null)
                ,(Button) findViewById(R.id.btn_repeat),R.layout.repeat_layout,getResources());

    }

    Listener datePickerListener = new Listener() {
        @Override
        public void onDateSelected(PersianCalendar persianCalendar) {
            if (GetDay.getDay(persianCalendar.getTimeInMillis()) >= GetDay.getDay(System.currentTimeMillis())) {
                startingTime = persianCalendar;
                persianDatePickerDialog.setInitDate(startingTime,true);
                setTvDateText();
            }else{
                Toast.makeText(AddPlanActivity.this,"برای روزهای قبل که نمیتونی برنامه بریزی!",Toast.LENGTH_SHORT).show();
                persianDatePickerDialog.show();
            }
        }

        @Override
        public void onDismissed() {

        }
    };
    private void setTvDateText(){
        tvDate.setText(String.format("%d %s %d", startingTime.getPersianDay(), startingTime.getPersianMonthName(), startingTime.getPersianYear()));
    }

    //Todo: courses must be fixed.
    public void chooseCourse(View view){
        chooseCourseDialog.start();
    }



//*****************************************************************************
// TODO: 8/27/2020 course / repeatingDays/ notification must be set
    private void sendPlanToDataBase(){
        PropertyHolder.addToDaily(getStartingTime(),
                new Plan(PropertyHolder.getCourseByName(course), timeDurationDialog.getHour() *60 + timeDurationDialog.getMinute(),
                        getAllDaysToAdd(null, 0), notification , checkListContainerAdapter.getCheckListsAsString() ));
    }

    public void repeat_button_listener(View view) {
        repeatDialog.start();
    }

    public void showDatePicker(View view){
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
        return GetDay.getDay(startingTime.getTimeInMillis());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AddPlanActivity.this,MainActivity.class));
        finish();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void showDurationDialog(View view){
        timeDurationDialog.start();
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