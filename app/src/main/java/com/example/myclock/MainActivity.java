package com.example.myclock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;

import com.example.myclock.Helper.FormatHelper;
import com.example.myclock.Views.SpiralClock;

import ir.samanjafari.easycountdowntimer.EasyCountDownTextview;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String a = "F android studio & git";
        setContentView(R.layout.activity_main);
        //Another Easter Egg
        SpiralClock spiralClock = new SpiralClock(this);
        EasyCountDownTextview countDownTextview = (EasyCountDownTextview) findViewById(R.id.easyCountDownTextview);
        Typeface typeface = ResourcesCompat.getFont(this, R.font.iransans);
        countDownTextview.setTypeFace(typeface);
        //Easter Egg :/
    }
}
