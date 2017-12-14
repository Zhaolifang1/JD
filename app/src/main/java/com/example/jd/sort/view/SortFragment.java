package com.example.jd.sort.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jd.R;
import com.example.jd.api.Api;
import com.example.jd.sort.adapter.FenleiRightAdapter;
import com.example.jd.sort.adapter.FenleileftAdapter;
import com.example.jd.sort.bean.FenleiLeftBean;
import com.example.jd.sort.bean.FenleiRightBean;
import com.example.jd.sort.presenter.FenleiLeftPresenter;
import com.example.jd.sort.presenter.FenleiRightPresenter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public class SortFragment extends Fragment implements FenleiLeftIView,FenleiRightIView{
    @Bind(R.id.fenleileftrecler)
    RecyclerView fenleileftrecler;
    @Bind(R.id.fenleirightrecler)
    RecyclerView fenleirightrecler;
    private int id;
    private FenleileftAdapter fenleileftAdapter;
    private FenleiRightPresenter fenleiRightPresenter;


    private View view;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fenlei, null);

        ButterKnife.bind(this, view);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        fenleileftrecler.setLayoutManager(linearLayoutManager);
        LinearLayoutManager linearLayoutManagerright=new LinearLayoutManager(getContext());
        fenleirightrecler.setLayoutManager(linearLayoutManagerright);
        FenleiLeftPresenter fenleiLeftPresenter = new FenleiLeftPresenter(this);
        fenleiLeftPresenter.loadList(Api.HOST);
        fenleiRightPresenter = new FenleiRightPresenter(this);
        fenleiRightPresenter.loadRightList(Api.HOST,1);


        return view;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void showList(final List<FenleiLeftBean.DataBean> list) {
        fenleileftAdapter = new FenleileftAdapter(getContext(), list);
        fenleileftrecler.setAdapter(fenleileftAdapter);
        fenleileftAdapter.setOnClickListener(new FenleileftAdapter.OnClickListener() {
            @Override
            public void OnDianji(View v, int position) {
                int cid = list.get(position).cid;
                fenleiRightPresenter.loadRightList(Api.HOST,cid);
            }
        });
    }

    @Override
    public void showError(String e) {

    }

    @Override
    public void showRight(List<FenleiRightBean.DataBean> list) {
        FenleiRightAdapter fenleiRightAdapter = new FenleiRightAdapter(getContext(), list);
        fenleirightrecler.setAdapter(fenleiRightAdapter);
    }


}
