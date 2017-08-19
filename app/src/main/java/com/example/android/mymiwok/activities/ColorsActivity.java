package com.example.android.mymiwok.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.mymiwok.R;
import com.example.android.mymiwok.fragments.ColorsFragment;

public class ColorsActivity extends AppCompatActivity {

    private static final String TAG = "ColorsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new ColorsFragment())
                .commit();
    }
}
