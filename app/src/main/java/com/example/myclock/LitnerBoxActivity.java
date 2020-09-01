package com.example.myclock;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myclock.Database.Note;
import com.example.myclock.litner.FlashCardPreView;
import com.example.myclock.litner.ViewPagerAdaptor;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

import LitnerBox.LitnerBox;


public class LitnerBoxActivity extends AppCompatActivity {
    ArrayList<String> listOfTabsNames;
    ArrayList<ArrayList<FlashCardPreView>> cardGroups;
    private static ViewPagerAdaptor viewPagerAdaptor;
    private TabLayoutMediator tabLayoutMediator;
    private ViewPager2 viewPager;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.litner_box_layout);
        viewPager = findViewById(R.id.viewPagerForLitner);
        TabLayout tabLayout = findViewById(R.id.tabsForLitner);



       View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(LitnerBoxActivity.this);
                Note note = (Note) view.getTag();
                LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(LitnerBoxActivity.this).
                        inflate(R.layout.dialog_for_fullview_card,null);

                int totalSeen = note.getTotalSeen();
                int correct = note.getCorrect();
                TextView title = linearLayout.findViewById(R.id.titleFull);
                title.setText(note.getTitle());
                TextView totalSeenText = linearLayout.findViewById(R.id.totalSeenText);
                totalSeenText.setText(Integer.toString(totalSeen));
                TextView correctText = linearLayout.findViewById(R.id.correctText);
                correctText.setText(Integer.toString(correct));
                TextView wrongText = linearLayout.findViewById(R.id.wrongText);
                wrongText.setText(Integer.toString(totalSeen - correct));
                Button like = linearLayout.findViewById(R.id.likeButton);
                like.setTag(note);
                if (note.isFavorite())
                    like.setBackgroundResource(R.drawable.heart_full);

                builder.setView(linearLayout);
                AlertDialog alertDialog = builder.create();
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                alertDialog.show();

                    /////////////////////////////////////

                alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {
                            viewPagerAdaptor.notifyItemChanged(0);
                        }
                    });
            }

        };


        listOfTabsNames = new ArrayList<>();
        listOfTabsNames.add("همه");
        listOfTabsNames.add("خوانده نشده");
        listOfTabsNames.add("مورد علاقه");

        //******************************************************Test
        cardGroups = new ArrayList<>();

        ArrayList<FlashCardPreView> all = new ArrayList<>();
        ArrayList<FlashCardPreView> favorite = new ArrayList<>();
        ArrayList<FlashCardPreView> unseen = new ArrayList<>();

        Note note = new Note("123456789/123456789/123456789/123456 ...123456789/123456789/123456789/123456 ...123456789/123456789/123456789/123456 ..." , "javab ke mohem ni" , null);
        note.setCorrect(13);
        note.setTotalSeen(20);
        FlashCardPreView flashCardPreView = new FlashCardPreView(this , note ,listener);
        favorite.add(flashCardPreView);
        all.add(flashCardPreView);


        Note note2 = new Note("نویسنده بی نوایان که بود؟" , "javab ke mohem ni" , null);
        note.setCorrect(13);
        note.setTotalSeen(20);
        flashCardPreView = new FlashCardPreView(this , note2, listener);
        favorite.add(flashCardPreView);
        all.add(flashCardPreView);

        Note note3 = new Note("خوانده نشده" , "javab ke mohem ni" , null);
        note.setCorrect(13);
        note.setTotalSeen(20);
        flashCardPreView = new FlashCardPreView(this , note3, listener);
        unseen.add(flashCardPreView);
        all.add(flashCardPreView);

        Note note4 = new Note("خوانده نشده" , "javab ke mohem ni" , null);
        note.setCorrect(13);
        note.setTotalSeen(20);
        flashCardPreView = new FlashCardPreView(this , note4, listener);
        unseen.add(flashCardPreView);
        all.add(flashCardPreView);
        Note note5 = new Note("خوانده نشده" , "javab ke mohem ni" , null);
        note.setCorrect(13);
        note.setTotalSeen(20);
        flashCardPreView = new FlashCardPreView(this , note5, listener);
        unseen.add(flashCardPreView);
        all.add(flashCardPreView);

        cardGroups.add(all);
        cardGroups.add(unseen);
        cardGroups.add(favorite);
//************************************************************************

        viewPagerAdaptor = new ViewPagerAdaptor(listOfTabsNames , cardGroups);
        viewPager.setAdapter(viewPagerAdaptor);



        tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager,
                true, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText( listOfTabsNames.get(position));
            }
        });
        tabLayoutMediator.attach();



    }
    public void likeClicked(View view){
        Note note = (Note) view.getTag();
        if (note.isFavorite()){
            view.setBackgroundResource(R.drawable.heart);
            note.setFavorite(false);
        }
        else{
            view.setBackgroundResource(R.drawable.heart_full);
            note.setFavorite(true);
        }
    }

    public void seeAnswer(View view){

    }

}