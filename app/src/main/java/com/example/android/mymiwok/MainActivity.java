package com.example.android.mymiwok;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.NavUtils;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.android.mymiwok.activities.ColorsActivity;
import com.example.android.mymiwok.activities.FamilyActivity;
import com.example.android.mymiwok.activities.NumbersActivity;
import com.example.android.mymiwok.activities.PhrasesActivity;
import com.example.android.mymiwok.adapters.SimpleFragmentPagerAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);

        ViewPager paginadorVista = (ViewPager) findViewById(R.id.pager);
        SimpleFragmentPagerAdapter pageAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), getApplicationContext());
        paginadorVista.setAdapter(pageAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(paginadorVista);

//        TextView numbers = (TextView) findViewById(R.id.numbers);
//        TextView phrases = (TextView) findViewById(R.id.phrases);
//        TextView family = (TextView) findViewById(R.id.family);
//        TextView colors = (TextView) findViewById(R.id.colors);
//
//        numbers.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent numbersIntent = new Intent(MainActivity.this, NumbersActivity.class);
//                startActivity(numbersIntent);
//            }
//        });
//
//        phrases.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent phrasesIntent = new Intent(MainActivity.this, PhrasesActivity.class);
//                startActivity(phrasesIntent);
//            }
//        });
//
//        family.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent familyIntent = new Intent(MainActivity.this, FamilyActivity.class);
//                startActivity(familyIntent);
//            }
//        });
//
//        colors.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent colorIntent = new Intent(MainActivity.this, ColorsActivity.class);
//                startActivity(colorIntent);
//            }
//        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
