package com.example.jd.sort.presenter;


import com.example.jd.sort.bean.GoodBean;
import com.example.jd.sort.model.GoodModel;
import com.example.jd.sort.view.GoodConstract;

import java.util.List;

public class GoodPresenter implements GoodConstract.IGoodPresenter {
    GoodConstract.IGoodView iGoodView;
    GoodConstract.IGoodModel iGoodModel;
    public GoodPresenter(GoodConstract.IGoodView iGoodView) {
        this.iGoodView = iGoodView;
        iGoodModel = new GoodModel();
    }

    @Override
    public void LoadList(String url, int pscid, int page, int sort) {
        iGoodModel.requestData(url, pscid, page, sort, new GoodConstract.OnGoodListener() {
            @Override
            public void onSuccess(List<GoodBean.DataBean> list) {
                iGoodView.ShowList(list);
            }

            @Override
            public void onError(String e) {
                iGoodView.ShowError(e);
            }
        });
    }


}
