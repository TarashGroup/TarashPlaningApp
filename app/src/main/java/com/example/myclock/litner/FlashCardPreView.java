package com.example.myclock.litner;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.drawable.DrawableCompat;
import com.example.myclock.Database.Note;
import com.example.myclock.Dialigs.MyDialog;
import com.example.myclock.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class FlashCardPreView {
    private Context context;
    private Note note;

    public FlashCardPreView(Context context , Note note) {
        this.context = context;
        this.note = note;

    }

    public View getView(){

        //************************************************** Main View
        final CardView flashCard = new CardView(context);
        flashCard.setElevation(dp2px(8));


        //************************************************************************************color change
        Drawable unwrappedDrawable = AppCompatResources.getDrawable(context, R.drawable.colored_flash_card_background);
        Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
        DrawableCompat.setTint(wrappedDrawable, Color.RED);
        flashCard.setBackgroundResource(R.drawable.flash_card);


       GridLayout.LayoutParams layoutParams= new GridLayout.LayoutParams();
       layoutParams.height = getScreenWidth()*35/100;
       layoutParams.width = getScreenWidth()/2;
       layoutParams.setMargins(dp2px(10),dp2px(10),dp2px(10),dp2px(10));
       flashCard.setLayoutParams(layoutParams);
        //******************************************************************* In Card
        LinearLayout inCard = new LinearLayout(context);
        inCard.setOrientation(LinearLayout.VERTICAL);
        //************************************************** Details

        LinearLayout details = new LinearLayout(context);
        details.setOrientation(LinearLayout.HORIZONTAL);
        details.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams detailsParams = new LinearLayout.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT,  getScreenWidth()/10 );
        details.setLayoutParams(detailsParams);


        LinearLayout seenDetails = new LinearLayout(context);
        seenDetails.setOrientation(LinearLayout.HORIZONTAL);
        seenDetails.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams seenDetailsParams = new LinearLayout.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT ,1);
        seenDetails.setLayoutParams(seenDetailsParams);

        LinearLayout.LayoutParams inSeenDetails = new LinearLayout.LayoutParams( ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT );
        inSeenDetails.gravity = Gravity.CENTER;
        inSeenDetails.setMargins(dp2px(5), dp2px(3), dp2px(5), dp2px(5));

        int total = note.getTotalSeen();
        int correct = note.getCorrect();
        int fontSize = getScreenWidth()/45;
        TextView allCount = new TextView(context);
        allCount.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
        allCount.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);
        allCount.setText(Integer.toString(total));
        allCount.setLayoutParams(inSeenDetails);
        seenDetails.addView(allCount);

        TextView correctCount = new TextView(context);
        correctCount.setTextColor(context.getResources().getColor(R.color.GreenLight));
        correctCount.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);
        correctCount.setText(Integer.toString(correct));
        correctCount.setLayoutParams(inSeenDetails);
        seenDetails.addView(correctCount);

        TextView wrongCount = new TextView(context);
        wrongCount.setTextColor(context.getResources().getColor(R.color.RedLight));
        wrongCount.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);
        wrongCount.setText(Integer.toString(total-correct));
        wrongCount.setLayoutParams(inSeenDetails);
        seenDetails.addView(wrongCount);

        details.addView(seenDetails);

        ImageView like = new ImageView(context);
        if (note.isFavorite())
            like.setBackgroundResource(R.drawable.heart_full);
        else
            like.setBackgroundResource(R.drawable.heart);

        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView imageView = (ImageView) view;
                if (note.isFavorite()){
                    note.setFavorite(false);
                    imageView.setBackgroundResource(R.drawable.heart);
                }
                else{
                    note.setFavorite(true);
                    imageView.setBackgroundResource(R.drawable.heart_full);
                }
            }
        });
        LinearLayout.LayoutParams likeParams = new LinearLayout.LayoutParams( getScreenWidth()/15,
               getScreenWidth()/15 );
        likeParams.setMargins(0, dp2px(3), dp2px(20), dp2px(5));
        like.setLayoutParams(likeParams);
        details.addView(like);
        //************************************************** Title


        TextView title = new TextView(context);
        LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT ,
                ViewGroup.LayoutParams.WRAP_CONTENT , 1);
        titleParams.gravity = Gravity.CENTER;
        titleParams.setMargins(dp2px(17), dp2px(10), dp2px(20), dp2px(5));
        title.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
        title.setText(note.getTitle());
        title.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        title.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize * 3/4);
        title.setMaxLines(4);
        title.setLayoutParams(titleParams);


        inCard.addView(title);
        inCard.addView(details);
        flashCard.addView(inCard);

        return flashCard;
    }
    private int dp2px(float dp){
        return (int) (dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels - dp2px(60);
    }
}
