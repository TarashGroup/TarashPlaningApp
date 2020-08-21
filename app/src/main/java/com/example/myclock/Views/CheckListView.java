package com.example.myclock.Views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
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

    public CheckListView(Context context, View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        this.context = context;
    }

    public View getCheckList (String title) {
        Typeface typeface = ResourcesCompat.getFont(context, R.font.iransans);


        LinearLayout layout = new LinearLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(dp2px(8), dp2px(8), dp2px(12), 0);
        params.gravity = Gravity.CENTER;
        layout.setLayoutParams(params);
        layout.setOrientation(LinearLayout.HORIZONTAL);

        TextView text = new TextView(context);
        text.setText(title);
        text.setTextColor(ContextCompat.getColor(context, R.color.DarkLight));
        text.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
        text.setTypeface(typeface);

        LinearLayout.LayoutParams text_Params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        text_Params.gravity = Gravity.CENTER_VERTICAL | Gravity.LEFT;

        ImageView image = new ImageView(context);
        image.setImageResource(R.drawable.group_376);

        LinearLayout.LayoutParams image_Params = new LinearLayout.LayoutParams(
                dp2px(35), dp2px(35));
        image_Params.gravity = Gravity.CENTER_VERTICAL;

        image.setOnClickListener(onClickListener);
        layout.addView(image, image_Params);
        layout.addView(text, text_Params);

        image.setTag(title);
        layout.setTag(title);

        return layout;
    }

    private int dp2px(float dp){
        return (int) (dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT));
    }
}
