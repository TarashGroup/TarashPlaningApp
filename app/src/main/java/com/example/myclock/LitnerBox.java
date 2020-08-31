package com.example.myclock;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;

import com.example.myclock.litner.ViewPagerAdaptor;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;


public class LitnerBox extends AppCompatActivity {
    ArrayList<String> listOfTabsNames;
    ArrayList<View> all;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.litner_box_layout);
        ViewPager2 viewPager = findViewById(R.id.viewPagerForLitner);
        TabLayout tabLayout = findViewById(R.id.tabsForLitner);

        listOfTabsNames = new ArrayList<>();
        listOfTabsNames.add("All");
        listOfTabsNames.add("unread");
        listOfTabsNames.add("favorite");

        ViewPagerAdaptor viewPagerAdaptor = new ViewPagerAdaptor(listOfTabsNames);
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