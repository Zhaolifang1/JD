package com.example.jd.found.model;


import com.example.jd.api.ApiService;
import com.example.jd.found.bean.MoviesBean;

import java.util.List;

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

public class SYModel implements SYIModel {
    @Override
    public void RequestData(String url, final SYOnRequestListener syOnRequestListener) {
        //retrofit网络请求
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())

                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<MoviesBean> getdata = apiService.getdata();
        getdata.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MoviesBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        syOnRequestListener.OnError(e.getMessage().toString());
                    }

                    @Override
                    public void onNext(MoviesBean moviesBean) {
                        List<MoviesBean.RetBean.ListBean> list = moviesBean.ret.list;
                        syOnRequestListener.OnSuccess(list);
                    }
                });
    }
}
