package com.example.tourisme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.*;

public class ProfileActivity extends AppCompatActivity {
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

        setContentView(R.layout.activity_profile);

        sharedPreference  = this.getSharedPreferences("app_state", Context.MODE_PRIVATE);

        Boolean isConnected = sharedPreference.getBoolean("is_authentificated",false);
        String name_ = sharedPreference.getString("name",null);
        String email_ = sharedPreference.getString("email",null);
        String password_ = sharedPreference.getString("password",null);

        EditText name = findViewById(R.id.name_profile);
        EditText email = findViewById(R.id.email_profile);
        EditText password = findViewById(R.id.password_profile);
        Button change = findViewById(R.id.change_profile);

        name.setText(name_);
        email.setText(email_);
        password.setText(password_);


    }
}