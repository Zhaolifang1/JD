package com.example.jd.mine.model;

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

public class RegModel implements RegIModel {

    public static final String TAG="MineRegModel";

    // 定义接口变量
    private OnRegFinishLisenter onRegFinishLisenter;

    //定义接口
    public interface OnRegFinishLisenter{
        void onRegFinish(LoginBean loginBean);
    }
    public void setOnRegFinishLisenter(OnRegFinishLisenter onRegFinishLisenter){
        this.onRegFinishLisenter = onRegFinishLisenter;
    }
    @Override
    public void HttpReg(String mobile, String password) {
        Observable<LoginBean> reg = RetroLoginFactory.getInstance().getRegister(mobile,password);
        reg.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                           onRegFinishLisenter.onRegFinish(loginBean);
                    }
                });
    }
}
