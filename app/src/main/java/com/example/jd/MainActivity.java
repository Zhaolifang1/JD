package com.example.jd;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.jd.home.view.HomeFragment;
import com.example.jd.home.view.NoScrollViewPager;
import com.example.jd.mine.view.MyFragment;
import com.example.jd.shop.view.ShopFragment;
import com.example.jd.sort.view.SortFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private NoScrollViewPager noscrollViewPager;
    private RadioGroup radiogroup;
    private List<Fragment> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_main);
        initView();
        OnClick();
        noscrollViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
    }
    //初始化
    private void initView() {
        noscrollViewPager = (NoScrollViewPager) findViewById(R.id.noscrollViewPager);
        radiogroup = (RadioGroup) findViewById(R.id.radiogroup);
        list = new ArrayList<>();
        list.add(new HomeFragment());
        list.add(new SortFragment());
        list.add(new ShopFragment());
        list.add(new MyFragment());
    }
    private void OnClick(){
        //设置默认第一个选中
        for (int i = 0; i < list.size(); i++) {
            RadioButton radioButton = (RadioButton) radiogroup.getChildAt(i);
            if (i == 0) {
                noscrollViewPager.setCurrentItem(0);
                radioButton.setChecked(true);
            } else {
                radioButton.setChecked(false);
            }
        }
        //点击切换页面
        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                for (int i = 0; i < list.size(); i++) {
                    RadioButton button = (RadioButton) radiogroup.getChildAt(i);
                    if (button.isChecked())
                        noscrollViewPager.setCurrentItem(i, false);
                }
            }
        });
    }
}
