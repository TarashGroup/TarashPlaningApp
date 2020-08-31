package com.example.myclock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridLayout;

import com.example.myclock.Database.Note;
import com.example.myclock.litner.FlashCardPreView;

public class Test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Note note = new Note("نویسنده بی نوایان که بود؟" , "javab ke mohem ni" , null);
        note.setCorrect(13);
        note.setTotalSeen(20);
        FlashCardPreView flashCardPreView = new FlashCardPreView(this , note);
        GridLayout gridLayout = findViewById(R.id.gridView);

        gridLayout.addView(flashCardPreView.getView());
        gridLayout.addView(flashCardPreView.getView());

    }
}