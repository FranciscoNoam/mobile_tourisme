package com.example.tourisme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tourisme.connexion.ConnexionURL;
import com.example.tourisme.fragment.DetailFragment;

public class DetailActiviy extends AppCompatActivity {

    private String categoryName;
    private String description;
    private  String urlImage;
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
        description = getIntent().getStringExtra("description_detail_site");
        urlImage = getIntent().getStringExtra("image_detail_site");
        sousCategoryId = getIntent().getStringExtra("id_detail_site");

        chargeDetail();
        loadDetailFragment();
    }

    private void chargeDetail(){
        TextView titre_ = findViewById(R.id.title_detail);
        TextView desc_ = findViewById(R.id.description_detail);
        ImageView img_ = findViewById(R.id.image_detail);
        titre_.setText(categoryName);
        desc_.setText(description);
        Glide.with(this)
                .load(new ConnexionURL().getBaseUrl()+urlImage)
                .into(img_);
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