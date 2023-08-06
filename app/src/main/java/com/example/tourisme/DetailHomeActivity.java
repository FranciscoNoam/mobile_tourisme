package com.example.tourisme;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.VideoView;

import com.example.tourisme.API.API;
import com.example.tourisme.connexion.ConnexionURL;

public class DetailHomeActivity extends AppCompatActivity {
    private WebView webView;
    private ConnexionURL connexion;
    private API axios;

    public ConnexionURL getConnexion() {
        return connexion;
    }

    public void setConnexion(ConnexionURL connexion) {
        this.connexion = connexion;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        decodeHTML();

    }

    private void decodeHTML(){
        webView = findViewById(R.id.description_detail_html);
        webView.getSettings().setJavaScriptEnabled(true); // Activer JavaScript si nécessaire

        String htmlContent = "<h1>Bonjour</h1>";
        webView.loadData(htmlContent, "text/html", "UTF-8");

        webView.setWebViewClient(new WebViewClient());
    }

    private void loadDataDescription(){

    }
}