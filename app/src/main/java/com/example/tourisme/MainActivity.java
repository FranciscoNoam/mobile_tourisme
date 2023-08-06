package com.example.tourisme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.tourisme.fragment.*;

public class MainActivity extends AppCompatActivity {

    private TextView pageLogin,pageRegister;

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

        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new LoginFragment()).commit();

         pageLogin = findViewById(R.id.page_singin);
         pageRegister = findViewById(R.id.page_signup);

        pageLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                replaceFragment(new LoginFragment());
            }
        });
        pageRegister.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                replaceFragment(new RegisterFragment());
            }
        });

    }

    public void replaceFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,fragment).commit();
    }
}