package com.example.myclock.litner;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.drawable.DrawableCompat;
import com.example.myclock.Database.Note;
import com.example.myclock.R;

public class FlashCardPreView {
    private Context context;
    private Note note;
    public FlashCardPreView(Context context , Note note) {
        this.context = context;
        this.note = note;
    }

    public View getView(){

        //************************************************** Main View
        CardView flashCard = new CardView(context);
        flashCard.setElevation(dp2px(8));

        Drawable unwrappedDrawable = AppCompatResources.getDrawable(context, R.drawable.colored_flash_card_background);
        Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
        DrawableCompat.setTint(wrappedDrawable, Color.RED);

        flashCard.setBackgroundResource(R.drawable.flash_card);
        GridLayout.LayoutParams layoutParams= new GridLayout.LayoutParams(GridLayout.spec(
                GridLayout.UNDEFINED,GridLayout.FILL),
                GridLayout.spec(GridLayout.UNDEFINED,GridLayout.FILL, 1f));
        layoutParams.height = dp2px(125);
        layoutParams.width = 0;
        layoutParams.setMargins(dp2px(10),dp2px(10),dp2px(10),dp2px(10));
        flashCard.setLayoutParams(layoutParams);
        //******************************************************************* In Card
        LinearLayout inCard = new LinearLayout(context);
        inCard.setOrientation(LinearLayout.VERTICAL);
        //************************************************** Details

        LinearLayout details = new LinearLayout(context);
        details.setOrientation(LinearLayout.HORIZONTAL);
        details.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams detailsParams = new LinearLayout.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT,  dp2px(40) );
        details.setLayoutParams(detailsParams);

        LinearLayout.LayoutParams iconParams = new LinearLayout.LayoutParams( ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,  1 );
        iconParams.setMargins(dp2px(11), dp2px(3), dp2px(11), dp2px(3));


        int total = note.getTotalSeen();
        int correct = note.getCorrect();

        TextView allCount = new TextView(context);
        allCount.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
        allCount.setText(Integer.toString(total));
        allCount.setLayoutParams(iconParams);
        details.addView(allCount);

        TextView correctCount = new TextView(context);
        correctCount.setTextColor(context.getResources().getColor(R.color.GreenLight));
        correctCount.setText(Integer.toString(correct));
        correctCount.setLayoutParams(iconParams);
        details.addView(correctCount);

        TextView wrongCount = new TextView(context);
        wrongCount.setTextColor(context.getResources().getColor(R.color.RedLight));
        wrongCount.setText(Integer.toString(total-correct));
        wrongCount.setLayoutParams(iconParams);
        details.addView(wrongCount);

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
        like.setLayoutParams(iconParams);
        details.addView(like);
        //************************************************** Title
        TextView title = new TextView(context);
        title.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
        title.setText(note.getTitle());
        title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT ,
                ViewGroup.LayoutParams.WRAP_CONTENT , 1);
        titleParams.gravity = Gravity.CENTER;
        titleParams.setMargins(dp2px(10), dp2px(10), dp2px(20), 0);



        inCard.addView(title , titleParams);
        inCard.addView(details);
        flashCard.addView(inCard);

        return flashCard;
    }
    private int dp2px(float dp){
        return (int) (dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT));
    }
}
