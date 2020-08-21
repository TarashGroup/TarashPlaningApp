package com.example.myclock;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.example.myclock.Views.SpiralClock;
import com.hanks.htextview.base.HTextView;


public class MainActivity extends AppCompatActivity {
    private int timeInSecondsUntilKonkur = 722000 , seconds , minutes , hours , days ;
    private Handler mainTimerHandler = new Handler();
    private LinearLayout dailySchedule;

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
        addProgressBars(typeface);


        Button btnAddPlan = findViewById(R.id.btn_add);
        btnAddPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,AddPlanActivity.class));
                finish();
            }
        });
    }

    private void addProgressBars (Typeface typeface) {
        ProgressBuilder progressBuilder = new ProgressBuilder(this, temp);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(dp2px(10), dp2px(10), dp2px(10), dp2px(10));

        View view = progressBuilder.getView("Sand King", typeface, 2f, 2.30f, 0);
        View view1 = progressBuilder.getView("is so", typeface, 0.80f, 2.30f, 1);
        View view2 = progressBuilder.getView("FUCKING", typeface, 0.80f, 2.30f, 2);
        View view3 = progressBuilder.getView("lovely", typeface, 0.80f, 2.30f, 3);

        dailySchedule = findViewById(R.id.DailyScheduleContainer);
        dailySchedule.addView(view, params);
        dailySchedule.addView(view1, params);
        dailySchedule.addView(view2, params);
    }

    View.OnClickListener temp = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(MainActivity.this, view.getTag() + "", Toast.LENGTH_SHORT).show();
        }
    };

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

    private int dp2px(float dp){
        return (int) (dp * ((float) this.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT));
    }
}
