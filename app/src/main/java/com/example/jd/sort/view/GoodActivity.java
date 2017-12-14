package com.example.jd.sort.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.jd.R;
import com.example.jd.api.Api;
import com.example.jd.sort.adapter.GoodAdapter;
import com.example.jd.sort.bean.DetialEvent;
import com.example.jd.sort.bean.GoodBean;
import com.example.jd.sort.presenter.GoodPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class GoodActivity extends AppCompatActivity implements GoodConstract.IGoodView {

    @Bind(R.id.good_rcv)
    RecyclerView goodRcv;
    //    @BindView(R.id.good_rcv)
//    RecyclerView goodRcv;
    private GoodPresenter goodPresenter;
    private int cid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        goodPresenter = new GoodPresenter(this);
        goodPresenter.LoadList(Api.BANNERURL, cid, 1, 0);

    }

    //
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void noEvent(DetialEvent messageEvent) {
        cid = messageEvent.getPscid();
//        Toast.makeText(GoodActivity.this,"cid"+ cid,Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void ShowList(List<GoodBean.DataBean> list) {
          GoodAdapter goodAdapter=new GoodAdapter(list,GoodActivity.this);
//        GoodAdapter goodAdapter = new GoodAdapter(list, GoodActivity.this);
        if (list != null) {
            goodRcv.setAdapter(goodAdapter);
            goodRcv.setLayoutManager(new GridLayoutManager(this, 2));
        }

    }

    @Override
    public void ShowError(String e) {
        Log.e("哈哈哈哈哈哈哈哈哈哈哈哈", e);
    }
}
