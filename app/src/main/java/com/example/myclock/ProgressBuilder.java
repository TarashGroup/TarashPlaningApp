package com.example.myclock;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import at.grabner.circleprogress.CircleProgressView;
import at.grabner.circleprogress.TextMode;
import at.grabner.circleprogress.UnitPosition;


public class ProgressBuilder{

    private View.OnClickListener onClickListener;
    private Context context;

    ProgressBuilder(Context context, View.OnClickListener onClickListener) {
        this.context = context;
        this.onClickListener = onClickListener;
    }

    View getView(String pTitle, Typeface pTitleTypeface, float pValue, float pMaxValue, int TAG_ID) {
        LinearLayout progressLayout = new LinearLayout(context);
        progressLayout.setTag(pTitle);
        progressLayout.setOrientation(LinearLayout.VERTICAL);


        CircleProgressView progressView = new CircleProgressView(context, null);
        progressView.setSeekModeEnabled(false);
        progressView.setTextMode(TextMode.TEXT);
        progressView.setTextColorAuto(false);
        progressView.setTextColor(ContextCompat.getColor(context, R.color.DarkLight));
        progressView.setText(Math.round(pMaxValue * 100) / 100.0 + " Hrs");
        progressView.setOuterContourSize(0);
        progressView.setInnerContourSize((int) dp2px(1f, context));
        progressView.setBarWidth((int) dp2px(5f, context));
        progressView.setRimWidth((int) dp2px(9f, context));
        progressView.setBarColor(ContextCompat.getColor(context, R.color.BarColor));
        progressView.setRimColor(ContextCompat.getColor(context, R.color.RimColor));
        progressView.setInnerContourColor(ContextCompat.getColor(context, R.color.ContourColor));
        progressView.setValueAnimated(pValue);
        progressView.setMaxValue(pMaxValue);
        progressView.setTextTypeface(pTitleTypeface);
        progressView.setAutoTextSize(true);

        TextView title = new TextView(context);
        title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        title.setTypeface(pTitleTypeface);
        title.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);
        title.setText(pTitle);
        title.setSingleLine();
        title.setTextColor(Color.rgb(27,56,81));

        LinearLayout.LayoutParams progressBarParams = new LinearLayout.LayoutParams(
                (int) dp2px(100, context), (int) dp2px(100, context));

        LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        titleParams.gravity = Gravity.CENTER;
        titleParams.setMargins(0, (int) dp2px(8, context), 0, 0);

        progressLayout.addView(progressView, progressBarParams);
        progressLayout.addView(title, titleParams);

        progressLayout.setOnClickListener(onClickListener);
        progressLayout.setTag(TAG_ID);

        return progressLayout;
    }

    private float dp2px(float dp, Context context){
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }
}