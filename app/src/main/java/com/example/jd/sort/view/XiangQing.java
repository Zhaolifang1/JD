package com.example.jd.sort.view;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.jd.R;
import com.example.jd.api.Api;
import com.example.jd.api.ApiService;
import com.example.jd.app.MessageEvent;
import com.example.jd.ijkplayer.common.PlayerManager;
import com.example.jd.sort.bean.XiangQingBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class XiangQing extends AppCompatActivity implements PlayerManager.PlayerStateListener{

    //    @Bind(R.id.goodsImg)
//    ImageView goodsImg;
    @Bind(R.id.goodsTitle)
    TextView goodsTitle;
    @Bind(R.id.bargainPrice)
    TextView bargainPrice;
    @Bind(R.id.original_price)
    TextView originalPrice;
    @Bind(R.id.salenum)
    TextView salenum;
    @Bind(R.id.gongyingshang)
    TextView gongyingshang;
    @Bind(R.id.dianpu)
    TextView dianpu;
    @Bind(R.id.guanzhu)
    CheckBox guanzhu;
    @Bind(R.id.gouwuche)
    TextView gouwuche;
    @Bind(R.id.add_Buy)
    Button addBuy;

    String name = "";

    private PlayerManager player;
    private String url1 = "rtmp://203.207.99.19:1935/live/CCTV5";
    private String url2 = "http://zv.3gv.ifeng.com/live/zhongwen800k.m3u8";
    private String url3 = "rtsp://184.72.239.149/vod/mp4:BigBuckBunny_115k.mov";
    private String url4 = "http://ips.ifeng.com/video19.ifeng.com/video09/2014/06/16/1989823-102-086-0009.mp4";
    private String url5 = "http://mp4.vjshi.com/2013-05-28/2013052815051372.mp4";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang_qing);
        ButterKnife.bind(this);
        EventBus.getDefault().register(XiangQing.this);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.LOGIN_REG)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<XiangQingBean> goodsInfo = apiService.getGoodsInfo(name + "1");
        goodsInfo.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<XiangQingBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(XiangQingBean xiangQingBean) {
//                        String[] images = xiangQingBean.data.images.split("\\|");
//                        Glide.with(XiangQing.this).load(images[0]).into(goodsImg);
                        initPlayer();
                        goodsTitle.setText(xiangQingBean.data.title);
                        bargainPrice.setText("¥" + xiangQingBean.data.bargainPrice);
                        originalPrice.setText("原价 " + xiangQingBean.data.price);
                        originalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
                        salenum.setText("销量" + xiangQingBean.data.salenum);
                    }
                });
    }
    private void initPlayer() {
//        //初始化播放器
        player = new PlayerManager(this);
        player.setFullScreenOnly(true);
        player.setScaleType(PlayerManager.SCALETYPE_FILLPARENT);
        player.playInFullScreen(true);
        player.setPlayerStateListener(XiangQing.this);
        player.play(url5);
    }
    @Subscribe(threadMode = ThreadMode.POSTING, sticky = true)
    public void ononMoonStickyEvent(MessageEvent messageEvent) {
        name = messageEvent.getMessage();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (player.gestureDetector.onTouchEvent(event))
            return true;
        return super.onTouchEvent(event);
    }

    @Override
    protected void onStop() {
        super.onStop();
        player.stop();
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
}
