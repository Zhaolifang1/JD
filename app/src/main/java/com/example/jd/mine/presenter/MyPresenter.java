package com.example.jd.mine.presenter;

import android.util.Log;

import com.example.jd.mine.bean.LoginBean;
import com.example.jd.mine.model.MyModel;
import com.example.jd.mine.view.MyIView;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public class MyPresenter implements MyModel.OnFinishLisenter{
    public static final String TAG="MinePresenter";

    private final MyIView myIView;
    private final MyModel myModel;

    public MyPresenter(MyIView myIView) {
        this.myIView = myIView;
        this.myModel = new MyModel();
        myModel.setOnFinishLisenter(this);
    }
    public void setLoginInfo(String mobile, String password) {
        //将数据传给model层
        Log.i("mobile--------------",mobile+"   "+password);
        myModel.HttpLogin(mobile,password);
    }
    @Override
    public void onLoginFinish(LoginBean loginBean) {
        Log.d(TAG, "onLoginFinish: =========="+loginBean.code);
        String code = loginBean.code;
        if (code.equals("0")){
            myIView.onLoginSuccess(code);
        }else if (code.equals("1")){
            myIView.onLoginFailed(code);
        }
    }
}
