package com.example.myclock;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.myclock.Views.CheckListContainerAdapter;

import yuku.ambilwarna.AmbilWarnaDialog;

public class AddCourseActivity extends AppCompatActivity {
    AmbilWarnaDialog dialog;
    CheckListContainerAdapter lessonsContainerAdapter , notesContainerAdapter;
    int limitNoteName = 20 , limitLessonsName = 20;
    int limitForNoteView = 15 , limitForLessonView = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        //****************************************toolbar
        androidx.appcompat.widget.Toolbar tbAddPlan = findViewById(R.id.tb_addCourse_activity);
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

        //***************************************************************** lesson
        LinearLayout lessonsContainer = findViewById(R.id.lessonsContainer);
        Button btnAddLesson = findViewById(R.id.btnAddLesson);
        lessonsContainerAdapter = new CheckListContainerAdapter(this , lessonsContainer , btnAddLesson );
        //***************************************************************** notes
        LinearLayout notesContainer = findViewById(R.id.linkedNotesContainer);
        Button btnAddNote = findViewById(R.id.btnAddLinkedNotes);
        notesContainerAdapter = new CheckListContainerAdapter(this , notesContainer , btnAddNote );

    }

    public void openColorPicker(View view){
        dialog = new AmbilWarnaDialog(this, Color.WHITE, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                // color is the color selected by the user.
                changeColor(color);
            }

            @Override
            public void onCancel(AmbilWarnaDialog dialog) {
                // cancel was selected by the user
            }
            //https://github.com/yukuku/ambilwarna
        });
        dialog.show();
    }
    private void changeColor(int color){
        ImageView imageView = findViewById(R.id.colorPicker);
        imageView.setColorFilter(color);
    }
    public void addNote(View view){
        notesContainerAdapter.addToContainer (R.string.addLinkedNote , limitForNoteView);
    }
    public void addLesson(View view){

        lessonsContainerAdapter.addToContainer (R.string.addLesson , limitForLessonView);
    }
    public void commit(View view){
        if(!notesContainerAdapter.check(limitNoteName))
            return;
        if(!lessonsContainerAdapter.check(limitLessonsName))
            return;
    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(AddCourseActivity.this , MainActivity.class));
    }
}