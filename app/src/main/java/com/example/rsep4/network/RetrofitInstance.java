package com.example.rsep4.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    public static String BASE_URL = "https://weather-forecast-sep4.herokuapp.com/api/v1/";
    private static Retrofit retrofit;
    public static Retrofit getRetrofitClient() {
        if(retrofit==null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
