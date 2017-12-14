package com.example.jd.sort.presenter;


import com.example.jd.sort.bean.FenleiLeftBean;
import com.example.jd.sort.bean.FenleiRightBean;
import com.example.jd.sort.model.FenleiRightIModel;
import com.example.jd.sort.model.FenleiRightModel;
import com.example.jd.sort.model.OnRequestListener;
import com.example.jd.sort.view.FenleiRightIView;

import java.util.List;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public class FenleiRightPresenter implements FenleiRightIPresenter {
    private FenleiRightIView fenleiRightIView;
    private FenleiRightIModel fenleiRightIModel;

    public FenleiRightPresenter(FenleiRightIView fenleiRightIView) {
        this.fenleiRightIView = fenleiRightIView;
        fenleiRightIModel = new FenleiRightModel();
    }
    @Override
    public void loadRightList(String url, int cid) {
        fenleiRightIModel.RequestRightData(url, cid, new OnRequestListener() {
            @Override
            public void OnSuccess(List<FenleiLeftBean.DataBean> list) {

            }

            @Override
            public void OnRightSuccess(List<FenleiRightBean.DataBean> list) {
                fenleiRightIView.showRight(list);
            }

            @Override
            public void OnError(String e) {

            }
        });
    }
}
