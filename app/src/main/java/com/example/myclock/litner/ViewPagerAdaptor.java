package com.example.myclock.litner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myclock.R;

import java.util.ArrayList;
import java.util.List;


public class ViewPagerAdaptor extends RecyclerView.Adapter<ViewPagerAdaptor.ViewHolder> {

    ArrayList<String> flashCard_GroupNames;

    public ViewPagerAdaptor(ArrayList<String> flashCard_GroupNames){
        this.flashCard_GroupNames = flashCard_GroupNames;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.fragment_all, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(flashCard_GroupNames.get(position));
    }

    @Override
    public int getItemCount() {
        return flashCard_GroupNames.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ViewHolder(@NonNull View itemView){
            super(itemView);
            textView = itemView.findViewById(R.id.ali);
        }
        void bind(String name){
            textView.setText(name);
        }
    }
}
