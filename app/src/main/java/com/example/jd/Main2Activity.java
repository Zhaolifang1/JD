package com.example.jd;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.jd.found.FoundActivity;
import com.example.jd.home.view.HomeFragment;
import com.example.jd.mine.view.MyFragment;
import com.example.jd.shop.view.ShopFragment;
import com.example.jd.sort.view.SortFragment;
import com.hjm.bottomtabbar.BottomTabBar;

public class Main2Activity extends AppCompatActivity {
    private BottomTabBar mb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mb =(BottomTabBar)findViewById(R.id.bottom_tab_bar);

        mb.init(getSupportFragmentManager())
                .setImgSize(50,50)
                .setFontSize(14)
                .setTabPadding(4,6,10)
                .setChangeColor(Color.RED,Color.DKGRAY)
                .addTabItem("首页",R.drawable.a_w, HomeFragment.class)
                .addTabItem("分类",R.drawable.a_7, SortFragment.class)
                .addTabItem("发现",R.drawable.a_q, FoundActivity.class)
                .addTabItem("购物车",R.drawable.a8c, ShopFragment.class)
                .addTabItem("我的",R.drawable.a_k, MyFragment.class)
                .isShowDivider(false)
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name) {

                    }
                });

    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        int tosome = getIntent().getIntExtra("to_shop",0);
//        mb.addTabItem("购物车",R.drawable.a8c, ShopFragment.class);
//        mb.setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
//            @Override
//            public void onTabChange(int position, String name) {
//                switch (position)
//                {
//                    case 3:
//
//                        break;
//                }
//            }
//        });
        //viewpager设置到购物车页面
//        viewPager.setCurrentItem(tosome);
    }
}
