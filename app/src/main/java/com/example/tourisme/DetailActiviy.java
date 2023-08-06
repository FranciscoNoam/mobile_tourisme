package com.example.tourisme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.tourisme.fragment.DetailFragment;

public class DetailActiviy extends AppCompatActivity {

    private String categoryName;
    private String sousCategoryId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isDarkThemeEnabled = sharedPreferences.getBoolean("theme_preference", false);

        if (isDarkThemeEnabled) {
            setTheme(R.style.AppTheme_Dark);
        } else {
            setTheme(R.style.AppTheme);
        }

        setContentView(R.layout.activity_detail);

        categoryName = getIntent().getStringExtra("title_detail_site");
        sousCategoryId = getIntent().getStringExtra("id_detail_site");
        loadDetailFragment();
    }

    private void loadDetailFragment(){
        getSupportActionBar().setTitle(categoryName);

        DetailFragment detailFragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("sousCategoryId", sousCategoryId);
        detailFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_detail_site, detailFragment).addToBackStack(null).commit();

    }
}