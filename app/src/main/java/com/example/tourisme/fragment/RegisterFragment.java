package com.example.tourisme.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tourisme.API.API;
import com.example.tourisme.AcceuilActivity;
import com.example.tourisme.R;
import com.example.tourisme.connexion.ConnexionURL;
import com.example.tourisme.models.UserModel;
import com.example.tourisme.notification.PopupNotification;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterFragment extends Fragment {
    private PopupNotification popupNotification;
    private View view;
    private ConnexionURL connexion;
    private API axios;
    public SharedPreferences sharedPreference;

    public ConnexionURL getConnexion() {
        return connexion;
    }

    public void setConnexion(ConnexionURL connexion) {
        this.connexion = connexion;
    }

    @Nullable
    @Override
    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        this.setView(inflater.inflate(R.layout.fragment_register, container, false));

        sharedPreference  = getActivity().getSharedPreferences("app_state", Context.MODE_PRIVATE);
        popupNotification = new PopupNotification(getActivity());

        Boolean isConnected = sharedPreference.getBoolean("is_authentificated",false);
        String emailSharedPreference = sharedPreference.getString("email",null);
        if(isConnected){
            Intent intent= new Intent(getActivity(), AcceuilActivity.class);
            intent.putExtra("email",emailSharedPreference);
            startActivity(intent);
        }

        EditText name = this.getView().findViewById(R.id.name_registre);
        EditText email = this.getView().findViewById(R.id.email_registre);
        EditText password = this.getView().findViewById(R.id.password_registre);
        TextView error =this.getView().findViewById(R.id.error_registre);

        Button registre = this.getView().findViewById(R.id.btn_registre);

        registre.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                error.setVisibility(getView().GONE);

                popupNotification.showLoadingPopup();

                String textName = name.getText().toString();
                String textUsername = email.getText().toString();
                String textPassword = password.getText().toString();
                if(textName.trim().isEmpty() || textUsername.trim().isEmpty() || textPassword.trim().isEmpty()){
                    error.setVisibility(getView().VISIBLE);
                    error.setText("Champs invalides");
                    popupNotification.hideLoadingPopup();

                } else {
                    name.setText("");
                    email.setText("");
                    password.setText("");

                    setConnexion(new ConnexionURL());
                    axios = getConnexion().getApi();
                    HashMap<String,String> map = new HashMap<>();
                    map.put("password",textPassword);
                    map.put("email",textUsername);

                    UserModel newData = new UserModel();
                    newData.setName(textName);
                    newData.setEmail(textUsername);
                    newData.setPassword(textPassword);

                    Call<UserModel> userResponse = axios.registreClient(newData);

                    userResponse.enqueue(new Callback<UserModel>() {
                        @Override
                        public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                            System.out.println(response.code());
                            if(response.code()==200){
                                UserModel tmp = response.body();


                                SharedPreferences.Editor connectedSharedPreference= sharedPreference.edit();
                                connectedSharedPreference.putBoolean("is_authentificated",true);
                                connectedSharedPreference.putString("id",tmp.get_id());
                                connectedSharedPreference.putString("email",textUsername);
                                connectedSharedPreference.putString("name",textName);
                                connectedSharedPreference.putString("password",textPassword);
                                connectedSharedPreference.apply();

                                Intent intentHomeActivity = new Intent(getActivity(), AcceuilActivity.class);
                                intentHomeActivity.putExtra("name_registre",tmp.getName());
                                startActivity(intentHomeActivity);

                            } else {
                                error.setVisibility(getView().VISIBLE);
                                error.setText("L'une de ses données ne sont pas valides");
                            }
                            popupNotification.hideLoadingPopup();

                        }

                        @Override
                        public void onFailure(Call<UserModel> call, Throwable t) {
                            error.setVisibility(getView().VISIBLE);
                            error.setText("Erreur de connexion");

                            popupNotification.hideLoadingPopup();
                        }
                    });
                }
            }
        });

        return this.getView();
    }
}