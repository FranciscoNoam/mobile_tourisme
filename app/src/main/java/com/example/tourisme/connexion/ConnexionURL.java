package com.example.tourisme.connexion;

import com.example.tourisme.API.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConnexionURL {
    private  static String BASE_URL="http://10.0.2.2:8080";
    private Retrofit retrofit;
    private API api;

    public API getApi() {
        return api;
    }

    public void setApi(API api) {
        this.api = api;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public void setRetrofit(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public ConnexionURL() {
        Retrofit retrofit_tmp = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.setRetrofit(retrofit_tmp);
        this.setApi(this.getRetrofit().create(API.class));

    }
}
