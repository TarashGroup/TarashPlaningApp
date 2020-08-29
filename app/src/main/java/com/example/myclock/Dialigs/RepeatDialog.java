package com.example.myclock.Dialigs;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.core.widget.CompoundButtonCompat;
import com.example.myclock.R;

public class RepeatDialog extends MyDialog{
    private Context context;
    private LinearLayout repeatLayout;
    private Button btnSetRepeat;
    private Button btnConfirm;
    private Resources resources;
    private EditText etNumRepeat;
    private int numRepeat;
    private boolean allDays = false;
    private CheckBox cbAllDays;
    private boolean[] days = new boolean[7];
    private boolean[] savedDays = new boolean[7];
    private Button[] daysButton = new Button[7];
    private boolean isCompleted = false;

    public RepeatDialog(Context context, LinearLayout repeatLayout, Button btnSetRepeat, int layout,Resources resources) {
        this.context = context;
        this.repeatLayout = repeatLayout;
        this.btnSetRepeat = btnSetRepeat;
        this.resources = resources;
        builder(context,layout,repeatLayout);
        setButtons();
        for (Button button:daysButton)
            button.setOnClickListener(daysButtonOnClick);
        cbAllDays.setOnCheckedChangeListener(checkedChangeListener);
        btnConfirm.setOnClickListener(btnConfirmOnClick);
    }
    public void start(){
        showDialog();
    }

    View.OnClickListener daysButtonOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            for (int i = 0; i < 7; i++) {
                if (view.getId() == daysButton[i].getId()){
                    selectOrUnSelectButton(i);
                    break;
                }
            }
        }
    }, btnConfirmOnClick = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            int c=0;
            for (int i = 0; i < 7; i++) {
                if (days[i])c++;
            }
            if(c>0 && !etNumRepeat.getText().toString().equals("") ) {
                int t = Integer.parseInt(etNumRepeat.getText().toString());
                if (t>0) {
                    btnSetRepeat.setText(String.format(" %d روز در هفته برای %d هفته", c, numRepeat));
                    setButtonSelected(btnSetRepeat);
                    isCompleted = true;
                    numRepeat = t;
                    savedDays = days;
                    dialog.dismiss();
                }else
                    Toast.makeText(context, "حداقل یه هفته که میخوای تکرار بشه!", Toast.LENGTH_SHORT).show();
            }else if(c==0)
                Toast.makeText(context, "چند شنبه ها تکرار بشه؟", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(context, "برا چند هفته تکرار بشه؟", Toast.LENGTH_SHORT).show();
        }
    };

    CheckBox.OnCheckedChangeListener checkedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if (b) {
                for (int i = 0; i < 7; i++)
                    selectButton(i);
                allDays = true;
                setColorForCheckbox(true);
            }
            else if(allDays) {
                for (int i = 0; i < 7; i++)
                    unSelectButton(i);
                allDays = false;
                setColorForCheckbox(false);
            }
        }
    };

    private void checkAllDays(){
        allDays = true;
        for (int i = 0; i < 7; i++) {
            if(!days[i]){
                allDays = false;
                break;
            }
        }
        cbAllDays.setChecked(allDays);
        setColorForCheckbox(allDays);
    }

    private void selectOrUnSelectButton(int i){
        if(days[i]){
           unSelectButton(i);
        }else {
           selectButton(i);
        }
    }

    private void selectButton(int i){
        daysButton[i].setBackgroundResource(R.drawable.week_days_selected);
        daysButton[i].setTextColor(Color.WHITE);
        days[i] = true;
        checkAllDays();
    }

    private void unSelectButton(int i){
        daysButton[i].setBackgroundResource(R.drawable.week_days_normal);
        daysButton[i].setTextColor(resources.getColor(R.color.Gray));
        allDays = false;
        days[i] = false;
        cbAllDays.setChecked(false);
        setColorForCheckbox(false);
    }

    private void setColorForCheckbox(boolean b){
        if (b){
            cbAllDays.setTextColor(resources.getColor(R.color.Bar));
            CompoundButtonCompat.setButtonTintList(cbAllDays, ColorStateList
                    .valueOf(context.getResources().getColor(R.color.Bar)));
        }
        else{
            cbAllDays.setTextColor(resources.getColor(R.color.Gray));
            CompoundButtonCompat.setButtonTintList(cbAllDays, ColorStateList
                    .valueOf(context.getResources().getColor(R.color.Gray)));
        }

    }

    private void setButtons(){
        daysButton[0] = repeatLayout.findViewById(R.id.iv_sat);
        daysButton[1] = repeatLayout.findViewById(R.id.iv_sun);
        daysButton[2] = repeatLayout.findViewById(R.id.iv_mon);
        daysButton[3] = repeatLayout.findViewById(R.id.iv_tue);
        daysButton[4] = repeatLayout.findViewById(R.id.iv_wen);
        daysButton[5] = repeatLayout.findViewById(R.id.iv_the);
        daysButton[6] = repeatLayout.findViewById(R.id.iv_fri);
        cbAllDays = repeatLayout.findViewById(R.id.cb_all_days);
        etNumRepeat = repeatLayout.findViewById(R.id.et_numRepeat);
        btnConfirm = repeatLayout.findViewById(R.id.btn_repeat_confirm);
    }

    public boolean[] getDays() {
        return savedDays;
    }

    public int getNumRepeat() {
        return numRepeat;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void removeRepeat(){
        isCompleted = false;
    }
}
