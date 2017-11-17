package com.example.jd.sort.model;

import android.util.Log;

import com.example.jd.api.Api;
import com.example.jd.sort.bean.SortLeftBean;
import com.example.jd.sort.bean.SortRightBean;

import java.util.HashMap;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public class SortModel implements SortIModel {
    // 定义接口 用于保存数据
    SortLeftBean bean;

    // 保存右侧数据
    SortRightBean rightBean;

    public interface OnLeftFinish {
        void onFinish(SortLeftBean sortLeftBean);
    }

    private OnLeftFinish onLeftfinish;

    public SortModel(OnLeftFinish onLeftfinish, OnRightFinish onrightfinish) {
        this.onLeftfinish = onLeftfinish;
        this.onrightfinish = onrightfinish;
    }

    public interface OnRightFinish {
        void onRightFinish(SortRightBean sortRightBean);
    }

    private OnRightFinish onrightfinish;

    @Override
    public void GetLeftData() {
        Observable<SortLeftBean> sortLeftData = SortLeftRetroFactory.getInstance().getSortLeftData(Api.SORT_LEFT_PATH);
        sortLeftData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SortLeftBean>() {
                    @Override
                    public void onCompleted() {
                        Log.d("sort", "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("sort", "onError: ");
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(SortLeftBean sortLeftBean) {
                        Log.d("sort", "onNext: " + sortLeftBean.getMsg());
                        bean = sortLeftBean;
                        onLeftfinish.onFinish(sortLeftBean);
                    }
                });
    }

    @Override
    public void GetRightData(int cid) {
        HashMap<String,String> map = new HashMap<>();
        map.put("cid",cid+"");
        Log.d("id",cid+"");
        final Observable<SortRightBean> rightData = RightRetroFactory.getInstance().getSortRightData(Api.SORT_RIGHT_PATH, map);
        rightData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SortRightBean>() {
                    @Override
                    public void onCompleted() {
                        Log.d("sort", "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("sort", "onError: ");
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(SortRightBean sortRightBean) {
                        Log.d("sort", "onNextRight: "+bean.getData().get(0).getName()+"--");
                        rightBean = sortRightBean;
                        onrightfinish.onRightFinish(rightBean);
                    }
                });
    }
}
