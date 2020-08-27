package com.example.myclock.Views;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import com.example.myclock.R;

public class CheckListView {
    private Context context;
    private View.OnClickListener onClickListener;
    private EditText editText;
    private LinearLayout layout;

    public CheckListView(Context context, View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        this.context = context;
    }

    public LinearLayout getLinearLayout () {
        return layout;
    }

    public EditText getEditText() {
        return editText;
    }

    public View getCheckList (String title) {
        Typeface typeface = ResourcesCompat.getFont(context, R.font.iransans);
        layout = new LinearLayout(context);

        //************************************************* main Linear
        layout.setBackgroundResource(R.drawable.textbox_background);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        params.setMargins(0, dp2px(8), 0, 0);
        params.gravity = Gravity.CENTER;
        layout.setLayoutParams(params);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        //************************************************* set Text
        editText = new EditText(context);
        editText.setText(title);
        editText.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        editText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        editText.setBackgroundTintMode(PorterDuff.Mode.CLEAR);
        editText.setSingleLine(true);
        editText.setTypeface(typeface);
        LinearLayout.LayoutParams text_Params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        text_Params.setMargins(0, 0, dp2px(12), 0);
        text_Params.gravity = Gravity.CENTER_VERTICAL  ;

        //*************************************************set remove icon


        TextView removeButton = new TextView(context);
        removeButton.setBackgroundColor(0);
        removeButton.setText("\u00D7");
        removeButton.setTextColor(ContextCompat.getColor(context, R.color.BGMain));
        removeButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        removeButton.setTypeface(null , Typeface.BOLD);
        removeButton.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        int removeIconSize = (int) context.getResources().getDimension(R.dimen.removeIconSize);
        LinearLayout.LayoutParams paramsForRemoveButton = new LinearLayout.LayoutParams(
                removeIconSize, removeIconSize);
        paramsForRemoveButton.setMargins(0 , dp2px(9) , 0 , 0);

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
