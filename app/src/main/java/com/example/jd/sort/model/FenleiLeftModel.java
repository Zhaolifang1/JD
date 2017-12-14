package com.example.jd.sort.model;


import com.example.jd.api.ApiService;
import com.example.jd.sort.bean.FenleiLeftBean;

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

public class FenleiLeftModel implements FenleiLeftIModel {
    @Override
    public void RequestData(String url, final OnRequestListener onRequestListener) {
        //retrofit网络请求
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())

                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<FenleiLeftBean> getdatas = apiService.getdatas();
        getdatas.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FenleiLeftBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onRequestListener.OnError(e.getMessage().toString());
                    }

                    @Override
                    public void onNext(FenleiLeftBean fenleiLeftBean) {
                        List<FenleiLeftBean.DataBean> data = fenleiLeftBean.data;
                        onRequestListener.OnSuccess(data);
                    }
                });
    }


}
