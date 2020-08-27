package com.example.myclock.Views;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.myclock.Database.Course;
import com.example.myclock.Database.Plan;
import com.example.myclock.R;

import java.util.ArrayList;

public class CheckListContainerAdapter {
    private LinearLayout container;
    private View.OnClickListener checkListListener;
    private ArrayList<CheckListView> checklistsViews = new ArrayList<>();
    private Context context;
    Button mainButton;

    public CheckListContainerAdapter(Context context, final LinearLayout container , final Button mainButton){

        this.context = context;
        this.container = container;
        this.mainButton = mainButton;
        //**************************************************** listener
        checkListListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < container.getChildCount(); i++) {
                    if (view.getTag().equals(container.getChildAt(i))) {
                        container.removeViewAt(i);
                        checklistsViews.remove(i);
                        break;
                    }
                }
                if(container.getChildCount() == 0)mainButton.setText("");
            }
        };


    }

    public void addToContainer (int s){
        checklistsViews.add(0 , new CheckListView(context , checkListListener));
        View view1 = checklistsViews.get(0).getCheckList("");
        container.addView(view1 , 0);
        mainButton.setText(s);
    }

    public Boolean check(int limit){
        //************************************************** reset Color
        for ( CheckListView checkListView : checklistsViews) {
            LinearLayout check_box_layout = checkListView.getLinearLayout();
            check_box_layout.setBackgroundResource(R.drawable.textbox_background);
        }
        for ( CheckListView checkListView : checklistsViews){
            LinearLayout check_box_layout = checkListView.getLinearLayout();
            String inputText = checkListView.getEditText().getText().toString();
            String number = Integer.toString(checklistsViews.indexOf(checkListView) + 1);
            if ( inputText.length() > limit){
                highlight(check_box_layout);
                Toast.makeText(context, "برای چک لیست " + number + " عنوان کوتاهتر وارد کن.", Toast.LENGTH_SHORT).show();
                return false;
            }
            else if (inputText.length() == 0) {
                highlight(check_box_layout);
                Toast.makeText(context , "برای چک لیست " + number + " عنوان رو وارد کن.", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

    private void highlight (LinearLayout linearLayout) {
        linearLayout.setBackgroundResource(R.drawable.highlighted_textbox_background);
    }

    public ArrayList<String> getCheckListsAsString () {
        ArrayList<String> temp = new ArrayList<>();
        for (CheckListView ch : checklistsViews) {
            temp.add(ch.getEditText().toString());
        }
        return temp;
    }


}

