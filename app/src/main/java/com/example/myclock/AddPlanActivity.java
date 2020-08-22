package com.example.myclock;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.example.myclock.Views.CheckListView;

import java.util.ArrayList;
import java.util.Objects;

public class AddPlanActivity extends AppCompatActivity {

    public ArrayList<String> courses = new ArrayList<>();
    private ArrayList<String> checklists = new ArrayList<>();
    private LinearLayout checkListContainer;
    private CheckListView checkListView;
    int limit = 20;
    AlertDialog.Builder checkListDialogBuilder ;
    AlertDialog checkListDialog;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plan);
        checkListContainer = findViewById(R.id.CheckListContainer);
        checkListView = new CheckListView(this, checkListListener);



        TimePicker picker=(TimePicker)findViewById(R.id.timePicker1);
        picker.setIs24HourView(true);


        Button btnAddCourse = (Button)findViewById(R.id.btn_choose_course);
        final Dialog chooseCourseDialog = new Dialog(this);
        btnAddCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseCourseDialog.setContentView(R.layout.choose_course_dialog);
                setCoursesButtons();
                chooseCourseDialog.show();
            }
        });
    }
    private void setCoursesButtons(){
        courses.add("ریاضی");
        courses.add("شیمی");
        courses.add("عربی");
        courses.add("زبان");
        courses.add("فیزیک");
        LinearLayout llCourses = (LinearLayout)findViewById(R.id.ll_courses);
        ArrayList<Button> coursesButtons = new ArrayList<>();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(20, 10, 20, 10);
        for (int i = 0; i < courses.size(); i++) {
            Button button = new Button(this);
            button.setText(courses.get(i));
            button.setGravity(Gravity.CENTER);
            button.setTypeface(ResourcesCompat.getFont(this, R.font.iransans));
            button.setBackgroundResource(R.drawable.button3);
            button.setHeight((int) getResources().getDimension(R.dimen.button_height));
            button.setWidth(llCourses.getWidth());
            button.setLayoutParams(params);
            coursesButtons.add(button);
            llCourses.addView(button);
        }
    }

    public void addCheckList(String title) {
        View view = checkListView.getCheckList(title);
        checklists.add(title);
        checkListContainer.addView(view);
    }

    public void removeFromCheckList(String title) {
        for (int i = 0; i < checklists.size(); i++) {
            View child = checkListContainer.getChildAt(i);
            if ((child.getTag()).equals(title)) {
                checkListContainer.removeViewAt(i);
                checklists.remove((String) child.getTag());
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
    public void addPlan(View view) {

        checkListDialogBuilder = new AlertDialog.Builder(this);
        checkListDialogBuilder.setView(getLayoutInflater().inflate(R.layout.add_check_list_dialogue,null));
        checkListDialog = checkListDialogBuilder.create();
        checkListDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        Objects.requireNonNull(checkListDialog.getWindow()).getAttributes().windowAnimations = R.style.DialogAnimation;
        checkListDialog.show();

    }
    public void cancel(View view) {
        checkListDialog.dismiss();
    }
    public void commit(View view) {

        EditText editText = checkListDialog.findViewById(R.id.editText);
        String checkListString = editText.getText().toString();
        if (checkListString.length() > limit ){
            Toast.makeText(AddPlanActivity.this, "عنوان کوتاه تری وارد کن.", Toast.LENGTH_SHORT).show();
        }
        else if (checkListString.equals("") )
            Toast.makeText(AddPlanActivity.this, "عنوان رو وارد کن.", Toast.LENGTH_SHORT).show();
        else{
            addCheckList(checkListString);
            checkListDialog.dismiss();
        }
    }
}