package com.example.myclock;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myclock.Views.CheckListView;

import java.util.ArrayList;
import java.util.Objects;

public class AddPlanActivity extends AppCompatActivity {
    private ArrayList<String> checklists = new ArrayList<>();
    private LinearLayout checkListContainer;
    private CheckListView checkListView;
    int limit = 20;
    AlertDialog.Builder checkListDialogBuilder ;
    AlertDialog checkListDialog;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plan);
        checkListContainer = findViewById(R.id.CheckListContainer);
        checkListView = new CheckListView(this, checkListListener);

    }

    public void addCheckList(String title) {
        View view = checkListView.getCheckList(title);
        checklists.add(title);
        checkListContainer.addView(view);
    }

    public void removeFromCheckList(String title) {
        for (int i = 0; i < checklists.size(); i++) {

            View child = checkListContainer.getChildAt(i);
            if ((child.getTag()).equals(title)) {
                checkListContainer.removeViewAt(i);
                checklists.remove((String) child.getTag());
                break;
            }
        }
    }

    View.OnClickListener checkListListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            removeFromCheckList((String) view.getTag());
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AddPlanActivity.this,MainActivity.class));
        finish();
    }
    public void addPlan(View view) {

        checkListDialogBuilder = new AlertDialog.Builder(this);
        checkListDialogBuilder.setView(getLayoutInflater().inflate(R.layout.add_check_list_dialogue,null));
        checkListDialog = checkListDialogBuilder.create();
        checkListDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        Objects.requireNonNull(checkListDialog.getWindow()).getAttributes().windowAnimations = R.style.DialogAnimation;
        checkListDialog.show();

    }
    public void cancel(View view) {
        checkListDialog.dismiss();
    }
    public void commit(View view) {

        EditText editText = checkListDialog.findViewById(R.id.editText);
        String checkListString = editText.getText().toString();
        if (checkListString.length() > limit ){
            Toast.makeText(AddPlanActivity.this, "عنوان کوتاه تری وارد کن.", Toast.LENGTH_SHORT).show();
        }
        else if (checkListString.equals("") )
            Toast.makeText(AddPlanActivity.this, "عنوان رو وارد کن.", Toast.LENGTH_SHORT).show();
        else{
            addCheckList(checkListString);
            checkListDialog.dismiss();
        }
    }
}