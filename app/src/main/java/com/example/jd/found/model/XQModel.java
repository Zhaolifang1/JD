package com.example.jd.found.model;

import android.util.Log;

import com.example.jd.api.ApiService;
import com.example.jd.found.bean.XQBean;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public class XQModel implements XQIModel {
    public static final String TAG="XQModel";

    @Override
    public void getHttpData(String url, String mediaId, final SYOnRequestListener syOnRequestListener) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        //动态代理得到网络接口
        ApiService apiService = retrofit.create(ApiService.class);

        Observable<XQBean> movieData = apiService.getMovieData(mediaId);

        movieData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<XQBean>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: ");
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(XQBean xqBean) {
                        Log.d(TAG, "onNext: ");
                        syOnRequestListener.OnIntoData(xqBean);
                    }
                });
    }





}
