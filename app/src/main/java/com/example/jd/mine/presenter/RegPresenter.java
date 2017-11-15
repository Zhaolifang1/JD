package com.example.jd.mine.presenter;

import com.example.jd.mine.bean.LoginBean;
import com.example.jd.mine.model.RegModel;
import com.example.jd.mine.view.MyRegIView;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public class RegPresenter implements RegModel.OnRegFinishLisenter{
    public static final String TAG="MineRegPresenter";


    private final MyRegIView myIRegView;
    private final RegModel RegModel ;

    public RegPresenter(MyRegIView myIRegView) {
        this.myIRegView = myIRegView;
        this.RegModel = new RegModel();
        RegModel.setOnRegFinishLisenter(this);
    }
    public void setRegInfo(String mobile,String password){
      RegModel.HttpReg(mobile,password);
    }
    @Override
    public void onRegFinish(LoginBean loginBean) {
        String code =loginBean.code;
        if (code.equals("0")){
            //成功
            myIRegView.onRegSuccess(code);
        }else if(code.equals("1")){
            //失败
            myIRegView.onRegFailed(code);
        }
    }
}
