package com.example.myclock;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.example.myclock.Views.CheckListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class AddPlanActivity extends AppCompatActivity {
    private ArrayList<String> checklists = new ArrayList<>();
    private LinearLayout checkListContainer;
    private CheckListView checkListView;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plan);
        checkListContainer = findViewById(R.id.CheckListContainer);
        checkListView = new CheckListView(this, checkListListener);

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

        addCheckList("بده بکنیم");
        addCheckList("سند");
        addCheckList("کینگ");
        addCheckList("ایز");
        addCheckList("گاد");
        addCheckList("لایک");
        addCheckList("اند");
        addCheckList("ایز");
        addCheckList("لاولی");
    }

    public void addCheckList(String title) {
        View view = checkListView.getCheckList(title);
        checklists.add(title);
        checkListContainer.addView(view);
    }

    public void removeFromCheckList(String title) {
        for (int i = 0; i < checklists.size(); i++) {
            View v = checkListContainer.getChildAt(i);
            Toast.makeText(this, title, Toast.LENGTH_SHORT).show();
            Log.d("TAG", checklists.toString());
            if (((String)v.getTag()).equals(title)) {
                checkListContainer.removeViewAt(i);
                checklists.remove((String) v.getTag());
                break;
            }
        }
    }

    View.OnClickListener checkListListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            removeFromCheckList((String) view.getTag());
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AddPlanActivity.this,MainActivity.class));
        finish();
    }
}