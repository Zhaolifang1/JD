package com.example.jd.sort.model;


import com.example.jd.api.ApiService;
import com.example.jd.sort.bean.FenleiRightBean;

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

public class FenleiRightModel implements FenleiRightIModel {
    @Override
    public void RequestRightData(String url, int cid, final OnRequestListener onRequestListener) {
        //retrofit网络请求
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<FenleiRightBean> rightData = apiService.getRightData(cid);
        rightData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FenleiRightBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onRequestListener.OnError(e.getMessage().toString());
                    }

                    @Override
                    public void onNext(FenleiRightBean fenleiRightBean) {
                        List<FenleiRightBean.DataBean> data = fenleiRightBean.data;
                        onRequestListener.OnRightSuccess(data);
                    }
                });
    }
}
