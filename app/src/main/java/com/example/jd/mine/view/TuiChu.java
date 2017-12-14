package com.example.jd.mine.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.jd.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TuiChu extends AppCompatActivity {

    @Bind(R.id.tuichu)
    Button tuichu;
    @Bind(R.id.ll_myInfo_head3)
    LinearLayout llMyInfoHead3;
    @Bind(R.id.ll_my_nick)
    LinearLayout llMyNick;

    @Bind(R.id.ll_myInfo_head4)
    LinearLayout llMyInfoHead4;
    @Bind(R.id.ll_my_sex)
    LinearLayout llMySex;
    @Bind(R.id.ll_myInfo_head5)
    LinearLayout llMyInfoHead5;
    @Bind(R.id.toux)
    ImageView toux;
    @Bind(R.id.ll_my_birth)
    LinearLayout llMyBirth;

    private String name = "登录/注册";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tui_chu);
        ButterKnife.bind(this);

        EventBus.getDefault().register(this);


    }

    //接收适配器传过来的值
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Shuju event) {


        String a = event.getA();
        if (a != null) {
            tuichu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    TuiChuaa tuiChuaa = new TuiChuaa(name);
                    EventBus.getDefault().postSticky(tuiChuaa);

                    finish();

                }
            });
        }


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }

    @OnClick(R.id.iv_cha)
    public void onViewClicked() {
        finish();
    }
}
