package com.example.jd.sort.model;


import com.example.jd.api.ApiService;
import com.example.jd.sort.bean.GoodBean;
import com.example.jd.sort.view.GoodConstract;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;



public class GoodModel implements GoodConstract.IGoodModel {

    @Override
    public void requestData(String url, int pscid, int page, int sort, final GoodConstract.OnGoodListener onGoodListener) {
        Map<String, Integer> map = new HashMap<>();
        map.put("pscid", pscid);
        map.put("page", page);
        map.put("sort", sort);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ApiService apiServers = retrofit.create(ApiService.class);
//       apiServer.getRight("product/getProductCatagory",map);
        Observable<GoodBean> getgood = apiServers.getgood("product/getProducts", map);
        getgood.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GoodBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onGoodListener.onError(e.getMessage().toString());
                    }

                    @Override
                    public void onNext(GoodBean goodBean) {
                        List<GoodBean.DataBean> data = goodBean.getData();
                        onGoodListener.onSuccess(data);
                    }
                });
    }



}

