package com.example.myclock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.myclock.Helper.FormatHelper;
import com.example.myclock.Views.SpiralClock;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SpiralClock spiralClock=new SpiralClock(this);
    }
}