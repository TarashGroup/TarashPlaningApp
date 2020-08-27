package com.example.myclock.Dialigs;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.core.content.res.ResourcesCompat;

import com.example.myclock.R;

import java.util.ArrayList;

public class ChooseCourseDialog extends MyDialog{
    private ArrayList<String> courses;
    private Button btnChooseCourse;
    private int colorBar , buttonHeight;
    private String course;
    private Context context;
    private LinearLayout chooseCourseLayout;

    public ChooseCourseDialog(ArrayList<String> courses, Button btnChooseCourse, int colorBar, int buttonHeight, Context context, LinearLayout chooseCourseLayout, int layout) {
        this.courses = courses;
        this.btnChooseCourse = btnChooseCourse;
        this.colorBar = colorBar;
        this.buttonHeight = buttonHeight;
        this.context = context;
        this.chooseCourseLayout = chooseCourseLayout;
        builder(context, layout, chooseCourseLayout);
    }

    public void start(){
        LinearLayout llCourses  = chooseCourseLayout.findViewById(R.id.ll_courses);
        setCoursesButtons(llCourses);
        showDialog();
    }
    private void setCoursesButtons(LinearLayout llCourses ){
        llCourses.removeAllViews();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(40, 20, 40, 20);
        params.gravity = Gravity.CENTER;
        for (int i = 0; i < courses.size(); i++) {
            Button button = new Button(context);
            button.setText(courses.get(i));
            button.setGravity(Gravity.CENTER);
            Typeface font = ResourcesCompat.getFont(context, R.font.vazir);
            button.setTypeface(font);
            button.setBackgroundResource(R.drawable.button1);
            button.setHeight(buttonHeight);
            button.setLayoutParams(params);
            button.setTextColor(colorBar);
            llCourses.addView(button);
            button.setOnClickListener(courseButtonOnClick);
        }
    }

    View.OnClickListener courseButtonOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button btnSelectedCourse = (Button)view;
            btnChooseCourse.setText(btnSelectedCourse.getText());
            course = btnSelectedCourse.getText().toString();
            setButtonSelected(btnChooseCourse);
            dialog.dismiss();
        }
    };

    public String getCourse() {
        return course;
    }
}
