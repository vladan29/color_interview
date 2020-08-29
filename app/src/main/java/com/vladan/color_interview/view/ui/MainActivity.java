package com.vladan.color_interview.view.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.vladan.color_interview.R;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            ListFragment listFragment = new ListFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, listFragment).commit();
        }
    }
}