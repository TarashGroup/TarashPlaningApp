package com.example.myclock;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;

import com.example.myclock.Database.Note;
import com.example.myclock.litner.FlashCardPreView;
import com.example.myclock.litner.ViewPagerAdaptor;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;


public class LitnerBox extends AppCompatActivity {
    ArrayList<String> listOfTabsNames;
    ArrayList<ArrayList<FlashCardPreView>> cardGroups;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.litner_box_layout);
        ViewPager2 viewPager = findViewById(R.id.viewPagerForLitner);
        TabLayout tabLayout = findViewById(R.id.tabsForLitner);

        listOfTabsNames = new ArrayList<>();
        listOfTabsNames.add("همه");
        listOfTabsNames.add("خوانده نشده");
        listOfTabsNames.add("مورد علاقه");

        //******************************************************Test
        cardGroups = new ArrayList<>();

        ArrayList<FlashCardPreView> all = new ArrayList<>();
        ArrayList<FlashCardPreView> favorite = new ArrayList<>();
        ArrayList<FlashCardPreView> unseen = new ArrayList<>();

        Note note = new Note("مورد علاقه" , "javab ke mohem ni" , null);
        note.setCorrect(13);
        note.setTotalSeen(20);
        FlashCardPreView flashCardPreView = new FlashCardPreView(this , note);
        favorite.add(flashCardPreView);
        all.add(flashCardPreView);


        Note note2 = new Note("مورد علاقه" , "javab ke mohem ni" , null);
        note.setCorrect(13);
        note.setTotalSeen(20);
        flashCardPreView = new FlashCardPreView(this , note2);
        favorite.add(flashCardPreView);
        all.add(flashCardPreView);

        Note note3 = new Note("خوانده نشده" , "javab ke mohem ni" , null);
        note.setCorrect(13);
        note.setTotalSeen(20);
        flashCardPreView = new FlashCardPreView(this , note3);
        unseen.add(flashCardPreView);
        all.add(flashCardPreView);

        Note note4 = new Note("خوانده نشده" , "javab ke mohem ni" , null);
        note.setCorrect(13);
        note.setTotalSeen(20);
        flashCardPreView = new FlashCardPreView(this , note4);
        unseen.add(flashCardPreView);
        all.add(flashCardPreView);

        cardGroups.add(all);
        cardGroups.add(unseen);
        cardGroups.add(favorite);
//************************************************************************

        ViewPagerAdaptor viewPagerAdaptor = new ViewPagerAdaptor(listOfTabsNames , cardGroups);
        viewPager.setAdapter(viewPagerAdaptor);



        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager,
                true, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText( listOfTabsNames.get(position));
            }
        });
        tabLayoutMediator.attach();





    }
}