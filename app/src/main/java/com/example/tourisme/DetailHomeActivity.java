package com.example.tourisme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

public class DetailHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    /*    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isDarkThemeEnabled = sharedPreferences.getBoolean("theme_preference", false);

        // Appliquez le thème approprié en fonction de la préférence
        if (isDarkThemeEnabled) {
            setTheme(R.style.AppTheme_Dark);
        } else {
            setTheme(R.style.AppTheme);
        }
*/
        setContentView(R.layout.activity_detail_home);

        String title = getIntent().getStringExtra("title_detail_home");
        getSupportActionBar().setTitle(title);
        VideoView videoView = findViewById(R.id.video_detail); // Remplacez "video_view" par l'ID de votre VideoView
        if(R.raw.a!=0){
            videoView.setVisibility(View.VISIBLE);
            //videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.a);
            Uri videoUri = Uri.parse("http://10.0.2.2:8080/upload/a.mp4");
            videoView.setVideoURI(videoUri);
            videoView.start();
        }

    }
}