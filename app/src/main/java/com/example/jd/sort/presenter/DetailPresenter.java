package com.example.jd.sort.presenter;

import com.example.jd.sort.bean.DetailsBean;
import com.example.jd.sort.model.DetailIModel;
import com.example.jd.sort.model.DetailModel;
import com.example.jd.sort.model.OnDetailListener;
import com.example.jd.sort.view.DetailIView;

import java.util.List;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public class DetailPresenter implements DetailIPresenter {
    //定义view和model接口的变量
     DetailIView detailIView;
     DetailIModel detailIModel;

    public DetailPresenter(DetailIView detailIView) {
        this.detailIView = detailIView;
        detailIModel = new DetailModel();

    }
    @Override
    public void getData(String url, int pscid, int page, int sort) {
      detailIModel.getHttpData(url, pscid, page, sort, new OnDetailListener() {
          @Override
          public void OnFinishListener(List<DetailsBean.DataBean> data) {
              detailIView.getShowData(data);
          }
      });
    }
}
