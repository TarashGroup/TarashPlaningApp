package com.example.myclock;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
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

import com.example.myclock.Views.CheckListView;

import java.util.ArrayList;
import java.util.Objects;

public class AddPlanActivity extends AppCompatActivity {

    public ArrayList<String> courses = new ArrayList<>();
    private ArrayList<CheckListView> checklistsViews = new ArrayList<>();
    private LinearLayout checkListContainer;
    private Button btnAddCheckList;
    private TimePicker tpDuration;
    private AlertDialog setDurationDialog;
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
            setButtonSelected(btnChooseCourse);
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
                break;
            }
            else if (inputText.length() == 0) {
                highlight(check_box_layout);
                Toast.makeText(AddPlanActivity.this, "برای چک لیست " + number + " عنوان رو وارد کن.", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }

    // TODO Replace /n with ' ' and Trim()
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


}