package com.example.jd.found.presenter;


import com.example.jd.found.bean.MoviesBean;
import com.example.jd.found.bean.XQBean;
import com.example.jd.found.model.SYOnRequestListener;
import com.example.jd.found.model.XQIModel;
import com.example.jd.found.model.XQModel;
import com.example.jd.found.view.XQIView;

import java.util.List;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public class XQPresenter {

    //定义view和model接口的变量
    private final XQIView xqiView;
    private final XQIModel xqModel;

    public XQPresenter(XQIView xqiView) {
        this.xqiView = xqiView;
        this.xqModel = new XQModel();
    }
    //传值到model中
    public void setMovieData(String url,String catalogId){
        xqModel.getHttpData(url, catalogId, new SYOnRequestListener() {
            @Override
            public void OnSuccess(List<MoviesBean.RetBean.ListBean> list) {

            }

            @Override
            public void OnError(String e) {

            }

            @Override
            public void OnIntoData(XQBean xqBean) {
              xqiView.getShowData(xqBean);
            }
        });
    }

}
