package com.example.myclock;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.example.myclock.Database.GetDay;
import com.example.myclock.Database.Plan;
import com.example.myclock.Database.PropertyHolder;
import com.example.myclock.Views.CheckListView;

import java.util.ArrayList;
import java.util.Objects;

public class AddPlanActivity extends AppCompatActivity {

    public ArrayList<String> courses = new ArrayList<>();
    private ArrayList<CheckListView> checklistsViews = new ArrayList<>();
    private LinearLayout checkListContainer;
    private Button btnAddCheckList;

    AlertDialog chooseCourseDialog;
    int limit = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plan);
        checkListContainer = findViewById(R.id.CheckListContainer);
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
//        TimePicker picker=(TimePicker)findViewById(R.id.timePicker1);
       // picker.setIs24HourView(true);
        //*********************************************test
        courses.add("ریاضی");
        courses.add("شیمی");
        courses.add("عربی");
        courses.add("زبان");
        courses.add("فیزیک");
        //*********************************************/test
        btnAddCheckList = findViewById(R.id.btn_check_list);
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
            btnChooseCourse.setBackgroundResource(R.drawable.button3);
            chooseCourseDialog.dismiss();
        }
    };

    public void highlight (LinearLayout linearLayout) {
        linearLayout.setBackgroundResource(R.drawable.highlighted_textbox_background);
    }


//*****************************************************************************  Checklist Listener

        View.OnClickListener checkListListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < checkListContainer.getChildCount(); i++) {
                    if (view.getTag().equals(checkListContainer.getChildAt(i))) {
                        checkListContainer.removeViewAt(i);
                        checklistsViews.remove(i);
                        break;
                    }
                }
                if(checkListContainer.getChildCount() == 0)btnAddCheckList.setText("");
            }
        };

//***************************************************************************** Add Button
    public void addCheckList(View view) {
        checklistsViews.add(0 , new CheckListView(this , checkListListener));
        View view1 = checklistsViews.get(0).getCheckList("");
        checkListContainer.addView(view1 , 0);
        btnAddCheckList.setText(R.string.checkList);
    }

    public void commit(View view){
        for ( CheckListView checkListView : checklistsViews) {
            LinearLayout check_box_layout = checkListView.getLinearLayout();
            check_box_layout.setBackgroundResource(R.drawable.textbox_background);
        }
        for ( CheckListView checkListView : checklistsViews){
            LinearLayout check_box_layout = checkListView.getLinearLayout();
            String inputText = checkListView.getEditText().getText().toString();
            String number = Integer.toString(checklistsViews.indexOf(checkListView) + 1);
            if ( inputText.length() > limit){
                highlight(check_box_layout);
                Toast.makeText(this, "برای چک لیست " + number + " عنوان کوتاهتر وارد کن.", Toast.LENGTH_SHORT).show();
                return;
            }
            else if (inputText.length() == 0) {
                highlight(check_box_layout);
                Toast.makeText(AddPlanActivity.this, "برای چک لیست " + number + " عنوان رو وارد کن.", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        PropertyHolder.addToDaily(GetDay.getDay(getStartingTime()),
                new Plan(PropertyHolder.getCourseByName(getCoursesName()), getTotalTime(),
                        getRepeating(), getNotification(), getCheckLists()));
    }
    private String getCoursesName () {
        return null;
    }
    private boolean getNotification () {
        return false;
    }
    private ArrayList<String> getCheckLists () {
        ArrayList<String> temp = new ArrayList<>();
        for (CheckListView ch : checklistsViews) {
            temp.add(ch.getEditText().toString().replace("\n", " ").trim());
        }
        return temp;
    }
    private ArrayList<Long> getRepeating () {
        return null;
    }
    private double getTotalTime () {
        return 0.0;
    }
    private Long getStartingTime () {
        return 0L;
    }

    // TODO Replace /n with ' ' and Trim()
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AddPlanActivity.this,MainActivity.class));
        finish();
    }

    public void setDuration(View view){

    }
}