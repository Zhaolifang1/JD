package com.example.jd;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView nav_iv;
    private Button nav_btn;
    int i = 3;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            nav_btn.setVisibility(View.VISIBLE);
            nav_btn.setText(i + "  跳过");
            nav_iv.setBackgroundResource(R.drawable.daohang);
            if (i == 0) {
                jump();
            }
            i--;
            sendEmptyMessageDelayed(0, 1000);

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏 第一种方法
        setContentView(R.layout.activity_welcome);
        initView();
        nav_iv.setBackgroundResource(R.drawable.azz);
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    sleep(1500);
                    handler.sendEmptyMessage(0);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeMessages(0);
    }

    private void initView() {
        nav_iv = (ImageView) findViewById(R.id.nav_iv);
        nav_btn = (Button) findViewById(R.id.nav_btn);

        nav_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nav_btn:
                jump();
                break;
        }
    }

    public void jump() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
        handler.removeMessages(0);

    }
}
