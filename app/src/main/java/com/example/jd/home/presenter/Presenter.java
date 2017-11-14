package com.example.jd.home.presenter;

import com.example.jd.home.bean.SYBean;
import com.example.jd.home.model.Imodel;
import com.example.jd.home.model.Model;
import com.example.jd.home.model.Onclick;
import com.example.jd.home.view.Iview;

import java.util.List;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public class Presenter {
    Imodel imodel;
    Iview iview;

    public Presenter( Iview iview) {

        this.iview = iview;
        imodel=new Model();
    }
    public  void getok(String url){
        imodel.Request(url, new Onclick() {
            @Override
            public void datasuccess(List<SYBean.GoodsListBean> list) {
                iview.showsuccess(list);
            }

            @Override
            public void error(String rr) {

            }
        });

    }
}
