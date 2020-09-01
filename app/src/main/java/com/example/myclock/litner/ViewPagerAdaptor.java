package com.example.myclock.litner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myclock.R;

import java.util.ArrayList;
import java.util.List;


public class ViewPagerAdaptor extends RecyclerView.Adapter<ViewPagerAdaptor.ViewHolder> {

    ArrayList<String> flashCard_GroupNames;
    ArrayList<ArrayList<FlashCardPreView>> allCards;
    public ViewPagerAdaptor(ArrayList<String> flashCard_GroupNames ,  ArrayList<ArrayList<FlashCardPreView>> allCards){
        this.flashCard_GroupNames = flashCard_GroupNames;
        this.allCards = allCards;
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
            for (FlashCardPreView flashcard : cards) {
                if (flashcard.getView().getParent() != null)
                    ((ViewGroup) flashcard.getView().getParent()).removeView(flashcard.getView());
                gridLayout.addView(flashcard.getView());
            }
        }
    }

}
