package com.example.jd.sort.model;

import com.example.jd.api.ApiService;
import com.example.jd.sort.bean.DetailsBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class DetailModel implements DetailIModel {

    @Override
    public void getHttpData(String url, int pscid, int page, int sort, final OnDetailListener onDetailListener) {
        Map<String,Integer> map=new HashMap<>();
        map.put("pscid",pscid);
        map.put("page",page);
        map.put("sort",sort);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        //动态代理得到网络接口
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<DetailsBean> detical = apiService.getDetical("product/getProducts", map);
        detical.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DetailsBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(DetailsBean detailsBean) {
                        List<DetailsBean.DataBean> data = detailsBean.data;
                        onDetailListener.OnFinishListener(data);
                    }
                });
    }
}
