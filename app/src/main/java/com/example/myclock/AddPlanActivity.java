package com.example.myclock;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AddPlanActivity extends AppCompatActivity {
    int limit = 15;
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
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AddPlanActivity.this,MainActivity.class));
        finish();
    }
    public void addPlan(View view){
       showCheckListDialog();
    }

    private void showCheckListDialog(){
        final Dialog checkListDialog = new Dialog(this);
        checkListDialog.setContentView(R.layout.add_check_list);
        checkListDialog.show();
        ImageView imageView = findViewById(R.id.commit);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = findViewById(R.id.editText);
                String checkListString = editText.getText().toString();
                if (checkListString.length() > limit ){
                    Toast.makeText(AddPlanActivity.this, "توضیح بیش از حد مجاز", Toast.LENGTH_SHORT).show();
                }
                else{
                    addCheckList(checkListString);
                    checkListDialog.dismiss();    
                }
                
            }
        });
        
        imageView = findViewById(R.id.cancel);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkListDialog.dismiss();
            }
        });
        
    }
}