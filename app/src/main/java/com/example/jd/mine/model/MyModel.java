package com.example.jd.mine.model;

import android.util.Log;

import com.example.jd.mine.bean.LoginBean;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public class MyModel implements MyIModel {
    public static final String TAG="MyModel";

    // 定义接口变量
    private OnFinishLisenter onFinishLisenter;

    //定义接口
    public interface OnFinishLisenter{
        void onLoginFinish(LoginBean loginBean);
    }
    public void setOnFinishLisenter(OnFinishLisenter onFinishLisenter){
        this.onFinishLisenter = onFinishLisenter;
    }
    @Override
    public void HttpLogin(String mobile, String password) {
        Log.i("=====================",mobile+" "+password);
        Observable<LoginBean> login = RetroLoginFactory.getInstance().getLogin(mobile, password);
        //订阅
        login.subscribeOn(Schedulers.io())
                //切换到主线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onCompleted() {
                       Log.i("onCompleted","onCompleted()");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("onError","onError()");
                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        Log.i("onNext","onNext()"+loginBean.code);
                        onFinishLisenter.onLoginFinish(loginBean);
                    }
                });
    }
}
