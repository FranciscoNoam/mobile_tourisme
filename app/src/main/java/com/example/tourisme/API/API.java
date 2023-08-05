package com.example.tourisme.API;

import com.example.tourisme.models.*;

import java.util.*;

import retrofit2.*;
import retrofit2.http.*;


public interface API {
    //+++++++++++++++++++ API Client +++++++++++++++

    @POST("/api/register")
    Call<UserModel> registre(@Body UserModel user);

    @POST("/api/auth/login")
    Call<UserModel> login(@Body HashMap<String,String> user);

    //+++++++++++++++++++ API Menu +++++++++++++++
    @GET("/api/menu")
    Call<ArrayList<CategorieModel>> findAll();

    //+++++++++++++++++++ API Sous Menu ++++++++++
    @GET("/api/sous-menu/{id}")
    Call<ArrayList<SousCategorieModel>> findSousCategorie(@Path("id") String id);

    //+++++++++++++++++++ API  Site touristique ++++++++++
    @GET("/api/site-touristique/{id}")
    Call<ArrayList<SiteTourismeModel>> findSiteTouristique(@Path("id") String id);

}
