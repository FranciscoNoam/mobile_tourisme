package com.example.tourisme;

import static android.view.View.VISIBLE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.tourisme.connexion.ConnexionURL;
import com.example.tourisme.fragment.DetailFragment_backup;

public class DetailActiviy extends AppCompatActivity {

    private String categoryName;
    private String description;
    private String contenu;
    private  String urlImage;
    private  String urlVideo;
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
        contenu = getIntent().getStringExtra("contenu_detail_site");
        urlImage = getIntent().getStringExtra("image_detail_site");
        sousCategoryId = getIntent().getStringExtra("id_detail_site");
        urlVideo = getIntent().getStringExtra("video_detail_site");
        chargeDetail();
       //loadDetailFragment();

    }

    private void chargeDetail(){

        TextView titre_ = findViewById(R.id.title_detail);
        TextView desc_ = findViewById(R.id.description_detail);
        ImageView img_ = findViewById(R.id.image_detail);
        WebView contenu_ = findViewById(R.id.contenu_detail);
        VideoView video = findViewById(R.id.video_detail);

        titre_.setText(categoryName);
        desc_.setText(description);
        contenu_.loadData(contenu, "text/html", "UTF-8");

        Glide.with(this)
                .load(new ConnexionURL().getBaseUrl()+urlImage)
                .into(img_);

        if(!urlImage.trim().isEmpty()){
            Glide.with(this)
                    .load(new ConnexionURL().getBaseUrl()+urlImage)
                    .into(img_);
        }

        if(urlVideo!="null" && urlVideo!=null){
            video.setVisibility(VISIBLE);
            Uri videoUri = Uri.parse(new ConnexionURL().getBaseUrl()+urlVideo);
            video.setVideoURI(videoUri);
            video.start();
        }
    }


    private void loadDetailFragment(){
        getSupportActionBar().setTitle(categoryName);

        DetailFragment_backup detailFragment = new DetailFragment_backup();
        Bundle bundle = new Bundle();
        bundle.putString("sousCategoryId", sousCategoryId);
        detailFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_detail_site, detailFragment).addToBackStack(null).commit();

    }
}