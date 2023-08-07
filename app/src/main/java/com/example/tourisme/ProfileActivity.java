package com.example.tourisme;

import static android.view.View.VISIBLE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.example.tourisme.API.API;
import com.example.tourisme.connexion.ConnexionURL;
import com.example.tourisme.models.UserModel;
import com.example.tourisme.notification.PopupNotification;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.app.ProgressDialog;
public class ProfileActivity extends AppCompatActivity {
    private PopupNotification popupNotification;
    public SharedPreferences sharedPreference;
    private ConnexionURL connexion;
    private API axios;
    private  String password_input=null;
    public ConnexionURL getConnexion() {
        return connexion;
    }

    public void setConnexion(ConnexionURL connexion) {
        this.connexion = connexion;
    }
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

        setContentView(R.layout.activity_profile);
        sharedPreference  = this.getSharedPreferences("app_state", Context.MODE_PRIVATE);
        popupNotification = new PopupNotification(this);

        String name_ = sharedPreference.getString("name",null);
        String email_ = sharedPreference.getString("email",null);
        String password_ = sharedPreference.getString("password",null);
        String id_ = sharedPreference.getString("id",null);

        EditText name_init = findViewById(R.id.name_profile);
        EditText email_init = findViewById(R.id.email_profile);
        EditText password_init = findViewById(R.id.password_profile);
        TextView error =findViewById(R.id.error_profile);

        Button change = findViewById(R.id.change_profile);

        name_init.setText(name_);
        email_init.setText(email_);
        password_init.setText(password_);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupNotification.showLoadingPopup();

                EditText name = findViewById(R.id.name_profile);
                EditText email = findViewById(R.id.email_profile);
                EditText password = findViewById(R.id.password_profile);

                String textName = name.getText().toString();
                String textEmail = email.getText().toString();
                String textPassword = password.getText().toString();

                if(textName.trim().isEmpty() || textEmail.trim().isEmpty()){
                    error.setVisibility(VISIBLE);
                    error.setText("Champs invalides");

                    popupNotification.hideLoadingPopup();

                } else {

                    if(textPassword.trim().isEmpty()){
                        password_input = textPassword;
                    }

                    setConnexion(new ConnexionURL());
                    axios = getConnexion().getApi();

                    UserModel updateData = new UserModel(id_,textName,textEmail,password_input);

                    Call<UserModel> userResponse = axios.updateClient(id_,updateData);
                    userResponse.enqueue(new Callback<UserModel>() {
                        @Override
                        public void onResponse(Call<UserModel> call, Response<UserModel> response) {

                            if(response.code()==200){
                                UserModel tmp = response.body();

                                SharedPreferences.Editor connectedSharedPreference= sharedPreference.edit();
                                connectedSharedPreference.putString("email",textEmail);
                                connectedSharedPreference.putString("name",textName);

                                if(password_input!=null){
                                    connectedSharedPreference.putString("password",password_input);
                                }

                                connectedSharedPreference.apply();

                                Intent intentHomeActivity = new Intent(ProfileActivity.this, AcceuilActivity.class);
                                intentHomeActivity.putExtra("update",textName);
                                intentHomeActivity.putExtra("name",tmp.getName());
                                startActivity(intentHomeActivity);


                            } else {
                                error.setVisibility(View.VISIBLE);
                                error.setText("Erreur de connection");

                            }

                            popupNotification.hideLoadingPopup();
                        }

                        @Override
                        public void onFailure(Call<UserModel> call, Throwable t) {
                            error.setVisibility(View.VISIBLE);
                            error.setText("Erreur de connexion");

                            popupNotification.hideLoadingPopup();
                        }
                    });
                }
            }
        });
    }


}