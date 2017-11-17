package com.example.jd.sort.model;


import com.example.jd.api.Api;
import com.example.jd.api.ApiService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class SortLeftRetroFactory {

    private SortLeftRetroFactory() {
    }

    private static OkHttpClient httpClient = new OkHttpClient.Builder()
            .addInterceptor(new LoggingInterceptor()).connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();
    private static ApiService retrofitService = new Retrofit.Builder()
            .baseUrl(Api.SORT_LEFT)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(httpClient)
            .build()
            .create(ApiService.class);

    //单列模式
    public static ApiService getInstance() {
        return retrofitService;
    }
}