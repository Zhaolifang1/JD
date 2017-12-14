package com.example.jd.sort.view;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jd.MainActivity;
import com.example.jd.R;
import com.example.jd.api.Api;
import com.example.jd.api.ApiService;
import com.example.jd.sort.bean.GoodEvent;
import com.example.jd.sort.bean.XiangQingBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class XiangQing extends AppCompatActivity  {

        @Bind(R.id.goodsImg)
        ImageView goodsImg;
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
    @Bind(R.id.change)
    RelativeLayout change;


    private String pid;

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
        Observable<XiangQingBean> goodsInfo = apiService.getGoodsInfo(pid,"android");
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
                        String[] images = xiangQingBean.data.images.split("\\|");
                        Glide.with(XiangQing.this).load(images[0]).into(goodsImg);
//                        initPlayer();
                        goodsTitle.setText(xiangQingBean.data.title);
                        bargainPrice.setText("¥" + xiangQingBean.data.bargainPrice);
                        originalPrice.setText("原价 " + xiangQingBean.data.price);
                        originalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
                        salenum.setText("销量" + xiangQingBean.data.salenum);
                    }
                });
    }

//    private void initPlayer() {
////        //初始化播放器
//        player = new PlayerManager(this);
//        player.setFullScreenOnly(true);
//        player.setScaleType(PlayerManager.SCALETYPE_FILLPARENT);
//        player.playInFullScreen(true);
//        player.setPlayerStateListener(XiangQing.this);
//        player.play(url5);
//    }

    @Subscribe(threadMode = ThreadMode.POSTING, sticky = true)
    public void ononMoonStickyEvent( GoodEvent messageEvent) {
//        name = messageEvent.getPscid();
        pid = messageEvent.getPid();
//        Toast.makeText(XiangQing.this,"pid"+pid,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }



    @OnClick(R.id.gouwuche)
    public void onViewClicked() {
        Intent intent = new Intent(XiangQing.this,MainActivity.class);
        intent.putExtra("to_shop",3);
        startActivity(intent);
    }

}
