package com.example.myclock.Views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import com.example.myclock.R;

public class CheckListView {
    private Context context;
    private Typeface typeface;
    private View.OnClickListener onClickListener;
    public EditText editText;

    public CheckListView(Context context, View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        this.context = context;
    }

    public View getCheckList (String title) {
        Typeface typeface = ResourcesCompat.getFont(context, R.font.iransans);

        //************************************************* main Linear
        LinearLayout layout = new LinearLayout(context);
        layout.setBackgroundResource(R.drawable.button1);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(dp2px(10), dp2px(8), dp2px(14), 0);
        params.gravity = Gravity.CENTER;
        layout.setLayoutParams(params);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        //************************************************* set Text
        editText = new EditText(context);
        editText.setText(title);
        editText.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        editText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        editText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        editText.setBackgroundTintMode(PorterDuff.Mode.CLEAR);
        editText.setTypeface(typeface);
        LinearLayout.LayoutParams text_Params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        text_Params.setMargins(0, 0, dp2px(5), 0);
        text_Params.gravity = Gravity.CENTER_VERTICAL ;
        //*************************************************set remove icon

        Button removeButton = new Button(context);

        removeButton.setBackgroundColor(0);
        removeButton.setText("✘\t");
        removeButton.setTextColor(ContextCompat.getColor(context, R.color.BGMain));
        removeButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);

        LinearLayout.LayoutParams paramsForRemoveButton = new LinearLayout.LayoutParams(
                dp2px(30), ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsForRemoveButton.setMargins(dp2px(10), 0, 0, dp2px(4));
        paramsForRemoveButton.gravity = Gravity.CENTER_VERTICAL;
        removeButton.setOnClickListener(onClickListener);
        layout.addView(removeButton, paramsForRemoveButton);
        layout.addView(editText, text_Params);
        layout.setTag(title);
        removeButton.setTag(layout);

        return layout;
    }

    private int dp2px(float dp){
        return (int) (dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT));
    }
}
