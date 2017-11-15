package com.example.jd.home.model;

import com.example.jd.api.Api;
import com.example.jd.api.ApiService;
import com.example.jd.home.bean.SYBean;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public class Model implements Imodel{

    private Retrofit retrofit;
    private ApiService apiservice;
    private Observable<SYBean> noParams;
    private List<SYBean.GoodsListBean> data=new ArrayList<>();
    @Override
    public void Request(String url, final Onclick onclick) {
        retrofit = new Retrofit.Builder()
                .baseUrl(Api.SY)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        //通过动态代理得到网络接口对象
        apiservice = retrofit.create(ApiService.class);
        noParams = apiservice.getDat();

        Subscription subscribe = noParams.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SYBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onclick.error(e.getMessage().toString());
                    }

                    @Override
                    public void onNext(SYBean bean) {
                        data = bean.goods_list;
                        onclick.datasuccess(data);
                    }
                });
    }
}
