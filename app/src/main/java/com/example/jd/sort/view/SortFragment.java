package com.example.jd.sort.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.jd.R;
import com.example.jd.sort.adapter.SortLeftAdapter;
import com.example.jd.sort.adapter.SortRightAdapter;
import com.example.jd.sort.bean.SortLeftBean;
import com.example.jd.sort.bean.SortRightBean;
import com.example.jd.sort.presenter.SortPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public class SortFragment extends Fragment implements SortIView {


    @Bind(R.id.sort_left_listview)
    ListView sortLeftListview;
    @Bind(R.id.sort_right_listview)
    ListView sortRightListview;
    private View view;


    private SortPresenter presenter;
    private SortLeftAdapter leftAdapter;
    private SortRightAdapter right_adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fenlei, null);

        ButterKnife.bind(this, view);

        init();
        return view;
    }

    private void init() {


        presenter = new SortPresenter(this);
        /*初始化 默认展示左侧数据*/
        presenter.GetleftData();

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter = new SortPresenter(this);
        /*初始化 默认展示左侧数据*/
        presenter.GetleftData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @Override
    public void ShowLeftData(SortLeftBean sortLeftBean) {
        /*配置左侧适配器你*/
        leftAdapter = new SortLeftAdapter(sortLeftBean, getActivity());
        sortLeftListview.setAdapter(leftAdapter);
        /*左侧的监听事件*/
        leftClick();
    }

    @Override
    public void ShowRightData(SortRightBean sortRightBean) {
        /*展示右侧数据*/
        right_adapter = new SortRightAdapter(sortRightBean, getActivity());
        sortRightListview.setAdapter(right_adapter);


    }
    private void leftClick() {
      leftAdapter.setClickName(new SortLeftAdapter.ClickName() {
          @Override
          public void Clickname(int cid) {
              presenter.GetRightData(cid);
          }
      });
    }
}
