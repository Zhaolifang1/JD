package com.example.jd.sort.presenter;

import com.example.jd.sort.bean.SortLeftBean;
import com.example.jd.sort.bean.SortRightBean;
import com.example.jd.sort.model.SortModel;
import com.example.jd.sort.view.SortIView;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public class SortPresenter implements SortModel.OnLeftFinish,SortModel.OnRightFinish{
    private final SortIView sortIView;
    private final SortModel sortModel;

    public SortPresenter(SortIView iSortView) {
        this.sortIView = iSortView;
        sortModel = new SortModel(this,this);
    }
    @Override
    public void onFinish(SortLeftBean sortLeftBean) {
      sortIView.ShowLeftData(sortLeftBean);
    }
    public void GetleftData(){
        sortModel.GetLeftData();
    }
    public void GetRightData(int cid){
        sortModel.GetRightData(cid);
    }
    @Override
    public void onRightFinish(SortRightBean sortRightBean) {
      sortIView.ShowRightData(sortRightBean);
    }
}
