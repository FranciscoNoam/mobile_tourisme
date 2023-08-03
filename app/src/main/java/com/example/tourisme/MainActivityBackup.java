package com.example.tourisme;

import static android.view.View.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivityBackup  {
/*
    public SharedPreferences sharedPreference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreference  = this.getSharedPreferences("app_state", Context.MODE_PRIVATE);
        Boolean isConnected = sharedPreference.getBoolean("is_authentificated",false);
        String emailSharedPreference = sharedPreference.getString("email",null);
        if(isConnected){
            Intent intent= new Intent(MainActivityBackup.this,HomeActivity.class);
            intent.putExtra("email",emailSharedPreference);
            startActivity(intent);
        }

        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);
        Button connexion = findViewById(R.id.connexion);
        TextView error = findViewById(R.id.error_login);
        TextView signup = findViewById(R.id.page_signup);
        connexion.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                error.setVisibility(GONE);

                String textUsername = username.getText().toString();
                String textPassword = password.getText().toString();
                if(textUsername.trim().isEmpty() || textPassword.trim().isEmpty()){
                    error.setVisibility(VISIBLE);
                    error.setText("Email ou mot de passe est incorrect");
                } else {
                    username.setText("");
                    password.setText("");

                    //  enregistrer le boolean connecter
                    SharedPreferences.Editor connectedSharedPreference= sharedPreference.edit();
                    connectedSharedPreference.putBoolean("is_authentificated",true);
                    connectedSharedPreference.putString("email",textUsername);
                    connectedSharedPreference.apply();

                    Intent intentHomeActivity = new Intent(MainActivityBackup.this, HomeActivity.class);
                    intentHomeActivity.putExtra("email",textUsername);
                    startActivity(intentHomeActivity);

                    //Toast.makeText(MainActivity.this,"Email: ${textUsername} et Password: ${textPassword}",Toast.LENGTH_LONG).show();
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intentInscription = new Intent(MainActivityBackup.this, Inscription.class);
                startActivity(intentInscription);
            }
        });

    }

 */
}