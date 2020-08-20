package com.example.myclock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.myclock.Helper.FormatHelper;
import com.example.myclock.Views.SpiralClock;
import com.hanks.htextview.base.HTextView;


public class MainActivity extends AppCompatActivity {
    private int timeInSecondsUntilKonkur = 722000 , seconds , minutes , hours , days ;
    private Handler mainTimerHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String a = "F android studio & git";
        setContentView(R.layout.activity_main);
        //Another Easter Egg
        SpiralClock spiralClock = new SpiralClock(this);
        Typeface typeface = ResourcesCompat.getFont(this, R.font.iransans);

        calculateMainTimer();
        startMainTimer();

        //Easter Egg :/
        //who is this Kosnamak?
    }
    private void calculateMainTimer(){
        int tempTime = timeInSecondsUntilKonkur ;

        days = tempTime / (60 * 60 * 24);
        tempTime %= (60 * 60 * 24);

        hours = tempTime / (60 * 60);
        tempTime %= (60 * 60 );

        minutes = tempTime / (60);
        seconds = tempTime % (60);

    }
    private void startMainTimer(){

        mainTimerHandler.post(new Runnable() {
            @Override
            public void run() {
                if (timeInSecondsUntilKonkur != 0)
                    mainTimerHandler.postDelayed(this , 1000);

                // seconds
                HTextView TextView = findViewById(R.id.seconds);

                if (seconds < 10)
                    TextView.animateText("0" + Integer.toString(seconds));
                else
                    TextView.animateText(Integer.toString(seconds));

                // minutes
                TextView = findViewById(R.id.minutes);
                if (minutes < 10)
                    TextView.animateText("0" + Integer.toString(minutes));
                else
                    TextView.animateText(Integer.toString(minutes));

                // hours
                TextView = findViewById(R.id.hours);
                if (hours < 10)
                    TextView.animateText("0" + Integer.toString(hours));
                else
                    TextView.animateText(Integer.toString(hours));

                // days
                TextView = findViewById(R.id.days);
                if (days < 10)
                    TextView.animateText("00" + Integer.toString(days));
                else if (days < 100)
                    TextView.animateText("0" + Integer.toString(days));
                else
                    TextView.animateText(Integer.toString(days));

                timeInSecondsUntilKonkur--;
                calculateMainTimer();

            }
        } );

    }
}
