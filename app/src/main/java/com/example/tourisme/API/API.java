package com.example.tourisme.API;

import com.example.tourisme.models.*;

import java.util.*;

import retrofit2.*;
import retrofit2.http.*;


public interface API {
    //+++++++++++++++++++ API Client +++++++++++++++

    @POST("/api/registre/client")
    Call<UserModel> registreClient(@Body UserModel user);
    @PUT("/api/update/client/{id}")
    Call<UserModel> updateClient(@Path("id") String id,@Body UserModel user);

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

    @GET("/api/detail-site-touristique/{id}")
    Call<ArrayList<DetailModel>> findDetailSiteTouristique(@Path("id") String id);

}
