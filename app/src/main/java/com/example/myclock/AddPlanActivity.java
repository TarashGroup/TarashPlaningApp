package com.example.myclock;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import com.example.myclock.Views.CheckListView;
import java.util.ArrayList;
import java.util.Objects;

public class AddPlanActivity extends AppCompatActivity {

    public ArrayList<String> courses = new ArrayList<>();
    private ArrayList<CheckListView> checklistsViews = new ArrayList<>();
    private LinearLayout checkListContainer;
    AlertDialog chooseCourseDialog;
    int limit = 20;
    AlertDialog.Builder checkListDialogBuilder ;
    AlertDialog checkListDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plan);


        //**********************************************************************First Checklist
        checkListContainer = findViewById(R.id.CheckListContainer);
        checklistsViews.add( new CheckListView(this, checkListListener));
        View view = checklistsViews.get(0).getCheckList("");
        checkListContainer.addView(view , 0);




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





        TimePicker picker=(TimePicker)findViewById(R.id.timePicker1);
        picker.setIs24HourView(true);
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
        ArrayList<Button> coursesButtons = new ArrayList<>();
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
            coursesButtons.add(button);
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
            chooseCourseDialog.dismiss();
        }
    };



            View.OnClickListener checkListListener = new View.OnClickListener() {
@Override
public void onClick(View view) {
    if (checkListContainer.getChildCount() != 2) {
        for (int i = 0; i < checkListContainer.getChildCount(); i++) {
            if (view.getTag().equals(checkListContainer.getChildAt(i))) {
                checkListContainer.removeViewAt(i);
                break;
            }
        }
    }
}
        };


public void addPlan(View view) {
        int lastIndex = checklistsViews.size() - 1;
        String inEditText = checklistsViews.get(lastIndex).editText.getText().toString();
        if (inEditText.length() > limit )
            Toast.makeText(AddPlanActivity.this, "عنوان کوتاه تری وارد کن.", Toast.LENGTH_SHORT).show();
        else if (inEditText.length() == 0)
            Toast.makeText(AddPlanActivity.this, "عنوان رو وارد کن.", Toast.LENGTH_SHORT).show();
        else{
            checklistsViews.add(new CheckListView(this , checkListListener) );
            lastIndex = checklistsViews.size() - 1;
            int lastChild = checkListContainer.getChildCount() - 1;
            View view1 = checklistsViews.get(lastIndex).getCheckList("");
            checkListContainer.addView(view1 , lastChild);
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AddPlanActivity.this,MainActivity.class));
        finish();
    }
}