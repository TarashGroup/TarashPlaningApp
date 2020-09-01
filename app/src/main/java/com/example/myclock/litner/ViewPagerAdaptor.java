package com.example.myclock.litner;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myclock.LitnerBoxActivity;
import com.example.myclock.R;

import java.util.ArrayList;
import java.util.List;


public class ViewPagerAdaptor extends RecyclerView.Adapter<ViewPagerAdaptor.ViewHolder> {
    ArrayList<String> flashCard_GroupNames;
    ArrayList<ArrayList<FlashCardPreView>> allCards;
    private Context context;
    private int lastUsedFlashCardPos = -1;
    private boolean needEnterAnimation = false;
    public ViewPagerAdaptor(Context context, ArrayList<String> flashCard_GroupNames ,  ArrayList<ArrayList<FlashCardPreView>> allCards){
        this.flashCard_GroupNames = flashCard_GroupNames;
        this.allCards = allCards;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.litner_view_pager, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.bind(allCards.get(position));

    }


    @Override
    public int getItemCount() {
        return flashCard_GroupNames.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        GridLayout gridLayout;
        ViewHolder(@NonNull View itemView){
            super(itemView);
            gridLayout = itemView.findViewById(R.id.gridView);
        }
        void bind(ArrayList<FlashCardPreView> cards ){
            gridLayout.removeAllViews();
            int i = 0;
            for (FlashCardPreView flashcard : cards) {
                View view = flashcard.getView();
                if (i == lastUsedFlashCardPos && needEnterAnimation ){
                    Animation animation;
                    if (i%2 == 0){
                        animation = AnimationUtils.loadAnimation( context , R.anim.slide_right_enter);
                    }
                    else{
                        animation = AnimationUtils.loadAnimation( context , R.anim.slide_left_enter);
                    }
                    view.startAnimation(animation);
                    needEnterAnimation =false;
                }
                gridLayout.addView(view);
                i++;
            }
        }


    }

    public void setLastUsedFlashCardPos(int lastUsedFlashCardPos) {
        this.lastUsedFlashCardPos = lastUsedFlashCardPos;
    }

    public void setNeedEnterAnimation(boolean needEnterAnimation) {
        this.needEnterAnimation = needEnterAnimation;
    }
}
