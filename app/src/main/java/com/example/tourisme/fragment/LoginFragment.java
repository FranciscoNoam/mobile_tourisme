package com.example.tourisme.fragment;

import android.content.*;
import android.os.Bundle;

import androidx.annotation.*;
import androidx.fragment.app.Fragment;

import android.view.*;
import android.widget.*;

import com.example.tourisme.API.API;
import com.example.tourisme.*;
import com.example.tourisme.connexion.ConnexionURL;
import com.example.tourisme.models.UserModel;

import java.util.HashMap;

import retrofit2.*;


public class LoginFragment extends Fragment {
    private ConnexionURL connexion;
    private API axios;

    public ConnexionURL getConnexion() {
        return connexion;
    }

    public void setConnexion(ConnexionURL connexion) {
        this.connexion = connexion;
    }

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.setView(inflater.inflate(R.layout.fragment_login, container, false));

        sharedPreference  = getActivity().getSharedPreferences("app_state", Context.MODE_PRIVATE);
        Boolean isConnected = sharedPreference.getBoolean("is_authentificated",false);
        String emailSharedPreference = sharedPreference.getString("email",null);
        if(isConnected){
            Intent intent= new Intent(getActivity(), HomeActivity.class);
            intent.putExtra("email",emailSharedPreference);
            startActivity(intent);
        }

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

                    setConnexion(new ConnexionURL());
                    axios = getConnexion().getApi();
                    HashMap<String,String> map = new HashMap<>();
                    map.put("password",textPassword);
                    map.put("email",textUsername);
                    Call<UserModel> userResponse = axios.login(map);

                    userResponse.enqueue(new Callback<UserModel>() {
                        @Override
                        public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                            System.out.println(response.code());
                            if(response.code()==200){
                                UserModel tmp = response.body();

                                    SharedPreferences.Editor connectedSharedPreference= sharedPreference.edit();
                                    connectedSharedPreference.putBoolean("is_authentificated",true);
                                    connectedSharedPreference.putString("email",textUsername);
                                    connectedSharedPreference.apply();

                                   // Intent intentHomeActivity = new Intent(getActivity(), HomeActivity.class);
                                Intent intentHomeActivity = new Intent(getActivity(), AcceuilActivity.class);
                                    intentHomeActivity.putExtra("name",tmp.getName());
                                    startActivity(intentHomeActivity);

                            } else {
                                error.setVisibility(getView().VISIBLE);
                                error.setText("Email ou mot de passe est incorrect");
                            }

                        }

                        @Override
                        public void onFailure(Call<UserModel> call, Throwable t) {
                            error.setVisibility(getView().VISIBLE);
                            error.setText("Email ou mot de passe est incorrect");

                            //System.out.println("tonga Erreur");
                            //System.out.println(t.getMessage());
                        }
                    });
                }
            }
        });

        return this.getView();

    }

    private void getAuthLogin(){

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
    }
}