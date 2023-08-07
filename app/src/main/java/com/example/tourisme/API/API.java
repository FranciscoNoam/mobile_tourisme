package com.example.tourisme.API;

import com.example.tourisme.models.*;

import java.util.*;

import retrofit2.*;
import retrofit2.http.*;


public interface API {
    //+++++++++++++++++++ API Client +++++++++++++++

    @POST("/api/register")
    Call<UserModel> registreClient(@Body UserModel user);
    @PUT("/api/update/{id}")
    Call<UserModel> updateClient(@Path("id") String id,@Body UserModel user);

    @POST("/api/login")
    Call<UserModel> login(@Body HashMap<String,String> user);

    //+++++++++++++++++++ API Menu +++++++++++++++
    @GET("/api/getAllCategories")
    Call<ArrayList<CategorieModel>> findAll();

    //+++++++++++++++++++ API Sous Menu ++++++++++
    @GET("/api/getAllSousCategories/{id}")
    Call<ArrayList<SousCategorieModel>> findSousCategorie(@Path("id") String id);

    //+++++++++++++++++++ API  Site touristique ++++++++++
    @GET("/api/getAllSites/{id}")
    Call<ArrayList<SiteTourismeModel>> findSiteTouristique(@Path("id") String id);

    @GET("/api/getSite/{id}")
    Call<ArrayList<DetailModel>> findDetailSiteTouristique(@Path("id") String id);

}
