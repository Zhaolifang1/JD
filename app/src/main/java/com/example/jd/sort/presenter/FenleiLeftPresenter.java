package com.example.jd.sort.presenter;


import com.example.jd.sort.bean.FenleiLeftBean;
import com.example.jd.sort.bean.FenleiRightBean;
import com.example.jd.sort.model.FenleiLeftIModel;
import com.example.jd.sort.model.FenleiLeftModel;
import com.example.jd.sort.model.OnRequestListener;
import com.example.jd.sort.view.FenleiLeftIView;

import java.util.List;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public class FenleiLeftPresenter implements FenleileftIPresenter{
    private FenleiLeftIView fenleiLeftIView;
    private FenleiLeftIModel fenleiLeftIModel;

    public FenleiLeftPresenter(FenleiLeftIView fenleiLeftIView) {
        this.fenleiLeftIView = fenleiLeftIView;
        fenleiLeftIModel = new FenleiLeftModel();
    }
    @Override
    public void loadList(String url) {
         fenleiLeftIModel.RequestData(url, new OnRequestListener() {
             @Override
             public void OnSuccess(List<FenleiLeftBean.DataBean> list) {
                 fenleiLeftIView.showList(list);
             }

             @Override
             public void OnRightSuccess(List<FenleiRightBean.DataBean> list) {

             }

             @Override
             public void OnError(String e) {
               fenleiLeftIView.showError(e);
             }
         });

    }



}
