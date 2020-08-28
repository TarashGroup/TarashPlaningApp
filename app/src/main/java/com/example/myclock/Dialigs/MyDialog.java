package com.example.myclock.Dialigs;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.myclock.R;

import java.util.Objects;

public class MyDialog {
    AlertDialog dialog;
    void builder(Context context , int layout,LinearLayout linearLayout){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(layout);
        dialog = builder.create();
        dialog.setView(linearLayout);
    }

    public void showDialog(){
        Objects.requireNonNull(dialog.getWindow()).getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.show();
    }
    public static void setButtonSelected(Button button){
        button.setBackgroundResource(R.drawable.button3);
    }
    //todo: amir ino to remove kardan check lista seda bezan yani vaghti ke size array listesh 0 shod
    public static void setButtonUnSelected(Button button){
        button.setBackgroundResource(R.drawable.button4);
        button.setText("");
    }


}
