package com.example.jd.found;

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
import com.example.jd.found.adapter.SYMyAdapter;
import com.example.jd.found.bean.MoviesBean;
import com.example.jd.found.presenter.SYPresenter;
import com.example.jd.found.view.SYIView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public class FoundActivity extends Fragment implements SYIView{
    @Bind(R.id.recycler)
    RecyclerView recycler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_found, null);
        ButterKnife.bind(this, view);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recycler.setLayoutManager(linearLayoutManager);
//        Presenter presenter = new Presenter(this);
        SYPresenter syPresenter = new SYPresenter(this);
        syPresenter.loadList(Api.MOVIES);
//        presenter.loadList(Api.MOVIES);
        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    @Override
    public void showList(List<MoviesBean.RetBean.ListBean> list) {
        SYMyAdapter syMyAdapter = new SYMyAdapter(getContext(), list);
        recycler.setAdapter(syMyAdapter);
    }

    @Override
    public void showError(String e) {

    }
}
