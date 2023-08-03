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
import android.widget.Toast;

import com.example.tourisme.HomeActivity;
import com.example.tourisme.R;


public class LoginFragment extends Fragment {
    private View view;
    public SharedPreferences sharedPreference;

    @Nullable
    @Override
    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setView(inflater.inflate(R.layout.fragment_login, container, false));


        sharedPreference  = getActivity().getSharedPreferences("app_state", Context.MODE_PRIVATE);
        Boolean isConnected = sharedPreference.getBoolean("is_authentificated",false);
        String emailSharedPreference = sharedPreference.getString("email",null);
        if(isConnected){
            Intent intent= new Intent(getActivity(), HomeActivity.class);
            intent.putExtra("email",emailSharedPreference);
            startActivity(intent);
        }

        TextView pageLogin = getView().findViewById(R.id.page_singin);
        TextView pageRegister = getView().findViewById(R.id.page_signup);


        EditText username = getView().findViewById(R.id.username);
        EditText password = getView().findViewById(R.id.password);
        Button connexion = getView().findViewById(R.id.connexion);
        TextView error = getView().findViewById(R.id.error_login);
        TextView signup = getView().findViewById(R.id.page_signup);
        connexion.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                error.setVisibility(getView().GONE);

                String textUsername = username.getText().toString();
                String textPassword = password.getText().toString();
                if(textUsername.trim().isEmpty() || textPassword.trim().isEmpty()){
                    error.setVisibility(getView().VISIBLE);
                    error.setText("Email ou mot de passe est incorrect");
                } else {
                    username.setText("");
                    password.setText("");

                    //  enregistrer le boolean connecter
                    SharedPreferences.Editor connectedSharedPreference= sharedPreference.edit();
                    connectedSharedPreference.putBoolean("is_authentificated",true);
                    connectedSharedPreference.putString("email",textUsername);
                    connectedSharedPreference.apply();

                    Intent intentHomeActivity = new Intent(getActivity(), HomeActivity.class);
                    intentHomeActivity.putExtra("email",textUsername);
                    startActivity(intentHomeActivity);

                   // Toast.makeText(getActivity(),"Email: ${textUsername} et Password: ${textPassword}",Toast.LENGTH_LONG).show();
                }
            }
        });

        return getView();

    }
}