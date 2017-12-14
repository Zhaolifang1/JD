package com.example.jd.found.presenter;

import com.example.jd.found.bean.MoviesBean;
import com.example.jd.found.bean.XQBean;
import com.example.jd.found.model.SYIModel;
import com.example.jd.found.model.SYModel;
import com.example.jd.found.model.SYOnRequestListener;
import com.example.jd.found.view.SYIView;

import java.util.List;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public class SYPresenter implements SYIPresenter {
    private SYIView syiView;
    private SYIModel syiModel;

    public SYPresenter(SYIView syiView) {
        this.syiView = syiView;
        syiModel = new SYModel();
    }
    @Override
    public void loadList(String url) {
        syiModel.RequestData(url, new SYOnRequestListener() {
            @Override
            public void OnSuccess(List<MoviesBean.RetBean.ListBean> list) {
                syiView.showList(list);
            }

            @Override
            public void OnError(String e) {
               syiView.showError(e);
            }

            @Override
            public void OnIntoData(XQBean xqBean) {

            }
        });
    }
}
