package com.example.jd.found.view;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

import com.example.jd.R;
import com.example.jd.api.Api;
import com.example.jd.common.PlayerManager;
import com.example.jd.found.bean.SyMessageEvent;
import com.example.jd.found.bean.XQBean;
import com.example.jd.found.presenter.XQPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SecondActivity extends AppCompatActivity implements PlayerManager.PlayerStateListener, XQIView{
    @Bind(R.id.tv)
    TextView tv;
//    @Bind(R.id.tab)
//    TabLayout tab;
//    @Bind(R.id.viewpager)
    ViewPager viewpager;
    private XQPresenter xqPresenter;
    private String[] tabname=new String[]{"简介","评论"};

    private String id;
    private PlayerManager player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initPlayer();
        xqPresenter = new XQPresenter(this);
        xqPresenter.setMovieData(Api.MOVIES, id);
//        final List<Fragment> flist=new ArrayList<>();
//        for (int i = 0; i < flist.size(); i++) {
//            tab.addTab(tab.newTab().setText(tabname[i]));
//        }
//        flist.add(new Fragment_jianjie());
//        flist.add(new Fragment_pinglun());
//        tab.setupWithViewPager(viewpager);
//        viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
//            @Override
//            public Fragment getItem(int position) {
//                return flist.get(position);
//            }
//
//            @Override
//            public int getCount() {
//                return flist.size();
//            }
//
//            @Override
//            public CharSequence getPageTitle(int position) {
//                return tabname[position];
//            }
//        });
    }
    private void initPlayer() {

        //初始化播放器
        player = new PlayerManager(this);
        player.setFullScreenOnly(true);
        player.setScaleType(PlayerManager.SCALETYPE_FILLPARENT);
        player.playInFullScreen(true);
        player.setPlayerStateListener(this);
//        player.play(url5);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (player.gestureDetector.onTouchEvent(event))
            return true;
        return super.onTouchEvent(event);
    }

    //在onStart调用register后，执行消息
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(SyMessageEvent event) {
        id = event.getId();

        String title = event.getTitle();
        tv.setText(title);


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError() {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onPlay() {

    }

    @Override
    public void getShowData(XQBean xqBean) {
        String hdurl = xqBean.ret.HDURL;
        Log.d("hdurl", hdurl + " ");
        player.play(hdurl);
    }
}
