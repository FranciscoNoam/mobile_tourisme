package com.example.tourisme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DetailHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_home);

        String title = getIntent().getStringExtra("title_detail_home");
        getSupportActionBar().setTitle(title);
    }
}