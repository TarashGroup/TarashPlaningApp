package com.example.myclock.Dialigs;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.myclock.R;

import java.util.Objects;

public class MyDialog {
    static AlertDialog dialog;
    static void builder(Context context , int layout){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(layout);
        dialog = builder.create();
    }

    public static void showDialog(){
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        Objects.requireNonNull(dialog.getWindow()).getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.show();
    }
    public static void setButtonSelected(Button button){
        button.setBackgroundResource(R.drawable.button3);
    }
}
