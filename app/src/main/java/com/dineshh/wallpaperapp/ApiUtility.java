package com.dineshh.wallpaperapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtility {
    private static Retrofit retrofit=null;
    public static final String API="563492ad6f9170000100000145b2ee60014d4a04bef2271f27117c58";

    public static ApiInterfaces getApiInterface(){
        if (retrofit==null){
            retrofit=new Retrofit.Builder().baseUrl(ApiInterfaces.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit.create(ApiInterfaces.class);

    }
}
