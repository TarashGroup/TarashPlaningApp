package com.example.myclock;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.lang.reflect.Array;

public class AddPlanActivity extends AppCompatActivity {

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plan);
        Spinner spHour = findViewById(R.id.sp_hour);
        Spinner spMinute = findViewById(R.id.sp_minute);
        Integer[] hours = new Integer[25];
        Integer[] minutes = new Integer[60];
        for (int i = 0; i <= 24; i++) {
            hours[i]=i;
            minutes[i] = i;
        }
        for (int i = 25; i < 60; i++)
            minutes[i] = i;

        spHour.setAdapter( new ArrayAdapter<Integer>(this,R.layout.support_simple_spinner_dropdown_item,hours));
        spMinute.setAdapter(new ArrayAdapter<Integer>(this,R.layout.support_simple_spinner_dropdown_item,minutes));
        ListView lvCheckList = findViewById(R.id.lv_check_list);
        String[] listItems =new String[1];
        listItems[0] = "مروز";
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AddPlanActivity.this,MainActivity.class));
        finish();
    }
}