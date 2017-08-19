package com.example.android.mymiwok.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.mymiwok.R;
import com.example.android.mymiwok.fragments.NumbersFragment;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new NumbersFragment())
                .commit();
    }


}
