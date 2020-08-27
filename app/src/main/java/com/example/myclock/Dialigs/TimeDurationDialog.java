package com.example.myclock.Dialigs;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.myclock.R;

import java.util.Objects;

public class TimeDurationDialog {
    private TimePicker tpDuration;
    private AlertDialog setDurationDialog;
    private LinearLayout setDurationLayout;
    private Context context;
    private Button btnDuration;
    private Button btnSetDuration;
    @RequiresApi(api = Build.VERSION_CODES.M)
    public TimeDurationDialog(TimePicker tpDuration, AlertDialog setDurationDialog, LinearLayout setDurationLayout, Context context, Button btnDuration) {
        this.tpDuration = tpDuration;
        this.setDurationDialog = setDurationDialog;
        this.setDurationLayout = setDurationLayout;
        this.context = context;
        this.btnDuration = btnDuration;
        btnSetDuration = setDurationDialog.findViewById(R.id.btn_timePickerOK);
        start();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void start(){
        tpDuration = setDurationLayout.findViewById(R.id.tp_duration);
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
        int minute = tpDuration.getMinute();
        int hour = tpDuration.getHour();
        if (minute == 0 && hour == 0)
            Toast.makeText(context,"تو ۰ دقیقه که نمیتونی کاری بکنی:)" , Toast.LENGTH_SHORT).show();
        else {
            if (minute > 0 && hour > 0)
                btnDuration.setText(hour + " ساعت " +  "و" +" "+ minute + " دقیقه ");
            else if (minute > 0)
                btnDuration.setText(minute + " دقیقه ");
            else
                btnDuration.setText(hour + " ساعت ");
            setDurationDialog.dismiss();
            MyDialog.setButtonSelected(btnDuration);
        }
    }
}
