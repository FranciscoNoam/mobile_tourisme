package com.example.tourisme;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.tourisme.fragment.PreferenceFragment;

public class PreferenceActivity extends AppCompatActivity {
    public SharedPreferences sharedPreference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isDarkThemeEnabled = sharedPreferences.getBoolean("theme_preference", false);

        // Appliquez le thème approprié en fonction de la préférence
        if (isDarkThemeEnabled) {
            setTheme(R.style.AppTheme_Dark);
        } else {
            setTheme(R.style.AppTheme);
        }

     /*   Boolean isConnected = sharedPreference.getBoolean("is_authentificated",false);
        String emailSharedPreference = sharedPreference.getString("email",null);
        if(!isConnected){
            Intent intent= new Intent(this, MainActivity.class);
            intent.putExtra("email",emailSharedPreference);
            startActivity(intent);
        } */


        setContentView(R.layout.activity_preference);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_setting, new PreferenceFragment()).commit();



    }



    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp(){
        //onBackPressed();
        Intent intentSearch = new Intent(PreferenceActivity.this,MainActivity.class);
        startActivity(intentSearch);

        return true;
    }

}