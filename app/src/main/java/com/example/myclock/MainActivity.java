package com.example.myclock;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;
import com.example.myclock.Views.ProgressBuilder;
import com.hanks.htextview.base.HTextView;
import java.util.Objects;
import yuku.ambilwarna.AmbilWarnaDialog;


public class MainActivity extends AppCompatActivity {
    private int timeInSecondsUntilKonkur = 722000 , seconds , minutes , hours , days ;
    private Handler mainTimerHandler = new Handler();
    private LinearLayout dailySchedule;
    private Animation shakeAnimation;
    private boolean isRemoving = false;
    private Button deleteButton;
    private Button btnAddPlan;
    //test
    AmbilWarnaDialog dialog;
    AlertDialog alertDialog;
    //test
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String a = "F android studio & git";
        setContentView(R.layout.activity_main);

        shakeAnimation = AnimationUtils.loadAnimation(this, R.anim.shake_that);

        Typeface typeface = ResourcesCompat.getFont(this, R.font.iransans);

        //calculateMainTimer();
        //startMainTimer();     //felan in kirio stop kardam ta ap sari tar run beshe ta bad ye fekri bokonim darmordesh.
        addProgressBars();

        deleteButton = findViewById(R.id.btn_remove);
        btnAddPlan = findViewById(R.id.btn_add);
        btnAddPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,AddPlanActivity.class));
                finish();
            }
        });
    }

    private void addProgressBars() {
        ProgressBuilder progressBuilder = new ProgressBuilder(this, progressListener);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(dp2px(10), dp2px(10), dp2px(10), dp2px(10));

        View view4 = progressBuilder.getView("فیزیک", 60f, 120f);
        View view1 = progressBuilder.getView("شیمی", 30f, 90f);
        View view2 = progressBuilder.getView("ریاضی", 100f, 145f);
        View view3 = progressBuilder.getView("ادبیات", 0f, 15f);

        dailySchedule = findViewById(R.id.DailyScheduleContainer);
        dailySchedule.addView(view4, params);
        dailySchedule.addView(view1, params);
        dailySchedule.addView(view2, params);
        dailySchedule.addView(view3, params);
        deleteButton = findViewById(R.id.btn_remove);

        CardView cardView = findViewById(R.id.cardView);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        animation.setDuration(700);
        cardView.startAnimation(animation);
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

    public void removeFromDailySchedule (View view) {
        if (isRemoving) {
            deleteButton.setText("حذف");
            isRemoving = false;
            shakeProgressBars(false);
        }
        else {
            deleteButton.setText("بازگشت");
            isRemoving = true;
            shakeProgressBars(true);
        }
    }

    private void shakeProgressBars (boolean shake) {
        if (shake) {
            for (int i = 0; i < dailySchedule.getChildCount(); i++) {
                dailySchedule.getChildAt(i).startAnimation(shakeAnimation);
            }
        }
        else {
            for (int i = 0; i < dailySchedule.getChildCount(); i++) {
                dailySchedule.getChildAt(i).clearAnimation();
            }
        }
    }
//*************************************************************************************test
    public void test(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.add_course_dialogue);
        alertDialog = builder.create();
        Objects.requireNonNull(alertDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        Objects.requireNonNull(alertDialog.getWindow()).getAttributes().windowAnimations = R.style.DialogAnimation;
        alertDialog.show();
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

        ImageView imageView = alertDialog.findViewById(R.id.colorPicker);
        imageView.setColorFilter(color);
    }
//*************************************************************************************test

    View.OnClickListener progressListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (isRemoving) {
                String name = view.getTag().toString();
                for (int i = 0; i < dailySchedule.getChildCount(); i++) {
                    if (dailySchedule.getChildAt(i).getTag().toString().equals(name)) {
                        dailySchedule.getChildAt(i).clearAnimation();
                        dailySchedule.removeViewAt(i);
                        return;
                    }
                }
            }
        }
    };

    private int dp2px(float dp){
        return (int) (dp * ((float) this.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT));
    }
}
