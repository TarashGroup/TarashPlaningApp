package com.example.myclock.Dialigs;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.myclock.R;

public class TimeDurationDialog extends MyDialog {
    private TimePicker tpDuration;
    private Context context;
    private Button btnDuration;
    private Button btnSetDuration;
    private int minute = 0 , hour=1;

    public TimeDurationDialog(LinearLayout setDurationLayout, Context context, Button btnDuration, int layout) {
        this.context = context;
        this.btnDuration = btnDuration;
        btnSetDuration = setDurationLayout.findViewById(R.id.btn_timePickerOK);
        tpDuration = setDurationLayout.findViewById(R.id.tp_duration);
        builder(context, layout,setDurationLayout);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void start() {
        tpDuration.setIs24HourView(true);
        tpDuration.setHour(hour);
        tpDuration.setMinute(minute);
        tpDuration.setOnTimeChangedListener(timeChangedListener);
        showDialog();
        btnSetDuration.setOnClickListener(setDuration);
    }

    TimePicker.OnTimeChangedListener timeChangedListener = new TimePicker.OnTimeChangedListener() {
        @RequiresApi(api = Build.VERSION_CODES.M)
        public void onTimeChanged(TimePicker timePicker, int i, int i1) {
            if (i == 11)
                timePicker.setHour(0);
            else if (i == 23)
                timePicker.setHour(10);
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.M)
    View.OnClickListener setDuration = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int m = tpDuration.getMinute();
            int h = tpDuration.getHour();
            if (m == 0 && h == 0)
                Toast.makeText(context, "تو ۰ دقیقه که نمیتونی کاری بکنی:)", Toast.LENGTH_SHORT).show();
            else {
                minute = m;
                hour = h;
                if (minute > 0 && hour > 0)
                    btnDuration.setText(hour + " ساعت " + "و" + " " + minute + " دقیقه ");
                else if (minute > 0)
                    btnDuration.setText(minute + " دقیقه ");
                else
                    btnDuration.setText(hour + " ساعت ");
                dialog.dismiss();
                MyDialog.setButtonSelected(btnDuration);
            }
        }
    };

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }
}
