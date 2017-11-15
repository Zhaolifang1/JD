package com.example.jd.mine.model;

import com.example.jd.api.Api;
import com.example.jd.api.ApiService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public class RetroLoginFactory {

    //构造
    private RetroLoginFactory() {

    }

    private static OkHttpClient httpClient = new OkHttpClient.Builder()
//            .addInterceptor(new LoggingInterceptor())//添加拦截器
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();
    private static ApiService retrofitService = new Retrofit.Builder()
            .baseUrl(Api.LOGIN_REG)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//            .client(httpClient)//添加拦截器
            .build()
            .create(ApiService.class);
    //单列模式
    public static ApiService getInstance() {
        return retrofitService;
    }


}
