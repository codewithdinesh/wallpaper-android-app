package com.dineshh.wallpaperapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

import static com.dineshh.wallpaperapp.ApiUtility.API;

public interface ApiInterfaces {
    String BASE_URL="https://api.pexels.com/v1/";
    @Headers("Authorization: "+API)
    @GET("curated")
    Call<SearchModel> getImage(
            @Query("page") int page,
            @Query("per_page") int per_page
    );

    @Headers("Authorization: "+API)
    @GET("search")
    Call<SearchModel> getImage(
            @Query("query") String query,
            @Query("page") int page,
            @Query("per_page") int per_page
    );


}
